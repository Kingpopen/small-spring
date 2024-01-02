package com.spring.step3.factory;

import com.spring.step3.BeanDefinition;
import com.spring.step3.exception.BeanException;
import com.spring.step3.register.BeanDefinitionRegistry;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author 彭锦波
 * @project small-spring
 * @description bean工厂 额外实现了BeanDefinition的管理
 * @date 2023/12/10 11:55:26
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements
    BeanDefinitionRegistry {

  Map<String, BeanDefinition> map = new HashMap<>();

  @Override
  protected BeanDefinition getBeanDefinition(String name) {
    BeanDefinition beanDefinition = map.get(name);
    if (Objects.isNull(beanDefinition)) {
      throw new BeanException(String.format("你还未注册该beanDefinition:[%s]", name));
    }
    return beanDefinition;
  }

  // 接口满足了单一职责原则 需要实现什么功能 就实现什么接口
  @Override
  public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
    map.put(name, beanDefinition);
  }
}
