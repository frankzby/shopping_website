import java.awt.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;


public class ProductDao {
	public static void preload() {
		try
		{
			//insert table and insert content
			Connection con = ConnectionDao.getConnection();
		    Statement st = con.createStatement();
		    st.execute("USE 4020_a2");
		    st.execute("DROP TABLE IF EXISTS store");
		    st.execute("DROP TABLE IF EXISTS cart");
		    st.execute("DROP TABLE IF EXISTS purchasecart");
		    st.execute("DROP TABLE IF EXISTS porder");
		    st.execute("DROP TABLE IF EXISTS ppurchaseorder");
		    st.execute("DROP TABLE IF EXISTS location");
		    st.execute("DROP TABLE IF EXISTS product");
	
		    st.execute("CREATE TABLE product (" +
		            "id BIGINT NOT NULL AUTO_INCREMENT,"
		            + "pname VARCHAR(50),"
		            + "price FLOAT,"
		            +"PRIMARY KEY(id)"
		            + ")");		    
		    st.execute("CREATE TABLE cart (" +
		            "id BIGINT NOT NULL AUTO_INCREMENT,"
		            + "uid BIGINT,"
		            + "pid BIGINT,"
		            + "count BIGINT,"
		            + "PRIMARY KEY(id),"
		            + "FOREIGN KEY(uid) references login(id),"
		            + "FOREIGN KEY(pid) references product(id)"
		            + ")");
		    //purchasecart
		    st.execute("CREATE TABLE purchasecart (" +
		            "id BIGINT NOT NULL AUTO_INCREMENT,"
		            + "uid BIGINT,"
		            + "pid BIGINT,"
		            + "count BIGINT,"
		            + "storelocation VARCHAR(50),"
		            + "PRIMARY KEY(id),"
		            + "FOREIGN KEY(uid) references login(id),"
		            + "FOREIGN KEY(pid) references product(id)"
		            + ")");
		    
		    
		    
		    
		    //
		    st.execute("CREATE TABLE location (" +
		            "id BIGINT NOT NULL AUTO_INCREMENT,"
		            + "lname VARCHAR(50),"
		            + "PRIMARY KEY(id)"
		            + ")");
		    
		    st.execute("CREATE TABLE store (" +
		            "id BIGINT NOT NULL AUTO_INCREMENT,"
		            + "lid BIGINT,"
		            + "pid BIGINT,"
		            + "quantity BIGINT,"
		            + "PRIMARY KEY(id),"
		            + "FOREIGN KEY(pid) references product(id),"
		            + "FOREIGN KEY(lid) references location(id)"
		            + ")");
		  //
		    st.execute("CREATE TABLE porder (" +
		            "id BIGINT NOT NULL AUTO_INCREMENT,"
		            + "uid BIGINT,"
		            + "sum FLOAT,"
		            + "PRIMARY KEY(id),"
		            + "FOREIGN KEY(uid) references login(id)"
		            + ")");
		    //
		    st.execute("CREATE TABLE ppurchaseorder (" +
		            "id BIGINT NOT NULL AUTO_INCREMENT,"
		            + "uid BIGINT,"
		            + "sum FLOAT,"
		            + "PRIMARY KEY(id),"
		            + "FOREIGN KEY(uid) references login(id)"
		            + ")");
		    //
		    
		    st.execute("INSERT INTO location (lname) VALUES" +
		            "('Toronto'),('Quebec')," +
		            "('Vancouver'),('Ottawa')");
		    //
		    
		  
		    st.execute("INSERT INTO product (pname, price) VALUES" + 
		            "('macbook', '1499.99'),('router', '199.99')," +
		            "('printer', '199.99'),('headphone', '230.99'),('DSLR camera', '1599.99'),('tablet', '530.99')");
		    
		    //
		    st.execute("INSERT INTO store (lid,pid,quantity) VALUES" +
		            "('1','1','30'),('2','1','40'),('1','2','11')," +
		            "('2','2','11'),('3','3','22'),('4','4','29'),('1','5','19'),('2','5','59'),('1','6','12'),('2','6','34')");
		   
		    System.out.println("working too");
		    st.close();
		    con.close();  
		}
		catch (Exception e)
		{
		    e.printStackTrace();
		}
	}
	//methods used in request filter and servlets

