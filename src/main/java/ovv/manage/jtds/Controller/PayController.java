package ovv.manage.jtds.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ovv.manage.jtds.entity.PayInfo;
import ovv.manage.jtds.entity.ResponseInfo;
import ovv.manage.jtds.serviceimpl.PayInfoServiceImpl;
import ovv.manage.jtds.utils.JedisUtil;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/jtds")
public class PayController {

    @Autowired
    PayInfoServiceImpl service;

    @GetMapping("/addPayInfo")
    private String addPayInfo(HttpServletRequest request, String amt, String createDate, String remake){
        System.out.println(amt + createDate + remake);
        String[] involveUserId= request.getParameterValues("involveUserId");
        System.out.println(involveUserId);
        Jedis jedis = JedisUtil.getJedis();
        jedis.set("amt","amt");
        return "true";

    }

    @GetMapping("/queryPayInfo")
    private ResponseInfo queryPayInfo(){
        ResponseInfo info = new ResponseInfo();
        PayInfo dto = new PayInfo();
        List payInfos = service.queryPayInfo(dto);
        Arrays.toString(payInfos.toArray());
        info.setCode(200);
        info.setContent(payInfos);
        info.setTotal(payInfos.size());
        return info;
    }
}
