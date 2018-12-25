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
		String ID = request.getParameter("ID");
		String pass = request.getParameter("pass");
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

		if(pass == null){
			pass = null;
			cookies = request.getCookies();//送信されているCookieを取得(Cookieが送信されていなかったらnull)
			//Cookieが送信されていた場合
			if (cookies.length != 0) {
				for (Cookie c : cookies) {
					// classというcookieがあるか
					if (c.getName().equals("class")) {
						cID = c.getValue();
						// 新しくclassをキーにしてCookieを生成する
						cookie = new Cookie("class", cID);
						// cookieの有効期限を秒で設定(下は90日)
						cookie.setMaxAge(60 * 60 * 24 * 90);
						// レスポンスヘッダーにcookieを詰める
						response.addCookie(cookie);
						break;
					}
				}
			}
		}

		if(ID != null && pass != null){
			//ログイン処理
			ArrayList<dto.Login> result = ManageDAO.login(ID,pass);

			dto.Login mid = (dto.Login)result.get(0);
			int cid = mid.getId();
			int cidLen = String.valueOf(cid).length();

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