	public static List<Product> retrieveProduct() {
		List<Product> pList = new ArrayList<Product>();
		try
		{
			Connection con = ConnectionDao.getConnection();
		    Statement st = con.createStatement();  
		    st.execute("USE 4020_a2");  
		    PreparedStatement ps = con.prepareStatement("SELECT * FROM product");
		    ResultSet res = ps.executeQuery();
		    while (res.next())
		    {
		    	Product p = new Product();
		    	p.setpId(res.getInt("id"));
		    	p.setPname(res.getString("pname"));
		        p.setPrice(res.getFloat("price"));
		        pList.add(p);
		    }
		    res.close();
		    st.close();
		    ps.close();
		    con.close();            
		}
		catch (Exception e)
		{
		    e.printStackTrace();
		}
		return pList;
	}
	//
	public static List<Store> retrieveStore() {
		List<Store> sList = new ArrayList<Store>();
		try
		{
			Connection con = ConnectionDao.getConnection();
		    Statement st = con.createStatement();
		    st.execute("USE 4020_a2");
		    PreparedStatement ps = con.prepareStatement("SELECT * FROM store");
		    ResultSet res = ps.executeQuery();
		    while (res.next())
		    {
		    	Store s = new Store();
		    	s.setPid(res.getInt("pid"));
		    	s.setLid(res.getInt("lid"));
		    	s.setQuantity(res.getInt("quantity"));
		        sList.add(s);
		    }
		    res.close();
		    st.close();
		    ps.close();
		    con.close();
		}
		catch (Exception e)
		{
		    e.printStackTrace();
		}
		return sList;
	}

	public static List<Branch> retrieveBranch() {
		List<Branch> lList = new ArrayList<Branch>();
		try
		{
			Connection con = ConnectionDao.getConnection();
		    Statement st = con.createStatement();
		    st.execute("USE 4020_a2");
		    PreparedStatement ps = con.prepareStatement("SELECT * FROM location");
		    ResultSet res = ps.executeQuery();
		    while (res.next())
		    {
		    	Branch l = new Branch();
		    	l.setId(res.getInt("id"));
		    	l.setLname(res.getString("lname"));
		        lList.add(l);
		    }
		    res.close();
		    st.close();
		    ps.close();
		    con.close();
		}
		catch (Exception e)
		{
		    e.printStackTrace();
		}
		return lList;
	}
	//
	
	
	public static List<Cart> retrieveCartProduct(String uid) {
		List<Cart> cList = new ArrayList<Cart>();
		try
		{
			Connection con = ConnectionDao.getConnection();
		    Statement st = con.createStatement();  
		    st.execute("USE 4020_a2"); 
		    PreparedStatement ps = con.prepareStatement("SELECT * FROM  cart where uid = ?");
		    ps.setString(1, uid);
		    ResultSet res = ps.executeQuery();
		    while (res.next())
		    {
		    	Cart c = new Cart();
		    	c.setpId(res.getInt("pid"));
		        c.setCount(res.getInt("count"));
		        cList.add(c);
		    }
		    res.close();
		    st.close();
		    ps.close();
		    con.close();            
		}
		catch (Exception e)
		{
		    e.printStackTrace();
		}
		return cList;
	}
	
	public static List<PurchaseCart> retrievePurchaseCartProduct(String uid) {
		List<PurchaseCart> pcList = new ArrayList<PurchaseCart>();
		try
		{
			Connection con = ConnectionDao.getConnection();
		    Statement st = con.createStatement();  
		    st.execute("USE 4020_a2"); 
		    PreparedStatement ps = con.prepareStatement("SELECT * FROM  purchasecart where uid = ?");
		    ps.setString(1, uid);
		    ResultSet res = ps.executeQuery();
		    while (res.next())
		    {
		    	PurchaseCart pc = new PurchaseCart();
		    	pc.setpId(res.getInt("pid"));
		        pc.setCount(res.getInt("count"));
		        pc.setStorelocation(res.getString("storelocation"));
		        pcList.add(pc);
		    }
		    res.close();
		    st.close();
		    ps.close();
		    con.close();            
		}
		catch (Exception e)
		{
		    e.printStackTrace();
		}
		return pcList;
	}
	
	
	public static void addtoCart(String uid, String pid) {
		try
		{
			Connection con = ConnectionDao.getConnection();
		    Statement st = con.createStatement();  
		    st.execute("USE 4020_a2");
		    PreparedStatement ps = null;
		    if (!ProductDao.checkCart(uid, pid)) {
			    ps = con.prepareStatement("insert into cart(uid, pid, count) values (?,?,'1')");
			    
		    }else{
		    	 ps = con.prepareStatement("update cart set count = count+1 where uid=? and pid=?");
		    }
			    ps.setString(1, uid);
			    ps.setString(2, pid);
			    ps.executeUpdate();
			    ps = con.prepareStatement("select * from cart");
			    ResultSet res = ps.executeQuery();
			    while (res.next())
			    {
		    	System.out.println(res.getInt("id")+", "+res.getInt("uid")+", "+res.getInt("pid"));
			    }
			    res.close();
		    st.close();
		    ps.close();
		    con.close();            
		}
		catch (Exception e)
		{
		    e.printStackTrace();
		}
	}
	
