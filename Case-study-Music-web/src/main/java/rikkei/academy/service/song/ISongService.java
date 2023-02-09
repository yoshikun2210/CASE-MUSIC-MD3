package rikkei.academy.service.song;

import rikkei.academy.model.singer.Singer;
import rikkei.academy.model.song.Song;
import rikkei.academy.service.IGenericService;

import java.util.List;

public interface ISongService extends IGenericService<Song> {
    List<Song> findByName(String name);
    List<Song> findAll();
    Song findById(int id);
    void deleteById(int id);
    List<Song> findAllSongs(int offset, int noOfRecords);

    void listen(Song song);

}
