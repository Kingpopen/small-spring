package com.spring.step5;

import com.spring.step5.entity.BeanDefinition;
import com.spring.step5.entity.BeanReference;
import com.spring.step5.entity.PropertyValue;
import com.spring.step5.entity.PropertyValues;
import com.spring.step5.factory.DefaultListableBeanFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 彭锦波
 * @project small-spring
 * @description step4的启动类
 * @date 2023/12/11 08:11:21
 */
public class app {

  public static void main(String[] args) {
    List<String> list = new ArrayList<>();
    String res = String.join(",", list);
    System.out.println("res is:" +  res);
  }
}