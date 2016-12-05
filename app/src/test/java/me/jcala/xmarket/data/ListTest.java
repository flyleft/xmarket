package me.jcala.xmarket.data;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;
import lombok.ToString;

public class ListTest {
    @Test
    public void initData(){
        MyData a1=new MyData("小明",23);
        MyData a2=new MyData("小红",13);
        List<MyData> list= Arrays.asList(a1,a2);
        ListData data = new ListData(list);
        System.out.println("修改之前的List:"+list);
        for (MyData data1:list){
            data1.setAge(0);
        }
        System.out.println("修改之后的List:"+list);
        System.out.println("修改之后的data:"+data);

    }
}

@Data
@AllArgsConstructor
class ListData{
    private  List<MyData> list;
}

@ToString
@Setter
class MyData{

      private String name;
      private int age;

     MyData(String name, int age) {
        this.name = name;
        this.age = age;
    }

}
