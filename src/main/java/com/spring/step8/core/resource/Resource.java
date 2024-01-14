package com.spring.step8.core.resource;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author 彭锦波
 * @project small-spring
 * @description 资源接口
 * @date 2023/12/20 12:05:02
 */
public interface Resource {
  InputStream getInputStream() throws IOException;
}
