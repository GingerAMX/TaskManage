package main;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.Manager;

import dao.ManageDAO;
import dto.DistributionIndex;

/**
 * Servlet implementation class TeacherPage
 */
@WebServlet("/TeacherPage")
public class TeacherPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeacherPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//String classs = request.getParameter("classs");
		String tID = "00000001";

		ArrayList<DistributionIndex> result = ManageDAO.distributionIndex(tID);

		request.setAttribute("resultList", result);

		String view = "/WEB-INF/view/TeacherPage.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String tID = request.getParameter("tID");
		String pass = request.getParameter("pass");

		ArrayList<Manager> user = ManageDAO.mLogin(tID,pass);

		request.setAttribute("manager", user);

		String view = "/WEB-INF/view/.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}

}
