/*
 * 
 * You can use the following import statements
 * 
 * import org.springframework.web.bind.annotation.*;
 * import java.util.*;
 *
 */

// Write your code here
package com.example.song.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.example.song.model.Song;
import com.example.song.service.SongJpaService;

import java.util.*;

@RestController
/**
 * SongController
 */
public class SongController {
    @org.springframework.beans.factory.annotation.Autowired
    private SongJpaService songService;

    @GetMapping("/songs")
    public ArrayList<Song> getSongs() {
        return songService.getSongs();
    }

    @GetMapping("/songs/{songId}")
    public Song getSong(@PathVariable("songId") int songId) {
        return songService.getSongById(songId);
    }

    @PostMapping("/songs")
    public Song addSong(@RequestBody Song song) {
        return songService.addSong(song);
    }

    @PutMapping("songs/{songId}")
    public Song updateSong(@PathVariable("songId") int songId, @RequestBody Song song) {
        return songService.updateSong(songId, song);
    }

    @DeleteMapping("songs/{songId}")
    public void deleteSong(@PathVariable("songId") int songId) {
        songService.deleteSong(songId);
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }

}