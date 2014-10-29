<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="common.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="http://static.blog.csdn.net/css/notice.css" rel="stylesheet"
	type="text/css">
<title>登录错误</title>
<style type="text/css">
iframe.dealply-toast {
	right: -99999px !important;
}

iframe.dealply-toast.fastestext-revealed {
	right: 0px !important;
	margin-bottom: 0px !important;
}
a:link {
text-decoration: underline;
}
</style>
</head>
<body>


	<div class="full" style="margin-top:15%">
		<div class="content_login">
			<div class="top_bg"></div>
			<div class="notice">
				<p class="error">
					<img src="http://static.blog.csdn.net/images/face_error.gif"><span>用户名密码错误,请重新登录！
						<a class="smarterwiki-linkify" href="${contextPath}/login.do">login</a>
					</span>
				</p>
			</div>
			<div class="btm_bg"></div>
		</div>
	</div>


	<img src="http://counter.csdn.net/pv.aspx?id=25" border="0" width="0"
		height="0">
	<link rel="stylesheet" type="text/css"
		href="//csdnimg.cn/pubfooter/css/pub_footer_2012.css">

</body>
</html>