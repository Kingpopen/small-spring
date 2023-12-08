package com.spring.step1;

import org.junit.Test;

/**
 * @author 彭锦波
 * @project small-spring
 * @description
 * @date 2023/12/7 21:08:13
 */

public class BeanFactoryTest {

    @Test
    public void testBeanFactory(){
        // 1. 生成bean工厂
        BeanFactory beanFactory = new BeanFactory();

        // 2. 定义bean 注册bean
        BeanDefinition beanDefinition = new BeanDefinition(new UserServiceTest());
        beanFactory.registerBean("userService", beanDefinition);

        // 3. 使用bean
        UserServiceTest userService = (UserServiceTest) beanFactory.getBean("userService");
        userService.find();
    }
}