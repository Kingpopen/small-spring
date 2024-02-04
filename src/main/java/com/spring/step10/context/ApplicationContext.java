package com.spring.step10.context;

import com.spring.step10.factory.ListableBeanFactory;

/**
 * @author 彭锦波
 * @project small-spring
 * @description 上下文类 接口
 * @date 2024/1/1 19:23:52
 */
public interface ApplicationContext extends ListableBeanFactory, ApplicationEventPublisher{

}
