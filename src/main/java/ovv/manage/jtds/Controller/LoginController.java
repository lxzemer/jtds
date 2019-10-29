package ovv.manage.jtds.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ovv.manage.jtds.entity.ResponseInfo;

@RestController
@RequestMapping("/jtds")
public class LoginController {

    @PostMapping("/login")
    private ResponseInfo login(){
        return null;
    }

    @PostMapping("/register")
    private ResponseInfo register(){
        return null;
    }
}
