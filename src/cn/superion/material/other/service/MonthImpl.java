package cn.superion.material.other.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Date;
import java.util.TimeZone;

import cn.superion.base.ReObject;
import cn.superion.center.material.dao.CdMaterialDictDAO;
import cn.superion.center.material.entity.CdMaterialDict;
import cn.superion.material.dao.MaterialCurrentStockDAO;
import cn.superion.material.dao.MaterialMonthDAO;
import cn.superion.material.dao.MaterialRdsStockDAO;
import cn.superion.material.dao.VMaterialRdsDAO;
import cn.superion.material.entity.MaterialMonth;
import cn.superion.material.entity.MaterialRdsStock;
import cn.superion.system.entity.SysUser;
import cn.superion.util.DateUtil;
import cn.superion.util.SessionUtil;

/**
 * 月末结账服务实现
 * 
 * @author 曹国魁
 * 
 */
public class MonthImpl implements IMonth {
	private CdMaterialDictDAO cdMaterialDictDAO;
	private MaterialMonthDAO materialMonthDAO;
	private MaterialRdsStockDAO materialRdsStockDAO;
	private VMaterialRdsDAO vMaterialRdsDAO;
	private MaterialCurrentStockDAO materialCurrentStockDAO;
	public static String TIMEZONE = "GMT+08:00";

	public MaterialCurrentStockDAO getMaterialCurrentStockDAO() {
		return materialCurrentStockDAO;
	}

	public void setMaterialCurrentStockDAO(
			MaterialCurrentStockDAO materialCurrentStockDAO) {
		this.materialCurrentStockDAO = materialCurrentStockDAO;
	}

	public CdMaterialDictDAO getCdMaterialDictDAO() {
		return cdMaterialDictDAO;
	}

	public void setCdMaterialDictDAO(CdMaterialDictDAO cdMaterialDictDAO) {
		this.cdMaterialDictDAO = cdMaterialDictDAO;
	}

	public MaterialMonthDAO getMaterialMonthDAO() {
		return materialMonthDAO;
	}

	public void setMaterialMonthDAO(MaterialMonthDAO materialMonthDAO) {
		this.materialMonthDAO = materialMonthDAO;
	}

	public MaterialRdsStockDAO getMaterialRdsStockDAO() {
		return materialRdsStockDAO;
	}

	public void setMaterialRdsStockDAO(MaterialRdsStockDAO materialRdsStockDAO) {
		this.materialRdsStockDAO = materialRdsStockDAO;
	}

	public VMaterialRdsDAO getvMaterialRdsDAO() {
		return vMaterialRdsDAO;
	}

	public void setvMaterialRdsDAO(VMaterialRdsDAO vMaterialRdsDAO) {
		this.vMaterialRdsDAO = vMaterialRdsDAO;
	}

	@Override
	public ReObject cancelMonth(String strStorageCode, String strYearMonth) {
		ReObject ro = new ReObject("取消结账");
		SysUser user = SessionUtil.getSysUser();
		String unitsCode = user.getUnitsCode();
		String personId = user.getPersonId();
		// 更新月末结账记录
		MaterialMonth mm = materialMonthDAO.findByStorageAndMonth(unitsCode,
				strStorageCode, strYearMonth);
		if (mm == null) {
			throw new RuntimeException("不存在月末结账记录，不能删除！");
		}
		if ("0".equals(mm.getAccountSign())) {
			throw new RuntimeException("仓库[" + strStorageCode + "]在"
					+ strYearMonth + "未结账，不能取消结账！");
		}
		mm.setAccountSign("0");
		mm.setCreateDate(new Date());
		mm.setCreatePerson(personId);
		// 删除收发存汇总记录
		materialRdsStockDAO.deleteByStorageAndMonth(unitsCode, strStorageCode,
				strYearMonth);
		return ro;
	}

