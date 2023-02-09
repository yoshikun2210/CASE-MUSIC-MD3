package rikkei.academy.service.singer;

import rikkei.academy.model.singer.Singer;
import rikkei.academy.model.song.Song;

import java.util.List;

public interface ISingerService {
    List<Singer> findByName(String name);
    List<Singer> findAll();
    void save(Singer singer);
    Singer findById(int id);
    void deleteById(int id);
    List<Singer> findAllSinger(int offset, int noOfRecords);
    Singer detailById(int id);
}

