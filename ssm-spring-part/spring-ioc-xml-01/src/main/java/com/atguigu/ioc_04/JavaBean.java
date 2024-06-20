package com.atguigu.ioc_04;

public class JavaBean {

    /**
     * 必须是public 必须是void 返回值 必须是无参数的
     * 初始化方法 -> 初始化业务逻辑
     */
    public void init() {
        System.out.println("JavaBean is initializing.");
    }

    /**
     * 销毁方法
     */
    public void clear() {
        System.out.println("JavaBean is destroy.");
    }
}
