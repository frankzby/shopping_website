<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ page import ="java.sql.*" %>
<script>
function cartbtn() {
    alert("Product Added, Thank you!!");
}
</script>
<div class="product">
<c:forEach items="${products}" var="p" varStatus="count">
<div class="productitem">
<table>
<tr><td height="180px">
   <img alt="" src="images/${p.pId}.jpg" width="50%"></td></tr>
    	<tr><td>${p.pname}</td></tr>
    	<tr><td>${p.price}</td></tr>		<%-- --%>
    	<c:choose>
        <c:when test="${utype=='sales'}"><tr><td height="70px">
        <%-- use jstl foreach function to extract content from request and display content in sequence--%>
    	<c:forEach items="${storing}" var="s" varStatus="count"> 
          <c:if test="${s.pid eq p.pId}">
            <c:forEach items="${branches}" var="l" varStatus="count"> 
                <c:if test="${l.id eq s.lid}">
               <div class="inventorytext"> 
                ${l.lname}
                ${s.quantity}<br></div>
                </c:if>
            </c:forEach>
           </c:if>
        </c:forEach>
        </td></tr>
							</c:when>
		</c:choose>
    	
    <tr><td>
    <a class="detail" href='p${p.pId}.jsp'>Details</a>
    </td><tr>
    <tr><td>
<form method="post" action="addtocart">

    <input type="hidden" name="pid" value="${p.pId}"/>
    <input type="hidden" name="uid" value="<%=request.getAttribute("uid")%>"/>
    <%--determine the identity of user so that create proper button --%>
    <%
    if(request.getAttribute("uid")!=null)
    {
    %>
    <input class="button"type="submit" value="Add to Cart"  onclick="cartbtn()"/>
    <%} %>
</form>	
</td></tr>
</table>
</div>
</c:forEach>


</div>
