package com.library.lili.ablumli.album;

/**
 * Created by lili on 15/9/20.
 */
public class Builder {
    public String albumPath;

    public Builder(String albumPath) {
        this.albumPath = albumPath;

    }

    public String getAlbumImage() {
        return albumPath;
    }

}