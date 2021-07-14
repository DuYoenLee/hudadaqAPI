<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	<title>Home</title>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.form/3.51/jquery.form.js"></script>
	<script type="text/javascript">
		function fn_goAPI(){
	 		alert("호출");
			$("#frm").ajaxSubmit({
				url: "/api.do",
				type: "POST",
				dataType: "json",
				/* data: { method : "chkLoginID",
		    		loginID : "snumusic3" 
		    			
		    		}, */
				success: function(data){
					alert("success : " + data);
					//fnJsonData(data);
				},
				
				error: function (request, status, error){        
					alert("fail : " + error);
				}
			
			});
		}
		
		function fnJsonData(data){
			var jData = JSON.parse(data);
			for(var i=0;i<jData.length;i++){
				var result = jData[i];
				console.log(result.paramErr);
				console.log(result.list);
				console.log(result.output.chkResult);
				
			}
		}
	</script>
	
</head>
<body>

	<form name="frm" id="frm" enctype="multipart/form-data">
		<input type="hidden" name="method" value="getMileageList" />
		<input type="hidden" name="customerNo" value="12291432" />
		<input type="hidden" name="dtStart" value="2018-02-01" />
		<input type="hidden" name="dtEnd" value="2020-02-01" />
	</form>

<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>

<button value="submit" onclick="fn_goAPI();">submit</button>

</body>
</html>
