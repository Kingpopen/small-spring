package com.spring.step2.factory;

import com.spring.step2.BeanDefinition;
import com.spring.step2.UserService;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * @author 彭锦波
 * @project small-spring
 * @description
 * @date 2023/12/10 12:33:41
 */
public class DefaultListableBeanFactoryTest{
    @Test
    public void testDefaultListableBeanFactory(){
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        BeanDefinition definition = new BeanDefinition(UserService.class);
        factory.registerBeanDefinition("userService", definition);
        UserService userService = (UserService) factory.getBean("userService");
        userService.find();
    }
}