package com.asteroid.expression.common.util.generate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class GenDataUtil {
	private static char[] CH_COMMON=(
			VarUtil.CHAR_MAP.get(VarUtil.CharType.CH_COMMON)
			+VarUtil.CHAR_MAP.get(VarUtil.CharType.LAST_NAME)
			).toCharArray();
	private static char[] EN_AND_NUM=(
			VarUtil.CHAR_MAP.get(VarUtil.CharType.EN_CHARS)
			+VarUtil.CHAR_MAP.get(VarUtil.CharType.NUMBER)
			).toCharArray();
    /**
     * 随机生成地址
     *
     * @return
     */
    public static String getAddress() {
        String format = "%s市%s区%s路 %d号";
        String city = RandomUtil.getStr(VarUtil.getCities().toArray());
        String area = RandomUtil.getStr(CH_COMMON, 4);
        String road = RandomUtil.getStr(CH_COMMON, 6);
        return String.format(format, city, area, road, RandomUtil.getInt(10, 10000));
    }

    /**
     * 随机生成指定长度的地址
     *
     * @return
     */
    public static String getAddress(int length) {
        String ret =getAddress();
        if(ret.length()>length){
            return ret.substring(0,length);
        }else{
            return ret;
        }
    }
    /**
     * 随机生成车牌
     *
     * @return
     */
    public static String getLicensePlates() {
        String st = "QWERTYUIOPASDFGHJKLZXCVBNM";
        String preffix = VarUtil.CHAR_MAP.get(VarUtil.CharType.STATE_ABBR);
        preffix = preffix.replaceAll("港","").replaceAll("澳","").replaceAll("台","");
        String license = RandomUtil.getStr(preffix.toCharArray(), 1);
        String plate = RandomUtil.getStr(st.toCharArray(), 1);
        String strs = RandomUtil.getStr((st +VarUtil.CHAR_MAP.get(VarUtil.CharType.NUMBER)).toCharArray(), 5, 5);
        while (strs.length() != 5) {
            strs = strs + RandomUtil.getStr((st +VarUtil.CHAR_MAP.get(VarUtil.CharType.NUMBER)).toCharArray(), 5, 5);
        }
        return license + plate + "·" + strs;

    }


    /**
     * 随机生成指定长度的车牌
     *
     * @return
     */
    public static String getLicensePlates(int length) {
        String ret =getLicensePlates();
        if(ret.length()>length){
            return ret.substring(0,length);
        }else{
            return ret;
        }
    }

    /**
     * 随机邮件o
     *
     * @return
     */
    public static String getEmail() {
        String emailCommon = RandomUtil.getStr(EN_AND_NUM, 10);
        emailCommon= emailCommon.replaceAll("-","");
        String emailSuffix = RandomUtil.getStr(VarUtil.EMAIL_SUFFIX);
        return emailCommon + emailSuffix;
    }

    /**
     * 随机生成指定长度的邮件地址
     *
     * @return
     */
    public static String getEmail(int length) {
        String emailSuffix = RandomUtil.getStr(VarUtil.EMAIL_SUFFIX);
        if(emailSuffix.length()>=length){
            return emailSuffix.substring(0,length);
        }
        String emailCommon = RandomUtil.getStr(EN_AND_NUM, length-emailSuffix.length());
        return emailCommon + emailSuffix;
    }

    /**
     * 随机生成18位身份证号
     *
     * @return
     */
    public static String getId() {
    	Object[] provs=VarUtil.AREA_CODE_MAP.keySet().toArray();
        String idProv = RandomUtil.getStr(provs);
        String[] idAreas=VarUtil.AREA_CODE_MAP.get(idProv);
        String idArea = RandomUtil.getStr(idAreas);
        String idBirth = RandomUtil.getDate(1910, 2018).replaceAll("-", "");
        String idSurfix = RandomUtil.getNumStr(3, true);
        String str17= idProv+idArea+idBirth+idSurfix;
        char code = OtherUtil.get18IdCardLastChar(str17);
        return str17+code;
    }

    /**
     * 随机生成15位身份证号
     *
     * @return
     */
    public static String get15Id() {
        Object[] provs=VarUtil.AREA_CODE_MAP.keySet().toArray();
        String idProv = RandomUtil.getStr(provs);
        String[] idAreas=VarUtil.AREA_CODE_MAP.get(idProv);
        String idArea = RandomUtil.getStr(idAreas);
        String idBirth = RandomUtil.getInt(40,99)+"0"+RandomUtil.getInt(1,9)+RandomUtil.getInt(10,28);
        String idSurfix = RandomUtil.getNumStr(3, true);
        String str15= idProv+idArea+idBirth+idSurfix;
        return str15;
    }

    public static String getEnglishCompany(){
        String[] prcity = VarUtil.PRI_CITY_ENG;
        String[] industory = VarUtil.PROFESSION_ENG;
        String[] preffiex = VarUtil.COMPANY_PREFIEXX_ENG;
        return prcity[RandomUtil.getInt(0,prcity.length-1)]+" "+industory[RandomUtil.getInt(0,industory.length-1)]+" "+preffiex[RandomUtil.getInt(0,preffiex.length-1)];
    }

    public static String getEnglishName(){
        String dict = VarUtil.CHAR_MAP.get(VarUtil.CharType.EN_CHARS).replaceAll("_","").replaceAll(" ","").replaceAll("-","");
        return RandomUtil.getStr(dict.toCharArray(),4,8);
    }

    /**
     * 随机生成公司名称
     *
     * @return
     */
    public static String getCompany() {
    	
        String company4PlaceName = getProvAndCity();
        String company4CommonName = RandomUtil.getStr(CH_COMMON, 2,8);
        String companySurfix = RandomUtil.getStr(VarUtil.COMPANY_SUFFIX);
        String r=company4PlaceName + company4CommonName + companySurfix;
        
        int prefixNum = RandomUtil.getInt(0, 1);
        if (prefixNum == 1) {
            return "中国"+r;
        }else
            return r;

    }
    /**
     * 随机生成指定长度公司名称
     *
     * @return
     */
    public static String getCompany(int length) {
        String ret =getCompany();
        if(ret.length()>length){
            return ret.substring(0,length);
        }else{
            return ret;
        }
    }

    /**
     * 随机生成信用卡号
     *
     * @return
     */
    public static String getCreditCard() {
        String creditCardPrefix = RandomUtil.getStr(VarUtil.CREDIT_CARD_PRIFIX);
        String creditCardCommon = RandomUtil.getNumStr(9, true);
        char verifyCode= OtherUtil.getBankCardCheckCode(creditCardPrefix+creditCardCommon);
        return creditCardPrefix + creditCardCommon+verifyCode;
    }

    /**
     * 随机生成指定长度的信用卡号
     *
     * @return
     */
    public static String getCreditCard(int length) {
        String ret =getCreditCard();
        if(ret.length()>length){
            return ret.substring(0,length);
        }else{
            return ret;
        }
    }
    
    /**
     * 随机生成手机号
     *
     * @return
     */
    public static String getMobilePhone() {
        String mobilePhonePrefix = RandomUtil.getStr(VarUtil.MOBIL_PREFIX);
        String mobilePhoneCommon = RandomUtil.getNumStr(8, false);
        return mobilePhonePrefix + mobilePhoneCommon;
    }
    /**
     * 随机生成手机号
     *
     * @return
     */
    public static String getMobilePhone(int length) {
        String ret =getMobilePhone();
        if(ret.length()>length){
            return ret.substring(0,length);
        }else{
            return ret;
        }
    }

    /**
     * 随机生成固定电话,默认分隔符为:-
     *
     * @return
     */
    public static String getTelePhone() {
        return getDelimiterTelePhone("-");
    }

    /**
     * 随机生成带指定分隔符固定电话
     *
     * @param delimiter 分隔符
     * @return
     */
    public static String getDelimiterTelePhone(String delimiter){
        String telePhonePrefix = RandomUtil.getStr(VarUtil.TEL_AREA);
        int digit = RandomUtil.getInt(6, 8);
        String telePhoneCommon = RandomUtil.getNumStr(digit, false);
        return telePhonePrefix + delimiter+ telePhoneCommon;
    }

    /**
     * 随机生成指定长度的固定电话
     *
     * @return
     */
    public static String getTelePhone(int length) {
        String ret =getTelePhone();
        if(ret.length()>length){
            return ret.substring(0,length);
        }else{
            return ret;
        }
    }

    /**
     * 随机生成姓名
     *
     * @return
     */
    public static String getName() {
    	List<String> lNames=new ArrayList<String>();
    	for(char c:VarUtil.CHAR_MAP.get(VarUtil.CharType.LAST_NAME).toCharArray()) {
    		lNames.add(String.valueOf(c));
    	}
    	for(String s:VarUtil.TWO_LAST_NAME) {
    		lNames.add(s);
    	}
        String lastName = RandomUtil.getStr(lNames.toArray());//姓
        String commonName = RandomUtil.getStr(CH_COMMON, 2);//名
        return lastName + commonName;
    }

    /**
     * 随机生成指定长度的姓名
     *
     * @return
     */
    public static String getName(int length) {
        List<String> lNames=new ArrayList<String>();
        for(char c:VarUtil.CHAR_MAP.get(VarUtil.CharType.LAST_NAME).toCharArray()) {
            lNames.add(String.valueOf(c));
        }
        String lastName = RandomUtil.getStr(lNames.toArray());//姓
        if(length==1){
            return lastName;
        }
        String commonName = RandomUtil.getStr(CH_COMMON, length-1,length-1);//名
        return lastName + commonName;
    }

    /**
     * 随机生成军官证号
     *
     * @return
     */
    public static String getOfficerId() {
        int policeNoPrefix = RandomUtil.getInt(0, 7);
        String policeNoCommon = RandomUtil.getNumStr(6, true);
        return policeNoPrefix + policeNoCommon;
    }

    /**
     * 随机工资
     *
     * @return
     */
    public static String getSalary() {
        return RandomUtil.getInt(30,300)+"00.00";
    }

    /**
     * 随机生成对公账户号 (20位数字,暂支持对建行)
     *
     * @param
     */
    public static String getCorporateaccount() {
        return RandomUtil.getNumStr(20, false);
    }

    /**
     * 随机生成组织机构代码
     *
     * @return
     */
    public static String getOrgCode() {
        Random random = new Random();
        String str = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int[] ws = { 3, 7, 9, 10, 5, 8, 4, 2 };
        StringBuilder strb = new StringBuilder();
        int sum = 0;
        //生成8位字符
        for(int i=0;i<8;i++){
            int index = random.nextInt(str.length()-1);
            strb.append(str.charAt(index));
            sum += index*ws[i];
        }
        int c9 = 11 - sum % 11;
        String chk = c9 == 10 ? "X" : (c9 == 11 ? "0":c9+"");
        if(chk.length()!=1){
            System.out.println(chk);
            return null;
        }
        return strb.toString()+chk;
    }

    /**
     * 15位企业纳税识别号
     *
     * @return
     */
    public static String getTaxpayerId15(){
        Object[] provs= VarUtil.AREA_CODE_MAP.keySet().toArray();
        String prov = RandomUtil.getStr(provs);
        String[] idAreas=VarUtil.AREA_CODE_MAP.get(prov);
        String area = RandomUtil.getStr(idAreas);
        return prov+area+ getOrgCode();
    }
    /**
     * 17位个人纳税识别号
     *
     * @return
     */
    public static String getTaxpayerId17(){
        return GenDataUtil.get15Id()+RandomUtil.getNumStr(2,true);
    }
    /**
     * 生成18位纳税识别号: 统一社会信息编码
     *
     * @return
     */
    public static String getTaxpayerId18(){
        String baseCode = "0123456789ABCDEFGHJKLMNPQRTUWXY";
        int[] ws = {1, 3, 9, 27, 19, 26, 16, 17, 20, 29, 25, 13, 8, 24, 10, 30, 28};
        Random random = new Random();
        StringBuilder strb = new StringBuilder();
        int sum = 0;
        for (int i = 0; i < 17; ++i) {
            int index = random.nextInt(baseCode.length() - 1);
            strb.append(baseCode.charAt(index));
            sum += index*ws[i];
        }
        int num = (31 - sum % 31)==31 ? 0 : (31 - sum % 31);
        String cheak = String.valueOf(baseCode.charAt(num));
        return strb.toString()+cheak;
    }
    /**
     * 20位个人纳税识别号
     *
     * @return
     */
    public static String getTaxpayerId20(){
        return GenDataUtil.getId()+RandomUtil.getNumStr(2,true);
    }

    /**
     * 生成15位工商注册号
     * @return
     */
    public static String getBusinesslicense15(){
        Object[] provs= VarUtil.AREA_CODE_MAP.keySet().toArray();
        String prov = RandomUtil.getStr(provs);
        String[] idAreas=VarUtil.AREA_CODE_MAP.get(prov);
        String area = RandomUtil.getStr(idAreas);
        String fnStr = prov+area+RandomUtil.getNumStr(8,true);
        String check = OtherUtil.getBusinessCheckCode(fnStr);
        return fnStr+check;
    }

    /**
     * 随机生成邮编
     *
     * @return
     */
    public static String getPostCode() {
        Set<String> proSet = VarUtil.AREA_CODE_MAP.keySet();
        List<String> proList =new ArrayList<String>(proSet);
        String proCode =proList.get(RandomUtil.getInt(0,proSet.size()-1));
        String[] cityCodeArr = VarUtil.AREA_CODE_MAP.get(proCode);
        String cityCode = cityCodeArr[RandomUtil.getInt(0,cityCodeArr.length-1)];
        return proCode+cityCode;
    }
    
    private static String getProvAndCity() {
    	String r = RandomUtil.getStr(VarUtil.getProvince().toArray());
        r=r+RandomUtil.getStr(VarUtil.CITY_MAP.get(r));
        return r;
    }

    /**
     * 获取车架号
     *
     * @return
     */
    public static String getCarSerialCode() {
        String beforeStr = GenCarSerialUtil.prepareBeforeStr();
        String afterStr = GenCarSerialUtil.prepareAfterStr();
        String vin = GenCarSerialUtil.spellVin(beforeStr, afterStr);
        return vin;
    }

    private static class GenCarSerialUtil {
        /**
         * 计算车架号的校验位
         *
         * @return
         */
        private static String getCarSerialCheck(String vin) {
            /**车架号校验位计算数组*/
            Object[][] KVMACTHUP = new Object[][]{{'A', 1}, {'B', 2}, {'C', 3}, {'D', 4}, {'E', 5}, {'F', 6}, {'G', 7}, {'H', 8}, {'I', 0}, {'J', 1}, {'K', 2}, {'L', 3}, {'M', 4}, {'N', 5}, {'O', 0}, {'P', 7}, {'Q', 8}, {'R', 9}, {'S', 2}, {'T', 3}, {'U', 4}, {'V', 5}, {'W', 6}, {'X', 7}, {'Y', 8}, {'Z', 9}};
            /**车架号数据加权数组*/
            int[] WEIGHTVALUE = new int[]{8, 7, 6, 5, 4, 3, 2, 10, 0, 9, 8, 7, 6, 5, 4, 3, 2};
            char[] Vin = vin.toCharArray();
            int sum = 0, tempValue = 0;
            char temp;
            for (int i = 0; i < 17; i++) {
                if (Vin[i] >= 'a' && Vin[i] <= 'z') {
                    temp = (char) (Vin[i] - 32);
                } else if ((Vin[i] >= 'A') && (Vin[i] <= 'Z')) {
                    temp = Vin[i];
                } else if ((Vin[i] >= '0') && (Vin[i] <= '9')) {
                    tempValue = Integer.parseInt(String.valueOf(Vin[i]));
                    temp = Vin[i];
                } else {
                    return "ERROR";
                }
                if ((temp >= 'A') && (temp <= 'Z')) {
                    for (int j = 0; j < 26; j++) {
                        if (temp == (char) KVMACTHUP[j][0]) {
                            tempValue = (int) KVMACTHUP[j][1];
                        }
                    }
                }
                sum += tempValue * WEIGHTVALUE[i];
            }
            int reslt = sum % 11;
            if (reslt != 10) {
                return String.valueOf(reslt);
            } else {
                return "X";
            }
        }

        /**
         * 判断vin是否正确
         *
         * @param vin
         * @return
         */
        private static boolean isVin(String vin) {
            String isuredCode = getCarSerialCheck(vin);
            if (vin.substring(8, 9).equals(isuredCode)) {
                return true;
            } else {
                return false;
            }
        }

        /**
         * 拼接车架号
         *
         * @param beforeStr
         * @param afterStr
         * @return
         */
        private static String spellVin(String beforeStr, String afterStr) {
            StringBuffer vinBuffer = new StringBuffer();
            String preVin = vinBuffer.append(beforeStr).append("X").append(afterStr).toString();
            String isuredCode = getCarSerialCheck(preVin);
            String vin = new StringBuffer(beforeStr).append(isuredCode).append(afterStr).toString();
            if (isVin(vin)) {
                return vin;
            } else {
                return spellVin(beforeStr, afterStr);
            }
        }

        /**
         * 生成随机前缀
         *
         * @return
         */
        private static String prepareBeforeStr() {
            StringBuffer stringBuffer = new StringBuffer();
            /**车架号地区代码数组*/
            String areaArray[] = new String[]{"1", "2", "3", "6", "9", "J", "K", "L", "R", "S", "T", "V", "W", "Y", "Z", "G"};
            /**车架号中可能出现的字符数组,没有I，O，Q，第10位不能出现I，O，Q，U，Z，0*/
            String charArray[] = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "L", "M", "N", "P", "R", "S", "T", "V", "W", "X", "Y"};
            for (int i = 0; i < 8; i++) {
                stringBuffer.append(charArray[(int) (Math.random() * 100 % areaArray.length)]);
            }
            return stringBuffer.toString();
        }

        /**
         * 生成随机后缀
         *
         * @return
         */
        private static String prepareAfterStr() {
            StringBuffer stringBuffer = new StringBuffer();
            /**车架号中可能出现的字符数组,没有I，O，Q，第10位不能出现I，O，Q，U，Z，0*/
            final String charArray[] = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "L", "M", "N", "P", "R", "S", "T", "V", "W", "X", "Y"};
            for (int i = 0; i < 3; i++) {
                stringBuffer.append(charArray[(int) (Math.random() * 100 % charArray.length)]);
            }
            for (int i = 0; i < 5; i++) {
                stringBuffer.append(Integer.toHexString(new Random().nextInt(16)).toUpperCase());
            }
            return stringBuffer.toString();
        }
    }

    public static void main(String[] args) {
        System.out.println("随机生成公司名称:" + getCompany());
        System.out.println("随机生成信用卡号:" + getCreditCard());
        System.out.println("随机生成手机号:" + getMobilePhone());
        System.out.println("随机生成固定电话:" + getTelePhone());
        System.out.println("随机生成姓名:" + getName());
        System.out.println("随机生成邮编:" + RandomUtil.getNumStr(6, true));
        System.out.println("随机生成国际移动台设备识别码:" + RandomUtil.getNumStr(15, true));
        System.out.println("随机生成军官证号:" + getOfficerId());
        System.out.println("随机生成邮箱:" + getEmail());
        System.out.println("随机生成生日:" + RandomUtil.getDate(1910, 2018));
        System.out.println("随机生成身份证号:" + getId());
        System.out.println("随机生成地址:" + getAddress());
        System.out.println("随机生成工资:" + getSalary());
        System.out.println("随机生成车牌:" + getLicensePlates());
    }
}
