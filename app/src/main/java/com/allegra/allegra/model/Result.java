package com.allegra.allegra.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Result {

    @PrimaryKey(autoGenerate = true)
    private int idResult;

    @ColumnInfo(name = "artist")
    private String artist;

    @ColumnInfo(name = "album")
    private String album;

    @ColumnInfo(name = "track")
    private String track;

    @ColumnInfo(name = "cover")
    private String cover;

    @ColumnInfo(name = "idArtist")
    private int idArtist;

    @ColumnInfo(name = "idAlbum")
    private int idAlbum;

    @ColumnInfo(name = "idTrack")
    private int idTrack;





    public Result() {}

    public int getIdArtist() { return idArtist; }

    public void setIdArtist(int idArtist) { this.idArtist = idArtist; }

    public int getIdAlbum() { return idAlbum; }

    public void setIdAlbum(int idAlbum) { this.idAlbum = idAlbum; }

    public int getIdTrack() { return idTrack; }

    public void setIdTrack(int idTrack) { this.idTrack = idTrack; }

    public String getCover() { return cover; }

    public void setCover(String cover) { this.cover = cover; }

    public int getIdResult() {
        return idResult;
    }

    public void setIdResult(int idResult) {
        this.idResult = idResult;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getTrack() {
        return track;
    }

    public void setTrack(String track) {
        this.track = track;
    }


    @Ignore
    public Result(int idResult, String artist, String album, String track,
                  String cover, int idArtist, int idAlbum, int idTrack) {
        this.idResult = idResult;
        this.artist = artist;
        this.album = album;
        this.track = track;
        this.cover = cover;
        this.idArtist = idArtist;
        this.idAlbum = idAlbum;
        this.idTrack = idTrack;
    }

}
