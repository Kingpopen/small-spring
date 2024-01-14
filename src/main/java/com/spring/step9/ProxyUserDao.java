package com.spring.step9;

import com.spring.step9.entity.FactoryBean;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 彭锦波
 * @project small-spring
 * @description 代理的UserDao
 * @date 2024/1/14 17:52:54
 */
public class ProxyUserDao implements FactoryBean<IUserDao> {

  @Override
  public IUserDao getObject() {
    // 自己实现了IUserDao
    return new IUserDao() {
      final Map<String, String> map = new HashMap<>();
      {
        map.put("1", "mike");
        map.put("2", "nancy");
        map.put("3", "john");
      }
      @Override
      public String find(String id) {
        return map.get(id) != null ? map.get(id) : "";
      }
    };
  }

  @Override
  public Class<?> getObjectType() {
    return IUserDao.class;
  }

  @Override
  public boolean isSingleton() {
    return true;
  }
}
