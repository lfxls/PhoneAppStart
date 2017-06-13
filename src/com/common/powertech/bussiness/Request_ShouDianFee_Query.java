package com.common.powertech.bussiness;

import java.util.TreeMap;

import com.common.powertech.param.GlobalParams;
import com.common.powertech.util.Md5Algorithm;
import com.common.powertech.util.StringUtil;
import com.common.powertech.util.SystemUtil;

/**
 * Created by ouyangguozhao on 2015/10/24.
 */
public class Request_ShouDianFee_Query extends ParentRequset {

	private static String Amt = ""; // 购电金额
	private static String MeterNo = ""; // 购电表号
	private static String ReceID = ""; // 应收账单ID 多条以“|”分割

	public static String getRequsetXML() {
		return "<ROOT>" + getTOP() + getBODY() + getTAIL(getMD5str())
				+ "</ROOT>";
	}

	public static String getBODY() {
		String body = "";
		body = "<BODY>";

		if (!"".equals(StringUtil.convertStringNull(Amt))) {
			body += "<AMT>" + Amt + "</AMT>";
		}
		
		if (!"".equals(StringUtil.convertStringNull(MeterNo))) {
			body += "<METER_NO>" + MeterNo + "</METER_NO>";
		}
		
		if (!"".equals(StringUtil.convertStringNull(ReceID))) {
			body += "<RECE_ID>" + ReceID + "</RECE_ID>";
		}
		
		body += "</BODY>";
		return body;
	}

	public static String getMD5str() {

		TreeMap<String, String> paramMap = new TreeMap<String, String>();
		// TOP
		paramMap.put("IMEI", SystemUtil.getIMEI(context));
		paramMap.put("SESSION_ID", GlobalParams.SESSION_ID);
		paramMap.put("REQUEST_TIME", date);
		paramMap.put("SOURCE", "3");
		paramMap.put("LOCAL_LANGUAGE", SystemUtil.getLocalLanguage(getContext()));

		// BODY
		paramMap.put("AMT", Amt);
		paramMap.put("METER_NO", MeterNo);
		paramMap.put("RECE_ID", ReceID);

		// TAIL
		paramMap.put("SIGN_TYPE", "1");

		String signStr = bulidParam(paramMap);
		String sign = "";
		try {
			sign = Md5Algorithm.getInstance().md5Digest(signStr.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sign;
	}

	public static String getDate() {
		return date;
	}

	public static void setDate(String date) {
		Request_ShouDianFee_Query.date = date;
	}

	public static String getAmt() {
		return Amt;
	}

	public static void setAmt(String amt) {
		Amt = amt;
	}
	
	public static String getMeterNo() {
		return MeterNo;
	}

	public static void setMeterNo(String meterno) {
		MeterNo = meterno;
	}

	public static String getReceID() {
		return ReceID;
	}

	public static void setReceID(String receID) {
		ReceID = receID;
	}
}
