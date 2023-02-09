package rikkei.academy.controller.song;


import rikkei.academy.model.account.User;
import rikkei.academy.model.like.Like;
import rikkei.academy.model.music.Category;
import rikkei.academy.model.singer.Band;
import rikkei.academy.model.singer.Singer;
import rikkei.academy.model.song.Song;
import rikkei.academy.service.band.BandServiceIMPL;
import rikkei.academy.service.band.IBandService;
import rikkei.academy.service.category.CategoryServiceIMPL;
import rikkei.academy.service.category.ICategoryService;
import rikkei.academy.service.like.ILikeService;
import rikkei.academy.service.like.LikeServiceIMPL;
import rikkei.academy.service.singer.ISingerService;
import rikkei.academy.service.singer.SingerServiceIMPL;
import rikkei.academy.service.song.ISongService;
import rikkei.academy.service.song.SongServiceIMPL;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebServlet({"/song"})
public class SongController extends HttpServlet {
    private ICategoryService categoryService = new CategoryServiceIMPL();
    private ISingerService singerService = new SingerServiceIMPL();
    private IBandService bandService = new BandServiceIMPL();
    private final ILikeService likeService = new LikeServiceIMPL();
    private static final long serialVersionUID = 1L;

    public SongController() {
        super();
    }

    private ISongService songService = new SongServiceIMPL();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                showCreateSong(request, response);
                break;
            case "playsong":
                actionSong(request, response);
                break;
            case "list":
                showListSong(request,response);
                break;
            default:
                showSongList(request, response);
                break;
        }
    }

    private void showListSong(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Song> songList = songService.findAll();
        request.setAttribute("song",songList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/song/list.jsp");
        dispatcher.forward(request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                actionCreateSong(request, response);
                break;

        }
    }

    private void actionCreateSong(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int categoryId = Integer.parseInt(request.getParameter("category"));
        Category category = categoryService.findById(categoryId);
        Set<Category> categories = new HashSet<>();
        categories.add(category);
        String[] singerId = request.getParameterValues("singer");
        List<Integer> singerIdList = new ArrayList<>();
        for (int i = 0; i < singerId.length; i++) {
            singerIdList.add(Integer.parseInt(singerId[i]));
        }
        String[] bandId = request.getParameterValues("singer");
        List<Integer> bandIdList = new ArrayList<>();
        for (int i = 0; i < bandId.length; i++) {
            singerIdList.add(Integer.parseInt(singerId[i]));
        }
        String name = request.getParameter("name");
        int listen = Integer.parseInt(request.getParameter("listen"));
        String img = request.getParameter("avatar");
        String audio = request.getParameter("mp3");
        Song song = new Song(name, listen, img, audio, categories, singerIdList, bandIdList);
        songService.save(song);
        request.setAttribute("message", "Create Song success !!!");
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/song/create.jsp");
        dispatcher.forward(request, response);
    }

    private void showCreateSong(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categoryList = categoryService.findAll();
        request.setAttribute("category", categoryList);
        List<Singer> singerList = singerService.findAll();
        request.setAttribute("singer", singerList);
        List<Band> bandList = bandService.findAll();
        request.setAttribute("band", bandList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/song/create.jsp");
        dispatcher.forward(request, response);
    }

    private void showSongList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page = 1;
        int recordsPerPage = 5;
        if (request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));
        SongServiceIMPL serviceIMPL = new SongServiceIMPL();
        List<Song> list = serviceIMPL.findAllSongs((page - 1) * recordsPerPage,
                recordsPerPage);
        int noOfRecords = serviceIMPL.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        request.setAttribute("song", list);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
        int page1 = 1;
        int recordsPerPage1 = 5;
        if (request.getParameter("page1") != null)
            page1 = Integer.parseInt(request.getParameter("page1"));
        SingerServiceIMPL singerserviceIMPL = new SingerServiceIMPL();
        List<Singer> singerList = singerserviceIMPL.findAllSinger((page1 - 1) * recordsPerPage1,
                recordsPerPage1);
        int noOfRecords1 = serviceIMPL.getNoOfRecords();
        int noOfPages1 = (int) Math.ceil(noOfRecords1 * 1.0 / recordsPerPage1);
        request.setAttribute("singer", singerList);
        request.setAttribute("noOfPagesSG", noOfPages1);
        request.setAttribute("currentPageSG", page1);
        RequestDispatcher view = request.getRequestDispatcher("test/index.jsp");
        view.forward(request, response);
    }

    public void actionSong(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Song song = songService.findById(id);
        song.setCountLike(likeService.findByIdSong(id).size());
        User userLogin = (User) request.getSession().getAttribute("user");
        request.setAttribute("song", song);
        if(userLogin != null) {
            Like check = likeService.checkLike(id, userLogin.getId());
            request.setAttribute("check",check);
        }
        song.setListen(song.getListen()+1);
        songService.save(song);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/song/playSong.jsp");
        dispatcher.forward(request, response);
    }
}
