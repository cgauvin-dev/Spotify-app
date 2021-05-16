package com.cgauvin.spotifyapp.service;
import com.cgauvin.spotifyapp.dao.AlbumDAO;


import java.util.List;


public interface AlbumService {

    List<AlbumDAO> getAlbumsCollection();

    AlbumDAO save(AlbumDAO album);

    void delete(Integer albumId);

}
