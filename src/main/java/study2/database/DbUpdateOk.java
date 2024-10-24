package study2.database;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/study2/database/DbUpdateOk")
public class DbUpdateOk extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int idx = request.getParameter("idx")==null || request.getParameter("idx").equals("") ? 0 : Integer.parseInt(request.getParameter("idx"));
		String viewPage = "";
		
		int res = 0;
		
		DbTestVO vo = new DbTestVO();
		
		
		DbTestDAO dao = new DbTestDAO();
		res = dao.setDbTestUpdateOk(vo);
		
		
		if(res==1) {
			request.setAttribute("msg", "삭제에 성공하였습니다");
			request.setAttribute("url", "/WEB-INF/study2/database/dbList.jsp");
		}
		else if(res==0) {
			request.setAttribute("msg", "삭제에 실패하였습니다");
			request.setAttribute("url", "/WEB-INF/study2/database/dbUpdate.jsp?="+idx);
		}
		
		viewPage = "/include/message.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
	
}
