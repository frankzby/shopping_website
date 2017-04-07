<div id="reg" class="form">
<div class="lrtable">
	<h3>
	Member Registration
	</h3>
	<br>
	<form method="post" action="register">
    <table>
    <tr>
    <td>First Name:</td>
    <td><input type="text" name="fname" class="textfield"/></td>
    </tr>
    <tr>
    <td colspan="2">
    <br>
    </td>
    </tr>
    <tr>
    <td>Last Name:</td>
    <td><input type="text" name="lname" class="textfield"/></td>
    </tr>
    <tr>
    <td colspan="2">
    <br>
    </td>
    </tr>
	<tr>
	<td>User Name:</td>
    <td><input type="text" name="uname" class="textfield"/></td>
    </tr>
    <tr>
    <td colspan="2">
    <br>
    </td>
    </tr>
	<tr>
	<td>Password:</td>
    <td><input type="password" name="pwd" class="textfield"/></td>
    </tr>
    <tr>
    <td colspan="2">
    <br>
    </td>
    </tr>
  	<tr><td colspan="2">
    <input type="hidden" name="action" value="register"/></td>
    <tr><td>
    <input type="submit" value="Register"  class="logbutton"/></td>
    <td>
    <input type="reset" value="Reset"  class="resbutton" onclick="document.form.msg.value = null; return false;"></td>
      </table>
    </div>
    <% String msg = (String) request.getAttribute("regerror"); %>
    <% if (msg != null) { %>
		<div style="color:red;">
			<output id="msg"><%=msg%></output>
	<% } %>
	</form>
    </div>
</div>