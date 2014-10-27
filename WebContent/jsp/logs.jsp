<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<%@include file="common.jsp"%>
<script type="text/javascript">
function showDetail(time, isNew, machine, timeSecond, inc){
	var p_url = '${contextPath}' + '/log/viewDetail.do';
	$('#info').html('');
	var data = 'time='+time/1000+"&"+'isNew='+isNew+"&"+'machine='+machine+"&"+'timeSecond='+timeSecond+"&"+'inc='+inc;
	doAjaxSubmit(p_url, data, p_callback);
}
p_callback = function(response) {
	if (response.result == 0) {
		$('#info').html(response.data);
		$('#error').hide('slow');
		$('#info').show('slow');

	} else {
		$('#error').html("Please correct following errors: " + response.msg);
		$('#info').hide('slow');
		$('#error').show('slow');

	}
};

function goToPage(pageIndex){
	$('#result').html('');
	var paramArray = [];
	$("#params").find("option:selected").each(function(){
		paramArray.push($(this).val());
	});
	var p_url = '${contextPath}' + '/log/showData.do';
	var data = 'device='+$("#device").find("option:selected").val()+'&date='+$("#date").val()+'&pageIndex='+pageIndex;
	var loadi;
	$.ajax({
		type : "POST",
		url : p_url,
        dataType: "json",
        data: data,
		beforeSend : function(XMLHttpRequest) {
			loadi = layer.load('Loading…');
		},
		complete : function(XMLHttpRequest, textStatus) {
			layer.close(loadi);
		},
		error : function() {
			layer.msg('Error occurring!', 2, -1);
		},
		success : function(response){buildSuccessResponse(response, paramArray)}
	});
}

function buildSuccessResponse(response, paramArray) {
	if (response.result == 0) {
		var html = [];
		html.push('<table class="table table-striped table-bordered bootstrap-datatable datatable">');
		html.push('<thead>');
		html.push('<tr>');
		html.push('<th>Serial Number</th>');
		html.push('<th>upload Date Time</th>');
		html.push('<th>server Time</th>');
		for(var j=0;j<paramArray.length;j++){
			html.push(' <th>'+paramArray[j]+'</th>');
		}	
		html.push('<th>Actions</th>');
		html.push('</tr>');
		html.push('</thead>');
		html.push(' <tbody>');
		var item;
		for(var i=0;i<response.data.items.length;i++){
			item = response.data.items[i];
			html.push(' <tr>');
			html.push(' <td class="center">'+i+'</td>');
			html.push(' <td class="center" >');
			if("uploadTime" in item){
				html.push(' <span class="label label-success">'+formatDate(new Date(item.uploadTime),"%H:%m:%s:%S")+'</span>');
			}else{
				html.push(' <span class="label-success"></span>');
			}
			html.push(' </td>');
			html.push(' <td class="center">');
			if("serverTime" in item){
				html.push(' <span class="label label-success">'+formatDate(new Date(item.serverTime),"%H:%m:%s:%S")+'</span>');
			}else{
				html.push(' <span class="label-success"></span>');
			}
			html.push(' </td>');
			for(var j=0;j<paramArray.length;j++){
				html.push(' <td class="center">');
				if(paramArray[j] in item){
					html.push(' <span class="label label-success">'+eval('item.'+paramArray[j])+'</span>');
				}else{
					html.push(' <span class="label-success"></span>');
				}
				html.push(' </td>');								
			}
			html.push(' <td class="center">');
			html.push(' <a class="btn btn-success" href="#" onclick="showDetail('+item._id.time+','+item._id["new"]+','+item._id.machine+','+item._id.timeSecond+','+item._id.inc+')">');
			html.push(' <i class="icon-zoom-in icon-white"></i>  View');
			//html.push(' <a class="btn btn-danger" href="#"><i class="icon-trash icon-white"></i> Delete</a>');
			html.push(' </td> ');
			html.push(' </tr>');
		}
		html.push(' </tbody>');
		html.push('  </table>  ');
		if(response.data.pageCount > 1){
			if (response.data.pageIndex == 1) {
				html.push('<span class="disqp">第一页</span>');
				html.push('&nbsp;&nbsp');
				html.push('<span class="disqp">上一页</span>');
			} else {
				html.push('<a href="javascript:void(0)" class="qp" onclick="goToPage(1)">第一页</a>');
				html.push('&nbsp;&nbsp');
				html.push('<a href="javascript:void(0)" class="qp" onclick="goToPage('+(response.data.pageIndex - 1)+')">上一页</a>');
			}
		}
		html.push('&nbsp;&nbsp');
		// 下一页
		if (response.data.pageCount > 1) {
			if (response.data.pageIndex == response.data.pageCount) {
				html.push('<span class="disqp">下一页</span>');
				html.push('&nbsp;&nbsp');
				html.push('<span class="disqp">末页</span>');
			} else {
				html.push('<a href="javascript:void(0)" class="qp" onclick="goToPage('+(response.data.pageIndex + 1)+')">下一页</a>');
				html.push('&nbsp;&nbsp');
				html.push('<a href="javascript:void(0)" class="qp" onclick="goToPage('+response.data.pageCount+')">末页</a>');
			}
		}
		html.push('&nbsp;&nbsp');
		html.push('<span class="info">共<font color=red>' + response.data.total+ '</font>条&nbsp;&nbsp;共<font color=red>'+ response.data.pageCount + '</font>页&nbsp;&nbsp;当前是第<font color=red>'+ response.data.pageIndex + '</font>页</span>');
		$('#result').html(
			html.join('')
		);
		//datatable
	} else {

	}
}
	$(function(){
		$(".multiselect").multiselect();
		$("#search").click(function(event){
			event.preventDefault();
			goToPage(0);				
		});
		
		$("#clearlogs").click(function(event){
			event.preventDefault();
			$('#result').html('');
			var p_url = '${contextPath}' + '/log/clearLogs.do';
			var data = 'device='+$("#device").find("option:selected").val()+'&date='+$("#date").val();
			var loadi = layer.load('Loading…');
			$.ajax({
				type : "POST",
				url : p_url,
		        dataType: "json",
		        data: data,
				success : function(response) {
					if (response.result == 0) {
						layer.close(loadi);
					}else{
						layer.msg('Error occurring!', 2, -1);
						layer.close(loadi);
					}
					}});
		});
	});
