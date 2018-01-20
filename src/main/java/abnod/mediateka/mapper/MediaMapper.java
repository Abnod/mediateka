package abnod.mediateka.mapper;

import abnod.mediateka.model.Media;
import abnod.mediateka.model.MediaType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface MediaMapper {

    @Select("SELECT * FROM mediateka.media WHERE id = #{id}")
    Media getMediaById(@Param("id") int id);

    @Select("SELECT * FROM mediateka.media")
    List<Media> getMediaByPage(int page);

    @Insert("INSERT INTO mediateka.media (title, singer, type) VALUES (#{title},#{singer},#{type})")
    void addMedia(Media media);
}
