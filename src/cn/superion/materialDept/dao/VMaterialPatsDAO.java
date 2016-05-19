package cn.superion.materialDept.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.materialDept.entity.VMaterialPats;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * VMaterialPats entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.materialDept.entity.VMaterialPats
 * @author MyEclipse Persistence Tools
 */

public class VMaterialPatsDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(VMaterialPatsDAO.class);

	public VMaterialPats findById(
			cn.superion.materialDept.entity.VMaterialPats id) {
		log.debug("getting VMaterialPats instance with id: " + id);
		try {
			VMaterialPats instance = (VMaterialPats) getSession().get(
					"cn.superion.materialDept.entity.VMaterialPats", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<VMaterialPats> findByExample(VMaterialPats instance) {
		log.debug("finding VMaterialPats instance by example");
		try {
			List<VMaterialPats> results = getSession().createCriteria(
					"cn.superion.materialDept.entity.VMaterialPats").add(
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
	public List<VMaterialPats> findByProperty(String propertyName, Object value) {
		log.debug("finding VMaterialPats instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from VMaterialPats as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<VMaterialPats> findByBarCode(String fstrBarCode) {
		log.debug("finding VMaterialPats with barCode ");
		try {
			String queryString = "from VMaterialPats where barCode='"+fstrBarCode+"' and factInSign='1'";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by barCode failed", re);
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<VMaterialPats> findByPatientId(String billType,
			String unitsCode, String storageCode, String patientId) {
		String hql = "from VMaterialPats where billType=:billType and unitsCode=:unitsCode and storageCode=:storageCode and patientId=:patientId order by makeDate,serialNo";
		return getSession().createQuery(hql).setString("billType", billType)
				.setString("unitsCode", unitsCode).setString("storageCode",
						storageCode).setString("patientId", patientId).list();
	}

	/**
	 * @param start
	 * @param limit
	 * @param unitsCode
	 * @param conditions
	 *            包含：
	 *            <ul>
	 *            <li>patientId 病人标识号</li>
	 *            <li>inpNo 住院号</li>
	 *            <li>personName 病人姓名</li>
	 *            <li>deptCode 所在科室</li>
	 *            <li>beginAccountDate 起始记帐日期</li>
	 *            <li>endAccountDate 结束记帐日期</li>
	 *            <li>materialClass 物资分类</li>
	 *            <li>materialCode 物资编码</li>
	 *            <li>beginRetailPrice 起始单价</li>
	 *            <li>endRetailPrice 结束单价</li>
	 *            <li>accounter 记账人</li>
	 *            </ul>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Object[] findByConditions(int start, int limit, String unitsCode,
			Map<String, Object> conditions) {
		Object[] objs = new Object[2];
		String patientId = (String) conditions.get("patientId");
		String inpNo = (String) conditions.get("inpNo");
		String personName = (String) conditions.get("personName");
		String deptCode = (String) conditions.get("deptCode");
		Date beginAccountDate = (Date) conditions.get("beginAccountDate");
		Date endAccountDate = (Date) conditions.get("endAccountDate");
		String materialClass = (String) conditions.get("materialClass");
		String materialCode = (String) conditions.get("materialCode");
		Double beginRetailPrice = conditions.get("beginRetailPrice") == null ? null
				: Double.valueOf(conditions.get("beginRetailPrice").toString());
		Double endRetailPrice = conditions.get("endRetailPrice") == null ? null
				: Double.valueOf(conditions.get("endRetailPrice").toString());
		String accounter = (String) conditions.get("accounter");
		StringBuilder shql = new StringBuilder(
				"from VMaterialPats where unitsCode='").append(unitsCode)
				.append("'");

		if (patientId != null && !"".equals(patientId)) {
			shql.append(" and patientId=:patientId");
		}
		if (inpNo != null && !"".equals(inpNo)) {
			shql.append(" and inpNo=:inpNo");
		}
		if (personName != null && !"".equals(personName)) {
			shql.append(" and personName like :personName");
			conditions.put("personName", "%" + personName + "%");
		}
		if (deptCode != null && !"".equals(deptCode)) {
			shql.append(" and deptCode=:deptCode");
		}
		if (beginAccountDate != null) {
			shql.append(" and accountDate>=:beginAccountDate");
		}
		if (endAccountDate != null) {
			shql.append(" and accountDate<=:endAccountDate");
		}
		if (materialClass != null && !"".equals(materialClass)) {
			shql.append(" and materialClass=:materialClass");
		}
		if (materialCode != null && !"".equals(materialCode)) {
			shql.append(" and materialCode=:materialCode");
		}
		if (beginRetailPrice != null) {
			shql.append(" and retailPrice>=:beginRetailPrice");
			conditions.put("beginTradePrice", beginRetailPrice);
		}
		if (endRetailPrice != null) {
			shql.append(" and retailPrice<=:endRetailPrice");
			conditions.put("endRetailPrice", endRetailPrice);
		}
		if (accounter != null && !"".equals(accounter)) {
			shql.append(" and accounter=:accounter");
		}
		Query countQuery = getSession().createQuery(
				"select count(*) " + shql.toString());
		Query query = getSession().createQuery(shql.toString());
		int count = Integer.parseInt(countQuery.setProperties(conditions)
				.uniqueResult().toString());
		List<VMaterialPats> list = query.setProperties(conditions)
				.setFirstResult(start).setMaxResults(limit).list();
		objs[0] = count;
		objs[1] = list;
		return objs;
	}
	
	/**
	 * 
	 * @return
	 */
	public Object[] findPatsMaterialAcctByCondition(
			Map<String, Object> conditions) {
		Object[] obj=new Object[2];
		String unitsCode = (String) conditions.get("unitsCode");
		String barCode = (String) conditions.get("barCode");
		String salerCode = (String) conditions.get("salerCode");
		String factoryCode = (String) conditions.get("factoryCode");
		String materialCode = (String) conditions.get("materialCode");
		String patientId = (String) conditions.get("patientId");
		String deptCode = (String) conditions.get("deptCode");
		String wardCode = (String) conditions.get("wardCode");
		String doctorGroup = (String) conditions.get("doctorGroup");
		String doctor = (String) conditions.get("doctor");
		Date beginAccountDate = (Date) conditions.get("beginAccountDate");
		Date endAccountDate = (Date) conditions.get("endAccountDate");
		
		StringBuilder condition=new StringBuilder();
		//一般高值耗材查询
		StringBuilder shql = new StringBuilder("select new map(")
				.append("v.materialCode as materialCode,")
				.append("v.materialName as materialName,")
				.append("v.barCode as barCode,")
				.append("v.materialBarCode as materialBarCode,")
				.append("v.materialSpec as materialSpec,")
				.append("v.tradePrice as tradePrice,")
				.append("v.tradePrice as retailPrice,")//byzcl
				.append("v.materialUnits as materialUnits,")
				.append("v.amount as amount,")
				.append("r.salerCode as salerCode,")
				.append("r.salerName as salerName,")
				.append("v.factoryCode as factoryCode,")
				.append("v.availDate as availDate,")
				.append("(select c.registerAvlDate from CdMaterialDict c where c.materialId=v.materialId)as registerAvlDate,")
				.append("v.patientId as patientId,")
				.append("v.personName as personName,")
				.append("v.sex as sex,")
				.append("i.superior as doctor,")
				.append("i.groupCode as deptGroup,")
				.append("v.deptCode as deptCode,")
				.append("v.wardCode as wardCode")
				.append(") from VMaterialPats v,InpPatsInDept i,VMaterialRds r where ")
				.append(" v.unitsCode=i.id.unitsCode and v.inpNo=i.id.inpNo and v.barCode=r.barCode")
		        .append(" and v.deptCode=i.deptCode and v.wardCode=i.wardCode and v.applyDoctor=i.doctor");
		if(unitsCode!=null && !unitsCode.equals("")){
			shql.append(" and v.unitsCode='"+unitsCode+"'");
		}
		
		//特殊高值耗材查询
		StringBuilder shql2 = new StringBuilder("select new map(")
		.append("v.materialCode as materialCode,")
		.append("v.materialName as materialName,")
		.append("v.barCode as barCode,")
		.append("v.materialBarCode as materialBarCode,")
		.append("v.materialSpec as materialSpec,")
		.append("v.tradePrice as tradePrice,")//byzcl
		.append("v.tradePrice as retailPrice,")
		.append("v.materialUnits as materialUnits,")
		.append("v.amount as amount,")
		.append("r.supplyDeptCode as salerCode,")
		.append("v.factoryCode as factoryCode,")
		.append("v.availDate as availDate,")
		.append("(select c.registerAvlDate from CdMaterialDict c where c.materialId=v.materialId)as registerAvlDate,")
		.append("v.patientId as patientId,")
		.append("v.personName as personName,")
		.append("v.sex as sex,")
		.append("i.superior as doctor,")
		.append("i.groupCode as deptGroup,")
		.append("v.deptCode as deptCode,")
		.append("v.wardCode as wardCode")
		.append(") from VMaterialPats v,InpPatsInDept i,VMaterialRdsDept r where")
		.append(" v.unitsCode=i.id.unitsCode and v.inpNo=i.id.inpNo and v.barCode=r.barCode")
        .append(" and v.deptCode=i.deptCode and v.wardCode=i.wardCode and v.applyDoctor=i.doctor");
		
		if(unitsCode!=null && !unitsCode.equals("")){
			shql2.append(" and v.unitsCode='"+unitsCode+"'");
		}
		
		if (materialCode != null && !"".equals(materialCode)) {
			condition.append(" and v.materialCode=:materialCode");
		}
		if (barCode != null && !"".equals(barCode)) {
			condition.append(" and v.barCode=:barCode");
		}
		if (patientId != null && !"".equals(patientId)) {
			condition.append(" and v.patientId=:patientId");
		}
		if (salerCode != null && !"".equals(salerCode)) {
			shql.append(" and r.salerCode=:salerCode");
			shql2.append(" and r.supplyDeptCode=:salerCode");
		}
		if (factoryCode != null && !"".equals(factoryCode)) {
			condition.append(" and r.factoryCode=:factoryCode");
		}
		if (deptCode != null && !"".equals(deptCode)) {
			condition.append(" and v.deptCode=:deptCode");
		}
		if (wardCode != null && !"".equals(wardCode)) {
			condition.append(" and v.wardCode=:wardCode");
		}
		if (doctor != null && !"".equals(doctor)) {
			condition.append(" and i.superior=:doctor");
		}
		if (doctorGroup != null && !"".equals(doctorGroup)) {
			condition.append(" and i.groupCode=:doctorGroup");
		}
		if (beginAccountDate != null) {
			condition.append(" and v.accountDate>=:beginAccountDate");
		}
		if (endAccountDate != null) {
			condition.append(" and v.accountDate<=:endAccountDate");
		}
		shql.append(condition).append(" and r.materialId in(select materialId from CdMaterialDict m where  m.highValueSign='1' and m.agentSign='0') and r.rdFlag='1' order by v.materialCode,v.barCode ");
		shql2.append(condition).append(" and r.materialId in(select materialId from CdMaterialDict m where m.highValueSign='1' and m.agentSign='1') and r.rdFlag='1' order by v.materialCode,v.barCode ");
		obj[0]=getSession().createQuery(shql.toString()).setProperties(conditions).list();
		obj[1]=getSession().createQuery(shql2.toString()).setProperties(conditions).list();
		return obj;
	}

	
	/**
	 * 科室病人使用材料查询
	 * @param unitsCode
	 * @param conditions
	 * @return
	 */
	public Object[] findPatsMaterialDeptByCondition(
			Map<String, Object> conditions) {
		Object[] obj=new Object[2];
		String unitsCode = (String) conditions.get("unitsCode");
		String barCode = (String) conditions.get("barCode");
		String salerCode = (String) conditions.get("salerCode");
		String factoryCode = (String) conditions.get("factoryCode");
		String materialCode = (String) conditions.get("materialCode");
		String patientId = (String) conditions.get("patientId");
		String deptCode = (String) conditions.get("deptCode");
		String wardCode = (String) conditions.get("wardCode");
		String doctorGroup = (String) conditions.get("doctorGroup");
		String doctor = (String) conditions.get("doctor");
		Date beginAccountDate = (Date) conditions.get("beginAccountDate");
		Date endAccountDate = (Date) conditions.get("endAccountDate");
		
		StringBuilder condition=new StringBuilder();
		
		StringBuilder shql2 = new StringBuilder("select new map(")
		.append("v.materialCode as materialCode,")
		.append("v.materialName as materialName,")
		.append("v.barCode as barCode,")
		.append("v.materialBarCode as materialBarCode,")
		.append("v.materialSpec as materialSpec,")
		.append("v.tradePrice as tradePrice,")
		.append("v.tradePrice as retailPrice,")//byzcl
		.append("v.materialUnits as materialUnits,")
		.append("v.amount as amount,")
		.append("r.supplyDeptCode as salerCode,")
		.append("v.factoryCode as factoryCode,")
		.append("v.availDate as availDate,")
		.append("(select c.registerAvlDate from CdMaterialDict c where c.materialId=v.materialId)as registerAvlDate,")
		.append("v.patientId as patientId,")
		.append("v.personName as personName,")
		.append("v.sex as sex,")
		.append("i.superior as doctor,")
		.append("i.groupCode as deptGroup,")
		.append("v.deptCode as deptCode,")
		.append("v.wardCode as wardCode")
		.append(") from VMaterialPats v,InpPatsInDept i,VMaterialRdsDept r where")
		.append(" v.unitsCode=i.id.unitsCode and v.inpNo=i.id.inpNo and v.barCode=r.barCode")
        .append(" and v.deptCode=i.deptCode and v.wardCode=i.wardCode and v.applyDoctor=i.doctor");
			
		if(unitsCode!=null && !unitsCode.equals("")){
			shql2.append(" and v.unitsCode='"+unitsCode+"'");
		}
		
		if (materialCode != null && !"".equals(materialCode)) {
			condition.append(" and v.materialCode=:materialCode");
		}
		if (barCode != null && !"".equals(barCode)) {
			condition.append(" and v.barCode=:barCode");
		}
		if (patientId != null && !"".equals(patientId)) {
			condition.append(" and v.patientId=:patientId");
		}
		if (salerCode != null && !"".equals(salerCode)) {
			shql2.append(" and r.supplyDeptCode=:salerCode");
		}
		if (factoryCode != null && !"".equals(factoryCode)) {
			condition.append(" and r.factoryCode=:factoryCode");
		}
		if (deptCode != null && !"".equals(deptCode)) {
			condition.append(" and r.deptCode=:deptCode");
		}
		if (wardCode != null && !"".equals(wardCode)) {
			condition.append(" and v.wardCode=:wardCode");
		}
		if (doctor != null && !"".equals(doctor)) {
			condition.append(" and i.superior=:doctor");
		}
		if (doctorGroup != null && !"".equals(doctorGroup)) {
			condition.append(" and i.groupCode=:doctorGroup");
		}
		if (beginAccountDate != null) {
			condition.append(" and v.accountDate>=:beginAccountDate");
		}
		if (endAccountDate != null) {
			condition.append(" and v.accountDate<=:endAccountDate");
		}
		shql2.append(condition).append(" and r.materialId in(select materialId from CdMaterialDict m ) and r.rdFlag='1' order by v.materialCode,v.barCode ");

		obj[0]=getSession().createQuery(shql2.toString()).setProperties(conditions).list();
		return obj;
	}
}