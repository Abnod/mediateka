package abnod.mediateka.mapper;

import abnod.mediateka.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {

    @Select("SELECT * FROM mediateka.users WHERE login = #{login}")
    User getUserByLogin(@Param("login") String login);
}
