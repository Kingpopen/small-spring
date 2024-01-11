package com.spring.step7.context.support;

import com.spring.step7.core.reader.XmlBeanDefinitionReader;
import com.spring.step7.factory.support.DefaultListableBeanFactory;
import java.util.List;
import java.util.Objects;

/**
 * @author 彭锦波
 * @project small-spring
 * @description
 * @date 2024/1/2 07:59:34
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {

  @Override
  protected void loadDefinitions(DefaultListableBeanFactory beanFactory) {
    XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader();
    reader.setRegistry(beanFactory);
    reader.setResourceLoader(this);
    List<String> configLocations = getConfigLocations();
    if (!Objects.isNull(configLocations)){
      configLocations.forEach(reader::loadBeanDefinition);
    }
  }

  protected abstract List<String> getConfigLocations();
}
