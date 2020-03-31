package com.htqyjsw1.repository;

import java.util.List;


public interface UserRepository {
    public int count();
    public List<com.htqyjsw1.entity.User> findAll();
    
}
