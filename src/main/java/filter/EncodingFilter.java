package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

//모든게 다 통과함
@WebFilter("/*")
public class EncodingFilter implements Filter{
	
	/*
	 * @Override public void init(FilterConfig filterConfig) throws ServletException
	 * { System.out.println("이곳은 init 메소드 입니다"); }
	 */

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		
		
		//전과 후를 쪼개서 처리 할수 있게 해주는게 필터체인
		//필터 전
		chain.doFilter(request, response);
		//필터 후
		
	}
	//init destroy
}
