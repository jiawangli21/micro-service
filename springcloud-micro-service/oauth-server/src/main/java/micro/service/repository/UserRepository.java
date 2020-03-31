package micro.service.repository;

import micro.service.entity.User;

import java.util.List;

public interface UserRepository {
    public int count();
    public List<User> findAll();

}
