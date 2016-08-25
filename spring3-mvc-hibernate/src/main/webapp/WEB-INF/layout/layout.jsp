<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/navbar.css" />
<script src="${pageContext.request.contextPath}/js/jquery-2.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>

<!-- JQuery UI -->
<link rel="stylesheet" type="text/css"
	href="https://code.jquery.com/ui/1.11.4/themes/ui-lightness/jquery-ui.css" />
<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>

<!-- Tree Grid -->
<link rel="stylesheet" href="css/jquery.treegrid.css">

<!-- Depends on  jquery.cookie.js to save state-->
<script type="text/javascript" src="js/jquery.cookie.js"></script>
<script type="text/javascript" src="js/jquery.treegrid.js"></script>

<!-- Bootstrap TreeView Plugin -->
<script src="${pageContext.request.contextPath}/js/bootstrap-treeview.js"></script>

<!-- Link Tables -->
<link rel="stylesheet" type="text/css" href="css/link-tables.css" />

<!-- Bootstrap TreeView Plugin data manipulation -->
<script type="text/javascript">
	$(function() {

		//Creates the menu tree view
		var defaultData = '${menu}';

		$('#treeview1').treeview({
			enableLinks : true,
			data : defaultData
		});

		$('#treeview1').treeview('collapseAll', {
			silent : true
		});

		//Give context to menu tree, with blue color previous clicked menu item 
		var nodeId = getUrlParameter('data-nodeid');

		if (nodeId) {
			var node = $("#treeview1 > ul li[data-nodeid='" + nodeId + "']")[0];

			$(node).css("color", "#FFFFFF");
			$(node).css("background-color", "#428bca;");
		}

		/** Events Section **/

		$("#treeview1 > ul li").on("click", function() {
			var nodeId = $(this).attr('data-nodeid');
			var url = $(this).children("a").attr("href");

			if (url && url != "#") {
				url = url.concat('?data-nodeid=' + nodeId);
				location.href = url;
			}
		});

		$("#treeview1 > ul > li a").on("click", function(event) {
			event.preventDefault();
			var parent = event.target.parent();
			$(parent).click();
		});
	});

	/**
	* this functions returns an url parameter value given is name^
	* this soluction was found on http://stackoverflow.com/questions/19491336/get-url-parameter-jquery
	**/
	function getUrlParameter(sParam) {
		var sPageURL = decodeURIComponent(window.location.search.substring(1)), sURLVariables = sPageURL
				.split('&'), sParameterName, i;

		for (i = 0; i < sURLVariables.length; i++) {
			sParameterName = sURLVariables[i].split('=');

			if (sParameterName[0] === sParam) {
				return sParameterName[1] === undefined ? true
						: sParameterName[1];
			}
		}
	};
</script>
<!-- END Bootstrap TreeView Plugin data manipulation END-->
</head>
<body>
	<div id="content" class="container-fluid">

		<div class="row">
			<tiles:insertAttribute name="nav-content" />
			<!-- Should contain a -->
			<div class="row">
				<div>
					<tiles:insertAttribute name="banner-content" />
				</div>
			</div>
			<div class="row">
				<div>
					<h3>
						<tiles:insertAttribute name="pagetitle-content" />
					</h3>
				</div>
			</div>
			<div class="row">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3>
							<spring:message code="label.addnew" />
						</h3>
					</div>
					<div class="panel-body">
						<div class="col-md-12" id="detailRegion">
							<tiles:insertAttribute name="detail-content" />
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3>
							<spring:message code="label.list" />
						</h3>
					</div>
					<div class="panel-body">
						<div class="col-md-12" id="listRegion">
							<tiles:insertAttribute name="list-content" />
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div id="footer">
					<tiles:insertAttribute name="footer-content" />
				</div>
			</div>
		</div>
		<div class="col-sm-1 col-md-1">
			<!-- little space because of container-fluid -->
		</div>
	</div>
	</div>
	<!-- This div is opened on tiles "nav-content", at least on topNavBar.jsp or asideNavBar, it depends on tiles configuration -->
</body>
</html>