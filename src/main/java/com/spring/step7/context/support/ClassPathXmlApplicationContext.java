package com.spring.step7.context.support;

import java.util.Arrays;
import java.util.List;

/**
 * @author 彭锦波
 * @project small-spring
 * @description
 * @date 2024/1/2 08:07:29
 */
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext {

  private List<String> configLocations;

  public ClassPathXmlApplicationContext() {
  }

  public ClassPathXmlApplicationContext(List<String> configLocations) {
    this.configLocations = configLocations;
    refresh();
  }

  public ClassPathXmlApplicationContext(String configLocation) {
    this(Arrays.asList(configLocation));
  }

  @Override
  protected List<String> getConfigLocations() {
    return configLocations;
  }
}
