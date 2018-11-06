<%@ page language="java" pageEncoding="utf-8"%>

<html>
	<head>
		<title>用户登录成功</title>
	</head>

	<body>
		<%
			//String name = request.getParameter("uname") != null ? (String) request.getParameter("uname"): "";
			String name = "";
			if(request.getParameter("uname") != null)
			{
				name = request.getParameter("uname");
			}
			else
			{
				name = "";
			}
			
			if(name.equals("whvcse")) 
			{
				session.setAttribute("isLogin", "true");
				response.sendRedirect("success.jsp");
			} 
			else 
			{
		%>
				<a href="index.jsp">返回登陆页</a>
		<%
			}
		%>
	</body>
</html>