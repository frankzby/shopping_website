<%@ page import ="java.sql.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<div class="menuList">
	<ul>
	<li>
	<div id="logo"><img src="images/logo.png"/></div>
	
	</li>
	<li>
		<ul id="mini-menu"><li><a href="ITEC4020-A2.jsp">Home</a></li>
				<li><c:choose>
            				<c:when test="${utype=='manager'}">
           						<a href="purchase.jsp">purchase</a>
							</c:when>
							<c:otherwise>
								<a href="product.jsp">Product</a>
							</c:otherwise>
						</c:choose>
				</li>
				<li>
						<a href="contact.jsp">Contact Us</a></li>
			</ul>
	</li>
	<li>
	<div id="cartlogo">
						<c:choose>
							<c:when test="${uid==null }">
           						<a href="login.jsp"><img alt="cart" src="images/cart.png"></a>
							</c:when>
							<c:otherwise>
								<c:choose>
	            				<c:when test="${utype=='manager'}">
	           						<a href="purchasecart.jsp"><img alt="cart" src="images/cart.png"></a>
								</c:when>
								<c:otherwise>
									<a href="cart.jsp"><img alt="cart" src="images/cart.png"></a>
								</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>
	
	</div>
	</li>
	</ul>
</div>
