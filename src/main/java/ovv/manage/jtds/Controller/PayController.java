package ovv.manage.jtds.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ovv.manage.jtds.entity.PayAccount;
import ovv.manage.jtds.entity.PayInfo;
import ovv.manage.jtds.entity.ResponseInfo;
import ovv.manage.jtds.entity.UserInfo;
import ovv.manage.jtds.serviceimpl.LoginServiceImpl;
import ovv.manage.jtds.serviceimpl.PayInfoServiceImpl;
import ovv.manage.jtds.utils.JedisUtil;
import ovv.manage.jtds.utils.JtdsCommon;
import ovv.manage.jtds.utils.JtdsUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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
        Object obj = JedisUtil.parseToObject(JedisUtil.getJedis().get(userId.getBytes()));
        if(obj == null){
            rsp.setMsg("请先登陆！");
            rsp.setCode(JtdsCommon.rspError);
            return rsp;
        }
        if(involveUserId == null || "".equals(involveUserId)) {
            rsp.setMsg("请选择承担人员！");
            rsp.setCode(JtdsCommon.rspError);
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
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String date = df.format(new Date());
        dto.setUpdateTime(date);
        dto.setCreateDate(date);
        dto.setAccountNo(JedisUtil.getJedis().get("accountNo"));
        dto.setIsAccount(JtdsCommon.NO);
        payService.addPayInfo(dto);
        rsp.setCode(JtdsCommon.rspSuccess);
        return rsp;
    }

    @GetMapping("/queryPayInfo")
    private ResponseInfo queryPayInfo(String recordUserName,String payDate){
        ResponseInfo rep = new ResponseInfo();
        PayInfo dto = new PayInfo();
        dto.setRecordUserName(recordUserName);
        dto.setPayDate(payDate);
        List payInfos = payService.queryPayInfo(dto);
        rep.setCode(JtdsCommon.rspSuccess);
        rep.setContent(payInfos);
        rep.setTotal(payInfos.size());
        return rep;
    }

    @GetMapping("/queryPayAccount")
    private ResponseInfo queryPayAccount(){
        ResponseInfo rep = new ResponseInfo();
        //List<PayAccount> accounts = payService.queryPayAccount();
        return rep;
    }
}
