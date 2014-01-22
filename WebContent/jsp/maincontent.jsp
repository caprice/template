<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>template</title>
<meta name="description" content="" />
<meta name="keywords" content="" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="chrome=1" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<link type="text/css" rel="stylesheet" rev="stylesheet" media="screen"
	href="${contextPath}/css/default.css" />
<!--[if IE]>
<link href="./css/ie.css" />
<![endif]-->
<!--[if IE 6]>
<link href="./css/ie6.css" rel="stylesheet" type="text/css" media="screen" />
<![endif]-->

<!-- /Sub menus -->
<script type="text/javascript"
	src="${contextPath}/js/jquery.hoverIntent.minified.js"></script>
<script type="text/javascript"
	src="${contextPath}/js/jquery.ba-resize.min.js"></script>
<script type="text/javascript" src="${contextPath}/js/magic.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		//Align the sub meu to their menu items
		$("#subItem2").align({
			top : {
				at : 1,
				selector : '#mainMenu'
			},
			right : "#content",
			left : "#menuItem2"
		});
		$("#subItem3").align({
			top : {
				at : 1,
				selector : '#mainMenu'
			},
			right : "#content",
			left : "#menuItem3"
		});
		$("#subItem4").align({
			top : {
				at : 1,
				selector : '#mainMenu'
			},
			right : "#content",
			left : "#menuItem4"
		});

		//Associate menu item and submenus
		$("#menuItem2").setSubMenu('subItem2');
		$("#menuItem3").setSubMenu('subItem3');
		$("#menuItem4").setSubMenu('subItem4');

		var itemsCount = $("#leftNav >li").length;
		for ( var i = 1; i <= itemsCount; i++) {
			$('#subMenu' + i).setSubMenu({
				id : 'subCat' + i,
				action : 'click',
				openFunction : function(me) {
					if (me.hasClass('hidden')) {
						me.slideUp()
						me.removeClass("hidden");
					}
					me.slideDown();
				},
				closeFunction : function(me) {
					me.slideUp();
				}
			});
		}
		$("#leftNav").openMenu();
	});
