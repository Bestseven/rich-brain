package com.rich.BasicKnowledge.Generic;

import lombok.SneakyThrows;

import javax.annotation.Resource;
import java.util.*;
import java.util.function.Consumer;

/**
 * @program: rich-brain
 * @description:
 * @author: Yafeng.Qin
 * @create: 2020-12-03 21:27
 **/
@Resource
public class Hello {
    class A{
    }
    class B{
    }
//    public static void main(String[] args) {
//        List<String> list = list();
//        List<Integer> integers = list();
//
//        List<? extends A> listA = new ArrayList<>();
//        get(listA);
//        getA(listA);
//        List<? super B> listB = new ArrayList<>();
//        get(listB);
//
//        list.forEach(ss -> ss.equals(""));
//
//    }

    /**
    * @Description: 最简单的泛型方法，我们 new 对象的时候，指定 T，T填充具体类型
     * 在调用这个方法时候，编译器自动填充
    * @Param: [target]
    * @return: void
    * @Author: Rick
    * @Date: 2020/12/3
    */
    public <T> void sayHiToTarget(T target){

    }

    /**
    * @Description: 泛型方法
    * @Param: [clazz]
    * @return: T
    * @Author: Rick
    * @Date: 2020/12/3
    */
    @SneakyThrows
    public static <T> T newInstance(Class<T> clazz){
        return clazz.newInstance();
    }
    
    /**
    * @Description: 普通方法
    * @Param: [clazz]
    * @return: T
    * @Author: Rick
    * @Date: 2020/12/3
    */
    @SneakyThrows
    public static <T> T newInstance1(Class<?> clazz){
        return (T) clazz.newInstance();
    }

    /**
    * @Description: 泛型方法：Class<T>你传递一个具体的 class 实例的时候，T 被填充了，占位符都被修改成了具体的类型
     * 比如 String，你可以在方法体中使用这个 T，这个 T 对你来说，很有用；我们就可以用 Class<T>这样的泛化方法
     * 如果，我们仅仅是传递一个 class 实例，我们不关心他是什么具体类型，我们就可以使用 Class<?> 入参，
     * 因为 Class<?>可以指向任何一个 class 实例；问号就是通配，万能通配；
     * 如果，我们还需要使用具体的 class 实例的类型引用的话，我们就要用 T 占位符；我们在方法体中拿到 T，就拿到了引用的类型。
     *
     * 泛型方法中，T 占位符具体被什么填充？是由编译器推断出来的；编译器根据你的方法参数推断出来的；实际入参的类型，推断出来的
     * 而不是像泛型类那种，由程序员指定的。
    * @Param: [tClass]
    * @return: void
    * @Author: Rick
    * @Date: 2020/12/3
    */
    public <T> void aaa(Class<T> tClass){
        System.out.println(tClass.getName());
    }

    public static <T> Set<T> union(Set<T> a, Set<T> b) {
        return null;
    }

    /**
    * @Description: 请问，这个泛型方法，被填充什么呢？没有参数了。因为你定义 list 这个方法的时候;
     *  他是无参的，编译器压根就无法通过入参类型来判断该填充什么;编译器犯难了,所以，编译器就会根据你返回值引用的类型来自动填充;
     *  如果我不用引用类型引用呢;比如，我直接 list()调用然后调用 get 方法;这个时候，编译器更难了，他知道的细节越来越少，压根没有办法填充了
     *  他只能填充 Object:所以，规则如下:
     *  【如果编译器能够根据方法入参推断，优先是方法入参；如果无法通过方法入参推断，那么就根据返回值；如果返回值也无法推断；编译器会填充 Object】
    * @Param: []
    * @return: java.util.List<T>
    * @Author: Rick
    * @Date: 2020/12/3
    */
    public static <T> List<T> list(){
        return new ArrayList<T>();
    }


    /**
    * @Description: 这个方法，要求传递一个 List 进来,但是呢，这个 List 有一定的限制;比如是是 List<T>或者是 List<T 的子类或实现类>
     *
     *   我 List<? extends A> list = new ArrayList<>(): 传递给get
     *   这个 list 入参是一个泛化的，他可以代表 List<A>，还可以代表 List<A 的子类>;
     *   因此，T 被填充 A;他就一定能接收这个 list;所以，会被填充 A
     *
     *   如果是List<? super B> list = new ArrayList<>()；
    * @Param: [list]
    * @return: T
    * @Author: Rick
    * @Date: 2020/12/3
    */
    public static <T> T get(List<? extends T> list){
        return list.get(0);
    }

    /**
    * @Description: 这个引用 get 出来的，有可能是 A 实例，也有可能是 A 的父类的实例;
     *
     * List<? extends A> list = new ArrayList<>();传递给getA
     *  因为传递进来只能是A或者A的父类，所以这里，A目前来看是最合适的，编译器还是很聪明所以它填充的是 A，取了一个交集。
     *  至少有一个 A 是大家同时满足的。因为你填充其他任何东西，都没有A好，A 是最佳的。
    * @Param: [list]
    * @return: T
    * @Author: Rick
    * @Date: 2020/12/3
    */
    public static <T> T getA(List<? super T> list){
        return (T) list.get(0);
    }

//    void forEach(Consumer<? super T> action) {
//        Objects.requireNonNull(action);
//        for (T t : this) {
//            action.accept(t);
//        }
//    }

}
