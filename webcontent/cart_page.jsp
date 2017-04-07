<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ page import ="java.sql.*" %>
<script>
function removebtn() {
    alert("Product Removed!");
}
function submitbtn(){
	 alert("Order Placed,Thank you!");
}
</script>
<div class="incart">
<h3>Cart</h3>
<table>
<tr>
<td colspan="2">Product</td><td>Price</td><td>Quantity</td><td>Amount</td><td>Action</td><tr>
<c:forEach items="${cartproducts}" var="c" varStatus="count">
<tr>

   <td width = "20%"><img alt="" src="images/${c.pId}.jpg" width="40%"></td>
    	<td>${c.pname}</td>
    	<td>${c.price}</td>
    	<td>${c.count}</td>
    	<td>${c.amount}</td>
		<td>
		<form method="post" action="remove" >
		    <input type="hidden" name="pid" value="${c.pId}"/>
		    <input type="hidden" name="uid" value="<%=request.getAttribute("uid")%>"/>
		    <input type="submit" value="Remove"  class="resbutton" onclick="removebtn()"/>
		</form>
		</td>
</tr>	
</c:forEach>
<tr><td colspan="6"><hr></td></tr>
 <tr><td colspan="6">Order Total:
<%=request.getAttribute("sumc")%>
</td></tr>
<tr><td colspan="6">

	<form method="post" action="createorder">
 	<input type="hidden" name="ordersum" value="<%=request.getAttribute("sumc")%>"/>
 	<input type="hidden" name="uid" value="<%=request.getAttribute("uid")%>"/>
 	    <%--determine the total amount of cart so that create proper button --%>
 	
 	<c:if test="${sumc==0.0}">
		<input type="button" value="Go Back" class="button" onClick="history.go(-1);return true;">
	</c:if>
	<c:if test="${sumc!=0.0}">
	<input type="submit" value="Submit" class="button" onclick="submitbtn()"/>
	</c:if>
 	
 	</form>
 	

 </td></tr>
 </table>
</div> 
