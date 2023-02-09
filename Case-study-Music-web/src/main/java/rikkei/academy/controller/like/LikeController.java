package rikkei.academy.controller.like;

import rikkei.academy.model.account.User;
import rikkei.academy.model.like.Like;
import rikkei.academy.service.like.ILikeService;
import rikkei.academy.service.like.LikeServiceIMPL;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/like")
public class LikeController extends HttpServlet {
    ILikeService likeService = new LikeServiceIMPL();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "like":
                createLike(request, response);
                break;
            case "dislike":
                dislike(request, response);
        }
    }

    private void dislike(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int idSong = Integer.parseInt(request.getParameter("id"));
        int idLike = Integer.parseInt(request.getParameter("idlike"));
        likeService.dislike(idLike);
        response.sendRedirect("song?action=playsong&id=" + idSong);
    }

    private void createLike(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int idSong = Integer.parseInt(request.getParameter("id"));
        User userLogin = (User) request.getSession().getAttribute("user");
        likeService.save(new Like(idSong, userLogin.getId()));
        response.sendRedirect("song?action=playsong&id=" + idSong);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
