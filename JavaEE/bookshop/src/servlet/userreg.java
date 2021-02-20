package servlet;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import service.manage_login;
import service.manage_user;

public class userreg extends HttpServlet{
	/** 
     * @param request servlet request
     * @param response servlet response
	 * @throws Exception 
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws Exception{
        response.setContentType("text/html;charset=UTF-8");
        String mesg = "";
        String submit = request.getParameter("Submit");
        manage_user user = new manage_user();
        HttpSession session=request.getSession(true);
        if (submit!=null && !submit.equals("")) {
        	PrintWriter out = response.getWriter();
        	Boolean user_has = (Boolean)session.getAttribute("user_has");
        	if(user.add(request)){
        		session.setAttribute("username",user.getUserName());
        		session.setAttribute( "userid", Integer.toString( user.getUserid() ) ); 
        		out.print("<script>alert('ע��ɹ�,���ص�¼ loading....')</script>");
        		response.setHeader("refresh", "1;/bookshop/login.jsp");
        	} else if (!user.getMessage().equals("")) {
        		mesg = user.getMessage();
        	} else if(user_has) {
        		out.print("<script>alert('���û���ע��')</script>");
        		response.setHeader("refresh", "1;bookshop/userreg.jsp");
        	}
        	else
        		mesg = "ע��ʱ���ִ������Ժ�����";
          if (!mesg.equals("")) 
        	  out.println("<p><font color=#ff0000>"+ mesg + "</font></p>");
        }
    }
    
    /** 
     * @param request servlet request
     * @param response servlet response
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
			processRequest(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /** 
     * @param request servlet request
     * @param response servlet response
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
			processRequest(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /**
     */
    public String getServletInfo(){
        return "Short description";
    }

}
