package com.atguigu.test;

import com.atguigu.ioc_03.A;
import com.atguigu.ioc_03.HappyComponent;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringIoCTest {

    /**
     * 讲解如何创建ioc容器并且读取配置文件
     */
    public void createToc () {

        // 1. 创建容器， 选择合适的容器实现即可
        /**
         * 接口
         *  BeanFactory
         *  ApplicationContext
         * 实现类
         * 可以直接通过构造函数实例化
         *  ClassPathXmlApplicationContext 读取路径下的xml配置方式(target)
         *  FileSystemXmlApplicationContext 读取指定文件位置的xml配置方式
         *  AnnotationConfigApplicationContext 读取配置类方式的ioc容器
         *  WebApplicationContext 读取web应用下的xml配置方式
         */

        // 方式1：直接创建容器并且指定配置文件即可 【推荐】
        // 构造函数(String... 配置文件) 可以一个可多个
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-03.xml");

        // 方式2：先创建IoC容器对象， 在指定配置文件，再刷新
        // 源码的匹配值过程：创建容器【spring】和配置文件指定分开【自己指定】
        ClassPathXmlApplicationContext applicationContext1 = new ClassPathXmlApplicationContext();
        applicationContext1.setConfigLocations("spring-03.xml");
        applicationContext1.refresh(); // 调用刷新方法
    }

    /**
     * 讲解如何从ioc容器中获取组件bean
     */
    @Test
    public void getBeanFormIoc() {

        // 1. 创建IoC容器
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext();
        applicationContext.setConfigLocations("spring-03.xml");
        applicationContext.refresh(); // Ioc DI动作

        // 2. 读取IoC容器的组件
        // 方案1-直接根据beanId获取即可，但是需要进行强转
        // Object happyComponent = applicationContext.getBean("happyComponent");
        // 进行强转 HappyComponent

        HappyComponent happyComponent = (HappyComponent) applicationContext.getBean("happyComponent");

        // 方案2-根据BeanId，同时指定bean的类型Class
        HappyComponent happyComponent1 = applicationContext.getBean("happyComponent", HappyComponent.class);

        // 方案3-直接根据类型获取
        // TODO：根据Bean的类型获取，同一类型，再ioc容器中只能由一个Bean！！！
        // TODO: 如果ioc容器存在多个同类型的Bean 会出现NoUniqueBeanDefinitionException
        // TODO: Ioc的配置一定是实现类，但是可以根据接口类型获取值！ getBean(类型)； instanceof ioc容器的类型 == true
        A happyComponent2 = applicationContext.getBean(A.class);

        happyComponent2.doWork();

        System.out.println(happyComponent == happyComponent1);
        System.out.println(happyComponent1 == happyComponent2);

    }

    /**
     * 测试IoC初始化和销毁的方法的触发
     */
    @Test
    public void test_04() {

        // 1. 创建ioc容器，就会进行组件对象的实例化 -> init
        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("spring-04.xml");


        // ioc -> 调用destroy
        // ioc会立即释放，死了!

        applicationContext.close(); // 调用销毁方法
    }
}
