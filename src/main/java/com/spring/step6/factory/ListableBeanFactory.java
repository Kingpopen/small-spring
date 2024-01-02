package com.spring.step6.factory;

import com.spring.step4.entity.BeanDefinition;
import java.util.List;
import java.util.Map;

/**
 * @author 彭锦波
 * @project small-spring
 * @description BeanFactory 工厂
 * @date 2023/12/30 20:32:15
 */
public interface ListableBeanFactory extends BeanFactory{
  // 获取指定类型的bean
  <T> Map<String, T> getBeanOfType(Class<T> type);

  // 获取所有的BeanDefinition
  List<String> getAllDefinitionNames();
}
