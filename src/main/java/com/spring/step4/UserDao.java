package com.spring.step4;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 彭锦波
 * @project small-spring
 * @description
 * @date 2023/12/14 21:20:51
 */
public class UserDao {
  private static Map<String, String> map = new HashMap<>();

  static{
    map.put("1", "mike");
    map.put("2", "nancy");
    map.put("3", "john");
  }

  public String find(String id){
    return map.get(id) != null ? map.get(id) : "";
  }
}
