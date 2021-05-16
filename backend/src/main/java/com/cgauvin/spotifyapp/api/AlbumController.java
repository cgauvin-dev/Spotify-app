package com.cgauvin.spotifyapp.api;


import com.cgauvin.spotifyapp.dao.AlbumDAO;
import com.cgauvin.spotifyapp.service.AlbumService;
import com.cgauvin.spotifyapp.model.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/album")
public class AlbumController {
    @Autowired
    AlbumService albumService;
    
    @GetMapping("/collection")
    public List<AlbumDAO> getMyCollection() {
        return albumService.getAlbumsCollection();
    }
    
    @PostMapping("/add")
    public ResponseEntity<AlbumDAO> addToMyCollection(@RequestBody Album album) {
        // convert to DAO
        AlbumDAO albumToPersist = new AlbumDAO();
        return ResponseEntity.ok(albumService.save(albumToPersist));
    }
    
    @DeleteMapping("/{albumId}")
    public void deleteFromMyCollection(@PathVariable("albumId") String albumId) {
        albumService.delete(Integer.getInteger(albumId));
    }

}
