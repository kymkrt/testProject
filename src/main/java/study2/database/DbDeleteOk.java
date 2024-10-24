package study2.database;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/study2/database/DbDeleteOk")
public class DbDeleteOk extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx = request.getParameter("idx")==null || request.getParameter("idx").equals("") ? 0 : Integer.parseInt(request.getParameter("idx"));
		
		String viewPage = "";
		
		DbTestDAO dao = new DbTestDAO();
		int res = dao.setDbTestDeleteOk(idx);
		
		if(res==1) {
			request.setAttribute("msg", "정보 삭제에 성공하였습니다");
			request.setAttribute(viewPage, "/include/message.jsp");
		}
		else if(res ==0) {
			request.setAttribute("msg", "정보 삭제에 실패하였습니다");
			request.setAttribute(viewPage, "/include/message.jsp");
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
		
	}
}
