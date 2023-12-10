### 介绍
#### step1
实现简单的 spring 容器：bean的注册，bean的获取。
BeanDefinition 和 BeanFactory 

#### step2
实现稍微复杂的 spring 容器：
bean注册，bean生成，bean获取


SingletonRegistry 进行bean的存储管理：注册、获取
Factory 进行bean的获取，定义getBean()方法，采用了模版方法的设计模式

BeanDefinitionRegistry是进行beanDefinition的管理：注册，获取

Factory: 整合了singleton 和 beanDefinition

#### step3
添加可以支持带参数的Bean的初始化
策略模式：使用 InstantiationStrategy的接口，添加多种实例化bean的方式。
