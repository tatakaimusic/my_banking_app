package ru.pet.my_banking_app.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import ru.pet.my_banking_app.config.RedisSchema;

@Repository
public class EmailRedisRepositoryImpl implements EmailRedisRepository {

    private final JedisPool jedisPool;

    @Autowired
    public EmailRedisRepositoryImpl(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    @Override
    public String getCode(String email) {
        try (Jedis jedis = jedisPool.getResource()) {
            String key = RedisSchema.emailKeys(email);
            return jedis.get(key);
        }
    }

    @Override
    public void deleteCode(String email) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.del(RedisSchema.emailKeys(email));
        }
    }

}
