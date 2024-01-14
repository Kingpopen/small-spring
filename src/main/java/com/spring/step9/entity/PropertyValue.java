package com.spring.step9.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author 彭锦波
 * @project small-spring
 * @description 属性类
 * @date 2023/12/14 18:43:30
 */
@Data
@AllArgsConstructor
public class PropertyValue {

  private String name;
  private Object val;
}
