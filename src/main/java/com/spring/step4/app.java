package com.spring.step4;

import com.spring.step4.entity.BeanDefinition;
import com.spring.step4.entity.BeanReference;
import com.spring.step4.entity.PropertyValue;
import com.spring.step4.entity.PropertyValues;
import com.spring.step4.factory.DefaultListableBeanFactory;

/**
 * @author 彭锦波
 * @project small-spring
 * @description step4的启动类
 * @date 2023/12/11 08:11:21
 */
public class app {

  public static void main(String[] args) {
    // 1. 创建工厂
    DefaultListableBeanFactory factory = new DefaultListableBeanFactory();

    // 2. 创建BeanDefinition 并 进行注册，有两个bean，一个userService 一个userDao
    // 这个userDao 是bean实例的名称
    BeanReference beanReference = new BeanReference("userDao");
    PropertyValues propertyValues = new PropertyValues();
    // 这个userDao 是属性的名称
    PropertyValue userDaoProperty = new PropertyValue("userDao", beanReference);
    PropertyValue idProperty = new PropertyValue("id", "2");
    PropertyValue ageProperty = new PropertyValue("age", 25);
    PropertyValue userProperty = new PropertyValue("friend", new User("kingpopen", 26));
    propertyValues.addPropertyValue(userDaoProperty);
    propertyValues.addPropertyValue(idProperty);
    propertyValues.addPropertyValue(ageProperty);
    propertyValues.addPropertyValue(userProperty);

    BeanDefinition userServiceBd = new BeanDefinition(UserService.class);
    userServiceBd.setPropertyValues(propertyValues);
    factory.registerBeanDefinition("userService", userServiceBd);
    BeanDefinition userDaoBd = new BeanDefinition(UserDao.class);
    factory.registerBeanDefinition("userDao", userDaoBd);

    // 3. 进行实例的获取
    UserService userService = (UserService) factory.getBean("userService");
    userService.find();

  }
}
