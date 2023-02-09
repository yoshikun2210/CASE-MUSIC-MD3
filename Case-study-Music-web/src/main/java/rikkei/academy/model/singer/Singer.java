package rikkei.academy.model.singer;

public class Singer {
    private int id;
    private String name ;

    private int birthDay;
    private String gender;
    private String img;

    public Singer() {
    }

    public Singer(int id, String name, int birthDay, String gender) {
        this.id = id;
        this.name = name;
        this.birthDay = birthDay;
        this.gender = gender;
    }

    public Singer(int id, String name, int birthDay, String gender, String img) {
        this.id = id;
        this.name = name;
        this.birthDay = birthDay;
        this.gender = gender;
        this.img = img;
    }

    public Singer(int birthday, String name, String gender) {
        this.name=name;
        this.birthDay=birthday;
        this.gender=gender;
    }

    public Singer(int birthday, String name, String gender, String img) {
        this.name = name;
        this.birthDay = birthDay;
        this.gender = gender;
        this.img = img;
    }


    public String getImg() {
        return img;
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

    public int getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(int birthDay) {
        this.birthDay = birthDay;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    @Override
    public String toString() {
        return "Singer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDay=" + birthDay +
                ", gender='" + gender + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
