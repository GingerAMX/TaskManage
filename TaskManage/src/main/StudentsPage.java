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
		String ID = null;
		String pass = null;
		String cID = null;

		int id=0;
		Cookie cookie;
		Cookie[] cookies = request.getCookies();//送信されているCookieを取得(Cookieが送信されていなかったらnull)
		//Cookieが送信されていた場合
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
		ID = Integer.toString(id);

		if(ID != null && pass != null){
			//ログイン処理
			ArrayList<dto.Login> result = ManageDAO.login(ID,pass);

			dto.Login mid = (dto.Login)result.get(0);

			cID = Integer.toString(mid.getcID());

			cookie = new Cookie("class", cID);
			cookie.setMaxAge(60 * 60 * 24 * 90);
			response.addCookie(cookie);
		}

		ArrayList<TaskIndex> resultList = ManageDAO.taskIndex(cID);

		request.setAttribute("resultList", resultList);
		request.setAttribute("key", ID);

		String view = "/WEB-INF/view/StudentsPage.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}

}
