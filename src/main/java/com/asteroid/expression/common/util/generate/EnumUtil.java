package com.asteroid.expression.common.util.generate;

import org.apache.commons.lang.StringUtils;

import java.util.*;

public class EnumUtil {
	/**
	 * 判断一个类型是否属于某个枚举
	 * 
	 * @param enumerator
	 * @param value
	 * @return
	 */
	public static <T extends Enum<T>> boolean contain(Class<T> enumerator, String value) {
		for (T c : enumerator.getEnumConstants()) {
			if (c.name().equals(value)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 把一个枚举中的类型全部输出到一个List的列表中
	 * 
	 * @param enumerator
	 * @return
	 */
	public static <T extends Enum<T>> List<String> toList(Class<T> enumerator) {
		List<String> l = new ArrayList<String>();
		for (T c : enumerator.getEnumConstants()) {
			l.add(c.name());
		}
		return l;
	}
	
	/**
	 * 把枚举中所有的类型，全部转换成实例放在List中返回
	 * @param enumerator 枚举class类名
	 * @return
	 */
	public static <T extends Enum<T>> Set<T> toEnumSet(Class<T> enumerator) {
		Set<T> s = new HashSet<T>();
		Collections.addAll(s, enumerator.getEnumConstants());
		return s;
	}

	/**
	 * 把枚举类型输出成字符串，有,进行隔开
	 * 
	 * @param enumerator
	 * @return
	 */
	public static <T extends Enum<T>> String toString(Class<T> enumerator) {
		return StringUtils.join(enumerator.getEnumConstants(), ",");
	}
	
	
	public static <T extends Enum<T>> String[] toArray(Class<T> enumerator) {
		String[] array = new String[enumerator.getEnumConstants().length];
		int count=0;
		for (T c : enumerator.getEnumConstants()) {
			array[count]=c.name();
			count++;
		}
		return array;
	}
	

	/**
	 * 把枚举类型输出成字符串，用自定义的字符串进行隔开
	 * 
	 * @param enumerator
	 * @param split
	 * @return
	 */
	public static <T extends Enum<T>> String toString(Class<T> enumerator, String split) {
		return StringUtils.join(enumerator.getEnumConstants(), split);
	}

	/**
	 * 输出枚举的个数
	 * 
	 * @param enumerator
	 * @return
	 */
	public static <T extends Enum<T>> int len(Class<T> enumerator) {
		return enumerator.getEnumConstants().length;
	}
	
	/**
	 * 根据String获取模板中的枚举类型
	 * @param <T>
	 * @param enumerator
	 * @param value
	 * @return
	 */
	public static <T extends Enum<T>> Enum<T> getEnum(Class<T> enumerator,String value) {
		for (T c : enumerator.getEnumConstants()) {
			if (c.name().equals(value)) {
				return c;
			}
		}
		return null;
	}

}
