package com.spring.step6.core.reader;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import com.spring.step3.exception.BeanException;
import com.spring.step6.core.resource.Resource;
import com.spring.step6.entity.BeanDefinition;
import com.spring.step6.entity.BeanReference;
import com.spring.step6.entity.PropertyValue;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Objects;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * @author 彭锦波
 * @project small-spring
 * @description xml的读取器
 * @date 2023/12/22 13:05:48
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

  @Override
  public void loadBeanDefinition(String location) {
    Resource resource = getResourceLoader().getResource(location);
    loadBeanDefinition(resource);
  }

  @Override
  public void loadBeanDefinition(Resource... resources) {
    Arrays.stream(resources).forEach(this::loadBeanDefinition);
  }

  /**
   * 着重 需要实现的方法
   *
   * @param resource
   */
  @Override
  public void loadBeanDefinition(Resource resource) {
    try {
      // 使用inputStream之后需要记得关闭
      try(InputStream is = resource.getInputStream()){
        doLoadBeanDefinition(is);
      }
    } catch (IOException | ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  private void doLoadBeanDefinition(InputStream inputStream) throws ClassNotFoundException {
    Document doc = XmlUtil.readXML(inputStream);
    if (Objects.isNull(doc)) {
      throw new BeanException("解析的Xml文件为空");
    }
    Element root = doc.getDocumentElement();
    NodeList childNodes = root.getChildNodes();
    for (int i = 0; i < childNodes.getLength(); i++) {
      // 1.1 判断元素
      if (!(childNodes.item(i) instanceof Element)) {
        continue;
      }
      // 1.2 找到bean的标签
      if (!"bean".equals(childNodes.item(i).getNodeName())) {
        continue;
      }

      // 进行解析
      Element bean = (Element) childNodes.item(i);
      String id = bean.getAttribute("id");
      String name = bean.getAttribute("name");
      String className = bean.getAttribute("class");
      Class<?> clazz = Class.forName(className);

      String beanName = StrUtil.isEmpty(id) ? name : id;
      if (StrUtil.isEmpty(beanName)) {
        beanName = StrUtil.lowerFirst(clazz.getSimpleName());
      }

      // 构造bean对象
      BeanDefinition beanDefinition = new BeanDefinition(clazz);
      // bean的属性注入
      for (int j = 0; j < bean.getChildNodes().getLength(); j++) {
        if (!(bean.getChildNodes().item(j) instanceof Element)) {
          continue;
        }

        if (!("property".equals(bean.getChildNodes().item(j).getNodeName()))) {
          continue;
        }

        Element property = (Element) bean.getChildNodes().item(j);
        String propertyName = property.getAttribute("name");
        String propertyVal = property.getAttribute("value");
        String propertyRef = property.getAttribute("ref");

        // 判断 是值类型 还是 引用类型
        Object val = StrUtil.isNotEmpty(propertyVal) ? propertyVal : new BeanReference(propertyRef);
        // 添加属性
        PropertyValue propertyValue = new PropertyValue(propertyName, val);
        beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
      }

      if (getRegister().containBeanDefinition(beanName)) {
        throw new BeanException(String.format("已经存在该Bean:[%s]", beanName));
      }

      getRegister().registerBeanDefinition(beanName, beanDefinition);
    }
  }
}