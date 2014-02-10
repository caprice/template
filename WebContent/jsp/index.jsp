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
								<label class="control-label" for="vid">Vid:</label>
								<div class="controls">
								  <select id="vid">
									<option>Option 1</option>
									<option>Option 2</option>
									<option>Option 3</option>
									<option>Option 4</option>
									<option>Option 5</option>
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
							  <div style="float:left;margin-left:12px;">Choose which params:</div>
								<div id="sss">
    							<select id="countries" class="multiselect"  multiple="multiple" name="countries[]">
      							  <option value="AFG">Afghanistan</option>
      							  <option value="ALB">Albania</option>
        							<option value="DZA">Algeria</option>
       								 <option value="AND">Andorra</option>
       								 <option value="ARG">Argentina</option>
      	 						 <option value="ARM">Armenia</option>
       							 <option value="ABW">Aruba</option>
        						<option value="AUS">Australia</option>

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
								<button type="submit" class="btn btn-primary">Search</button>
							  </div>
							</fieldset>
						  </form>
					</div>					
					
					
				</div><!--/span-->

		</div><!--/row-->
    		
		<div id="result" class="cust">
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
										<a class="btn btn-danger" href="#">
											<i class="icon-trash icon-white"></i> 
											Delete
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