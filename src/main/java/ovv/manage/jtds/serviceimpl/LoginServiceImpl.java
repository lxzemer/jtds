package ovv.manage.jtds.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ovv.manage.jtds.mapper.loginMapper;

@Service
public class LoginServiceImpl {

    @Autowired(required = false)
    private loginMapper loginmapper;

    public void login() {

    }
}
