package me.jcala.xmarket.mock;

public class MockHelper {

   private static class MockHelperBuilder{
       private static MockHelper init=new MockHelper();
   }
    private MockHelper() {
    }
    public static MockHelper INSTANCE(){
        return MockHelperBuilder.init;
    }

}
