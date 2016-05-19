package cn.superion.equipment.common;

import cn.superion.equipment.dao.EqCurrentRcptNoDAO;
import cn.superion.equipment.entity.EqCurrentRcptNo;
import cn.superion.system.entity.SysUser;
import cn.superion.util.SessionUtil;

public class EqBillNoServiceImpl implements IEqBillNoService {
	private EqCurrentRcptNoDAO eqCurrentRcptNoDAO;

	public EqCurrentRcptNoDAO getEqCurrentRcptNoDAO() {
		return eqCurrentRcptNoDAO;
	}

	public void setEqCurrentRcptNoDAO(EqCurrentRcptNoDAO eqCurrentRcptNoDAO) {
		this.eqCurrentRcptNoDAO = eqCurrentRcptNoDAO;
	}

	@Override
	public String getNextBillNo(String rcptFlag) {
		SysUser user = SessionUtil.getSysUser();
		String unitsCode = user.getUnitsCode();
		// 应用程序编号 891
		// String appNo = user.getAppCode();
		String rcptType = "1";// cdSysParamDAO.findByParaCode(unitsCode, appNo,
								// "0101","1");
		String nowTypeDate = BillNoGenerator.getNowTypeDate(rcptType);
		EqCurrentRcptNo rcptNo = eqCurrentRcptNoDAO.findByStorageCode(
				unitsCode, rcptFlag, rcptType, nowTypeDate);
		String curTypeDate = null;
		String curNo = null;
		if (rcptNo != null) {
			curTypeDate = rcptNo.getTypeDate();
			curNo = rcptNo.getCurrentNo();
		}
		String nextNo = null;
		try {
			nextNo = BillNoGenerator.getNextNo(rcptType, curTypeDate, curNo,
					nowTypeDate);
		} catch (Exception e) {
			throw new RuntimeException("生成下一个流水号错误", e);
		}
		// 流水号数据库中不存在，则新增
		if (rcptNo == null) {
			rcptNo = new EqCurrentRcptNo(unitsCode, rcptFlag, rcptType,
					nowTypeDate, nextNo);
			eqCurrentRcptNoDAO.save(rcptNo);
		} else {
			rcptNo.setTypeDate(nowTypeDate);
			rcptNo.setCurrentNo(nextNo);
			eqCurrentRcptNoDAO.flush();
		}
		return nowTypeDate + nextNo;
	}

}
