package com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/servlet1")
public class Servlet1  extends HttpServlet{

	@Override
	protected void 
	doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		resp.setHeader("Cache-Control","no-cache"); //Forces caches to obtain a new copy of the page from the origin server
		resp.setHeader("Cache-Control","no-store"); //Directs caches not to store the page under any circumstance
		resp.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
		resp.setHeader("Pragma","no-cache"); //HTTP 1.0 backward compatibility
		resp.setContentType("text/html");;
		String name=req.getParameter("name");
		String pass=req.getParameter("pass");
		PrintWriter out = resp.getWriter();
		if(pass.equals("123"))
		{

			HttpSession session = req.getSession(true);
			session.setAttribute("name",name);
			req.getRequestDispatcher("servlet2").forward(req, resp);
			
		}
		else
		{
			out.print("<h1>Wrong Password</h1>");
			req.getRequestDispatcher("index.html").include(req, resp);
		}


	}
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	doGet(req,resp);
}

}
