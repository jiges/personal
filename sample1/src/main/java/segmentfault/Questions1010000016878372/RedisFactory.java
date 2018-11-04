package segmentfault.Questions1010000016878372;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Properties;

public class RedisFactory {

    private static JedisPool jedisPool = null;
    //把redis连接对象放到本地线程中

    static {
        try {
//            Properties props = new Properties();
            //加载连接池配置文件
//            props.load(RedisFactory.class.getClassLoader().getResourceAsStream("redis-config.properties"));
            // 创建jedis池配置实例
            JedisPoolConfig config = new JedisPoolConfig();
            // 设置池配置项值
            config.setMaxTotal(20);
            config.setMaxIdle(5);
            config.setMaxWaitMillis(100);
//            config.setTestOnBorrow(Boolean.valueOf(props.getProperty("jedis.pool.testOnBorrow")));
//            config.setTestOnReturn(Boolean.valueOf(props.getProperty("jedis.pool.testOnReturn")));
            // 根据配置实例化jedis池
            jedisPool = new JedisPool(config, "192.168.1.105",6379,2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获得连接
     *
     * @return Jedis
     */
    public static Jedis getConn() {
        //Redis对象
        return jedisPool.getResource();
    }

    //新版本用close归还连接
    public static void closeConn(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

    //关闭池
    public static void closePool() {
        if (jedisPool != null) {
            jedisPool.close();
        }
    }

    public static JedisPool getJedisPool() {
        return jedisPool;
    }
}