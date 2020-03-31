package micro.service.controller;

import micro.service.entity.User;
import micro.service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/oauth")
public class UserHandler {

    @Value("${server.port}")
    private String port;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/index")
    public String index() {
        return this.port;
    }

    @GetMapping("/count")
    public int count(){
      return userRepository.count();
    }

     @GetMapping("/findAll")
     public List<User> findAll(){
       return userRepository.findAll();
     }

}
