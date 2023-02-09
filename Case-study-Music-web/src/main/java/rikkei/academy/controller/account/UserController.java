package rikkei.academy.controller.account;

import rikkei.academy.model.account.Role;
import rikkei.academy.model.account.RoleName;
import rikkei.academy.model.account.User;
import rikkei.academy.model.like.Like;
import rikkei.academy.model.song.Song;
import rikkei.academy.service.role.IRoleService;
import rikkei.academy.service.role.RoleServiceIMPL;
import rikkei.academy.service.song.ISongService;
import rikkei.academy.service.song.SongServiceIMPL;
import rikkei.academy.service.user.IUserService;
import rikkei.academy.service.user.UserServiceIMPL;

import java.io.*;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(value = "/users")
public class UserController extends HttpServlet {
    private ISongService songService = new SongServiceIMPL();
    private IRoleService roleService = new RoleServiceIMPL();
    private IUserService userService = new UserServiceIMPL();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "register":
                showFormRegister(request, response);
                break;
            case "login":
                showFormLogin(request, response);
                break;
            case "logout":
                logOut(request, response);
                break;
            case "change_avatar":
                showUpLoadAvatar(request, response);
                break;
            case "profile":
                showMyProfile(request, response);
                break;
            case "change_pass":
                showFormChangePass(request, response);
                break;
            case "change_profile":
                showFormChangeProfile(request, response);
                break;
            case "delete_user":
                showFormDelete(request, response);
                break;
            case "change_role":
                showFormChangeRole(request,response);
                break;
            default:
                showListUser(request,response);

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
            case "register":
                actionRegister(request, response);
                break;
            case "login":
                actionLogin(request, response);
                break;
            case "change_avatar":
                actionUpLoadAvatar(request, response);
                break;
            case "change_pass":
                try {
                    actionChangePass(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "change_profile":
                try {
                    actionChangeProfile(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "delete_user":
                actionDelete(request, response);
                break;

        }
    }

    public void destroy() {
    }

    public void showListUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> userList = userService.findAll();
        System.out.println(userList);
        request.setAttribute("list_user",userList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/admin/list_user.jsp");
        requestDispatcher.forward(request,response);
    }
    public void showFormRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/form-login/register.jsp");
        dispatcher.forward(request, response);
    }

    public void actionRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String role = "user";
        Set<String> strRole = new HashSet<>();
        Set<Role> roles = new HashSet<>();
        strRole.add(role);
        strRole.forEach(role1 -> {
            switch (role1) {
                case "admin":
                    Role adminRole = roleService.findByName(RoleName.ADMIN);
                    roles.add(adminRole);
                    break;
                case "pm":
                    Role pmRole = roleService.findByName(RoleName.PM);
                    roles.add(pmRole);
                    break;
                default:
                    Role userRole = roleService.findByName(RoleName.USER);
                    roles.add(userRole);
            }
        });
        System.out.println("roles set ---> " + roles);
        String name = request.getParameter("name");
        if (name=="" ){
            request.setAttribute("message", "Please enter the name");
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/form-login/register.jsp");
            dispatcher.forward(request, response);
        }


        String username = request.getParameter("username");
        if (username=="" ){
            request.setAttribute("message", "Please enter the username");
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/form-login/register.jsp");
            dispatcher.forward(request, response);
        }
        if (userService.existedByUsername(username)) {
            request.setAttribute("message", "The username existed! Please try again!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/form-login/register.jsp");
            dispatcher.forward(request, response);
//            return;
        }
        String email = request.getParameter("email");
        if (email=="" ){
            request.setAttribute("message", "Please enter the email");
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/form-login/register.jsp");
            dispatcher.forward(request, response);
        }
        if (userService.existedByEmail(email)) {
            request.setAttribute("message", "The email existed! Please try again!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/form-login/register.jsp");
            dispatcher.forward(request, response);
//            return;
        }
        String password = request.getParameter("password");
        if (password=="" ){
            request.setAttribute("message", "Please enter the password");
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/form-login/register.jsp");
            dispatcher.forward(request, response);
        }
        String confirm_pass = request.getParameter("repeat_pass");
        if (confirm_pass=="" ){
            request.setAttribute("message", "Please enter the repeat password");
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/form-login/register.jsp");
            dispatcher.forward(request, response);
        }
        if (!password.equals(confirm_pass)) {
            request.setAttribute("message", "The password do not match!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/form-login/register.jsp");
            dispatcher.forward(request, response);
            return;
        }
        User user = new User(name, username, email, password, roles);
        userService.save(user);
        request.setAttribute("success", "Create user success!!");
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/form-login/login.jsp");
        dispatcher.forward(request, response);
    }

