package ovv.manage.jtds.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
}
