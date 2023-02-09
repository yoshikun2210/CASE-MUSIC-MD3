package rikkei.academy.model.singer;

import java.util.ArrayList;
import java.util.List;

public class Band {
    int id;
    String name;
    int year;

    public Band() {
    }

    public Band(int id, String name, int year) {
        this.id = id;
        this.name = name;
        this.year = year;
    }

    public Band(String name, int year) {
        this.name=name;
        this.year=year;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Band{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", year=" + year +
                '}';
    }
}
