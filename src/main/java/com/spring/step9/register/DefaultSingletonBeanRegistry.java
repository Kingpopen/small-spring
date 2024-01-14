package com.spring.step9.register;

import com.spring.step3.exception.BeanException;
import com.spring.step9.factory.DisposableBean;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 彭锦波
 * @project small-spring
 * @description 单实例bean注册的抽象类
 * @date 2023/12/10 11:04:05
 */
@Slf4j
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

  private final Map<String, Object> singletonObjects = new HashMap<>();

  // 销毁bean的适配器
  private final Map<String, DisposableBean> disposableBeanMap = new ConcurrentHashMap<>();

  // 异常以及为空判断 放在外层
  public Object getSingleton(String name) {
    return singletonObjects.get(name);
  }

  // bean实例的添加
  protected void addSingleton(String name, Object singletonObject) {
    singletonObjects.put(name, singletonObject);
  }

  // 销毁的bean的注册
  public void registerDisposableBean(String beanName, DisposableBean disposableBean){
    disposableBeanMap.put(beanName, disposableBean);
  }

  // 销毁bean的执行


  @Override
  public void destroyBeans() {
    Set<String> keySet = disposableBeanMap.keySet();
    keySet.forEach(key -> {
      DisposableBean disposableBean = disposableBeanMap.remove(key);
      try {
        disposableBean.destroy();
      } catch (NoSuchMethodException | SecurityException | IllegalArgumentException e){
        log.error("执行销毁方法出现异常！key is:{}", key, e);
        throw new BeanException("执行销毁方法异常:" +  e.getMessage());
      } catch (Exception e) {
        throw new BeanException("执行销毁方法异常:" + e.getMessage());
      }
    });
  }
}
