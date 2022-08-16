package ua.com.epam.library.servlet.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.com.epam.library.entity.User;
import ua.com.epam.library.service.ServiceFactory;
import ua.com.epam.library.service.UserService;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/user/updateProfile")
public class UpdateProfileServlet extends HttpServlet {

    UserService userService = ServiceFactory.getInstance().getUserService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");

        HttpSession session = request.getSession();
        Optional<User> customer = userService.findUserByIdAndPassword(id, password);
        if (customer.isPresent()) {
            boolean updateProfile = userService.updateProfile(id, name, email, phone);
            if (updateProfile) {
                session.setAttribute("successMessage", "Profile updated Successfully...");
            } else {
                session.setAttribute("failedMessage", "Something go wrong!");
            }
        } else {
            session.setAttribute("failedMessage", "Your Password is incorrect");
        }
        getServletContext().getRequestDispatcher("/user/editProfile.jsp").forward(request, response);
    }
}
