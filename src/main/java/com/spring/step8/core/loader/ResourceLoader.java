package com.spring.step8.core.loader;

import com.spring.step8.core.resource.Resource;

/**
 * @author 彭锦波
 * @project small-spring
 * @description 资源包装 接口
 * @date 2023/12/21 12:45:08
 */
public interface ResourceLoader {
  String CLASS_PATH_PREFIX = "classpath:";

  Resource getResource(String location);
}
