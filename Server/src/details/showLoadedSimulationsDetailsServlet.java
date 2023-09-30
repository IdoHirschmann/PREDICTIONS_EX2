package details;

import com.google.gson.Gson;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import manager.FilesManager;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Get simulations name servlet", urlPatterns = "/get_simulations_name")
public class showLoadedSimulationsDetailsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        FilesManager manager = (FilesManager) getServletContext().getAttribute("manager");

        Gson gson = new Gson();
        String json = gson.toJson(manager.getSimulationNameDtoList());

        resp.setContentType("application/json");

        try (PrintWriter out = resp.getWriter()) {
            out.print(json);
        }
    }
}
