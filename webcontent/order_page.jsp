<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ page import ="java.sql.*" %>
<h3>Order</h3>
<div class="inorder">
<table>
<tr>
<td>
Order ID:<%=request.getAttribute("oid")%><br>
</td></tr>
<tr><td>
	   
Order Total:<%=request.getAttribute("total")%>
</td></tr>
</table>
</div> 
