package main;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ManageDAO;

/**
 * Servlet implementation class Authority
 */
@WebServlet("/Authority")
public class Authority extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Authority() {
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
		Boolean flg = false;
		String tID = request.getParameter("tID");
		String mPass = request.getParameter("mPass");
		String takeover = request.getParameter("takeover");
		if(takeover != null){
			flg = Boolean.valueOf(takeover);
		}

		Cookie cookie = null;
		if(tID != null){
			cookie = new Cookie("teacher", tID);
			cookie.setMaxAge(60 * 60 * 24 * 90);
			response.addCookie(cookie);
			System.out.println(tID + "残念！！");
		}

		int id = 0;
		Cookie[] cookies = request.getCookies();//送信されているCookieを取得(Cookieが送信されていなかったらnull)
		//Cookieが送信されていた場合
		if("".equals(tID)){
			if (cookies.length != 0) {
				for (Cookie c : cookies) {
					// teacherというcookieがあるか
					if (c.getName().equals("teacher")) {
						id = Integer.parseInt(c.getValue());
						System.out.println(id);
						// 新しくteacherをキーにしてCookieを生成する
						cookie = new Cookie("teacher", String.valueOf(id));
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

		if(ID != null && mPass != null){				//権限の付与
			ManageDAO.grant(ID,mPass);

			String view = "/WEB-INF/view/UserIndex.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response);

		}else if(tID != null){							//権限の確認
			String[] list = ManageDAO.authority(tID);
			request.setAttribute("list", list);

			String view = "/WEB-INF/view/Authority.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response);

		}else if(flg = true){
			ManageDAO.takeOver(ID);
			System.out.println(ID + "です");

			String view = "/WEB-INF/view/UserIndex.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response);
		}

	}

}
