package requests;


import com.google.gson.Gson;
import ex3DTO.NewRequestDTO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import manager.FilesManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@WebServlet(name = "new sim run servlet", urlPatterns = "/new_sim_run")
public class NewSimulationRunReqServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        FilesManager manager = (FilesManager) getServletContext().getAttribute("manager");

        Gson gson = new Gson();
        StringBuilder requestBody = new StringBuilder();
        String line;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream()))) {
            while ((line = reader.readLine()) != null) {
                requestBody.append(line);
            }
        } catch (IOException e) {
        }

        // Print or process the request body as needed
        String json = requestBody.toString();

        NewRequestDTO newRequestDTO = gson.fromJson(json , NewRequestDTO.class);

        manager.addSimulationRunReq(newRequestDTO);

    }
}
