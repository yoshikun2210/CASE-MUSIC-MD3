package rikkei.academy.service.like;

import rikkei.academy.model.like.Like;

import java.util.List;

public interface ILikeService {

   void save(Like like);

   List<Like> findByIdSong(int idSong);

   Like checkLike(int idSong, int idUser);

   void dislike(int id);

}
