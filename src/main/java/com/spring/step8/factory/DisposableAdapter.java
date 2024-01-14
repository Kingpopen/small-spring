package com.spring.step8.factory;

import cn.hutool.core.util.StrUtil;
import com.spring.step8.entity.BeanDefinition;
import com.spring.step8.exception.BeanException;
import java.lang.reflect.Method;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 彭锦波
 * @project small-spring
 * @description 销毁方法的 适配器
 * @date 2024/1/11 09:16:37
 */
@Slf4j
@AllArgsConstructor
public class DisposableAdapter implements DisposableBean {
  private Object bean;
  private BeanDefinition beanDefinition;

  @Override
  public void destroy() throws Exception{
    if (bean instanceof DisposableBean){
      ((DisposableBean)bean).destroy();
      return;
    }

    if (!Objects.isNull(bean) && !StrUtil.isEmpty(beanDefinition.getDestroyMethodName())){
      Class<?> clazz = bean.getClass();
      Method method = clazz.getMethod(beanDefinition.getDestroyMethodName());
      method.invoke(bean);
      return;
    }
    log.error("执行destroy方法异常，bean is:{}, method is:{}", bean, beanDefinition.getDestroyMethodName());
    throw new BeanException("执行destroy方法异常！");
  }
}
