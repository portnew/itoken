package com.zhang.itokenserviceadmin.service.service.impl;

import com.zhang.itokenserviceadmin.domain.TbSysUser;
import com.zhang.itokenserviceadmin.mapper.TbSysUserMapper;
import com.zhang.itokenserviceadmin.service.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.UUID;

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
//        加密密码
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        mapper.insert(user);
    }

    @Override
    public TbSysUser login(String loginCode, String plantPassword) {
        return null;
    }
}
