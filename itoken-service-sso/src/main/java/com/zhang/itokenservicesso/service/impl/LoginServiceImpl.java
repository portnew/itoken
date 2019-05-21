package com.zhang.itokenservicesso.service.impl;

import com.alibaba.fastjson.JSON;
import com.zhang.itokencommondomain.domain.TbSysUser;
import com.zhang.itokenservicesso.mapper.TbSysUserMapper;
import com.zhang.itokenservicesso.service.LoginService;
import com.zhang.itokenservicesso.service.consumer.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import tk.mybatis.mapper.entity.Example;

/**
 * @Author: 张宏运
 * @Date: 2019-05-20 17:57
 */
@Service
public class LoginServiceImpl implements LoginService {

    TbSysUser tbSysUser = null;
    @Autowired
    TbSysUserMapper mapper;



    @Autowired
    RedisService redisService;

    @Override
    public TbSysUser login(String loginCode, String plantPassword) {
        String json = redisService.get(loginCode);
        if (json == null) {
            //缓存中没有数据 从数据库中查询

            Example example = new Example(TbSysUser.class);
            example.createCriteria().andEqualTo("loginCode",loginCode);
            tbSysUser = mapper.selectOneByExample(example);

            String password = DigestUtils.md5DigestAsHex(plantPassword.getBytes());
            if(tbSysUser!=null&&password.equals(tbSysUser.getPassword())){
                //验证成功 将数据存入缓存
                redisService.set(loginCode, JSON.toJSONString(tbSysUser));
                return tbSysUser;

            }else{
                //验证失败
                return  null;
            }
        }else{
            //缓存有数据 要不要先判断缓存中的数据的正确性？或者有其它手段保证缓存的数据是正确的，最新的？
            return tbSysUser = JSON.parseObject(json,TbSysUser.class);
        }



    }
}
