package com.cv.peseer.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.msgpack.MessagePack;
import org.msgpack.template.Template;
import org.msgpack.template.Templates;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

public abstract class JedisObjectUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(JedisObjectUtil.class);

	public static final String SEARCH_BASIC_PREFIX = "search_basic";
	public static final String SEARCH_EXTEND_PREFIX = "search_ext";
	public static final String SEARCH_RPT_PREFIX = "search_rpt";
	public static final String LOGIN_QUERY_PREFIX = "login_query";
	public static final String LOGIN_ORG_QUERY_PREFIX = "login_org_query";
	public static final String LOGIN_RPT_QUERY_PREFIX = "login_rpt_query";
	public static final String ORG_INFO_PREFIX = "org_info";
	public static final String ORG_INVEST_EVENT_PREFIX = "org_invest_event";
	public static final String ORG_EXIT_EVENT_PREFIX = "org_exit_event";
	public static final String QXB_ENT_DATA_PREFIX = "qxb_ent_data";
	public static final String FUND_INFO_PREFIX = "fund_info";
	public static final String WORD_SEGMENT_PREFIX = "word_segment"; // LTP分词服务
	public static final String DISTANCE_PREFIX = "distance"; // 拓词服务
	public static final String DISTANCE_EXCEPTION_PREFIX = "distance_exception"; // 拓词服务
	public static final String COMPANY_DETAIL_SS_PREFIX = "CD_ss"; // 上市公司或者A股
	public static final String COMPANY_DETAIL_XSB_PREFIX = "CD_xsb"; // 新三板
	public static final String COMPANY_DETAIL_ENT_PREFIX = "CD_ent"; // 未上市公司
	public static final String STATICS_INFO = "statics_info";	//统计信息
	public static final String CHARTS_INFO = "charts_info";	//统计信息
	public static final String SEPARATOR = ":";
	public static final String DATE_FORMAT = "yyyyMMdd";
	public static final int HALF_HOUR = 30*60; //半小时 timeout
	public static final int ONE_HOUR = 60*60; //一小时
	public static final int ONE_DAY = 24*ONE_HOUR; //一天
	public static final int ONE_WEEK = 7*ONE_DAY; //一周
	public static final int ONE_MONTH = 30*ONE_DAY; //一个月30天

	@SuppressWarnings("unchecked")
	public static <T> T getData(Class<T> className, String key) {
		Jedis jedis = RedisAliyun.getJedis();

		T queryValue = null;

		try {

			byte[] data = jedis.get(key.getBytes());
			if (data != null) {
				queryValue = (T) SerializeUtil.deserialize(data,className);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			LOGGER.error("", ex);
		} finally {
			if (jedis != null) {
				try {
					jedis.close();
				} catch (Exception ex) {
					ex.printStackTrace();
					LOGGER.error("", ex);
				}
			}
		}

		return queryValue;
	}

	public static <T> List<T> getDataFromList(String key, Class<T> clazz) {
		Jedis jedis = RedisAliyun.getJedis();
		List<T> queryValue = null;
		try {

			byte[] data = jedis.get(key.getBytes());
			if (data != null) {
				MessagePack messagePack = new MessagePack();
				Template<T> elementTemplate = messagePack.lookup(clazz);
				Template<List<T>> listTmpl = Templates.tList(elementTemplate);

				queryValue = messagePack.read(data, listTmpl);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			LOGGER.error("", ex);
		} finally {
			if (jedis != null) {
				try {
					jedis.close();
				} catch (Exception ex) {
					ex.printStackTrace();
					LOGGER.error("", ex);
				}
			}
		}

		return queryValue;
	}
	static long totalbytes = 0;
	static long totalredis = 0;
	protected static void saveData(String key, Object obj, int timeout) {
		Jedis jedis = RedisAliyun.getJedis();
		try {
			 jedis.set(key.getBytes(), SerializeUtil.serialize(obj));
			if (timeout > 0) {
				jedis.expire(key, timeout);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			LOGGER.error("", ex);
		} finally {
			if (jedis != null) {
				try {
					jedis.close();
				} catch (Exception ex) {
					ex.printStackTrace();
					LOGGER.error("", ex);
				}
			}
		}
	}

	protected static void delData(String key) {
		Jedis jedis = RedisAliyun.getJedis();
		try {
			jedis.del(key.getBytes());
		} catch (Exception ex) {
			ex.printStackTrace();
			LOGGER.error("", ex);
		} finally {
			if (jedis != null) {
				try {
					jedis.close();
				} catch (Exception ex) {
					ex.printStackTrace();
					LOGGER.error("", ex);
				}
			}
		}
	}

	public static void batchDelData(String key_pre_str) {
		Jedis jedis = RedisAliyun.getJedis();
		try {
			String patten = (String.format("%s", key_pre_str)+"*");
			Set<byte[]> key_bytes = jedis.keys(patten.getBytes());
			for (byte[] key_byte : key_bytes) {
				jedis.del(key_byte);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			LOGGER.error("", ex);
		} finally {
			if (jedis != null) {
				try {
					jedis.close();
				} catch (Exception ex) {
					ex.printStackTrace();
					LOGGER.error("", ex);
				}
			}
		}
	}

	protected static <T> HashSet<T> getDataForSet(String key, Class<T> className) {
		Jedis jedis = RedisAliyun.getJedis();
		HashSet<T> queryValue = new HashSet<>();

		try {
			Set<byte[]> dataSet = jedis.smembers(key.getBytes());
			if (dataSet != null) {
				MessagePack messagePack = new MessagePack();

				for (byte[] value : dataSet) {
					T obj = messagePack.read(value, className);
					queryValue.add(obj);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			LOGGER.error("", ex);
		} finally {
			if (jedis != null) {
				try {
					jedis.close();
				} catch (Exception ex) {
					ex.printStackTrace();
					LOGGER.error("", ex);
				}
			}
		}

		return queryValue;
	}

	protected static <T> void saveDataForSet(String key, HashSet<T> hashSetObject, int expire_time) {
		Jedis jedis = RedisAliyun.getJedis();
		try {

			byte[][] values = new byte[hashSetObject.size()][];
			MessagePack messagePack = new MessagePack();
			int index = 0;
			for (Object hashsetObj : hashSetObject) {
				values[index] = messagePack.write(hashsetObj);
				index++;
			}
			jedis.del(key.getBytes());
			jedis.sadd(key.getBytes(), values);
			if (expire_time > 0) {
				jedis.expire(key.getBytes(), expire_time);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			LOGGER.error("", ex);
		} finally {
			if (jedis != null) {
				try {
					jedis.close();;
				} catch (Exception ex) {
					ex.printStackTrace();
					LOGGER.error("", ex);
				}
			}
		}
	}

	public static void clearAllData(){
		Jedis jedis = RedisAliyun.getJedis();
		jedis.flushDB();
	}

	@SuppressWarnings("rawtypes")
	public static void pipelineData(HashMap<String,Object> map){
		Jedis jedis = RedisAliyun.getJedis();
		Pipeline pipe = jedis.pipelined();
		try {
			Iterator iter = map.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				String key = (String) entry.getKey();
				Object val = entry.getValue();
				pipe.set(key.getBytes(),SerializeUtil.serialize(val));
			}
			pipe.sync();
		} catch (Exception ex) {
			ex.printStackTrace();
			LOGGER.error("", ex);
		} finally {
			if (jedis != null) {
				try {
					jedis.close();;
				} catch (Exception ex) {
					ex.printStackTrace();
					LOGGER.error("", ex);
				}
			}
		}
	}

	public static <T> void pipelineMap(HashMap<String,List<T>> map){
		Jedis jedis = RedisAliyun.getJedis();
		Pipeline pipe = jedis.pipelined();
		try {
			Iterator iter = map.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				String key = (String) entry.getKey();
				Object val = entry.getValue();
				pipe.set(key.getBytes(),SerializeUtil.serialize(val));
			}
			pipe.sync();
		} catch (Exception ex) {
			ex.printStackTrace();
			LOGGER.error("", ex);
		} finally {
			if (jedis != null) {
				try {
					jedis.close();;
				} catch (Exception ex) {
					ex.printStackTrace();
					LOGGER.error("", ex);
				}
			}
		}
	}

	public static void saveHMap(String key,HashMap<String,String> map){
		Jedis jedis = RedisAliyun.getJedis();
		try {
			jedis.hmset(key, map);
		} catch (Exception ex) {
			ex.printStackTrace();
			LOGGER.error("", ex);
		} finally {
			if (jedis != null) {
				try {
					jedis.close();;
				} catch (Exception ex) {
					ex.printStackTrace();
					LOGGER.error("", ex);
				}
			}
		}
		jedis.hmset(key, map);
	}
}
