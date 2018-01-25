package com.jdbcexample;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class ViewDetails
 */
@WebServlet("/ViewDetails")
public class ViewDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ViewDetails() {
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  final String QUERY = "select * from User";
		  List<Entity> list=new ArrayList<Entity>();
		  try{
			  Connection con = DButil.getConnection();
			  Statement stmt = con.createStatement();
			  ResultSet rs = stmt.executeQuery(QUERY);
			  while(rs.next()){
				  Entity e1= new Entity();
				  int id = rs.getInt("id");
				  e1.setFirstame(rs.getString("firstname"));
				  e1.setLastname(rs.getString("lastname"));
				  String firstname=rs.getString("firstname");
				  String lastname=rs.getString("lastname");
				  System.out.println(id + "," +firstname+ "," +lastname);
				  list.add(e1);
				 
			  }
			  
		  } catch (SQLException e) {
				e.printStackTrace();
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}
for (Entity entity : list) {
	System.out.println(entity.getFirstame());
	System.out.println(entity.getLastname());
	
}

String json = new Gson().toJson(list);
response.setContentType("application/json");
response.setCharacterEncoding("UTF-8");
response.getWriter().write(json);
	}
}
