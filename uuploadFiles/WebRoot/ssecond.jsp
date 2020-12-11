<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'ssecond.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  
    <%
    	String Sno=new String(request.getParameter("Sno").getBytes("iso-8859-1"), "utf-8");
    	String Species1="Recourses";
    	String Species2="Homeworks";
    	String p = "sthird.jsp";
    	String p1 = "sfifth.jsp";
     %>
    <a href="${pageContext.request.contextPath}/LShowAllFiles?Sno=<%=Sno%>&Species=<%=Species1%>&p=<%=p1%>">资源浏览</a>
    <a href="${pageContext.request.contextPath}/LShowAllFiles?Sno=<%=Sno%>&Species=<%=Species2%>&p=<%=p%>">提交作业</a><br>
  </body>
</html>
