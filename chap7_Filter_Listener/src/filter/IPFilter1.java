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

import javax.servlet.RequestDispatcher;

/**
 * Servlet Filter implementation class IPFilter1
 */
@WebFilter(
			//urlPatterns = { "/IPFilter1" }, 
			urlPatterns = { "/*" }, 
			initParams =  { 
						  	@WebInitParam(name = "filteredIP", value = "127.0.0.1", description = "filteredIP")
						  }
		)
public class IPFilter1 implements Filter 
{
	String filteredIP = "";
	
    /**
     * Default constructor. 
     */
    public IPFilter1() 
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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
//		chain.doFilter(request, response);
		String remoteAddr = request.getRemoteAddr();//request.getRemoteAddr() 获取的值为0:0:0:0:0:0:0:1的原因及解决办法:本机访问的时候用127.0.0.1或本机ip代替localhost即可解决
		
		if(remoteAddr.equals(filteredIP))
		{			
			//允许的IP，则继续处理
			chain.doFilter(request, response);				
		}
		else
		{
			//禁止的IP，打印提示信息至控制台，并转至error.jsp页
			System.out.println("被IPFilter拦截一个未认证的请求");
			RequestDispatcher requestDisPatcher = request.getRequestDispatcher("error.jsp");		
			requestDisPatcher.forward(request, response);	
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException 
	{
		// TODO Auto-generated method stub
		filteredIP = fConfig.getInitParameter("filteredIP");
		if(null == filteredIP)
		{
			filteredIP = "";			
		}
	}

}
