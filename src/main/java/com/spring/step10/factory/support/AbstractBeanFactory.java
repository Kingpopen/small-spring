package com.spring.step10.factory.support;

import com.spring.step10.entity.BeanDefinition;
import com.spring.step10.entity.FactoryBean;
import com.spring.step10.factory.ConfigurableBeanFactory;
import com.spring.step10.postprocessor.BeanPostProcessor;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author 彭锦波
 * @project small-spring
 * @description Bean工厂的抽象类 提取出共有的内容
 * @date 2023/12/10 10:30:58
 */
// FactoryBeanRegistrySupport 在 DefaultSingletonBeanRegistry上添加了一些操作
public abstract class AbstractBeanFactory extends FactoryBeanRegistrySupport implements
    ConfigurableBeanFactory {

  private final List<BeanPostProcessor>  beanPostProcessors = new ArrayList<>();

  // 获取beanDefinition
  protected abstract BeanDefinition getBeanDefinition(String name);

  protected abstract Object createBean(String name, BeanDefinition beanDefinition, Object[] args);

  // 实现获取bean的方法 定义了获取bean的流程： 模版方法 的设计模式
  public Object getBean(String name) {
    return doGetBean(name, null);
  }

  @Override
  public Object getBean(String name, Object[] args) {
    return doGetBean(name, args);
  }


  // 抽象出一个共有的方法
  protected <T> T doGetBean(String name, Object[] args) {
    Object singleton = getSingleton(name);
    if (!Objects.isNull(singleton)) {
      return (T) getObjectForBeanInstance(singleton, name);
    }

    BeanDefinition beanDefinition = getBeanDefinition(name);
    Object bean = createBean(name, beanDefinition, args);
    return (T) getObjectForBeanInstance(bean, name);
  }

  private Object getObjectForBeanInstance(Object bean, String name){
    if (!(bean instanceof FactoryBean)) return bean;

    // 如果该bean 是FactoryBean 对象 就需要 通过 该FactoryBean来获取具体的bean了
    FactoryBean<?> factoryBean = (FactoryBean<?>) bean;
    return getFactoryBean(factoryBean, name);
  }

  @Override
  public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
    beanPostProcessors.remove(beanPostProcessor);
    beanPostProcessors.add(beanPostProcessor);
  }

  @Override
  public List<BeanPostProcessor> getBeanPostProcessors() {
    return beanPostProcessors;
  }
}
