package com.spring.step5;

import com.spring.step5.core.loader.DefaultResourceLoader;
import com.spring.step5.core.reader.XmlBeanDefinitionReader;
import com.spring.step5.factory.DefaultListableBeanFactory;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * @author 彭锦波
 * @project small-spring
 * @description
 * @date 2023/12/29 23:37:02
 */
public class UserServiceTest extends TestCase {
  @Test
  public void testUserService(){
    String filePath = "classpath:spring.xml";
    // bean工厂
    DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
    // 加载器
    DefaultResourceLoader loader = new DefaultResourceLoader();

    XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader();
    reader.setRegistry(beanFactory);
    reader.setResourceLoader(loader);

    reader.loadBeanDefinition(filePath);

    UserService userService = (UserService) beanFactory.getBean("userService");
    userService.find();
  }
}