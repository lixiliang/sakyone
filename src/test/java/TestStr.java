import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

@Slf4j
public class TestStr {

    @Test
    public void test(){
        String a="a";
        String b=a+"b";
        String c="ab";
        String d=new String (b);
        log.info("a:{},b:{},c:{},d:{}",a,b,c,d);
        System.out.println(b==c);

        System.out.println(d==c);
        System.out.println(c==d.intern());
        System.out.println(c.intern()==d.intern());
        System.out.println(b.intern()==d.intern());
        System.out.println(b==d.intern());
    }
    @Test
    public void test02(){
        List<String> alist = new ArrayList<String>();
        alist.add("c");
        List<String> blist = Arrays.asList("a","a","b","b","c","d");
        blist.forEach( b->{
            if(!alist.contains(b)){
                alist.add(b);
            }
        });
        System.out.println(alist);
    }
    @Test
    public void test03(){
        //创建集合对象
        List<String> list = new ArrayList<String>();
        //添加元素
        list.add("hello");
        //并发修改异常，创建Iterator迭代器对象
//		Iterator<String> iterator = list.iterator();
//		while (iterator.hasNext()) {
//			String x = iterator.next();
//			if(x.equals("world")) {
//				list.add("javaee");
//			}
//		}
        //方法一
        //for循环方式解决
       /* for(int i=0; i<list.size(); i++) {
            String s = list.get(i);
            if(s.equals("world")) {
                list.add("java");
            }
        }*/
        //方法二
        //通过list集合的listIterator ()方法得到ListIterator列表迭代器对象
        ListIterator<String> listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            String x = listIterator.next();
            if(x.equals("world")) {
                //注意，这里使用listIterator列表迭代器对象的add方法添加
                listIterator.add("java");
            }
        }
        System.out.println(list);
    }

}
