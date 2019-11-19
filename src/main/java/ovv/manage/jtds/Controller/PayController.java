package ovv.manage.jtds.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ovv.manage.jtds.entity.PayInfo;
import ovv.manage.jtds.entity.ResponseInfo;
import ovv.manage.jtds.entity.UserInfo;
import ovv.manage.jtds.serviceimpl.PayInfoServiceImpl;
import ovv.manage.jtds.utils.JedisUtil;
import ovv.manage.jtds.utils.JtdsCommon;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/jtds")
public class PayController {

    @Autowired
    PayInfoServiceImpl service;

    @PostMapping(path = "/addPayInfo")
    private ResponseInfo addPayInfo(String userId, String amt, String createDate, String remake, String involveUserId){
        ResponseInfo rsp = new ResponseInfo();
        Object obj = JedisUtil.parseToObject(JedisUtil.getJedis().get(userId.getBytes()));
        if(obj == null){
            rsp.setMsg("请先登陆！");
            rsp.setCode(JtdsCommon.rspError);
            return rsp;
        }
        UserInfo user = (UserInfo)obj;
        PayInfo dto = new PayInfo();
        dto.setAmt(amt);
        dto.setCreateDate(createDate);
        dto.setRemake(remake);
        dto.setInvolveUserId(involveUserId);
        dto.setIsJieZhang("0");
        dto.setRecordUserId(user.getId());
        dto.setRecordUserName(user.getUserName());
        service.addPayInfo(dto);
        rsp.setCode(JtdsCommon.rspSuccess);
        return rsp;
    }

    @GetMapping("/queryPayInfo")
    private ResponseInfo queryPayInfo(){
        ResponseInfo rep = new ResponseInfo();
        PayInfo dto = new PayInfo();
        List payInfos = service.queryPayInfo(dto);
        Arrays.toString(payInfos.toArray());
        rep.setCode(JtdsCommon.rspSuccess);
        rep.setContent(payInfos);
        rep.setTotal(payInfos.size());
        return rep;
    }
}
