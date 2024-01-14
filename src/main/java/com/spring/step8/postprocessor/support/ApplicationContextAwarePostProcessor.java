package com.spring.step8.postprocessor.support;

import com.spring.step8.context.ApplicationContext;
import com.spring.step8.postprocessor.BeanPostProcessor;
import com.spring.step8.aware.ApplicationContextAware;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author 彭锦波
 * @project small-spring
 * @description ApplicationContextAware的后置处理器
 * @date 2024/1/14 15:08:57
 */
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationContextAwarePostProcessor implements BeanPostProcessor {

  // 添加ApplicationContext的属性 在refresh的时候就提前传递给postprocessor
  ApplicationContext context;

  @Override
  public Object postProcessorBeforeInitialize(String beanName, Object bean) {
    if (bean instanceof ApplicationContextAware){
      ((ApplicationContextAware) bean).setApplicationContext(context);
    }
    return bean;
  }

  @Override
  public Object postProcessorAfterInitialize(String beanName, Object bean) {
    return bean;
  }
}
