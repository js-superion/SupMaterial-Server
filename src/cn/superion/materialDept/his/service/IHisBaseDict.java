package cn.superion.materialDept.his.service;

/**
 * HIS基础字典服务接口
 * @author 曹国魁
 *
 */
public interface IHisBaseDict {
	/**
	 * 根据项目类别，编码，规格和单位查询项目价格
	 * @param fstrClass HIS诊疗项目类别
	 * @param fstrCode HIS诊疗项目编码
	 * @param fstrSpec HIS诊疗项目规格
	 * @param fstrUnits HIS诊疗项目单位
	 * @return 返回价格
	 */
	Double findPrice(String fstrClass,String fstrCode,String fstrSpec,String fstrUnits);
}
