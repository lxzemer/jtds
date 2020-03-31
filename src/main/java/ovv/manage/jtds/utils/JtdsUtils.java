package ovv.manage.jtds.utils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class JtdsUtils {

    public static String USER_ID_TYPE = "0";
    public static String PAY_ID_TYPE = "1";
    public static String PAY_ACCO_ID_TYPE = "2";

    /**
     * @return 用户单调ID
     * */
    public static String getRandUserId(){
        return "1007" +USER_ID_TYPE+ (System.currentTimeMillis());
    }

    /**
     * @return 账单信息单调ID
     * */
    public static String getPayInfoId(){
        return "1007" +PAY_ID_TYPE+ (System.currentTimeMillis());
    }

    /**
     * @return 账单汇总单调ID
     * */
    public static String getPayAccountId(){
        return "1007" +PAY_ACCO_ID_TYPE+ (System.currentTimeMillis());
    }

    /**
     * 提供精确加法计算的add方法
     * @param value1 被加数
     * @param value2 加数
     * @return 两个参数的和
     */
    public static String add(String value1,String value2){
        BigDecimal b1 = new BigDecimal(Double.valueOf(value1));
        BigDecimal b2 = new BigDecimal(Double.valueOf(value2));
        return b1.add(b2).toString();
    }

    /**
     * 提供精确减法运算的sub方法
     * @param value1 被减数
     * @param value2 减数
     * @return 两个参数的差
     */
    public static String sub(String value1,String value2){
        BigDecimal b1 = new BigDecimal(Double.valueOf(value1));
        BigDecimal b2 = new BigDecimal(Double.valueOf(value2));
        return b1.subtract(b2).toString();
    }

    /**
     * 提供精确乘法运算的mul方法
     * @param value1 被乘数
     * @param value2 乘数
     * @return 两个参数的积
     */
    public static String mul(String value1,String value2){
        BigDecimal b1 = new BigDecimal(Double.valueOf(value1));
        BigDecimal b2 = new BigDecimal(Double.valueOf(value2));
        return b1.multiply(b2).toString();
    }

    /**
     * 提供精确的除法运算方法div
     * @param value1 被除数
     * @param value2 除数
     * @param scale 精确范围
     * @return 两个参数的商
     * @throws IllegalAccessException
     */
    public static String divide(String value1,String value2,int scale) throws IllegalAccessException{
        //如果精确范围小于0，抛出异常信息
        if(scale<0){
            throw new IllegalAccessException("精确度不能小于0");
        }
        BigDecimal b1 = new BigDecimal(Double.valueOf(value1));
        BigDecimal b2 = new BigDecimal(Double.valueOf(value2));
        return b1.divide(b2, scale).toString();
    }

    public static String getMapValue(Map map,String key,String defaultValue){
        if (map == null)
            return null;
        Object value;
        return (value = map.get(key)) == null ? defaultValue : (String) value;
    }

    /**
     * @return 当前时间 yyyy-MM-dd
     * */
    public static String getCurrentDate(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(new Date());
    }

}
