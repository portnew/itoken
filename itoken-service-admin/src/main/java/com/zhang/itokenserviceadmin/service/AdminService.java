package com.zhang.itokenserviceadmin.service;

import com.zhang.itokencommondomain.domain.TbSysUser;

/**
 * @Author: 张宏运
 * @Date: 2019-05-14 14:21
 */
public interface AdminService {
    public void register(TbSysUser user);

/**
 * @Author 张宏运
 * @Date  2019-05-14 14:25
 * @param loginCode 用户名
 * @param plantPassword 明文密码
 */
    public TbSysUser login(String loginCode,String plantPassword);
}
