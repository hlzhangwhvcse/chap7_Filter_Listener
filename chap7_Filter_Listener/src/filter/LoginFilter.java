package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter(
		urlPatterns = { "success.jsp" }
	)
public class LoginFilter implements Filter 
{

    /**
     * Default constructor. 
     */
    public LoginFilter() 
    {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() 
	{
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException 
	{
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
//		chain.doFilter(request, response);
		
		//session是HTTP协议下的，需对request的ServletRequest类型转换为HttpServletRequest，再获取其session		
		HttpServletRequest httpServletRequest = (HttpServletRequest)request;
		HttpServletResponse httpServletResponse = (HttpServletResponse)response;		
		HttpSession httpSession = httpServletRequest.getSession();		
		
//		String isLogin = httpSession.getAttribute("isLogin")!=null?(String)session.getAttribute("isLogin"):"";		
		String isLogin = "";
		if(httpSession.getAttribute("isLogin") != null)
		{	
			isLogin = (String)httpSession.getAttribute("isLogin");
		}
		else 
		{
			isLogin = "";
		}
		
		if(isLogin.equals("true"))
		{			
			//通过验证，继续处理
			chain.doFilter(request, response);		
		}
		else
		{
			//未通过验证，回到登陆页
			System.out.println("被LoginFilter拦截一个未认证的请求");
			httpServletResponse.sendRedirect("index.jsp");
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException 
	{
		// TODO Auto-generated method stub
	}

}
