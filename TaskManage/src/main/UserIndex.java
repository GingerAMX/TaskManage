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
 * Servlet implementation class UserIndex
 */
@WebServlet("/UserIndex")
public class UserIndex extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserIndex() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String view = "/WEB-INF/view/UserIndex.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String[] userID = request.getParameterValues("userID");
		String grade = request.getParameter("grade");
		String cName = request.getParameter("cName");
		String teacher = request.getParameter("teacher");
		//String userID = "1111111";
//		String grade = "";
//		String cName = "";

		System.out.println(teacher);
		System.out.println(grade + cName);
		if(userID != null){
			ManageDAO.userDelete(userID);
		}
		if(grade != null && cName != null && teacher == null){
			System.out.println("2");
			ArrayList<dto.UserIndex> result = ManageDAO.userIndex(grade,cName);
			request.setAttribute("resultList", result);
		} else if(teacher != null) {
			System.out.println("3");
			ArrayList<dto.UserIndex> result = ManageDAO.userIndex(grade,cName);
			request.setAttribute("resultList", result);
		}

		String view = "/WEB-INF/view/UserIndex.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}

}