	@Override
	public ReObject findMonth(String strStorageCode, String strYear) {
		ReObject ro = new ReObject("查找当前年度的月末结账记录列表");
		String unitsCode = SessionUtil.getUnitsCode();
		List<MaterialMonth> months = materialMonthDAO.findByStorageAndYear(
				unitsCode, strStorageCode, strYear);
		List<MaterialMonth> data = new ArrayList<MaterialMonth>();
		int y = Integer.parseInt(strYear);
		int curMonth = 12;
		String curYear = DateUtil.getCurrentYear();
		if (curYear.equals(strYear)) {
			curMonth = Integer.parseInt(DateUtil.getCurrentMonth());
		}
		for (int i = 1; i <= curMonth; i++) {
			String yearMonth = strYear + "-" + DateUtil.lpad(i);
			MaterialMonth mm = retrieveMonth(months, yearMonth);
			if (mm == null) {
				mm = new MaterialMonth();
				mm.setUnitsCode(unitsCode);
				mm.setStorageCode(strStorageCode);
				mm.setYearMonth(yearMonth);
				mm.setStartDate(DateUtil.getStartDate(y, i));
				mm.setEndDate(DateUtil.getEndDate(y, i));
				mm.setAccountSign("0");
			}
			data.add(mm);
		}
		ro.setData(data);
		return ro;
	}

	private MaterialMonth retrieveMonth(List<MaterialMonth> months,
			String yearMonth) {
		if(months == null)
			return null;
		for (MaterialMonth month : months) {
			if (yearMonth.equals(month.getYearMonth())) {
				return month;
			}
		}
		return null;
	}

