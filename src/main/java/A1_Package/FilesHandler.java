	package A1_Package;

import java.io.File;
import java.io.IOException;
import A1_Package.Hibernate_entity.Files;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import Hibernate.DAO.filesDAO;

/*
 * Servlet implementation class ImageUpload
 */

@WebServlet("/FilesHandler")
public class FilesHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public String path = "C:/Images/";
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		switch(action) {
		case "filesUpload":
			filesUpload(request, response);
			break;
		case "updateInformation":
			updateInformation(request,response);
			break;
		default : 
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		
	}
	
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		switch(action) {
		case "listingImages":
			listingImages(request, response);
			break;
		case "viewImage":
			viewImage(request,response);
			break;
		case "deleteImage":
			deleteImage(request,response);
			break;
		default : 
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		
	}
	
	
	
	private void deleteImage(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		int fileId = Integer.parseInt(request.getParameter("fileId"));
		Files file = new filesDAO().getFile(fileId);
		new filesDAO().deleteFile(fileId);
		//Logic for file deletion from file system.
		File fileOnDic = new File(path+file.getFileName());
		if(fileOnDic.delete()) {
			System.out.println("File deleted from database");
		}else {
			System.out.println("File not deleted from database");
		}
		listingImages(request, response);
	}



	private void viewImage(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		int fileId = Integer.parseInt(request.getParameter("fileId"));
		Files file = new filesDAO().getFile(fileId);
		request.setAttribute("file", file);
		request.setAttribute("path", path);
		request.getRequestDispatcher("viewImage.jsp").forward(request, response);
		
	}



	private void updateInformation(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		int fileId =Integer.parseInt(request.getParameter("fileId"));
		String label = request.getParameter("label");
		String caption = request.getParameter("caption");
		String fileName = request.getParameter("fileName");
		Files file = new Files(fileId, fileName, label, caption);
		new filesDAO().updateInformation(fileId,label,caption);//author write updateInformation instead of information
		listingImages(request,response);
	}
	
	
	
	
	private void listingImages(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		List<Files> files = new filesDAO().ListFiles();
		request.setAttribute("files",files);
		request.setAttribute("path", path);
		request.getRequestDispatcher("ListFiles.jsp").forward(request, response);
		
	}

	public void filesUpload(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());//all the logic for manage the file which i upload usign Jsp page
		
		try {
			List<FileItem> images = upload.parseRequest(request);
			
			for(FileItem image : images) {
				String name = image.getName();
				
				try {
				name = name.substring(name.lastIndexOf("\\")+1);
				}catch(Exception e) {}
				File file = new File(path+name);
				if(!file.exists()) {
					//Add file details on databae
					new filesDAO().addFileDetails(new Files(name));
					image.write(file);
				}
				
//				listingImages(request, response);
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		listingImages(request, response);

		
		
	}

}
