
package main;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ManageDAO;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String view = "/WEB-INF/view/Register.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//学生
		String sID = request.getParameter("sID");
		String sName = request.getParameter("sName");
		String sPass = request.getParameter("sPass");
		String grade = request.getParameter("grade");
		String cName = request.getParameter("cName");
		//教員
		String tID = request.getParameter("tID");
		String tName = request.getParameter("tName");
		String tPass = request.getParameter("tPass");

		if(sID != null){
			//学生の登録
			int result = ManageDAO.sRegister(sID,sPass,sName,grade,cName);
		}else if(tID != null) {
			int result = ManageDAO.tRegister(tID,tName,tPass);
		}

//		String view = "/WEB-INF/view/Login.jsp";
//		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
//		dispatcher.forward(request, response);
	}

}