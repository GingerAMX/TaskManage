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
 * Servlet implementation class TeacherPage
 */
@WebServlet("/TeacherPage")
public class TeacherPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeacherPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//String tID = request.getParameter("tID");
		String tID = "20181214";

		ArrayList<DistributionIndex> result = ManageDAO.distributionIndex(tID);

		request.setAttribute("resultList", result);

		String view = "/WEB-INF/view/TeacherPage.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

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
		String tID = Integer.toString(id);

		ArrayList<DistributionIndex> resultList = ManageDAO.distributionIndex(tID);

		request.setAttribute("resultList", resultList);
		request.setAttribute("key", tID);

		String view = "/WEB-INF/view/TeacherPage.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}

}
