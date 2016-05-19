package cn.superion.cssd.dao;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.cssd.entity.VCssdStock;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * VCssdStock entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see cn.superion.cssd.entity.VCssdStock
 * @author MyEclipse Persistence Tools
 */

public class VCssdStockDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(VCssdStockDAO.class);

	@SuppressWarnings("unchecked")
	public List<VCssdStock> findByExample(VCssdStock instance) {
		log.debug("finding VCssdStock instance by example");
		try {
			List results = getSession().createCriteria(
					"cn.superion.cssd.entity.VCssdStock").add(
					Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<VCssdStock> findByProperty(String propertyName, Object value) {
		log.debug("finding VCssdStock instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from VCssdStock as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<VCssdStock> findDeliverDetail(String deliverAutoId) {
		return findByProperty("deliverAutoId", deliverAutoId);
	}

	/**
	 * 根据物品包编号查询已发放并审核的可回收物品包物资信息，物品包回收时调用
	 * 
	 * @param unitsCode
	 * @param packageNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<VCssdStock> findRetrieveVStockByPackageNo(String unitsCode,
			String packageNo) {
		String hql = "from VCssdStock s where unitsCode=:unitsCode and packageNo=:packageNo and retrieveSign='1' and currentStatus='2' and exists(from CssdDeliverMaster p where p.unitsCode=s.unitsCode and s.deliverAutoId=p.autoId and p.currentStatus='2')";
		return getSession().createQuery(hql).setString("unitsCode", unitsCode)
				.setString("packageNo", packageNo).list();
	}

	/**
	 * 根据物品包拼音码或五笔码查询已发放并审核的可回收物品包物资信息，物品包回收时调用
	 * @param unitsCode
	 * @param inputCode
	 *            输入码
	 * @param isPho
	 *            是否拼音码
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<VCssdStock> findRetrieveVStockByInputCode(String unitsCode,
			String inputCode, boolean isPho) {
		String hql = "from VCssdStock s where unitsCode=:unitsCode and retrieveSign='1' and currentStatus='2' and exists(from CssdDeliverMaster p where p.unitsCode=s.unitsCode and s.deliverAutoId=p.autoId and p.currentStatus='2') and exists(from CssdPackageDict d where d.unitsCode=s.unitsCode and d.packageId=s.packageId and d."
				+ (isPho ? "phoInputCode" : "fiveInputCode")
				+ " like :inputCode)";
		return getSession().createQuery(hql).setString("unitsCode", unitsCode)
				.setString("inputCode", inputCode + "%").list();
	}

}