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
		String flg = request.getParameter("flg");
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

		if("student".equals(flg)){		//学生の登録
			//学生の登録
			int result = ManageDAO.sRegister(sID,sPass,sName,grade,cName);
			if(result == 1){			//入力した値にすべて問題がなかった場合
				String view = "/WEB-INF/view/Login.jsp";
				RequestDispatcher dispatcher = request.getRequestDispatcher(view);
				dispatcher.forward(request, response);
			}else if(result == 0){		//入力した値に不備があった場合
				request.setAttribute("message", "⚠重複するID または 入力されていない項目があります⚠");
				String view = "/WEB-INF/view/Register.jsp";
				RequestDispatcher dispatcher = request.getRequestDispatcher(view);
				dispatcher.forward(request, response);
			}else if(result == 2){		//IDの入力値が7桁ではなかった場合
				request.setAttribute("message", "⚠学籍番号の7桁で登録してください⚠");
				String view = "/WEB-INF/view/Register.jsp";
				RequestDispatcher dispatcher = request.getRequestDispatcher(view);
				dispatcher.forward(request, response);
			}

		}else if("teacher".equals(flg)) {		//教員の登録
			int result = ManageDAO.tRegister(tID,tName,tPass);
			if(result == 1){					//入力した値にすべて問題がなかった場合
				String view = "/WEB-INF/view/Login.jsp";
				RequestDispatcher dispatcher = request.getRequestDispatcher(view);
				dispatcher.forward(request, response);
			}else if(result == 0){				//入力した値に不備があった場合
				request.setAttribute("message", "⚠重複するID または 入力されていない項目があります⚠");
				String view = "/WEB-INF/view/Register.jsp";
				RequestDispatcher dispatcher = request.getRequestDispatcher(view);
				dispatcher.forward(request, response);
			}else if(result == 2){				//IDの入力値が7桁ではなかった場合
				request.setAttribute("message", "⚠8桁の数字で登録してください⚠");
				String view = "/WEB-INF/view/Register.jsp";
				RequestDispatcher dispatcher = request.getRequestDispatcher(view);
				dispatcher.forward(request, response);
			}
		}
	}

}
