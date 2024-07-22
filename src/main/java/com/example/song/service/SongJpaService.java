/*
 * You can use the following import statements
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * import java.util.*;
 */

// Write your code here
package com.example.song.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.song.model.Song;
import com.example.song.repository.SongJpaRepository;
import com.example.song.repository.SongRepository;

/**
 * SongJpaService
 */
@Service
public class SongJpaService implements SongRepository {

    @Autowired
    private SongJpaRepository songJpaRepository;

    @Override
    public ArrayList<Song> getSongs() {
        List<Song> songList = songJpaRepository.findAll();
        return new ArrayList<>(songList);
    }

    @Override
    public Song getSongById(int songId) {
        try {
            Song song = songJpaRepository.findById(songId).get();
            return song;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Song addSong(Song song) {
        songJpaRepository.save(song);
        return song;
    }

    @Override
    public Song updateSong(int songId, Song song) {
        try {
            Song songToUpdate = songJpaRepository.findById(songId).get();
            if (song.getLyricist() != null) {
                songToUpdate.setLyricist(song.getLyricist());
            }
            if (song.getMusicDirector() != null) {
                songToUpdate.setMusicDirector(song.getMusicDirector());
            }
            if (song.getSinger() != null) {
                songToUpdate.setSinger(song.getSinger());
            }
            if (song.getSongName() != null) {
                songToUpdate.setSongName(song.getSongName());
            }
            songJpaRepository.save(songToUpdate);
            return songToUpdate;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteSong(int songId) {
        try {
            songJpaRepository.deleteById(songId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}