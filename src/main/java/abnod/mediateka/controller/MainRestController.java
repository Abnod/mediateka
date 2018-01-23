package abnod.mediateka.controller;

import abnod.mediateka.mapper.MediaMapper;
import abnod.mediateka.model.Media;
import abnod.mediateka.model.MediaType;
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

    @GetMapping(value = {"/", "/{page}"})
    public List<Media> listMedia(@PathVariable(required = false) String page) {
        int pages = 1;
        try {
            if (page == null || Integer.parseInt(page) < 1) pages = 1;
        } catch (NumberFormatException e) {
            pages = 1;
        }
        return mediaMapper.getMediaByPage(pages);
    }

    @GetMapping("/search")
    public List<Media> searchMedia(@RequestParam(required = false) String title, @RequestParam(required = false) String type,
                                   @RequestParam(required = false) String singer, @RequestParam(required = false) String path) {

        return new ArrayList<>();
    }

    @PostMapping("/")
    public void addMedia(@RequestParam String title, @RequestParam String type,
                         @RequestParam(required = false) String singer, @RequestParam String path) {
        Media med = new Media();
        med.setSinger(singer);
        med.setTitle(title);
        med.setType(MediaType.valueOf(type));
        med.setPath(path);
        mediaMapper.addMedia(med);
    }

    @PostMapping(value = "/{mediaId}")
    public void editMedia(@PathVariable String mediaId, @RequestParam String title, @RequestParam String type,
                          @RequestParam String singer, @RequestParam String path) {
        Media med = new Media();
        med.setId(Integer.parseInt(mediaId));
        med.setSinger(singer);
        med.setTitle(title);
        med.setType(MediaType.valueOf(type));
        med.setPath(path);
        mediaMapper.updateMedia(med);
    }

    @DeleteMapping(value = "/{mediaId}")
    public void deleteMedia(@PathVariable String mediaId) {
        int id = 0;
        try {
            id = Integer.parseInt(mediaId);
        } catch (NumberFormatException e) {
        }
        mediaMapper.deleteMediaById(id);
    }
}
