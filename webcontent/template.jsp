<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
          "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<link href="http://fonts.googleapis.com/css?family=Arvo" rel="stylesheet" type="text/css" />
 <link rel="stylesheet" type="text/css" href="1.css" /> 

<title>${param.title}</title>
</head>

<body>
<div class="head1">
<ul>
		<li>
		<strong>
		<script type="text/javascript">
	 	<!--
		var now = new Date();
		var Weekday = new Array("Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday");
		var Month = new Array("January","February","March","April","May","June","July","August","September","October","November","December");
		document.write(Weekday[now.getDay()]+", "+Month[now.getMonth()]+" "+now.getDate()+", "+now.getFullYear());
		//--></script>
		</strong>
		</li>
		<li>
		<strong>
		<c:choose>
            <c:when test="${loggedIn}">
            <jsp:include page="welcome.jsp" />
			</c:when>
			<c:otherwise>
			<a href="login.jsp">Login </a><strong> | </strong><a href="registration.jsp"> Register</a>
			</c:otherwise>
		</c:choose>
		</strong>
		</li>
</ul>	
</div>
<div id="menu">
		<jsp:include page="menu.jsp" />
</div>	


	<jsp:include page="${param.content}.jsp" />

<div id="copyright">
	4020 Group Project Developed By Xiaoyi Li, Boyao Zhang, Shengnan Zhang, Luo Wei.
</div>
</body>
</html>