</script>
<title>Logs Data</title>
<body>
	<%@include file="header.jsp"%>
		<div id="tip" class="tips" >
			<div id="info" class="success"></div>
			<div id="error" style="display:none"></div>
		</div>
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
						<a href="#">Logs Data</a>
					</li>
				</ul>
			</div>
		
			<div class="row-fluid sortable">
				<div class="box span12">
					<div class="box-header well" data-original-title>
						<h2><i class="icon-search"></i>Search</h2>
						<div class="box-icon">
							<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a>
							<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
							<a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a>
						</div>
					</div>
					<div class="box-content">
						<form class="form-horizontal" method="post" action="${contextPath}/log/exportExcel.do">
							<fieldset>

							  <div class="control-group">
								<label class="control-label" for="device">Device:</label>
								<div class="controls">
								  <select id="device" name="device">
								  	<c:forEach var="item" items="${devices}" varStatus="status">
										<option value="${item.device}">${item.device}</option>	  	
									</c:forEach>
								  </select>
								</div>
							  </div>
							  
							  <div class="control-group">
							  	<div style="float:left;margin-left:46px;">Choose params:</div>
    							<select id="params" class="multiselect"  multiple="multiple" name="params">
								  	<c:forEach var="item" items="${params}" varStatus="status">
										<option value="${item}" selected="selected">${item}</option>	  	
									</c:forEach>
      							</select>
							 </div>
							<div class="control-group"  style="float:left">
							  <label class="control-label" for="date">log date:</label>
							  <div class="controls">
								<input type="text" class="input-xlarge datepicker" id="date" name="date">
							  </div>
							</div>		
							  <div class="form-actions" style="clear:both">
								<button id="search" class="btn btn-primary">Search</button>
								<input type="submit" id="exportExcel" class="btn btn-primary" value="Export to Excel"/>
								<button id="clearlogs" class="btn btn-primary">clear logs</button>
							  </div>
							</fieldset>
						  </form>
					</div>					
					
					
				</div><!--/span-->

		</div><!--/row-->
    		
		<div id="result" class="cust">
		</div>
		<!-- content ends -->
		</div><!--/#content.span10-->			
			
		</div><!--/fluid-row-->
		<%@include file="footer.jsp"%>
	</div><!--/.fluid-container-->
</body>
</html>