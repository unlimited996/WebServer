import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * JDK8之前的4种日期类
 * 1.System类中的的currentTimeMillis()系统当前时间
 * 2.java.util.Data和java.sql.Data日期类
 * 3.SimpleDateFormat简单日期格式类
 * 4.Calendar日历类
 *
 */
public class DataDemo {
        public static void main(String[] args) throws ParseException {
            //实例化SimpleDateFormat对象
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            //格式化：日期转为字符串
            String str=simpleDateFormat.format(date);
            //解码：符串转为日期
            Date parse = simpleDateFormat.parse(str);
            //Data类转为sql类的日期格式
            java.sql.Date date1 = new java.sql.Date(date.getTime());

            Thread t1 = new Thread(()->{
                while (true) {
                    try {
                        System.out.println(date.toString());
                        System.out.println(str);
                        System.out.println(parse);
                        System.out.println(date1);
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            Thread t2 = new Thread(()->{
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            t1.setDaemon(true);
            t1.start();
            t2.start();
        }
}
