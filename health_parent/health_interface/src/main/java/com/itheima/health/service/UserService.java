package com.itheima.health.service;

import com.itheima.health.pojo.User;
import org.springframework.stereotype.Repository;

/**
 * @author TALON WAT
 * @date 2019-11-21 19:55
 */
public interface UserService {
    User findUserByUsername(String username);
}
