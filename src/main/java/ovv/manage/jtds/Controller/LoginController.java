package ovv.manage.jtds.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ovv.manage.jtds.entity.ResponseInfo;
import ovv.manage.jtds.entity.UserInfo;
import ovv.manage.jtds.serviceimpl.LoginServiceImpl;
import ovv.manage.jtds.utils.JtdsCommon;
import ovv.manage.jtds.utils.JtdsUtils;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/jtds")
public class LoginController {

    @Autowired(required = false)
    LoginServiceImpl service;

    @PostMapping("/login")
    private ResponseInfo login(String userName,String password){
        ResponseInfo res = new ResponseInfo();
        UserInfo user = service.queryUser(userName,password);
        res.setContent(user);
        if (user != null)
            res.setCode(JtdsCommon.rspSuccess);
        else
            res.setCode(JtdsCommon.rspError);
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
        user.setSysNo(JtdsCommon.sysNo);
        int modifyNum = service.insertUser(user);
        if (modifyNum > 0)
            res.setCode(JtdsCommon.rspSuccess);
        else
            res.setCode(JtdsCommon.rspError);
        return res;
    }

    @GetMapping("/queryUserName")
    private Object queryUserName(){
        ResponseInfo info = new ResponseInfo();
        info.setCode(JtdsCommon.rspSuccess);
        List list = service.queryUserName();
        info.setContent(list);
        info.setMsg("查询成功");
        new HashMap<>();
        return info;
    }
}
