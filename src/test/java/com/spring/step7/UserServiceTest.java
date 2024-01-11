package com.spring.step7;

import static org.junit.Assert.*;

import com.spring.step7.context.ConfigurableApplicationContext;
import com.spring.step7.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

/**
 * @author 彭锦波
 * @project small-spring
 * @description
 * @date 2024/1/11 10:52:37
 */
public class UserServiceTest {
  @Test
  public void testUserService(){
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-step7.xml");
    context.registerShutDownHook();

    UserService userService = (UserService)context.getBean("userService");
    userService.find();
  }
}