	public static void addPurchaseCart(String uid, String pid, String quantity, String storelocation) {
		try
		{
			Connection con = ConnectionDao.getConnection();
		    Statement st = con.createStatement();  
		    st.execute("USE 4020_a2");
		    PreparedStatement ps = null;
		    if (!ProductDao.checkPurchaseCart(uid, pid, storelocation)) {
			    ps = con.prepareStatement("insert into purchasecart(uid, pid, count, storelocation) values (?,?,?,?)");
			    ps.setString(1, uid);
			    ps.setString(2, pid);
			    ps.setString(3, quantity);
			    ps.setString(4, storelocation);
		    }else{
		    	 ps = con.prepareStatement("select * from purchasecart where uid=? and pid= ? and storelocation= ?");
		    	 ps.setString(1, uid);
				 ps.setString(2, pid);
				 ps.setString(3, storelocation);
		    	 ResultSet res = ps.executeQuery();
		    	 int count = Integer.parseInt(quantity);
		    	 if(res.next()){count += res.getInt("count");}
		    	 ps = con.prepareStatement("update purchasecart set count =? where uid=? and pid= ? and storelocation= ?");
		    	 ps.setInt(1, count);
		    	 ps.setString(2, uid);
				 ps.setString(3, pid);
				 ps.setString(4, storelocation);
		    }
			    
			    ps.executeUpdate();
			    ps = con.prepareStatement("select * from purchasecart");
			    ResultSet res = ps.executeQuery();
			    while (res.next())
			    {
		    	System.out.println(res.getInt("id")+", "+res.getInt("uid")+", "+res.getInt("pid")+", "+res.getInt("count")+", "+res.getString("storelocation"));
			    }
			    res.close();
		    
		    st.close();
		    ps.close();
		    con.close();            
		}
		catch (Exception e)
		{
		    e.printStackTrace();
		}
	}
	
	
	
	public static Order createOrder(String uid,String sum) {
		 Order o = new Order();
		try
		{
			Connection con = ConnectionDao.getConnection();
		    Statement st = con.createStatement();  
		    st.execute("USE 4020_a2");
		    PreparedStatement ps = null;
			    ps = con.prepareStatement("insert into porder(uid, sum) values (?,?)");
			
			    ps.setString(1, uid);
			    ps.setString(2, sum);
			    ps.executeUpdate();
			    ps = con.prepareStatement("select * from porder");
			    ResultSet res = ps.executeQuery();
			   
			    while (res.next())
			    {

			    	o.setoId(res.getInt("id"));
			    	o.setTotal(res.getFloat("sum"));
			    
		    	System.out.println(res.getInt("id")+", "+res.getFloat("sum"));
			    }
			    res.close();
		    
		    st.close();
		    ps.close();
		    con.close();            
		}
		catch (Exception e)
		{
		    e.printStackTrace();
		}
		return o;
	}
	
	public static PurchaseOrder createPurchaseOrder(String uid,String sum) {
		 PurchaseOrder po = new PurchaseOrder();
		try
		{
			Connection con = ConnectionDao.getConnection();
		    Statement st = con.createStatement();  
		    st.execute("USE 4020_a2");
		    PreparedStatement ps = null;
			    ps = con.prepareStatement("insert into ppurchaseorder(uid, sum) values (?,?)");
			
			    ps.setString(1, uid);
			    ps.setString(2, sum);
			    ps.executeUpdate();
			    ps = con.prepareStatement("select * from ppurchaseorder");
			    ResultSet res = ps.executeQuery();
			   
			    while (res.next())
			    {

			    	po.setoId(res.getInt("id"));
			    	po.setTotal(res.getFloat("sum"));
			    
		    	System.out.println(res.getInt("id")+", "+res.getFloat("sum"));
			    }
			    res.close();
		    
		    st.close();
		    ps.close();
		    con.close();            
		}
		catch (Exception e)
		{
		    e.printStackTrace();
		}
		return po;
	}
	
	
	
