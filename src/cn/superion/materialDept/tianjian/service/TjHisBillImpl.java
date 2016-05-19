package cn.superion.materialDept.tianjian.service;

import java.util.List;

import cn.superion.materialDept.entity.MaterialPatsDetail;
import cn.superion.materialDept.entity.PatsBillMaster;
import cn.superion.materialDept.his.service.IHisBill;
/**
 * 天健HIS病人费用服务实现
 * @author 曹国魁
 *
 */
public class TjHisBillImpl implements IHisBill {

	@Override
	public void save(PatsBillMaster master, List<MaterialPatsDetail> details) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<MaterialPatsDetail> writeRed(PatsBillMaster master,
			List<MaterialPatsDetail> blueDetails) {
		// TODO Auto-generated method stub
		return null;
	}

}
