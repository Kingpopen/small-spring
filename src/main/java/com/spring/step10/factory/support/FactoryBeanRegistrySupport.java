package com.spring.step10.factory.support;

import com.spring.step10.entity.FactoryBean;
import com.spring.step10.register.DefaultSingletonBeanRegistry;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 彭锦波
 * @project small-spring
 * @description FactoryBean的处理容器类
 * @date 2024/1/14 17:23:31
 */
public class FactoryBeanRegistrySupport extends DefaultSingletonBeanRegistry {
  // factoryBean的map，如果时单例的factorybean 就放在在这个map中
  private Map<String, Object> factoryBeanMap = new ConcurrentHashMap<>();

  // 获取factoryBean的方法
  protected Object getFactoryBean(FactoryBean factory, String name){
    if (factory.isSingleton()){
      Object bean = factoryBeanMap.get(name);
      if (Objects.isNull(bean)){
        bean = factory.getObject();
        factoryBeanMap.put(name, bean);
      }
      return bean;
    } else{
      // 不是单例的 直接调用本身的创建object的方法
      return factory.getObject();
    }
  }
}
