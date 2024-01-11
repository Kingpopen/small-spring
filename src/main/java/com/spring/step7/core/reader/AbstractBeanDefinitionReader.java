package com.spring.step7.core.reader;

import com.spring.step7.core.loader.ResourceLoader;
import com.spring.step7.register.BeanDefinitionRegistry;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author 彭锦波
 * @project small-spring
 * @description 抽象方法
 * @date 2023/12/22 13:02:42
 * 模版方法
 */
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {
  private BeanDefinitionRegistry registry;
  private ResourceLoader resourceLoader;


  @Override
  public BeanDefinitionRegistry getRegister() {
    return registry;
  }

  @Override
  public ResourceLoader getResourceLoader() {
    return resourceLoader;
  }
}
