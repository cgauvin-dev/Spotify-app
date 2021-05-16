package com.cgauvin.spotifyapp.service.impl;


import com.cgauvin.spotifyapp.model.Album;
import com.cgauvin.spotifyapp.model.EAlbum;
import com.cgauvin.spotifyapp.service.SpotifyService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

@Service
public class SpotifyServiceImpl implements SpotifyService {

    public static final String TOKEN_TYPE = "token_type";
    public static final String ACCESS_TOKEN = "access_token";
    public static final int FORMAT_64x64 = 2;
    public final String GRANT_TYPE = "grant_type";
    public final String CLIENT_CREDENTIALS = "client_credentials";
    public String token = "";
    HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .build();

    @Override
    public List<Album> getListFromSpotifyAPI(String keyword) throws URISyntaxException {
        if (token.isEmpty()){
            getTokenSpotifyAPI();
        }

        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("api.spotify.com")
                .path("/v1/search")
                .queryParam("query", keyword)
                .queryParam("type", "album")
                .build();

        HttpRequest requete = HttpRequest.newBuilder()
                .uri(uriComponents.toUri())
                .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .setHeader(HttpHeaders.AUTHORIZATION, token)
                .GET()
                .build();

        HttpResponse response;
        List<Album> list = null;
        try {
            response = httpClient.send(requete, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status  : " + response.statusCode());
            System.out.println("Headers : " + response.headers());
            System.out.println("Body    : " + response.body());

            if (response.statusCode() == HttpStatus.OK.value()) {
                JSONObject json = new JSONObject(response.body().toString());
                list = buildAlbumsListFromJson(json);
            }
        } catch (IOException | InterruptedException | JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public String getTokenSpotifyAPI() {
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("accounts.spotify.com")
                .path("/api/token")
                .build();

        String body = GRANT_TYPE + "=" + CLIENT_CREDENTIALS;

        HttpRequest requete = HttpRequest.newBuilder()
                .uri(uriComponents.toUri())
                .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .setHeader(HttpHeaders.AUTHORIZATION, "Basic MmE4ZjMxZWIwOWY1NGRiYTk1MjVmY2M0YzA4YzgwNjE6YzAwNjcwN2RjYjgxNDU0MjliZjQ1ZmE1Mjk0YzM2MmE=")
                .POST(HttpRequest.BodyPublishers.ofString(body, UTF_8))
                .build();

        HttpResponse response;

        try {
            response = httpClient.send(requete, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status  : " + response.statusCode());
            System.out.println("Headers : " + response.headers());
            System.out.println("Body    : " + response.body());

            if (response.statusCode() == HttpStatus.OK.value()) {
                JSONObject json = new JSONObject(response.body().toString());
                token = json.getString(TOKEN_TYPE).concat(" ").concat(json.getString(ACCESS_TOKEN));
            }
        } catch (IOException | InterruptedException | JSONException e) {
            e.printStackTrace();
        }
        return token;
    }

    /**
     * construct an albumList from a raw json
     * @param json
     * @return
     * @throws JSONException
     */
    private List<Album> buildAlbumsListFromJson(JSONObject json) throws JSONException {
        JSONArray albumsJson = (JSONArray) json.getJSONObject(EAlbum.ALBUMS.getValue()).get(EAlbum.ITEMS.getValue());
        List<Album> list = new ArrayList<>();
        for (int i = 0; i < albumsJson.length(); i++) {
            JSONObject jsonObject = albumsJson.getJSONObject(i);
            Album album = new Album();
            album.setIdSpotify(jsonObject.getString(EAlbum.ID.getValue()));
            album.setTitle(jsonObject.getString(EAlbum.NAME.getValue()));
            album.setReleaseDate(LocalDate.parse(addDefaultMonthAndDay(jsonObject.getString(EAlbum.RELEASE_DATE.getValue()))));
            album.setImg(jsonObject.getJSONArray(EAlbum.IMAGES.getValue()).getJSONObject(FORMAT_64x64).getString(EAlbum.URL.getValue()));
            list.add(album);
        }
        return list;
    }

    //TODO Put this method in util class
    /**
     * if date only contains the year, we adding by default a month and a day
     * @param date
     * @throws JSONException
     */
    private static String addDefaultMonthAndDay(String date) {
        if (date.length() == 4){
            date = date.concat("-01-01");
        }
        return date;
    }
}
