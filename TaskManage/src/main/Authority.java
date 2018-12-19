package main;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
		String tID = request.getParameter("tID");
		String mPass = request.getParameter("mPass");
		String takeover = request.getParameter("takeover");

		if(tID != null && mPass != null){				//権限の付与
			ManageDAO.grant(tID,mPass);

			String view = "/WEB-INF/view/UserIndex.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response);

		}else if(tID != null){							//権限の確認
			String[] list = ManageDAO.authority(tID);
			request.setAttribute("list", list);

			String view = "/WEB-INF/view/Authority.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response);

		}else if(takeover != null){
			ManageDAO.takeOver(takeover);

			String view = "/WEB-INF/view/UserIndex.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response);
		}

	}

}