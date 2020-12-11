package uploadFiles;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class addNewDir extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String Sno=new String(request.getParameter("Sno").getBytes("iso-8859-1"), "utf-8");
    	String Species = request.getParameter("Species");  
    	String Ssubject = request.getParameter("filename");  
    	request.setAttribute("Sno", Sno);
    	request.setAttribute("Species", Species);
    	request.setAttribute("Ssubject", Ssubject);
        //得到所有可以下载的文件名字
        String realPath = getServletContext().getRealPath("/WEB-INF/file/"+Sno+"/"+Species+"/"+Ssubject);
        
        System.out.println("ADADADADADAD"+realPath);
        System.out.println("ADADADADADAD:   sno="+Sno+"Species="+Species+"Species="+Ssubject);

		File file = new File(realPath);
		if(!file.exists()){
			file.mkdirs();
		}
		request.getRequestDispatcher("/tsecond.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
