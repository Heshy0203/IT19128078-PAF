package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProjectAPI
 */
@WebServlet("/ProjectAPI")
public class ProjectAPI extends HttpServlet {
	
	
	Project projectObj =new Project();
       
    
    public ProjectAPI() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String output = projectObj.insertProject(request.getParameter("projectCode"), 
				request.getParameter("projectName"), 
				request.getParameter("researcherID"),
				request.getParameter("projectDesc"),
				request.getParameter("categoryID"),
				request.getParameter("projectRemarks")); 
				response.getWriter().write(output); 
				
				
	}

	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap(request); 
		 String output = projectObj.updateProject(paras.get("hidProjectIDSave").toString(), 
		 paras.get("projectCode").toString(),
		 paras.get("projectName").toString(),
		 paras.get("researcherID").toString(),
		 paras.get("projectDesc").toString(), 
		paras.get("categoryID").toString(), 
		paras.get("projectRemarks").toString()); 
		response.getWriter().write(output); 
	}

	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap(request); 
		 String output = projectObj.deleteProject(paras.get("projectID").toString()); 
		response.getWriter().write(output);
	}
	
	private static Map getParasMap(HttpServletRequest request) 
	{ 
	 Map<String, String> map = new HashMap<String, String>(); 
	try
	 { 
	 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8"); 
	 String queryString = scanner.hasNext() ? 
	 scanner.useDelimiter("\\A").next() : ""; 
	 scanner.close(); 
	 String[] params = queryString.split("&"); 
	 for (String param : params) 
	 { 
	String[] p = param.split("=");
	 map.put(p[0], p[1]); 
	 } 
	 } 
	catch (Exception e) 
	 { 
	 } 
	return map; 
	}
	
	
	

}
