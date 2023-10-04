package login;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import manager.FilesManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

@WebServlet(name = "user login servlet", urlPatterns = "/user_login")
@MultipartConfig
public class UserLoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        FilesManager manager = (FilesManager) getServletContext().getAttribute("manager");

        Part userNamePart = req.getPart("name");
        InputStream inputStream = userNamePart.getInputStream();
        Scanner scanner = new Scanner(inputStream, "UTF-8").useDelimiter("\\A");
        String userName = scanner.next();


        if(!manager.isUserExist(userName)){
            manager.addUser(userName);
        }else {
            String errorMessage = "Error processing the request: the user " + userName + " is already registered to the system!";

            // Set the response status code to 400 (Bad Request)
            resp.setStatus(400);

            // Set the response content type to plain text
            resp.setContentType("text/plain");

            // Send the error message as the response body
            resp.getWriter().write(errorMessage);
        }
    }
}
