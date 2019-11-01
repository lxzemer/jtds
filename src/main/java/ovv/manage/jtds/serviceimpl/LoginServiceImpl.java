package ovv.manage.jtds.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ovv.manage.jtds.entity.UserInfo;
import ovv.manage.jtds.mapper.LoginMapper;

@Service
public class LoginServiceImpl {

    @Autowired(required = false)
    private LoginMapper loginmapper;

    public UserInfo queryUser(String signCode, String password) {
        return loginmapper.queryUser(signCode,password);
    }

    public int insertUser(UserInfo user) {
        return loginmapper.insertUser(user);
    }
}
