<html>
<head>
<style type="text/css">
span.header, table.gridtable{
	font-family: verdana,arial,sans-serif;
}
table.gridtable {
	font-size:12px;
	color:#333333;
	border-width: 1px;
	border-color: #666666;
	border-collapse: collapse;
}
table.gridtable th {
	border-width: 1px;
	padding: 12px;
	border-style: solid;
	border-color: #666666;
	background-color: #dedede;
}
table.gridtable td {
	border-width: 1px;
	padding: 11px;
	border-style: solid;
	border-color: #666666;
	background-color: #ffffff;
}
</style>
</head>

<body>

<#list reportDtos as dto>



<span class="header">
<h1>${dto.student1[0].name} </h1>

Grade:  ${dto.student1[0].grade} <br>
Teacher:  ${dto.student1[0].teacher} <br>
<span>
	<table class="gridtable">
      <tr>
        <th>Session </th>
        <th>Session Class </th>
        <th>Location </th>
        <th>Rain Plan Location </th>
      </tr>	          	

	

<#list dto.student1 as x>
  	
       <tr>
	        <td> ${x.hour} </td>      
	        <td>${x.activityName}</td>
	        <td>${x.location}</td>
	        <td>${x.altLocation}</td>
      </tr>

      <#if x.hour == "3">
       <tr><th colspan=3>LUNCH</th></tr>
      </#if>
      
</#list> 
 


    </table>
    <br><br>
    
<span class="header">    
<h1>${dto.student2[0].name} </h1>

Grade:  ${dto.student2[0].grade} <br>
Teacher:  ${dto.student2[0].teacher} <br>
</span>
   <table class="gridtable">
      <tr strong>
        <th>Session </th>
        <th>Session Class </th>
        <th>Location </th>
        <th>Rain Plan Location </th>
      </tr>	         	

	

<#list dto.student2 as x>
  	
       <tr>
	        <td> ${x.hour} </td>      
	        <td>${x.activityName}</td>
	        <td>${x.location}</td>
	        <td>${x.altLocation}</td>
      </tr>
 
       <#if x.hour == "3">
       <tr><th colspan=3>LUNCH</th></tr>
      </#if>
      
</#list> 
 


    </table>
    
    <DIV style="page-break-after:always"></DIV>




</#list>

 </body>
</html>
