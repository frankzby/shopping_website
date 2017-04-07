import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public CartServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String p_id=request.getParameter("pid");
    	String u_id=request.getParameter("uid");
    	ProductDao.addtoCart(u_id,p_id);
    	
    		request.setAttribute("msg","successfully add to cart.");
    		RequestDispatcher rd=request.getRequestDispatcher("product.jsp");
			rd.forward(request,response);
    	return;
    	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
}
