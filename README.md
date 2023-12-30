### 介绍
#### step1
实现简单的 spring 容器：bean的注册，bean的获取。
BeanDefinition 和 BeanFactory 

#### step2
实现稍微复杂的 spring 容器：
bean注册，bean生成，bean获取


SingletonRegistry 进行bean的存储管理：注册、获取 (不负责创建)
Factory 进行bean的获取，定义getBean()方法，采用了模版方法的设计模式

BeanDefinitionRegistry是进行beanDefinition的管理：注册，获取

Factory: 整合了singleton 和 beanDefinition
         负责bean的创建，以及bean的获取

#### step3
添加可以支持带参数的Bean的初始化
策略模式：使用 InstantiationStrategy的接口，添加多种实例化bean的方式。

策略作为属性放在 Bean工厂里面，**辅助Bean的创建**

当两个方法的逻辑都比较类似时，可以将共有的部分抽出来单独作为一个方法。

#### step4
给Bean 注入属性值 注入属性值应该放在哪一个流程中呢？ createBean流程

注入类型有两种：直接给出的基本类型或者对象类型 和 **需要引用Bean容器中的类型（构建一个BeanReference）**

Bean含有的属性需要放在BeanDefinition中，因此BeanDefinition中需要添加一个PropertyValues的属性
PropertyValues封装了PropertyValue，PropertyValue类中记录了Bean的每个属性的 名称 和 **值**。
如果值是 提前已经交给Bean容器进行管理(有点给这个属性加了@Autowire注解的意味） 就采用BeanReference 进行表示，可以通过BeanReference到容器中去获取。
(感觉这个地方很巧妙)
步骤：
1. 构造BeanDefinition 可以添加相应的属性信息（PropertyValues）。
2. Factory 注册 BeanDefinition。
3. 获取bean 进行使用。

#### step5
上一章是使用代码的方式生成PropertyValue然后 注入到bean中：
```java
    BeanReference beanReference = new BeanReference("userDao");
    PropertyValues propertyValues = new PropertyValues();
    // 这个userDao 是属性的名称
    PropertyValue userDaoProperty = new PropertyValue("userDao", beanReference);
    PropertyValue idProperty = new PropertyValue("id", "2");
    PropertyValue ageProperty = new PropertyValue("age", 25);
    PropertyValue userProperty = new PropertyValue("friend", new User("kingpopen", 26));
    propertyValues.addPropertyValue(userDaoProperty);
    propertyValues.addPropertyValue(idProperty);
    propertyValues.addPropertyValue(ageProperty);
    propertyValues.addPropertyValue(userProperty);
```
这样代码显得很冗余，所以现在想要通过读取xml来生成bean交给Spring进行管理，需要读取xml首先需要实现一个文件读取的接口，
该接口应该包含三个基础功能：
1.普通本地文件读取
2.类文件读取
3.网络文件读取
这个接口就是ReSource.java, 其中含有getInputStream()的方法
上述三个功能分别写3个类实现该接口，感觉像是3种策略。

接下来对Resource接口进行包装，编写一个类 通过文件路径 来获取Resource接口：ResourceLoader(这同样是一个接口)
具体的实现是DefaultResourceLoader

能够正常对文件进行读取之后，就需要实现对xml文件进行解析，和bean的生成与注入。
BeanDefinitionReader 就是一个进行 文件读取和解析 并进行bean注册的接口
当下我们是通过xml文件格式，因此写一个XmlBeanDefinitionReader实现该接口。

bean注入流程是：
1.文件解析
2.BeanDefinition的生成
3.PropertyValue属性的注入
4.Bean的注册