</script>
</head>
<body>
	<div id="header" class="bkgDark whiteLink">
		<div class="bkgLighter tCenter">
			<div class="center siteWidth tLeft clearfix pushUp pushDown">
				<div class="h1 left w35 tWhite">
					<a href="http://www.binarymind.org">logo</a>
				</div>
				<form id="mainSearchForm" method="get" action="search" name="search"
					class="w25 right">
					<input type="submit"
						class="darkButton right marginLeft height30 button" value="Go">
						<input type="text" accesskey="f" id="searchinput" name="q"
						size="20" maxlength="255"
						class="right curved borderL w56 height20">
				</form>
			</div>
		</div>

		<div class="bkgMedium tCenter">
			<div class="center tLeft siteWidth clearfix whiteLink">
				<ul id="mainMenu" class="height30 horizontalList menu">
					<li class="selected bkgWhite"><a href="">Home</a></li>
					<li id="menuItem2"><a href="">menu Item 2</a></li>
					<li id="menuItem3"><a href="">menu Item 3</a></li>
					<li id="menuItem4"><a href="">menu Item 4</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div id="content"
		class="center siteWidth clearfix relative tLeft bkgWhite">
		<div class="relative">
			<div class="block">
				<div class="clearfix pushUp">
					<div class="w23 left tLeft ">
						<div class="right w34 editText">
							<h1 class="w1 pushDown ">Here is some content ;)</h1>
							<div class="pushUp">
								<p>
									<img class="w14 pushRight left"
										src="http://www2.cnrs.fr/sites/communique/image/mona_unvarnish_web_image.jpg"
										title="La Joconde"> Lorem Ipsum is simply dummy text of
										the printing and typesetting industry. Lorem Ipsum has been
										the industry's standard dummy text ever since the 1500s, when
										an unknown printer took a galley of type and scrambled it to
										make a type specimen book. It has survived not only five
										centuries, but also the leap into electronic typesetting,
										remaining essentially unchanged. It was popularised in the
										1960s with the release of Letraset sheets containing Lorem
										Ipsum passages, and more recently with desktop publishing
										software like Aldus PageMaker including versions of Lorem
										Ipsum. 
								</p>
								<p>Lorem Ipsum is simply dummy text of the printing and
									typesetting industry. Lorem Ipsum has been the industry's
									standard dummy text ever since the 1500s, when an unknown
									printer took a galley of type and scrambled it to make a type
									specimen book. It has survived not only five centuries, but
									also the leap into electronic typesetting, remaining
									essentially unchanged. It was popularised in the 1960s with the
									release of Letraset sheets containing Lorem Ipsum passages, and
									more recently with desktop publishing software like Aldus
									PageMaker including versions of Lorem Ipsum.</p>
								<p>Lorem Ipsum is simply dummy text of the printing and
									typesetting industry. Lorem Ipsum has been the industry's
									standard dummy text ever since the 1500s, when an unknown
									printer took a galley of type and scrambled it to make a type
									specimen book. It has survived not only five centuries, but
									also the leap into electronic typesetting, remaining
									essentially unchanged. It was popularised in the 1960s with the
									release of Letraset sheets containing Lorem Ipsum passages, and
									more recently with desktop publishing software like Aldus
									PageMaker including versions of Lorem Ipsum. Lorem Ipsum is
									simply dummy text of the printing and typesetting industry.
									Lorem Ipsum has been the industry's standard dummy text ever
									since the 1500s, when an unknown printer took a galley of type
									and scrambled it to make a type specimen book. It has survived
									not only five centuries, but also the leap into electronic
									typesetting, remaining essentially unchanged. It was
									popularised in the 1960s with the release of Letraset sheets
									containing Lorem Ipsum passages, and more recently with desktop
									publishing software like Aldus PageMaker including versions of
									Lorem Ipsum.</p>
								<p>Lorem Ipsum is simply dummy text of the printing and
									typesetting industry. Lorem Ipsum has been the industry's
									standard dummy text ever since the 1500s, when an unknown
									printer took a galley of type and scrambled it to make a type
									specimen book. It has survived not only five centuries, but
									also the leap into electronic typesetting, remaining
									essentially unchanged. It was popularised in the 1960s with the
									release of Letraset sheets containing Lorem Ipsum passages, and
									more recently with desktop publishing software like Aldus
									PageMaker including versions of Lorem Ipsum.</p>
							</div>
						</div>
						<div class="left w14">
							<ul id="leftNav" class="verticalList">
								<li id="subMenu1" class="pushDown"><a href=""
									class="tLoud tMedium">Some category 1</a>
									<ul id="subCat1"
										class="verticalList arrows padding lineHeight20 hidden ">
										<li><a href="">some subCategory</a></li>
										<li><a class="tLoud tColor" href="">some subCategory</a></li>
										<li><a href="#">some subCategory</a></li>
										<li><a href="">some subCategory</a></li>
										<li><a href="">some subCategory</a></li>
									</ul></li>
								<li id="subMenu2" class="pushDown"><a href=""
									class="tLoud tMedium">Some category 2</a>
									<ul id="subCat2"
										class="verticalList arrows padding lineHeight20 hidden ">
										<li><a href="">some subCategory</a></li>
										<li><a href="">some subCategory</a></li>
										<li><a href="">some subCategory</a></li>
										<li><a href="">some subCategory</a></li>
										<li><a href="">some subCategory</a></li>
									</ul></li>
								<li id="subMenu3" class="pushDown"><a href=""
									class="tLoud tMedium">Some category 3</a>
									<ul id="subCat3"
										class="verticalList arrows padding lineHeight20 hidden ">
										<li><a href="">some subCategory</a></li>
										<li><a href="">some subCategory</a></li>
										<li><a href="">some subCategory</a></li>
										<li><a href="">some subCategory</a></li>
										<li><a href="">some subCategory</a></li>
									</ul></li>
								<li id="subMenu4" class="pushDown"><a href=""
									class="tLoud tMedium">Some category 4</a>
									<ul id="subCat4"
										class="verticalList arrows padding lineHeight20 hidden ">
										<li><a href="">some subCategory</a></li>
										<li><a href="">some subCategory</a></li>
										<li><a href="">some subCategory</a></li>
										<li><a href="">some subCategory</a></li>
										<li><a href="">some subCategory</a></li>
									</ul></li>
								<li id="subMenu5"><a href="" class="tLoud tMedium">Some
										category 5</a>
									<ul id="subCat5"
										class="verticalList arrows padding lineHeight20 hidden ">
										<li><a href="">some subCategory</a></li>
										<li><a href="">some subCategory</a></li>
										<li><a href="">some subCategory</a></li>
										<li><a href="">some subCategory</a></li>
										<li><a href="">some subCategory</a></li>
									</ul></li>
							</ul>
						</div>
					</div>
					<div class="w13 right">
						<div class="height300 bkgDarker">Advertising</div>
						<div class="pushUp">
							<h3 class="h1">Some block here</h3>
							<div class="clearfix pushDown">
								<div class="clearfix tCenter">
									<div class="left w14 tCenter">
										<a href="http://www.binarymind.org" title="Binarymind.org">
											<img class="w1"
											src="http://www2.cnrs.fr/sites/communique/image/mona_unvarnish_web_image.jpg"
											title="La Joconde">
										</a>
									</div>
									<div class="left w14 tCenter">
										<a href="http://www.binarymind.org" title="Binarymind.org">
											<img class="w1"
											src="http://www2.cnrs.fr/sites/communique/image/mona_unvarnish_web_image.jpg"
											title="La Joconde">
										</a>
									</div>
									<div class="left w14 tCenter">
										<a href="http://www.binarymind.org" title="Binarymind.org">
											<img class="w1"
											src="http://www2.cnrs.fr/sites/communique/image/mona_unvarnish_web_image.jpg"
											title="La Joconde">
										</a>
									</div>
									<div class="left w14 tCenter">
										<a href="http://www.binarymind.org" title="Binarymind.org">
											<img class="w1"
											src="http://www2.cnrs.fr/sites/communique/image/mona_unvarnish_web_image.jpg"
											title="La Joconde">
										</a>
									</div>
								</div>
								<ul class="w1 hoverList verticalList">
									<li><a href="http://www.binarymind.org"> Lorem ipsum
											dolor sit amet, co</a></li>
									<li><a href="http://www.binarymind.org"> Lorem ipsum
											dolor sit ameipsum dolor sitt, co</a></li>
									<li><a href="http://www.binarymind.org"> Lorem ipsum
											dolor sit amet, coLorem ipsum dolor sit amet, co</a></li>
									<li><a href="http://www.binarymind.org"> Lorem ipsum
											dolor sit amet, co</a></li>
									<li><a href="http://www.binarymind.org"> Lorem ipsum
											dolor sit amipsum dolor sitet, co</a></li>
									<li><a href="http://www.binarymind.org"> Lorem ipsum
											dolorm dol sit amet, co</a></li>
									<li><a href="http://www.binarymind.org"> Lorem ipsum
											dolor sit ameipsum dolor sitt, co</a></li>
									<li><a href="http://www.binarymind.org"> Lorem ipsum
											dolor sit am dolmet, co</a></li>
									<li><a href="http://www.binarymind.org"> Lorem dolm
											ipsum dolor sit amet, co</a></li>

								</ul>
							</div>
							<a class="readMore" href="">Read more</a>
						</div>
						<div class="pushUp">
							<h3 class="h1">Some other block here</h3>
							<div class="clearfix pushDown">
								<div class="clearfix tCenter pushDown">
									<div class="left w13 tCenter">
										<a href="http://www.binarymind.org" title="Binarymind.org">
											<img class="w1"
											src="http://www2.cnrs.fr/sites/communique/image/mona_unvarnish_web_image.jpg"
											title="La Joconde">
										</a>
									</div>
									<div class="left w13 tCenter">
										<a href="http://www.binarymind.org" title="Binarymind.org">
											<img class="w1"
											src="http://www2.cnrs.fr/sites/communique/image/mona_unvarnish_web_image.jpg"
											title="La Joconde">
										</a>
									</div>
									<div class="left w13 tCenter">
										<a href="http://www.binarymind.org" title="Binarymind.org">
											<img class="w1"
											src="http://www2.cnrs.fr/sites/communique/image/mona_unvarnish_web_image.jpg"
											title="La Joconde">
										</a>
									</div>
									<div class="left w13 tCenter">
										<a href="http://www.binarymind.org" title="Binarymind.org">
											<img class="w1"
											src="http://www2.cnrs.fr/sites/communique/image/mona_unvarnish_web_image.jpg"
											title="La Joconde">
										</a>
									</div>
									<div class="left w13 tCenter">
										<a href="http://www.binarymind.org" title="Binarymind.org">
											<img class="w1"
											src="http://www2.cnrs.fr/sites/communique/image/mona_unvarnish_web_image.jpg"
											title="La Joconde">
										</a>
									</div>
									<div class="left w13 tCenter">
										<a href="http://www.binarymind.org" title="Binarymind.org">
											<img class="w1"
											src="http://www2.cnrs.fr/sites/communique/image/mona_unvarnish_web_image.jpg"
											title="La Joconde">
										</a>
									</div>
								</div>
								<a class="readMore" href="">Read more</a>
							</div>
						</div>
					</div>
				</div>
			</div>

		</div>
	</div>
	<div id="footer" class="bkgDark">footer we set 500px height</div>
	<!-- Sub menus wherever you want in the page code (cool for SEO) -->
	<div id="subItem2" class="clearfix subMenu bkgMedium hidden">
		<div class="padding">This is the sub menu for menu item2</div>
	</div>
	<div id="subItem3" class="clearfix subMenu bkgMedium hidden">
		<div class="padding">This is the sub menu for menu item3</div>
	</div>
	<div id="subItem4" class="clearfix subMenu bkgMedium hidden">
		<div class="padding">This is the sub menu for menu item4</div>
	</div>
</body>
</html>
