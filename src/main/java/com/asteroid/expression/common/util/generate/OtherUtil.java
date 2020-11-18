package com.asteroid.expression.common.util.generate;

import org.apache.commons.lang.StringUtils;

public class OtherUtil {

	/**
	 * 15位身体证号码转18位身份证号码
	 *
	 * @param _15IdCard
	 * @return
	 */
	public static String _15IdCardTo18IdCard(String _15IdCard){
		if(_15IdCard.length()!=15 || !StringUtils.isNumeric(_15IdCard)){
			return _15IdCard;
		}
		String proCity = _15IdCard.substring(0, 6);
		String str17 =proCity+ "19"+_15IdCard.substring(6);
		String  verifyCode = get18IdCardVerifyCode(str17);
		return str17+verifyCode;
	}

	/**
	 * 生成18位身份号码校验码：暂时支付18位身份证
	 * replaced by get18IdCardLastChar
	 * @param str17   身份证前17位号码
	 * @return
	 */
	@Deprecated
	
	public static String get18IdCardVerifyCode(String str17){
		if(str17.length()!=17){
			return null;
		}
		String[] wi = {"7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7", "9", "10", "5", "8", "4", "2"};
		String[] valCodeArr = {"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};
		// ================ 判断最后一位的值 ================
		int TotalmulAiWi = 0;
		for (int i = 0; i < 17; i++) {
			TotalmulAiWi = TotalmulAiWi + Integer.parseInt(String.valueOf(str17.charAt(i))) * Integer.parseInt(wi[i]);
		}
		int modValue = TotalmulAiWi % 11;
		String strVerifyCode = valCodeArr[modValue];
		
		return strVerifyCode;
	}
	/**
	 * 
	 * @param str17 17位身份证值
	 * @return 最后的校验码
	 */
	public static char get18IdCardLastChar(String str17){
		int[] wi= {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
		char[] valCodeArr="10X98765432".toCharArray();
		int TotalmulAiWi = 0;
		// 48为'0'的二进制值
		for (int i = 0; i < 17; i++) {
			TotalmulAiWi = TotalmulAiWi + (str17.charAt(i)-48) * wi[i];
		}
		int modValue = TotalmulAiWi % 11;
		char c = valCodeArr[modValue];
		return c;
	}
	
	/**
	 * 从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位
	 *
	 * @param nonCheckCodeBankCard
	 * @return
	 */
	public static char getBankCardCheckCode(String nonCheckCodeBankCard) {
		if (nonCheckCodeBankCard == null || nonCheckCodeBankCard.trim().length() == 0
				|| !nonCheckCodeBankCard.matches("\\d+")) {
			//如果传的不是数据返回N
			return 'N';
		}
		char[] chs = nonCheckCodeBankCard.trim().toCharArray();
		int luhmSum = 0;
		for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
			int k = chs[i] - '0';
			if (j % 2 == 0) {
				k *= 2;
				k = k / 10 + k % 10;
			}
			luhmSum += k;
		}
		return (luhmSum % 10 == 0) ? '0' : (char) ((10 - luhmSum % 10) + '0');
	}

	/**
	 *
	 * @param str 14位数字
	 * @return
	 */
	public static String getBusinessCheckCode(String str){
		if(str==null || str.length()!=14){
			return null;
		}
		if(!str.matches("[0-9]{14}")){
			return null;
		}
		char[] fncArr = str.toCharArray();
		int ti = 0;
		int si = 0;
		int cj = 0;
		int pj = 10;
		for (int i=0;i<fncArr.length;i++){
			ti = Integer.parseInt(String.valueOf(fncArr[i]));
			pj = (cj % 11) == 0 ? 10 : (cj % 11);
			si = pj + ti;
			cj = (0 == si % 10 ? 10 : si % 10) * 2;
			if (i == fncArr.length-1) {
				pj = (cj % 11) == 0 ? 10 : (cj % 11);
			}
		}
		String check = String.valueOf((pj == 1) ? 0 : 11 - pj);
		return check;
	}

	public static void main(String[] args) {
	   System.out.println(get18IdCardVerifyCode("43058119900714084"));
		System.out.println(getBusinessCheckCode("37110137110134"));
	}
}
