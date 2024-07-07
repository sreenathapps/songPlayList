/*
 * 
 * You can use the following import statements
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.jdbc.core.JdbcTemplate;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * import java.util.ArrayList;
 * 
 */

// Write your code here

package com.example.song.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.song.model.Song;
import com.example.song.model.SongRowMapper;
import com.example.song.repository.SongRepository;

/**
 * SongH2Service
 */
@Service
public class SongH2Service implements SongRepository {
    @Autowired
    private JdbcTemplate db;

    @Override
    public ArrayList<Song> getSongs() {
        List<Song> songList = db.query("Select * from playlist", new SongRowMapper());
        ArrayList<Song> songs = new ArrayList<>(songList);
        return songs;
    }

    @Override
    public Song getSongById(int songId) {
        try {
            Song song = db.queryForObject("Select * from playlist where songId = ?", new SongRowMapper(), songId);
            return song;
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Song addSong(Song song) {
        db.update("INSERT INTO playlist(songName, lyricist, singer, musicDirector) Values (?, ?, ?, ?)", song.getSongName(), song.getLyricist(), song.getSinger(), song.getMusicDirector());
        Song savedSong = db.queryForObject("Select * from playlist Where songName = ? and lyricist = ? ", new SongRowMapper(), song.getSongName(), song.getLyricist());
        return savedSong;
    }

    @Override
    public Song updateSong(int songId, Song song) {
        if (song.getSongName()!= null) {
            db.update("UPDATE PLAYLIST SET songName = ? ", song.getSongName());
        }
        if (song.getLyricist() != null) {
            db.update("Update playlist SET lyricist = ? " , song.getLyricist());
        }
        if (song.getSinger() != null) {
            db.update("update playlist set singer = ? ", song.getSinger());
        }
        if (song.getMusicDirector() != null) {
            db.update("update playlist set musicDirector = ? ", song.getMusicDirector());
        }
        return getSongById(songId);
        
    }

    @Override
    public void deleteSong(int songId) {
        db.update("Delete from playlist where songId = ? ", songId);
    }

    
}