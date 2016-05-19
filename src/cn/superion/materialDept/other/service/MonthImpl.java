package cn.superion.materialDept.other.service;

import java.util.List;
import java.util.Date;

import cn.superion.base.ReObject;
import cn.superion.center.material.dao.CdMaterialDictDAO;
import cn.superion.center.material.entity.CdMaterialDict;
import cn.superion.material.dao.MaterialMonthDAO;
import cn.superion.material.dao.MaterialRdsStockDAO;
import cn.superion.materialDept.dao.VMaterialRdsDeptDAO;
import cn.superion.material.entity.MaterialMonth;
import cn.superion.material.entity.MaterialRdsStock;
import cn.superion.system.entity.SysUser;
import cn.superion.util.DateUtil;
import cn.superion.util.SessionUtil;
/**
 * 月末结账服务实现
 * @author 曹国魁
 *
 */
public class MonthImpl implements IMonth {
	private CdMaterialDictDAO cdMaterialDictDAO;
	private MaterialMonthDAO materialMonthDAO;
	private MaterialRdsStockDAO materialRdsStockDAO;
	private VMaterialRdsDeptDAO vMaterialRdsDeptDAO;
	
	public CdMaterialDictDAO getCdMaterialDictDAO() {
		return cdMaterialDictDAO;
	}

	public void setCdMaterialDictDAO(CdMaterialDictDAO cdMaterialDictDAO) {
		this.cdMaterialDictDAO = cdMaterialDictDAO;
	}

	public MaterialMonthDAO getMaterialMonthDAO() {
		return materialMonthDAO;
	}

	public void setMaterialMonthDAO(MaterialMonthDAO materialMonthDAO) {
		this.materialMonthDAO = materialMonthDAO;
	}

	public MaterialRdsStockDAO getMaterialRdsStockDAO() {
		return materialRdsStockDAO;
	}

	public void setMaterialRdsStockDAO(MaterialRdsStockDAO materialRdsStockDAO) {
		this.materialRdsStockDAO = materialRdsStockDAO;
	}

	public VMaterialRdsDeptDAO getvMaterialRdsDeptDAO() {
		return vMaterialRdsDeptDAO;
	}

	public void setvMaterialRdsDeptDAO(VMaterialRdsDeptDAO vMaterialRdsDeptDAO) {
		this.vMaterialRdsDeptDAO = vMaterialRdsDeptDAO;
	}

	@Override
	public ReObject cancelMonth(String strYearMonth) {
		ReObject ro = new ReObject("取消结账");
		SysUser user = SessionUtil.getSysUser();
		String unitsCode = user.getUnitsCode();
		String personId = user.getPersonId();
		String storageCode = user.getDeptCode();
		//更新月末结账记录
		MaterialMonth mm = materialMonthDAO.findByStorageAndMonth(unitsCode,storageCode,strYearMonth);
		if(mm == null){
			throw new RuntimeException("不存在月末结账记录，不能删除！");
		}
		if("0".equals(mm.getAccountSign())){
			throw new RuntimeException("仓库["+storageCode+"]在"+strYearMonth+"未结账，不能取消结账！");
		}
		mm.setAccountSign("0");
		mm.setCreateDate(new Date());
		mm.setCreatePerson(personId);
		//删除收发存汇总记录
		materialRdsStockDAO.deleteByStorageAndMonth(unitsCode,storageCode,strYearMonth);
		return ro;
	}

	@Override
	public ReObject findMonth(String strYear) {
		ReObject ro = new ReObject("查找当前年度的月末结账记录列表");
		SysUser user = SessionUtil.getSysUser();
		String unitsCode = user.getUnitsCode();
		String storageCode = user.getDeptCode();
		List<MaterialMonth> data = materialMonthDAO.findByStorageAndYear(unitsCode,storageCode,strYear);
		if(data.isEmpty()){
			int y = Integer.parseInt(strYear);
			int curMonth = 12;
			String curYear = DateUtil.getCurrentYear();
			if(curYear.equals(strYear)){
				curMonth = Integer.parseInt(DateUtil.getCurrentMonth());
			}
			for(int i=1;i<=curMonth;i++){
				MaterialMonth mm = new MaterialMonth();
				mm.setUnitsCode(unitsCode);
				mm.setStorageCode(storageCode);
				mm.setYearMonth(strYear+"-"+DateUtil.lpad(i));
				mm.setStartDate(DateUtil.getStartDate(y, i));
				mm.setEndDate(DateUtil.getEndDate(y, i));
				mm.setAccountSign("0");
				data.add(mm);
			}
		}
		ro.setData(data);	
		return ro;
	}

