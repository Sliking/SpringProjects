<%@ taglib prefix="linktable" uri="http://pt.link.libraries/linktables"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<script type="text/javascript">

/** 
 * linkedtable.jsp main function
 * init things and define events
 **/
jQuery( document ).ready(function() {

	var idTDList = $("#tableDocumentoTable tr>td:first-child");

	var element;
	
    var id;

	var arrayLength = idTDList.length;
	var nextElement;
	for (var i = 0; i < arrayLength; i++) {
		element = idTDList[i];
		
		id =  parseInt($(element).text());
		
		$(element).prepend('<input class="checkedDocuments" type="checkbox" name="checkedDocuments[]" value="'+id+'">');
	}

    dialog = $( "#dialog-form" ).dialog({
		open: addInfoDialog,
        autoOpen: false,
        height: 'auto',
        width: 'auto',
        modal: true,
        buttons: {
          "Edit Documents": editDocuments,
          Cancel: function() {
            dialog.dialog( "close" );
          }
        },
        close: function() {
        	removeInfoDialog();
        }
      });

	  //TODO should be done on css generic file
      dialog.prev(".ui-dialog-titlebar").css("background","#428bca");


	/** Events section **/
	$( "#selectElements" ).on( "click", function() {
		selectItens();
	});

	$( "#allAuthor" ).on( "change", function() {
		changeValues('author');
	});

	$( "#allLength" ).on( "change", function() {
		changeValues('length');
	});

	$( "#allExtention" ).on( "change", function() {
		changeValues('extention');
	});

	$( "#allCreationDate" ).on( "change", function() {
		changeValues('creationDate');
	});
    
});

/**
 * prototype to agregate informantion of a document
 */
function documentPrototype(id, author, length, extention, creationDate) {
    this.id = id;
    this.author = author;
    this.length = length;
    this.extention = extention;
    this.creationDate = creationDate;
}

/**
 * Gets elements that are selected on main table
 * adds elements to data selectedDocuments of dialog form, and then opens the dialog
 */
function selectItens(){

    var checkedDocuments = $('.checkedDocuments:checked');

    var id;
    var author;
    var extention;
    var length;
    var creationDate;

    var element;

    var selectedDocuments = [];
    $(checkedDocuments).each(function( index, value ) {

    	id = $(value).val();

    	element = $(value).parent();
    	
    	element = $(element).next();

		author = $(element).text();
		author = $.trim(author);

		element = $(element).next();

		length =  parseInt($(element).text());

		element = $(element).next();

		extention = $(element).text();
		extention = $.trim(extention);

		element = $(element).next();

		creationDate = $(element).text();
		creationDate = $.trim(creationDate);

		selectedDocuments.push(new documentPrototype(id, author, length, extention, creationDate));
    });
    
    $( "#dialog-form" ).data('selectedDocuments', selectedDocuments).dialog('open');
}

/**
 * used to submit dialog form
 */
function editDocuments() {
	$("#documentDTOList").submit();
}

/**
 * add new rows to dialog form
 * Should have data selectedDocuments on dialog #dialog-form
 * selectedDocuments should be a list documentPrototype
 * This function depends on addInputToDialog
 */
function addInfoDialog( event, ui ) {
	var selectedDocuments = $( "#dialog-form" ).data("selectedDocuments");

	var table = $("#dialog-form table")[0];
	
	$(selectedDocuments).each(function( index, value ) {

		addInputToDialog(index, value);
		
	});
};

/**
 * Given and index and a value, and row to dialog form
 * Warning to repited indexs, must be prevented
 */
function addInputToDialog(index, value){
	var lastRow;
	var lastInserted;

	var table = $("#documentDTOList table")[0];
	
	lastRow  = $(table).find("tr:last");
	
	$('<tr></tr>').insertBefore(lastRow);
	
	lastInserted = $(lastRow).prev();
	
	$(lastInserted).append('<td><div class="input-group"><input class="form-control" type="number" value="'+value.id+'" name="documentsDTO['+index+'].id" disabled ></div></td>').insertBefore();
	$(lastInserted).append('<td><div class="input-group"><input class="form-control" type="text" value="'+value.author+'" name="documentsDTO['+index+'].author"></div></td>');
	$(lastInserted).append('<td><div class="input-group"><input class="form-control" type="number" value="'+value.length+'" name="documentsDTO['+index+'].length"></div></td>');
	$(lastInserted).append('<td><div class="input-group"><input class="form-control" type="text" value="'+value.extention+'" name="documentsDTO['+index+'].extention"></div></td>');
	$(lastInserted).append('<td><div class="input-group"><input class="form-control" type="date" pattern="yyyy-MM-dd" value="'+value.creationDate+'" name="documentsDTO['+index+'].creationDate"></div></td>');

}

