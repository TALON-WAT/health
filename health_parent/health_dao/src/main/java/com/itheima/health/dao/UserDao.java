package com.itheima.health.dao;

import com.itheima.health.pojo.User;
import org.springframework.stereotype.Repository;

/**
 * @author TALON WAT
 * @date 2019-11-21 19:55
 */
@Repository
public interface UserDao {
    User findUserByUsername(String username);
}
