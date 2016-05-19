package cn.superion.materialDept.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.material.common.RdConstant;
import cn.superion.materialDept.entity.MaterialRdsMasterDept;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * MaterialRdsMaster entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.material.entity.MaterialRdsMaster
 * @author MyEclipse Persistence Tools
 */

public class MaterialRdsMasterDeptDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(MaterialRdsMasterDeptDAO.class);
	/**
	 * 检查指定的billNo还未被使用
	 * @param unitsCode
	 * @param storageCode
	 * @param rdFlag
	 * @param billNo
	 * @return
	 */
	public boolean checkBillNoUnique(String unitsCode,String storageCode,String rdFlag,String billNo){
		String hql = "select count(*) from MaterialRdsMasterDept where unitsCode=:unitsCode and storageCode=:storageCode and rdFlag=:rdFlag and billNo=:billNo";
		int cnt = Integer.parseInt(getSession().createQuery(hql).setString("unitsCode", unitsCode).setString("storageCode", storageCode).setString("rdFlag", rdFlag).setString("billNo", billNo).uniqueResult().toString());
		return cnt == 0;
	}
	
	public MaterialRdsMasterDept findByOperationNo(String unitsCode,String storageCode,String fstrOperationNo,String fstrOperationType) {
		String hql = "from MaterialRdsMasterDept where unitsCode=:unitsCode and storageCode=:storageCode and operationNo=:operationNo and operationType=:operationType";
		return (MaterialRdsMasterDept) getSession().createQuery(hql).setString("unitsCode", unitsCode).setString("storageCode", storageCode).setString(
				"operationNo", fstrOperationNo).setString("operationType", fstrOperationType).uniqueResult();
	}
	
	public void save(MaterialRdsMasterDept transientInstance) {
		log.debug("saving MaterialRdsMasterDept instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(MaterialRdsMasterDept persistentInstance) {
		log.debug("deleting MaterialRdsMasterDept instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MaterialRdsMasterDept findById(java.lang.String id) {
		log.debug("getting MaterialRdsMasterDept instance with id: " + id);
		try {
			MaterialRdsMasterDept instance = (MaterialRdsMasterDept) getSession().get(
					"cn.superion.materialDept.entity.MaterialRdsMasterDept", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<MaterialRdsMasterDept> findByExample(MaterialRdsMasterDept instance) {
		log.debug("finding MaterialRdsMasterDept instance by example");
		try {
			List results = getSession().createCriteria(
					"cn.superion.materialDept.entity.MaterialRdsMasterDept").add(
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
	public List<MaterialRdsMasterDept> findByProperty(String propertyName,
			Object value) {
		log.debug("finding MaterialRdsMasterDept instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MaterialRdsMasterDept as model where model."
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
	public List<MaterialRdsMasterDept> findAll() {
		log.debug("finding all MaterialRdsMasterDept instances");
		try {
			String queryString = "from MaterialRdsMasterDept";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public MaterialRdsMasterDept merge(MaterialRdsMasterDept detachedInstance) {
		log.debug("merging MaterialRdsMasterDept instance");
		try {
			MaterialRdsMasterDept result = (MaterialRdsMasterDept) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MaterialRdsMasterDept instance) {
		log.debug("attaching dirty MaterialRdsMasterDept instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MaterialRdsMasterDept instance) {
		log.debug("attaching clean MaterialRdsMasterDept instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	/**
	 * 领用出库业务时查询
	 * @param unitsCode
	 * @param conditions 包含：
	 *            <ul>
	 *            <li>beginBillDate 起始出库日期</li>
	 *            <li>endBillDate 结束出库日期</li>
	 *            <li>storageCode 仓库</li>
	 *            <li>deptCode 领用部门</li>
	 *            <li>rdType 出库类别</li>
	 *            <li>billNo 出库单号</li>
	 *            <li>materialCode 物资编码</li>
	 *            <li>materialName 物资名称</li>
	 *            <li>currentStatus 当前状态</li>
	 * 			  </ul>	
	 * @return
	 */
	public List<Object> findAutoIdsByDeliverCondition(String unitsCode,
			Map<String, Object> conditions) {
		conditions.put("operationType", RdConstant.D_DELIVER);
		return findAutoIdsByCondition(unitsCode,null,conditions);
	}

	/**
	 * 其他出库业务时查询
	 * @param unitsCode
	 * @param conditions 包含：
	 *            <ul>
	 *            <li>beginBillDate 起始出库日期</li>
	 *            <li>endBillDate 结束出库日期</li>
	 *            <li>storageCode 仓库</li>
	 *            <li>deptCode 领用部门</li>
	 *            <li>rdType 出库类别</li>
	 *            <li>billNo 出库单号</li>
	 *            <li>materialCode 物资编码</li>
	 *            <li>materialName 物资名称</li>
	 *            <li>currentStatus 当前状态</li>
	 * 			  </ul>	
	 * @return
	 */
	public List<Object> findAutoIdsByOtherDeliverCondition(String unitsCode,
			Map<String, Object> conditions) {
		String[] operationTypes = new String[]{RdConstant.D_CANN,RdConstant.D_CHECK_LOSS,RdConstant.D_REJECT,RdConstant.D_OTHERS};
		return findAutoIdsByCondition(unitsCode,operationTypes,conditions);
	}

	/**
	 * 其他入库业务时查询
	 * @param unitsCode
	 * @param conditions 包含：
	 *            <ul>
	 *            <li>storageCode 仓库</li>
	 *            <li>operationType 业务类型</li>
	 *            <li>rdType 入库类别</li>
	 *            <li>beginBillNo 起始入库单号</li>
	 *            <li>endBillNo 结束入库单号</li>
	 *            <li>beginBillDate 起始入库日期</li>
	 *            <li>endBillDate 结束入库日期</li>
	 *            <li>deptCode 部门</li>
	 *            <li>materialCode 物资编码</li>
	 *            <li>beginTradePrice 最小进价</li>
	 *            <li>endTradePrice 最大进价</li>
	 *            <li>currentStatus 当前状态</li>
	 *            </ul>
	 * @return
	 */
	public List<Object> findAutoIdsByOtherReceiveCondition(String unitsCode,
			Map<String, Object> conditions) {
		String[] operationTypes = new String[]{RdConstant.R_CHECK_PROFIT,RdConstant.R_OTHERS};
		return findAutoIdsByCondition(unitsCode,operationTypes,conditions);
	}
	
	/**
	 * 入库，出库业务查询主记录ID列表
	 * @param unitsCode
	 * @param operationTypes 条件operationType为空或全选时的默认值
	 * @param conditions 包含：
	 *            <ul>
	 *            <li>storageCode 仓库</li>
	 *            <li>operationType 业务类型</li>
	 *            <li>rdType 入库类别</li>
	 *            <li>billNo 单据号</li>
	 *            <li>beginBillNo 起始入库单号，与billNo条件互斥</li>
	 *            <li>endBillNo 结束入库单号，与billNo条件互斥</li>
	 *            <li>beginBillDate 起始入库日期</li>
	 *            <li>endBillDate 结束入库日期</li>
	 *            <li>beginOrderNo 起始订单号</li>
	 *            <li>endOrderNo 结束订单号</li>
	 *            <li>beginArrivalNo 结束到货号</li>
	 *            <li>endArrivalNo 结束到货号</li>
	 *            <li>operationNo 业务号</li>
	 *            <li>salerCode 供应单位</li>
	 *            <li>deptCode 部门</li>
	 *            <li>personId 业务员</li>
	 *            <li>currentStatus 当前状态</li>
	 *            以下为从表条件
	 *            <li>materialCode 物资编码</li>
	 *            <li>materialName 物资名称</li>
	 *            <li>beginTradePrice 最小进价</li>
	 *            <li>endTradePrice 最大进价</li>
	 *            <li>agentSign 是否代销</li>
	 *            </ul>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<Object> findAutoIdsByCondition(String unitsCode,String[] operationTypes,Map<String, Object> conditions){
		boolean flag = false;
		boolean operationTypeAll = false;
		String storageCode = (String)conditions.get("storageCode");
		String operationType = (String)conditions.get("operationType");
		String rdType = (String)conditions.get("rdType");
		String billNo = (String)conditions.get("billNo");
		String beginBillNo = (String)conditions.get("beginBillNo");
		String endBillNo = (String)conditions.get("endBillNo");
		Date beginBillDate = (Date)conditions.get("beginBillDate");
		Date endBillDate = (Date)conditions.get("endBillDate");
		String beginOrderNo = (String)conditions.get("beginOrderNo");
		String endOrderNo = (String)conditions.get("endOrderNo");
		String beginArrivalNo = (String)conditions.get("beginArrivalNo");
		String endArrivalNo = (String)conditions.get("endArrivalNo");
		String operationNo = (String)conditions.get("operationNo");
		String salerCode = (String)conditions.get("salerCode");
		String deptCode = (String)conditions.get("deptCode");
		String personId = (String)conditions.get("personId");
		String currentStatus = (String)conditions.get("currentStatus");
		String materialCode = (String)conditions.get("materialCode");
		String materialName = (String)conditions.get("materialName");
		Double beginTradePrice= conditions.get("beginTradePrice")==null?null:Double.valueOf(conditions.get("beginTradePrice").toString());
		Double endTradePrice= conditions.get("endTradePrice")==null?null:Double.valueOf(conditions.get("endTradePrice").toString());
		String agentSign = (String)conditions.get("agentSign");
		//主表条件
		StringBuilder shql = new StringBuilder("select autoId from MaterialRdsMasterDept m where unitsCode='").append(unitsCode).append("'");
		if(storageCode != null && !"".equals(storageCode)){
			shql.append(" and storageCode=:storageCode");
		}
		if(operationType != null && !"".equals(operationType)){
			shql.append(" and operationType=:operationType");
		}else if(operationTypes != null && operationTypes.length > 0){
			operationTypeAll = true;
			shql.append(" and operationType in (:operationTypes)");
		}
		if(rdType != null && !"".equals(rdType)){
			shql.append(" and rdType=:rdType");
		}
		if(billNo != null && !"".equals(billNo)){
			shql.append(" and billNo=:billNo");
		}else{
			if(beginBillNo != null && !"".equals(beginBillNo)){
				shql.append(" and billNo>=:beginBillNo");
			}
			if(endBillNo != null && !"".equals(endBillNo)){
				shql.append(" and billNo<=:endBillNo");
			}
		}
		if(beginBillDate != null){
			shql.append(" and billDate>=:beginBillDate");
		}
		if(endBillDate != null){
			shql.append(" and billDate<=:endBillDate");
		}
		if(beginOrderNo != null && !"".equals(beginOrderNo)){
			shql.append(" and orderNo>=:beginOrderNo");
		}
		if(endOrderNo != null && !"".equals(endOrderNo)){
			shql.append(" and orderNo<=:endOrderNo");
		}
		if(beginArrivalNo != null && !"".equals(beginArrivalNo)){
			shql.append(" and arrivalNo>=:beginArrivalNo");
		}
		if(endArrivalNo != null && !"".equals(endArrivalNo)){
			shql.append(" and arrivalNo<=:endArrivalNo");
		}
		if(operationNo != null && !"".equals(operationNo)){
			shql.append(" and operationNo=:operationNo");
		}
		if(salerCode != null && !"".equals(salerCode)){
			shql.append(" and supplyDeptCode=:salerCode");
		}
		if(deptCode != null && !"".equals(deptCode)){
			shql.append(" and deptCode=:deptCode");
		}
		if(personId != null && !"".equals(personId)){
			shql.append(" and personId=:personId");
		}
		if(currentStatus != null && !"".equals(currentStatus)){
			shql.append(" and currentStatus=:currentStatus");
		}
		//从表条件
		StringBuilder shql2 = new StringBuilder("from MaterialRdsDetailDept d where d.mainAutoId=m.autoId");
		if(materialCode != null && !"".equals(materialCode)){
			flag = true;
			shql2.append(" and d.materialCode=:materialCode");
		}
		if(materialName != null && !"".equals(materialName)){
			flag = true;
			shql2.append(" and materialName like :materialName");
			conditions.put("materialName", "%"+materialName+"%");
		}
		if(beginTradePrice != null){
			flag = true;
			shql2.append(" and d.tradePrice>=:beginTradePrice");
			conditions.put("beginTradePrice", beginTradePrice);
		}
		if(endTradePrice != null){
			flag = true;
			shql2.append(" and d.tradePrice<=:endTradePrice");
			conditions.put("endTradePrice", endTradePrice);
		}
		if(agentSign != null && !"".equals(agentSign)){
			flag = true;
			shql2.append(" and d.agentSign=:agentSign");
		}
		if(flag){
			shql.append(" and exists(").append(shql2).append(")");
		}
		Query query = getSession().createQuery(shql.toString());
		if(operationTypeAll){
			query.setParameterList("operationTypes", operationTypes);
		}
		return query.setProperties(conditions).list();
	}

	/**
	 * 其他入库时查询盘盈入库单主记录
	 * @param start
	 * @param limit
	 * @param unitsCode
	 * @param conditions 包含：
	 *            <ul>
	 *            <li>operationType 业务类型</li>
	 *            <li>storageCode 仓库</li>
	 *            <li>billNo 单据编号</li>
	 *            <li>operationNo 业务号</li>
	 *            <li>beginBillDate 起始入库日期</li>
	 *            <li>endBillDate 结束入库日期</li>
	 *            </ul>
	 * @return
	 */
	public Object[] findUnverifiedMasterByCheckReceiveCondition(int start,
			int limit, String unitsCode, Map<String, Object> conditions) {
		conditions.put("operationType", RdConstant.R_CHECK_PROFIT);
		//String[] operationTypes = new String[]{RdConstant.R_CHECK_PROFIT,RdConstant.R_INITIAL};
		return findUnverifiedMasterByCondition(start,limit,unitsCode,null,conditions);
	}
	
	/**
	 * 其他出库时，查询调拨，盘亏，报损出库单主记录
	 * @param start
	 * @param limit
	 * @param unitsCode
	 * @param conditions 包含：
	 *            <ul>
	 *            <li>operationType 业务类型</li>
	 *            <li>storageCode 仓库</li>
	 *            <li>billNo 单据编号</li>
	 *            <li>operationNo 业务号</li>
	 *            <li>beginBillDate 起始入库日期</li>
	 *            <li>endBillDate 结束入库日期</li>
	 *            </ul>
	 * @return
	 */
	public Object[] findMasterByOtherDeliverCondition(int start,
			int limit, String unitsCode, Map<String, Object> conditions) {
		String[] operationTypes = new String[]{RdConstant.D_CANN,RdConstant.D_CHECK_LOSS,RdConstant.D_REJECT};
		return findUnverifiedMasterByCondition(start,limit,unitsCode,operationTypes,conditions);
	}
	
	/**
	 * 分页查询未审核的入出库主记录
	 * @param start
	 * @param limit
	 * @param unitsCode
	 * @param operationTypes 条件operationType为空或全选时的默认值
	 * @param conditions 包含：
	 *            <ul>
	 *            <li>operationType 业务类型</li>
	 *            <li>storageCode 仓库</li>
	 *            <li>billNo 单据编号</li>
	 *            <li>operationNo 业务号</li>
	 *            <li>beginBillDate 起始单据日期</li>
	 *            <li>endBillDate 结束单据日期</li>
	 *            </ul>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Object[] findUnverifiedMasterByCondition(int start,
			int limit, String unitsCode,String[] operationTypes, Map<String, Object> conditions) {
		Object[] objs = new Object[2];
		boolean operationTypeAll = false;
		String operationType = (String)conditions.get("operationType");
		String storageCode = (String)conditions.get("storageCode");
		String billNo = (String)conditions.get("billNo");
		String operationNo = (String)conditions.get("operationNo");
		Date beginBillDate = (Date)conditions.get("beginBillDate");
		Date endBillDate = (Date)conditions.get("endBillDate");
		StringBuilder shql = new StringBuilder("from MaterialRdsMasterDept where currentStatus='0' and unitsCode='").append(unitsCode).append("'");
		if(operationType != null && !"".equals(operationType)){
			shql.append(" and operationType=:operationType");
		}else{
			if(operationTypes != null && operationTypes.length>0){
				operationTypeAll = true;
				shql.append(" and operationType in (:operationTypes)");
			}
		}
		if(storageCode != null && !"".equals(storageCode)){
			shql.append(" and storageCode=:storageCode");
		}
		if(billNo != null && !"".equals(billNo)){
			shql.append(" and billNo=:billNo");
		}
		if(operationNo != null && !"".equals(operationNo)){
			shql.append(" and operationNo=:operationNo");
		}
		if(beginBillDate != null){
			shql.append(" and billDate>=:beginBillDate");
		}
		if(endBillDate != null){
			shql.append(" and billDate<=:endBillDate");
		}
		Query countQuery = getSession().createQuery("select count(*) "+shql.toString());
		Query query = getSession().createQuery(shql.toString());
		if(operationTypeAll){
			countQuery.setParameterList("operationTypes", operationTypes);
			query.setParameterList("operationTypes", operationTypes);
		}
		int count = Integer.parseInt(countQuery.setProperties(conditions).uniqueResult().toString());
		List<MaterialRdsMasterDept> list = query.setProperties(conditions).setFirstResult(start).setMaxResults(limit).list();
		objs[0] = count;
		objs[1] = list;
		return objs;
	}

	/**
	 * 
	 * @param unitsCode
	 * @param conditions 包含：
	 *            <ul>
	 *            <li>storageCode 仓库</li>
	 *            <li>rdType 入库类别</li>
	 *            <li>beginBillNo 起始入库单号</li>
	 *            <li>endBillNo 结束入库单号</li>
	 *            <li>beginBillDate 起始入库日期</li>
	 *            <li>endBillDate 结束入库日期</li>
	 *            <li>deptCode 部门</li>
	 *            <li>personId 业务员，经手人</li>
	 *            <li>materialCode 物资编码</li>
	 *            <li>agentSign 是否代销</li>
	 *            <li>currentStatus 当前状态</li>
	 *            </ul>
	 * @return
	 */
	public List<Object> findAutoIdsByInitialCondition(String unitsCode,
			Map<String, Object> conditions) {
		conditions.put("operationType", RdConstant.R_INITIAL);
		return findAutoIdsByCondition(unitsCode,null,conditions);
	}

	/**
	 * 高值耗材入库查询  
	 * @param unitsCode
	 * @param conditions 包含：
	 *            <ul>
	 *            <li>storageCode 仓库</li>
	 *            <li>rdType 入库类别</li>
	 *            <li>beginBillNo 起始入库单号</li>
	 *            <li>endBillNo 结束入库单号</li>
	 *            <li>beginBillDate 起始入库日期</li>
	 *            <li>endBillDate 结束入库日期</li>
	 *            <li>deptCode 部门</li>
	 *            <li>personId 业务员，经手人</li>
	 *            <li>currentStatus 当前状态</li>
	 *            </ul>
	 * @return
	 * 
	 * @author 芮玉红
	 * @date 2012.9.27
	 */
	public List<Object> findAutoIdsBySpecialReceiveCondition(String unitsCode,
			Map<String, Object> conditions) {
		conditions.put("operationType", RdConstant.R_AGENCY);
		return findAutoIdsByCondition(unitsCode,null,conditions);

	}

}