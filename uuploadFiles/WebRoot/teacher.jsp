<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'teacher.jsp' starting page</title>
    
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
  		String Ssubject = new String(request.getParameter("Ssubject").getBytes("iso-8859-1"), "utf-8");
  		String Species = new String(request.getParameter("Species").getBytes("iso-8859-1"), "utf-8");
  	 %>
    <form action="${pageContext.request.contextPath}/TUpload?Sno=<%=Sno%>&Ssubject=<%=Ssubject%>&Species=<%=Species%>" method="post" enctype="multipart/form-data">
   <table border="1">
     
     <tr><td>文件选择</td><td><input type="file" name="photo"></td></tr>
     <tr><td>文件选择</td><td><input type="file" name="photo"></td></tr>
   
     <tr><td align="center" colspan="2"><input type="submit" value="文件上传"></td></tr>
     <tr><td colspan="1">对上传的文件下载</td><td><a href="${pageContext.request.contextPath}/TShowAllFiles?Sno=${Sno}&Ssubject=${Ssubject}&Species=${Species}">资源预览及下载</a></td></tr>
     </table>
   </form>
  
  </body>
</html>
