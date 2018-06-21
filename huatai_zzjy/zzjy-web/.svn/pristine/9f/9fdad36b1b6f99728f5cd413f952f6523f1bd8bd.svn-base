package com.fairyland.jdp.framework.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

public class RedisUtil {
	private static Logger logger = LoggerFactory.getLogger(RedisUtil.class);
	private static String LOCKED = "LOCKED";
	private static JedisCluster jc;

	private synchronized static JedisCluster getConnection() {
		if (jc == null) {
			Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
			// Jedis Cluster will attempt to discover cluster nodes
			// automatically
			String addressProp = PropsUtil.get("redis.server.address");
			String[] addresses = addressProp.split(",");
			for (String address : addresses) {
				String[] hostPort = address.split(":");
				HostAndPort hostAndPort = new HostAndPort(hostPort[0], Integer.parseInt(hostPort[1]));
				jedisClusterNodes.add(hostAndPort);
			}
			String password = PropsUtil.get("redis.server.password");
			if (password == null || password.equals("")) {
				jc = new JedisCluster(jedisClusterNodes);
			} else {
				GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
				genericObjectPoolConfig.setMaxTotal(100);
				jc = new JedisCluster(jedisClusterNodes, 20000, 20000, 3, password, genericObjectPoolConfig);
			}
		}
		return jc;
	}

	/**
	 * 
	 * @param key
	 * @param timeout
	 *            超时时间，秒
	 * @param value
	 */
	public static void set(String key, int timeout, Object value) {
		// String json = JSON.toJSONString(value);
		String serialized = objectToBase64(value);
		logger.info("Redis set：key=" + key);
		if (timeout <= 0)
			getConnection().set(key, serialized);
		else
			getConnection().setex(key, timeout, serialized);
	}

	public static void setByJson(String key, int timeout, Object value) {
		String json = JSON.toJSONString(value);
		// String serialized = objectToBase64(value);
		logger.info("Redis set：key=" + key);
		if (timeout <= 0)
			getConnection().set(key, json);
		else
			getConnection().setex(key, timeout, json);
	}

	// public static void set(String key,Object value){
	// set(key,0, value);
	// }
	public static Object get(String key) throws ClassNotFoundException, IOException {
		String base64 = getConnection().get(key);
		return base64ToObject(base64);
	}

	public static String getByJson(String key) {
		String json = getConnection().get(key);
		return json;
	}

	public static void del(String key) {
		JedisCluster jc = getConnection();
		if (jc.exists(key)) {
			jc.del(key);
		}
	}

	public static <T> T get(String key, Class<T> clazz) throws ClassNotFoundException, IOException {
		Object obj = get(key);
		return (T) obj;
	}

	public static <T> T getByJson(String key, Class<T> clazz) {
		String json = getByJson(key);
		return JSON.parseObject(json, clazz);
	}

	public static Boolean exists(String key) {
		return getConnection().exists(key);
	}

	public static <T> List<T> getListByJson(String key, Class<T> clazz) {
		String json = getByJson(key);
		List<T> obj = JSON.parseArray(json, clazz);
		return obj;
	}

