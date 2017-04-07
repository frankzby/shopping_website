<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ page import ="java.sql.*" %>
<script>
function cartbtn() {
    alert("Product Added!");
}
</script>
<div class="product">
<c:forEach items="${products}" var="p" varStatus="count">
<div class="productitem">
<form method="post" action="addpurchase">
<table>
   <tr><td height="180px"><img alt="" src="images/${p.pId}.jpg" width="50%"></td></tr>
    	<tr><td>${p.pname}</td></tr>
    	<tr><td>${p.price}</td></tr>
    	<tr><td height="80px">
    	<c:forEach items="${storing}" var="s" varStatus="count"> 
          <c:if test="${s.pid eq p.pId}">
            <c:forEach items="${branches}" var="l" varStatus="count"> 
                <c:if test="${l.id eq s.lid}">
               <div class="inventorytext"> ${l.lname}
                ${s.quantity}</div>
                </c:if>
            </c:forEach>
           </c:if>
        </c:forEach>
        </td></tr>
<tr><td>
    <input type="text" name="quantity" class="countfield"/></td></tr>
   <tr><td>
    <select name="storelocation" class="selection">
    <option value="Toronto">Toronto</option>
    <option value="Quebec">Quebec</option>
    <option value="Vancouver">Vancouver</option>
    <option value="Ottawa">Ottawa</option>
    </select>
    </td></tr>
    <tr><td>
     <input type="hidden" name="pid" value="${p.pId}"/>
    <input type="hidden" name="uid" value="<%=request.getAttribute("uid")%>"/>
    <input type="submit" value="Add to Cart"  class="button" onclick="cartbtn();"/>	
	</td></tr>
</table>
</form>
</div>
</c:forEach>
</div> 
