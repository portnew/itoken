package com.zhang.itokenserviceadmin;

import com.zhang.itokenserviceadmin.domain.TbSysUser;
import com.zhang.itokenserviceadmin.service.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
//@ActiveProfiles(value = "prod")
@ActiveProfiles(value = "dev")
public class ItokenServiceAdminApplicationTests {

    @Autowired
    AdminService service;
    @Test
    public void register() {
        TbSysUser user = new TbSysUser();
        user.setUserCode(UUID.randomUUID().toString());
        user.setLoginCode("zhanghongy");
        user.setUserName("zhanghongy");
        user.setPassword("123456");
//        String plantPassword = "123456";
//        String password = DigestUtils.md5DigestAsHex(plantPassword.getBytes());
//        user.setPassword(password);
        user.setUserType("VIP");
        user.setMgrType("1");
        user.setStatus("0");
        user.setCreateBy(user.getUserCode());
        user.setCreateDate(new Date());
        user.setUpdateBy(user.getUserCode());
        user.setUpdateDate(new Date());
        user.setCorpCode("0");
        user.setCorpName("iToken");

        service.register(user);
    }

    @Test
    public void login(){
        TbSysUser user = service.login("zhanghongy","123456");
        System.out.println(user);
    }
}
