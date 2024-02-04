package com.spring.step10.context.event;

import com.spring.step10.context.ApplicationEvent;
import com.spring.step10.context.ApplicationListener;
import com.spring.step10.utils.ClassUtils;
import com.spring.step10.exception.BeanException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 彭锦波
 * @project small-spring
 * @description ApplicationEventMulticaster的抽象类 抽象类对接口进行了基础的实现。
 * @date 2024/01/31 23:00:30
 */
@Slf4j
public abstract class AbstractApplicationEventMulticaster implements ApplicationEventMulticaster {

  private Set<ApplicationListener<ApplicationEvent>> listeners = new HashSet<>();

  @Override
  public void addListener(ApplicationListener<?> listener) {
    listeners.add((ApplicationListener<ApplicationEvent>) listener);
  }

  @Override
  public void removeListener(ApplicationListener<?> listener) {
    listeners.remove(listener);
  }

  // 获取某个事件所有的监听器
  protected Collection<ApplicationListener> getApplicationListeners(ApplicationEvent event) {
    if (Objects.isNull(event)) {
      return Collections.emptyList();
    }
    return listeners.stream().filter(listener -> isMatch(listener, event))
        .collect(Collectors.toList());
  }

  // 判断事件 和 监听器 是否匹配
  protected boolean isMatch(ApplicationListener<ApplicationEvent> listener,
      ApplicationEvent event) {
    // TODO: 2024/01/31
    /*
        获取 listener 的类对象
        如果是 代理类 就获取父类的 类对象
        如果不是代理类 就获取该类本身的类对象
     */
    Class<? extends ApplicationListener> listenerClazz = listener.getClass();
    Class<?> targetClazz =
        ClassUtils.isCglibProxyClass(listenerClazz) ? listenerClazz.getSuperclass() : listenerClazz;

    /*
        获取类对象的函数  onApplication(T event), 判断其中的 T类型 是否 和 本函数传入的event类型相同
        1. 先获取类中的函数 (onApplication)
        2. 再获取函数中的入参类型 T
     */
    Type genericInterface = targetClazz.getGenericInterfaces()[0];
    Type actualTypeArgument = ((ParameterizedType) genericInterface).getActualTypeArguments()[0];
    String typeName = actualTypeArgument.getTypeName();
    Class<?> eventClazz;
    try {
      eventClazz = Class.forName(typeName);
    } catch (ClassNotFoundException e) {
      log.error("获取类对象异常：获取的类对象不存在! 参数为:{}", typeName, e);
      throw new BeanException("获取类对象异常：获取的类对象不存在!");
    }
    return eventClazz.isAssignableFrom(event.getClass());
  }

}
