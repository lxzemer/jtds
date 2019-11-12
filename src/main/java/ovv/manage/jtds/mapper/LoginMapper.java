package ovv.manage.jtds.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import ovv.manage.jtds.entity.UserInfo;

import java.util.List;

@Mapper
public interface LoginMapper {

    UserInfo queryUser(@Param("userName") String userName,@Param("password") String password);

    int insertUser(UserInfo user);

    List queryUserName();
}
