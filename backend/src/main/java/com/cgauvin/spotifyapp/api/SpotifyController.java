package com.cgauvin.spotifyapp.api;


import com.cgauvin.spotifyapp.model.Album;
import com.cgauvin.spotifyapp.service.SpotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;


@RestController
@RequestMapping("/spotify")
public class SpotifyController {
    @Autowired
    SpotifyService spotifyService;

    // Spotify API
    @GetMapping("/search")
    public List<Album> getAlbumsByKeyWord(@RequestParam(value = "query") String keyword) throws URISyntaxException {
            return spotifyService.getListFromSpotifyAPI(keyword);
    }
}
