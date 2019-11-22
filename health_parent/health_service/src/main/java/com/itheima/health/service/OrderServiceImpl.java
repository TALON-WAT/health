package com.itheima.health.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.health.constant.MessageConstant;
import com.itheima.health.dao.MemberDao;
import com.itheima.health.dao.OrderDao;
import com.itheima.health.dao.OrderSettingDao;
import com.itheima.health.entity.Result;
import com.itheima.health.pojo.Member;
import com.itheima.health.pojo.Order;
import com.itheima.health.pojo.OrderSetting;
import com.itheima.health.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author TALON WAT
 * @date 2019-11-18 22:29
 */
@Service(interfaceClass = OrderService.class)
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderSettingDao orderSettingDao;
    @Autowired
    private MemberDao memberDao;
    @Autowired
    private OrderDao orderDao;

    /**
     * 体检预约方法处理逻辑比较复杂，需要进行如下业务处理：
     * 1、检查用户所选择的预约日期是否已经提前进行了预约设置，如果没有设置则无法进行预约
     * 2、检查用户所选择的预约日期是否已经约满，如果已经约满则无法预约
     * 3、检查当前用户是否为会员，如果是会员则直接完成预约，如果不是会员则自动完成注册并进行预约
     * 4、检查用户是否重复预约（同一个用户在同一天预约了同一个套餐），如果是重复预约则无法完成再次预约
     * 5、预约成功，更新当日的已预约人数
     */
    @Override
    public Result order(Map map) throws Exception {
        String orderDate = (String) map.get("orderDate");
        Date date = DateUtils.parseString2Date(orderDate);
        //1、检查用户所选择的预约日期是否已经提前进行了预约设置，如果没有设置则无法进行预约
        OrderSetting orderSetting = orderSettingDao.findByOrderDate(date);// 根据日期查询预约设置
        if (orderSetting == null){
            //没开启预约
            return new Result(false, MessageConstant.SELECTED_DATE_CANNOT_ORDER);//没开启预约
        }

        //2、检查用户所选择的预约日期是否已经约满，如果已经约满则无法预约
        if (orderSetting.getNumber() == orderSetting.getReservations()){
            //预约已满
            return new Result(false,MessageConstant.ORDER_FULL);//预约已满
        }

        //3、根据电话号码 检查当前用户是否为会员，
        // 如果是会员则直接完成预约，并且更新填写的名字
        // 如果不是会员则自动完成注册并进行预约
        String phoneNumber = (String) map.get("telephone");
        Member member = memberDao.findByTelephone(phoneNumber);
        //防止重复预约
        if (member != null){
            Integer id = member.getId();
            int setmealId = Integer.parseInt((String) map.get("setmealId"));//套餐 id
            //检查用户是否重复预约（同一个 用户 在同一 天 预约了同一个 套餐 ）
            Order order = new Order(id, date, null, null, setmealId);
            List<Order> list = orderDao.findByCondition(order);

            if (list != null && list.size()>0){
                //已预约
                return new Result(false,MessageConstant.HAS_ORDERED);
            }
        }

        //进行预约 ,更新预约人数
        orderSetting.setReservations(orderSetting.getReservations() + 1);
        orderSettingDao.editReservationsByOrderDate(orderSetting);

        //不是会员,加会员
        if (member == null){
            member = new Member();
            member.setName((String) map.get("name"));
            member.setPhoneNumber(phoneNumber);
            member.setIdCard((String) map.get("idCard"));
            member.setSex((String) map.get("sex"));
            member.setRegTime(new Date());
            memberDao.add(member);
        }

        //是会员,更新填写的用户名
        member.setName((String)map.get("name"));
        memberDao.update(member);

        //保存预约信息到预约表
        Order order = new Order(
                member.getId(),
                date,
                (String) map.get("orderType"),
                Order.ORDERSTATUS_NO,
                Integer.parseInt((String) map.get("setmealId")));
        orderDao.add(order);

        return new Result(true, MessageConstant.ORDER_SUCCESS, order);
    }


    /**
     * 根据id查询预约信息，包括套餐信息和会员信息
     */
    @Override
    public Result findById4Detail(Integer id) throws Exception {
        Map map = orderDao.findById(id);
        if (map != null){
            Date orderDate = (Date)map.get("orderDate");
            map.put("orderDate",DateUtils.parseDate2String(orderDate));
            return new Result(true,MessageConstant.QUERY_ORDER_SUCCESS,map);
        }
        return new Result(true,MessageConstant.QUERY_ORDER_FAIL);
    }


}
