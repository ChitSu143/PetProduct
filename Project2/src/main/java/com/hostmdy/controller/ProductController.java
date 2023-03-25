package com.hostmdy.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import javax.sql.DataSource;

import com.hostmdy.model.Pet;
import com.hostmdy.model.PetDAO;
import com.hostmdy.model.Product;
import com.hostmdy.model.ProductDAO;
import com.hostmdy.model.User;
import com.hostmdy.model.UserDAO;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;


@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50)
public class ProductController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2477735402691979277L;
	
	public static final String UPLOAD_DIR = "images";
	 public String dbFileName = "";
	
	@Resource(name = "jdbc/PetProductProject")
	private DataSource dataSource;
	
	private ProductDAO productDAO;
	
	@Override
	public void init() throws ServletException {
		productDAO = new ProductDAO(dataSource);
	}
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//showResultList(request, response);
		String mode=request.getParameter("mode");
		System.out.println("Mode is ::::"+mode+":::::::::::::");
		if (mode==null) {
			mode="CREATE";
		}
				switch (mode) {
				case "LIST": {
					
					showResultList(request, response);
					break;
				}
				case "LISTPRO": {
					
					showResultListForUser(request, response);
					break;
				}
				case "LOADID": {
					
					loadResultById(request, response);
					break;
				}
				case "CREATE":{
					createProduct(request, response);
					break;
				}
				case "UPDATE":{
					updateResult(request, response);
					break;
				}
				
				case "DELETE":{
					deleteResult(request, response);
					break;
				}
				
				case "ALL":{
					showAllAction(request, response);
					break;
				}
					
				default:
					createProduct(request, response);
					break;
				}
	}

	private void showResultListForUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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


	private void showAllAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd=request.getRequestDispatcher("AdminPage.jsp");
		
		rd.forward(request, response);
		
	}


	private void deleteResult(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		Product pet = (Product) session.getAttribute("product");
		request.setAttribute("pet",pet);
		int id = Integer.parseInt(request.getParameter("id"));
		int rowEffected = this.productDAO.deleteResult(id);
		
		if(rowEffected > 0)
			showResultList(request, response);
		
	}


	private void updateResult(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Part part = request.getPart("imageName");
		System.out.println("Part is :"+part.toString());
		String fileName = extractFileName(part);
		System.out.println("fileName is +"+fileName);
		if(fileName != "") {
			
			//A- A-R
			String applicationPath ="D:\\Servlet Project\\PetProductProject\\src\\main\\webapp\\";
//			String applicationPath = getServletContext().getRealPath("");
			String uploadPath = applicationPath+ UPLOAD_DIR;
			
			File fileUploadDir = new File(uploadPath);
			
			if(!fileUploadDir.exists()) {
				fileUploadDir.mkdirs();
			}
			
			String savePath = uploadPath + File.separator + fileName;
			System.out.println("save path is"+savePath+";;;;;;;;;;;");
			part.write(savePath );
			
		}
		
		
		HttpSession session = request.getSession();		
		Product product = (Product) session.getAttribute("product");		
		request.setAttribute("product",product);
		
		int id=Integer.parseInt(request.getParameter("id"));
		String productName = request.getParameter("productName");
		int qty = Integer.parseInt(request.getParameter("qty"));
		String description = request.getParameter("description");
		double sellingprice = Double.parseDouble(request.getParameter("sellingprice"));
		boolean discount = Boolean.parseBoolean(request.getParameter("discount"));
		String imageName=null;
		if (fileName=="")
		{
			Product pro=this.productDAO.getResult(id);
			imageName=pro.getImageName();
			System.out.println("Inside If imageName is"+imageName+";;;;;;;;;;");
		}
		else
		{
			imageName=fileName;
			System.out.println("Inside else imageName is"+imageName+";;;;;;;;;;");
		}
		
		String brandname=request.getParameter("brandName");
	
		Product result=new Product(id,productName, qty, description, sellingprice, discount, imageName, brandname);
		
		
		int rowEffected = this.productDAO.updateResult(result);
		
				
		if(rowEffected > 0)
			showResultList(request, response);
	}

	
	private void createProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		 
				Part part = request.getPart("imageName");
				String fileName = extractFileName(part);
				
				HttpSession session = request.getSession();
				Product product = (Product) session.getAttribute("product");
				request.setAttribute("product",product);
				String productName = request.getParameter("productName");
				int qty = Integer.parseInt(request.getParameter("qty"));
				String description = request.getParameter("description");
				double sellingprice = Double.parseDouble(request.getParameter("sellingprice"));
				boolean discount = Boolean.parseBoolean(request.getParameter("discount"));
				String imageName=fileName;
				System.out.println("imageName"+imageName+";;;;;;;;;;;;;");
				
				String brandname=request.getParameter("brandName");
				
				String applicationPath ="D:\\Servlet Project\\PetProductProject\\src\\main\\webapp\\";
//				String applicationPath = getServletContext().getRealPath("");
				String uploadPath = applicationPath+ UPLOAD_DIR;
				
				File fileUploadDir = new File(uploadPath);
				
				if(!fileUploadDir.exists()) {
					fileUploadDir.mkdirs();
				}
				
				String savePath = uploadPath + File.separator + fileName;
				System.out.println("save path is"+savePath+";;;;;;;;;;;");
				part.write(savePath );
				
				
		        
				// TODO Auto-generated method stub
				
				
				
				Product result=new Product(productName, qty, description, sellingprice, discount, imageName, brandname);
				
				int rowEffected = this.productDAO.createProduct(result);
				
				if(rowEffected > 0)
					showResultList(request, response);
		
		
		
		
		
		
		
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
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
		RequestDispatcher rd=request.getRequestDispatcher("ProductList.jsp");
		rd.forward(request, response);
		
		
	}
	
	private void loadResultById(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		int id=Integer.parseInt(request.getParameter("id"));
		Product product=this.productDAO.getResult(id);
		request.setAttribute("product", product);
		RequestDispatcher rd=request.getRequestDispatcher("UpdateProduct.jsp");
		rd.forward(request, response);
		
		
	}
	
	private String extractFileName(Part part) {//This method will print the file name.
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }
	
	
		
	
	
	
	
	
}
