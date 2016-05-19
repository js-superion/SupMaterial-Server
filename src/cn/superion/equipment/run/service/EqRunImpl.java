package cn.superion.equipment.run.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.equipment.common.IEqBillNoService;
import cn.superion.equipment.dao.EqRunDetailDAO;
import cn.superion.equipment.dao.EqRunMasterDAO;
import cn.superion.equipment.dao.VEqRunDAO;
import cn.superion.equipment.entity.EqRunDetail;
import cn.superion.equipment.entity.EqRunMaster;
import cn.superion.system.entity.SysUser;
import cn.superion.util.SessionUtil;
/**
 * 运行记录服务实现
 * @author 曹国魁
 *
 */
public class EqRunImpl implements IEqRun {
	private EqRunMasterDAO eqRunMasterDAO;
	private EqRunDetailDAO eqRunDetailDAO;
	private VEqRunDAO vEqRunDAO;
	private IEqBillNoService eqBillNoService;
	public EqRunMasterDAO getEqRunMasterDAO() {
		return eqRunMasterDAO;
	}

	public void setEqRunMasterDAO(EqRunMasterDAO eqRunMasterDAO) {
		this.eqRunMasterDAO = eqRunMasterDAO;
	}

	public EqRunDetailDAO getEqRunDetailDAO() {
		return eqRunDetailDAO;
	}

	public void setEqRunDetailDAO(EqRunDetailDAO eqRunDetailDAO) {
		this.eqRunDetailDAO = eqRunDetailDAO;
	}

	public VEqRunDAO getvEqRunDAO() {
		return vEqRunDAO;
	}

	public void setvEqRunDAO(VEqRunDAO vEqRunDAO) {
		this.vEqRunDAO = vEqRunDAO;
	}

	public IEqBillNoService getEqBillNoService() {
		return eqBillNoService;
	}

	public void setEqBillNoService(IEqBillNoService eqBillNoService) {
		this.eqBillNoService = eqBillNoService;
	}

	@Override
	public ReObject del(String fstrAutoId) {
		ReObject ro = new ReObject("删除未审核的运行记录");
		EqRunMaster master = eqRunMasterDAO.findById(fstrAutoId);
		if(master == null){
			throw new RuntimeException("不存在此运行记录[系统标识号："+fstrAutoId+"],不能删除！");
		}
		if("1".equals(master.getCurrentStatus())){
			throw new RuntimeException("运行记录已审核[系统标识号："+fstrAutoId+"]，不能删除！");
		}
		eqRunDetailDAO.delByAutoId(fstrAutoId);
		eqRunMasterDAO.delete(master);
		return ro;
	}

	@Override
	public ReObject findAutoIdsByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据条件，查询运行主记录ID列表");
		List<Object> data = eqRunMasterDAO.findAutoIdsByCondition(SessionUtil.getUnitsCode(),fparameter.getConditions());
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject findByAutoId(String fstrAutoId) {
		ReObject ro = new ReObject("根据运行记录ID，查询运行记录");
		EqRunMaster master = eqRunMasterDAO.findById(fstrAutoId);
		List<EqRunDetail> details = eqRunDetailDAO.findByAutoId(fstrAutoId);
		List<Object> data = new ArrayList<Object>();
		data.add(master);
		data.add(details);
		ro.setData(data);
		return ro;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ReObject findDetailListByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据条件，查询运行记录明细列表");
		int start = fparameter.getStart();
		int limit = fparameter.getItemsPerPage();
		Object[] objs = vEqRunDAO.findByCondition(start,limit,SessionUtil.getUnitsCode(),fparameter.getConditions());
		int count = Integer.parseInt(objs[0].toString());
		ro.setCount(count, limit);
		ro.setData((List) objs[1]);
		return ro;
	}

	@Override
	public ReObject findRunStat(ParameterObject fparameter) {
		ReObject ro = new ReObject("运行统计");
		List<Object> data = eqRunDetailDAO.findEquipmentStat(SessionUtil.getUnitsCode(),fparameter.getConditions());
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject save(EqRunMaster master, List<EqRunDetail> details) {
		ReObject ro = new ReObject("保存运行记录");
		SysUser user = SessionUtil.getSysUser();
		String unitsCode = user.getUnitsCode();
		String autoId = master.getAutoId();
		if(autoId == null || "".equals(autoId) ){
			//新增
			String billNo = master.getBillNo();
			if(billNo == null || "".equals(billNo)){
				master.setBillNo(eqBillNoService.getNextBillNo("7"));
			}else{
				//校验运行单号唯一
				if(!eqRunMasterDAO.checkBillNoUnique(unitsCode,billNo)){
					throw new RuntimeException("手工输入的运行单号[" + billNo + "]在单位["
							+ unitsCode + "]]下有重复");
				}
			}
			master.setUnitsCode(unitsCode);
			master.setCurrentStatus("0");
			master.setMaker(user.getPersonId());
			master.setMakeDate(new Date());
			eqRunMasterDAO.save(master);
			autoId = master.getAutoId();

		}else{
			//修改
			EqRunMaster original = eqRunMasterDAO.findById(autoId);
			if(original == null){
				throw new RuntimeException("不存在此运行记录[系统标识号："+autoId+"],不能修改！");
			}
			if("1".equals(original.getCurrentStatus())){
				throw new RuntimeException("运行记录已审核[系统标识号："+autoId+"]，不能修改！");
			}
			eqRunMasterDAO.merge(master);
			//先删除明细记录
			eqRunDetailDAO.delByAutoId(autoId);
		}
		short i = 0;
		for(EqRunDetail detail : details){
			detail.setAutoId(autoId);
			detail.setSerialNo(++i);
			eqRunDetailDAO.save(detail);
		}
		List<Object> data = new ArrayList<Object>();
		data.add(master);
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject verify(String fstrAutoId) {
		ReObject ro = new ReObject("审核未审核的运行记录");
		EqRunMaster master = eqRunMasterDAO.findById(fstrAutoId);
		if(master == null){
			throw new RuntimeException("不存在此运行记录[系统标识号："+fstrAutoId+"],不能审核！");
		}
		if("1".equals(master.getCurrentStatus())){
			throw new RuntimeException("运行记录已审核[系统标识号："+fstrAutoId+"]，不能重复审核！");
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