	@Override
	public ReObject saveMonth(MaterialMonth materialMonth) {
		ReObject ro = new ReObject("生成收发存汇总记录表");
		SysUser user = SessionUtil.getSysUser();
		String unitsCode = user.getUnitsCode();
		String personId = user.getPersonId();
		String storageCode = materialMonth.getStorageCode();
		String yearMonth = materialMonth.getYearMonth();
		Date startDate = materialMonth.getStartDate();
		Date endDate = materialMonth.getEndDate();
		Date curDate = new Date();
		// 更新月末结账记录
		MaterialMonth mm = materialMonthDAO.findByStorageAndMonth(unitsCode,
				storageCode, yearMonth);
		if (mm == null) {
			materialMonth.setAccountSign("1");
			materialMonth.setCreateDate(curDate);
			materialMonth.setCreatePerson(personId);
			materialMonthDAO.save(materialMonth);
		} else {
			if ("1".equals(mm.getAccountSign())) {
				throw new RuntimeException("仓库[" + storageCode + "]在"
						+ yearMonth + "已结账，不能再次结账！");
			} else {
				mm.setAccountSign("1");
				mm.setCreateDate(curDate);
				mm.setCreatePerson(personId);
			}
		}
		// 写收发存汇总记录
		List<CdMaterialDict> dictList = materialCurrentStockDAO
				.findCurrentMaterialDict(unitsCode, storageCode); // cdMaterialDictDAO.findAll();
		for (CdMaterialDict dict : dictList) {
			String materialId = dict.getMaterialId();
			// 计算期初数量，上个月的结存数量
			String previousMonth = DateUtil.getPreviousMonth(yearMonth);
			MaterialRdsStock preMonthStock = materialRdsStockDAO
					.findByUniqueIndex(unitsCode, storageCode, previousMonth,
							materialId);
			Double initAmount = 0d;
			if (null == preMonthStock) {
				initAmount = vMaterialRdsDAO.addUpInitAmount(unitsCode,
						storageCode, materialId);
				if (null == initAmount)
					initAmount = 0d;
			} else {
				initAmount = preMonthStock.getCurrentStockAmount();
			}
			Object[] objs = vMaterialRdsDAO.addUpRdsAmountMoney(unitsCode,
					storageCode, startDate, endDate, materialId);
			nullToZeros(objs);
			// 采购入库数量
			Double receiveAmount = objs == null ? 0d : Double.valueOf(objs[0]
					.toString());
			// 其他入库数量
			Double otherReceiveAmount = objs == null ? 0d : Double
					.valueOf(objs[1].toString());
			// 领用出库数量
			Double deliveryAmount = objs == null ? 0d : Double.valueOf(objs[2]
					.toString());
			// 其他出库数量
			Double deliveryOtherAmount = objs == null ? 0d : Double
					.valueOf(objs[3].toString());
			// 进价金额
			Double tradeMoney = objs == null ? 0d : Double.valueOf(objs[4]
					.toString());
			// 平均进价
			Double tradePrice = ((receiveAmount + otherReceiveAmount)==0)?0d:Math.round(tradeMoney*100
					/ (receiveAmount + otherReceiveAmount))/100d;
			// 售价金额
			Double retailMoney = objs == null ? 0d : Double.valueOf(objs[5]
					.toString());
			// 平均售价
			Double retailPrice = ((deliveryAmount + deliveryOtherAmount)==0)?0d:Math.round(retailMoney*100
					/ (deliveryAmount + deliveryOtherAmount))/100d;
			// 计算结存数量
			Double currentStockAmount = initAmount + receiveAmount
					+ otherReceiveAmount - deliveryAmount - deliveryOtherAmount;
			MaterialRdsStock stock = new MaterialRdsStock();
			stock.setUnitsCode(unitsCode);
			stock.setStorageCode(storageCode);
			stock.setYearMonth(yearMonth);
			stock.setMaterialClass(dict.getMaterialClass());
			stock.setMaterialId(materialId);
			stock.setMaterialCode(dict.getMaterialCode());
			stock.setMaterialName(dict.getMaterialName());
			stock.setMaterialSpec(dict.getMaterialSpec());
			stock.setMaterialUnits(dict.getMaterialUnits());
			stock.setFactoryCode(dict.getFactoryCode());
			stock.setTradePrice(tradePrice);
			stock.setTradeMoney(tradeMoney);
			stock.setRetailPrice(retailPrice);
			stock.setRetailMoney(retailMoney);
			stock.setInitAmount(initAmount);
			stock.setReceiveAmount(receiveAmount);
			stock.setOtherReceiveAmount(otherReceiveAmount);
			stock.setDeliveryAmount(deliveryAmount);
			stock.setDeliveryOtherAmount(deliveryOtherAmount);
			stock.setCurrentStockAmount(currentStockAmount);
			materialRdsStockDAO.save(stock);
		}
		return ro;
	}

