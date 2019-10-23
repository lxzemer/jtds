package ovv.manage.jtds.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jtds")
public class PayController {

    @PostMapping("/addPayInfo")
    private String addPayInfo(){

        return "true";
    }
}
