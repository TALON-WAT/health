package com.itheima.health.service;

import com.itheima.health.entity.PageResult;
import com.itheima.health.pojo.Setmeal;

import java.util.List;
import java.util.Map;

/**
 * @author TALON WAT
 * @date 2019-11-17 14:38
 */
public interface SetmealService {
    List<Setmeal> findAll();

    Setmeal findByid(Integer id);

    void add(Setmeal setmeal, Integer[] checkgroupIds);

    PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);

    List<Map<String, Object>> findSetmealCountByName();

}
