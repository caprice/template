<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="common.jsp"%>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script type="text/javascript">
	function addNewUser() {
		var p_url = '${contextPath}' + '/user/register.do';
		var jsonuserinfo = $('#form').serializeObject();
		console.log(jsonuserinfo);
		doAjaxSubmit(p_url, jsonuserinfo, p_callback);
	}

	p_callback = function(response) {
		if (response.result == 0) {
			$('#info').html(
					"Plate has been added to the list successfully. -- "
							+ response.data.userDetail.plate);
			$('#error').hide('slow');
			$('#info').show('slow');

		} else {
			errorInfo = "";
			for (i = 0; i < response.data.length; i++) {
				errorInfo += "<br>" + (i + 1) + ". " + response.data[i].code;
			}
			$('#error').html("Please correct following errors: " + errorInfo);
			$('#info').hide('slow');
			$('#error').show('slow');

		}
	};

	function checkAccountExisted(obj) {
		var p_url = '${contextPath}' + '/user/verifyAccount.do';
		var data = {
			'userName' : obj.value,
			'token' : 'ed7be964f32bb873d091d6a059729f88'
		};
		doAjaxSubmit(p_url, data, function(obj) {
			if (obj.result == 0) {

			} else {
				alert(obj.msg);
			}
		});
	}
	
	$(document).ready(function(){
		  $("#uploadPic1").click(function(){
			  var url = '${contextPath}' + '/user/picUpload.do';
			  var data = $('#upload').serializeObject();
			  console.log("fiel",data);
				$.ajax({
					type : "POST",
					url : url,
					data:data,
					enctype: 'multipart/form-data',
					beforeSend : function(XMLHttpRequest) {
						// ShowLoading();
					},
					success : function(data) {
						alert(data);
					},
					complete : function(XMLHttpRequest, textStatus) {
						// HideLoading();
					},
					error : function() {
						// 请求出错处理
					}
				});
		  });
		  
		  
		  $("#addTrip").click(function(){
				var p_url = '${contextPath}' + '/trip/uploadTripData.do';
				var jsonuserinfo = JSON.stringify(${tripJson});
				console.log("abc", JSON.stringify(jsonuserinfo));
				doAjaxSubmitJson(p_url, jsonuserinfo, function(response){
					if (response.result == 0) {
						$('#info').html(response.msg);
						$('#error').hide('slow');
						$('#info').show('slow');

					} else {
						$('#error').html("Please correct errors");
						$('#info').hide('slow');
						$('#error').show('slow');

					}
					
				});
		  });
		});
</script>
<title>用户注册</title>
<body>
	<%@include file="header.jsp"%>
		<div class="container-fluid">
		<div class="row-fluid">
						
			<!-- left menu starts -->
			<%@include file="leftmenu.jsp"%>
			<!-- left menu ends -->
			<div id="content" class="span10">
			<!-- content starts -->

			<div>
				<ul class="breadcrumb">
					<li>
						<a href="#">Home</a> <span class="divider">/</span>
					</li>
					<li>
						<a href="#">用户注册</a>
					</li>
				</ul>
			</div>
					<div id="tip" class="tips" >
			<div id="info" class="success"></div>
			<div id="error" style="display:none"></div>
		</div>		
		<div class="cust box-content">
			<form action="user/addUser" method="post" id="form">
				<table>
					<tr>
						<td>请输入你的用户名 :</td>
						<td><input type="text" id="userName" name="userName"><br />
						<input type="hidden" id="userDetail.userName" name="userDetail.userName">	
						</td>
					</tr>
					<tr>
						<td>请填写一个昵称 :</td>
						<td><input type="text" id="userDetail.nickName" name="userDetail.nickName"><br /></td>
					</tr>
					<tr>
						<td>填写你的个性签名 :</td>
						<td><textarea id="userDetail.signature" name="userDetail.signature" rows="3"
								cols="20"></textarea><br /></td>
					</tr>
					<tr>
						<td><label class="control-label">性别：</label></td>
						<td >
							  <div class="control-group">
								  <label class="radio">
									<input type="radio" name="userDetail.sex" id="sex1" value="0" checked="">
									男
								  </label>
								  <label class="radio">
									<input type="radio" name="userDetail.sex" id="sex2" value="1">
									女
							  </div>	
						</td> 						
					</tr>
					<tr>
						<td>密码 :</td>
						<td><input type="password" id="password" name="password"><br /></td>
					</tr>
					<tr>
						<td>确认密码:</td>
						<td><input type="password" id="rPassword" name="rPassword"><br /></td>
					</tr>
					<tr>
						<td>邮箱:</td>
						<td><input type="text" id="email" name="email"><br /></td>
					</tr>
					<tr>
						<td>手机:</td>
						<td><input type="text" id="userDetail.phone" name="userDetail.phone"><br /></td>
					</tr>
					<tr>
						<td>车牌:</td>
						<td><input type="text" id="userDetail.plate" name="userDetail.plate"><br /></td>
					</tr>
					<tr>
						<td colspan="2">
							<div class="form-actions">
							  <button type="button" class="btn btn-primary" onclick="addNewUser()">Save</button>
							  <button id="addTrip" class="btn btn-primary" type="reset" class="btn">上传trip data</button>
							</div>
						</td>
				</table>
			</form>
		</div>
		<div class="cust">
		<form  action="${contextPath}/user/picUpload.do" method="POST" id="upload" enctype="multipart/form-data">  
   			 username: <input type="text" name="userName"/><br/>  
    		your photo: <input type="file" name="imgFile" id="imgFile"/><br/>  
    		<input type="submit" class="btn btn-primary" id="uploadPic" value="为用户添加图片"/>  
		</form>
		
		</div>
		
		<div class="cust">
		<form  action="${contextPath}/user/chatVoiceUpload.do" method="POST" id="upload" enctype="multipart/form-data">  
   			 username: <input type="text" name="userName"/><br/>  
    		语音: <input type="file" name="voiceFile" id="voiceFile"/><br/>  
    		<input type="submit" class="btn btn-primary" id="uploadVoice" value="上传语音"/>  
		</form>
		
		</div>
		<!-- content ends -->
		</div><!--/#content.span10-->			
			
		</div><!--/fluid-row-->
		<%@include file="footer.jsp"%>
	</div><!--/.fluid-container-->
</body>
</html>