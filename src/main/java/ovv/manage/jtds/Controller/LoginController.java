package ovv.manage.jtds.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ovv.manage.jtds.entity.ResponseInfo;
import ovv.manage.jtds.entity.UserInfo;
import ovv.manage.jtds.serviceimpl.LoginServiceImpl;
import ovv.manage.jtds.utils.JedisUtil;
import ovv.manage.jtds.utils.Common;
import ovv.manage.jtds.utils.JtdsUtils;

import java.util.HashMap;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/jtds")
public class LoginController {

    @Autowired(required = false)
    LoginServiceImpl service;

    @PostMapping("/login")
    private ResponseInfo login(String userName, String password){
        ResponseInfo res = new ResponseInfo();
        log.info("username:{}.",userName);
        UserInfo user = service.queryUser(userName,password);
        res.setContent(user);
        if (user != null) {
            res.setCode(Common.SUCCESS);
            JedisUtil.getJedis().setex(user.getId().getBytes(),30*60,JedisUtil.parseToBytes(user));
        } else {
            res.setMsg("用户不存在！");
            res.setCode(Common.ERROR);
        }
        return res;
    }

    @PostMapping("/logout")
    private ResponseInfo logout(String id){
        ResponseInfo res = new ResponseInfo();
        res.setCode(Common.SUCCESS);
        JedisUtil.getJedis().del(id.getBytes());
        return res;
    }

    @PostMapping("/register")
    private ResponseInfo register(String userName,String password){
        ResponseInfo res = new ResponseInfo();
        UserInfo user = new UserInfo();
        user.setId(JtdsUtils.getRandUserId());
        user.setCreateDate("2019-10-30");
        user.setUserName(userName);
        user.setPassword(password);
        user.setIsAlive("1");
        user.setSysNo(Common.sysNo);
        int modifyNum = service.insertUser(user);
        res.setContent(user);
        JedisUtil.getJedis().setex(user.getId().getBytes(),30*60,JedisUtil.parseToBytes(user));
        if (modifyNum > 0)
            res.setCode(Common.SUCCESS);
        else
            res.setCode(Common.ERROR);
        return res;
    }

    @GetMapping("/queryUserName")
    private Object queryUserName(){
        ResponseInfo info = new ResponseInfo();
        info.setCode(Common.SUCCESS);
        List list = service.queryUserName();
        info.setContent(list);
        info.setMsg("查询成功");
        new HashMap<>();
        return info;
    }
}
