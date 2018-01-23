package abnod.mediateka.mapper;

import abnod.mediateka.model.Media;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface MediaMapper {

    @Select("SELECT * FROM mediateka.media LIMIT #{page}, #{records};")
    List<Media> getMediaByPage(@Param("page") int page, @Param("records") int records);

    @SelectProvider(type = SearchProvider.class, method = "search")
    List<Media> getSearchMediaByPage(@Param("searchParams") Media searchParams, @Param("page") int page,
                                     @Param("records") int records);

    @Insert("INSERT INTO mediateka.media (title, singer, type, path) VALUES (#{title},#{singer},#{type},#{path});")
    void addMedia(Media media);

    @Update("UPDATE mediateka.media SET title=#{title}, singer=#{singer}, type=#{type}, path=#{path} WHERE id=#{id};")
    void updateMedia(Media media);

    @Delete("DELETE FROM mediateka.media WHERE id=#{id};")
    void deleteMediaById(@Param("id") int id);

    @Select("SELECT COUNT(id) FROM mediateka.media;")
    int getMediaCount();

    @SelectProvider(type = SearchProvider.class, method = "getPages")
    int getSearchMediaCount(@Param("searchParams") Media searchParams);
}
