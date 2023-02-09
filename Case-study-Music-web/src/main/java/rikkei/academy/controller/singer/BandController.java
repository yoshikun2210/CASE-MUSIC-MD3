package rikkei.academy.controller.singer;

import rikkei.academy.model.singer.Band;
import rikkei.academy.model.singer.Singer;
import rikkei.academy.service.band.BandServiceIMPL;
import rikkei.academy.service.band.IBandService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/band")
public class BandController extends HttpServlet {
    private IBandService bandService = new BandServiceIMPL();
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
                showFormCreateBand(request, response);
                break;
            case "edit":
                showFormEditBand(request,response);
                break;
            case "delete":
                fromDeleteBand(request,response);
                break;
            case "detail":
                formDetailBand(request,response);
                break;
            case "show":
                showListBand(request, response);
                break;
            case "showBand":
                actionSearchBand(request, response);
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
                try {
                    actionCreateBand(request,response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "edit":
                try {
                    actionEditBand(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "delete":
                actionDeleteBand(request, response);
                break;
            case "showBand":
                actionSearchBand(request, response);
                break;
        }
    }


//CREATE
    private void actionCreateBand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String name = request.getParameter("name");
        int year = Integer.parseInt(request.getParameter("year"));
        Band band = new Band(name,year);
        bandService.save(band);
        request.setAttribute("message", "Create Band success !!!");
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/band/create.jsp");
        dispatcher.forward(request, response);
    }

    //SHOW
    private void showFormCreateBand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/band/create.jsp");
        dispatcher.forward(request, response);

    }

    private void showListBand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Band> bandList = bandService.findAll();
        request.setAttribute("listBand", bandList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/band/list.jsp");
        dispatcher.forward(request, response);
    }
    //EDIT
    private void showFormEditBand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Band band = bandService.findById(id);
        request.setAttribute("band",band);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/band/edit.jsp");
        dispatcher.forward(request, response);
    }
    private void actionEditBand(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Band band = bandService.findById(id);
        String name = request.getParameter("name");
        int year= Integer.parseInt(request.getParameter("year"));
        band.setName(name);
        band.setYear(year);
        bandService.save(band);
        request.setAttribute("message", "Edit success!");
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/band/edit.jsp");
        dispatcher.forward(request, response);
    }



    //DELETE
    private void fromDeleteBand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Band band = bandService.findById(id);
        request.setAttribute("band",band);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/band/delete.jsp");
        dispatcher.forward(request, response);
    }
    private void actionDeleteBand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        bandService.deleteById(id);
        request.setAttribute("message", "Delete success!");
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/band/delete.jsp");
        dispatcher.forward(request, response);

    }
    //SEARCH
    private void actionSearchBand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("search");
        List<Band> bandListSearch = bandService.findByName(name);
        request.setAttribute("listSinger", bandListSearch);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/band/list.jsp");
        dispatcher.forward(request, response);


    }

    //DETAIL
    private void formDetailBand(HttpServletRequest request, HttpServletResponse response) {
    }






}
