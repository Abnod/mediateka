package abnod.mediateka.mapper;

import abnod.mediateka.model.Media;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface MediaMapper {

    @Select("SELECT * FROM mediateka.media;")
    List<Media> getMediaByPage(int page);

    @Insert("INSERT INTO mediateka.media (title, singer, type, path) VALUES (#{title},#{singer},#{type},#{path});")
    void addMedia(Media media);

    @Update("UPDATE mediateka.media SET title=#{title}, singer=#{singer}, type=#{type}, path=#{path} WHERE id=#{id};")
    void updateMedia(Media media);

    @Delete("DELETE FROM mediateka.media WHERE id=#{id};")
    void deleteMediaById(@Param("id") int id);
}
