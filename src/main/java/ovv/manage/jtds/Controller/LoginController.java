package ovv.manage.jtds.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ovv.manage.jtds.entity.ResponseInfo;
import ovv.manage.jtds.entity.UserInfo;
import ovv.manage.jtds.serviceimpl.LoginServiceImpl;
import ovv.manage.jtds.utils.JtdsCommon;
import ovv.manage.jtds.utils.JtdsUtils;

import java.util.Random;
import java.util.UUID;

@RestController
@RequestMapping("/jtds")
public class LoginController {

    @Autowired(required = false)
    LoginServiceImpl service;

    @PostMapping("/login")
    private ResponseInfo login(String signCode,String password){
        ResponseInfo res = new ResponseInfo();
        UserInfo user = service.queryUser(signCode,password);
        if (user != null)
            res.setCode(JtdsCommon.SUCCESS);
        else
            res.setCode(JtdsCommon.FAIL);
        return res;
    }

    @PostMapping("/register")
    private ResponseInfo register(String signCode,String password){
        ResponseInfo res = new ResponseInfo();
        UserInfo user = new UserInfo();
        user.setId(JtdsUtils.getRandId());
        user.setCreateDate("2019-10-30");
        user.setSignCode(signCode);
        user.setPassword(password);
        user.setIsAlive("1");
        user.setSysNo("1007");
        int code = service.insertUser(user);
        if (code > 0)
            res.setCode(JtdsCommon.SUCCESS);
        else
            res.setCode(JtdsCommon.FAIL);
        return res;
    }
}
