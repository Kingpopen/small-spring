package com.spring.step7.core.resource;

import cn.hutool.core.lang.Assert;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author 彭锦波
 * @project small-spring
 * @description 云端的文件资源
 * @date 2023/12/20 12:10:13
 */
public class UrlResource implements Resource {
  private URL url;

  public UrlResource(URL url){
    Assert.notNull(url, "url not be null");
    this.url = url;
  }

  @Override
  public InputStream getInputStream() throws IOException {
    URLConnection urlConnection = this.url.openConnection();
    try {
      return urlConnection.getInputStream();
    } catch (IOException e) {
      if (urlConnection instanceof HttpURLConnection){
        ((HttpURLConnection) urlConnection).disconnect();
      }
      throw e;
    }
  }
}
