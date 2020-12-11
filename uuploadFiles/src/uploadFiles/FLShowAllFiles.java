package uploadFiles;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FLShowAllFiles extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		

		String p=request.getParameter("p");

        //得到所有可以下载的文件名字
        String realPath = getServletContext().getRealPath("/WEB-INF/file/");
        System.out.println("LSLSLSLSLSLSLSLSLSLSLSLS"+realPath);

		//key:UUID文件名；value：老文件名
		Map<String, String> map = new HashMap<String, String>();
		File file = new File(realPath);
		if(!file.exists()){
			file.mkdirs();
		}
		//进行递归的遍历
		treeWalk(file,map);
		
		request.setAttribute("map", map);
		request.getRequestDispatcher("/"+p).forward(request, response);
		
		
		
	}
	
	//递归遍历，得到最终的文件
		private void treeWalk(File file, Map<String,String> map) {
			File files[] = file.listFiles();
			for(File f:files){
				String guidFileName = f.getName();// LKSDJFLKDSKF_a.txt
				String oldFileName = guidFileName.substring(guidFileName.indexOf("_")+1);// a.txt
//				map.put(guidFileName, oldFileName);
				Long lastModified = f.lastModified();
		        Date date = new Date(lastModified);
		        System.out.println(date);
		        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
		        
		        System.out.println("当前时间为: " + ft.format(date));
		        map.put(oldFileName, ft.format(date));
			}
			
		}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
