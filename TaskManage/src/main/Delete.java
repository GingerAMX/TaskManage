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
 * Servlet implementation class Delete
 */
@WebServlet("/Delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Delete() {
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

		if(taskID != null && ID != null){		//課題の削除
			System.out.println("課題削除");
			ManageDAO.taskDelete(taskID);

			ArrayList<DistributionIndex> result = ManageDAO.distributionIndex(ID);

			request.setAttribute("resultList", result);

			//教員ページへ戻る
			if(pass != null){
				ArrayList<DistributionIndex> resultList = ManageDAO.distributionIndex(ID);

				request.setAttribute("resultList", resultList);
				request.setAttribute("key", ID);

				String view = "/WEB-INF/view/TeacherPage.jsp";
				RequestDispatcher dispatcher = request.getRequestDispatcher(view);
				dispatcher.forward(request, response);
			}else if(pass == null){
				ArrayList<DistributionIndex> resultList = ManageDAO.distributionIndex(ID);

				request.setAttribute("resultList", resultList);
				request.setAttribute("key", ID);

				String view = "/WEB-INF/view/ManagerPage.jsp";
				RequestDispatcher dispatcher = request.getRequestDispatcher(view);
				dispatcher.forward(request, response);
			}
		}
	}

}
