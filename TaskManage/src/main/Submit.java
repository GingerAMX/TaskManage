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

import dao.ManageDAO;

/**
 * Servlet implementation class Submit
 */
@WebServlet("/Submit")
@MultipartConfig(location="c:/tmp", maxFileSize=1048576)
public class Submit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String view = "/WEB-INF/view/Submit.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String taskID = request.getParameter("taskID");
		String sID = request.getParameter("userID");

		String cID = ManageDAO.key(sID);

		String[] key = new String[3];
		key[0] = taskID;
		key[1] = sID;

		request.setAttribute("key", key);

		//ファイルパスの取得
		Part part = request.getPart("file");
		if(part != null){
	    	String name = this.getFileName(part);
	    	System.out.println(getServletContext().getRealPath("/WEB-INF/uploaded") + "/" + name);

	    	//ファイルの保存 パス変更箇所(サーバ接続時)
	        try {
	        part.write(getServletContext().getRealPath("/WEB-INF/uploaded") + "\\" + name);

	        }
	        catch( IOException e){
	        	System.out.println("ファイル出力error");
	        	e.printStackTrace();
	        }
	        String textPath = getServletContext().getRealPath("/WEB-INF/uploaded") + "\\" + name;

	        //DAOにデータの送信
	        ManageDAO.submit(taskID,cID,sID,textPath);
		}

        //jspファイルへ
        String view = "/WEB-INF/view/Submit.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
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
