package com.spring.step4;

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
  // jdk 自带类
  private String id;
  // jdk 基础类型
  private int age;
  // 自定义的类
  private User friend;
  // 提前交给容器管理的 bean
  private UserDao userDao;


  public void find() {
    String name = userDao.find(id);
    System.out.printf("find user:[%s]!\n", name);
    System.out.printf("%s age is:%d\n", name, age);
    System.out.printf("%s`s friend is %s\n", name, friend.toString());
  }
}