    //LOGIN
    public void showFormLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/form-login/login.jsp");
        dispatcher.forward(request, response);

    }

    public void actionLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = userService.findByUsernameAndPassword(username, password);

        String pageJSP = "";
        if (user != null) {

            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            System.out.println("get userlogin ---> " + session.getAttribute("user"));
            pageJSP = "test/index.jsp";

        } else {

            pageJSP = "WEB-INF/form-login/login.jsp";
        }
        int page = 1;
        int recordsPerPage = 5;
        if(request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));
        SongServiceIMPL serviceIMPL = new SongServiceIMPL();
        List<Song> list = serviceIMPL.findAllSongs((page-1)*recordsPerPage,
                recordsPerPage);
        int noOfRecords = serviceIMPL.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        request.setAttribute("song", list);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
        request.setAttribute("message", "Wrong username or password");
        RequestDispatcher dispatcher = request.getRequestDispatcher(pageJSP);
        dispatcher.forward(request, response);
    }

    //LOG OUT
    public void logOut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute("user");
            session.invalidate();
            response.sendRedirect("index.jsp");
        }
    }

    //CHANGE AVATAR
    public void showUpLoadAvatar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/upload/upload_avatar_user.jsp");
        requestDispatcher.forward(request, response);
    }

    public void actionUpLoadAvatar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String avatar = request.getParameter("avatar");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int id = user.getId();
        userService.changeAvatar(avatar, id);
        request.setAttribute("avatar", avatar);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/profile/profile.jsp");
        requestDispatcher.forward(request, response);
    }

    public void showMyProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/profile/profile.jsp");
        requestDispatcher.forward(request, response);
    }

    public void showFormChangePass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/form-login/change_pass.jsp");
        requestDispatcher.forward(request, response);
    }

    public void actionChangePass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        User userLogin = (User) request.getSession().getAttribute("user");

        String oldPass = request.getParameter("old-pass");
        String newPass = request.getParameter("new-pass");
        String repeatPass = request.getParameter("repeat-pass");

        if (!newPass.equals(repeatPass)) {
            request.setAttribute("message", "Repeat password not match");
            request.getRequestDispatcher("WEB-INF/form-login/change_pass.jsp").forward(request, response);
            return;
        }

        if (!userLogin.getPassword().equals(oldPass)) {
            request.setAttribute("message", "Old password not match");
            request.getRequestDispatcher("WEB-INF/form-login/change_pass.jsp").forward(request, response);
            return;
        }
        if (newPass.matches("[A-Z][a-zA-Z]{1,10}")) {
            userLogin.setPassword(newPass);
            userService.update(userLogin);
            request.getSession().setAttribute("user", userLogin);
            request.setAttribute("success", "Change password success");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }


    }

    public void showFormChangeProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/form-login/change_profile.jsp");
        requestDispatcher.forward(request, response);
    }

    public void actionChangeProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        User userLogin = (User) request.getSession().getAttribute("user");

        String oldName = request.getParameter("oldName");
        String newName = request.getParameter("newName");
        String oldEmail = request.getParameter("oldEmail");
        String newEmail = request.getParameter("newEmail");
        if (oldName.equals(newName) || !oldName.equals("name") || oldName=="") {
            request.setAttribute("message", "New name must be different or Old password is not correct  ");
            request.getRequestDispatcher("WEB-INF/form-login/change_profile.jsp").forward(request, response);
            return;
        }
        if (newEmail.equals(oldEmail) || !oldEmail.equals("name") || oldEmail =="") {
            request.setAttribute("message", "New email must be different or Old email is not correct");
            request.getRequestDispatcher("WEB-INF/form-login/change_profile.jsp").forward(request, response);
            return;
        }
        userLogin.setName(newName);
        userLogin.setEmail(newEmail);
        userService.update(userLogin);
        request.getSession().setAttribute("user", userLogin);

        request.setAttribute("success", "Change profile success");
        request.getRequestDispatcher("index.jsp").forward(request, response);

    }

    public void showFormDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        int id = Integer.parseInt(request.getParameter("id"));
//        request.setAttribute("id",id);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/admin/delete_user.jsp");
        requestDispatcher.forward(request, response);
    }

    public void actionDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        userService.deleteById(id);
        request.setAttribute("message", "Delete success!");
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/admin/delete_user.jsp");
        dispatcher.forward(request, response);
    }
    public void showFormChangeRole(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/admin/change_role.jsp");
        requestDispatcher.forward(request, response);
    }
    public void actionChangeRole(HttpServletRequest request, HttpServletResponse response){

    }

}