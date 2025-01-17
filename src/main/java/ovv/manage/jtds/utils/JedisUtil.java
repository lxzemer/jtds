package ovv.manage.jtds.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.*;


public class JedisUtil {

    //Redis服务器IP
    private static String ADDR = "106.13.145.143";
    //Redis的端口号
    private static int PORT = 13000;
    //可用连接实例的最大数目，默认值为8；
    //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
    private static int MAX_ACTIVE = 1024;
    //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
    private static int MAX_IDLE = 20;
    //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
    private static int MAX_WAIT = 10000;
    private static int TIMEOUT = 10000;
    //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
    private static boolean TEST_ON_BORROW = true;
    private static JedisPool jedisPool = null;

    /**
     * 初始化Redis连接池
     */
    static {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(MAX_ACTIVE);
            config.setMaxIdle(MAX_IDLE);
            config.setMaxWaitMillis(MAX_WAIT);
            config.setTestOnBorrow(TEST_ON_BORROW);
            jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取
     */
    public static Jedis getJedis() {
        try {
            if (jedisPool != null) {
                Jedis resource = jedisPool.getResource();
                return resource;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 释放jedis资源
     * @param jedis
     */
    public static void returnResource(Jedis jedis) {
        if (jedis != null) {
            jedisPool.returnResource(jedis);
        }
    }

    public static byte[] parseToBytes(Object obj){
        byte[] pBytes = null;
        ByteArrayOutputStream ba = null;
        ObjectOutputStream oos = null;
        if(obj == null)
            return pBytes;
        try {
            ba = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(ba);
            oos.writeObject(obj);
            pBytes = ba.toByteArray();
        }catch (IOException e){
            e.printStackTrace();
            return pBytes;
        }finally {
            close(null,ba);
            close(null,oos);
        }
        return pBytes;
    }

    private static void close(InputStream is,OutputStream os) {
        try{
            if(is != null)
                is.close();
            if(os != null)
                os.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static Object parseToObject(byte[] pBytesResp){
        Object obj = null;
        ByteArrayInputStream bi = null;
        ObjectInputStream oi = null;
        if(pBytesResp == null)
            return obj;
        try {
            bi = new ByteArrayInputStream(pBytesResp);
            oi = new ObjectInputStream(bi);
            obj = oi.readObject();
        }catch (Exception e){
            e.printStackTrace();
            return obj;
        }finally {
            close(bi,null);
            close(oi,null);
        }
        return obj;
    }
}
