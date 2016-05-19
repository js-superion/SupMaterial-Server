package cn.superion.materialAcct.daily.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.base.ReadPropertiesFile;
import cn.superion.center.config.dao.CdSysParamDAO;
import cn.superion.center.config.entity.CdSysParam;
import cn.superion.center.material.dao.CdMaterialDictDAO;
import cn.superion.center.material.entity.CdMaterialDict;
import cn.superion.material.dao.MaterialAcctCurrentRcptNoDAO;
import cn.superion.material.dao.MaterialChangePriceDetailDAO;
import cn.superion.material.dao.MaterialChangePriceMasterDAO;
import cn.superion.material.dao.MaterialCurrentStockDAO;
import cn.superion.material.entity.MaterialAcctCurrentRcptNo;
import cn.superion.material.entity.MaterialChangePriceDetail;
import cn.superion.material.entity.MaterialChangePriceMaster;
import cn.superion.system.entity.SysUser;
import cn.superion.util.DateUtil;
import cn.superion.util.SessionUtil;

public class ChangePriceImpl implements IChangePrice {
	
	public static final String UNITS_CODE_SOUTH = ReadPropertiesFile
	.getValue("UNITS_CODE_SOUTH");
	public static final String UNITS_CODE_NORTH = ReadPropertiesFile
	.getValue("UNITS_CODE_NORTH");
	public static final String SOUTH_STORAGE = ReadPropertiesFile
	.getValue("SOUTH_STORAGE");
	public static final String NORTH_STORAGE = ReadPropertiesFile
	.getValue("NORTH_STORAGE");
	private MaterialChangePriceMasterDAO materialChangePriceMasterDAO;
	private MaterialChangePriceDetailDAO materialChangePriceDetailDAO;
	private CdSysParamDAO cdSysParamDAO;
	private MaterialAcctCurrentRcptNoDAO materialAcctCurrentRcptNoDAO;
	private MaterialCurrentStockDAO materialCurrentStockDAO;
	private CdMaterialDictDAO cdMaterialDictDAO;

	/**
	 * 检查者：周作建 2011.06.21
	 */
	@Override
	public ReObject del(String fstrAutoId) {
		ReObject ro = new ReObject("删除未审核的调价记录");

		MaterialChangePriceMaster master = materialChangePriceMasterDAO
				.findById(fstrAutoId);
		if (master == null) {
			ro.setError("该调价记录单不存在！");
			return ro;
		}
		if ("1".equals(master.getCurrentStatus())) {
			ro.setError("该调价记录已审核，您不能进行删除！");
			return ro;
		}

		materialChangePriceDetailDAO.delByMainAutoId(fstrAutoId);
		materialChangePriceMasterDAO.delete(master);
		return ro;

	}

	/**
	 * 检查者：周作建 2011.06.21
	 */
	@Override
	public ReObject findByConditon(ParameterObject fparameter) {
		ReObject ro = new ReObject();
		ro.setAction("查询调价记录单");

		Map<String, Object> map = fparameter.getConditions();
		String unitsCode = SessionUtil.getUnitsCode();
		List<String> masterAutoId = materialChangePriceMasterDAO
				.findAutoIdsByCondition(unitsCode, map);
		ro.setData(masterAutoId);
		return ro;
	}

	/**
	 * 检查者：周作建 2011.06.21
	 */
	@Override
	public ReObject findByAutoId(String fstrAutoId) {
		ReObject ro = new ReObject("根据查询调价明细列表");

		MaterialChangePriceMaster master = materialChangePriceMasterDAO
				.findById(fstrAutoId);
		List<MaterialChangePriceDetail> detail = materialChangePriceDetailDAO
				.findByProperty("mainAutoId", fstrAutoId);

		List<Object> data = new ArrayList<Object>();
		data.add(master);
		data.add(detail);
		ro.setData(data);
		return ro;
	}

	/**
	 * 检查者：周作建 2011.06.21
	 */
	@Override
	public ReObject verify(String fstrAutoId) {
		ReObject reObject = new ReObject("审核调价信息");

		MaterialChangePriceMaster master = materialChangePriceMasterDAO
				.findById(fstrAutoId);
		if (master == null) {
			reObject.setError("该调价记录不存在!");
			return reObject;
		}
		if ("1".equals(master.getCurrentStatus())) {
			reObject.setError("该调价记录已审核!");
			return reObject;
		}

		master.setCurrentStatus("1");
		master.setVerifier(SessionUtil.getPersonId());
		master.setVerifyDate(new Date());

		materialChangePriceMasterDAO.attachDirty(master);

		List<Object> data = new ArrayList<Object>();
		data.add(master);
		reObject.setData(data);
		return reObject;
	}

