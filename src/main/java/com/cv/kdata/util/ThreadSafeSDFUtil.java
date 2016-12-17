package com.cv.kdata.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ThreadSafeSDFUtil {
	private static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	private static final String DATE_FORMAT = "yyyy-MM-dd";

	@SuppressWarnings("rawtypes")
	private static ThreadLocal threadLocal = new ThreadLocal() {
		protected synchronized Object initialValue() {
			return new SimpleDateFormat(DATETIME_FORMAT);
		}
	};
	
	@SuppressWarnings("rawtypes")
	private static ThreadLocal threadLocal2 = new ThreadLocal() {
		protected synchronized Object initialValue() {
			return new SimpleDateFormat(DATE_FORMAT);
		}
	};
	
	public static DateFormat getDateFormat() {
		return (DateFormat) threadLocal2.get();
	}

	public static DateFormat getDateTimeFormat() {
		return (DateFormat) threadLocal.get();
	}

	public static Date parse(String textDate) throws ParseException {
		return getDateTimeFormat().parse(textDate);
	}
}
