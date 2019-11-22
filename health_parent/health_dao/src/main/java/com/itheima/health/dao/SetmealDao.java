package com.itheima.health.dao;

import com.github.pagehelper.Page;
import com.itheima.health.pojo.CheckItem;
import com.itheima.health.pojo.Setmeal;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author TALON WAT
 * @date 2019-11-17 14:40
 */
@Repository
public interface SetmealDao {
    List<Setmeal> findAll();

    Setmeal findByid(Integer id);

    void setSetmealAndCheckGroup(Map<String, Integer> map);

    void add(Setmeal setmeal);

    Page<CheckItem> selectByCondition(String queryString);

    List<Map<String, Object>> SetmealCount();
}
