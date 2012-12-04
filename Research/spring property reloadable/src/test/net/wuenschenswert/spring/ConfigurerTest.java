package net.wuenschenswert.spring;

import junit.framework.TestCase;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Properties;

/**
 * test the ReloadingPropertyPlaceholderConfigurer
 */
public class ConfigurerTest extends TestCase {
  ClassPathXmlApplicationContext applicationContext;
  private TestBean guineePig;
  private TestReloadableProperties properties;

  protected void setUp() throws Exception {
    applicationContext = new ClassPathXmlApplicationContext("net/wuenschenswert/spring/test1.xml");
    properties = (TestReloadableProperties) applicationContext.getBean("properties");
    guineePig = (TestBean) applicationContext.getBean("guineePig");
  }

  public void testConfigured() {
    assertEquals("fooval", guineePig.getPropA());
    assertEquals("barval", guineePig.getPropB());
  }

  public void testReconfigured() {
    Properties newProps = new Properties();
    String newFooVal = "newFooVal";
    String newBarVal = "newBarVal";
    newProps.put("foo", newFooVal);
    newProps.put("bar", newBarVal);
    properties.setProperties(newProps);
    assertEquals(newFooVal, guineePig.getPropA());
    assertEquals(newBarVal, guineePig.getPropB());
  }

  // more tests:
  // default values (reappearing when property disappears)
  // ReconfigurationAware beans
}
