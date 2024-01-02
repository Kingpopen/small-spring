package com.spring.step5.strategy;

import com.spring.step5.entity.BeanDefinition;
import java.lang.reflect.Constructor;
import java.util.Objects;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

/**
 * @author 彭锦波
 * @project small-spring
 * @description cglib的策略创建实例
 * @date 2023/12/10 20:40:36
 */
public class CglibStrategy implements InstantiationStrategy {

  @Override
  public Object instantiate(BeanDefinition beanDefinition, Constructor<?> ctor, Object[] args) {
    Enhancer enhancer = new Enhancer();
    enhancer.setSuperclass(beanDefinition.getBean());
    enhancer.setCallback(new NoOp() {
      @Override
      public int hashCode() {
        return super.hashCode();
      }
    });
    if (Objects.isNull(ctor)) {
      return enhancer.create();
    }
    return enhancer.create(ctor.getParameterTypes(), args);
  }
}
