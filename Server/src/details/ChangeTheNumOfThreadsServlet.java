package details;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import manager.FilesManager;

import java.io.IOException;

@WebServlet(name = "Set num of threads servlet", urlPatterns = "/set_num_of_thread")
public class ChangeTheNumOfThreadsServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        FilesManager manager = (FilesManager) getServletContext().getAttribute("manager");

        manager.changeThreadNumber(Integer.parseInt(req.getParameter("num")));
    }
}
