package servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

@WebServlet("/Controller_todo")
public class Controller_todo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		User registerUser = (User) session.getAttribute("registerUser");	

		// パラメータ初期化
		Integer userId = 0;
		String userName = "";
		String content = "";
//		Date deadline
				
		userId = registerUser.getId();
		userName = registerUser.getName();
		content = request.getParameter("content");
		
		String strDeadline = request.getParameter("deadline");
		SimpleDateFormat sqlDateParser = new SimpleDateFormat("yyyy-mm-dd");
		Date deadline_UtilDate = null;
		try {
			deadline_UtilDate = sqlDateParser.parse(strDeadline);
		} catch (ParseException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		long deadline_timeInMilliSeconds = deadline_UtilDate.getTime();
		
		java.sql.Date deadline = new java.sql.Date(deadline_timeInMilliSeconds);
		
		if(content != "" && deadline != null) {
			Map<Integer,Object >userInfo = Query.insertTaskInfo(userId, userName, content, deadline);
		}
		
		
		//フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/displayTodo.jsp");
		dispatcher.forward(request, response);
	}
	
}