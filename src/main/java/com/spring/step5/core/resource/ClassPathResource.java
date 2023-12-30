package com.spring.step5.core.resource;

import cn.hutool.core.util.ClassUtil;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Objects;
import lombok.NoArgsConstructor;

/**
 * @author 彭锦波
 * @project small-spring
 * @description 类文件资源
 * @date 2023/12/20 12:08:47
 */
@NoArgsConstructor
public class ClassPathResource implements Resource {

  private String classPath;
  private ClassLoader classLoader;

  public ClassPathResource(String classPath) {
    this(classPath, null);
  }

  public ClassPathResource(String classPath, ClassLoader classLoader) {
    this.classPath = classPath;
    this.classLoader = Objects.isNull(classLoader) ? ClassUtil.getClassLoader() : classLoader;
  }

  @Override
  public InputStream getInputStream() throws FileNotFoundException {
    InputStream is = classLoader.getResourceAsStream(classPath);
    if (Objects.isNull(is)) {
      throw new FileNotFoundException(String.format("没有找到该文件:[%s]", classPath));
    }
    return is;
  }
}
