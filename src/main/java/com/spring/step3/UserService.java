package com.spring.step3;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author 彭锦波
 * @project small-spring
 * @description 用户service
 * @date 2023/12/11 08:11:59
 */
@AllArgsConstructor
@NoArgsConstructor
public class UserService {

  private String name;

  public void find() {
    System.out.printf("find user:[%s]!%n", name);
  }
}
