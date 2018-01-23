package abnod.mediateka;

import abnod.mediateka.model.Media;
import abnod.mediateka.model.MediaType;
import abnod.mediateka.model.User;
import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("abnod.mediateka.mapper")
@MappedTypes({User.class, Media.class, MediaType.class})
public class MediatekaApplication {

    private final Logger logger = LoggerFactory.getLogger(MediatekaApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(MediatekaApplication.class, args);
    }
}
