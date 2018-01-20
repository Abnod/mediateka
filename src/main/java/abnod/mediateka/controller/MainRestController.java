package abnod.mediateka.controller;

import abnod.mediateka.mapper.MediaMapper;
import abnod.mediateka.mapper.UserMapper;
import abnod.mediateka.model.Media;
import abnod.mediateka.model.MediaType;
import abnod.mediateka.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/media")
public class MainRestController {

    private MediaMapper mediaMapper;

    public MainRestController(MediaMapper mediaMapper) {
        this.mediaMapper = mediaMapper;
    }

    @GetMapping(value = {"", "/", "/all", "/all/{pageId}"})
    public List<Media> listMedia(@PathVariable(required = false) String pageId) {
        int pages=1;
        try{
//            if(pageId==null || Integer.parseInt(pageId) < 1 || Integer.parseInt(pageId) > pages) pages = 1;
            if(pageId==null || Integer.parseInt(pageId) < 1) pages = 1;
        } catch (NumberFormatException e){
            pages = 1;
        }
        return mediaMapper.getMediaByPage(pages);
    }

    @GetMapping("/show/{mediaId}")
    public Media showMedia(@PathVariable String mediaId) {
        if (mediaId == null) {
            mediaId = "1";
        }
        return new Media();

    }

    @GetMapping("/search")
    public List<Media> searchMedia(@RequestParam(required = false) String title, @RequestParam(required = false) String type,
                                   @RequestParam(required = false) String singer) {

        return new ArrayList<>();
    }

    @RequestMapping("/add")
    public List<Media> addMedia(@RequestParam(required = false) String title, @RequestParam(required = false) String type,
                          @RequestParam(required = false) String singer) {
        Media med = new Media();
        med.setSinger("singerrrr");
        med.setTitle("test title");
        med.setType(MediaType.AUDIO);
        mediaMapper.addMedia(med);
        return mediaMapper.getMediaByPage(1);
    }

    @RequestMapping("/edit/{mediaId}")
    public Media editMedia(@PathVariable String mediaId) {
        if (mediaId == null) {
            mediaId = "1";
        }
        return new Media();
    }

    @RequestMapping("/delete/{mediaId}")
    public Media deleteMedia(@PathVariable String mediaId) {
        if (mediaId == null) {
            mediaId = "1";
        }
        return new Media();
    }
}
