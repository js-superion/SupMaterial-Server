package cn.superion.materialDept.his.service;

/**
 * HIS服务总线接口，提供HIS基础字典服务，HIS病人信息服务和HIS病人费用服务
 * @author 曹国魁
 *
 */
public interface IHisServiceFacade {
	/**
	 * 根据属性hisLibSign，返回HIS基础字典服务
	 * @return
	 */
	IHisBaseDict getHisBaseDictService();
	
	/**
	 * 根据属性hisLibSign，返回HIS病人信息服务
	 * @return
	 */
	IHisPat getHisPatService();
	
	/**
	 * 根据属性hisLibSign，返回HIS病人费用服务
	 * @return
	 */
	IHisBill getHisBillService();
}
