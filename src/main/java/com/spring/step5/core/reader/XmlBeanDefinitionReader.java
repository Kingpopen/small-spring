package com.spring.step5.core.reader;

import cn.hutool.core.util.XmlUtil;
import com.spring.step5.core.resource.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/**
 * @author 彭锦波
 * @project small-spring
 * @description xml的读取器
 * @date 2023/12/22 13:05:48
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

  @Override
  public void loadBeanDefinition(String location) {
    Resource resource = getResourceLoader().getResource(location);
    loadBeanDefinition(resource);
  }

  @Override
  public void loadBeanDefinition(Resource... resources) {
    Arrays.stream(resources).forEach(this::loadBeanDefinition);
  }

  /**
   * 着重 需要实现的方法
   *
   * @param resource
   */
  @Override
  public void loadBeanDefinition(Resource resource) {
    try {
      XmlUtil.readXML(resource.getInputStream());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private void doLoadBeanDefinition(InputStream inputStream) {
    // Todo
  }

}
