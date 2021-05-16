package com.cgauvin.spotifyapp.repository;

import com.cgauvin.spotifyapp.dao.AlbumDAO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created By Zhu Lin on 1/2/2019.
 */

public interface AlbumRepository extends JpaRepository<AlbumDAO, Integer> {
}
