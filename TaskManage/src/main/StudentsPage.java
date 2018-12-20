package main;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ManageDAO;
import dto.TaskIndex;

/**
 * Servlet implementation class StudentsPage
 */
@WebServlet("/StudentsPage")
public class StudentsPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentsPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//String cID = request.getParameter("cID");
		String cID = "1";

		ArrayList<TaskIndex> result = ManageDAO.taskIndex(cID);

		request.setAttribute("resultList", result);

		String view = "/WEB-INF/view/StudentsPage.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String ID = request.getParameter("ID");
		String pass = request.getParameter("pass");

		// 存在しなければnullとなる
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			System.out.println(cookie.getName() + ":" + cookie.getValue());
		}

		//ログイン処理
		ArrayList<dto.Login> result = ManageDAO.login(ID,pass);

		dto.Login mid = (dto.Login)result.get(0);
		int id = mid.getId();
		int idLen = String.valueOf(id).length();

		String cID = Integer.toString(mid.getcID());

		ArrayList<TaskIndex> resultList = ManageDAO.taskIndex(cID);

		request.setAttribute("resultList", resultList);
		request.setAttribute("key", ID);

		String view = "/WEB-INF/view/StudentsPage.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}

}