	/**
	 * 检查者：周作建 2011.06.21
	 */
	@Override
	public ReObject save(MaterialChangePriceMaster master,
			List<MaterialChangePriceDetail> details) {
		
		ReObject ro = new ReObject();
		ro.setAction("保存调价记录单");

		MaterialChangePriceMaster masters=new MaterialChangePriceMaster();
		SysUser user = SessionUtil.getSysUser();
		String unitsCode = user.getUnitsCode();

		String autoId = master.getAutoId();
		String autoIdN = "";
		String billNo = master.getBillNo();
		String sysValue ="";
		List<CdSysParam> sysparam= cdSysParamDAO.findByCondition(unitsCode, "892", "0104");
		for (CdSysParam sys: sysparam )
		{
			sysValue=sys.getParaValue();
		}
		if (autoId == null || "".equals(autoId)) {
			System.out.println("在157行 ");
			// 获取当前系统标识号
			autoId = materialChangePriceMasterDAO.nextValue(
					"SEQ_MATERIAL_CHANGE_MASTER").toString();
			if (billNo == null || "".equals(billNo)) {
				// 获取当前单据编号--里面存在写表，所有再下如出现错误，需用抛出异常处理
				billNo = getBillNo(unitsCode);
				master.setBillNo(billNo);
			} else {
				// 检验单号唯一
				if (!materialChangePriceMasterDAO.checkBillNoUnique(unitsCode,
						billNo)) {
					ro.setError("该调价单号已存在，请修改！");
					return ro;
				}
			}
			master.setAutoId(autoId);
			master.setUnitsCode(unitsCode);
			master.setMaker(user.getPersonId());
			master.setMakeDate(new Date());
			master.setCurrentStatus("0");
			materialChangePriceMasterDAO.save(master);
			
			if(sysValue.equals("1"))
			{
				
				autoIdN = materialChangePriceMasterDAO.nextValue(
				"SEQ_MATERIAL_CHANGE_MASTER").toString();
				masters.setAutoId(autoIdN);
				if(master.getUnitsCode().equals(UNITS_CODE_SOUTH))
				{
					masters.setBillNo(getBillNo(UNITS_CODE_NORTH));
					masters.setUnitsCode(UNITS_CODE_NORTH);
				}else if(master.getUnitsCode().equals(UNITS_CODE_NORTH))
				{
					masters.setBillNo(getBillNo(UNITS_CODE_SOUTH));
					masters.setUnitsCode(UNITS_CODE_SOUTH);
				}
				masters.setMaker(user.getPersonId());
				masters.setMakeDate(new Date());
				//泰州需求——南院调价后，北院生成的调价单，状态更改为已审核
				masters.setCurrentStatus("1");
				masters.setBillDate(master.getBillDate());
				masters.setChangeReason(master.getChangeReason());
				masters.setRemark(master.getRemark());
				masters.setSalerCode(master.getSalerCode());
				masters.setSalerName(master.getSalerName());
				materialChangePriceMasterDAO.save(masters);
			    
			}
		} else {
			// 修改
			MaterialChangePriceMaster original = materialChangePriceMasterDAO
					.findById(autoId);
			String billNo1="";
			String code="";
			if(original.getUnitsCode().equals(UNITS_CODE_SOUTH))
			{
				code=UNITS_CODE_NORTH;
				billNo1="B"+original.getBillNo().substring(1);
			}else{
				code=UNITS_CODE_SOUTH;
				billNo1="N"+original.getBillNo().substring(1);
			}
			if (original == null) {
				ro.setError("该调价记录单不存在，您不能进行保存！");
				return ro;
			}
			if ("1".equals(original.getCurrentStatus())) {
				ro.setError("该调价记录单已审核，您不能进行保存！");
				return ro;
			}
			materialChangePriceMasterDAO.merge(master);
			//判断南北院是否同步
			if(sysValue.equals("1"))
			{
				MaterialChangePriceMaster Noriginal = materialChangePriceMasterDAO.findByBillNo(code,billNo1);
				String currentPrefix = cdSysParamDAO.findByParaCode(code, "892","0102");
				Noriginal.setBillNo(currentPrefix+master.getBillNo().substring(1));
			    Noriginal.setBillDate(master.getBillDate());
			    Noriginal.setChangeReason(master.getChangeReason());
			    Noriginal.setSalerCode(master.getSalerCode());
			    Noriginal.setSalerName(master.getSalerName());
			    Noriginal.setRemark(master.getRemark());
			    autoIdN=Noriginal.getAutoId();
			    materialChangePriceMasterDAO.merge(Noriginal);
			}
		}

		// 保存明细记录
		short j = 0;
		// 先删除调价明细记录
		materialChangePriceDetailDAO.delByMainAutoId(autoId);
		materialChangePriceDetailDAO.delByMainAutoId(autoIdN);
		for (MaterialChangePriceDetail detail : details) {
			detail.setMainAutoId(autoId);
			
			MaterialChangePriceDetail materialDetail = new MaterialChangePriceDetail();
			// 查找当前库存量
            // String condition = getConditon(unitsCode, detail.getMaterialId());
			Object[] stock = materialCurrentStockDAO
			.findByUnitsCodeAndMaterialId(unitsCode,detail.getMaterialId());
			if(stock == null ){
				detail.setSerialNo(++j);
				detail.setStorageCode("0");
				detail.setAmount(0.0);
				materialChangePriceDetailDAO.save(detail);
				
				if(sysValue.equals("1"))
				{
					try {
						PropertyUtils.copyProperties(materialDetail, detail);
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
					if(SessionUtil.getUnitsCode().equals(UNITS_CODE_NORTH))
					{
						List<CdMaterialDict>  material=cdMaterialDictDAO.findByMaterialCode(UNITS_CODE_SOUTH, detail.getMaterialCode());
						if(material.size()==1)
						{
							materialDetail.setMaterialId(material.get(0).getMaterialId());
						}
					}else if(SessionUtil.getUnitsCode().equals(UNITS_CODE_SOUTH))
					{
						List<CdMaterialDict>  material=cdMaterialDictDAO.findByMaterialCode(UNITS_CODE_NORTH, detail.getMaterialCode());
						if(material.size()==1)
						{
							materialDetail.setMaterialId(material.get(0).getMaterialId());
						}
					}
					materialDetail.setMainAutoId(autoIdN);
					materialChangePriceDetailDAO.save(materialDetail);
				}
			}else{
					MaterialChangePriceDetail newDetail = new MaterialChangePriceDetail();
					// 属性复制
					try {
						PropertyUtils.copyProperties(newDetail, detail);
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
					newDetail.setSerialNo(++j);
					newDetail.setStorageCode((stock[1].toString()));
					newDetail.setAmount((Double) (stock[3]));
					materialChangePriceDetailDAO.save(newDetail);
					
					if(sysValue.equals("1"))
					{
						try {
							PropertyUtils.copyProperties(materialDetail, newDetail);
						} catch (Exception e) {
							throw new RuntimeException(e);
						}
						
						if(SessionUtil.getUnitsCode().equals(UNITS_CODE_NORTH))
						{
							List<CdMaterialDict>  material=cdMaterialDictDAO.findByMaterialCode(UNITS_CODE_SOUTH, detail.getMaterialCode());
							if(material.size()==1)
							{
								materialDetail.setMaterialId(material.get(0).getMaterialId());
								materialDetail.setStorageCode(SOUTH_STORAGE);
							}
						}else if(SessionUtil.getUnitsCode().equals(UNITS_CODE_SOUTH))
						{
							List<CdMaterialDict>  material=cdMaterialDictDAO.findByMaterialCode(UNITS_CODE_NORTH, detail.getMaterialCode());
							if(material.size()==1)
							{
								materialDetail.setMaterialId(material.get(0).getMaterialId());
								materialDetail.setStorageCode(NORTH_STORAGE);
							}
						}
						materialDetail.setMainAutoId(autoIdN);
						materialChangePriceDetailDAO.save(materialDetail);
					}	
			}
			//对于立即执行的数据，直接更新字典
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		    String startDate = df.format(detail.getStartDate());
			String curDate = DateUtil.getCurentDate();
			if(startDate.equals(curDate)){
				CdMaterialDict  materialDict=  cdMaterialDictDAO.findById(unitsCode, detail.getMaterialId());
				   String materialCode=materialDict.getMaterialCode();
				   List<CdMaterialDict> list=cdMaterialDictDAO.findByProperty("materialCode", materialCode);
				   for (CdMaterialDict materialList:list)
				   {
					   double amount = materialCurrentStockDAO.findAmount(unitsCode, materialDict.getMaterialId());
					   materialList.setWholeSalePrice(detail.getNewWholeSalePrice());
					   materialList.setTradePrice(detail.getNewTradePrice());//6-27,泰州要求加jzx
					   materialList.setInvitePrice(detail.getNewInvitePrice());
					   materialList.setAmount(amount);
					   materialList.setRetailPrice(detail.getNewRetailPrice());
						if(detail.getFactoryCode()==null||detail.getFactoryCode()=="")
						{
							materialList.setFactoryCode("");
						}
						cdMaterialDictDAO.update(materialList);
				   }
			}
		}
		
		List<Object> data = new ArrayList<Object>();
		data.add(master);
		data.add(details);
		ro.setData(data);
		return ro;
	}

	/**
	 * 生成查询条件
	 * 
	 * @param unitsCode
	 * @param materialId
	 * @return
	 */
	private String getConditon(String unitsCode, String materialId) {
		StringBuilder conditions = new StringBuilder();

		conditions.append(" where unitsCode='" + unitsCode + "'");
		conditions.append(" and materialId='" + materialId + "'");
		return conditions.toString();
	}

	/**
	 * 获取当前最大单据编号
	 * 
	 * @param unitsCode
	 * @return
	 */
	private String getBillNo(String unitsCode) {
		String billNo = "";
		// 单据编号生成规则
		String rcptType = cdSysParamDAO
				.findByParaCode(unitsCode, "892", "0101");
		if (rcptType.equals("")) {
			rcptType = "1";
		}
		String billDate = "";
		if (rcptType.equals("1")) {
			billDate = getTypeDate("yyyyMMdd");
		} else if (rcptType.equals("2")) {
			billDate = getTypeDate("yyyyMM");
		} else {
			billDate = getTypeDate("yyyy");
		}
		// 单据号前缀
		String currentPrefix = cdSysParamDAO.findByParaCode(unitsCode, "892",
				"0102");
		if (rcptType.equals("")) {
			currentPrefix = "N";
		}
		// 读取当前最大单据号
		MaterialAcctCurrentRcptNo maCurrentRcptNo = materialAcctCurrentRcptNoDAO
				.findByUniqueIndex(unitsCode, "3", rcptType, billDate);
		if (maCurrentRcptNo == null) {
			maCurrentRcptNo = new MaterialAcctCurrentRcptNo();
			String currentRcptNoAutoId = materialAcctCurrentRcptNoDAO
					.nextValue("SEQ_MATERAIL_ACCT_CURRENT_RCPT").toString();
			maCurrentRcptNo.setAutoId(currentRcptNoAutoId);
			maCurrentRcptNo.setUnitsCode(unitsCode);
			maCurrentRcptNo.setRcptType(rcptType);
			maCurrentRcptNo.setRdFlag("3");
			maCurrentRcptNo.setTypeDate(billDate);
			billNo = billDate + "0001";
			maCurrentRcptNo.setCurrentNo(billNo);

			materialAcctCurrentRcptNoDAO.save(maCurrentRcptNo);
		} else {
			billNo = maCurrentRcptNo.getCurrentNo();
			billNo = (Long.parseLong(billNo) + 1) + "";

			maCurrentRcptNo.setCurrentNo(billNo);
			materialAcctCurrentRcptNoDAO.attachDirty(maCurrentRcptNo);
		}
		billNo = currentPrefix + billNo;
		return billNo;
	}

	/**
	 * 获取日期格式
	 * 
	 * @param fstrDateFormat
	 * @return
	 */
	private String getTypeDate(String fstrDateFormat) {
		java.util.Date dt = Calendar.getInstance().getTime();
		SimpleDateFormat df = new SimpleDateFormat(fstrDateFormat);
		return df.format(dt);
	}

	public void setMaterialChangePriceMasterDAO(
			MaterialChangePriceMasterDAO materialChangePriceMasterDAO) {
		this.materialChangePriceMasterDAO = materialChangePriceMasterDAO;
	}

	public MaterialChangePriceMasterDAO getMaterialChangePriceMasterDAO() {
		return materialChangePriceMasterDAO;
	}

	public void setMaterialChangePriceDetailDAO(
			MaterialChangePriceDetailDAO materialChangePriceDetailDAO) {
		this.materialChangePriceDetailDAO = materialChangePriceDetailDAO;
	}

	public MaterialChangePriceDetailDAO getMaterialChangePriceDetailDAO() {
		return materialChangePriceDetailDAO;
	}

	public CdSysParamDAO getCdSysParamDAO() {
		return cdSysParamDAO;
	}

	public void setCdSysParamDAO(CdSysParamDAO cdSysParamDAO) {
		this.cdSysParamDAO = cdSysParamDAO;
	}

	public MaterialAcctCurrentRcptNoDAO getMaterialAcctCurrentRcptNoDAO() {
		return materialAcctCurrentRcptNoDAO;
	}

	public void setMaterialAcctCurrentRcptNoDAO(
			MaterialAcctCurrentRcptNoDAO materialAcctCurrentRcptNoDAO) {
		this.materialAcctCurrentRcptNoDAO = materialAcctCurrentRcptNoDAO;
	}

	public MaterialCurrentStockDAO getMaterialCurrentStockDAO() {
		return materialCurrentStockDAO;
	}

	public void setMaterialCurrentStockDAO(
			MaterialCurrentStockDAO materialCurrentStockDAO) {
		this.materialCurrentStockDAO = materialCurrentStockDAO;
	}
	public CdMaterialDictDAO getCdMaterialDictDAO() {
		return cdMaterialDictDAO;
	}

	public void setCdMaterialDictDAO(CdMaterialDictDAO cdMaterialDictDAO) {
		this.cdMaterialDictDAO = cdMaterialDictDAO;
	}
}
