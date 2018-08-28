package com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/servlet2")
public class Servlet2 extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setHeader("Cache-Control","no-cache"); //Forces caches to obtain a new copy of the page from the origin server
		resp.setHeader("Cache-Control","no-store"); //Directs caches not to store the page under any circumstance
		resp.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
		resp.setHeader("Pragma","no-cache"); //HTTP 1.0 backward compatibility
		HttpSession session = req.getSession(false);
		PrintWriter out =resp.getWriter();
		if(session!=null)
		{
			String msg =(String) session.getAttribute("name");
			out.print("<html>");
			out.print("Welcome "+msg);
			out.print("<h1>hi</h1>");
			out.print("<a href='servlet2'>profile</a>");
			out.print("<a href='servlet3'>Logout</a>");
			out.print("</html>");
		}
		else 
		{
			out.print("<h1>Plese Login to continue</h1>");
			req.getRequestDispatcher("index.html").include(req, resp);	
		}
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}

}
