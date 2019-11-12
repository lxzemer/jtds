package ovv.manage.jtds.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ovv.manage.jtds.entity.PayInfo;
import ovv.manage.jtds.entity.ResponseInfo;
import ovv.manage.jtds.entity.UserInfo;
import ovv.manage.jtds.serviceimpl.LoginServiceImpl;
import ovv.manage.jtds.utils.JtdsCommon;
import ovv.manage.jtds.utils.JtdsUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@RestController
@RequestMapping("/jtds")
public class LoginController {

    @Autowired(required = false)
    LoginServiceImpl service;

    @PostMapping("/login")
    private ResponseInfo login(String userName,String password){
        ResponseInfo res = new ResponseInfo();
        UserInfo user = service.queryUser(userName,password);
        if (user != null)
            res.setCode(JtdsCommon.SUCCESS);
        else
            res.setCode(JtdsCommon.FAIL);
        return res;
    }

    @PostMapping("/register")
    private ResponseInfo register(String userName,String password){
        ResponseInfo res = new ResponseInfo();
        UserInfo user = new UserInfo();
        user.setId(JtdsUtils.getRandId());
        user.setCreateDate("2019-10-30");
        user.setUserName(userName);
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

    @GetMapping("/queryUserName")
    private Object queryUserName(){
        ResponseInfo info = new ResponseInfo();
        info.setCode(200);
        List list = service.queryUserName();
        info.setContent(list);
        info.setMsg("查询成功");
        return info;
    }
}
