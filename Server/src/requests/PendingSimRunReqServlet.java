package requests;

import com.google.gson.Gson;
import ex3DTO.RequestListDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import manager.FilesManager;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "get pending req servlet", urlPatterns = "/get_pending_req")
public class PendingSimRunReqServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        FilesManager manager = (FilesManager) getServletContext().getAttribute("manager");

        RequestListDTO res = manager.getPendingReqList();

        Gson gson = new Gson();
        String json = gson.toJson(res.getRequestDTOList());

        resp.setContentType("application/json");

        try (PrintWriter out = resp.getWriter()) {
            out.print(json);
        }
    }
}
