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
	 alert("Order Placed!");
}
</script>
<div class="incart">
<h3>Purchase Cart</h3>
<table>
<tr>
<td colspan="2">Product</td><td>Price</td><td>Quantity</td><td>Amount</td><td>location<td>Action</td><tr>
<c:forEach items="${purchasecartproducts}" var="pc" varStatus="count">
<tr>

   <td width = "20%"><img alt="" src="images/${pc.pId}.jpg" width="40%"></td>
    	<td>${pc.pname}</td>
    	<td>${pc.price}</td>
    	<td>${pc.count}</td>
    	<td>${pc.amount}</td>
    	<td>${pc.storelocation}</td>
		<td>
		<form method="post" action="removepurchase">
		    <input type="hidden" name="pid" value="${pc.pId}"/>
		    <input type="hidden" name="uid" value="<%=request.getAttribute("uid")%>"/>
		    <input type="hidden" name="storelocation" value="${pc.storelocation}"/>
		    <input type="submit" value="Remove"  class="resbutton" onclick="removebtn()"/>
		</form>
		</td>
</tr>	
</c:forEach>
<tr><td colspan="7"><hr></td></tr>
<tr><td colspan="7">Order Total: 
<%=request.getAttribute("sump")%></td></tr>
<tr><td colspan="7">
<form method="post" action="createorder">
 	<input type="hidden" name="uid" value="<%=request.getAttribute("uid")%>"/>
 	<input type="hidden" name="ordersum" value="<%=request.getAttribute("sump")%>"/>
 	<%--determine the total amount of cart so that create proper button --%>
 	<c:if test="${sump==0.0}">
		<input type="button" value="Go Back" class="button" onClick="history.go(-1);return true;">
	</c:if>
	<c:if test="${sump!=0.0}">
	<input type="submit" value="Submit" class="button" onclick="submitbtn()"/>
	</c:if>
 </form>
 </td></tr></table>
</div> 
