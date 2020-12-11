package uploadFiles;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;



public class Upload extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//解决上传文件在存放目录中的乱码问题
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String Sno=new String(request.getParameter("Sno").getBytes("iso-8859-1"), "utf-8");
		String Ssubject = new String(request.getParameter("Ssubject").getBytes("iso-8859-1"), "utf-8");
		String Species = new String(request.getParameter("Species").getBytes("iso-8859-1"), "utf-8");
		System.out.println("TUTUTUTUTUTUTUTUTUTU     Sno="+Sno+"Ssubject="+Ssubject);
		
		//检测form是否是multipart/form-data类型的
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if(!isMultipart){
			throw new RuntimeException("The form's enctype attribute value must be multipart/form-data");
		}
		//解析请求内容
		DiskFileItemFactory factory = new DiskFileItemFactory();//产生FileItem的工厂
		//指定上传文件的临时文件存放目录，当上传文件的大小大于缓存区的大小就会产生临时存储区
		factory.setRepository(new File("d:/"));//指定临时文件的存放目录
		ServletFileUpload sfu = new ServletFileUpload(factory);
		
		//如果这两条语句执行的话，那么上传的文件的大小就会受到限制
//		因为此处为了方便，所以没有进行大小的限制
//		sfu.setFileSizeMax(3*1024*1024);//单个文件大小限制
//		sfu.setSizeMax(5*1024*1024);//总文件大小
		
		List<FileItem> items = new ArrayList<FileItem>();
		try {
			items = sfu.parseRequest(request);
		}catch(FileUploadBase.FileSizeLimitExceededException e) {
			response.getWriter().write("单个文件不能超过3M");
		}
		catch(FileUploadBase.SizeLimitExceededException e) {
			response.getWriter().write("总文件不能超过5M");
		}catch (FileUploadException e) {
			e.printStackTrace();
			throw new RuntimeException("解析请求失败");
		}
		//遍历：
		//因为可能存在多个上传文件表单，所以对于每一个表单的内容要进行一一的处理
		for(FileItem item:items){
			//处理普通字段
			if(item.isFormField()){
				processFormField(item);
			}else{
			//处理上传字段
				processUploadField(item,Sno,Ssubject);
			}
			
		
		}
		request.getRequestDispatcher("/ssecond.jsp").forward(request, response);
		
	
	}

	protected void processUploadField(FileItem item, String Sno, String Ssubject) {
		try {
			
			
//			InputStream in = item.getInputStream();
			//找一个存放文件的位置；存放的文件名
			String fileName = item.getName();//上传的文件的文件名  C:\Users\wzhting\Desktop\a.txt  a.txt(浏览器不同)
			if(fileName!=null&&!fileName.equals("")){
				
				//限定上传文件的类型，从他提供的方法中查看他是不是某一类型的文件
				//这个函数如果开启的话，那么就只能上传是图片类型的数据
//				if(!item.getContentType().startsWith("image")){
//					return;
//				}
				
				
				fileName = FilenameUtils.getName(fileName);
				fileName = IdGenertor.genGUID()+"_"+fileName;
				
				//存放路径
				String realPath = getServletContext().getRealPath("/WEB-INF/file/2011/高等数学甲");
				System.out.println(realPath);
				//生成一个子目录,也就是在realPath下面生成新的子目录，并且返回子目录
				//这个地方是根据日期来生成目录
				//如果是根据时间来上传东西的话，那么对于同一天上传的东西，都会在同一个目录下
				//并且每次上传即使是相同的名字，也会产生不同的hashcode从而都能偶得到保存
				String childDirectory = genChildDirectory(realPath,fileName);
				
				//创造新的存放目录
				File storeDirectory = new File(realPath+File.separator+childDirectory);
				//如果目录不存在，那么就创建它
				if(!storeDirectory.exists()){
					storeDirectory.mkdirs();
				}
//				OutputStream out = new FileOutputStream(new File(storeDirectory, fileName));
//				
//				int len = -1;
//				byte b[] = new byte[1024];
//				while((len=in.read(b))!=-1){
//					out.write(b, 0, len);
//				}
//				in.close();
//				out.close();
//				
//				item.delete();//清除临时文件
				
				//这个方法会自动的进行临时文件的清理，也就是上传完毕之后，他会自动的清除临时文件
				item.write(new File(storeDirectory, fileName));
				
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//利用hashcode来生成文件的目录
	private String genChildDirectory(String realPath, String fileName) {
		int hashCode = fileName.hashCode();
		int dir1 = hashCode&0xf;
		int dir2 = (hashCode&0xf0)>>4;
		
		String str = dir1+File.separator+dir2;
		
		File file = new File(realPath,str);
		if(!file.exists()){
			file.mkdirs();
		}
		
		return str;
		
	}

	//按照日期生成子目录
	private String genChildDirectory(String realPath) {
		Date now = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String str = df.format(now);
		
		File file = new File(realPath,str);
		if(!file.exists()){
			file.mkdirs();
		}
		
		return str;
	}

	protected void processFormField(FileItem item) {
		//打印到控制台
		String fieldName = item.getFieldName();
		String fieldValue = "";
		try {
			//这里是为了解决表单提交的汉字在控制台输出乱码的问题，也就是得到表单里边的
			//数据在控制台输出不会乱码
			fieldValue = item.getString("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println(fieldName+"="+fieldValue);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
   this.doGet(request, response);
	}

}
