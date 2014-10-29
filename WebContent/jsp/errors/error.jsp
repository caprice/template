<%@ page language="java" import="org.apache.log4j.Logger" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>页面出错啦</title>
	<%@include file="../common.jsp"%>
</head>
<body>
页面出错了:<br>
${exceptionMessage}
</body>
</html>