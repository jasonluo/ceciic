============ Use Ehcache Directly==============
CacheManager.newInstance(this.getClass().getResource("/META-INF/cache/sample-ehcache.xml"));
also see EhCacheSampleServiceImpl.java
ehcache.xsd and sample-ehcache.xml files both reside in META-INF/cache folder

============ Use Ehcache through Hibernate==============

1. in .hbm.xml file [this is object cache. only works when retrieve object by get() or load()]
   <hibernate-mapping>
     <class   name="ooolong.User"  table="xyz"  lazy="false">
     <cache usage="read-only"/>
	.....
  </hibernate-mapping>

   Define cache in each mapping file is NOT a good idea.  should extract this info to a global file. e.g. @see hibernate.cfg.xml 

2. in Spring config file
   <bean id="abstractFafsaSessionFactory" class="org.springframework.orm.hibernate3.LocalSessionFactoryBean" abstract="true">
      	<property name="dataSource">
      		<ref bean="dataSource"/>
      	</property>
        <property name="hibernateProperties">
            <props>
                <prop key="hibernate.dialect">org.hibernate.dialect.Oracle10gDialect</prop>
            	<prop key="hibernate.show_sql">false</prop>
            	<prop key="hibernate.cache.region.factory_class">net.sf.ehcache.hibernate.SingletonEhCacheRegionFactory</prop>
				<prop key="hibernate.cache.use_second_level_cache">true</prop>
				<!--- this caches query result. e.g whatever returned from calling find();
				<prop key="hibernate.cache.use_query_cache">true</prop>	
				<prop key="hibernate.generate_statistics">true</prop>
				<!---- path to ehcache config file ---->
				<prop key="net.sf.ehcache.configurationResourceName">/META-INF/cache/sample-ehcache.xml</prop>
            </props>
        </property>
    </bean>

3.  in DAO method:    List result = session.createQuery(query).setCacheable(true)
	                                                                   .setCacheRegion("sampleCache")
	                                                                   .list();
=========================== Ehcache Monitoring =================
http://www.ehcache.org/documentation/user-guide/monitor 
  1.  need slf4j-api-1.6.1.jar and slf4j-jdk14-1.6.1.jar or later version (1.5.5 not working)
  2. Add the ehcache-probe-.jar to your application classpath (or war file).
  3. adding the following to ehcache.xml

     <cacheManagerPeerListenerFactory  class="org.terracotta.ehcachedx.monitor.probe.ProbePeerListenerFactory"
            properties="monitorAddress=localhost, monitorPort=9889, memoryMeasurement=true" />

  4. goto C:\bit9prog\dev\EhcacheMonitor\ehcache-monitor-kit-1.0.3\bin and click startup.bat




