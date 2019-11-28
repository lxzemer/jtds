package ovv.manage.jtds.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import ovv.manage.jtds.entity.PayAccount;
import ovv.manage.jtds.entity.PayInfo;

import java.util.List;

@Mapper
public interface PayInfoMapper {

    List queryPayInfo(@Param("info") PayInfo info);
    int insertPayInfo(PayInfo info);
    List queryPayAccount(@Param("info") PayAccount info);
    int updatePayAccount(PayAccount info);
    int insertPayAccount(PayAccount info);
    void sudoPayAccount();
    void sudoPayInfo();
}
