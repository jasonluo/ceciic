==========need servlet 3.0 (tomcat 7 or glassfish) and java 1.6 and over =================
if "run out of memeory" -XX:PermSize=64m -XX:MaxPermSize=128m for Tomcat jdk start


//Spring context annotation
http://stackoverflow.com/questions/6276561/spring-how-annotationconfigwebapplicationcontext-could-not-overwrite-later-bean
http://www.coderanch.com/t/516087/Spring/Super-Simple-Servlet-API-Based
http://www.theserverside.com/tutorial/How-to-Use-Spring-30-In-a-Servlet-Based-Web-Application


//servlet 3.0 annotation
https://blogs.oracle.com/swchan/entry/servlet_3_0_annotations
http://explodingjava.blogspot.com/2010/05/servlet-30-annotations.html


/**
 * http://www.mkyong.com/spring/spring-auto-scanning-components/
 * http://blogs.sourceallies.com/2011/08/spring-injection-with-resource-and-autowired/
 * 
 * use @Service, @Repository, @Component, or @Controller tag for auto scan and create proxy
 * @Component("beanName") to explicitly name the bean
 * 
 * use @Resource, @Autowired or @Inject to perform auto dependency injection
 * @Resource(name="beanName") can explicitly refer a bean dependency
 * */