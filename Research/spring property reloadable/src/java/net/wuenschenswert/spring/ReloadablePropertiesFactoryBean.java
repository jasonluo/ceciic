package net.wuenschenswert.spring;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.core.io.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.io.File;

/**
 * A properties factory bean that creates a reconfigurable Properties object.
 * When the Properties' reloadConfiguration method is called, and the file has
 * changed, the properties are read again from the file.
 */
public class ReloadablePropertiesFactoryBean extends PropertiesFactoryBean implements DisposableBean {

  private Log log = LogFactory.getLog(getClass());

  // add missing getter for locations

  private Resource[] locations;
  private long[] lastModified;
  private List<ReloadablePropertiesListener> preListeners;

  public void setLocation(Resource location) {
    setLocations(new Resource[]{ location });
  }

  public void setLocations(Resource[] locations) {
    this.locations = locations;
    lastModified = new long[locations.length];
    super.setLocations(locations);
  }

  protected Resource[] getLocations() {
    return locations;
  }

  public void setListeners(List listeners) {
    // early type check, and avoid aliassing
    this.preListeners = new ArrayList<ReloadablePropertiesListener>();
    for(Object o: listeners) {
      preListeners.add((ReloadablePropertiesListener) o);
    }
  }

  private ReloadablePropertiesBase reloadableProperties;

  protected Object createInstance() throws IOException {
    // would like to uninherit from AbstractFactoryBean (but it's final!)
    if(!isSingleton())
      throw new RuntimeException("ReloadablePropertiesFactoryBean only works as singleton");
    reloadableProperties = new ReloadablePropertiesImpl();
    if(preListeners != null)
      reloadableProperties.setListeners(preListeners);
    reload(true);
    return reloadableProperties;
  }

  public void destroy() throws Exception {
    reloadableProperties = null;
  }

  protected void reload(boolean forceReload) throws IOException {
    boolean reload = forceReload;
    for (int i = 0; i < locations.length; i++) {
      Resource location = locations[i];
      File file;
      try {
        file = location.getFile();
      } catch (IOException e) {
        // not a file resource
        continue;
      }
      try {
        long l = file.lastModified();
        if(l > lastModified[i]) {
          lastModified[i] = l;
          reload = true;
        }
      } catch (Exception e) {
        // cannot access file. assume unchanged.
        if(log.isDebugEnabled())
          log.debug("can't determine modification time of "+file+" for "+location, e);
      }
    }
    if (reload)
      doReload();
  }

  private void doReload() throws IOException {
    reloadableProperties.setProperties(mergeProperties());
  }

  class ReloadablePropertiesImpl extends ReloadablePropertiesBase implements ReconfigurableBean {
    public void reloadConfiguration() throws Exception {
      ReloadablePropertiesFactoryBean.this.reload(false);
    }
  }

}
