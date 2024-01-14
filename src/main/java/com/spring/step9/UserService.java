package com.spring.step9;

import com.spring.step9.aware.ApplicationContextAware;
import com.spring.step9.aware.BeanClassLoaderAware;
import com.spring.step9.aware.BeanFactoryAware;
import com.spring.step9.aware.BeanNameAware;
import com.spring.step9.context.ApplicationContext;
import com.spring.step9.factory.BeanFactory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 彭锦波
 * @project small-spring
 * @description 用户service
 * @date 2023/12/11 08:11:59
 */
@Slf4j
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserService implements BeanFactoryAware, BeanNameAware, BeanClassLoaderAware,
    ApplicationContextAware {

  // jdk 自带类
  private String id;
  // jdk 基础类型
  private int age;
  // jdk 基础类型
  private String identity;
  // 自定义的类
  private User friend;
  // 提前交给容器管理的 bean
  private IUserDao userDao;


  public void find() {
    String name = userDao.find(id);
    System.out.printf("find user:[%s]!\n", name);
    System.out.printf("%s age is:%d\n", name, age);
    System.out.printf("%s identity is:%s\n", name, identity);
    System.out.printf("%s`s friend is %s\n", name, friend.toString());
  }


  @Override
  public void setApplicationContext(ApplicationContext applicationContext) {
    log.info("applicationContext is:{}", applicationContext);
  }

  @Override
  public void setClassLoader(ClassLoader classLoader) {
    log.info("classLoader is:{}", classLoader);
  }

  @Override
  public void setBeanFactory(BeanFactory beanFactory) {
    log.info("beanFactory is:{}", beanFactory);
  }

  @Override
  public void setBeanName(String beanName) {
    log.info("beanName is:{}", beanName);
  }
}