		public static boolean checkCart(String uid, String pid){
			boolean status = false;
			try
			{
				Connection con = ConnectionDao.getConnection();
			    Statement st = con.createStatement();  
			    st.execute("USE 4020_a2");
			    PreparedStatement ps = null;
			    ps = con.prepareStatement("select 1 from cart where uid = ? and pid = ?");
			    ps.setString(1, uid);
			    ps.setString(2, pid);
			    ResultSet res = ps.executeQuery();
			    while (res.next())
			    {
		    		status = true;
			    }
			    res.close();
			    st.close();
			    ps.close();
			    con.close();            
			}
			catch (Exception e)
			{
			    e.printStackTrace();
			}
			return status;
		}
		
		public static boolean checkPurchaseCart(String uid, String pid, String storelocation){
			boolean status = false;
			try
			{
				Connection con = ConnectionDao.getConnection();
			    Statement st = con.createStatement();  
			    st.execute("USE 4020_a2");
			    PreparedStatement ps = null;
			    ps = con.prepareStatement("select 1 from purchasecart where uid = ? and pid = ? and storelocation= ?");
			    ps.setString(1, uid);
			    ps.setString(2, pid);
			    ps.setString(3, storelocation);
			    ResultSet res = ps.executeQuery();
			    while (res.next())
			    {
		    		status = true;
			    }
			    res.close();
			    st.close();
			    ps.close();
			    con.close();            
			}
			catch (Exception e)
			{
			    e.printStackTrace();
			}
			return status;
		}
		
		public static void removecart(String uid, String pid){
			try
			{
			Connection con = ConnectionDao.getConnection();
		    Statement st = con.createStatement();  
		    st.execute("USE 4020_a2");
		    PreparedStatement ps = null;
		    ps = con.prepareStatement("delete from cart where uid = ? and pid = ?");
		    ps.setString(1, uid);
		    ps.setString(2, pid);
		    ps.executeUpdate();
		    ps = con.prepareStatement("select * from cart");
		    ResultSet res = ps.executeQuery();
		    while (res.next())
		    {
	    	System.out.println(res.getInt("id")+", "+res.getInt("uid")+", "+res.getInt("pid"));
		    }
		    res.close();
	    
	    st.close();
	    ps.close();
	    con.close();            
	}
	catch (Exception e)
	{
	    e.printStackTrace();
	}
		}
		
		
		public static void clearcart(String uid){
			try
			{
			Connection con = ConnectionDao.getConnection();
		    Statement st = con.createStatement();  
		    st.execute("USE 4020_a2");
		    PreparedStatement ps = null;
		    ps = con.prepareStatement("delete from cart where uid = ?");
		    ps.setString(1, uid);
		    ps.executeUpdate();
		    ps = con.prepareStatement("select * from cart");
		    ResultSet res = ps.executeQuery();
		    while (res.next())
		    {
	    	System.out.println(res.getInt("id")+", "+res.getInt("uid"));
		    }
		    res.close();
	    
	    st.close();
	    ps.close();
	    con.close();            
	}
	catch (Exception e)
	{
	    e.printStackTrace();
	}
		}
		
		
		public static void clearpurchasecart(String uid){
			try
			{
			Connection con = ConnectionDao.getConnection();
		    Statement st = con.createStatement();  
		    st.execute("USE 4020_a2");
		    PreparedStatement ps = null;
		    ps = con.prepareStatement("delete from purchasecart where uid = ?");
		    ps.setString(1, uid);
		    ps.executeUpdate();
		    ps = con.prepareStatement("select * from purchasecart");
		    ResultSet res = ps.executeQuery();
		    while (res.next())
		    {
	    	System.out.println(res.getInt("id")+", "+res.getInt("uid"));
		    }
		    res.close();
	    
	    st.close();
	    ps.close();
	    con.close();            
	}
	catch (Exception e)
	{
	    e.printStackTrace();
	}
		}
		
	
		public static void removepurchasecart(String uid, String pid,String storelocation){
			try
			{
			Connection con = ConnectionDao.getConnection();
		    Statement st = con.createStatement();  
		    st.execute("USE 4020_a2");
		    PreparedStatement ps = null;
		    ps = con.prepareStatement("delete from purchasecart where uid = ? and pid = ? and storelocation=?");
		    ps.setString(1, uid);
		    ps.setString(2, pid);
		    ps.setString(3, storelocation);
		    ps.executeUpdate();
		    ps = con.prepareStatement("select * from purchasecart");
		    ResultSet res = ps.executeQuery();
		    while (res.next())
		    {
	    	System.out.println(res.getInt("id")+", "+res.getInt("uid")+", "+res.getInt("pid"));
		    }
		    res.close();
	    
	    st.close();
	    ps.close();
	    con.close();            
	}
	catch (Exception e)
	{
	    e.printStackTrace();
	}
		}
	
	
	

}
