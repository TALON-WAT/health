package com.itheima.health.dao;

import com.itheima.health.pojo.Role;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * @author TALON WAT
 * @date 2019-11-21 20:07
 */
@Repository
public interface RoleDao {

    Set<Role> findRolesByUserId(Integer userId);

}
