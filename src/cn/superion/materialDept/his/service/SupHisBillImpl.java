package cn.superion.materialDept.his.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.superion.center.clinic.dao.ClinicItemDictDAO;
import cn.superion.center.clinic.entity.ClinicItemDict;
import cn.superion.center.material.dao.CdMaterialDictDAO;
import cn.superion.center.material.entity.CdMaterialDict;
import cn.superion.materialDept.dao.InpPatsBillDetailDAO;
import cn.superion.materialDept.entity.InpPatsBillDetail;
import cn.superion.materialDept.entity.InpPatsBillDetailId;
import cn.superion.materialDept.entity.MaterialPatsDetail;
import cn.superion.materialDept.entity.PatsBillMaster;
import cn.superion.system.dao.SysUnitInforDAO;
import cn.superion.util.SessionUtil;
/**
 * 世一HIS病人费用服务实现
 * @author 曹国魁
 *
 */
public class SupHisBillImpl implements IHisBill {
	private Log log = LogFactory.getLog(SupHisBillImpl.class);
	private InpPatsBillDetailDAO inpPatsBillDetailDAO; 
	private ClinicItemDictDAO clinicItemDictDAO;
	private SysUnitInforDAO sysUnitInforDAO;
	private CdMaterialDictDAO cdMaterialDictDAO;
	
	public CdMaterialDictDAO getCdMaterialDictDAO() {
		return cdMaterialDictDAO;
	}
	public void setCdMaterialDictDAO(CdMaterialDictDAO cdMaterialDictDAO) {
		this.cdMaterialDictDAO = cdMaterialDictDAO;
	}
	public SysUnitInforDAO getSysUnitInforDAO() {
		return sysUnitInforDAO;
	}
	public void setSysUnitInforDAO(SysUnitInforDAO sysUnitInforDAO) {
		this.sysUnitInforDAO = sysUnitInforDAO;
	}
	public ClinicItemDictDAO getClinicItemDictDAO() {
		return clinicItemDictDAO;
	}
	public void setClinicItemDictDAO(ClinicItemDictDAO clinicItemDictDAO) {
		this.clinicItemDictDAO = clinicItemDictDAO;
	}
	public InpPatsBillDetailDAO getInpPatsBillDetailDAO() {
		return inpPatsBillDetailDAO;
	}
	public void setInpPatsBillDetailDAO(InpPatsBillDetailDAO inpPatsBillDetailDAO) {
		this.inpPatsBillDetailDAO = inpPatsBillDetailDAO;
	}
	@Override
	public void save(PatsBillMaster master, List<MaterialPatsDetail> details) {
		if(details != null && !details.isEmpty()){
			String unitsCode = master.getUnitsCode();
			MaterialPatsDetail mpd = details.get(0);
			boolean isRed = mpd.getAmount() < 0;
			String patientId = master.getPatientId();
			//Byte visitId = mpd.getVisitId();
			
			//蓝字单据时，校验hisClass,hisCode非空；退费时校验蓝字单据结算状态
			for(MaterialPatsDetail redMpd : details){
				if(isRed){
				String hisBillNo = redMpd.getHisBillNo();
				if(hisBillNo == null || "".equals(hisBillNo)){
					log.warn("病人使用材料明细记录[系统标识号:"+redMpd.getAutoId()+"]的HIS费用单据号为空，不能查询原蓝字单据，也不能写红字！");
					continue;
				}
				Integer itemNo = Integer.valueOf(hisBillNo);
				InpPatsBillDetail blueBillDetail = inpPatsBillDetailDAO.findById(new InpPatsBillDetailId(unitsCode,patientId, itemNo));
				String rcptNo = blueBillDetail.getSettledRcptNo();
				if(rcptNo != null && !"".equals(rcptNo) && !"0".equals(rcptNo)){
					throw new RuntimeException(blueBillDetail.getItemName()+"(费用项目序号:"+itemNo+")费用已结算(结算收据号:"+rcptNo+")，不能退费！");
				}
				}
			}
				
			inpPatsBillDetailDAO.lockPatBill(unitsCode,patientId);
			int curItemNo = inpPatsBillDetailDAO.getCurItemNo(unitsCode,patientId);
			for(MaterialPatsDetail detail : details){
				InpPatsBillDetail bd = buildHisBillDetail(master,detail,++curItemNo);
				detail.setHisBillNo(String.valueOf(curItemNo));
				inpPatsBillDetailDAO.save(bd);
			}
		}
	}
	
