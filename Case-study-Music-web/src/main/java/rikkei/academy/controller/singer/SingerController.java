package rikkei.academy.controller.singer;

import rikkei.academy.model.singer.Singer;
import rikkei.academy.model.song.Song;
import rikkei.academy.service.singer.ISingerService;
import rikkei.academy.service.singer.SingerServiceIMPL;
import rikkei.academy.service.song.SongServiceIMPL;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet({"/singer"})
public class SingerController extends HttpServlet {
    public SingerController(){
        super();
    }
    private ISingerService singerService = new SingerServiceIMPL();
    private static final long serialVersionUID = 1L;
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
                showFormCreateSinger(request, response);
                break;
            case "edit":
                showFormEditSinger(request,response);
                break;
            case "delete":
                fromDeleteSinger(request,response);
                break;
            case "detail":
                formDetailSinger(request,response);
                break;
            case "list":
                showListSinger(request, response);
                break;
        }
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
                actionCreateSinger(request, response);
                break;
            case "edit":
                actionEditSinger(request, response);
                break;
            case "delete":
                actionDeleteSinger(request,response);
                break;
            case "show":
                actionSearchSinger(request,response);
                break;




        }
    }
    //SHOW
    public void showListSinger(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Singer> singerList = singerService.findAll();
        request.setAttribute("listSinger", singerList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/singer/list.jsp");
        dispatcher.forward(request, response);
    }
    //CREATE
    public void showFormCreateSinger(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/singer/create.jsp");
        dispatcher.forward(request, response);
    }

    public void actionCreateSinger(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        int birthday = Integer.parseInt(request.getParameter("birthday"));
        String gender = request.getParameter("gender");
        String img = request.getParameter("avatar");
        Singer singer = new Singer(birthday,name,gender,img);
        singerService.save(singer);
        request.setAttribute("message", "Create Singer success !!!");
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/singer/create.jsp");
        dispatcher.forward(request, response);


    }
//SEARCH
    private void actionSearchSinger(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("search");
        List<Singer> singerListSearch = singerService.findByName(name);
        request.setAttribute("listSinger", singerListSearch);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/singer/list.jsp");
        dispatcher.forward(request, response);
        System.out.println("==================tìm ==== "+singerService.findByName(name));
    }

   //DELETE
    private void actionDeleteSinger(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        singerService.deleteById(id);
        request.setAttribute("message", "Delete success!");
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/singer/delete.jsp");
        dispatcher.forward(request, response);
    }

    private void fromDeleteSinger(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Singer singer = singerService.findById(id);
//        System.out.println("singer show ---> "+singer);
        request.setAttribute("singer",singer);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/singer/delete.jsp");
        dispatcher.forward(request, response);
    }

    //DETAIL
    private void formDetailSinger(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Singer singer = singerService.findById(id);

        request.setAttribute("singer",singer);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/singer/detail.jsp");
        dispatcher.forward(request, response);
    }



    //EDIT
    private void actionEditSinger(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Singer singer = singerService.findById(id);
        String name = request.getParameter("name");
        int birthday = Integer.parseInt(request.getParameter("birthDay"));
        String gender = request.getParameter("gender");
        singer.setName(name);
        singer.setBirthDay(birthday);
        singer.setGender(gender);
        singerService.save(singer);
        request.setAttribute("message", "Edit success!");
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/singer/edit.jsp");
        dispatcher.forward(request, response);

    }
    public void showFormEditSinger(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Singer singer = singerService.findById(id);
//        System.out.println("singer show ---> "+singer);
        request.setAttribute("singer",singer);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/singer/edit.jsp");
        dispatcher.forward(request, response);
    }

}
