package abnod.mediateka;

import abnod.mediateka.model.Media;
import abnod.mediateka.model.MediaType;
import abnod.mediateka.model.User;
import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("abnod.mediateka.mapper")
@MappedTypes({User.class, Media.class, MediaType.class})
public class MediatekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MediatekaApplication.class, args);
    }
}
