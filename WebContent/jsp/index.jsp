<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="common.jsp"%>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script type="text/javascript">
	function showDetail(id){
		var p_url = '${contextPath}' + '/data/viewDetail.do';
		$('#info').html('');
		var data = 'id='+id;
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
	
	$(function(){
		$(".multiselect").multiselect();
		$("#search").click(function(event){
			event.preventDefault();
			$('#result').html('');
			var paramArray = [];
			$("#params").find("option:selected").each(function(){
				paramArray.push($(this).val());
			});
			
			var p_url = '${contextPath}' + '/data/showData.do';
			$.ajax({
				type : "POST",
				url : p_url,
		        dataType: "json",
				success : function(response) {
					if (response.result == 0) {
						var html = ['<table class="table table-striped table-bordered bootstrap-datatable datatable">'];
						html.push('<thead>');
						html.push('<tr>');
						html.push('<th>Serial Number</th>');
						html.push('<th>Date Time</th>');
						for(var j=0;j<paramArray.length;j++){
							html.push(' <th>'+paramArray[j]+'</th>');
						}	
						html.push('<th>Actions</th>');
						html.push('</tr>');
						html.push('</thead>');
						html.push(' <tbody>');
						for(var i=0;i<response.data.length;i++){
							item = response.data[i];
							var objectId = item._id.time;
							html.push(' <tr>');
							html.push(' <td class="center">'+i+'</td>');
							html.push(' <td class="center" style="white-space:nowrap;overflow:hidden;">');
							html.push(' <span class="label label-success">'+item.year+'-'+item.month+'-'+item.day+'  '+item.hours+':'+item.minutes+':'+item.seconds+'</span>');
							html.push(' </td>');
							for(var j=0;j<paramArray.length;j++){
								html.push(' <td class="center">');
								html.push(' <span class="label label-success">'+eval('item.'+paramArray[j])+'</span>');
								html.push(' </td>');								
							}
							html.push(' <td class="center">');
							html.push(' <a class="btn btn-success" href="#" onclick="showDetail(\''+objectId+'\')">');
							html.push(' <i class="icon-zoom-in icon-white"></i>  View');
							html.push(' <a class="btn btn-danger" href="#"><i class="icon-trash icon-white"></i> Delete</a>');
							html.push(' </td> ');
							html.push(' </tr>');
						}
						html.push(' </tbody>');
						html.push('  </table>  ');
						$('#result').html(
							html.join('')
						);
						//datatable
						$('.datatable').dataTable({
								"bPaginate": true, //翻页功能
								"bLengthChange": true, //改变每页显示数据数量
								"bFilter": false, //过滤功能
								"bSort": false, //排序功能
								"bInfo": true,//页脚信息
								"bAutoWidth": false,//自动宽度
								"sDom": "<'row-fluid'<'span12'l>r>t<'row-fluid'<'span12'i>p>",
								"sPaginationType": "bootstrap",
								"oLanguage": {
								"sLengthMenu": "_MENU_ records per page"
								}
							} );
						
					} else {
						$('#error').html("Please correct following errors: " + response.msg);
						$('#info').show('hide');
						$('#error').show('slow');

					}
				},
			});
			
		});
	})
</script>
<title>NGI Data</title>
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
						<a href="#">NGI Data</a>
					</li>
				</ul>
			</div>
					<div id="tip" class="tips" >
			<div id="info" class="success"></div>
			<div id="error" style="display:none"></div>
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
						<form class="form-horizontal">
							<fieldset>

							  <div class="control-group">
								<label class="control-label" for="vid">Vin:</label>
								<div class="controls">
								  <select id="vid">
								  	<c:forEach var="item" items="${vins}" varStatus="status">
										<option value="${item.vin_2_9};${item.vin_10_17}">${item.vin_2_9}${item.vin_10_17}</option>	  	
									</c:forEach>
								  </select>
								</div>
							  </div>
							
							  <div class="control-group">
								<label class="control-label" for="focusedInput">Focused input</label>
								<div class="controls">
								  <input class="input-xlarge focused" id="focusedInput" type="text" value="This is focused…">
								</div>
							  </div>
							  <div class="control-group">
							  <div style="float:left;margin-left:12px;">Choose params:</div>
								<div id="sss">
    							<select id="params" class="multiselect"  multiple="multiple" name="params">
								  	<c:forEach var="item" items="${params}" varStatus="status">
										<option value="${item}">${item}</option>	  	
									</c:forEach>
      							</select>
								</div>
							</div>
							<div class="control-group">
							  <label class="control-label" for="date">Date:</label>
							  <div class="controls">
								<input type="text" class="input-xlarge datepicker" id="date">
							  </div>
							</div>							  				  
							  <div class="form-actions">
								<button id="search" class="btn btn-primary">Search</button>
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