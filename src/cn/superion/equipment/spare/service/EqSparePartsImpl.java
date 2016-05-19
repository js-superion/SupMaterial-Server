package cn.superion.equipment.spare.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.equipment.entity.EqSparePartsDetail;
import cn.superion.equipment.entity.EqSparePartsMaster;
import cn.superion.equipment.dao.EqSparePartsDetailDAO;
import cn.superion.equipment.dao.EqSparePartsMasterDAO;
import cn.superion.equipment.dao.VEqSpareDAO;
import cn.superion.system.entity.SysUser;
import cn.superion.util.SessionUtil;

/**
 * 备件清单记录接口服务
 * 
 * @author 李攀
 * 
 *         修改历史：
 * @author 周作建
 * @version 1.1
 * @date 2011-05-27
 */
public class EqSparePartsImpl implements IEqSpareParts {
	private EqSparePartsMasterDAO eqSparePartsMasterDAO;
	private EqSparePartsDetailDAO eqSparePartsDetailDAO;
	private VEqSpareDAO  vEqSpareDAO;

	@Override
	public ReObject del(String fstrEquipmentTypeCode) {
		ReObject reObject = new ReObject("删除备件信息");

		EqSparePartsMaster master = eqSparePartsMasterDAO
				.findById(fstrEquipmentTypeCode);
		if (master == null) {
			reObject.setError("该设备单已被删除或不存在！");
			return reObject;
		}
		if ("1".equals(master.getCurrentStatus())) {
			reObject.setError("该设备单已经被审核，您不能进行删除！");
			return reObject;
		}

		eqSparePartsDetailDAO.delByEquipmentTypeCode(fstrEquipmentTypeCode);
		eqSparePartsMasterDAO.delete(master);
		return reObject;
	}

	@Override
	public ReObject findByEquipmentTypeCode(String fstrEquipmentTypeCode) {
		ReObject reObject = new ReObject("查询备件信息");

		EqSparePartsMaster master = eqSparePartsMasterDAO
				.findById(fstrEquipmentTypeCode);

		List<EqSparePartsDetail> detailList = eqSparePartsDetailDAO
				.findByEquipmentTypeCode(fstrEquipmentTypeCode);

		List<Object> data = new ArrayList<Object>();
		data.add(master);
		data.add(detailList);
		reObject.setData(data);
		return reObject;
	}

	@Override
	public ReObject save(EqSparePartsMaster master,
			List<EqSparePartsDetail> details) {
		ReObject reObject = new ReObject("保存设备备件信息");

		SysUser user = SessionUtil.getSysUser();

		String equipmentTypeCode = master.getEquipmentTypeCode();
		master.setMaker(user.getPersonId());
		master.setMakeDate(new Date());
		master.setCurrentStatus("0");
		EqSparePartsMaster eqSparePartsMasterTmp = null;
		if( equipmentTypeCode!=null && !"".equals(equipmentTypeCode)){
			eqSparePartsMasterTmp = eqSparePartsMasterDAO
			.findById(equipmentTypeCode);
		}
		if (eqSparePartsMasterTmp == null) {
			// 新增
			eqSparePartsMasterDAO.save(master);
		} else {
			// 修改
				eqSparePartsMasterDAO.merge(master);
				// 先删除明细记录
				eqSparePartsDetailDAO.delByEquipmentTypeCode(equipmentTypeCode);
		}
		short i = 0;
		for (EqSparePartsDetail detail : details) {
			detail.setEquipmentTypeCode(equipmentTypeCode);
			detail.setSerialNo(++i);
			eqSparePartsDetailDAO.save(detail);
		}

		List<Object> data = new ArrayList<Object>();
		data.add(master);
		data.add(details);
		reObject.setData(data);
		return reObject;
	}

	@Override
	public ReObject verify(String fstrEquipmentTypeCode) {
		ReObject reObject = new ReObject("审核备件信息");

		EqSparePartsMaster master = eqSparePartsMasterDAO
				.findById(fstrEquipmentTypeCode);
		if (master == null) {
			reObject.setError("该设备清单不存在!");
			return reObject;
		}
		if ("1".equals(master.getCurrentStatus())) {
			reObject.setError("该设备清单已审核!");
			return reObject;
		}

		master.setCurrentStatus("1");
		master.setVerifier(SessionUtil.getPersonId());
		master.setVerifyDate(new Date());

		List<Object> data = new ArrayList<Object>();
		data.add(master);
		reObject.setData(data);
		return reObject;
	}

	public void setvEqSpareDAO(VEqSpareDAO vEqSpareDAO) {
		this.vEqSpareDAO = vEqSpareDAO;
	}

	@Override
	public ReObject findAutoIdsCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据条件，查询备件清单主记录列表");
		List<Object> data = eqSparePartsMasterDAO.findAutoIdsByCondition(fparameter.getConditions());
		ro.setData(data);
		return ro;
	}

	public void setEqSparePartsDetailDAO(
			EqSparePartsDetailDAO eqSparePartsDetailDAO) {
		this.eqSparePartsDetailDAO = eqSparePartsDetailDAO;
	}

	public EqSparePartsDetailDAO getEqSparePartsDetailDAO() {
		return eqSparePartsDetailDAO;
	}

	public void setEqSparePartsMasterDAO(
			EqSparePartsMasterDAO eqSparePartsMasterDAO) {
		this.eqSparePartsMasterDAO = eqSparePartsMasterDAO;
	}

	public EqSparePartsMasterDAO getEqSparePartsMasterDAO() {
		return eqSparePartsMasterDAO;
	}
	public VEqSpareDAO getvEqSpareDAO() {
		return vEqSpareDAO;
	}

	public void setvEqMeasureDAO(VEqSpareDAO vEqSpareDAO) {
		this.vEqSpareDAO = vEqSpareDAO;
	}

}
