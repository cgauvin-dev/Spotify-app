package com.cgauvin.spotifyapp.service;

import com.cgauvin.spotifyapp.model.Album;

import java.net.URISyntaxException;
import java.util.List;


public interface SpotifyService {
    List<Album> getListFromSpotifyAPI(String keyword) throws URISyntaxException;
    String getTokenSpotifyAPI() throws URISyntaxException;
}
