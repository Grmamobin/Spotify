package com.example.spotify;
import com.example.spotify.DataBase.Artist;

import java.io.IOException;

public interface MyListener {
    public void onClickListener(Artist artist) throws IOException;
}
