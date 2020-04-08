import com.htqyjsw1.utils.RedisUtils;
import com.htqyjsw1.utils.TokenUtil;
import io.jsonwebtoken.Claims;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

public class TestRedis {

    @Autowired
    private  RedisUtils redisUtils ;

    @Autowired
    private  RedisTemplate redisTemplate;


    @Test
    public void testRedis() {
        redisUtils.set("test","dvndwjknvbujdw",60,TimeUnit.MINUTES);
        Object test = redisUtils.get("test");

        System.out.println(test);
    }

    @Test
    public void  testJwt(){

        Claims claims = TokenUtil.parseJWT("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI3IiwiaWF0IjoxNTg2MzE4NTMwLCJleHAiOjMxNzI2MzcwNjB9.Z8nCtSB9eJR0hzBBYbN3RsjQLKi_5RNiqUPZYZom7SE");
        System.out.println( claims.get("jti"));
        System.out.println(claims);
    }
}
