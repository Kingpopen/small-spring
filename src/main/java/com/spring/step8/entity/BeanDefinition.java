package com.spring.step8.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 彭锦波
 * @project small-spring
 * @description
 * @date 2023/12/10 10:27:32
 */
@Data
@NoArgsConstructor
public class BeanDefinition {

  private Class<?> bean;
  private PropertyValues propertyValues;

  // 初始化方法的名称
  private String initMethodName;
  // 销毁方法的名称
  private String destroyMethodName;

  public BeanDefinition(Class<?> bean) {
    this.bean = bean;
    this.propertyValues = new PropertyValues();
  }

  public BeanDefinition(Class<?> bean, PropertyValues propertyValues) {
    this.bean = bean;
    this.propertyValues = propertyValues != null ? propertyValues : new PropertyValues();
  }
}
