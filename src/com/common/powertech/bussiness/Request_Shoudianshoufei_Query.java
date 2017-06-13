package com.common.powertech.bussiness;

import java.util.TreeMap;

import com.common.powertech.param.GlobalParams;
import com.common.powertech.util.Md5Algorithm;
import com.common.powertech.util.StringUtil;
import com.common.powertech.util.SystemUtil;

/**
 * Created by yeqw on 2015/10/24.
 */
public class Request_Shoudianshoufei_Query extends ParentRequset {

	private static String MeterNo = ""; // 表号
	private static String Amt = ""; // 金额
	private static String IcType = ""; // IC卡类型 1-4428卡；2-4442卡
	private static String ICJsonReq = ""; // 读卡信息
	private static String Prdordno = ""; //订单号

	public static String getRequsetXML() {
		return "<ROOT>" + getTOP() + getBODY() + getTAIL(getMD5str())
				+ "</ROOT>";
	}

	public static String getBODY() {
		String body = "";
		body = "<BODY>";

		if (!"".equals(StringUtil.convertStringNull(MeterNo))) {
			body += "<METER_NO>" + MeterNo + "</METER_NO>";
		}

		if (!"".equals(StringUtil.convertStringNull(Amt))) {
			body += "<AMT>" + Amt + "</AMT>";
		}
		
		if (!"".equals(StringUtil.convertStringNull(Prdordno))) {
			body += "<PRDORDNO>" + Prdordno + "</PRDORDNO>";
		}

		if (!"".equals(StringUtil.convertStringNull(IcType))) {
			body += "<IC_TYPE>" + IcType + "</IC_TYPE>";
		}

		if (!"".equals(StringUtil.convertStringNull(ICJsonReq))) {
			body += "<IC_JSON_REQ>" + ICJsonReq + "</IC_JSON_REQ>";
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
		paramMap.put("METER_NO", MeterNo);
		paramMap.put("PRDORDNO", Prdordno);
		paramMap.put("AMT", Amt);
		paramMap.put("IC_TYPE", IcType);
		paramMap.put("IC_JSON_REQ", ICJsonReq);

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
		Request_Shoudianshoufei_Query.date = date;
	}

	public static String getAmt() {
		return Amt;
	}

	public static void setAmt(String amt) {
		Amt = amt;
	}
	
	public static String getPrdordno() {
		return Prdordno;
	}

	public static void setPrdordno(String prdordno) {
		Prdordno = prdordno;
	}

	public static String getMeterNo() {
		return MeterNo;
	}

	public static void setMeterNo(String meterNo) {
		MeterNo = meterNo;
	}

	public static String getIcType() {
		return IcType;
	}

	public static void setIcType(String icType) {
		IcType = icType;
	}

	public static String getICJsonReq() {
		return ICJsonReq;
	}

	public static void setICJsonReq(String iCJsonReq) {
		ICJsonReq = iCJsonReq;
	}

}
