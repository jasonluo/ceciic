package com.oolong;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;



/**
 *
 *
 *
 *
 *
 */
public class EhCacheSampleServiceImpl implements InitializingBean
{
	private static final long serialVersionUID = 18L;
	//Logger
	private static	Logger log =Logger.getLogger(EhCacheSampleServiceImpl.class);
	//ehcache manager
	private CacheManager cacheManager;
    //SampleCache name.  This name must match the cache name defined in sample-ehcache.xml.
	private String sampleCacheName;
	//Cache Configuration file path
	private String cachConfigFilePath ="/META-INF/cache/sample-ehcache.xml";

	//CodeByTypeTable key
	public static final String Cache_KEY_1 ="Cache_KEY_1";
	//CodeByIdTable key
	public static final String Cache_KEY_2 = "Cache_KEY_2";

	/**
	 * Default constructor
	 */
	public EhCacheSampleServiceImpl(){

	}

	/**
	 *
	 * @param codeCacheName
	 * @param cachConfigFilePath
	 */
	public EhCacheSampleServiceImpl(String sampleCacheName, String cachConfigFilePath){
		this.sampleCacheName = sampleCacheName;
		this.cachConfigFilePath = cachConfigFilePath;
	}


	/**
	 * This method refreshes the cache with the latest data from Code table.
	 * @param codes
	 */
	private synchronized void refreshCache()
	{
		Map data1 = new HashMap();
		Map data2 = new HashMap();
        getCodeCache().evictExpiredElements();
        getCodeCache().put(new Element(Cache_KEY_1, data1));
		getCodeCache().put(new Element(Cache_KEY_2, data2));

		log.debug("EhCacheCodeServiceImpl.refreshCache ends...................");
    }


	@SuppressWarnings("unchecked")
	@Override
	public Map get1() {
		Element element = getSampleCache().get(Cache_KEY_1);
		if(element==null || element.isExpired()){
			log.debug("element expired,  refill...........");
			reload();
		}
		return (Map)getSampleCache().get(Cache_KEY_1).getValue();
	}


	@SuppressWarnings("unchecked")
	@Override
	public Map get2() {
		Element element = getSampleCache().get(Cache_KEY_2);
		if(element==null || element.isExpired()){
			log.debug("CodeById Table expired,  refill...........");
			reload();
		}

		return  (Map)getSampleCache().get(Cache_KEY_2).getValue();
	}



	/**
	 *
	 * @param key - cache name
	 * @return the cache referred by key
	 */
	public Cache getCache(String key){
		return cacheManager.getCache(key);
	}

	/**
	 *
	 * @return the cache stores Code table data
	 */
	private Cache getSampleCache(){
		return cacheManager.getCache(getSampleCacheName());
	}

	/**
	 *
	 * @return the name of cache for Code table
	 */
	public String getSampleCacheName() {
		return this.sampleCacheName;
	}

	/**
	 *
	 * @return cache configuration file path
	 */
	public String getCachConfigFilePath() {
		return cachConfigFilePath;
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		if(this.cachConfigFilePath == null){
			this.sampleCacheName = "sampleCache";
			log.debug("configured default cacheManager. codeCache name==="+this.getSampleCacheName());
			this.cacheManager = CacheManager.newInstance(this.getClass().getResource("/META-INF/cache/sample-ehcache.xml"));
			log.debug("cacheManager=="+this.cacheManager);
		}
		else{
			Assert.notNull(this.getCachConfigFilePath(), "Must specify cache configuration file path for SampleService");
			Assert.notNull(this.getSampleCacheName(), "Must specify sample cache name for SampleService.");
			log.debug("configured cacheManager using "+this.getCachConfigFilePath()+"; codeCache name==="+this.getSampleCacheName());
			this.cacheManager = CacheManager.newInstance(this.getClass().getResource(this.getCachConfigFilePath()));
			log.debug("cacheManager=="+this.cacheManager);
		}
	}
}