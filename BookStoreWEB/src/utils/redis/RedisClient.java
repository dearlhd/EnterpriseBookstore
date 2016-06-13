package utils.redis;

import java.util.ArrayList;
import java.util.List;

import entityBean.Order;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;

public class RedisClient {
	private Jedis jedis;
	private JedisPool jedisPool;
	private ShardedJedisPool shardedJedisPool;

	public RedisClient() {
		initPool();
		initShardedPool();
		shardedJedisPool.getResource();
		jedis = jedisPool.getResource();
		//jedis.flushDB();
	}

	private void initPool() {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxIdle(5);
		config.setTestOnBorrow(false);

		jedisPool = new JedisPool(config, "127.0.0.1", 6379);
	}

	private void initShardedPool() {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxIdle(5);
		config.setTestOnBorrow(false);
		
		List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
		shards.add(new JedisShardInfo("127.0.0.1", 6379, "master"));

		shardedJedisPool = new ShardedJedisPool(config, shards);
	}

	public void setOrders(List<Order> orders) {
		jedis.set(("order:"+orders.get(0).getBuyer()).getBytes(), SerializeUtil.serialize(orders));
	}

	public List<Order> getOrders(String key) {
		byte[] orders = jedis.get(key.getBytes());
		return (List<Order>) SerializeUtil.unserialize(orders);
	}
	
	public void delOrders(String key) {
		jedis.del(key.getBytes());
	}
}
