package abnod.mediateka.controller;

import abnod.mediateka.mapper.MediaMapper;
import abnod.mediateka.model.Media;
import abnod.mediateka.model.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/media")
public class MainRestController {

    private MediaMapper mediaMapper;
    private double recordsOnPage = 5.0;

    public MainRestController(MediaMapper mediaMapper) {
        this.mediaMapper = mediaMapper;
    }

    //at the moment the number of records on the page is specified in this method.
    //In the future, it is planned to receive this number from the user
    @GetMapping("/pages")
    public int getPagesCount() {
        return (int) Math.ceil(mediaMapper.getMediaCount() / recordsOnPage);
    }

    @PostMapping("/searchpages")
    public int getSearchPages(@RequestParam(required = false) String title, @RequestParam String type,
                              @RequestParam(required = false) String singer, @RequestParam(required = false) String path) {
        Media med = new Media();
        med.setSinger(singer);
        med.setTitle(title);
        med.setType(MediaType.valueOf(type));
        med.setPath(path);
        return (int) Math.ceil(mediaMapper.getSearchMediaCount(med) / recordsOnPage);
    }

    @GetMapping(value = {"/", "/{page}"})
    public List<Media> listMedia(@PathVariable(required = false) String page) {
        int pages;
        try {
            if (page == null || Integer.parseInt(page) < 1) pages = 1;
            else {
                pages = Integer.parseInt(page);
            }
        } catch (NumberFormatException e) {
            pages = 1;
        }
        return mediaMapper.getMediaByPage((pages - 1) * (int) recordsOnPage, (int) recordsOnPage);
    }

    @PostMapping(value = {"/search", "/search/{page}"})
    public List<Media> searchMedia(@PathVariable(required = false) String page,
                                   @RequestParam(required = false) String title, @RequestParam String type,
                                   @RequestParam(required = false) String singer, @RequestParam(required = false) String path) {
        int pages;
        try {
            if (page == null || Integer.parseInt(page) < 1) pages = 1;
            else {
                pages = Integer.parseInt(page);
            }
        } catch (NumberFormatException e) {
            pages = 1;
        }
        Media med = new Media();
        med.setSinger(singer);
        med.setTitle(title);
        med.setType(MediaType.valueOf(type));
        med.setPath(path);
        return mediaMapper.getSearchMediaByPage(med, (pages - 1) * (int) recordsOnPage, (int) recordsOnPage);
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
