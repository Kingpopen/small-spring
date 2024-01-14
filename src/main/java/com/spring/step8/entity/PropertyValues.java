package com.spring.step8.entity;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 * @author 彭锦波
 * @project small-spring
 * @description 属性包装类
 * @date 2023/12/14 18:45:21
 */
@Data
public class PropertyValues {

  private List<PropertyValue> propertyValueList = new ArrayList<>();

  // 添加属性
  public void addPropertyValue(PropertyValue propertyValue) {
    propertyValueList.add(propertyValue);
  }
}
