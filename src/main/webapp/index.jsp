<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   	<%@ page import="A1_Package.Hibernate_entity.Files" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Upload the Docoment</title>
</head>
<body>

<form action="FilesHandler?action=filesUpload" method = "post" enctype = "multipart/form-data">

Select Image<input type = file name = "files" multiple/>
<hr/>
<input type ="submit" value = "upload"/>
<br>
<hr>
<a href="${pageContext.request.contextPath}/FilesHandler?action=listingImages">View available Images</a>


</form>
</body>
</html>