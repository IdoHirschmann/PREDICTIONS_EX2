package requests;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import manager.FilesManager;

import java.io.IOException;

@WebServlet(name = "approve sim req servlet", urlPatterns = "/approve_req")
public class ApproveReqServlet extends HttpServlet {
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        FilesManager manager = (FilesManager) getServletContext().getAttribute("manager");
        manager.getReq(Integer.parseInt(req.getParameter("id"))).approve();
    }
}
