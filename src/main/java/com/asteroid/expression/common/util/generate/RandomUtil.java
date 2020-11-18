package com.asteroid.expression.common.util.generate;

import java.util.Random;

public class RandomUtil {
	
	/**
	 * 生成随机整数,e>b
	 * @param b 
	 * @param e
	 * @return
	 */
	static public int getInt(int min, int max) {
		Random rand = new Random();
		return rand.nextInt(max-min+1)+min;
	}
	
	/**
	 * 生成随机年月日，返回格式为：yyyy-MM-dd
	 * @param fromYear,必须是4位格式，如：1999
	 * @param endYear,必须是4位格式，如：2000
	 * @return
	 */
	static public String getDate(int fromYear, int endYear) {
		String format="%d-%s-%s";
		int year=getInt(fromYear,endYear);
		int month=getInt(1,12);
		int day=0;
		if(month==2) {
			day=getInt(1,28);
		}else if(month==1||month==3||month==5||month==7||month==10||month==12) {
			day=getInt(1,31);
		}else {
			day=getInt(1,30);
		}
		String monStr=String.valueOf(month);
		String dayStr=String.valueOf(day);;
		if(month<10) {
			monStr="0"+month;
		}
		
		if(day<10) {
			dayStr="0"+day;
		}
		return String.format(format, year,monStr,dayStr);
	}
	/**
	 * 生成随机时分秒，返回格式为：HH:mm:ss
	 * @return
	 */
	static public String getTime() {
		String format="%s:%s:%s";
		int hour=getInt(0,23);
		int minute=getInt(0,60);
		int second=getInt(0,60);
		String hStr=String.valueOf(hour);
		String mStr=String.valueOf(minute);
		String sStr=String.valueOf(second);
		if(hour<10)hStr="0"+hStr;
		if(minute<10)mStr="0"+mStr;
		if(second<10)sStr="0"+sStr;
		return String.format(format, hStr,mStr,sStr);
	}
	
	/**
	 * 从数组中随机获取字符串
	 * @param all 字符串数组
	 * @return
	 */
	static public String getStr(Object[] all) {
		int pos=getInt(0,all.length-1);
		Object r=all[pos];
		return r.toString();
	}
	/**
	 * 从all字符数组中随机组合获取不超过maxLen长度的字符串
	 * @param all 所有字符表
	 * @param maxLen 最大长度
	 * @return
	 */
	static public String getStr(char[] all, int maxLen) {
		return getStr(all,1,maxLen);
	}
	
	static public String getStr(char[] all, int minLen,int maxLen) {
		int length=getInt(minLen,maxLen);
		char[] chars=new char[length];
		for (int i = 0; i < length; i++) {
			chars[i]=all[getInt(0,all.length-1)];
		}
		return String.valueOf(chars);
	}
	
	/**
	 * 获取固定宽度的数字字符串
	 * @param width 宽度
	 * @return
	 */
	static public String getNumStr(int width,boolean canBeginZero) {
		String r="";
		for (int i = 0; i < width; i++) {
			if(!canBeginZero && i==0)
				r= r+ getInt(1,9);
			else {
				r= r+ getInt(0,9);
			}
		}
		return r;
	}

	/**
	 * 获取固定宽度英文+数字字符串
	 * @param width 宽度
	 * @return
	 */
	static public String getEngNumStr(int width) {
		String str ="1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		char[] all = str.toCharArray();
//		int len = str.length();
		String r="";
		for (int i = 0; i < width; i++) {
			int pos=getInt(0,all.length-1);
			r+=all[pos];
		}
		return r;
	}



	public static void main(String[] args) {
//		String[] all= {
//				"aaa","bbb","cccc"
//		};
		for (int i = 0; i < 30; i++) {
//			System.out.println(getNumStr(20,false));
			System.out.println(getEngNumStr(5));
		}
	}

}
