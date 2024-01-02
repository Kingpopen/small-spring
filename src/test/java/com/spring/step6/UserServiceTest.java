package com.spring.step6;

import com.spring.step6.context.support.ClassPathXmlApplicationContext;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * @author 彭锦波
 * @project small-spring
 * @description
 * @date 2024/1/2 19:53:54
 */
public class UserServiceTest extends TestCase {
  @Test
  public void testUserService(){
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring.xml");
    UserService userService = (UserService) context.getBean("userService");
    userService.find();
  }
}