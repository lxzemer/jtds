package ovv.manage.jtds.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mang")
public class LoginController {

    @PostMapping("/login")
    private String login(){
        System.out.println("aaa");
        return "true";
    }
}
