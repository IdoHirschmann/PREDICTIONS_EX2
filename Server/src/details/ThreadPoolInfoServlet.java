package details;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import manager.FilesManager;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Get num of threads servlet", urlPatterns = "/get_num_of_thread")

public class ThreadPoolInfoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        FilesManager manager = (FilesManager) getServletContext().getAttribute("manager");
        resp.setContentType("text/plain");

        Gson gson = new Gson();
        String json = gson.toJson(manager.getQueueInfo());

        resp.setContentType("application/json");

        try (PrintWriter out = resp.getWriter()) {
            out.print(json);
        }
    }
}
