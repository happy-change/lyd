<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'listFiles.jsp' starting page</title>
    
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
   <c:forEach items="${ map}"  var="me">
   <c:url value="/TDownLoad" var="u">
    		<c:param name="filename" value="${me.key}"></c:param>
    	</c:url>
   
<!--    //将map里边的每一个元素遍历出来 -->
     <a href="${u}&Sno=${Sno}&Ssubject=${Ssubject}&Species=${Species}">${me.value}</a><br/>
   
   </c:forEach>
   
   <a href="teacher.jsp?Sno=${Sno}&Ssubject=${Ssubject}&Species=${Species}">上传</a>
   
   
   
  </body>
</html>
