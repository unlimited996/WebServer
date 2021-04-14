import jdk.nashorn.internal.objects.annotations.Function;

/**
 * 枚举类：类的对象只有有限、确定的个数，当定义一组常量时使用，
 *        如果只有一个对象的枚举类可以用单例模式的实现方式
 * JDK5.0之前：自定义枚举类
 * JDK5.0之后：enum:默认继承与java.lang.Enum类
 * 注解 Annocation
 * 一、生成文档相关的注解
 * 二、在编译时进行格式检查(JDK内置3种)
 *      1.@Override：重写
 *      2.@Deprecated：过时，不推荐使用，但不影响使用【不安全或过时】
 *      3.@SuppressWarnings：抑制编译器警告【IDEA无用部分显示为灰色时添加注解】
 * 三、跟踪代码依赖性，实现替代配置文件功能，需配合反射使用
 *      1.注解声明为@interface
 *      2.内部定义成员通常用value表示
 *      3.可以指定成员的默认值，用default定义
 *      4.如果自定义注解没有成员则表明是一个标识作用
 * 四、元注解：解释说明注解的注解
 *      1.Retention：表示注解的生命周期【SOURCE/CLASS(默认行为)、RUNTIME(反射获取需要的状态)】
 *      2.Target：修饰的注解能用于修饰那些程序元素
 *      3.Documented(少用)：表示所修饰的注解被javadoc解析时会保留下来
 *      4.Inherited(少用)：表示所修饰的注解有继承性
 * 五、通过反射获取注解
 * 六、可重读注解：1.在注解上声明@Repeatable，成员值为xxxx.class 2.其他和原注解一样
 * 七、类型注解：修饰泛型
 *      【ElementType.TYPE_PARAMETER表示该注解能写在类型变量的声明语句中】
 *      【ElementType.TYPE_USE表示该注解能写在使用类型的任何语句中】
 */
public class enumDemo {
    public static void main(String[] args) {
        Season1 spring = Season1.SPRING;
        //重写toString输出属性，否则只输出常量名称
        System.out.println(spring.toString());
        //以声明顺序返回(包含此枚举类型的常量)的数组,用于遍历枚举类
        Season1[] seasons=Season1.values();
        for (Season1 s:seasons)
            System.out.println(s);
        //把字符串转为对应的枚举类【对象】,不存在会抛异常IllegalArgumentException
        Season1 next = Season1.valueOf("WINTER");
        System.out.println(next);
    }
}
interface show{
    public void show();
}
//enum定义枚举类,当需要实现接口时，在enum类中可以每个对象分别重写
enum  Season1 implements show{
    //提供当前枚举类的对象【枚举类必须先在开始新建默认常量的数据，每个数据用，分隔；结尾】
    SPRING("春天","3"){
        @Override
        public void show() {
            System.out.println("春天在哪里？");
        }
    },
    SUMMER("夏天","3") {
        @Override
        public void show() {
            System.out.println("夏雨在哪里？");
        }
    },
    AUTUMN("秋天","3") {
        @Override
        public void show() {
            System.out.println("秋香在哪里？");
        }
    },
    WINTER("冬天","3") {
        @Override
        public void show() {
            System.out.println("冬眠不觉晓");
        }
    };
    //声明类对象的属性：private final修饰
    private final String seasonName;
    private final String seasonDesc;
    //私有化类的构造器，并给对象属性赋值
    Season1(String seasonName, String seasonDesc) {
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }
    //可选：获取枚举类对象的属性
    public String getSeasonName() {
        return seasonName;
    }
    public String getSeasonDesc() {
        return seasonDesc;
    }
    //可选：提供toString()
    @Override
    public String toString() {
        return "Season{" +
                "seasonName='" + seasonName + '\'' +
                ", seasonDesc='" + seasonDesc + '\'' +
                '}';
    }
}
//自定义枚举类
class Season{
    //声明类对象的属性：private final修饰
    private final String seasonName;
    private final String seasonDesc;
    //私有化类的构造器，并给对象属性赋值
    private Season(String seasonName, String seasonDesc) {
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }
    //提供当前枚举类的对象【】
    public static final Season SPRING=new Season("春天","3");
    public static final Season SUMMER=new Season("夏天","3");
    public static final Season AUTUMN=new Season("秋天","3");
    public static final Season WINTER=new Season("冬天","3");
    //可选：获取枚举类对象的属性
    public String getSeasonName() {
        return seasonName;
    }
    public String getSeasonDesc() {
        return seasonDesc;
    }
    //可选：提供toString()
    @Override
    public String toString() {
        return "Season{" +
                "seasonName='" + seasonName + '\'' +
                ", seasonDesc='" + seasonDesc + '\'' +
                '}';
    }
}
