package cn.superion.equipment.run.service;
import java.util.List;
import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.equipment.dao.EqMeasurePointDAO;
import cn.superion.equipment.entity.EqMeasurePoint;
/**
 * 测量点服务接口
 * @author 曹国魁
 *
 */
public class EqMeasurePointImpl implements IEqMeasurePoint {
	private EqMeasurePointDAO eqMeasurePointDAO;
	public EqMeasurePointDAO getEqMeasurePointDAO() {
		return eqMeasurePointDAO;
	}

	public void setEqMeasurePointDAO(EqMeasurePointDAO eqMeasurePointDAO) {
		this.eqMeasurePointDAO = eqMeasurePointDAO;
	}

	@Override
	public ReObject del(String pointCode) {
		ReObject ro = new ReObject("删除测量点信息");
		EqMeasurePoint dict = eqMeasurePointDAO.findById(pointCode);
		if(dict == null){
			throw new RuntimeException("不存在此测量点[编码："+pointCode+"]！");
		}
		eqMeasurePointDAO.delete(dict);
		return ro;
	}

	@Override
	public ReObject findByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据条件，查询测量点列表");
		//int start = fparameter.getStart();
		//int limit = fparameter.getItemsPerPage();
		//TODO... 
		return ro;
	}

	@Override
	public ReObject save(boolean isAdd, EqMeasurePoint measurePoint) {
		ReObject ro = new ReObject("保存测量点信息");
		if(isAdd){
			String code = measurePoint.getPointCode();
			if(eqMeasurePointDAO.findById(code) == null){
				eqMeasurePointDAO.save(measurePoint);
			}else{
				throw new RuntimeException("存在重复的编码"+code+"，违反了编码唯一性");
			}
		}else{
			eqMeasurePointDAO.update(measurePoint);
		}
		return ro;
	}

	@Override
	public ReObject findAll() {
		ReObject ro = new ReObject("查询所有的测量点");
		List<EqMeasurePoint> data = eqMeasurePointDAO.findAll();
		ro.setData(data);
		return ro;
	}

}
