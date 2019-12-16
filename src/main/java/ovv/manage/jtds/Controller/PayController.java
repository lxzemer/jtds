package ovv.manage.jtds.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ovv.manage.jtds.entity.PayAccount;
import ovv.manage.jtds.entity.PayInfo;
import ovv.manage.jtds.entity.ResponseInfo;
import ovv.manage.jtds.entity.UserInfo;
import ovv.manage.jtds.runner.ovThreadPool;
import ovv.manage.jtds.runner.task.payAccountTask;
import ovv.manage.jtds.serviceimpl.LoginServiceImpl;
import ovv.manage.jtds.serviceimpl.PayInfoServiceImpl;
import ovv.manage.jtds.utils.JedisUtil;
import ovv.manage.jtds.utils.Common;
import ovv.manage.jtds.utils.JtdsUtils;

import java.util.List;

@RestController
@RequestMapping("/jtds")
public class PayController {

    @Autowired
    PayInfoServiceImpl payService;
    @Autowired(required = false)
    LoginServiceImpl logService;

    @PostMapping(path = "/addPayInfo")
    private ResponseInfo addPayInfo(String userId, String amt, String payDate, String remake, String involveUserId){
        ResponseInfo rsp = new ResponseInfo();
        if(userId == null || "".equals(userId)){
            rsp.setMsg("请先登陆！");
            rsp.setCode(Common.ERROR);
            return rsp;
        }
        Object obj = JedisUtil.parseToObject(JedisUtil.getJedis().get(userId.getBytes()));
        if(obj == null){
            rsp.setMsg("登陆已过期，请重新登陆！");
            rsp.setCode(Common.OVERLOAD);
            return rsp;
        }
        if(involveUserId == null || "".equals(involveUserId)) {
            rsp.setMsg("请选择承担人员！");
            rsp.setCode(Common.ERROR);
            return rsp;
        }
        UserInfo user = (UserInfo)obj;
        PayInfo dto = new PayInfo();
        dto.setId(JtdsUtils.getPayInfoId());
        dto.setAmt(amt);
        dto.setPayDate(payDate);
        dto.setRemake(remake);
        dto.setInvolveUserId(involveUserId);
        String userIds[] = involveUserId.split(",");
        String userNames = "";
        for(String id : userIds){
            UserInfo person = logService.queryUserById(id);
            if(person != null)
                userNames += person.getUserName()+",";
        }
        dto.setInvolveUserName(userNames.substring(0,userNames.length()-1));
        dto.setRecordUserId(user.getId());
        dto.setRecordUserName(user.getUserName());
        dto.setUpdateTime(JtdsUtils.getCurrentDate());
        dto.setCreateDate(JtdsUtils.getCurrentDate());
        dto.setAccountNo(JedisUtil.getJedis().get("accountNo"));
        dto.setIsAccount(Common.NO);
        payService.addPayInfo(dto);
        rsp.setCode(Common.SUCCESS);
        ovThreadPool.pool.submit(new payAccountTask());
        return rsp;
    }

    @GetMapping("/queryPayInfo")
    private ResponseInfo queryPayInfo(String recordUserName,String payDate){
        ResponseInfo rep = new ResponseInfo();
        PayInfo dto = new PayInfo();
        dto.setRecordUserName(recordUserName);
        dto.setPayDate(payDate);
        List payInfos = payService.queryPayInfo(dto);
        rep.setCode(Common.SUCCESS);
        rep.setContent(payInfos);
        rep.setTotal(payInfos.size());
        return rep;
    }

    @GetMapping("/queryPayAccount")
    private ResponseInfo queryPayAccount(){
        ResponseInfo rep = new ResponseInfo();
        PayAccount pay = new PayAccount();
        pay.setIsAccount(Common.NO);
        List accounts = payService.queryPayAccount(pay);
        rep.setCode(Common.SUCCESS);
        rep.setContent(accounts);
        rep.setTotal(accounts.size());
        return rep;
    }

    @GetMapping("/sudoPayAccount")
    private ResponseInfo sudoPayAccount(){
        ResponseInfo rep = new ResponseInfo();
        payService.sudoPayAccount();
        payService.sudoPayInfo();
        rep.setCode(Common.SUCCESS);
        return rep;
    }
}
