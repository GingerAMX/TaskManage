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

/**
 * Servlet implementation class TaskContent
 */
@WebServlet("/TaskContent")
public class TaskContent extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TaskContent() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//String taskID = request.getParameter("taskID");
		String userID = "20181210";
		String taskID = "2";

		int val = Integer.parseInt(userID);
		int valLen = String.valueOf(val).length();
		if(valLen == 7){							//ユーザが学生の場合
			ArrayList<dto.TaskContent> result = ManageDAO.taskContent(taskID,valLen);

			request.setAttribute("resultList", result);

			String view = "/WEB-INF/view/TaskContent.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response);
		}else if(valLen == 8){						//ユーザが教員の場合
			ArrayList<dto.TaskContent> result = ManageDAO.taskContent(taskID,valLen);

			request.setAttribute("resultList", result);

			String view = "/WEB-INF/view/TaskContent'.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//String taskID = request.getParameter("taskID");
		//String tID = request.getParameter("tID");
		String tID = "20181214";
		String taskID = "4";

		//課題の削除
		ManageDAO.taskDelete(taskID);

		ArrayList<DistributionIndex> result = ManageDAO.distributionIndex(tID);

		request.setAttribute("resultList", result);

		//教員ページへ戻る
		String view = "/WEB-INF/view/TeacherPage.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}

}