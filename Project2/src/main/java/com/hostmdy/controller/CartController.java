package com.hostmdy.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.hostmdy.model.Cart;
import com.hostmdy.model.Product;
import com.hostmdy.model.ProductDAO;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class CartController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6542104811146948351L;
	
	@Resource(name = "jdbc/PetProductProject")
	private DataSource dataSource;
	
	private ProductDAO productDAO;
	
	@Override
	public void init() throws ServletException {
		productDAO = new ProductDAO(dataSource);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*List<Cart> products=new ArrayList<>();
		HttpSession session = request.getSession();		
		Cart cart = (Cart) session.getAttribute("proudct");		
		request.setAttribute("product",cart);
		
		int id=Integer.parseInt(request.getParameter("id"));
		String productName = request.getParameter("productName");
		int qty = Integer.parseInt(request.getParameter("qty"));
		String description = request.getParameter("description");
		double sellingprice = Double.parseDouble(request.getParameter("sellingprice"));
		boolean discount = Boolean.parseBoolean(request.getParameter("discount"));
		String imageName=request.getParameter("imageName");
		String brandname=request.getParameter("brandName");		
		Product result=new Product(id,productName, qty, description, sellingprice, discount, imageName, brandname);
		products.add(cart);*/
		response.setContentType("text/html;charset=UTF-8");
		try(PrintWriter out=response.getWriter()){
			List<Cart> cartList=new ArrayList<>();
			
			int id=Integer.parseInt(request.getParameter("id"));
			//Product product=productDAO.getResult(id);
			Cart cm=new Cart();
			cm.setId(id);
			cm.setQuantity(1);
			
			
			
			HttpSession session = request.getSession();		
			ArrayList<Cart> cart_list= (ArrayList<Cart>) session.getAttribute("cart");
			 if(cart_list==null) {
				 cartList.add(cm);
				 session.setAttribute("cart", cartList);
				 
				 
			 }
			 else {
				 cartList.addAll(cart_list);
				 cartList.add(cm);
				 session.setAttribute("cart", cartList);
				 
			 }
			 
			 System.out.println("1111111111111111The element in the cart is "+cartList);
			 showResultList(request,response);
			
			
		}
		
		
		
		
		
	}
	
	//custom method
		private void showResultList(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
			HttpSession session = request.getSession();
			Product product = (Product) session.getAttribute("product");
			request.setAttribute("product",product);
			
			List<Product> resultList=this.productDAO.getResultList();
			/*PrintWriter out=response.getWriter();
			
			for(final Pet result: resultList) {
				out.println(result+"\n");
			}*/
			request.setAttribute("resultList", resultList);
			RequestDispatcher rd=request.getRequestDispatcher("ProductListForUser.jsp");
			rd.forward(request, response);
			
			
		}
				
	

}