	@Override
	public ReObject saveMonth(MaterialMonth materialMonth) {
		ReObject ro = new ReObject("生成收发存汇总记录表");
		SysUser user = SessionUtil.getSysUser();
		String unitsCode = user.getUnitsCode();
		String personId = user.getPersonId();
		String storageCode = materialMonth.getStorageCode();
		String yearMonth = materialMonth.getYearMonth();
		Date startDate = materialMonth.getStartDate();
		Date endDate = materialMonth.getEndDate();
		Date curDate = new Date();
		//更新月末结账记录
		MaterialMonth mm = materialMonthDAO.findByStorageAndMonth(unitsCode,storageCode,yearMonth);
		if(mm == null){
			materialMonth.setAccountSign("1");
			materialMonth.setCreateDate(curDate);
			materialMonth.setCreatePerson(personId);
			materialMonthDAO.save(materialMonth);
		}else{
			if("1".equals(mm.getAccountSign())){
				throw new RuntimeException("仓库["+storageCode+"]在"+yearMonth+"已结账，不能再次结账！");
			}else{
				mm.setAccountSign("1");
				mm.setCreateDate(curDate);
				mm.setCreatePerson(personId);
			}
		}
		//写收发存汇总记录
		List<CdMaterialDict> dictList = cdMaterialDictDAO.findAll();
		for(CdMaterialDict dict : dictList){
			String materialId = dict.getMaterialId();
			//计算期初数量，上个月的结存数量
			String previousMonth = DateUtil.getPreviousMonth(yearMonth);
			MaterialRdsStock preMonthStock = materialRdsStockDAO.findByUniqueIndex(unitsCode,storageCode,previousMonth,materialId);
			Double initAmount = preMonthStock==null?0d:preMonthStock.getCurrentStockAmount();
			Object[] objs = vMaterialRdsDeptDAO.addUpRdsAmountMoney(unitsCode,storageCode,startDate,endDate,materialId);
			nullToZeros(objs);
			//采购入库数量
			Double receiveAmount = objs == null ? 0d : Double.valueOf(objs[0].toString());
			//其他入库数量
			Double otherReceiveAmount = objs == null ? 0d : Double.valueOf(objs[1].toString());
			//领用出库数量
			Double deliveryAmount = objs == null ? 0d : Double.valueOf(objs[2].toString());
			//其他出库数量
			Double deliveryOtherAmount = objs == null ? 0d : Double.valueOf(objs[3].toString());
			//进价金额
			Double tradeMoney = objs == null ? 0d : Double.valueOf(objs[4].toString());
			//平均进价
			Double tradePrice = tradeMoney/(receiveAmount+otherReceiveAmount);
			tradePrice=tradePrice.isNaN()?0d:tradePrice;
			//售价金额
			Double retailMoney = objs == null ? 0d : Double.valueOf(objs[5].toString());
			//平均售价
			Double retailPrice = retailMoney/(deliveryAmount+deliveryOtherAmount);
			retailPrice=retailPrice.isNaN()?0d:retailPrice;
			//计算结存数量 
			Double currentStockAmount = initAmount + receiveAmount + otherReceiveAmount - deliveryAmount - deliveryOtherAmount;
			MaterialRdsStock stock = new MaterialRdsStock();
			stock.setUnitsCode(unitsCode);
			stock.setStorageCode(storageCode);
			stock.setYearMonth(yearMonth);
			stock.setMaterialClass(dict.getMaterialClass());
			stock.setMaterialId(materialId);
			stock.setMaterialCode(dict.getMaterialCode());
			stock.setMaterialName(dict.getMaterialName());
			stock.setMaterialSpec(dict.getMaterialSpec());
			stock.setMaterialUnits(dict.getMaterialUnits());
			stock.setFactoryCode(dict.getFactoryCode());
			stock.setTradePrice(tradePrice);
			stock.setTradeMoney(tradeMoney);
			stock.setRetailPrice(retailPrice);
			stock.setRetailMoney(retailMoney);
			stock.setInitAmount(initAmount);
			stock.setReceiveAmount(receiveAmount);
			stock.setOtherReceiveAmount(otherReceiveAmount);
			stock.setDeliveryAmount(deliveryAmount);
			stock.setDeliveryOtherAmount(deliveryOtherAmount);
			stock.setCurrentStockAmount(currentStockAmount);
			materialRdsStockDAO.save(stock);
		}
		return ro;
	}
    private void nullToZeros(Object[] objs){
    	for(int i=0;i<objs.length;i++){
    		if(objs[i]==null){
    			objs[i]="0";
    		}
    	}
    }
}
