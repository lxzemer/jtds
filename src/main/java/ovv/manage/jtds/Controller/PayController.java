package ovv.manage.jtds.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ovv.manage.jtds.entity.PayInfo;
import ovv.manage.jtds.entity.ResponseInfo;
import ovv.manage.jtds.serviceimpl.PayInfoServiceImpl;
import ovv.manage.jtds.utils.JtdsCommon;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/jtds")
public class PayController {

    @Autowired
    PayInfoServiceImpl service;

    @PostMapping(path = "/addPayInfo")
    private ResponseInfo addPayInfo(String amt,String createDate,String remake,String involveUserId){
        PayInfo dto = new PayInfo();
        dto.setAmt(amt);
        dto.setCreateDate(createDate);
        dto.setRemake(remake);
        dto.setInvolveUserId(involveUserId);
        dto.setIsJieZhang("0");
        dto.setRecordUserId("");
        dto.setRecordUserName("");
        ResponseInfo info = new ResponseInfo();
        info.setCode(JtdsCommon.rspSuccess);
        return info;
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
