<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${!empty menu}">

	<style>
		label, input { display:block; }
	    input.text { margin-bottom:12px; width:95%; padding: .4em; }
	    fieldset { padding:0; border:0; margin-top:25px; }
	</style>

	<script type="text/javascript">
	$(document).ready(function() {

		/** May be not necessary **/
		jQuery("#formMenuItemUpdate").attr("accept-charset", "UTF-8");
		jQuery("#formMenuItemAddChild").attr("accept-charset", "UTF-8");

		$('.tree').treegrid({
			'initialState': 'collapsed',
		    'saveState': true
		});

		var target = $(this);

		//Create the update dialog
		var dialogNumber = "";

		var actionButtonName = '<spring:message code="label.updatemenu"/>';
		var actionButtonFunction = submitForm;

		var dialog = createJqueryDialog(dialogNumber, actionButtonName, actionButtonFunction, target);

		dialogNumber = "2";

		actionButtonName = '<spring:message code="label.updatemenu"/>';
		actionButtonFunction = submitForm2;
		
		var dialog2 = createJqueryDialog(dialogNumber, actionButtonName, actionButtonFunction, target);
		     

		     var errorAddOrUpdateMenuItem = '${errorAddOrUpdateMenuItem}';

			 if(errorAddOrUpdateMenuItem == 'errorUpdateMenuItem'
				 || errorAddOrUpdateMenuItem == 'errorAddChildMenuItem'){

			     	$("#formId").val('');
					$("#formLabel").val('');
					$("#formHref").val('');
					$("#formPosition").val('');

					//Copy and erase the label field error message on details form
					var e = $("#formLabel")[0];
					var parent = $(e).parent();
					var errorArea = $(parent).next();
					var errorSpan = $(errorArea).find("span");
					var formLabelErrorMessage = $(errorSpan).text();
					$(errorSpan).text("");

					//Copy and erase the link field error message on details form
					e = $("#formHref")[0];
					parent = $(e).parent();
					errorArea = $(parent).next();
					errorSpan = $(errorArea).find("span");
					formHrefErrorMessage = $(errorSpan).text();
					$(errorSpan).text("");

					//Copy and erase the position field error message on details form
					e = $("#formPosition")[0];
					parent = $(e).parent();
					errorArea = $(parent).next();
					errorSpan = $(errorArea).find("span");
					formPositionErrorMessage = $(errorSpan).text();
					$(errorSpan).text("");


					//Set error messages on dialogs
			    	var parentId = '${menuItem.parentId}';

					var id = '${menuItem.id}';
			    	var label = '${menuItem.label}';
			    	var href = '${menuItem.href}';
			    	var position = '${menuItem.position}'; 

			    	var dialogToAddMessage;
							 
				     if(errorAddOrUpdateMenuItem == 'errorUpdateMenuItem'){

				    	 	dialogNumber = "";
				    	 	dialogToAddMessage = dialog;
							
					 }else if(errorAddOrUpdateMenuItem == 'errorAddChildMenuItem'){

						 dialogNumber = "2";
				    	 dialogToAddMessage = dialog2;
					 }

						setErrorMessageOnDialog(dialogNumber, dialogToAddMessage, parentId, id, label, href, position, formLabelErrorMessage, formHrefErrorMessage, formPositionErrorMessage);	 
		     }

			 /** Event Section**/
		     $( ".editMenuItem" ).on( "click", function() {
			    	var dataid = $(this).attr("data-id");
					var datalabel = $(this).attr("data-label");
					var datahref = $(this).attr("data-href");
					var dataposition = $(this).attr("data-position");

					$("#dialogFormId").val(dataid);
					$("#dialogFormLabel").val(datalabel);
					$("#dialogFormHref").val(datahref);
					$("#dialogFormPosition").val(dataposition);
					
				 	dialog.dialog( "open" );
			     });

		     $( ".addChildMenuItem" ).on( "click", function() {

				    var dataparentId = $(this).attr("data-parentId");

					$("#dialogFormParentId2").val(dataparentId);
					
				 	dialog2.dialog( "open" );
				 	
			  });
	 });

	 function submitForm(){
		$("#formMenuItemUpdate").submit();
	 }

	 function submitForm2(){
	 	$("#formMenuItemAddChild").submit();
	 }

	function createJqueryDialog(dialogNumber, actionButtonName, actionButtonFunction, target){
		 
		var genericDialog = $( "#dialog-form"+dialogNumber ).dialog({
			autoOpen: false,
			height: 'auto',
			width: 350,
			modal: true,
			position: {
				my: "center",
	            at: "center",
	            of: target
		    },
			buttons: [
				{
					text: actionButtonName,
					click: actionButtonFunction
				},
				{
					text: "Cancelar",
					click: function() {
				    	genericDialog.dialog( "close" );
				    }
				}

			],
			close: function() {
				//Clear all input fields
				
		     	$("#dialogFormParentId"+dialogNumber).val('');
				$("#dialogFormId"+dialogNumber).val('');
				$("#dialogFormLabel"+dialogNumber).val('');
				$("#dialogFormHref"+dialogNumber).val('');
				$("#dialogFormPosition"+dialogNumber).val('');

				//Reset tips/error section
				$("#validateTips"+dialogNumber).css('color','black');
				$("#validateTips"+dialogNumber).html('<spring:message code="label.form.allfieldarerequired"/>');
			}
		});

		//TODO should be done on css generic file
		genericDialog.prev(".ui-dialog-titlebar").css("background","#428bca");

		return genericDialog;
	}

	function setErrorMessageOnDialog(dialogNumber, dialogToAddMessage, parentId, id, label, href, position, formLabelErrorMessage, formHrefErrorMessage, formPositionErrorMessage) {
	 
 	$("#dialogFormParentId"+dialogNumber).val(parentId);
	$("#dialogFormId"+dialogNumber).val(id);
	$("#dialogFormLabel"+dialogNumber).val(label);
	$("#dialogFormHref"+dialogNumber).val(href);
	$("#dialogFormPosition"+dialogNumber).val(position);


		$("#validateTips"+dialogNumber).html(formLabelErrorMessage
				+ '<br>' + formHrefErrorMessage
				+ '<br>' + formPositionErrorMessage);

		$("#validateTips"+dialogNumber).css('color','red');


		dialogToAddMessage.dialog( "open" );
	}
	 
	</script>


	<div id="dialog-form" title="Edit Menu Item" hidden="hidden">
	  <p id="validateTips"><spring:message code="label.form.allfieldarerequired"/></p>
	  
	 
	  <form:form method="post" action="updateMenuItem.do" commandName="menuItem" id="formMenuItemUpdate">
	    <form:hidden path="id" id="dialogFormId" />
	    <form:hidden path="parentId" id="dialogFormParentId"/>
	    
	    <fieldset>
	      <form:label path="label"><spring:message code="label.label" /></form:label>
	      <form:input path="label" cssClass="form-control" id="dialogFormLabel" />
	      <form:label path="href"><spring:message code="label.href" /></form:label>
	      <form:input path="href" cssClass="form-control" id="dialogFormHref" />
	      <form:label path="position"><spring:message code="label.position" /></form:label>
	      <form:input path="position" cssClass="form-control" id="dialogFormPosition" type="number"/>
	      <input type="submit" tabindex="-1" style="position:absolute; top:-1000px">
	    </fieldset>
	  </form:form>
	</div>
	
	<div id="dialog-form2" title="Add Child Menu Item" hidden="hidden">
	  <p id="validateTips2"><spring:message code="label.form.allfieldarerequired"/></p>
	 
	  <form:form method="post" action="addChildMenuItem.do" commandName="menuItem" id="formMenuItemAddChild">
	    <form:hidden path="id" id="dialogFormId2"/>
	   	<form:hidden path="parentId" id="dialogFormParentId2"/>
	    
	    <fieldset>
	      <form:label path="label"><spring:message code="label.label" /></form:label>
	      <form:input path="label" cssClass="form-control" id="dialogFormLabel2"/>
	      <form:label path="href"><spring:message code="label.href" /></form:label>
	      <form:input path="href" cssClass="form-control" id="dialogFormHref2"/>
	      <form:label path="position"><spring:message code="label.position" /></form:label>
	      <form:input path="position" cssClass="form-control" type="number" id="dialogFormPosition2" />
	      <input type="submit" tabindex="-1" style="position:absolute; top:-1000px">
	    </fieldset>
	  </form:form>
	</div>
	
	<table class="tree table table-hover">
		<tr>
			<th><spring:message code="label.label"/></th>
			<th><spring:message code="label.href"/></th>
			<th><spring:message code="label.position"/></th>
			<!-- <th><a id="addMenuItem"><span class="glyphicon glyphicon-plus"></span></a></th>  -->
			<th>&nbsp;</th>
			<th>&nbsp;</th>
			<th>&nbsp;</th>
		</tr>
		<jsp:include page="/WEB-INF/jsp/include/creategridtreelist.jsp"/>
	</table>
</c:if>
<c:if test="${empty menuItemList}">
	<h3><spring:message code="label.nomenuitenstoshow"/></h3>
</c:if>