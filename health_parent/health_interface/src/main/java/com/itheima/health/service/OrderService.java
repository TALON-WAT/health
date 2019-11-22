package com.itheima.health.service;

import com.itheima.health.entity.Result;

import java.util.Map;

/**
 * @author TALON WAT
 * @date 2019-11-18 22:29
 */
public interface OrderService {
    Result order(Map map) throws Exception;

    Result findById4Detail(Integer id) throws Exception;
}
