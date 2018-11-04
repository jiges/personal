package segmentfault.Questions1010000016878372;


import redis.clients.jedis.Jedis;

public class RedisUtil {

    public static String get(String key) {
        Jedis jedis = null;
        try {
            jedis = RedisFactory.getConn();
            return jedis.get(key);
        } finally {
            RedisFactory.closeConn(jedis);
        }
    }
}
