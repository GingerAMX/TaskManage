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
		String taskID = request.getParameter("taskID");
		String tID = request.getParameter("tID");
		String[] key = new String[3];
		Cookie cookie = null;// Cookie変数を宣言

		//nullで更新されることを避けるため
		if(tID != null){
			// 新しくCookieを生成
			cookie = new Cookie("teacher", tID);
			cookie.setMaxAge(60 * 60 * 24 * 90);
			response.addCookie(cookie);
		}
		if(taskID != null){
			cookie = new Cookie("task", taskID);
			cookie.setMaxAge(60 * 60 * 24 * 90);
			response.addCookie(cookie);
		}

		Cookie[] cookies = request.getCookies();//送信されているCookieを取得(Cookieが送信されていなかったらnull)
		//Cookieが送信されていた場合
		int id = 0;
		if (cookies.length != 0) {
			for (Cookie c : cookies) {
				// idというcookieがあるか
				if (c.getName().equals("id")) {
					id = Integer.parseInt(c.getValue());
					// 新しくidをキーにしてCookieを生成する
					cookie = new Cookie("id", String.valueOf(id));
					// cookieの有効期限を秒で設定(下は90日)
					cookie.setMaxAge(60 * 60 * 24 * 90);
					// レスポンスヘッダーにcookieを詰める
					response.addCookie(cookie);
					break;
				}
			}
		}

		String pass = null;
		cookies = request.getCookies();//送信されているCookieを取得(Cookieが送信されていなかったらnull)
		//Cookieが送信されていた場合
		if (cookies.length != 0) {
			for (Cookie c : cookies) {
				// passというcookieがあるか
				if (c.getName().equals("pass")) {
					pass = c.getValue();
					// 新しくpassをキーにしてCookieを生成する
					cookie = new Cookie("pass", pass);
					// cookieの有効期限を秒で設定(下は90日)
					cookie.setMaxAge(60 * 60 * 24 * 90);
					// レスポンスヘッダーにcookieを詰める
					response.addCookie(cookie);
					break;
				}
			}
		}
		cookies = request.getCookies();//送信されているCookieを取得(Cookieが送信されていなかったらnull)
		//Cookieが送信されていた場合
		if(taskID == null){
			if (cookies.length != 0) {
				for (Cookie c : cookies) {
					// taskというcookieがあるか
					if (c.getName().equals("task")) {
						taskID = c.getValue();
						System.out.println(taskID);
						// 新しくtaskをキーにしてCookieを生成する
						cookie = new Cookie("task", taskID);
						// cookieの有効期限を秒で設定(下は90日)
						cookie.setMaxAge(60 * 60 * 24 * 90);
						// レスポンスヘッダーにcookieを詰める
						response.addCookie(cookie);
						break;
					}
				}
			}
		}

		String ID = Integer.toString(id);
		key[0] = ID;
		key[1] = taskID;
		key[2] = pass;

		if(id != 0){
			int valLen = String.valueOf(id).length();
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