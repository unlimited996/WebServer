import java.util.Arrays;
import java.util.Comparator;

/**
 * 比较对象的大小只能用comparable接口
 * String和包装类自动重写了compareTo方法进行比较
 *
 */
public class compareDemo {
    public static void main(String[] args) {
        String[] strings={"aa","bb","pp","qq","ff","xx"};
        Arrays.sort(strings);
        System.out.println(Arrays.toString(strings));

        //从大到小排序，在sort方法第二个参数里重写Comparator<String>
        Arrays.sort(strings, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if(o1 instanceof String&&o2 instanceof String){
                    String s1=(String)o1;
                    String s2=(String)o2;
                    return -s1.compareTo(s2);
                }
                return 0;
            }
        });
        System.out.println(Arrays.toString(strings));
    }
}
