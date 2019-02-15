package main;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ManageDAO;

/**
 * Servlet implementation class Download
 */
@WebServlet("/Download")
public class Download extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Download() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String flg = request.getParameter("flg");

		request.setAttribute("flg", flg);

		String view = "/WEB-INF/view/Download.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String taskName = request.getParameter("taskName");
		String grade = request.getParameter("grade");
		String cName = request.getParameter("class");
		String flg = request.getParameter("flg");

		//ここに新規
		if("".equals(grade) || "".equals(cName) || "".equals(taskName)){
			request.setAttribute("message", "⚠未入力の項目があります⚠");
			request.setAttribute("flg", flg);
		}else {
			ArrayList<dto.Download> result = ManageDAO.download(taskName, grade, cName);
			request.setAttribute("flg", flg);
			request.setAttribute("result", result);
		}

		String view = "/WEB-INF/view/Download.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}

}
