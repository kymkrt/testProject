package study2.database;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/study2/database/DbInputOk")
public class DbInputOk extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("name")==null ? "" : request.getParameter("name");
		int age = request.getParameter("age")==null || request.getParameter("age").equals("") ? 0 : Integer.parseInt(request.getParameter("age"));
		String gender = request.getParameter("gender")==null ? "" : request.getParameter("gender");
		String address = request.getParameter("address")==null ? "" : request.getParameter("address");
		
		String viewPage ="";
		
		DbTestVO vo = new DbTestVO();
		vo.setName(name);
		vo.setAge(age);
		vo.setGender(gender);
		vo.setAddress(address);
		
		DbTestDAO dao = new DbTestDAO();
		
		int res = dao.setDbTestInput(vo);
		
		if(res == 1) {
			request.setAttribute("msg", "회원가입 성공");
			request.setAttribute("url", "");
		}
		else if(res == 0) {
			request.setAttribute("msg", "회원가입 성공");
			request.setAttribute("url", "");
		}
		
		
		viewPage = "/study2/database/DbList";
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
		
	}
}
