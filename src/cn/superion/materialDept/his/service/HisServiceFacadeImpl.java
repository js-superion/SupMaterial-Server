package cn.superion.materialDept.his.service;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
/**
 * HIS服务总线实现
 * @author 曹国魁
 *
 */
public class HisServiceFacadeImpl implements IHisServiceFacade,ApplicationContextAware {
	private static final String SupHisBaseDictServiceName = "supHisBaseDictImpl";
	private static final String TjHisBaseDictServiceName = "tjHisBaseDictImpl";
	private static final String SupHisPatServiceName = "supHisPatImpl";
	private static final String TjHisPatServiceName = "tjHisPatImpl";
	private static final String SupHisBillServiceName = "supHisBillImpl";
	private static final String TjHisBillServiceName = "tjHisBillImpl";
	private ApplicationContext applicationContext;
	
	/** HIS库标识：1：世一HIS库；2：天健HIS库 */
	private String hisLibSign;
	public String getHisLibSign() {
		return hisLibSign;
	}

	public void setHisLibSign(String hisLibSign) {
		this.hisLibSign = hisLibSign;
	}

	@Override
	public IHisBaseDict getHisBaseDictService() {
		if("1".equals(hisLibSign))
			return (IHisBaseDict) applicationContext.getBean(SupHisBaseDictServiceName);
		if("2".equals(hisLibSign))
			return (IHisBaseDict) applicationContext.getBean(TjHisBaseDictServiceName);
		return null;
	}

	@Override
	public IHisBill getHisBillService() {
		if("1".equals(hisLibSign))
			return (IHisBill) applicationContext.getBean(SupHisBillServiceName);
		if("2".equals(hisLibSign))
			return (IHisBill) applicationContext.getBean(TjHisBillServiceName);
		return null;
	}

	@Override
	public IHisPat getHisPatService() {
		if("1".equals(hisLibSign))
			return (IHisPat) applicationContext.getBean(SupHisPatServiceName);
		if("2".equals(hisLibSign))
			return (IHisPat) applicationContext.getBean(TjHisPatServiceName);
		return null;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

}
