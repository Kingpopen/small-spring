package com.spring.step10;

import static org.junit.Assert.*;

import com.spring.step10.context.support.ClassPathXmlApplicationContext;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author 彭锦波
 * @project small-spring
 * @description
 * @date 2024/02/04 23:17:14
 */
public class LearnListenerTest {
  @Test
  public void testListener(){
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-step10.xml");
    LearnEvent event = new LearnEvent(context, "篮球", 2);
    context.publish(event);

    context.registerShutDownHook();
  }
}