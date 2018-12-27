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
		String pass = request.getParameter("pass");

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
		}

		cookie = new Cookie("pass", pass);
		cookie.setMaxAge(60 * 60 * 24 * 90);
		response.addCookie(cookie);

		String ID = Integer.toString(id);

		//管理者承認
		ArrayList<Manager> user = ManageDAO.mLogin(ID,pass);
		request.setAttribute("manager", user);

		//課題一覧の表示
		ArrayList<DistributionIndex> result = ManageDAO.distributionIndex(ID);
		request.setAttribute("resultList", result);

		ArrayList<DistributionIndex> resultList = ManageDAO.distributionIndex(ID);
		request.setAttribute("resultList", resultList);
		request.setAttribute("key", ID);

		String view = "/WEB-INF/view/ManagerPage.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}

}
