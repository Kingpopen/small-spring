package com.spring.step3;
import com.spring.step3.factory.DefaultListableBeanFactory;

/**
 * @author 彭锦波
 * @project small-spring
 * @description step3的启动类
 * @date 2023/12/11 08:11:21
 */
public class app {

  public static void main(String[] args) {
    // 1. 创建工厂
    // Todo 这里的BeanFactory的接口中为什么没有 registerBeanDefinition的方法呢？ 这样就可以使用多态
    DefaultListableBeanFactory factory = new DefaultListableBeanFactory();

    // 2. 创建BeanDefinition 并 进行注册
    BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
    factory.registerBeanDefinition("userService", beanDefinition);

    // 3. 进行实例的获取
    String[] strs = {"mike"};
    UserService userService = (UserService) factory.getBean("userService", strs);
    userService.find();
  }
}
