package com.cgauvin.spotifyapp.model;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;


@Data
public class Album {
    @Min(value = 1)
    private String id;
    @NotEmpty
    private String idSpotify;
    private boolean isFavorite;
    private String tags;
    private String title;
    private String img;
    private LocalDate releaseDate;
    private Time albumDuration;
}
