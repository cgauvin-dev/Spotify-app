package com.cgauvin.spotifyapp.model;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
public enum EAlbum {
    ALBUMS("albums"),
    ITEMS("items"),
    ID("id"),
    NAME("name"),
    IMAGES("images"),
    URL("url"),
    RELEASE_DATE("release_date");

    private final String value;

}
