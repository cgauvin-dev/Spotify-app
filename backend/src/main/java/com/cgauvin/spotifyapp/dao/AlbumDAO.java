package com.cgauvin.spotifyapp.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created By Zhu Lin on 1/2/2019.
 */
@Data
@Entity
@NoArgsConstructor
public class AlbumDAO implements Serializable {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long albumId;

    private String idSpotify;
    private boolean isFavorite;
    private String tags;
}
