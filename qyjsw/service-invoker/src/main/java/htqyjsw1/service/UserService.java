package htqyjsw1.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import htqyjsw1.entity.Result;
import htqyjsw1.entity.ResultStatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UserService {

    @Autowired
    private RestTemplate restTemplate;


    @HystrixCommand(fallbackMethod = "getUsersFallback" )
    @CacheResult
    public Result getUsers(long userId){

        //调用服务，获取想要的数据
        Result result = new Result(ResultStatusCode.OK);
        result.setData(userId);
        System.out.println(result);
        return result;
    }

    public Result getUsersFallback(long userId){
        Result result = new Result(ResultStatusCode.HTTP_ERROR_400);

        return result;
    }


}

