package cn.superion.equipment.job.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.equipment.common.IEqBillNoService;
import cn.superion.equipment.dao.EqJobContentDAO;
import cn.superion.equipment.dao.EqJobContentItemDAO;
import cn.superion.equipment.dao.EqJobContentItemPartDAO;
import cn.superion.equipment.dao.EqSparePartsDetailDAO;
import cn.superion.equipment.entity.EqJobContent;
import cn.superion.equipment.entity.EqJobContentItem;
import cn.superion.equipment.entity.EqJobContentItemPart;
import cn.superion.equipment.entity.EqSparePartsDetail;
import cn.superion.system.entity.SysUser;
import cn.superion.util.SessionUtil;
/**
 * 作业内容服务实现
 * @author 曹国魁
 *
 */
public class EqJobContentImpl implements IEqJobContent {
	private EqJobContentDAO eqJobContentDAO;
	private EqJobContentItemDAO eqJobContentItemDAO;
	private EqJobContentItemPartDAO eqJobContentItemPartDAO;
	EqSparePartsDetailDAO eqSparePartsDetailDAO;
	private IEqBillNoService eqBillNoService;
	
	public IEqBillNoService getEqBillNoService() {
		return eqBillNoService;
	}

	public void setEqBillNoService(IEqBillNoService eqBillNoService) {
		this.eqBillNoService = eqBillNoService;
	}

	public EqJobContentDAO getEqJobContentDAO() {
		return eqJobContentDAO;
	}

	public void setEqJobContentDAO(EqJobContentDAO eqJobContentDAO) {
		this.eqJobContentDAO = eqJobContentDAO;
	}

	public EqJobContentItemDAO getEqJobContentItemDAO() {
		return eqJobContentItemDAO;
	}

	public void setEqJobContentItemDAO(EqJobContentItemDAO eqJobContentItemDAO) {
		this.eqJobContentItemDAO = eqJobContentItemDAO;
	}

	public EqJobContentItemPartDAO getEqJobContentItemPartDAO() {
		return eqJobContentItemPartDAO;
	}

	public void setEqJobContentItemPartDAO(
			EqJobContentItemPartDAO eqJobContentItemPartDAO) {
		this.eqJobContentItemPartDAO = eqJobContentItemPartDAO;
	}

	@Override
	public ReObject del(String fstrAutoId) {
		ReObject ro = new ReObject("删除未审核的作业内容");
		EqJobContent content = eqJobContentDAO.findById(fstrAutoId);
		if(content == null){
			throw new RuntimeException("不存在此作业内容[系统标识号："+fstrAutoId+"],不能删除！");
		}
		if("1".equals(content.getCurrentStatus())){
			throw new RuntimeException("作业内容已审核[系统标识号："+fstrAutoId+"]，不能删除！");
		}
		//删除作业内容项目备件
		eqJobContentItemPartDAO.delByAutoId(fstrAutoId);
		//删除作业内容项目
		eqJobContentItemDAO.delByAutoId(fstrAutoId);
		//删除作业内容
		eqJobContentDAO.delete(content);
		return ro;
	}

	@Override
	public ReObject findAutoIdsByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据条件，查询作业内容主记录ID列表");
		List<Object> data = eqJobContentDAO.findAutoIdsByCondition(SessionUtil.getUnitsCode(),fparameter.getConditions());
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject findByAutoId(String fstrAutoId) {
		ReObject ro = new ReObject("根据作业内容主记录ID查询作业内容");
		EqJobContent content = eqJobContentDAO.findById(fstrAutoId); 
		List<EqJobContentItem> itemList = eqJobContentItemDAO.findByAutoId(fstrAutoId);
		List<EqJobContentItemPart> partList = eqJobContentItemPartDAO.findByAutoId(fstrAutoId);
		List<Object> data = new ArrayList<Object>();
		data.add(content);
		data.add(itemList);
		data.add(partList);
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject save(EqJobContent content, List<EqJobContentItem> itemList,
			List<EqJobContentItemPart> partList) {
		ReObject ro = new ReObject("保存作业内容");
		String autoId = content.getAutoId();
		SysUser user = SessionUtil.getSysUser();
		String unitsCode = user.getUnitsCode();
		if(autoId == null || "".equals(autoId)){
			//新增
			String jobCode = content.getJobCode();
			if(jobCode == null || "".equals(jobCode)){
				content.setJobCode(eqBillNoService.getNextBillNo("2"));
			}else{
				//校验作业编码唯一
				if(!eqJobContentDAO.checkJobCodeUnique(unitsCode,jobCode)){
					throw new RuntimeException("手工输入的作业编码[" + jobCode + "]在单位["
							+ unitsCode + "]]下有重复");
				}
			}
			content.setUnitsCode(unitsCode);
			content.setCurrentStatus("0");
			content.setMaker(user.getPersonId());
			content.setMakeDate(new Date());
			eqJobContentDAO.save(content);
			autoId = content.getAutoId();
		}else{
			//修改
			//校验未审核
			EqJobContent original = eqJobContentDAO.findById(autoId);
			if(original == null){
				throw new RuntimeException("不存在此作业内容[系统标识号："+autoId+"],不能修改！");
			}
			if("1".equals(original.getCurrentStatus())){
				throw new RuntimeException("作业内容已审核[系统标识号："+autoId+"]，不能修改！");
			}
			eqJobContentDAO.merge(content);
			//先删除明细记录
			//删除作业内容项目备件
			eqJobContentItemPartDAO.delByAutoId(autoId);
			//删除作业内容项目
			eqJobContentItemDAO.delByAutoId(autoId);
		}
		for(EqJobContentItem item : itemList){
			item.setAutoId(autoId);
			eqJobContentItemDAO.save(item);
		}
		for(EqJobContentItemPart part : partList){
			part.setAutoId(autoId);
			eqJobContentItemPartDAO.save(part);
		}
		List<Object> data = new ArrayList<Object>();
		data.add(content);
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject verify(String fstrAutoId) {
		ReObject ro = new ReObject("审核作业内容");
		EqJobContent content = eqJobContentDAO.findById(fstrAutoId);
		if(content == null){
			throw new RuntimeException("不存在此作业内容[系统标识号："+fstrAutoId+"],不能审核！");
		}
		if("1".equals(content.getCurrentStatus())){
			throw new RuntimeException("作业内容已审核[系统标识号："+fstrAutoId+"]，不能重复审核！");
		}
		content.setCurrentStatus("1");
		content.setVerifier(SessionUtil.getPersonId());
		content.setVerifyDate(new Date());
		List<Object> data = new ArrayList<Object>();
		data.add(content);
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject findPartsDetailByEquipmentType(String fstrEquipmentType) {
		ReObject ro = new ReObject("根据设备类型查找备件明细");
		List<EqSparePartsDetail> eqSpareParts= eqSparePartsDetailDAO.findByEquipmentTypeCode(fstrEquipmentType);
		ro.setData(eqSpareParts);
		return ro;
	}

	public EqSparePartsDetailDAO getEqSparePartsDetailDAO() {
		return eqSparePartsDetailDAO;
	}

	public void setEqSparePartsDetailDAO(EqSparePartsDetailDAO eqSparePartsDetailDAO) {
		this.eqSparePartsDetailDAO = eqSparePartsDetailDAO;
	}
}
