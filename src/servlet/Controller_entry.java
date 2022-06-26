package servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Query;
import model.User;

@WebServlet("/Controller_entry")
public class Controller_entry extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//フォワード先
		String forwardPath = "/WEB-INF/jsp/index.jsp";
		
		//設定されたフォワード先へ遷移
		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String addName = request.getParameter("name");
		
		Map<Integer, String>userInfo = Query.insertUserInfo(addName);
		System.out.println(userInfo);
		
		Integer userId = 0;
		String userName = "";

		for(Map.Entry<Integer, String> entry : userInfo.entrySet()) {
			userId = entry.getKey();
			userName = entry.getValue();
		}
		
		//登録するユーザーの情報を設定
		User registerUser = new User(userId, userName);
		
		//セッションスコープに登録ユーザーを保存
		HttpSession session = request.getSession();
		session.setAttribute("registerUser", registerUser);
		
		//フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/insertTodo.jsp");
		dispatcher.forward(request, response);
	}
	
}