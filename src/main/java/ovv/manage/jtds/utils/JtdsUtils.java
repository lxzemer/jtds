package ovv.manage.jtds.utils;
public class JtdsUtils {

    public static String USER_ID_TYPE = "0";
    public static String PAY_ID_TYPE = "1";

    public static String getRandUserId(){
        return "1007" +USER_ID_TYPE+ (System.currentTimeMillis());
    }

    public static String getPayInfoId(){
        return "1007" +PAY_ID_TYPE+ (System.currentTimeMillis());
    }
}
