import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import javax.servlet.RequestDispatcher;
/**
 * Servlet Filter implementation class IPFilter
 */
@WebFilter("/IPFilter")//使用注解，配置过滤器名称、作用的URL地址、初始化参数等
//@WebFilter("/*")
public class IPFilter implements Filter //过滤器类必须实现Filter接口
{

	private String adminIP = null;	
    /**
     * Default constructor. 
     */
    public IPFilter() 
    {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
  //destroy()：在过滤器实例销毁时释放资源
	public void destroy() 
	{
		// TODO Auto-generated method stub
		adminIP = null;
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	//doFilter()方法：进行实际的过滤操作
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException 
	{
		// TODO Auto-generated method stub
		// place your code here
		
//		String remoteIP = request.getRemoteAddr();//取客户端IP
		String remoteIP = "127.0.0.1";
	
		if (remoteIP.equals(adminIP)) 	//将客户端IP与管理员IP进行比对
		{
			chain.doFilter(request, response);//客户端IP与管理员IP一致则通过过滤，继续向后执行
		} 
		else 
		{	
			RequestDispatcher requestDisPatcher = request.getRequestDispatcher("/error.jsp");//客户端IP与管理员IP不一致则导向至error.jsp页
			requestDisPatcher.forward(request, response);
		}

		// pass the request along the filter chain
		//chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	//init()方法：利用FilterConfig对象从配置文件中读取管理员IP的值
	public void init(FilterConfig fConfig) throws ServletException 
	{
		// TODO Auto-generated method stub
//		adminIP = fConfig.getInitParameter("adminIP");
		adminIP = "127.0.0.1";
//		adminIP = "localhost";
	}

}
