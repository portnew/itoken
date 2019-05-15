package com.zhang.itokenserviceadmin.service.impl;

import com.zhang.itokenserviceadmin.domain.TbSysUser;
import com.zhang.itokenserviceadmin.mapper.TbSysUserMapper;
import com.zhang.itokenserviceadmin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import tk.mybatis.mapper.entity.Example;

/**
 * @Author: 张宏运
 * @Date: 2019-05-14 14:27
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    TbSysUserMapper mapper;

    @Override
    public void register(TbSysUser user) {
// 加密密码
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        mapper.insert(user);
    }

    @Override
    public TbSysUser login(String loginCode, String plantPassword) {
        Example example = new Example(TbSysUser.class);
        example.createCriteria().andEqualTo("loginCode",loginCode);
        TbSysUser tbSysUser = mapper.selectOneByExample(example);
        String password = DigestUtils.md5DigestAsHex(plantPassword.getBytes());

        if(password.equals(tbSysUser.getPassword())){
            return tbSysUser;
        }
            return null;

    }
}
