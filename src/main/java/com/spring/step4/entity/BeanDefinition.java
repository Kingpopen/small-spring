package com.spring.step4.entity;

import lombok.AllArgsConstructor;
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

  private Class bean;
  private PropertyValues propertyValues;

  public BeanDefinition(Class bean){
    this.bean = bean;
    this.propertyValues = new PropertyValues();
  }

  public BeanDefinition(Class bean, PropertyValues propertyValues){
    this.bean = bean;
    this.propertyValues = propertyValues != null ? propertyValues : new PropertyValues();
  }
}