	@Override
	public ReObject saveMonthFee(MaterialMonth materialMonth) {
		ReObject ro = new ReObject("生成结账汇总记录表");
		SysUser user = SessionUtil.getSysUser();
		String unitsCode = user.getUnitsCode();
		String personId = user.getPersonId();
		String storageCode = materialMonth.getStorageCode();
		String yearMonth = materialMonth.getYearMonth();
		Date startDate1 = materialMonth.getStartDate();
		Date endDate = materialMonth.getEndDate(); 
		Date curDate = materialMonthDAO.getSysDate();
		String mon =DateUtil.getCurrentYear()+"-"+DateUtil.getCurrentMonth();
		Date curDate1 = DateUtil.getDateTimeComeString(getCurrentEndTimestamp());
		if(!mon.equals(yearMonth)){
			String curStr = DateUtil.getStringFromDate(endDate)+" 23:59:59";
			curDate1 = DateUtil.getDateTimeComeString(curStr);
		}
		//byzcl
		String previousMonth = DateUtil.getPreviousMonth(yearMonth);
		MaterialMonth previousmm = materialMonthDAO.findByStorageAndMonth(unitsCode,
				storageCode, previousMonth);
		if(previousmm != null){
			startDate1 = previousmm.getEndDate();
		}
		// 更新月末结账记录
		MaterialMonth mm = materialMonthDAO.findByStorageAndMonth(unitsCode,
				storageCode, yearMonth);
		
		materialMonth.setStartDate(startDate1);//startDate byzcl
		materialMonth.setEndDate(curDate1);
		if (mm == null) {
			materialMonth.setAccountSign("1");
			materialMonth.setCreateDate(curDate);
			materialMonth.setCreatePerson(personId);
			materialMonthDAO.save(materialMonth);
		} else {
			if ("1".equals(mm.getAccountSign())) {
				throw new RuntimeException("仓库[" + storageCode + "]在"
						+ yearMonth + "已结账，不能再次结账！");
			} else {
				mm.setAccountSign("1");
				mm.setCreateDate(curDate);
				mm.setCreatePerson(personId);
			}
		}
		// 写收发存汇总记录
		List<CdMaterialDict> dictList = materialCurrentStockDAO
				.findCurrentMaterialDict1(unitsCode, storageCode); // cdMaterialDictDAO.findAll();
		for (CdMaterialDict dict : dictList) {
			String materialId = dict.getMaterialId();
			//查找批次，东方。//byzcl
			List<String> batch = materialCurrentStockDAO.findCurrentMaterialDictBatch(unitsCode,storageCode,materialId);
			// 计算期初数量，上个月的结存数量
			for(int i = 0;i < batch.size();i++){
				String str = batch.get(i).toString();//Integer.parseInt(batch.get(i).toString()) > 0 byzcl
				if(batch.get(i) != null && !"".equals(batch.get(i).toString()) && !batch.get(i).toString().equals("0")){
					//byzclString previousMonth = DateUtil.getPreviousMonth(yearMonth);
					MaterialRdsStock preMonthStock = materialRdsStockDAO
					.findByUniqueIndex(unitsCode, storageCode, previousMonth,
							materialId,String.valueOf(batch.get(i).toString()));
					Double initAmount = 0d;
					Double initMoney = 0d;
					if (null == preMonthStock) {
//						initAmount = vMaterialRdsDAO.addUpInitAmount(unitsCode,
//								storageCode, materialId,String.valueOf(batch.get(i).toString()));
//byzcl						if (null == initAmount)
							initAmount = 0d;
							initMoney = 0d;
					} else {
						initAmount = preMonthStock.getCurrentStockAmount();
						initMoney = preMonthStock.getCurrentStockMoney();
					}
					Object[] objs = vMaterialRdsDAO.addUpRdsAmountMoney(unitsCode,
							storageCode, startDate1, curDate1, materialId,String.valueOf(batch.get(i).toString()));//byzcl endDate startDate
					nullToZeros(objs);
					// 采购入库数量
					Double receiveAmount = objs == null ? 0d : Double.valueOf(objs[0]
							.toString());
					// 其他入库数量
//					Double otherReceiveAmount = objs == null ? 0d : Double
//							.valueOf(objs[1].toString());
					// 领用出库数量
					Double deliveryAmount = objs == null ? 0d : Double.valueOf(objs[1]
							.toString());
					// 其他出库数量
//					Double deliveryOtherAmount = objs == null ? 0d : Double
//							.valueOf(objs[3].toString());
					// 进价金额
					Double tradeMoney = objs == null ? 0d : Double.valueOf(objs[2]
							.toString());
					// 平均进价
//					Double tradePrice = ((receiveAmount + otherReceiveAmount)==0)?0d:Math.round(tradeMoney*100
//							/ (receiveAmount + otherReceiveAmount))/100d;
					// 售价金额
					Double retailMoney = objs == null ? 0d : Double.valueOf(objs[3]
							.toString());
					// 平均售价
//					Double retailPrice = ((deliveryAmount + deliveryOtherAmount)==0)?0d:Math.round(retailMoney*100
//							/ (deliveryAmount + deliveryOtherAmount))/100d;
					// 计算结存数量
					Double currentStockAmount = initAmount + receiveAmount - deliveryAmount;
					Double currentStockMoney = initMoney + tradeMoney - retailMoney;
					MaterialRdsStock stock = new MaterialRdsStock();
					stock.setUnitsCode(unitsCode);
					stock.setStorageCode(storageCode);
					stock.setYearMonth(yearMonth);
					stock.setMaterialClass(dict.getMaterialClass());
					stock.setCountClass(dict.getCountClass());
					stock.setMaterialId(materialId);
					stock.setMaterialCode(dict.getMaterialCode());
					stock.setMaterialName(dict.getMaterialName());
					stock.setMaterialSpec(dict.getMaterialSpec());
					stock.setMaterialUnits(dict.getMaterialUnits());
					stock.setFactoryCode(dict.getFactoryCode());
					//stock.setTradePrice(tradePrice);
					stock.setTradeMoney(tradeMoney);
					//stock.setRetailPrice(retailPrice);
					stock.setRetailMoney(retailMoney);
					stock.setInitAmount(initAmount);
					stock.setInitMoney(initMoney);
					stock.setReceiveAmount(receiveAmount);
					//stock.setOtherReceiveAmount(otherReceiveAmount);
					stock.setDeliveryAmount(deliveryAmount);
					//stock.setDeliveryOtherAmount(deliveryOtherAmount);
					stock.setCurrentStockAmount(currentStockAmount);
					stock.setBatch(String.valueOf(batch.get(i).toString()));
					stock.setCurrentStockMoney(currentStockMoney);
					materialRdsStockDAO.save(stock);
				}
			}
			}
			String bt = "0";
			for (CdMaterialDict dict : dictList) {
				String materialId = dict.getMaterialId();
					MaterialRdsStock preMonthStock = materialRdsStockDAO
					.findByUniqueIndex(unitsCode, storageCode, previousMonth,
							materialId,bt);
					Double initAmount = 0d;
					Double initMoney = 0d;
					if (null == preMonthStock) {
//						initAmount = vMaterialRdsDAO.addUpInitAmount(unitsCode,
//								storageCode, materialId,String.valueOf(batch.get(i).toString()));
//byzcl						if (null == initAmount)
							initAmount = 0d;
							initMoney = 0d;
					} else {
						initAmount = preMonthStock.getCurrentStockAmount();
						initMoney = preMonthStock.getCurrentStockMoney();
					}
					Object[] objs = vMaterialRdsDAO.addUpRdsAmountMoney(unitsCode,
							storageCode, startDate1, curDate1, materialId,bt);//byzcl endDate startDate
					nullToZeros(objs);
					// 采购入库数量
					Double receiveAmount = objs == null ? 0d : Double.valueOf(objs[0]
							.toString());
					// 其他入库数量
//					Double otherReceiveAmount = objs == null ? 0d : Double
//							.valueOf(objs[1].toString());
					// 领用出库数量
					Double deliveryAmount = objs == null ? 0d : Double.valueOf(objs[1]
							.toString());
					// 其他出库数量
//					Double deliveryOtherAmount = objs == null ? 0d : Double
//							.valueOf(objs[3].toString());
					// 进价金额
					Double tradeMoney = objs == null ? 0d : Double.valueOf(objs[2]
							.toString());
					// 平均进价
//					Double tradePrice = ((receiveAmount + otherReceiveAmount)==0)?0d:Math.round(tradeMoney*100
//							/ (receiveAmount + otherReceiveAmount))/100d;
					// 售价金额
					Double retailMoney = objs == null ? 0d : Double.valueOf(objs[3]
							.toString());
					// 平均售价
//					Double retailPrice = ((deliveryAmount + deliveryOtherAmount)==0)?0d:Math.round(retailMoney*100
//							/ (deliveryAmount + deliveryOtherAmount))/100d;
					// 计算结存数量
					Double currentStockAmount = initAmount + receiveAmount - deliveryAmount;
					Double currentStockMoney = initMoney + tradeMoney - retailMoney;
					MaterialRdsStock stock = new MaterialRdsStock();
					stock.setUnitsCode(unitsCode);
					stock.setStorageCode(storageCode);
					stock.setYearMonth(yearMonth);
					stock.setMaterialClass(dict.getMaterialClass());
					stock.setMaterialId(materialId);
					stock.setMaterialCode(dict.getMaterialCode());
					stock.setMaterialName(dict.getMaterialName());
					stock.setMaterialSpec(dict.getMaterialSpec());
					stock.setMaterialUnits(dict.getMaterialUnits());
					stock.setFactoryCode(dict.getFactoryCode());
					//stock.setTradePrice(tradePrice);
					stock.setTradeMoney(tradeMoney);
					//stock.setRetailPrice(retailPrice);
					stock.setRetailMoney(retailMoney);
					stock.setInitAmount(initAmount);
					stock.setInitMoney(initMoney);
					stock.setReceiveAmount(receiveAmount);
					//stock.setOtherReceiveAmount(otherReceiveAmount);
					stock.setDeliveryAmount(deliveryAmount);
					//stock.setDeliveryOtherAmount(deliveryOtherAmount);
					stock.setCurrentStockAmount(currentStockAmount);
					stock.setBatch(bt);
					stock.setCurrentStockMoney(currentStockMoney);
					materialRdsStockDAO.save(stock);
		
		}
		return ro;
	}
	
