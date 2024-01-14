package com.spring.step9;

import static org.junit.Assert.*;

import com.spring.step9.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

/**
 * @author 彭锦波
 * @project small-spring
 * @description
 * @date 2024/1/14 17:57:32
 */
public class UserServiceTest {
  // 测试原型模式
  @Test
  public void testPrototype(){
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-step9.xml");
    context.registerShutDownHook();

    UserService userService1 = (UserService) context.getBean("userService");
    UserService userService2 = (UserService) context.getBean("userService");

    System.out.println("userService1 == userService2:");
    System.out.println(userService1 == userService2);
    System.out.println("userService1 is:" + Integer.toHexString(userService1.hashCode()));
    System.out.println("userService2 is:" + Integer.toHexString(userService2.hashCode()));
  }


  @Test
  public void testUserService(){
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-step9.xml");
    context.registerShutDownHook();

    UserService userService = (UserService) context.getBean("userService");
    userService.find();
  }
}