/*
 * Just removes all rows, but first and last, from the table on dialog form
 */
function removeInfoDialog() {
	//Remove all childs of tr elements,
	//that are not the first child either the last,
	//inside element with id dialog-form
	$("#dialog-form tr:not(:first):not(:last)").remove();
}

/*
 * Its used as event on change on last row of the table present on dialog form
 * This functions sets with the same value all inputs of the same type/kind of field, by name as parameter
 * field must match an the field present on DocumentDTO object, on name and type
 */
function changeValues(field) {
	var inputsToChange = $("#dialog-form tr:not(:first):not(:last) input[name$='."+field+"']");

	var valueToInsert;
	
	switch (true) {
		case /author/.test(field):
			valueToInsert = $("#allAuthor").val();
	        break;
	    case /length/.test(field):
	    	valueToInsert = $("#allLength").val();
       	 	break;
	    case /extention/.test(field):
	    	valueToInsert = $("#allExtention").val();
        	break;
	    case /creationDate/.test(field):
	    	valueToInsert = $("#allCreationDate").val();
        	break;
	    default:
	        break;
	}

	if(valueToInsert) {
		$(inputsToChange).each(function( index, value ) {
			$(value).val(valueToInsert);
	    });
	}

	return true;
}
</script>

<spring:url value="/linktable.do" var="urlExemplo">
	<spring:param name="author" value="author" />
	<spring:param name="length" value="1" />
	<spring:param name="extention" value="extention" />
	<spring:param name="creationDate" value="1991-10-10" />
</spring:url>

<div id="dialog-form" title="Editar documento">
	<p class="validateTips">
		<spring:message code="label.form.allfieldarerequired" />
	</p>

	<form:form method="post" action="editDocuments.do"
		commandName="documentDTOList"  modelAttribute="documentDTOList">
		<fieldset>
			<table>
				<tr>
					<th>Identificador</th>
					<th>Autor</th>
					<th>Tamanho</th>
					<th>Extensão</th>
					<th>Data de criação</th>
				</tr>
				<tr>
					<td>
						<div class="input-group">
							<input class="form-control" placeholder="Identificadores únicos" disabled />
						</div>
					</td>
					<td>
						<div class="input-group">
							<input class="form-control" placeholder="Autor" type="text" value="" id="allAuthor" />
						</div>
					</td>
					<td>
						<div class="input-group">
							<input class="form-control" placeholder="Tamanho" type="number" value="" id="allLength" />
						</div>
					</td>
					<td>
						<div class="input-group" style="width:100%">
							<select class="form-control" id="allExtention">
							  <option value="" disabled selected>Extensão</option>
							  <option value="txt">txt</option>
							  <option value="avi">avi</option>
							  <option value="docx">docx</option>
							  <option value="xml">xml</option>
							</select>
						</div>
					</td>
					<td>
						<div class="input-group">
							<input class="form-control" placeholder="Data" type="date" pattern="yyyy-MM-dd" value="" id="allCreationDate" />
						</div>	
					</td>				
				</tr>
			</table>
			<!-- Allow form submission with keyboard without duplicating the dialog button -->
			<input type="submit" tabindex="-1"
				style="position: absolute; top: -1000px" />
		</fieldset>
	</form:form>
</div>


<linktable:table id="tableDocumento" tableObject="${tableDocument}"
	currentUrl="${urlExemplo}" tableCssClasses="table table-hover" >
	<jsp:attribute name="additionalHeaders">
			<th>Update</th>
	  		<th>Delete</th>
	 	</jsp:attribute>
	<jsp:attribute name="additionalColumns"> 
			<td class="operacaoEdit">
	   			<a href="${pageContext.request.contextPath}/documentUpdate.do?documentId=${rowObject.id}">
					Update
				</a>
	  		</td>
	  		<td class="operacaoEdit">
	   			<a
			href="${pageContext.request.contextPath}/documentDelete.do?documentId=${rowObject.id}">
	   				Delete
				</a>
	  		</td>
	</jsp:attribute>
</linktable:table>
<button id="selectElements" type="button" class="btn btn-primary">Selecionar registos</button>