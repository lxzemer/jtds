package ovv.manage.jtds.utils;

import java.util.UUID;

public class JtdsUtils {
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String getRandId(){
        return (int)(Math.random()*9000)+1000+"1007"+System.currentTimeMillis();
    }
}
