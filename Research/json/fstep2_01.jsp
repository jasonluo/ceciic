<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
          "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
   
		<%-- Common Header Includes --%>
		<jsp:directive.include file="includes/commonHeadIncludesForFotw.jsp" />

		<%-- Include common dropdown after clLocale is defined --%>
		<jsp:directive.include file="commonDropDown.jsp" />
		
		 <%-- title tag --%>
        <jsputil:title name="<%=clBundle.getString(BundleUtil.getPageTextKey(\"fstep2_01.Title\"))%>" />

		<%-- Hints toggle bindings --%>
		<jsp:directive.include file="includes/hintsToggle.jsp" />

		<%  
			Locale alertLocale = new Locale(clLocale.getLanguage(), clLocale.getCountry(), "fotw1011.i18n.FAFSA_AlertMessages");
			ResourceBundle alertBundle, fscBundle;
		    if (BundleUtil.isSpanish(alertLocale)) 
	        {
	        	alertBundle = ResourceBundle.getBundle("fotw1011.i18n.FAFSA_AlertMessages", new Locale("es", "ES"));
	        	fscBundle = BundleUtil.getFieldTextsBundle(new Locale("es", "ES", "FSC"));
	        } 
	        else 
	        {
	        	alertBundle = ResourceBundle.getBundle("fotw1011.i18n.FAFSA_AlertMessages", new Locale("en", "US"));
	        	fscBundle = BundleUtil.getFieldTextsBundle(new Locale("en", "US", "FSC"));
	        }
		   
	    	StringBuffer strBuf = new StringBuffer();
	 	    strBuf.append("<ul>");
	 	    strBuf.append("<li>").append(alertBundle.getString("msg.fstep2_01.Add.Inactive")).append("</li>");
	 	    strBuf.append("</ul>");
	 	    String alertInactiveCode = strBuf.toString();
	 	
	 	    String successSearch = alertBundle.getString("success.fstep2_01.Search");
	 	    String successAdd = alertBundle.getString("success.fstep2_01.Add");
	 	    
			clLocale = new Locale(clLocale.getLanguage(), clLocale.getCountry(), "schoolSelection");
		    clBundle = BundleUtil.getFieldTextsBundle(clLocale);
		    // Used for this page
		    szHelpText = clBundle.getString("Help.fstep2_01");
		
		    String[][] m_aryOptions = aryHousingCodeTypes;
		    String housingPlanDropdown = JSPUtil.getDropDown("emptyName", "", m_aryOptions, "emptyId", "").replaceAll("'", "&#39;").replaceAll("\\n", "");
		    
		    boolean bPreFilled = SUBJSPUtil.hasSignedData(clList, clFieldValues);
		    String szOTHERSCHOOLQUESTIONS =  clList.getFieldValue("szOTHERSCHOOLQUESTIONS", clFieldValues);
		    
		    boolean showOTHERSCHOOLQUESTIONS = false;
		    if( (szOTHERSCHOOLQUESTIONS==null || szOTHERSCHOOLQUESTIONS.equals("") ) && bPreFilled){
		    	String szWORKSTUDY = clList.getFieldValue("szWORKSTUDY", clFieldValues);
			    String szLOANS = clList.getFieldValue("szLOANS", clFieldValues);
			    String szTEACH = clList.getFieldValue("szTEACH", clFieldValues);
			    if( (szWORKSTUDY != null && !szWORKSTUDY.equals(""))
			        || (szWORKSTUDY != null && !szWORKSTUDY.equals(""))
			        || (szWORKSTUDY != null && !szWORKSTUDY.equals(""))){
			        	showOTHERSCHOOLQUESTIONS = true;
			        }
		   }
	
		
		%>
		<!-- my stuff -->
		<script language="javascript" type="text/javascript" src="/fotw1011/js/jquery.dataTables.js"></script>
		<script>
		  function validate() {
		  	var firstInst = $("#szINSTCODE1").val();
		  	
		  	if(nvl(firstInst) == ""){
		  	  hideAjaxErrors();
		  	  $("#errorIcon_szFSCODE").show();
		  	  self.scrollTo(0,0);
		  	  focusFirstField();
		      return false;
		    }
		    else {
		      return true;
		    }
		  }
		  function toggleOtherSchool (){
		  	  var provideOtherSchool = $("input[name='szOTHERSCHOOLQUESTIONS']:checked").val();
			  if (provideOtherSchool == 1) {
			    $("#other_school_questions").show();
			  } else {
			    $("#other_school_questions").hide();
			  }
		  }
		  $(document).ready(function(){
			 focusFirstField();
		     bindViewStateActionOnChange($("input[name='szOTHERSCHOOLQUESTIONS']"), toggleOtherSchool);
		  });
		</script>
		<script type="text/javascript" charset="utf-8">
			/* Global variable for the DataTables object */
			var selectedSchoolTable;
			var seachResultTable;
			
			//Global variables for table headers.  The headers matches the search field names
			var hFederalSchhoolCode = "<%=BundleUtil.getString(clBundle, BundleUtil.getFieldLabelKey("szFSCODE"))%>";
			var hSchoolName = "<%=BundleUtil.getString(clBundle, BundleUtil.getFieldLabelKey("szFSNAME"))%>";
			var hAddress = "<%=BundleUtil.getString(clBundle, BundleUtil.getFieldLabelKey("szFSADDRESS"))%>";
			var hCity = "<%=BundleUtil.getString(clBundle, BundleUtil.getFieldLabelKey("szFSCITY"))%>";
			var hHousingPlan = "<%=BundleUtil.getString(clBundle, BundleUtil.getFieldLabelKey("szFSHSECODE"))%>";
			var hGRADRATE = "<%=BundleUtil.getString(clBundle, BundleUtil.getFieldLabelKey("GRADRATE")).trim()%>";
		    var hRETNRATE = "<%=BundleUtil.getString(clBundle, BundleUtil.getFieldLabelKey("RETNRATE")).trim()%>";
		    var hTRANSRATE = "<%=BundleUtil.getString(clBundle, BundleUtil.getFieldLabelKey("TRANSRATE")).trim()%>"; 
		    var hSCHOOLADDRESS = "<%=BundleUtil.getString(clBundle, BundleUtil.getFieldLabelKey("SCHOOLADDRESS")).trim()%>";
		    
			//Global variables for dynamic table text
			var sZeroRecords = "<%=BundleUtil.getString(clBundle, BundleUtil.getPageTextKey("table.sZeroRecords"))%>";
			var resultInfo = "<%=BundleUtil.getString(clBundle, BundleUtil.getPageTextKey("table.result"))%>";
			var resultInfo2 = "<%=BundleUtil.getString(clBundle, BundleUtil.getPageTextKey("table.result2"))%>";
			var alertInactiveCode = "<%=alertInactiveCode%>";
			var successSearch = "<%=successSearch%>";
			var successAdd = "<%=successAdd%>";
			
			
			var housingDropdownHtml = '<%=housingPlanDropdown%>';
			//var housingDropdownHtml ="<select id='emptyId' name= 'emptyName' value=''><option ></option><option value='0'>On Campus</option><option value='1'>With Parent</option><option value='2'>Off Campus&#145;s</option></select>";
			//var divHtml = '<div id="szFSCODE_question" class="layout_horizontal" >';
			//this is the Add link
			var addLinkHtml = "<span><a href='javascript: function () {return false;}'><%=BundleUtil.getString(clBundle, "Common.button.add")%></a></span>";
			var removeLinkHtml = "<a href=javascript:fnDeleteSchool('rowId')><%=BundleUtil.getString(clBundle, "Common.button.remove")%></a>";
			var errorIconHtml = '<jsputil:errorIcon name="emptyId" />';
			
		    //Global variable for hidden field prefix. these prefixes must match those defined in fdef.txt
		    var pINSTCODE = "szINSTCODE";
		    var pHSECODE = "szHSECODE";
		    var pVENDORNAME = "szVENDORNAME";
		    var pGRADRATE = "GRADRATE";
		    var pRETNRATE = "RETNRATE";
		    var pTRANSRATE = "TRANSRATE";
		    var pSCHOOLADDRESS = "SCHOOLADDRESS";
		    
			
			$(document).ready(function(){
				//var url= '../fotw1011/FFOTWServlet?ajax=retrieveSelectedSchool&&dowhat=display';
				buildSelectedSchoolTable(new Array());
				
				//display error messages when "next" button failed to pass validations
				var ajaxErrorStr ='<%=JSPUtil.getErrorsAsJson(request)%>';
				var errorData = JSON.parse(ajaxErrorStr).errorData;
				var i;
				//hideen field housing plan has id like "szHSECODE". But the error icon need to be shown next to housing dropdown with id like "emptyId" 
				for(i=0; i<errorData.length; i++){
					errorData[i][0] = errorData[i][0].replace("szHSECODE","emptyId");
				}
		        showAjaxErrors(errorData);
		        
		         //disable add link if selected school table is full (10 rows)
		          if($("#selectedSchools tbody tr").size() >= 10){
		     		fnDisableAddLink();
		     	  }
				
			});
			
			function buildSelectedSchoolTable(aData){
				var selectedSchoolTableHeader = '<table  class="tabletype1" cellpadding="0" cellspacing="0" border="0" class="display" id="selectedSchools"></table><p style="margin-bottom:10px"></p>'; 
				$('#selectedSchoolsDiv').html(selectedSchoolTableHeader); 
				selectedSchoolTable = $('#selectedSchools').dataTable({
					"bProcessing": false,
					"bSort": false,
					"bInfo": false,
					"bFilter": false,
					"bLengthChange": false,
					"bStateSave": false,
					"bAutoWidth": false,
					"oLanguage": {"sZeroRecords": sZeroRecords},
					"aoColumns": [{"sTitle":hSchoolName, "sWidth": "130px" },{"sTitle":hFederalSchhoolCode, "sWidth": "60px" },{"sTitle":hHousingPlan, "sWidth": "185px" },{"sTitle":"", "sWidth": "50px" },{"bVisible": false },{ "bVisible": false }, { "bVisible": false },{ "bVisible": false },{ "bVisible": false }],
					"aaData": aData
				});
				//rebuilt table from hidden fields
				fnRestoreSelectedSchoolTable();
			}
			
			function schoolSearch()
			{
			
				var szFSSTATE =document.getElementById("szFSSTATE").value;
			    var szFSCITY = fnURLEncode(document.getElementById("szFSCITY").value);
			    var szFSNAME = fnURLEncode(document.getElementById("szFSNAME").value);
			  
			    var url= "../fotw1011/FFOTWServlet?ajax=searchSchool&&dowhat=display"+"&&szFSSTATE="+szFSSTATE+"&&szFSCITY="+szFSCITY+"&&szFSNAME="+szFSNAME;
		        
			    // Show the processing modal
			    showProcessingModal("<%=clBundle.getString(BundleUtil.getPageTextKey("processing"))%>", null);
			    
				if(seachResultTable){
					seachResultTable.fnClearTable(0);
					$("#searchResults").remove();
					//reset scrollable search result div style to deafult
					$("#searchResultDiv").attr("style", "");
				}
				
			   $.post(url,null,
			       function(data){
			          //$.each(data.items, function(i,item){
			          hideAjaxErrors();
			       
			          var errorData = data.errorData;
			          var alertData = data.alertData;
			          var fatalError = data.fatalError;
			          
			          if(typeof fatalError != "undefined"){
				      	submitAjaxFatal();
				      }  
			          else if(typeof errorData != "undefined"){
			          	 //hide searchResults_info
				         $("#searchResults_info").text("");
			          	 showAjaxErrors(errorData);
			          }
			          else if(typeof alertData != "undefined"){
			          	//display zero match text
				        $("#searchResults_info").text(resultInfo.replace("_match_num_","0"));
			          	showAjaxAlerts(alertData);
			          }
			          else{
			          	  var aData = data.aaData;
			          	
			          	  if(aData.length == 0){
			          	    //display zero match text
				          	$("#searchResults_info").text(resultInfo.replace("_match_num_","0"));
			          	  }
			          	  else{
			          	  	 buildSeachResultTable(aData);
			          	  	 $('#searchResultDiv').scrollTo(0,0);
				         
					         //Batch build tooltip.  This might be very costly
					       /*  $("#searchResults tbody tr").each( function() {
					              var toolTipText = $("td:eq(1) span:hidden", this).attr("title");
					              var rates = toolTipText.split(";");
							  	 
							  	  var title = fnBuildToolTipText(rates[0], rates[1], rates[2]);
							  	  if(title != ""){
							  	  	//$("td:gt(0)",this).attr("title", rates[2]+" "+rates[1]+" "+rates[0]);
							  	  	$(this).attr("title", title);
							  	  }
							  	 
							 })*/
							 //seachResultTable.fnSetColumnVis( 5, flase );
					         aData.length = 0;
					         //disable add link if selected school table is full (10 rows)
					          if($("#selectedSchools tbody tr").size() >= 10){
					     		fnDisableAddLink();
					     	  }
					     	  //show confirmation messages
					     	  var confirmationMessage = new Array();
					     	  confirmationMessage[0] = successSearch;
					     	  showAjaxConfirmation(confirmationMessage);
			          	  }
				      }
				      
				      // refresh the client session to sync with server
					  RefreshSession();
				      
				      // Hide the processing modal
				      hideProcessingModal();
		          },
		          "json");
			}
			//this funciton is invoked by "add" link to add a school.
			function addSchool(schoolCode){
			
			     var name = $("#searchResults tbody tr:contains('"+schoolCode+"') td:eq(1)").text();
			     var address = $("#searchResults tbody tr:contains('"+schoolCode+"') td:eq(2)").text();
			     var city = "";
			     var code = $("#searchResults tbody tr:contains('"+schoolCode+"') td:eq(4)").text();
			     var toolTipText = $("#searchResults tbody tr:contains('"+schoolCode+"') td:eq(1) span:hidden").attr("title");
			     var rates = toolTipText.split(";");
			     var gradRate = rates[0];
			     var retenRate = rates[1];
			     var tranRate = rates[2];
			
			     aData = new Array();
			     aData[0] = code;
			     aData[1] = name;
			     aData[2] = address;
			     aData[3] = city;
			     aData[4] = gradRate;
			     aData[5] = retenRate;
			     aData[6] = tranRate;
			     
			     //remove selected row for search result table
			     $("#searchResults tbody tr:contains('"+schoolCode+"')").remove();
			     //adjust search result div height
			     var size = fnAdjustSearchResultDiv($("#searchResults tbody tr").size());
			     
			     //add the school to selected table
			     fnSelectSchool(aData);
			  
			}
			
			/* This function builds the searchResults table */
			function buildSeachResultTable(aData)
			{
				
		    	var searchResultTableHeader = '<table  class="tabletype1" cellpadding="0" cellspacing="0" border="0" class="display" id="searchResults"><tbody></tbody></table><p style="margin-bottom:10px"></p>'; 
		    	//create table header inside search result div
		    	$('#searchResultDiv').html(searchResultTableHeader); 
		        seachResultTable = $('#searchResults').dataTable({
					"fnFooterCallback": function ( nRow, aaData, iStart, iEnd, aiDisplay ) {
					   var searchResultText = resultInfo.replace("_match_num_",iEnd+"");
					   //set info text
					   $("#searchResults_info").text(searchResultText);
					   if( iEnd <=0){
					   		
					   }
					   else if(iEnd <= 10){
					        $("#searchResults_info").append("<p>"+resultInfo2+"</p>");
						    fnAdjustSearchResultDiv(iEnd);
					   }
					   else if(iEnd == 251){
                           $("#searchResults_info").text("<%=fscBundle.getString("PageText.fslookup3.Section.MaxFound2")%><%=fscBundle.getString("PageText.fslookup3.Section.MaxInfn2")%>");
                            $("#searchResults_info").append("<p>"+resultInfo2+"</p>");
                            fnAdjustSearchResultDiv(iEnd);
                       }
					   else{
					   		$("#searchResults_info").append("<p>"+resultInfo2+"</p>");
						    fnAdjustSearchResultDiv(10);
					   }
					   
					},
					"sDom": '<"top"i>rt<"bottom"flp><"clear">',
					"bInfo": false,
					"bProcessing": false,
					"bServerSide": false,
					"bSort": false,
					"bFilter": false,
					"bPaginate": false,
					"bLengthChange": false,
					"bStateSave": false,
					"bAutoWidth": false,
					"oLanguage": {"sZeroRecords": ""},
					"aoColumns": [{"sTitle":"", "sWidth": "50px" },{"sTitle":hSchoolName, "sWidth": "100px" },{"sTitle":hAddress, "sWidth": "100px" },{"sTitle":hCity, "sWidth": "100px" },{"sTitle":hFederalSchhoolCode, "sWidth": "70px" },{ "bVisible": false }, { "bVisible": false }, { "bVisible": false }],
					"aaData": aData
			    });
			}
			
		  function isSchoolSelected(federalSchoolCode)
		  {
		  	    var bSelected = 'false';
			   /* $('tbody tr', selectedSchoolTable).each(function(i){
			       //td[1] must be schoolCode column
			       var code = $('td:eq(1)', $(this)).text();
			       if(code==federalSchoolCode){
			            bSelected = 'true';
			       }
			    });*/
		        return bSelected;
		  }
		  
		  function fnUpdateHiddenField(index, hiddenData, aryInput){
		      var fieldPrefixes = new Array(pINSTCODE,pVENDORNAME,pHSECODE,pGRADRATE,pRETNRATE,pTRANSRATE,pSCHOOLADDRESS);
			  var counter;
			  
			  for(counter=0; counter<aryInput.length; counter++){
			        var id = nvl(aryInput[counter].attr("id"));
			        var i;
			        for(i=0; i<fieldPrefixes.length; i++){
			           if(id == (fieldPrefixes[i]+index)){
			           		aryInput[counter].attr("value", hiddenData[i]);
			           }
			        } 
			  }
		  }
		  
		  function fnCleanHiddenField(index, aryInput){
		      var fieldPrefixes = new Array(pINSTCODE,pVENDORNAME,pHSECODE,pGRADRATE,pRETNRATE,pTRANSRATE,pSCHOOLADDRESS);
			  var counter;
			  
			  for(counter=0; counter<aryInput.length; counter++){
			        var id = nvl(aryInput[counter].attr("id"));
			        var i;
			        for(i=0; i<fieldPrefixes.length; i++){
			           if(id == (fieldPrefixes[i]+index)){
			           		aryInput[counter].attr("value", "");
			           }
			        } 
			   }
		  }
		  /* Helper fucntion to adjust the height of School Searh Result div based on the number of rows*/
		  function fnAdjustSearchResultDiv(nRow){
		        var tblSize;  
		        if(nRow > 10){
		           tblSize = 10;
		        }
		        else{
		        	tblSize = nRow;
		        }
		  		var height = (tblSize +2.0) * 40;
			    var searchResultDivStyle = "overflow: auto; height:"+height+"px;"
			    $("#searchResultDiv").attr("style", searchResultDivStyle);
		  }
		  
		  //This funcion adds row to selectedSchoolTable.  aData must be in right order.
		  function fnAddRow(aData, hiddenFieldIndex){
  				//add a row with aData
		   		var row = selectedSchoolTable.fnAddData( [
		   			nvl(aData[0]),
					nvl(aData[1]),
					nvl(aData[2]),
					nvl(aData[3]),
					nvl(aData[4]),
					nvl(aData[5]),
					nvl(aData[6]),
					nvl(aData[7]),
					nvl(aData[8])
					] );
				
				//add errorIcon next to housing dropdown
				var hpd = $("#emptyId"+hiddenFieldIndex,selectedSchoolTable);
				//alert("hpd==="+hpd);
				hpd.wrap('"<div class="layout_horizontal" ></div>');
				hpd.before('<div class="short_label"><label></label></div>');
				hpd.before(errorIconHtml.replace("emptyId",("emptyId"+hiddenFieldIndex)));
			
				//register onchange function for dropdown	
				$("tbody tr:eq("+row+") select", selectedSchoolTable).change(function () { 
		                var val = $(this).val();
		                var id = $(this).attr("id");
		                $("#"+id.replace("emptyId",pHSECODE)).attr("value", val);
		             });
		   		
				//preserve housing plan selection
				var aryHSECODE = nvl(aData[4]);
				if(typeof aryHSECODE != "undefined" && aryHSECODE !=null && aryHSECODE != ""){
					 var optionIndex = parseInt(aryHSECODE);
					 //alert("optionIndex=="+optionIndex);
					 $("#emptyId"+hiddenFieldIndex+" option:eq("+optionIndex+")").attr("selected", 1);
				}
				     
			     //var housingSelect = document.getElementById(addbackId);
			     //if(housingPlanArray[i] != ""){
			       //var optionIndex = parseInt(housingPlanArray[i])+1;
			       //alert("optionIndex=="+optionIndex);
			       //housingSelect.options[optionIndex].selected="1";	
		     	//}
		     	
		       //binds hints toggle
			   $("select[id^='emptyId']").bind("focus", function(){
		            show("#hint_housingPlans")
		        });
		        
	     	   //disable add link if selected school table is full (10 rows)
	          if($("#selectedSchools tbody tr").size() >= 10){
	     		fnDisableAddLink();
	     	  }
		  }
		   
		  /* This function add a row to SelectedSchoolTable and update teh hidden fields accordingly 
		   * This is used when:
		   *  1. user clicks the Add button
		   *  2. user clciks the Add link
		   *  aData is an array of column values
		  */
		  function  fnSelectSchool(aData)
		  {
			    var aryFSCODE = aData[0];
			    var aryFSNAME = aData[1];
			    var aryFSADDRESS = aData[2];
			    var aryFSCITY = aData[3];
			    var aryGRADRATE = aData[4];
			    var aryRETNRATE = aData[5];
			    var aryTRANSRATE = aData[6];
				
			  
			   //check if school has been selected
			   var bSelected = isSchoolSelected(aryFSCODE);
			   if(bSelected == "true"){
			      //alert("school has been selected");
			      return false;
			   }
			   else{
				   var i;
				   for(i=1; i<=10; i++){
				      var instCode = $("#"+pINSTCODE+i);
				      if(instCode.attr("value")==""){
				        
				         //add a row to selected school table
				   		 var housingDD = housingDropdownHtml.replace("emptyId", "emptyId"+i).replace("emptyName", "emptyName"+i);
				   		 var tableData = new Array(aryFSNAME,aryFSCODE,housingDD,removeLinkHtml.replace("rowId","emptyId"+i),null,aryFSCITY,aryGRADRATE,aryRETNRATE,aryTRANSRATE);
				   		 fnAddRow(tableData, i);
				   		 //Post procesing to build a tooltip for the added row 
					     /*var title = fnBuildToolTipText(aryGRADRATE,aryRETNRATE, aryTRANSRATE,aryFSADDRESS);
					     if(title != ""){
					     	$("#selectedSchools tbody tr:contains('"+aryFSCODE+"')").attr("title",title);
					     }*/
			   
			   		     //update hidden field value
		                 var hiddenData = new Array(aryFSCODE,aryFSNAME,"",aryGRADRATE,aryRETNRATE,aryTRANSRATE,aryFSADDRESS);
		                 var aryInput = new Array();
					     var counter = 0;
					     $("input[type=text]").each(function (){
					             aryInput[counter] = $(this);
					             counter++;
					     });
			   			 fnUpdateHiddenField(i,hiddenData, aryInput);
			   			 aryInput.length =0;
				         break;
				      }
				   }
			   }
			   
			   return true;
		  }
		  
		  /* This function is invoked by "Add" button add a school.  It makes an ajax call with school code to retrieve school infomation */
		  function fnAddSchoolByCode()
		  {
			    // Show the processing modal
			    showProcessingModal("<%=clBundle.getString(BundleUtil.getPageTextKey("processing"))%>", null);

				//uppercase school code
				$("#szFSCODE").attr("value",nvl($("#szFSCODE").attr("value")).toUpperCase());
		   		var szFSCODE = fnURLEncode(document.getElementById("szFSCODE").value); 
				$.post("../fotw1011/FFOTWServlet?ajax=addSchool&&dowhat=display",{szFSCODE: szFSCODE},
		        function(data){
		          hideAjaxErrors();
		          
		          var errorData = data.errorData;
		          var alertData = data.alertData;
		          var fatalError = data.fatalError;
			      if(typeof fatalError != "undefined"){
			      	submitAjaxFatal();
			      }   
		          else if(typeof errorData != "undefined"){
		          	 showAjaxErrors(errorData);
		          }
		          else if(typeof alertData != "undefined"){
		          	showAjaxAlerts(alertData);
		          }
		          else {
		          	  var aData = data.aaData;
		              if(aData.length > 0){
		             	 if(fnSelectSchool(aData[0])){
		             	 	 //inactive code. alert msg
			             	 if(szFSCODE != aData[0][0]){
			             	 	var msg = alertInactiveCode.replace("{0}",szFSCODE).replace("{1}",aData[0][0]);
			          	  		showAjaxAlerts(new Array(msg));
			             	 }
			             	  //show confirmation messages
					     	  var confirmationMessage = new Array();
					     	  confirmationMessage[0] = successAdd;
					     	  showAjaxConfirmation(confirmationMessage);
		             	 }
		             	 //school has been selected. alert msg
		             	 else{
		             	 
		             	 }
		             	
		             	 //clear out school code field after successful add
		             	 $("#szFSCODE").attr("value", "");
		              }
	              }
			      
			      // refresh the client session to sync with server
				  RefreshSession();
			      
			      // Hide the processing modal
			      hideProcessingModal();
		        },
		        "json");
		  }
		    
		  /* Get the rows which are currently selected */
		 function fnGetSelected( oTableLocal, index )
		 {
				var aReturn = new Array();
				var aTrs = oTableLocal.fnGetNodes();
				
				for ( var i=0 ; i<aTrs.length ; i++ ){
				   //td[1] must be schoolCode column
				    var x = $('td:eq(1)', aTrs[i]).text();
					if ( x==index){
						aReturn.push( aTrs[i] );
					}
				}
				return aReturn;
		 }
		  
		 /* This function deletes a row form SelectedSchoolTable */
		  function fnDeleteSchool(rowId)
		  {
			   //var anSelected = fnGetSelected( selectedSchoolTable, federalCode );
			   //delete row from selected school table
			   //var iRow = selectedSchoolTable.fnGetPosition( anSelected[0] );
			   //selectedSchoolTable.fnDeleteRow(iRow);
			   //re-set the corresponding hidden value
			   //var housingId = $("select", anSelected[0]).attr("id");
			   
			   var aryInput = new Array();
		       var counter = 0;
		       $("input[type=text]").each(function (){
		             aryInput[counter] = $(this);
		             counter++;
		       });
	         
	         //fnCleanHiddenField(housingId.replace("emptyId",""), aryInput);
			   fnCleanHiddenField(rowId.replace("emptyId",""), aryInput);
			   
		  	  //shift hidden fields 
		  	   fnShiftHiddenField(aryInput);
		  	   
		  	  //record slected school order 
		  	  //var aData = selectedSchoolTable.fnGetData();
			  //var selectedSchoolOrder = new Array();
			 
			  // var i;
			  // var counter = 0;
			  // for(i=0; i<aData.length; i++){
		   		 //if(iRow != i){
		   		     // aData[i][2] requires column 3 must be housing plan dropdown
		   		 	 //var addBackHousingId= $( aData[i][2]).attr("id");
		   		 	 //selectedSchoolOrder[counter] = addBackHousingId.replace("emptyId","");
		   		     //counter++;
		   		 //}
			   //}
		  	  
			   //clear old table
			   selectedSchoolTable.fnClearTable(1);
			  
			   //rebuilt table from hidden fields
			   fnRestoreSelectedSchoolTable();
			   
			   //enable add link and button
			   fnEnableAddLink();
		  }
		   
		  /*this function build selected school table from the hidden fields
		   * This is used to build table when 
		   * 1.  user first come to the page with a list of previously selected schools
		   * 2.  a school is deleted from the table.  (the table is completed wiped out and 
		   *     rebuilt from the hidden fields.
		  */
		  function fnRestoreSelectedSchoolTable(rowIndices)
		  {
		  		var aryInput = new Array();
			    var counter = 0;
			    $("input[type=text]").each(function (){
			             aryInput[counter] = $(this);
			             counter++;
			    });
		   
		       if(typeof rowIndices == "undefined" ||rowIndices==null || rowIndices.length ==0){
		       		rowIndices = new Array(1,2,3,4,5,6,7,8,9,10);
		       }
			   //record hideen field values
			   var fieldPrefixes = new Array(pVENDORNAME,pINSTCODE,pHSECODE,pGRADRATE,pRETNRATE,pTRANSRATE,pSCHOOLADDRESS);
			   var aaData = new Array();
				
			   for(counter=0; counter<aryInput.length; counter++){
			        var id = nvl(aryInput[counter].attr("id"));
			        var i;
			        for(i=0; i<fieldPrefixes.length; i++){
			            var j;
			            for(j=0; j<rowIndices.length; j++){
				            if(id == (fieldPrefixes[i]+rowIndices[j])){
				           		var fieldValue = nvl(aryInput[counter].attr("value"));
				           		if(fieldValue != ""){
				           		    if(nvl(aaData[j]) ==""){
				           		    	aaData[j] = new Array();
				           		    }
				           		    aaData[j][i] = fieldValue;
				           		}
				            }
			            }
			        } 
			   }
			   
			   //restore table using saved hidden field values
			   for(counter =0; counter < aaData.length; counter++){
			        var aryFSNAME = aaData[counter][0];
			        var aryFSCODE= aaData[counter][1];
			        var aryHSECODE = aaData[counter][2];
			        var housing = housingDropdownHtml.replace("emptyId", "emptyId"+rowIndices[counter]).replace("emptyName", "emptyName"+rowIndices[counter]);
			        var GRADRATE = nvl(aaData[counter][3]);
			        var RETNRATE = nvl(aaData[counter][4]);
			        var TRANSRATE = nvl(aaData[counter][5]); 
			        var aryFSADDRESS=nvl(aaData[counter][6]);
			        var aryFSCITY="";
			        
			        var aData = new Array (aryFSNAME, aryFSCODE,housing, removeLinkHtml.replace("rowId","emptyId"+rowIndices[counter]),aryHSECODE,aryFSCITY,GRADRATE,RETNRATE,TRANSRATE);
			        
			   	    fnAddRow(aData, rowIndices[counter]);
			   	    //build tooltip
				    /*var title = fnBuildToolTipText(GRADRATE,RETNRATE,TRANSRATE,aryFSADDRESS);
				    if(title !=""){
				    	$("#selectedSchools tbody tr:contains('"+aryFSCODE+"')").attr("title",title);
				    }*/
			   }
			   //binds hints toggle
			   $("select[id^='emptyId']").bind("focus", function(){
		            show("#hint_housingPlans")
		        });
			   aaData.length = 0;
			   rowIndices.length = 0;
			   
		  }
		 
		  /* This function will disable Add link in each row of SchoolSearchResult table as welll as Add button */ 
		  function fnDisableAddLink(){
		 
		      var nodes = $("td:eq(0) span:eq(0)", $("#searchResults tbody tr"));
		      if(nodes.size() > 0){
		      	nodes.hide();
		      }
		      
		      var nodes = $("td:eq(0) span:eq(1)", $("#searchResults tbody tr"));
		      if(nodes.size() > 0){
		      	nodes.show();
		      }
		     
		      $("#addBtn_enabled").hide();
		      $("#addBtn_disabled").show();
		  
		      //$("#addBtn_school").attr('disabled', 'disabled');
		  }
		  function fnEnableAddLink(){
		      var nodes = $("td:eq(0) span:eq(0)", $("#searchResults tbody tr"));
		      if(nodes.size() > 0){
		        nodes.show();
		      }
		      
		      var nodes = $("td:eq(0) span:eq(1)", $("#searchResults tbody tr"));
		      if(nodes.size() > 0){
		      	nodes.hide();
		      }
		      
		      $("#addBtn_enabled").show();
		      $("#addBtn_disabled").hide();
		  }
		  
		  /* Helper function to encode url */
		  function fnURLEncode(inputString){
		  	 var encodedInputString=escape(inputString);
			 encodedInputString=encodedInputString.replace("+", "%2B");
			 encodedInputString=encodedInputString.replace("/", "%2F");
			 
			 return encodedInputString;
		  }
		  
		  /* Helper function to shift hidden fields to make them always start at 1 and being continuous */
		  function fnShiftHiddenField(aryInput){
		  
	         var hiddenFieldValues = new Array();
	         var k;
	         for(k=1; k<11; k++){
	         	hiddenFieldValues[k] = new Array();
	         }
	         
	         var fieldPrefixes = new Array(pINSTCODE,pVENDORNAME,pHSECODE,pGRADRATE,pRETNRATE,pTRANSRATE,pSCHOOLADDRESS);
	         var counter = 0;
	         //record all hidden field values
	         for(counter=0; counter <aryInput.length; counter++){
	            //alert(aryInput[counter].attr("id"));
	            var id = nvl(aryInput[counter].attr("id"));
	            var i;
	            for(i=0; i<fieldPrefixes.length; i++){
	            	if(id.indexOf(fieldPrefixes[i]) != -1){
		                var fieldIndex = id.replace(fieldPrefixes[i],"");
		                var fieldValue = nvl(aryInput[counter].attr("value"));
		                if(fieldValue !=""){
		                   hiddenFieldValues[fieldIndex][i] = fieldValue;
		                   aryInput[counter].attr("value","");
		                }
	                }
	            }
	         }
		   
		     //shift hidden field value array
		     var hiddenFieldValuesShifted = new Array();
		     var i;
		     counter=1;
		     for(i=1; i<=10; i++){
		         var temp = hiddenFieldValues[i];
		         if(nvl(temp[0]) != ""){
		            hiddenFieldValuesShifted[counter] = temp;
		            counter++;
		         }
		     }
		     
		     //reset hidden field values
		     for(counter=0; counter < aryInput.length ;counter++){
	            var id = nvl(aryInput[counter].attr("id"));
	            var i;
	            for(i=0; i<fieldPrefixes.length; i++){
	               if(id.indexOf(fieldPrefixes[i]) != -1){
		               var index = id.replace(fieldPrefixes[i],"");
		               if(index < hiddenFieldValuesShifted.length){
		                   var fieldValue = hiddenFieldValuesShifted[index][i];
		                   aryInput[counter].attr("value",fieldValue);
		               }
		            }
	            }
	         }
	         
	         hiddenFieldValues.length =0;
		   
		  }
		  
		  /* Helper function to build tooltip text */
		  function fnBuildToolTipText(gradRate, retnRate, transRate, schoolAddress){
		  	  var toolTipText = "";
		  	  if(nvl(gradRate) !=""){
		  	  	gradRate = gradRate + "%";
		  	  }
		  	  
		  	  if(nvl(retnRate) !=""){
		  	  	retnRate = retnRate + "%";
		  	  }
		  	  
		  	  if(nvl(transRate) !=""){
		  	  	transRate = transRate + "%";
		  	  }
		  	  
		  	  
		  	 /* if(gradRate !="" || transRate !="" || retnRate !=""){
		  	     toolTipText = hGRADRATE+":"+gradRate+" "+ hRETNRATE+":"+retnRate+" "+ hTRANSRATE+":"+transRate;
		  	     
		  	     if(nvl(schoolAddress) != ""){
		  	       toolTipText = toolTipText + " " + hSCHOOLADDRESS+":"+schoolAddress;
		  	     }
		  	  }*/
		  	  
		  	 if(nvl(schoolAddress) != ""){
		  	       toolTipText = hSCHOOLADDRESS+":"+schoolAddress;
		  	 }
			
			 return toolTipText;
		  }
		</script>
		
	</head>
	
    <%-- body tag --%>
    <jsputil:body/>	
	
		<div id="main" >
			<div id="skipLink">
				<p class="noprint">
					<a href="#mainContent">Skip to Main Content</a>
				</p>
			</div>

			<%-- form tag --%>
			<jsputil:form />

				<%-- add common hiddens --%>
				<jsputil:controlFields />

                <%-- page header --%>
                <jsp:directive.include file="includes/commonPageHeader.jsp" />

                <%-- left side bar --%>
                <jsp:include page="includes/left_side_inc.jsp" flush="true">
                    <jsp:param name="type" value="student" />
                    <jsp:param name="bSpanish" value="<%=bSpanish%>" />
                </jsp:include>

                <%-- top navigation --%>
                <jsputil:topbar />
            	
				<div id="main_content">
					
					<div id="content">
						
							
						<div class="worksheet_header">
							<h2>
								<%=BundleUtil.getString(clBundle, BundleUtil.getPageTextKey("fstep2_01.Title"))%>
							</h2>
						</div>
						<div id="copy">

							<!-- start global messages -->
						    <span id="globalmessages">

						        <!-- error messages -->
							    <jsp:directive.include file="includes/commonErrorMessages.jsp" />
							     <!-- alert messages -->
							    <jsp:directive.include file="includes/commonAlertMessages.jsp" />
							     <!-- confirm messages -->
							    <jsp:directive.include file="includes/commonSuccessMessages.jsp" />

							</span>
							<!-- End Global Messages -->
							
							<!--  START MAIN CONTENT -->
							<p style="margin-bottom:10px">
								<%=BundleUtil.getString(clBundle, BundleUtil.getPageTextKey("txt1"))%>
							</p>
							
					        <div style="clear: both"></div>
					        <div id="szFSCODE_question" class="layout_horizontal" >
					        	<div id="szFSCODE_label" class="short_label center">
									<label for="szFSCODE">
				                        <%=BundleUtil.getString(clBundle, BundleUtil.getFieldLabelKey("szFSCODE"))%>
				                    </label>
						        </div>
						        <jsputil:errorIcon name="szFSCODE" />
				                <div id="szFSCODE_input" class="form_input">
									 <input id="szFSCODE" name="szFSCODE" size="15" type="text" maxlength="6"/>
								</div>
					        </div>
					        <div style="clear: both"></div>
					        <div id="addBtnDiv"  class="btn_center">
				         		<div id="addBtn_enabled">
				         			<%=	JSPUtil.getBtnLink(
								 			"fnAddSchoolByCode()", 
								 			"btn_add", 
								 			BundleUtil.getButtonSrc("btn_add", BundleUtil.BTN_STATE_ON, szFaaMode, clLocale), 
								 			BundleUtil.getImgText("btn_add", clLocale), 
								 			BundleUtil.BTN_STATE_ON)
									%>
								</div>
								<div id="addBtn_disabled"  style="display:none;">
									<img id="btn_add_img"  alt="<%=BundleUtil.getImgText("btn_add", clLocale)%>" src="<%=BundleUtil.getImgPath(clLocale)%>btn_add_wh.gif" />
								</div>
				        	</div>
				        	<div style="clear: both"></div>
				        	<p style="margin-bottom:10px">
					         	<%=BundleUtil.getString(clBundle, BundleUtil.getPageTextKey("txt3"))%>
					        </p>
					         <div id="szFSSTATE_question" class="layout_horizontal" >
						        <div id="szFSSTATE_label" class="short_label center">
									<label for="szFSSTATE" ><%=BundleUtil.getString(clBundle, BundleUtil.getFieldLabelKey("szFSSTATE"))%></label>
						        </div>
						        <jsputil:errorIcon name="szFSSTATE" />
				                <div id="szFSSTATE_input" class="form_input">
									<tagutil:stateDropdown id="szFSSTATE"  name="szFSSTATE"  type="school" />
								 </div>
					        </div>
				         	<div style="clear: both"></div>
					        <p style="margin-bottom:10px">
						        <div id="szFSCITY_question" class="layout_horizontal" >
							        <div id="szFSCITY_label" class="short_label center">
										<label for="szFSCITY"><%=BundleUtil.getString(clBundle, BundleUtil.getFieldLabelKey("szFSCITY"))%></label>
							        </div>
							        <jsputil:errorIcon name="szFSCITY" />
					                <div id="szFSCITY_input" class="form_input">
										<input id=szFSCITY name="szFSCITY" type="text" size="25" maxlength="22"  /><%=BundleUtil.getString(clBundle, "Common.optional")%>
									</div>
						        </div>
					        </p>
					        <div style="clear:both"></div>
					        <p style="margin-bottom:10px">
						        <div id="szFSNAME_question" class="layout_horizontal">
						        	<div id="szFSNAME_label" class="short_label center">
										<label for="szFSNAME"><%=BundleUtil.getString(clBundle, BundleUtil.getFieldLabelKey("szFSNAME"))%></label>
							        </div>
							        <jsputil:errorIcon name="szFSNAME" />
					                <div id="szFSNAME_input" class="form_input">
										<input id="szFSNAME" name="szFSNAME" type="text" size="36" maxlength="34" /><%=BundleUtil.getString(clBundle, "Common.optional")%>
									</div>
						        </div>
				        	</p>
				         	<div style="clear:both"></div>
				         	<div id="searchBtnDiv"  class="btn_center">
				         		<%=	JSPUtil.getBtnLink(
					 			"schoolSearch()", 
					 			"btn_search", 
					 			BundleUtil.getButtonSrc("btn_search", BundleUtil.BTN_STATE_ON, szFaaMode, clLocale), 
					 			BundleUtil.getImgText("btn_search", clLocale), 
					 			BundleUtil.BTN_STATE_ON)
								%>
					        </div>
					        <div style="clear:both"></div>
					       
					        <div id="searchResults_info" ></div>
					        <div id="searchResultDiv" ></div>
					       
					        <p style="margin-bottom:10px">
					         <%=BundleUtil.getString(clBundle, BundleUtil.getPageTextKey("txt4"))%>
					        </p>
					        
					        <div id="hiddenFields" style="text-align: right; display:none">
						        <input  id="szINSTCODE1" name="szINSTCODE1" type="text" value="<tagutil:value name="szINSTCODE1"/>" />
						        <input  id="szINSTCODE2" name="szINSTCODE2" type="text" value="<tagutil:value name="szINSTCODE2"/>" />
						        <input  id="szINSTCODE3" name="szINSTCODE3" type="text" value="<tagutil:value name="szINSTCODE3"/>" />
						        <input  id="szINSTCODE4" name="szINSTCODE4" type="text" value="<tagutil:value name="szINSTCODE4"/>" />
						        <input  id="szINSTCODE5" name="szINSTCODE5" type="text" value="<tagutil:value name="szINSTCODE5"/>" />
						        <input  id="szINSTCODE6" name="szINSTCODE6" type="text" value="<tagutil:value name="szINSTCODE6"/>" />
						        <input  id="szINSTCODE7" name="szINSTCODE7" type="text" value="<tagutil:value name="szINSTCODE7"/>" />
						        <input  id="szINSTCODE8" name="szINSTCODE8" type="text" value="<tagutil:value name="szINSTCODE8"/>" />
						        <input  id="szINSTCODE9" name="szINSTCODE9" type="text" value="<tagutil:value name="szINSTCODE9"/>" />
						        <input  id="szINSTCODE10" name="szINSTCODE10" type="text" value="<tagutil:value name="szINSTCODE10"/>" />
						        
						        <input  id="szVENDORNAME1" name="szVENDORNAME1" type="text" value="<tagutil:value name="szVENDORNAME1"/>" />
						        <input  id="szVENDORNAME2" name="szVENDORNAME2" type="text" value="<tagutil:value name="szVENDORNAME2"/>" />
						        <input  id="szVENDORNAME3" name="szVENDORNAME3" type="text" value="<tagutil:value name="szVENDORNAME3"/>" />
						        <input  id="szVENDORNAME4" name="szVENDORNAME4" type="text" value="<tagutil:value name="szVENDORNAME4"/>" />
						        <input  id="szVENDORNAME5" name="szVENDORNAME5" type="text" value="<tagutil:value name="szVENDORNAME5"/>" />
						        <input  id="szVENDORNAME6" name="szVENDORNAME6" type="text" value="<tagutil:value name="szVENDORNAME6"/>" />
						        <input  id="szVENDORNAME7" name="szVENDORNAME7" type="text" value="<tagutil:value name="szVENDORNAME7"/>" />
						        <input  id="szVENDORNAME8" name="szVENDORNAME8" type="text" value="<tagutil:value name="szVENDORNAME8"/>" />
						        <input  id="szVENDORNAME9" name="szVENDORNAME9" type="text" value="<tagutil:value name="szVENDORNAME9"/>" />
						        <input  id="szVENDORNAME10" name="szVENDORNAME10" type="text" value="<tagutil:value name="szVENDORNAME10"/>" />
						        
						        <input  id="GRADRATE1" name="GRADRATE1" type="text" value="<tagutil:value name="GRADRATE1"/>" />
						        <input  id="GRADRATE2" name="GRADRATE2" type="text" value="<tagutil:value name="GRADRATE2"/>" />
						        <input  id="GRADRATE3" name="GRADRATE3" type="text" value="<tagutil:value name="GRADRATE3"/>" />
						        <input  id="GRADRATE4" name="GRADRATE4" type="text" value="<tagutil:value name="GRADRATE4"/>" />
						        <input  id="GRADRATE5" name="GRADRATE5" type="text" value="<tagutil:value name="GRADRATE5"/>" />
						        <input  id="GRADRATE6" name="GRADRATE6" type="text" value="<tagutil:value name="GRADRATE6"/>" />
						        <input  id="GRADRATE7" name="GRADRATE7" type="text" value="<tagutil:value name="GRADRATE7"/>" />
						        <input  id="GRADRATE8" name="GRADRATE8" type="text" value="<tagutil:value name="GRADRATE8"/>" />
						        <input  id="GRADRATE9" name="GRADRATE9" type="text" value="<tagutil:value name="GRADRATE9"/>" />
						        <input  id="GRADRATE10" name="GRADRATE10" type="text" value="<tagutil:value name="GRADRATE10"/>" />
						        
						        <input  id="RETNRATE1" name="RETNRATE1" type="text" value="<tagutil:value name="RETNRATE1"/>" />
						        <input  id="RETNRATE2" name="RETNRATE2" type="text" value="<tagutil:value name="RETNRATE2"/>" />
						        <input  id="RETNRATE3" name="RETNRATE3" type="text" value="<tagutil:value name="RETNRATE3"/>" />
						        <input  id="RETNRATE4" name="RETNRATE4" type="text" value="<tagutil:value name="RETNRATE4"/>" />
						        <input  id="RETNRATE5" name="RETNRATE5" type="text" value="<tagutil:value name="RETNRATE5"/>" />
						        <input  id="RETNRATE6" name="RETNRATE6" type="text" value="<tagutil:value name="RETNRATE6"/>" />
						        <input  id="RETNRATE7" name="RETNRATE7" type="text" value="<tagutil:value name="RETNRATE7"/>" />
						        <input  id="RETNRATE8" name="RETNRATE8" type="text" value="<tagutil:value name="RETNRATE8"/>" />
						        <input  id="RETNRATE9" name="RETNRATE9" type="text" value="<tagutil:value name="RETNRATE9"/>" />
						        <input  id="RETNRATE10" name="RETNRATE10" type="text" value="<tagutil:value name="RETNRATE10"/>" />
						        
						        <input  id="TRANSRATE1" name="TRANSRATE1" type="text" value="<tagutil:value name="TRANSRATE1"/>" />
						        <input  id="TRANSRATE2" name="TRANSRATE2" type="text" value="<tagutil:value name="TRANSRATE2"/>" />
						        <input  id="TRANSRATE3" name="TRANSRATE3" type="text" value="<tagutil:value name="TRANSRATE3"/>" />
						        <input  id="TRANSRATE4" name="TRANSRATE4" type="text" value="<tagutil:value name="TRANSRATE4"/>" />
						        <input  id="TRANSRATE5" name="TRANSRATE5" type="text" value="<tagutil:value name="TRANSRATE5"/>" />
						        <input  id="TRANSRATE6" name="TRANSRATE6" type="text" value="<tagutil:value name="TRANSRATE6"/>" />
						        <input  id="TRANSRATE7" name="TRANSRATE7" type="text" value="<tagutil:value name="TRANSRATE7"/>" />
						        <input  id="TRANSRATE8" name="TRANSRATE8" type="text" value="<tagutil:value name="TRANSRATE8"/>" />
						        <input  id="TRANSRATE9" name="TRANSRATE9" type="text" value="<tagutil:value name="TRANSRATE9"/>" />
						        <input  id="TRANSRATE10" name="TRANSRATE10" type="text" value="<tagutil:value name="TRANSRATE10"/>" />
						        
						        <input  id="szHSECODE1" name="szHSECODE1" type="text" value="<tagutil:value name="szHSECODE1"/>" />
						        <input  id="szHSECODE2" name="szHSECODE2" type="text" value="<tagutil:value name="szHSECODE2"/>" />
						        <input  id="szHSECODE3" name="szHSECODE3" type="text" value="<tagutil:value name="szHSECODE3"/>" />
						        <input  id="szHSECODE4" name="szHSECODE4" type="text" value="<tagutil:value name="szHSECODE4"/>" />
						        <input  id="szHSECODE5" name="szHSECODE5" type="text" value="<tagutil:value name="szHSECODE5"/>" />
						        <input  id="szHSECODE6" name="szHSECODE6" type="text" value="<tagutil:value name="szHSECODE6"/>" />
						        <input  id="szHSECODE7" name="szHSECODE7" type="text" value="<tagutil:value name="szHSECODE7"/>" />
						        <input  id="szHSECODE8" name="szHSECODE8" type="text" value="<tagutil:value name="szHSECODE8"/>" />
						        <input  id="szHSECODE9" name="szHSECODE9" type="text" value="<tagutil:value name="szHSECODE9"/>" />
						        <input  id="szHSECODE10" name="szHSECODE10" type="text" value="<tagutil:value name="szHSECODE10"/>" />
						        
						        <input  id="SCHOOLADDRESS1" name="SCHOOLADDRESS1" type="text" value="<tagutil:value name="SCHOOLADDRESS1"/>" />
						        <input  id="SCHOOLADDRESS2" name="SCHOOLADDRESS2" type="text" value="<tagutil:value name="SCHOOLADDRESS2"/>" />
						        <input  id="SCHOOLADDRESS3" name="SCHOOLADDRESS3" type="text" value="<tagutil:value name="SCHOOLADDRESS3"/>" />
						       	<input  id="SCHOOLADDRESS4" name="SCHOOLADDRESS4" type="text" value="<tagutil:value name="SCHOOLADDRESS4"/>" />
						        <input  id="SCHOOLADDRESS5" name="SCHOOLADDRESS5" type="text" value="<tagutil:value name="SCHOOLADDRESS5"/>" />
						        <input  id="SCHOOLADDRESS6" name="SCHOOLADDRESS6" type="text" value="<tagutil:value name="SCHOOLADDRESS6"/>" />
						        <input  id="SCHOOLADDRESS7" name="SCHOOLADDRESS7" type="text" value="<tagutil:value name="SCHOOLADDRESS7"/>" />
						        <input  id="SCHOOLADDRESS8" name="SCHOOLADDRESS8" type="text" value="<tagutil:value name="SCHOOLADDRESS8"/>" />
						        <input  id="SCHOOLADDRESS9" name="SCHOOLADDRESS9" type="text" value="<tagutil:value name="SCHOOLADDRESS9"/>" />
						        <input  id="SCHOOLADDRESS10" name="SCHOOLADDRESS10" type="text" value="<tagutil:value name="SCHOOLADDRESS10"/>" />
						        
						    </div>
						    <div id="selectedSchoolsDiv" ></div>
					        <p style="margin-bottom:10px">	
							</p>
							<div id="szOTHERSCHOOLQUESTIONS_question" class="layout_vertical">
								<div id="szOtherSchool_label" class="long_label">
									 <label for="szOTHERSCHOOLQUESTIONS"><%=BundleUtil.getString(clBundle, BundleUtil.getFieldLabelKey("szOTHERSCHOOLQUESTIONS"))%></label>
						        </div>
						        <jsputil:errorIcon name="szOTHERSCHOOLQUESTIONS" />
				                <div id="szOTHERSCHOOLQUESTIONS_input" class="form_input">
				                    <% if(showOTHERSCHOOLQUESTIONS) { %>
				                    	<tagutil:radio default="true" option="1" name="szOTHERSCHOOLQUESTIONS" id="szOtherSchool_yes"/>
						  				<label for="szOtherSchool_yes"><%= clBundle.getString("OptionText.YesNo.1") %></label>
										<tagutil:radio default="false" option="2" name="szOTHERSCHOOLQUESTIONS" id="szOtherSchool_no"/>
						  				<label for="szOtherSchool_no"><%= clBundle.getString("OptionText.YesNo.2") %></label>
				                    <% } else {%>
				                        <tagutil:radio default="false" option="1" name="szOTHERSCHOOLQUESTIONS" id="szOtherSchool_yes"/>
						  				<label for="szOtherSchool_yes"><%= clBundle.getString("OptionText.YesNo.1") %></label>
										<tagutil:radio default="true" option="2" name="szOTHERSCHOOLQUESTIONS" id="szOtherSchool_no"/>
						  				<label for="szOtherSchool_no"><%= clBundle.getString("OptionText.YesNo.2") %></label>
				                    <% } %>
								</div>
							</div>
							<div style="clear:both"></div>
							<div id="other_school_questions" style="display:none">
								<div id="szSIENROLL_question" class="layout_vertical">
									<div id="szSIENROLL_label" class="long_label">
										<label for="szSIENROLL"> <%=BundleUtil.getString(clBundle, BundleUtil.getFieldLabelKey("szSIENROLL"))%> </label>
							        </div>
							        <jsputil:errorIcon name="szSIENROLL" />
							        <div id="szSIENROLL_input" class="form_input">
										<tagutil:dropdown name="szSIENROLL" id="szSIENROLL" options="<%=aryEnrollmentPlanTypes %>" />
									 </div>
								</div>
								<div style="clear:both"></div>
								<p style="margin-bottom:10px">
						        	<label for="szSTUDLOAN"><%=BundleUtil.getString(clBundle, BundleUtil.getFieldLabelKey("szSTUDLOAN"))%></label>
						        </p>
								<div id="szWORKSTUDY_question" class="layout_horizontal " >
									<jsputil:errorIcon name="szWORKSTUDY" />
						            <div id="szWORKSTUDY_input" class="form_input">
										<tagutil:checkbox id="szWORKSTUDY" name="szWORKSTUDY" option="1" />
									</div>
									 <div id="szWORKSTUDY_label" class="short_label">
										<label for="szWORKSTUDY"><%=BundleUtil.getString(clBundle, BundleUtil.getFieldLabelKey("szWORKSTUDY"))%></label>
							        </div>
						        </div>
						        
						       
						        <div style="clear:both"></div>
						        <div id="szSTUDLOAN_question" class="layout_horizontal " >
						        	<jsputil:errorIcon name="szLOANS" />
						            <div id="szSTUDLOAN_input" class="form_input">
										<tagutil:checkbox id="szLOANS" name="szLOANS" option="1" />
									</div>
							        <div id="szSTUDLOAN_label" class="short_label">
										<label for="szLOANS"><%=BundleUtil.getString(clBundle, BundleUtil.getFieldLabelKey("szLOANS"))%></label>
							        </div>
						        </div>
						        <div style="clear:both"></div>
						        <div id="szTEACH_question" class="layout_horizontal" >
						        	<jsputil:errorIcon name="szTEACH" />
						            <div id="szTEACH_input" class="form_input">
										<tagutil:checkbox id="szTEACH" name="szTEACH" option="1" />
									</div>
							        <div id="szTEACH_label" class="short_label">
										<label for="szTEACH"><%=BundleUtil.getString(clBundle, BundleUtil.getFieldLabelKey("szTEACH"))%></label>
							        </div>
						        </div>
						    </div>
							<%-- subfooter tag --%> 
							<jsputil:subfooter>
							</jsputil:subfooter>

						<jsputil:bottombar help="<%=szHelpText%>">
						</jsputil:bottombar>

				
				</div>
				<!-- end main_content -->

				<%-- status message and help & hints --%>
                <jsp:include page="includes/right_scroll_section_inc.jsp" flush="true">
                    <jsp:param name="statusMsg" value="<%=clBundle.getString(BundleUtil.getPageTextKey(\"status\"))%>" />
                    <jsp:param name="hintsPage" value="/fotw1011/hints/hints_school.jsp" />
                    <jsp:param name="bSpanish" value="<%=bSpanish%>" />
                </jsp:include>

				<%-- sitestamp tag --%>
				<div>
				<jsputil:sitestamp />

				<%-- footer tag --%>
				<jsputil:footer>
				</jsputil:footer>

			</form>	
		</div>
		<!-- end main -->

        <%-- processing modal box --%>
        <jsp:directive.include file="includes/processingModal.jsp" />

	</body>
</html>


