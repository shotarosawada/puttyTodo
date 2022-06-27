package servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DTO;
import model.Query;
import model.User;

@WebServlet("/Controller_todo")
public class Controller_todo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		User registerUser = (User) session.getAttribute("registerUser");

		// タスク情報の準備
		Integer userId = 0;
		String content = "";
		Boolean status = false; //タスク完了のフラグ true⇒完了、false⇒未完了
				
		userId = registerUser.getId();
		content = request.getParameter("content");
		String strDeadline = request.getParameter("deadline");
		Date deadline = Date.valueOf(strDeadline);
		
		// タスクの挿入
		Query.insertTaskInfo(userId, content, status, deadline);
		
		//セッションスコープに登録するタスク一覧の情報を取得
		ArrayList<DTO> registerTask = Query.getTaskInfo(userId);
		
		session.setAttribute("registerTask", registerTask);
		
		//フォワード先の設定
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/displayTodo.jsp");
		dispatcher.forward(request, response);
	}
	
}