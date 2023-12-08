package com.spring.step1;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 彭锦波
 * @project small-spring
 * @description
 * @date 2023/12/7 21:00:55
 */
public class BeanFactory {
    // 存放bean
    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();

    // 获取bean
    public Object getBean(String name){
        return beanDefinitionMap.get(name).getBean();
    }

    // 注册bean
    public void registerBean(String name, BeanDefinition beanDefinition){
        beanDefinitionMap.put(name, beanDefinition);
    }
}
