package com.spring.step2.exception;

import com.spring.step2.factory.BeanFactory;

/**
 * @author 彭锦波
 * @project small-spring
 * @description bean的异常
 * @date 2023/12/10 11:43:51
 */
public class BeanException extends RuntimeException{
    public BeanException(String msg){
        super(msg);
    }
}