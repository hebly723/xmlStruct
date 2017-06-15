package mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import po.UserGrade;
import po.UserGradeExample;

public interface UserGradeMapper {
    int countByExample(UserGradeExample example);

    int deleteByExample(UserGradeExample example);

    int deleteByPrimaryKey(String id);

    int insert(UserGrade record);

    int insertSelective(UserGrade record);

    List<UserGrade> selectByExample(UserGradeExample example);

    UserGrade selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") UserGrade record, @Param("example") UserGradeExample example);

    int updateByExample(@Param("record") UserGrade record, @Param("example") UserGradeExample example);

    int updateByPrimaryKeySelective(UserGrade record);

    int updateByPrimaryKey(UserGrade record);
}