	/**
	 * 获取当前日期(前一天)及时间(yyyy-MM-dd HH:mm:ss)
	 * 
	 * @return String format:yyyy-MM-dd HH:mm:ss
	 */
	public static String getCurrentTimestamp() {

		/*
		 * java.util.Date dt = Calendar.getInstance(tzCN).getTime();
		 * SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 * return df.format(dt);
		 */
		TimeZone tzCN = TimeZone.getTimeZone(TIMEZONE);
		Calendar calendar = Calendar.getInstance(tzCN);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH)-1;
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		int ss = calendar.get(Calendar.SECOND);
//		return year + "-" + lpad(month) + "-" + lpad(day) + " " + lpad(hour)
//				+ ":" + lpad(minute) + ":" + lpad(ss);
		return year + "-" + lpad(month) + "-" + lpad(day) + " " + "23"
		+ ":" + "59" + ":" + "59";
	}
	
	/**
	 * 获取当前日期 + 23:59:59
	 * 
	 * @return String format:yyyy-MM-dd HH:mm:ss
	 */
	public static String getCurrentEndTimestamp() {
		
		/*
		 * java.util.Date dt = Calendar.getInstance(tzCN).getTime();
		 * SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 * return df.format(dt);
		 */
		TimeZone tzCN = TimeZone.getTimeZone(TIMEZONE);
		Calendar calendar = Calendar.getInstance(tzCN);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		int ss = calendar.get(Calendar.SECOND);
//		return year + "-" + lpad(month) + "-" + lpad(day) + " " + lpad(hour)
//				+ ":" + lpad(minute) + ":" + lpad(ss);
		return year + "-" + lpad(month) + "-" + lpad(day) + " " + "23"
		+ ":" + "59" + ":" + "59";
	}
	
	/**
	 * 补0
	 * 
	 * @param a
	 * @return
	 */
	public static String lpad(int a) {
		return a < 10 ? "0" + a : String.valueOf(a);
	}
	
	private void nullToZeros(Object[] objs) {
		for (int i = 0; i < objs.length; i++) {
			if (objs[i] == null) {
				objs[i] = "0";
			}
		}
	}
	
	public static java.util.Date getDateTimeComeString(String stringdate) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (stringdate != null) {
			try {
				Date date = sf.parse(stringdate);
				return date;
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
