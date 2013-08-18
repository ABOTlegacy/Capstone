<%@page import="com.tek271.reverseProxy.services.Timestamp"%>
<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
 <base href="<%=basePath%>">

 <title>Proxy Pattern Example</title>
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
 <h3>Domain object is created:</h3>

<%
System.out.println("AAAA");
 Timestamp timestamp = (Timestamp)request.getAttribute("timestamp");
System.out.println("BBBB");
 out.print(timestamp.getTimestamp());
 %>
 </body>
</html>