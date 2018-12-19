
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
import dto.DistributionIndex;
import dto.Manager;

/**
 * Servlet implementation class ManagerPage
 */
@WebServlet("/ManagerPage")
public class ManagerPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//String tID = request.getParameter("tID");
		String pass = request.getParameter("pass");
		String tID = "1";

		//管理者承認
		ArrayList<Manager> user = ManageDAO.mLogin(tID,pass);
		request.setAttribute("manager", user);

		//課題一覧の表示
		ArrayList<DistributionIndex> result = ManageDAO.distributionIndex(tID);
		request.setAttribute("resultList", result);

		String view = "/WEB-INF/view/ManagerPage.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}

}