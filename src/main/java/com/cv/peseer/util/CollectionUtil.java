package com.cv.peseer.util;

import java.util.Collection;
import java.util.HashSet;

import org.apache.commons.lang3.StringUtils;

public class CollectionUtil {
	public static <T> String toString(Collection<T> valueSet) {
		if (valueSet.isEmpty())
			return "";

		StringBuilder sBuilder = new StringBuilder("(");
		for (T value : valueSet) {
			sBuilder.append(value.toString()).append(",");
		}

		sBuilder.deleteCharAt(sBuilder.length() - 1).append(")");

		return sBuilder.toString();
	}
	
	public static <T> String toString(Collection<T> valueSet, boolean drop_empty_object, String separator) {
		if (valueSet.isEmpty())
			return "";

		StringBuilder sBuilder = new StringBuilder("(");
		for (T value : valueSet) {
			sBuilder.append(value.toString()).append(separator);
		}

		sBuilder.delete(sBuilder.length()-separator.length(), sBuilder.length()).append(")");

		return sBuilder.toString();
	}

	public static <T> String toStringWithoutBracket(Collection<T> valueSet, boolean drop_empty_object, String separator) {
		if (valueSet.isEmpty())
			return "";

		StringBuilder sBuilder = new StringBuilder("");
		for (T value : valueSet) {
			if (drop_empty_object && StringUtils.isEmpty(value.toString()))
				continue;
			sBuilder.append(value.toString()).append(separator);

		}

		sBuilder.deleteCharAt(sBuilder.length() - separator.length());

		return sBuilder.toString();
	}
	
	//转化为带单引号括上的字符串
	public static <T> String toStringWithSingleComma(Collection<T> valueSet, boolean drop_empty_object, String separator) {
		if (valueSet.isEmpty())
			return "";

		StringBuilder sBuilder = new StringBuilder("");
		for (T value : valueSet) {
			if (drop_empty_object && StringUtils.isEmpty(value.toString()))
				continue;
			sBuilder.append(String.format("'%s'", value.toString())).append(separator);
		}

		sBuilder.deleteCharAt(sBuilder.length() - separator.length());

		return sBuilder.toString();
	}
	
	
	//合并相同的值, 同时根据设置确定是否丢弃掉空对象
	public static String mergeSameAndRemoveEmpty(String group_value, boolean drop_empty_object, String separator){
		if (StringUtils.isEmpty(group_value)){
			return "";
		}
		
		String[] splited_values = group_value.split(separator);
		HashSet<String> uniq_values = new HashSet<>();
		for (String item:splited_values){
			if (drop_empty_object &&StringUtils.isEmpty(item))
				continue;
			uniq_values.add(item);
		}
		
		return toStringWithoutBracket(uniq_values, true, separator);
	}
	
	public static void main(String[] args) {
		String groupValues=",,,,深港产学研创业投资有限公司,深港产学研创业投资有限公司,,,,";
		System.out.println(mergeSameAndRemoveEmpty(groupValues, true, ","));
	}
}
