package com.spring.step10.entity;

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

  public static final String SCOPE_SINGLETON = "singleton";
  public static final String SCOPE_PROTOTYPE = "prototype";

  private Class<?> bean;
  private PropertyValues propertyValues;

  // 初始化方法的名称
  private String initMethodName;
  // 销毁方法的名称
  private String destroyMethodName;

  private String scope = SCOPE_SINGLETON;
  private boolean singleton = true;
  private boolean prototype = false;

  public BeanDefinition(Class<?> bean) {
    this.bean = bean;
    this.propertyValues = new PropertyValues();
  }

  public BeanDefinition(Class<?> bean, PropertyValues propertyValues) {
    this.bean = bean;
    this.propertyValues = propertyValues != null ? propertyValues : new PropertyValues();
  }

  public void setScope(String scope){
    this.scope = scope;
    this.singleton = SCOPE_SINGLETON.equals(scope);
    this.prototype = SCOPE_PROTOTYPE.equals(scope);
  }
}
