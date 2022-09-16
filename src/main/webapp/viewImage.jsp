<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="Hibernate.DAO.*"  %>
     <%@ page import="A1_Package.Hibernate_entity.*"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Image</title>
</head>
<body>

<%! Files file; String path;%>
<% file = (Files)request.getAttribute("file"); 
   path = (String)request.getAttribute("path");
%>

File Id : <%=file.getId() %> | File Name : <%=file.getFileName() %> |

<%
if(file.getLabel() != null){
	out.print("| Level : "+file.getLabel());
}
%>

<%
if(file.getCaption() != null){
	out.print("| Caption : "+ file.getCaption());
}
%>

<a href="${pageContext.request.contextPath}">Home</a>
<a href="${pageContext.request.contextPath}/FilesHandler?action=listingImages">Images</a>

<hr/>

<img src="<%=path+file.getFileName() %>">



</body>
</html>