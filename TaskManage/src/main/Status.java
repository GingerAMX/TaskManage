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
import dto.Submitted;
import dto.UnSubmitted;

/**
 * Servlet implementation class Status
 */
@WebServlet("/Status")
public class Status extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Status() {
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
		String flg = request.getParameter("flg");

		Cookie cookie;
		int task = 0;
		Cookie[] cookies = request.getCookies();//送信されているCookieを取得(Cookieが送信されていなかったらnull)
		//Cookieが送信されていた場合
		if (cookies.length != 1) {
			for (Cookie c : cookies) {
				// taskというcookieがあるか
				if (c.getName().equals("task")) {
					task = Integer.parseInt(c.getValue());
					// 新しくtaskをキーにしてCookieを生成する
					cookie = new Cookie("task", String.valueOf(task));
					// cookieの有効期限を秒で設定(下は90日)
					cookie.setMaxAge(60 * 60 * 24 * 90);
					// レスポンスヘッダーにcookieを詰める
					response.addCookie(cookie);
					break;
				}
			}
		}
		String taskID = Integer.toString(task);

		//課題の情報の取得
		ArrayList<dto.TaskContent> result = ManageDAO.taskContent(taskID,8);
		request.setAttribute("resultList", result);

		dto.TaskContent mid = (dto.TaskContent)result.get(0);
		String cID = Integer.toString(mid.getcID());

		//提出者の取得
		ArrayList<Submitted> submitted = ManageDAO.submitted(taskID);
		request.setAttribute("submitted", submitted);

		//未提出者の取得
		ArrayList<UnSubmitted> unSubmitted = ManageDAO.unSubmitted(cID,taskID);
		request.setAttribute("unSubmitted", unSubmitted);

		request.setAttribute("flg", flg);

		String view = "/WEB-INF/view/Status.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}
}
