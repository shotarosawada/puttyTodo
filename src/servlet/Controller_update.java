package servlet;

import java.io.IOException;
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

@WebServlet("/Controller_update")
public class Controller_update extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		User registerUser = (User) session.getAttribute("registerUser");	

		int userId = 0;
		int taskId = 0;
				
		userId = registerUser.getId();
		String taskId_str = request.getParameter("taskId");
		taskId = Integer.parseInt(taskId_str);
		
//		デバッグ用
//		System.out.println(taskId);
		
		Query.switchTaskStatus(taskId);
		
		//セッションスコープに登録するタスク一覧の情報を取得
		ArrayList<DTO> registerTask = Query.getTaskInfo(userId);
		
		session.setAttribute("registerTask", registerTask);
		
		//フォワード先の設定
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/displayTodo.jsp");
		dispatcher.forward(request, response);
	}
	
}