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

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String userID = request.getParameter("userID");
		String taskID = request.getParameter("taskID");
		String tID = request.getParameter("tID");
		String[] key = new String[2];
		key[0] = userID;
		key[1] = taskID;

		if(userID != null){
			int valLen = String.valueOf(userID).length();
			if(valLen == 7){							//ユーザが学生の場合
				ArrayList<dto.TaskContent> result = ManageDAO.taskContent(taskID,valLen);

				request.setAttribute("resultList", result);
				request.setAttribute("key", key);

				String view = "/WEB-INF/view/TaskContent.jsp";
				RequestDispatcher dispatcher = request.getRequestDispatcher(view);
				dispatcher.forward(request, response);
			}else if(valLen == 8){						//ユーザが教員の場合
				ArrayList<dto.TaskContent> result = ManageDAO.taskContent(taskID,valLen);

				request.setAttribute("resultList", result);
				request.setAttribute("key", key);

				String view = "/WEB-INF/view/TaskContent'.jsp";
				RequestDispatcher dispatcher = request.getRequestDispatcher(view);
				dispatcher.forward(request, response);
			}
		}

		//課題の削除
		if(taskID != null && tID != null){
			ManageDAO.taskDelete(taskID);

			ArrayList<DistributionIndex> result = ManageDAO.distributionIndex(tID);

			request.setAttribute("resultList", result);

			//教員ページへ戻る
			String view = "/WEB-INF/view/TeacherPage.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response);
		}
	}

}
