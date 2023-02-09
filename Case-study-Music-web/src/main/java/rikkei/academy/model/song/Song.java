package rikkei.academy.model.song;

import rikkei.academy.model.music.Category;
import rikkei.academy.model.singer.Band;
import rikkei.academy.model.singer.Singer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Song {
    private int id;
    private String name;
    private int listen;
    private String img;
    private String audio;
    private int countLike;

    public int getCountLike() {
        return countLike;
    }

    public void setCountLike(int countLike) {
        this.countLike = countLike;
    }

    private Set<Category> categories = new HashSet<>();

    private List<Singer> singerList = new ArrayList<>();
    private List<Integer> singerIdList = new ArrayList<>();
    private List<Integer> bandIdList = new ArrayList<>();
    private List<Band> bandList = new ArrayList<>();

//    public Song(String name, int listen, String img, String audio, Set<Category> categories, List<Singer> singerList) {
//        this.name = name;
//        this.listen = listen;
//        this.img = img;
//        this.audio = audio;
//        this.categories = categories;
//        this.singerList = singerList;
//    }

    public Song(int id, String name, int listen, String img, String audio, Set<Category> categories) {
        this.id = id;
        this.name = name;
        this.listen = listen;
        this.img = img;
        this.audio = audio;
        this.categories = categories;
    }


    public Song(String name, int listen, String img, String audio) {
        this.name = name;
        this.listen = listen;
        this.img = img;
        this.audio = audio;
    }

    public Song(int id, String name, int listen, String img, String audio) {
        this.id = id;
        this.name = name;
        this.listen = listen;
        this.img = img;
        this.audio = audio;
    }

    public Song(int id, String name, int listen) {
        this.id = id;
        this.name = name;
        this.listen = listen;

    }

    public Song(int id, String name, int listen, String img) {
        this.id = id;
        this.name = name;
        this.listen = listen;
        this.img = img;
    }

    public Song() {

    }

    public Song(String name, int listen, String img, String audio, Set<Category> categories) {
        this.name = name;
        this.listen = listen;
        this.img = img;
        this.audio = audio;
        this.categories = categories;
    }

    public Song(String name, int listen, String img, String audio, Set<Category> categories, List<Integer> singerIdList,List<Integer> bandIdList) {
        this.name = name;
        this.listen = listen;
        this.img = img;
        this.audio = audio;
        this.categories = categories;
        this.singerIdList = singerIdList;
        this.bandIdList = bandIdList;
    }

    public List<Band> getBandList() {
        return bandList;
    }

    public void setBandList(List<Band> bandList) {
        this.bandList = bandList;
    }

    public List<Integer> getBandIdList() {
        return bandIdList;
    }

    public void setBandIdList(List<Integer> bandIdList) {
        this.bandIdList = bandIdList;
    }

    public List<Integer> getSingerIdList() {
        return singerIdList;
    }

    public void setSingerIdList(List<Integer> singerIdList) {
        this.singerIdList = singerIdList;
    }

    public List<Singer> getSingerList() {
        return singerList;
    }

    public void setSingerList(List<Singer> singerList) {
        this.singerList = singerList;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getImg() {
        return this.img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getListen() {
        return listen;
    }

    public void setListen(int listen) {
        this.listen = listen;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", listen=" + listen +
                ", img='" + img + '\'' +
                '}';
    }
}