	// 创建一个名称为key的锁，如果已经有该锁，则会等待直到解锁或者达到超时时间timeout（毫秒）。如果没有该锁，则创建锁并锁住expire（秒）
	public static boolean lock(String key, long timeout, int expire) {
		JedisCluster redisClient = getConnection();
		long nanoTime = System.nanoTime();
		timeout *= 1000000;
		boolean lock = false;
		try {
			// 在timeout的时间范围内不断轮询锁
			while (System.nanoTime() - nanoTime < timeout) {
				// 锁不存在的话，设置锁并设置锁过期时间，即加锁
				if (redisClient.setnx(key, LOCKED) == 1) {
					redisClient.expire(key, expire);// 设置锁过期时间是为了在没有释放
					// 锁的情况下锁过期后消失，不会造成永久阻塞
					lock = true;
					return lock;
				}
				System.out.println("出现锁等待");
				// 短暂休眠，避免可能的活锁
				Thread.sleep(3, (int) (Math.random() * 30));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void unlock(String key) {
		try {
			getConnection().del(key);// 直接删除
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public static String objectToBase64(Object obj) {
		byte[] objByte = toByteArray(obj);
		String objBase64 = Base64Util.encode(objByte);
		return objBase64;
	}

	public static Object base64ToObject(String objBase64) throws ClassNotFoundException, IOException {
		byte[] objByte = Base64Util.decodeToByte(objBase64);
		Object obj = toObject(objByte);
		return obj;
	}

	public static byte[] toByteArray(Object obj) {
		byte[] bytes = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(obj);
			oos.flush();
			bytes = bos.toByteArray();
			oos.close();
			bos.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return bytes;
	}

	/**
	 * 数组转对象
	 * 
	 * @param bytes
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Object toObject(byte[] bytes) throws IOException, ClassNotFoundException {
		Object obj = null;
		// try {
		ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
		ObjectInputStream ois = new ObjectInputStream(bis);
		obj = ois.readObject();
		ois.close();
		bis.close();
		// } catch (IOException ex) {
		// ex.printStackTrace();
		// } catch (ClassNotFoundException ex) {
		// ex.printStackTrace();
		// }
		return obj;
	}

	public static TreeSet<String> keys(String pattern) {
		TreeSet<String> keys = new TreeSet<>();
		Map<String, JedisPool> clusterNodes = getConnection().getClusterNodes();
		for (String k : clusterNodes.keySet()) {
			JedisPool jp = clusterNodes.get(k);
			Jedis connection = null;
			try {
				long start = System.currentTimeMillis();
				connection = jp.getResource();
				keys.addAll(connection.keys(pattern));
				long end = System.currentTimeMillis();
				System.out.println(k+"用时："+(end-start)+"ms");
			} catch (Exception e) {
				logger.error("Getting keys error: {}", e);
			} finally {
				// 用完一定要close这个链接！！！
				if(connection!=null)
					connection.close();
			}
		}
		return keys;
	}
	public static void printKeys(String pattern,String folder) {
		Map<String, JedisPool> clusterNodes = getConnection().getClusterNodes();
		for (String k : clusterNodes.keySet()) {
			JedisPool jp = clusterNodes.get(k);
			Jedis connection = null;
			try {
				long start = System.currentTimeMillis();
				connection = jp.getResource();
				Set<String> keys = connection.keys(pattern);
				File file = new File(folder+"/"+k.replace(":", "_")+".txt");
				FileWriter writer = new FileWriter(file);
				for (String key : keys) {
					writer.write(key);
					writer.write("\r\n");
				}
				writer.close();
				long end = System.currentTimeMillis();
				System.out.println(k+"用时："+(end-start)+"ms");
			} catch (Exception e) {
				logger.error("Getting keys error: {}", e);
			} finally {
				// 用完一定要close这个链接！！！
				if(connection!=null)
					connection.close();
			}
		}
	}
	public static void delByPattern(String pattern) {
		Map<String, JedisPool> clusterNodes = getConnection().getClusterNodes();
		for (String k : clusterNodes.keySet()) {
			logger.debug("Getting keys from: {}", k);
			JedisPool jp = clusterNodes.get(k);
			Jedis connection = jp.getResource();
			try {
				Set<String> keys = connection.keys(pattern);
				for (String key : keys) {
					connection.del(key);
				}
			} catch (Exception e) {
				logger.error("Getting keys error: {}", e);
			} finally {
				logger.debug("Connection closed.");
				// 用完一定要close这个链接！！！
				connection.close();
			}
		}
	}

	public static void keysOut(TreeSet<String> dataSet) throws Exception {
		if (null != dataSet) {
			for (String str : dataSet) {
				logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
				System.out.println(str + ": " + get(str));
				logger.debug("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
			}
		}
	}

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		// set("foo","123");
		// System.out.println(get("foo"));
		// jc.hkeys(key)
		// set("KEY1", 0, "123");
		// set("KEY2", 0, "456");
		// set("KEY3", 0, "789");

//		System.out.println(keys("ZZJY-TEMP03_REG01-T01001-1-DAY"));
//
//		System.out.println(keys("ZZJY-TEMP03_REG01-T01001-1-DAY-BFDA_CHANNEL_TYPE_1"));
//
//		System.out.println(keys("ZZJY-TEMP03_REG01-T01001-1-DAY-BFDA_CHANNEL_TYPE_5"));
//
//		System.out.println(keys("ZZJY-TEMP03_REG01-T01001-1-DAY-BFDA_CHANNEL_TYPE_1,5"));
//
//		System.out.println(keys("ZZJY-TEMP03_REG01-T01001-1-MONTH"));
//
//		System.out.println(keys("ZZJY-TEMP03_REG01-T01001-1-MONTH-BFDA_CHANNEL_TYPE_1"));
//
//		System.out.println(keys("ZZJY-TEMP03_REG01-T01001-1-MONTH-BFDA_CHANNEL_TYPE_5"));
//
//		System.out.println(keys("ZZJY-TEMP03_REG01-T01001-1-MONTH-BFDA_CHANNEL_TYPE_1,5"));
//
//		System.out.println(keys("ZZJY-TEMP03_REG01-T01001-1-YEAR"));
//
//		System.out.println(keys("ZZJY-TEMP03_REG01-T01001-1-YEAR-BFDA_CHANNEL_TYPE_1"));
//
//		System.out.println(keys("ZZJY-TEMP03_REG01-T01001-1-YEAR-BFDA_CHANNEL_TYPE_5"));
//
//		System.out.println(keys("ZZJY-TEMP03_REG01-T01001-1-YEAR-BFDA_CHANNEL_TYPE_1,5"));

		// System.out.println(getConnection().);
		// Set<String> keys = getConnection().hkeys("KEY1");
		// System.out.println(keys);
		printKeys("*", "E:/temp/");
//		set("test", 0, "test");
//		set("test2", 0, "test2");
//		set("test3", 0, "test3");
	}
}
