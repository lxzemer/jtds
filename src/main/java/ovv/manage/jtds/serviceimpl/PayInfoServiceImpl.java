package ovv.manage.jtds.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ovv.manage.jtds.entity.PayAccount;
import ovv.manage.jtds.entity.PayInfo;
import ovv.manage.jtds.mapper.PayInfoMapper;

import java.util.List;

@Service
public class PayInfoServiceImpl {

    @Autowired(required = false)
    PayInfoMapper mapper;

    public List queryPayInfo(PayInfo info) {
        return mapper.queryPayInfo(info);
    }

    public int addPayInfo(PayInfo info){
        return mapper.insertPayInfo(info);
    }

    public List queryPayAccount(PayAccount info) {
        return mapper.queryPayAccount(info);
    }

    public int updatePayAccount(PayAccount info) {
        return mapper.updatePayAccount(info);
    }

    public int insertPayAccount(PayAccount info) {
        return mapper.insertPayAccount(info);
    }

    public void sudoPayAccount() {
        mapper.sudoPayAccount();
    }

    public void sudoPayInfo() {
        mapper.sudoPayInfo();
    }
}
