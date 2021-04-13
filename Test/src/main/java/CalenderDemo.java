import java.util.Calendar;
import java.util.Date;

public class CalenderDemo {
    public static void main(String[] args) {
        //创建Calendar子类对象，调用getInstance方法
        Calendar calendar = Calendar.getInstance();
        //get()方法
        int month = calendar.get(Calendar.DAY_OF_MONTH);//当前时间是这个月的第？天
        System.out.println(month);
        System.out.println(calendar.get(Calendar.WEEK_OF_MONTH));//当前时间是今年的第？天
        //set()方法
        calendar.set(Calendar.DAY_OF_YEAR,11);//设置时间：今年的第？天
        System.out.println(calendar.get(Calendar.DAY_OF_YEAR));
        //add()方法
        calendar.add(Calendar.DAY_OF_YEAR,11);//修改时间：今年的第几+？天
        System.out.println(calendar.get(Calendar.DAY_OF_YEAR));
        //日历类转为Data类
        Date data = calendar.getTime();
        //Data类转为日历类
        calendar.setTime(data);
    }
}
