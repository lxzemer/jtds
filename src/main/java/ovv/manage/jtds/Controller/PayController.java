package ovv.manage.jtds.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ovv.manage.jtds.entity.PayInfo;
import ovv.manage.jtds.entity.ResponseInfo;
import ovv.manage.jtds.utils.JedisUtil;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;

@RestController
@RequestMapping("/jtds")
public class PayController {

    @GetMapping("/addPayInfo")
    private String addPayInfo(String amt,String date,String remake){
        System.out.println(amt + date + remake);
        Jedis jedis = JedisUtil.getJedis();
        jedis.set("amt","amt");
        return "true";

    }

    @GetMapping("/queryPersonName")
    private Object queryPersonName(){
        ResponseInfo info = new ResponseInfo();
        info.setCode(200);
        ArrayList list = new ArrayList<PayInfo>();
        info.setContent(list);
        info.setMsg("查询成功");
        return info;
    }

    @GetMapping("/queryPayInfos")
    private ResponseInfo queryPayInfos(){
        ResponseInfo info = new ResponseInfo();

        return info;
    }
}
