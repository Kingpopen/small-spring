package com.spring.step8;

import static org.junit.Assert.*;

import com.spring.step8.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

/**
 * @author 彭锦波
 * @project small-spring
 * @description
 * @date 2024/1/14 15:17:08
 */
public class UserServiceTest {
  @Test
  public void testUserService(){
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-step8.xml");
    context.registerShutDownHook();

    UserService userService = (UserService)context.getBean("userService");
    userService.find();
  }
}