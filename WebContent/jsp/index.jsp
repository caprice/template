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
		<div class="cust box-content">
								<table class="table table-striped table-bordered bootstrap-datatable datatable">
						  <thead>
							  <tr>
								  <th>Serial Number</th>
								  <th>Data</th>
								  <th>Actions</th>
							  </tr>
						  </thead>   
						  <tbody>
						  	 <c:forEach var="item" items="${ngidata}" varStatus="status">
						  	 		<td class="center">${status.count}</td>
						  	 		<td class="center" style="white-space:nowrap;overflow:hidden;">
										<span class="label label-success">${item.year}-${item.month}-${item.day} ${item.hours}:${item.minutes}:${item.seconds}</span>
									</td> 
									<td class="center">
										<a class="btn btn-success" href="#" onclick="showDetail('${item._id}')">
											<i class="icon-zoom-in icon-white"></i>  
											View                                            
										</a>
								   </td>   

								</tr>
							</c:forEach>
						  </tbody>
					  </table>   
		</div>
		<!-- content ends -->
		</div><!--/#content.span10-->			
			
		</div><!--/fluid-row-->
		<%@include file="footer.jsp"%>
	</div><!--/.fluid-container-->
</body>
</html>