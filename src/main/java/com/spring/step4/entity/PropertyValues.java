package com.spring.step4.entity;

import com.spring.step4.exception.BeanException;
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

  public PropertyValue getPropertyValue(String name){
    for(PropertyValue item : propertyValueList){
      if (name.equals(item.getName())){
        return item;
      }
    }
    throw new BeanException(String.format("找不到名称为:[%s]的属性", name));
  }

  // 添加属性
  public void addPropertyValue(PropertyValue propertyValue){
    propertyValueList.add(propertyValue);
  }
}
