package com.spring.step2;

import com.spring.step2.factory.BeanFactory;
import com.spring.step2.factory.DefaultListableBeanFactory;
import com.sun.xml.internal.fastinfoset.algorithm.BooleanEncodingAlgorithm;

/**
 * @author 彭锦波
 * @project small-spring
 * @description 启动类
 * @date 2023/12/10 12:07:32
 */
public class app {
    public static void main(String[] args) {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        BeanDefinition definition = new BeanDefinition(UserService.class);
        factory.registerBeanDefinition("userService", definition);
        UserService userService = (UserService) factory.getBean("userService");
        userService.find();
    }
}
