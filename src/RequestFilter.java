import java.io.IOException;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import java.util.HashMap;
import java.util.Map;

@WebFilter("/RequestFilter")
public class RequestFilter implements Filter {
    public RequestFilter() {
        // TODO Auto-generated constructor stub
    }
    @Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
        String reqUrl = req.getRequestURI();
        System.out.println("requested Url------"+reqUrl);
        response.setContentType("text/html");
        HttpSession session=req.getSession();
        List<Product> products = ProductDao.retrieveProduct();  //extract content from sql table into list
        req.setAttribute("products", products);					//put the list into request
        List<Store> storing = ProductDao.retrieveStore();      //extract content from sql table into list
        req.setAttribute("storing", storing);                  //put the list into request
        List<Branch>  branches= ProductDao.retrieveBranch();     //extract content from sql table into list 
        req.setAttribute("branches", branches);                  //put the list into request
        float sumc=0, sump=0;
        
        if(session.getAttribute("uid")!= null){				//determine the identity of user so that create proper cart
            List<Cart> cartproducts = ProductDao.retrieveCartProduct( session.getAttribute("uid").toString());
            Map<Integer, Product> productMap = new HashMap<Integer, Product>();
            for(Product product : products){
                productMap.put(product.getpId(), product);
            }
            for(Cart cart : cartproducts){
                cart.setPname(productMap.get(cart.getpId()).getPname());
                cart.setPrice(productMap.get(cart.getpId()).getPrice());
                cart.setAmount(cart.getPrice()*cart.getCount());
                sumc =  sumc + cart.getAmount();
            }
            req.setAttribute("cartproducts", cartproducts);
            req.setAttribute("sumc", sumc);
            //extract content from sql table into list
            List<PurchaseCart> purchasecartproducts = ProductDao.retrievePurchaseCartProduct( session.getAttribute("uid").toString());
            Map<Integer, Product> purchaseproductMap = new HashMap<Integer, Product>();
            for(Product product : products){
            	purchaseproductMap.put(product.getpId(), product);
            }//set cart for manager 
            for(PurchaseCart purchasecart : purchasecartproducts){
            	purchasecart.setPname(purchaseproductMap.get(purchasecart.getpId()).getPname());
            	purchasecart.setPrice(purchaseproductMap.get(purchasecart.getpId()).getPrice());
            	purchasecart.setAmount(purchasecart.getPrice()*purchasecart.getCount());
                sump =  sump + purchasecart.getAmount();
            }
            req.setAttribute("purchasecartproducts", purchasecartproducts);
            req.setAttribute("sump", sump);
        }
     ///   
      
     ///   determine the identity and status of user 
        String a=req.getParameter("action");
        if (a != null && session.getAttribute("loggedIn") == null && a.equals("login")) {
	    	String n=req.getParameter("username");
	    	String p=req.getParameter("password");		
	    	if(LoginDao.validate(n, p)){
	    		session.setAttribute("loggedIn", true);
	    		User u = LoginDao.retrieveUser(n);
	    		session.setAttribute("uname", u.getUserName());
	    		session.setAttribute("utype", u.getUserType());
	    		session.setAttribute("loggedIn",true);
	    		session.setAttribute("uid", u.getUserId());
	    		req.setAttribute("uid", u.getUserId());
	    		req.setAttribute("uname", u.getUserName());
	    		req.setAttribute("utype", u.getUserType());
	    		req.setAttribute("loggedIn",true);
	    		System.out.println("login success");
	    		RequestDispatcher rd=request.getRequestDispatcher("ITEC4020-A2.jsp");
				rd.forward(request,response);
	    	} else {
	    		req.setAttribute("loggedIn",false);
	    		req.setAttribute("login_msg","login failed");
	    		System.out.println(req.getAttribute("login_msg"));
	    		RequestDispatcher rd=request.getRequestDispatcher("login.jsp");
				rd.forward(request,response);
	    	}
	    } else if (a != null && a.equals("logout")) {
	    	session.invalidate();
	        req.setAttribute("loggedIn",false);
	        req.setAttribute("msg","You have successfully logged out.");
			System.out.println(req.getAttribute("msg"));
			RequestDispatcher rd=request.getRequestDispatcher("ITEC4020-A2.jsp");
			rd.forward(req,res);
			return;
	    } else if (session.getAttribute("loggedIn") != null) {
	    	req.setAttribute("uname", session.getAttribute("uname"));
			req.setAttribute("utype", session.getAttribute("utype"));
			req.setAttribute("uid", session.getAttribute("uid"));
			req.setAttribute("loggedIn",true);
	    }
        chain.doFilter(request, response);
	}
    
	public void init(FilterConfig fConfig) throws ServletException {
		//call initial functions 
		LoginDao.preload();
		ProductDao.preload();
	}

	public void destroy() {
		
	}
}
