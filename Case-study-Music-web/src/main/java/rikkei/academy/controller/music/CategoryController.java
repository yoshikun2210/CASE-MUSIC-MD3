package rikkei.academy.controller.music;

import rikkei.academy.model.music.Category;
import rikkei.academy.service.category.CategoryServiceIMPL;
import rikkei.academy.service.category.ICategoryService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet( value ="/category")
public class CategoryController extends HttpServlet {
    private ICategoryService categoryService = new CategoryServiceIMPL();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String action = request.getParameter("action");
        if(action==null){
            action = "";
        }
        switch (action){
            case "create":
                showCategory(request, response);
                break;
            case "edit":
                showFormEdit(request, response);
                break;
            case "delete":
                showFormDeleteCategory(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String action = request.getParameter("action");
        if(action==null){
            action = "";
        }
        switch (action){
            case "create":
                actionCreateCategory(request, response);
                break;
            case "edit":
                actionEditCategory(request, response);
                break;
            case "delete":
                actionDeleteCategory(request, response);
                break;
        }
    }
    public void showCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categories = categoryService.findAll();
        request.setAttribute("category", categories);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/music/category.jsp");
        dispatcher.forward(request, response);
    }
    public void actionCreateCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        Category category = new Category(name);
        categoryService.save(category);
        request.setAttribute("message", "Create Successfully!");
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/music/category.jsp");
        dispatcher.forward(request, response);
    }
    public void showFormEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Category category = categoryService.findById(id);
        request.setAttribute("category", category);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/music/editcategory.jsp");
        dispatcher.forward(request, response);
    }
    public void actionEditCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Category category = categoryService.findById(id);
        String name = request.getParameter("name");
        category.setName(name);
        categoryService.save(category);
        request.setAttribute("message", "Edit Successfully");
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/music/editcategory.jsp");
        dispatcher.forward(request, response);
    }
    public void showFormDeleteCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Category category = categoryService.findById(id);
        request.setAttribute("category", category);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/music/delete.jsp");
        dispatcher.forward(request, response);
    }
    public void actionDeleteCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        categoryService.deleteById(id);
        request.setAttribute("message", "Delete Successfully");
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/music/category.jsp");
        dispatcher.forward(request, response);
    }
    public void showFormDetailCategory(HttpServletRequest request, HttpServletResponse response){
    }
}
