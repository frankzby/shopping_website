import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PurchaseCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public PurchaseCartServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String p_id=request.getParameter("pid");
    	String u_id=request.getParameter("uid");
    	String quantity=request.getParameter("quantity");
    	String storelocation=request.getParameter("storelocation");
    	ProductDao.addPurchaseCart(u_id,p_id,quantity,storelocation);
    		request.setAttribute("msg","successfully add to cart.");
    		RequestDispatcher rd=request.getRequestDispatcher("purchase.jsp");
			rd.forward(request,response);
    	return;
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
}
