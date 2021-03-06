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
import dto.TaskIndex;
/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//cookieの削除
		Cookie cookies[] = request.getCookies();
        for ( Cookie cookie : cookies) {
                if ("id".equals(cookie)) {					//id
                        cookie.setMaxAge(0);
                        cookie.setValue("");
                        cookie.setPath("/");
                        response.addCookie(cookie);
                }
        }

        for ( Cookie cookie : cookies) {
            if ("pass".equals(cookie)) {					//pass
                    cookie.setMaxAge(0);
                    cookie.setValue("");
                    cookie.setPath("/");
                    response.addCookie(cookie);
            }
        }
        for ( Cookie cookie : cookies) {
            if ("task".equals(cookie)) {					//task
                    cookie.setMaxAge(0);
                    cookie.setValue("");
                    cookie.setPath("/");
                    response.addCookie(cookie);
            }
        }
        for ( Cookie cookie : cookies) {
            if ("teacher".equals(cookie)) {					//teacher
                    cookie.setMaxAge(0);
                    cookie.setValue("");
                    cookie.setPath("/");
                    response.addCookie(cookie);
            }
        }
		request.setCharacterEncoding("UTF-8");
		String view = "/WEB-INF/view/Login.jsp";
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

		Cookie cookie = null;// Cookie変数を宣言
		// 新しくCookieを生成
		cookie = new Cookie("id", ID);
		cookie.setMaxAge(60 * 60 * 24 * 90);
		response.addCookie(cookie);

		if("".equals(ID) || "".equals(pass)){
			request.setAttribute("message", "⚠ID または パスワードが未入力です⚠");

			String view = "/WEB-INF/view/Login.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response);
		}

		//ログイン処理
		ArrayList<dto.Login> result = ManageDAO.login(ID,pass);

		if(result.size() == 0){
			request.setAttribute("message", "⚠ID または パスワードが違います⚠");

			String view = "/WEB-INF/view/Login.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response);
		}

		dto.Login mid = (dto.Login)result.get(0);
		int id = mid.getId();
		int idLen = String.valueOf(id).length();

		if(result != null && idLen == 7){				//学生の場合
			cookie = new Cookie("pass", pass);
			cookie.setMaxAge(60 * 60 * 24 * 90);
			response.addCookie(cookie);

			String cID = Integer.toString(mid.getcID());

			ArrayList<TaskIndex> resultList = ManageDAO.taskIndex(cID);

			request.setAttribute("resultList", resultList);
			request.setAttribute("key", ID);

			String view = "/WEB-INF/view/StudentsPage.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response);

		}else if(result != null && idLen == 8){		//教員の場合
			String tID = Integer.toString(id);

			ArrayList<DistributionIndex> resultList = ManageDAO.distributionIndex(tID);

			request.setAttribute("resultList", resultList);
			request.setAttribute("key", ID);

			String view = "/WEB-INF/view/TeacherPage.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response);
		}
	}

}