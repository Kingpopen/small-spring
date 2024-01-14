package com.spring.step9.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author 彭锦波
 * @project small-spring
 * @description bean对象引用类
 * @date 2023/12/14 18:42:34
 */
@Data
@AllArgsConstructor
public class BeanReference {

  // 因为实例是交给容器进行管理 所以这个地方只需要存储bean的名称
  private String name;
}
