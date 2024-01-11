package com.spring.step7;

import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 彭锦波
 * @project small-spring
 * @description
 * @date 2023/12/14 21:20:51
 */
@Slf4j
public class UserDao {

  private static final Map<String, String> map = new HashMap<>();

  public void initMethod(){
    log.info("UserDao 执行Bean初始化！");
    map.put("1", "mike");
    map.put("2", "nancy");
    map.put("3", "john");
  }

  public void destroyMethod(){
    log.info("UserDao 执行Bean的销毁方法！");
    map.clear();
  }

  public String find(String id) {
    return map.get(id) != null ? map.get(id) : "";
  }
}
