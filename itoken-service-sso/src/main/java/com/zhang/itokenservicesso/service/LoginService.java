package com.zhang.itokenservicesso.service;

import com.zhang.itokencommondomain.domain.TbSysUser;

/**
 * @Author: 张宏运
 * @Date: 2019-05-20 17:56
 */
public interface LoginService {
    TbSysUser login(String loginCode,String plantPassword);
}
