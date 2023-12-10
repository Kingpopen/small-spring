package com.spring.step2.factory;

import com.spring.step2.BeanDefinition;
import com.spring.step2.exception.BeanException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 彭锦波
 * @project small-spring
 * @description 工厂抽象类
 * @date 2023/12/10 11:36:18
 */
@Slf4j
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{
    // 实现创建bean的方法
    @Override
    protected Object createBean(String name, BeanDefinition beanDefinition) {
        Object bean = null;
        try {
            Class objClass = beanDefinition.getBean();
            bean = objClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new BeanException(String.format("创建bean异常:%s", e.getMessage()));
        }
        addSingleton(name, bean);
        return bean;
    }
}
