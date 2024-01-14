package com.spring.step9.factory.support;

import com.spring.step9.entity.BeanDefinition;
import com.spring.step9.exception.BeanException;
import com.spring.step9.factory.ConfigurableListableBeanFactory;
import com.spring.step9.register.BeanDefinitionRegistry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author 彭锦波
 * @project small-spring
 * @description bean工厂 额外实现了BeanDefinition的管理
 * @date 2023/12/10 11:55:26
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements
    BeanDefinitionRegistry, ConfigurableListableBeanFactory {

  Map<String, BeanDefinition> map = new HashMap<>();

  @Override
  public BeanDefinition getBeanDefinition(String name) {
    BeanDefinition beanDefinition = map.get(name);
    if (Objects.isNull(beanDefinition)) {
      throw new BeanException(String.format("你还未注册该beanDefinition:[%s]", name));
    }
    return beanDefinition;
  }

  @Override
  public void preInstantiateSingletons() {
    map.keySet().forEach(this::getBean);
  }

  // 接口满足了单一职责原则 需要实现什么功能 就实现什么接口
  @Override
  public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
    map.put(name, beanDefinition);
  }

  @Override
  public boolean containBeanDefinition(String name) {
    return map.containsKey(name);
  }

  @Override
  public <T> Map<String, T> getBeanOfType(Class<T> type) {
    Map<String, T> res = new HashMap<>();
    map.forEach((beanName, beanDefinition) -> {
      Class<?> bean = beanDefinition.getBean();
      if (type.isAssignableFrom(bean)) {
        res.put(beanName, (T) getBean(beanName));
      }
    });
    return res;
  }

  @Override
  public List<String> getAllDefinitionNames() {
    return new ArrayList<>(map.keySet());
  }
}
