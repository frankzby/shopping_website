import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public OrderServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String u_id=request.getParameter("uid");
    	String total=request.getParameter("ordersum");
    	if(!request.getAttribute("utype").equals("manager")){
    		Order o = ProductDao.createOrder(u_id,total);
    	    request.setAttribute("oid", o.getoId());
            request.setAttribute("total", o.getTotal());
            ProductDao.clearcart(u_id);
    	}
    	else{
    		PurchaseOrder po = ProductDao.createPurchaseOrder(u_id,total);
    	    request.setAttribute("oid", po.getoId());
            request.setAttribute("total", po.getTotal());
            ProductDao.clearpurchasecart(u_id);
    		
    	}
		request.setAttribute("msg","order created");
		RequestDispatcher rd=request.getRequestDispatcher("order.jsp");
		rd.forward(request,response);
	return;
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
}
