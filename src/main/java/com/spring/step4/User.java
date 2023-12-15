package com.spring.step4;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * @author 彭锦波
 * @project small-spring
 * @description 用户类
 * @date 2023/12/15 10:42:17
 */
@Data
@ToString
@AllArgsConstructor
public class User {
  private String name;
  private int age;
}
