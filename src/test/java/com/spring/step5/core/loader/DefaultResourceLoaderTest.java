package com.spring.step5.core.loader;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.XmlUtil;
import com.spring.step5.core.resource.ClassPathResource;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 * @author 彭锦波
 * @project small-spring
 * @description
 * @date 2023/12/29 21:33:11
 */
public class DefaultResourceLoaderTest extends TestCase {

  // 测试类路径的文件访问
  @Test
  public void testClassPathFile() {
//    String filePath = "classpath:myTest.properties";
    String filePath = "classpath:spring.xml";
    DefaultResourceLoader loader = new DefaultResourceLoader();
    try {
      InputStream is = loader.getResource(filePath).getInputStream();
      String content = IoUtil.readUtf8(is);
      System.out.println("content is:" + content);
      Assert.assertEquals("内容不相等", content, "server.port=8081");
    } catch (IOException e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  // 测试普通的文件加载
  @Test
  public void testNormalFile() {
    String filePath = "src/main/resources/myTest.properties";
    DefaultResourceLoader loader = new DefaultResourceLoader();
    try {
      InputStream is = loader.getResource(filePath).getInputStream();
      String content = IoUtil.readUtf8(is);
      Assert.assertEquals("内容不相等", content, "server.port=8081");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  // 网络文件的读取
  @Test
  public void testUrlFile() {
    String filePath = "https://raw.githubusercontent.com/Kingpopen/small-spring/main/src/main/resources/myTest.properties";
    DefaultResourceLoader loader = new DefaultResourceLoader();
    try {
      InputStream is = loader.getResource(filePath).getInputStream();
      String content = IoUtil.readUtf8(is);
      System.out.println("content:" + content);
      Assert.assertEquals("内容不相等", content, "server.port=8081");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  // 解析xml文件
  @Test
  public void testParseXml(){
    String filePath = "classpath:spring.xml";
    DefaultResourceLoader loader = new DefaultResourceLoader();

    try (InputStream is = loader.getResource(filePath).getInputStream();){
      Document document = XmlUtil.readXML(is);
      System.out.println("document is:" + XmlUtil.toStr(document));
      NodeList childNodes = document.getChildNodes();
      System.out.println("childNodes size is:" + childNodes.getLength());
    } catch (FileNotFoundException e) {
      throw new RuntimeException("文件没有找到!");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}