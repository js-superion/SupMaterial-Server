package cn.superion.equipment.run.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.equipment.common.IEqBillNoService;
import cn.superion.equipment.dao.EqMeasureDetailDAO;
import cn.superion.equipment.dao.EqMeasureMasterDAO;
import cn.superion.equipment.dao.VEqMeasureDAO;
import cn.superion.equipment.entity.EqMeasureDetail;
import cn.superion.equipment.entity.EqMeasureMaster;
import cn.superion.system.entity.SysUser;
import cn.superion.util.SessionUtil;
/**
 * 测量记录服务实现
 * @author 曹国魁
 *
 */
public class EqMeasureImpl implements IEqMeasure {
	private EqMeasureMasterDAO eqMeasureMasterDAO;
	private EqMeasureDetailDAO eqMeasureDetailDAO;
	private VEqMeasureDAO vEqMeasureDAO;
	private IEqBillNoService eqBillNoService;
	public VEqMeasureDAO getvEqMeasureDAO() {
		return vEqMeasureDAO;
	}

	public void setvEqMeasureDAO(VEqMeasureDAO vEqMeasureDAO) {
		this.vEqMeasureDAO = vEqMeasureDAO;
	}

	public EqMeasureMasterDAO getEqMeasureMasterDAO() {
		return eqMeasureMasterDAO;
	}

	public void setEqMeasureMasterDAO(EqMeasureMasterDAO eqMeasureMasterDAO) {
		this.eqMeasureMasterDAO = eqMeasureMasterDAO;
	}

	public EqMeasureDetailDAO getEqMeasureDetailDAO() {
		return eqMeasureDetailDAO;
	}

	public void setEqMeasureDetailDAO(EqMeasureDetailDAO eqMeasureDetailDAO) {
		this.eqMeasureDetailDAO = eqMeasureDetailDAO;
	}

	public IEqBillNoService getEqBillNoService() {
		return eqBillNoService;
	}

	public void setEqBillNoService(IEqBillNoService eqBillNoService) {
		this.eqBillNoService = eqBillNoService;
	}

	@Override
	public ReObject del(String fstrAutoId) {
		ReObject ro = new ReObject("删除未审核的测量记录");
		EqMeasureMaster master = eqMeasureMasterDAO.findById(fstrAutoId);
		if(master == null){
			throw new RuntimeException("不存在此测量记录[系统标识号："+fstrAutoId+"],不能删除！");
		}
		if("1".equals(master.getCurrentStatus())){
			throw new RuntimeException("测量记录已审核[系统标识号："+fstrAutoId+"]，不能删除！");
		}
		eqMeasureDetailDAO.delByAutoId(fstrAutoId);
		eqMeasureMasterDAO.delete(master);
		return ro;
	}

	@Override
	public ReObject findAutoIdsByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据条件，查询测量主记录ID列表");
		List<Object> data = eqMeasureMasterDAO.findAutoIdsByCondition(SessionUtil.getUnitsCode(),fparameter.getConditions());
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject findByAutoId(String fstrAutoId) {
		ReObject ro = new ReObject("根据测量主记录ID，查询测量记录");
		EqMeasureMaster master = eqMeasureMasterDAO.findById(fstrAutoId);
		List<EqMeasureDetail> details = eqMeasureDetailDAO.findByAutoId(fstrAutoId);
		List<Object> data = new ArrayList<Object>();
		data.add(master);
		data.add(details);
		ro.setData(data);
		return ro;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ReObject findDetailListByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据条件，查询测量点记录明细列表");
		int start = fparameter.getStart();
		int limit = fparameter.getItemsPerPage();
		Object[] objs = vEqMeasureDAO.findByCondition(start,limit,SessionUtil.getUnitsCode(),fparameter.getConditions());
		int count = Integer.parseInt(objs[0].toString());
		ro.setCount(count, limit);
		ro.setData((List) objs[1]);
		return ro;
	}

	@Override
	public ReObject save(EqMeasureMaster master, List<EqMeasureDetail> details) {
		ReObject ro = new ReObject("保存测量记录");
		SysUser user = SessionUtil.getSysUser();
		String unitsCode = user.getUnitsCode();
		String autoId = master.getAutoId();
		if(autoId == null || "".equals(autoId) ){
			//新增
			String billNo = master.getBillNo();
			if(billNo == null || "".equals(billNo)){
				master.setBillNo(eqBillNoService.getNextBillNo("5"));
			}else{
				//校验测量单号唯一
				if(!eqMeasureMasterDAO.checkBillNoUnique(unitsCode,billNo)){
					throw new RuntimeException("手工输入的测量单号[" + billNo + "]在单位["
							+ unitsCode + "]]下有重复");
				}
			}
			master.setUnitsCode(unitsCode);
			master.setCurrentStatus("0");
			master.setMaker(user.getPersonId());
			master.setMakeDate(new Date());
			eqMeasureMasterDAO.save(master);
			autoId = master.getAutoId();

		}else{
			//修改
			EqMeasureMaster original = eqMeasureMasterDAO.findById(autoId);
			if(original == null){
				throw new RuntimeException("不存在此测量记录[系统标识号："+autoId+"],不能修改！");
			}
			if("1".equals(original.getCurrentStatus())){
				throw new RuntimeException("测量记录已审核[系统标识号："+autoId+"]，不能修改！");
			}
			eqMeasureMasterDAO.merge(master);
			//先删除明细记录
			eqMeasureDetailDAO.delByAutoId(autoId);
		}
		short i = 0;
		for(EqMeasureDetail detail : details){
			detail.setAutoId(autoId);
			detail.setSerialNo(++i);
			eqMeasureDetailDAO.save(detail);
		}
		List<Object> data = new ArrayList<Object>();
		data.add(master);
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject verify(String fstrAutoId) {
		ReObject ro = new ReObject("审核未审核的测量记录");
		EqMeasureMaster master = eqMeasureMasterDAO.findById(fstrAutoId);
		if(master == null){
			throw new RuntimeException("不存在此测量记录[系统标识号："+fstrAutoId+"],不能审核！");
		}
		if("1".equals(master.getCurrentStatus())){
			throw new RuntimeException("测量记录已审核[系统标识号："+fstrAutoId+"]，不能重复审核！");
		}
		master.setCurrentStatus("1");
		master.setVerifier(SessionUtil.getPersonId());
		master.setVerifyDate(new Date());
		List<Object> data = new ArrayList<Object>();
		data.add(master);
		ro.setData(data);
		return ro;
	}

}
