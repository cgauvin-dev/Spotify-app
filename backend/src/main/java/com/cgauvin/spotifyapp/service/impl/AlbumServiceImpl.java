package com.cgauvin.spotifyapp.service.impl;


import com.cgauvin.spotifyapp.dao.AlbumDAO;
import com.cgauvin.spotifyapp.repository.AlbumRepository;
import com.cgauvin.spotifyapp.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    AlbumRepository albumRepository;

    @Override
    public List<AlbumDAO> getAlbumsCollection() {
        return albumRepository.findAll();
    }

    @Override
    public AlbumDAO save(AlbumDAO album) {
        return albumRepository.save(album);
    }

    @Override
    @Transactional
    public void delete(Integer itemId) {
            albumRepository.deleteById(itemId);
    }
}
