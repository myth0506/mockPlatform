package com.mockCommon.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CacheUtil {
	public static final String BUSI_INSURE_BAOJIA = "businessInsure";
	private static final Map<String, Object> cache = new HashMap<String, Object>();

	public static void put(String key, Object value){
		cache.put(key, value);
	}
	
	public static Object get(String key){
		return cache.get(key);
	}
	
	public static void clear(){
		cache.clear();;
	}
	
	public static void remove(String key){
		cache.remove(key);
	}
	//处理商业保险配置的缓存
	private static final Map<String, Object> busiInsureCache = new HashMap<String, Object>();
	public static void putBusiInsureCache(String key, Object value){
		busiInsureCache.put(key, value);
	}
	public static Object getBusiInsureCache(String key){
		return busiInsureCache.get(key);
	}
	
	public static void clearBusiInsureCache(){
		busiInsureCache.clear();
	}
	
	public static void removeBusiInsureCache(String key){
		busiInsureCache.remove(key);
	}
	
	public static Set<String> getBusiInsureCacheKeys(){
		return busiInsureCache.keySet();
	}
}