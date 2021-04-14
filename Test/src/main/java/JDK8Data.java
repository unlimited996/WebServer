import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAccessor;

/**
 * LocalDate.now()、LocalTime.now()、LocalDateTime.now()
 * Instant.now()
 * DateTimeFormatter的3种实例化方式
 * 格式化：日期-->字符串
 * 解析：字符串-->日期
 *
 */
public class JDK8Data {
    public static void main(String[] args) {
        //3种日期类实例化 now(获取当前时间) of(设置指定数字对应的时间，无偏移量影响)
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        LocalDateTime dateTime = LocalDateTime.now();
        LocalDateTime of = LocalDateTime.of(2021, 11, 11, 11, 11, 11, 11);

        System.out.println(date);
        System.out.println(time);
        System.out.println(dateTime);
        System.out.println(of);
        //获取当前时间属性
        dateTime.getDayOfMonth();
        dateTime.getDayOfWeek();
        dateTime.getDayOfYear();
        dateTime.getHour();
        dateTime.getMinute();
        dateTime.getSecond();

        //withxxx设置时间属性
        System.out.println(dateTime.withDayOfMonth(1));
        //plusxxx、minusxxx增加、减少时间
        System.out.println(dateTime.plusDays(1));
        System.out.println(dateTime.minusDays(1));

        //静态方法，返回UTC时区的instant类对象(与中国相差8小时)
        Instant instant = Instant.now();
        //输入偏移量设置当前时区的时间
        OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime);
        //从1970年开始的时间毫秒数
        System.out.println(instant.toEpochMilli());
        //计算输入毫秒数的标准时间
        System.out.println(instant.ofEpochMilli(198456156156156L));


        //根据日期标准格式实例化
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        //日期类转为String格式(使用1)
        String format = dateTimeFormatter.format(LocalDateTime.now());
        //String格式转为日期(使用2)
        TemporalAccessor parse = dateTimeFormatter.parse(format);
        System.out.println(format);
        System.out.println(parse);

        //根据日期样式实例化
        DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);

        //自定义日期格式(推荐使用)
        DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
    }
}
