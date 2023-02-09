package rikkei.academy.model.like;

public class Like {
    private int id;
    private int idSong;
    private int idUser;

    public Like(int id) {
    }

    public Like(int id, int idSong, int idUser) {
        this.id = id;
        this.idSong = idSong;
        this.idUser = idUser;
    }

    public Like(int idSong, int idUser) {
        this.idSong = idSong;
        this.idUser = idUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdSong() {
        return idSong;
    }

    public void setIdSong(int idSong) {
        this.idSong = idSong;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return "Like{" +
                "id=" + id +
                ", idSong=" + idSong +
                ", idUser=" + idUser +
                '}';
    }
}
