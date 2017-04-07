
<%@ page import ="java.sql.*" %>
<form method="post" action="<%= request.getRequestURI() %>">
Welcome, <%=request.getAttribute("uname")%>!
You are logged in as <%=request.getAttribute("utype")%>.
	<input type="hidden" name="action" value="logout"/>
	<input type="submit" class="logbutton" value="Logout"/>
</form>
