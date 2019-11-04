package ovv.manage.jtds.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import ovv.manage.jtds.entity.PayInfo;

import java.util.List;

@Mapper
public interface PayInfoMapper {

    List queryPayInfo(@Param("info") PayInfo info);
}
