<div id="login" class="form">
<div class="lrtable">
	<h3>
	User Login
	</h3>
	<br>
	<form method="post" action="<%= request.getRequestURI() %>">
    <table>
    <tr>
    <td>
    UserName:
	</td>
	<td>
    <input type="text" name="username" class="textfield"/>
    </td>
    <tr>
    <td colspan="2">
    <br>
    </td>
    </tr>
    <tr>
    <td>
    Password:
    </td>
    <td>
    <input type="password" name="password" class="textfield"/>
    </td>
    </tr>
    <tr>
    <td colspan="2">
    <br>
    </td>
    </tr>
    <tr>
    <td colspan="2">
    <input type="hidden" name="action" value="login"/>
    </td>
    <tr>
    <td>
    <input type="submit" value="Login"  class="logbutton"/>
    </td>
    <td>
    <input type="reset" value="Reset"  class="resbutton" onclick="document.form.msg.value = null; return false;">
    </td>
    </tr>
    </table>
    <% String msg = (String) request.getAttribute("msg"); %>
    <% if (msg != null) { %>
		<div style="color:red;">
			<output id="msg"><%=msg%></output>
		</div>
	<% } %>
    </form>
    </div>
</div>