import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RemovePurchaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public RemovePurchaseServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String p_id=request.getParameter("pid");
    	String u_id=request.getParameter("uid");
    	String store_location=request.getParameter("storelocation");
    		ProductDao.removepurchasecart(u_id,p_id,store_location);
    		request.setAttribute("msg","successfully remove from cart.");
    		response.sendRedirect("purchasecart.jsp");
    	return;
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
}
