package com.itheima.health.dao;

import com.itheima.health.pojo.Member;
import com.itheima.health.pojo.Order;
import com.itheima.health.pojo.OrderSetting;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author TALON WAT
 * @date 2019-11-18 22:30
 */
@Repository
public interface OrderDao {
    List<Order> findByCondition(Order order);

    void add(Order order);

    Map findById(Integer id);

    Integer findOrderCountByDate(String today);

    Integer findOrderCountBetweenDate(Map<String, Object> weekMap);

    Integer findVisitsCountByDate(String today);

    Integer findVisitsCountAfterDate(String thisWeekMonday);

    List<Map> findHotSetmeal();
}
