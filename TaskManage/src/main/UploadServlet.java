package main;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/UploadServlet")
@MultipartConfig(location="c:/tmp", maxFileSize=1048576)
public class UploadServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String view = "/WEB-INF/view/UploadServlet.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	Part part = request.getPart("file");
    	String name = this.getFileName(part);
    	System.out.println(getServletContext().getRealPath("/WEB-INF/uploaded") + "/" + name);
        try {
        part.write(getServletContext().getRealPath("/WEB-INF/uploaded") + "\\" + name);

        }
        catch( IOException e){
        	System.out.println("ファイル出力error");
        	e.printStackTrace();
        }
        String view = "/WEB-INF/view/UploadServlet.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
        //response.sendRedirect("/WEB-INF/view/UploadServlet.jsp");
    }

    private String getFileName(Part part) {
        String name = null;
        for (String dispotion : part.getHeader("Content-Disposition").split(";")) {
            if (dispotion.trim().startsWith("filename")) {
                name = dispotion.substring(dispotion.indexOf("=") + 1).replace("\"", "").trim();
                name = name.substring(name.lastIndexOf("\\") + 1);
                break;
            }
        }
        return name;
    }
}
