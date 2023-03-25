package com.hostmdy.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.sql.DataSource;

import com.hostmdy.model.Pet;
import com.hostmdy.model.User;
import com.hostmdy.model.UserDAO;

/**
 * Servlet implementation class UserController
 */
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(name = "jdbc/PetProductProject")
	private DataSource dataSource;
	private UserDAO userDAO;
	
	@Override
	public void init() throws ServletException {
		userDAO = new UserDAO(dataSource);
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mode = request.getParameter("mode");
		if(mode == null) {
			mode = "SIGNIN";
		}
		
		switch (mode) {
		case "SIGNUP":
			signup(request, response);
			break;
		
		case "SIGNIN":
			signin(request, response);
			break;
			
		case "ALL":
			loadAllUser(request,response);
			break;
			
		case "LOADID":
			loadUserById(request,response);
			break;
		
		case "DELETE":
			deleteUser(request,response);
			
		
			
		default:
			signup(request, response);
			break;
		}
	}

	

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		request.setAttribute("user",user);
		int id = Integer.parseInt(request.getParameter("id"));
		int rowEffected = this.userDAO.deleteResult(id);
		
		if(rowEffected > 0)
			loadAllUser(request, response);

	}

	private void loadUserById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				
				HttpSession session = request.getSession();
				User user = (User) session.getAttribute("user");
				request.setAttribute("user",user);
				int id = Integer.parseInt(request.getParameter("id"));
				User userList=this.userDAO.getResultById(id);
				/*PrintWriter out=response.getWriter();
				
				for(final Pet result: resultList) {
					out.println(result+"\n");
				}*/
				request.setAttribute("userList", userList);
				RequestDispatcher rd=request.getRequestDispatcher("UpdateAdmin.jsp");
				rd.forward(request, response);
		
		
	}

	private void signin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		if(userDAO.isValidUser(email, password)) {
			System.out.println("password correct!!!!!!!!!!!!!!!!");
			RequestDispatcher rd = request.getRequestDispatcher("AdminPage.jsp");
			rd.forward(request, response);
		}
			
		else
		{
			boolean loginFail = true;
			request.setAttribute("loginFail",loginFail);
			RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
			rd.forward(request, response);
		}
		
		
	}

	private void signup(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		boolean adminOk = Boolean.parseBoolean(request.getParameter("role"));
		String role = adminOk ? "admin" : "user";
		
		User user = new User(username, email, password, role);
		
		int rowEffected = this.userDAO.createUser(user);
		
		if(rowEffected > 0)
			loadAllUser(request, response);
	}
	
	private void loadAllUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		request.setAttribute("user",user);
		
		List<User> userList=this.userDAO.getResultList();
		/*PrintWriter out=response.getWriter();
		
		for(final Pet result: resultList) {
			out.println(result+"\n");
		}*/
		request.setAttribute("userList", userList);
		RequestDispatcher rd=request.getRequestDispatcher("AdminList.jsp");
		rd.forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
