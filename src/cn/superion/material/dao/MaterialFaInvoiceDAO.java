package cn.superion.material.dao;

import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.material.entity.MaterialFaInvoice;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * MaterialFaInvoice entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.material.entity.MaterialFaInvoice
 * @author MyEclipse Persistence Tools
 */

public class MaterialFaInvoiceDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(MaterialFaInvoiceDAO.class);

	public void save(MaterialFaInvoice transientInstance) {
		log.debug("saving MaterialFaInvoice instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(MaterialFaInvoice persistentInstance) {
		log.debug("deleting MaterialFaInvoice instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MaterialFaInvoice merge(MaterialFaInvoice detachedInstance) {
		log.debug("merging MaterialFaInvoice instance");
		try {
			MaterialFaInvoice result = (MaterialFaInvoice) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MaterialFaInvoice instance) {
		log.debug("attaching dirty MaterialFaInvoice instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MaterialFaInvoice instance) {
		log.debug("attaching clean MaterialFaInvoice instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delByAutoId(String autoId) {
		getSession().createQuery("delete from MaterialFaInvoice where mainAutoId=:autoId").setString("autoId", autoId).executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	public List<MaterialFaInvoice> findByAutoId(String autoId){
		return getSession().createQuery("from MaterialFaInvoice where mainAutoId=:autoId").setString("autoId", autoId).list();
	}
	
	public MaterialFaInvoice addFaInvoice(String autoId,String invoiceId){
		MaterialFaInvoice faInvoice = new MaterialFaInvoice(autoId,invoiceId);
		save(faInvoice);
		return faInvoice;
	}

	public void delByInvoiceAutoId(String invAutoId) {
		getSession().createQuery("delete from MaterialFaInvoice where invoiceAutoId=:autoId").setString("autoId", invAutoId).executeUpdate();
	}
}