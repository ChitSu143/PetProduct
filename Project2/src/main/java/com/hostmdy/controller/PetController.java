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
import com.hostmdy.model.PetDAO;







/**
 * Servlet implementation class PetController
 */

public class PetController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(name = "jdbc/PetProductProject")
	private DataSource dataSource;
	
	private PetDAO petDAO;
	
	@Override
	public void init() throws ServletException {
		petDAO = new PetDAO(dataSource);
	}
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PetController() {
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
				case "LOAD": {
					
					loadResultById(request, response);
					break;
				}
				case "CREATE":{
					createResult(request, response);
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
					createResult(request, response);
					break;
				}
	}

	private void showAllAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd=request.getRequestDispatcher("AdminPage.jsp");
		
		rd.forward(request, response);
		
	}


	private void deleteResult(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		Pet pet = (Pet) session.getAttribute("pet");
		request.setAttribute("pet",pet);
		int id = Integer.parseInt(request.getParameter("id"));
		int rowEffected = this.petDAO.deleteResult(id);
		
		if(rowEffected > 0)
			showResultList(request, response);
		
	}


	private void updateResult(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		Pet pet = (Pet) session.getAttribute("pet");
		request.setAttribute("pet",pet);
		int id = Integer.parseInt(request.getParameter("id"));
		String petName = request.getParameter("petName");
		int petAge = Integer.parseInt(request.getParameter("petAge"));
		String ownerName = request.getParameter("ownerName");
		double ownerAge = Double.parseDouble(request.getParameter("ownerAge"));
		boolean purchased = Boolean.parseBoolean(request.getParameter("purchasedStatus"));
		
		Pet result = new Pet(id,petName,petAge,ownerName,ownerAge,purchased);
		
		int rowEffected = this.petDAO.updateResult(result);
		
				
		if(rowEffected > 0)
			showResultList(request, response);
	}


	private void createResult(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		Pet pet = (Pet) session.getAttribute("pet");
		request.setAttribute("pet",pet);
		String petName = request.getParameter("petName");
		int petAge = Integer.parseInt(request.getParameter("petAge"));
		String ownerName = request.getParameter("ownerName");
		double ownerAge = Double.parseDouble(request.getParameter("ownerAge"));
		boolean purchased = Boolean.parseBoolean(request.getParameter("purchasedStatus"));
		
		Pet result = new Pet(petName,petAge,ownerName,ownerAge,purchased);
		
		int rowEffected = this.petDAO.createResult(result);
		
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
		Pet pet = (Pet) session.getAttribute("pet");
		request.setAttribute("pet",pet);
		
		List<Pet> resultList=this.petDAO.getResultList();
		/*PrintWriter out=response.getWriter();
		
		for(final Pet result: resultList) {
			out.println(result+"\n");
		}*/
		request.setAttribute("resultList", resultList);
		RequestDispatcher rd=request.getRequestDispatcher("Member.jsp");
		rd.forward(request, response);
		
		
	}
	
	private void loadResultById(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		int id=Integer.parseInt(request.getParameter("id"));
		Pet result=this.petDAO.getResult(id);
		request.setAttribute("pet", result);
		RequestDispatcher rd=request.getRequestDispatcher("UpdateMember.jsp");
		rd.forward(request, response);
		
		
	}
	
	
		
	
	
	
	

}