	private InpPatsBillDetail buildHisBillDetail(PatsBillMaster master,
			MaterialPatsDetail detail,Integer itemNo) {
		InpPatsBillDetail bd = new InpPatsBillDetail(new InpPatsBillDetailId(master.getUnitsCode(),master.getPatientId(),itemNo),detail.getHisClass(),detail.getHisCode());
		bd.setAmount(detail.getAmount());
		
		String unitsCode=master.getUnitsCode();
		String materialId=detail.getMaterialId();
		//如果当前病人使用的耗材不是来自本院的入库，则需要重新查询耗材信息
		if(detail.getCurrentReceiveSign()!=null && detail.getCurrentReceiveSign().equals("0")){
			List<CdMaterialDict> materialDictList=cdMaterialDictDAO.findByMaterialCode(unitsCode, detail.getMaterialCode());
			if(materialDictList!=null && materialDictList.size()>0){
				CdMaterialDict material=materialDictList.get(0);
				bd.setItemName(material.getMaterialName());
				bd.setGeneralName(material.getMaterialName());
				bd.setItemSpec(material.getHisSpec());
				bd.setUnits(material.getHisUnits());
				bd.setUnitPrice(material.getRetailPrice());
				bd.setCosts(detail.getRetailPrice() * detail.getAmount());
				bd.setCharges(detail.getRetailPrice() * detail.getAmount());
				bd.setProduceCorporation(detail.getFactoryCode());
				materialId=material.getMaterialId();
			}
			else{
				throw new RuntimeException("单位[unitsCode:"+unitsCode+"]下没有物资[code："+detail.getMaterialCode()+"]！");
			}
		}
		else
		{
			bd.setItemName(detail.getMaterialName());
			bd.setGeneralName(detail.getMaterialName());
			bd.setItemSpec(detail.getHisSpec());
			bd.setUnits(detail.getHisUnits());
			bd.setUnitPrice(detail.getRetailPrice());
			bd.setCosts(detail.getRetailMoney());
			bd.setCharges(detail.getRetailMoney());
			bd.setProduceCorporation(detail.getFactoryCode());
		}
		ClinicItemDict clinicItem=clinicItemDictDAO.findByItemId(materialId);
		if(clinicItem == null){
			throw new RuntimeException("物资[id："+materialId+"]没有对应的治疗项目！");
		}
		
		bd.setItemCode(clinicItem.getItemId());
		bd.setItemClass(clinicItem.getItemClass());
		bd.setItemSpec(clinicItem.getItemSpec());
		bd.setUnits(clinicItem.getItemUnits());
		bd.setClassOnAccount(clinicItem.getClassOnAccount());
		bd.setClassOnInpRcpt(clinicItem.getClassOnInpRcpt());
		bd.setClassOnReckoning(clinicItem.getClassOnReckoning());
		bd.setClassOnMr(clinicItem.getClassOnMr());
		bd.setInsurancePay(clinicItem.getInsurancePay());
		
		//付数
		bd.setQuantity(1);
		//非药品
		bd.setDrugSign("0");
		//纳入库存管理
		bd.setStorageManageSign("0");
		//开单科室，即执行科室
		bd.setOrderedDept(master.getOrderedDept());
		//开单医生，即执行医生
		bd.setOrderedDoctor(master.getOrderedDoctor());
		bd.setPerformedDept(master.getPerformedDept());
		bd.setPerformedDoctor(master.getPerformedDoctor());
		//计价来源
		bd.setBillingSource("10");
		bd.setBillingRcptNo(detail.getAutoId());
		bd.setBillingDateTime(new Date());
		bd.setOperator(SessionUtil.getPersonId());
		//退费标志
		bd.setRefundSign("0");
		//传输标志
		bd.setTransferSign("0");
		//结算收据号
		bd.setSettledRcptNo("0");
		return bd;
	}
	@Override
	public List<MaterialPatsDetail> writeRed(PatsBillMaster master,
			List<MaterialPatsDetail> blueDetails) {
		List<MaterialPatsDetail> redDetails = new ArrayList<MaterialPatsDetail>();
		String unitsCode = master.getUnitsCode();
		String patientId = master.getPatientId();
		inpPatsBillDetailDAO.lockPatBill(unitsCode,patientId);
		int curItemNo = inpPatsBillDetailDAO.getCurItemNo(unitsCode,patientId);
		for(MaterialPatsDetail blueDetail : blueDetails){
			String hisBillNo = blueDetail.getHisBillNo();
			if(hisBillNo == null || "".equals(hisBillNo)){
				log.warn("病人使用材料明细记录[系统标识号:"+blueDetail.getAutoId()+"]的HIS费用单据号为空，不能查询原蓝字单据，也不能写红字！");
				continue;
			}
			//校验蓝字单据结算状态
			Integer itemNo = Integer.valueOf(hisBillNo);
			InpPatsBillDetail blueBillDetail = inpPatsBillDetailDAO.findById(new InpPatsBillDetailId(unitsCode,patientId, itemNo));
			String rcptNo = blueBillDetail.getSettledRcptNo();
			if(rcptNo != null && !"".equals(rcptNo) && !"0".equals(rcptNo)){
				throw new RuntimeException(blueBillDetail.getItemName()+"(费用项目序号:"+itemNo+")费用已结算(结算收据号:"+rcptNo+")，不能退费！");
			}
			//改写退费标志
			inpPatsBillDetailDAO.updateRefundSign(unitsCode,patientId, itemNo);
			
			InpPatsBillDetail redBill = blueBillDetail.writeRed();
			redBill.setRefundSign("1");
			redBill.setRemark(redBill.getId().getInpNo()+redBill.getId().getItemNo());
			redBill.getId().setItemNo(++curItemNo);
			inpPatsBillDetailDAO.save(redBill);
			MaterialPatsDetail redDetail = blueDetail.writeRedPatsDetail();
			redDetail.setHisBillNo(String.valueOf(curItemNo));
			redDetails.add(redDetail);
		}
		return redDetails;
	}

}
