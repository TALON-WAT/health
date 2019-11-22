package com.itheima.health.controller;

import com.itheima.health.constant.MessageConstant;
import com.itheima.health.constant.RedisMessageConstant;
import com.itheima.health.entity.Result;
import com.itheima.health.utils.SMSUtils;
import com.itheima.health.utils.ValidateCodeUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

/**
 * @author TALON WAT
 * @date 2019-11-18 20:52
 * 短信验证码
 */
@RequestMapping("/validateCode")
@RestController
public class ValidateCodeController {
    @Autowired
    private JedisPool jedisPool;

    @RequestMapping("/send4Order")
    public Result send4Order(@RequestParam("telephone")String telephone){
        Integer code = ValidateCodeUtils.generateValidateCode(4);
        try {
            //发送短信验证码
            //SMSUtils.sendShortMessage(SMSUtils.VALIDATE_CODE,telephone,code.toString());
        } catch (Exception e) {
            return new Result(true, MessageConstant.SEND_VALIDATECODE_FAIL);
        }
        System.out.println("发送的验证码为: "+code);
        //将验证码缓存到redis
        jedisPool.getResource().setex(
                telephone + RedisMessageConstant.SENDTYPE_ORDER,
                6000*60,code.toString());
        return new Result(true, MessageConstant.SEND_VALIDATECODE_SUCCESS);
    }

    @RequestMapping("/send4Login")
    public Result send4Login(@RequestParam("telephone")String telephone){
        Integer code = ValidateCodeUtils.generateValidateCode(4);
        try {
            //发送短信验证码
            //SMSUtils.sendShortMessage(SMSUtils.VALIDATE_CODE,telephone,code.toString());
        } catch (Exception e) {
            return new Result(true, MessageConstant.SEND_VALIDATECODE_FAIL);
        }
        System.out.println("发送的验证码为: "+code);
        //将验证码缓存到redis
        jedisPool.getResource().setex(
                telephone + RedisMessageConstant.SENDTYPE_LOGIN,
                6000*60,code.toString());
        return new Result(true, MessageConstant.SEND_VALIDATECODE_SUCCESS);
    }
}
