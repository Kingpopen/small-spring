package com.spring.step7;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author 彭锦波
 * @project small-spring
 * @description 用户类
 * @date 2023/12/15 10:42:17
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {

  private String name;
  private int age;
}
