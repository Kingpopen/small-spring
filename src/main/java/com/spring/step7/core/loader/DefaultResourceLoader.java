package com.spring.step7.core.loader;

import com.spring.step7.core.resource.ClassPathResource;
import com.spring.step7.core.resource.FileSystemResource;
import com.spring.step7.core.resource.Resource;
import com.spring.step7.core.resource.UrlResource;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

/**
 * @author 彭锦波
 * @project small-spring
 * @description 默认的资源包装类
 * @date 2023/12/21 12:46:58
 */
public class DefaultResourceLoader implements ResourceLoader {

  @Override
  public Resource getResource(String location) {
    if (Objects.isNull(location) || location.isEmpty()) {
      throw new RuntimeException("资源路径不能为空！");
    }

    if (location.startsWith(CLASS_PATH_PREFIX)) {
      // 类文件
      return new ClassPathResource(location.substring(CLASS_PATH_PREFIX.length()));
    } else {
      try {
        URL url = new URL(location);
        return new UrlResource(url);
      } catch (MalformedURLException e) {
        return new FileSystemResource(location);
      }
    }

  }
}
