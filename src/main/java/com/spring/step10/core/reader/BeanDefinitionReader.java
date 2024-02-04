package com.spring.step10.core.reader;

import com.spring.step10.core.loader.ResourceLoader;
import com.spring.step10.core.resource.Resource;
import com.spring.step10.register.BeanDefinitionRegistry;

/**
 * @author 彭锦波
 * @project small-spring
 * @description BeanDefinition的读取接口
 * @date 2023/12/22 12:57:18
 */
public interface BeanDefinitionReader {
  // 获取beanDefinition的注册器
  BeanDefinitionRegistry getRegister();

  // 获取资源导入器
  ResourceLoader getResourceLoader();

  void loadBeanDefinition(String location);

  void loadBeanDefinition(Resource... resources);
  // 导入BeanDefinition
  void loadBeanDefinition(Resource resource);


}
