import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RemoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public RemoveServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String p_id=request.getParameter("pid");
    	String u_id=request.getParameter("uid");
    		ProductDao.removecart(u_id,p_id);
    		request.setAttribute("msg","successfully remove from cart.");
    		response.sendRedirect("cart.jsp");
    	return;
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
}
