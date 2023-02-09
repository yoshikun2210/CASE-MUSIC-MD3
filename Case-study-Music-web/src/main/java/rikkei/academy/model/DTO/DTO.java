package rikkei.academy.model.DTO;

import rikkei.academy.model.music.Category;
import rikkei.academy.model.singer.Band;
import rikkei.academy.model.singer.Singer;
import rikkei.academy.model.song.Song;

import java.util.ArrayList;
import java.util.List;

public class DTO {
    private List<Song> songList = new ArrayList<>();
    private List<Category> categoryList = new ArrayList<>();
    private List<Band> bandList = new ArrayList<>();
    private List<Singer> singerList = new ArrayList<>();

    public DTO() {
    }

    public DTO(List<Song> songList, List<Category> categoryList, List<Band> bandList, List<Singer> singerList) {
        this.songList = songList;
        this.categoryList = categoryList;
        this.bandList = bandList;
        this.singerList = singerList;
    }

    public List<Song> getSongList() {
        return songList;
    }

    public void setSongList(List<Song> songList) {
        this.songList = songList;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public List<Band> getBandList() {
        return bandList;
    }

    public void setBandList(List<Band> bandList) {
        this.bandList = bandList;
    }

    public List<Singer> getSingerList() {
        return singerList;
    }

    public void setSingerList(List<Singer> singerList) {
        this.singerList = singerList;
    }

    @Override
    public String toString() {
        return "DTO{" +
                "songList=" + songList +
                ", categoryList=" + categoryList +
                ", bandList=" + bandList +
                ", singerList=" + singerList +
                '}';
    }
}
