package com.zhang.itokenserviceadmin;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.zhang.itokenserviceadmin.domain.TbSysUser;
import com.zhang.itokenserviceadmin.service.service.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(value = "prod")
public class ItokenServiceAdminApplicationTests {

    @Autowired
    AdminService service;
    @Test
    public void register() {
        System.out.println("8888888888888888888888888888");
        TbSysUser user = new TbSysUser();
        user.setUserCode(UUID.randomUUID().toString());
        user.setLoginCode("zhanghongy");
        user.setUserName("zhanghongy");
        user.setPassword("123456");
        user.setUserType("VIP");
        user.setMgrType("1");
        user.setStatus("0");
        user.setCreateBy(user.getUserCode());
        user.setCreateDate(new Date());
        user.setUpdateBy(user.getUserCode());
        user.setUpdateDate(new Date());
        user.setCorpCode("0");
        user.setCorpName("iToken");
        user.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        service.register(user);
    }

}
