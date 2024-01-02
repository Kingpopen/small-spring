package com.spring.step6.postprocessor.support;

import com.spring.step6.entity.BeanDefinition;
import com.spring.step6.entity.PropertyValue;
import com.spring.step6.entity.PropertyValues;
import com.spring.step6.factory.ConfigurableListableBeanFactory;
import com.spring.step6.postprocessor.BeanFactoryPostProcessor;

/**
 * @author 彭锦波
 * @project small-spring
 * @description 自定义的BeanFactoryPostProcessor
 * @date 2024/1/2 19:41:16
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

  @Override
  public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
    BeanDefinition userService = beanFactory.getBeanDefinition("userService");
    PropertyValues propertyValues = userService.getPropertyValues();

    propertyValues.addPropertyValue(new PropertyValue("identity", "经理"));
  }
}
