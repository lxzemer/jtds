package ovv.manage.jtds.utils;
public class JtdsUtils {

    public static String getRandId(){
        return (int)(Math.random()*9000)+1000+"1007"+System.currentTimeMillis();
    }

    public static Long getPayInfoId(){
       return Long.valueOf("10070" + (System.currentTimeMillis()));
    }
}
