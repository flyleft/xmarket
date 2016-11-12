package me.jcala.xmarket.util;

/**
 * 保存常用单例的静态工厂
 */
public class CommonFactory {


    private CommonFactory(){}

    private static class FactoryHolder{
       private static CommonFactory factory=new CommonFactory();
    }

    public static CommonFactory INSTANCE(){
        return FactoryHolder.factory;
    }
}
