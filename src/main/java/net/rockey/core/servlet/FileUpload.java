package net.rockey.core.servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.rockey.core.util.PropertyUtils;

import org.apache.commons.fileupload.DiskFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUpload extends HttpServlet {

	private static Logger log = LoggerFactory.getLogger(FileUpload.class);
	
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		log.info("{}", "调用Servlet的doGet方法");
		String fileName = request.getParameter("file_name"); // 源文件名称
		String newFileName = ""; // 新文件名称
		String responseMsg = ""; // 返回消息
		String currFilePath = ""; // 新文件路径
		String localFolder = PropertyUtils.getInstance().getValue(
				"file.load.path")
				+ File.separator + "down" + File.separator;
		
		File f = new File(localFolder);
		if (f.exists()) {
			f.mkdirs();
		}
		DiskFileUpload upload = new DiskFileUpload();
		
		
		
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
