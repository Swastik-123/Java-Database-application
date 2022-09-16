<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="A1_Package.Hibernate_entity.Files" %>
    <%@ page import = "java.util.*"  %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List Images</title>
</head>
<body>

<%! String form; int fileId; %>
<%




%>

<!-- We are handling only Image File hare -->
<h1>Listing File</h1>
<table border = "1">
<tr>
<th>Preview</th>
<th>Available information  </th>
<th>Update information  </th>
<th>Available Action  </th>
<% 
	String path = (String) request.getAttribute("path");
	List<Files> files =(List<Files>) request.getAttribute("files");  
	for(Files file : files){
		out.println("<tr><td><img src="+path+file.getFileName()+" height=200></td>");
		
		out.print("<td><ul>"+
		"<li>File Id:"+file.getId()+"</li>"+
		"<li>File Name:"+file.getFileName()+"</li>"+
		"<li>File Level:"+file.getLabel()+"</li>"+
		"<li>File Caption:"+file.getCaption()+"</li>"+
		"</ul></td>"
		);
		fileId = file.getId();
		String form = "<form action='FilesHandler' method = 'post'>"+
				  "Label: <input type='text' name='label'/><br/><br/>"+
				  "Caption: <input type'text' name ='caption'/><br/><br/>"+
				  "<input type='hidden' name='fileId' value='"+fileId+"'/>"+
				  "<input type='hidden' name='action' value='updateInformation'/>"+
				  "<input type='hidden' name='fileName' value='"+file.getFileName()+"'/>"+
				  "<input type='submit' value='Update'>"+
				  "</form>";
		out.print("<td>"+form+"</td>");
		
		out.print("<td><ul><li><a href='"+request.getContextPath()+"/FilesHandler?action=viewImage&fileId="+
				file.getId() +"'>View Image</a></li>");
				
		out.print("<li><a href='"+request.getContextPath()+"/FilesHandler?action=deleteImage&fileId="+
				file.getId() 
		+"' onclick=\"if(!confirm('Are you sure you want to delete the user?')) return false\">Delete</a></li></ul></td></tr>");
	}
// when you double code("") inside double code(" "" ") this time use \ double code like this"( \" \" )"
%>








</table>

</body>
</html>