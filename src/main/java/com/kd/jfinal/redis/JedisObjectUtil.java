package com.kd.jfinal.redis;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cv.kdata.util.SerializeUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

public abstract class JedisObjectUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(JedisObjectUtil.class);

	@SuppressWarnings("unchecked")
	public static <T> T getData(Class<T> className, String key) {
		Jedis jedis = RedisUtil.getJedis();

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


	static long totalbytes = 0;
	static long totalredis = 0;
	protected static void saveData(String key, Object obj, int timeout) {
		Jedis jedis = RedisUtil.getJedis();
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
		Jedis jedis = RedisUtil.getJedis();
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
		Jedis jedis = RedisUtil.getJedis();
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

	public static void clearAllData(){
		Jedis jedis = RedisUtil.getJedis();
		jedis.flushAll();
	}

	@SuppressWarnings("rawtypes")
	public static void pipelineData(HashMap<String,Object> map){
		Jedis jedis = RedisUtil.getJedis();
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
		Jedis jedis = RedisUtil.getJedis();
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
		Jedis jedis = RedisUtil.getJedis();
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
