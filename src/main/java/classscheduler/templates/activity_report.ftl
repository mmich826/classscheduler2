<#list reportDtos as activityDto>



<html>
<head>
<style type="text/css">
body, table.gridtable{
	font-family: verdana,arial,sans-serif;
}
table.gridtable {
	font-size:14px;
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
	padding: 12px;
	border-style: solid;
	border-color: #666666;
	background-color: #ffffff;
}
</style>
</head>
<body>

<h1>
	${activityDto.activity.activityName},  Session ${activityDto.hour}
</h1>
Time:  ${activityDto.time} <br><br>
Leader:  ${activityDto.activity.activityLeader} <br>

Location:  ${activityDto.activity.location} <br>
Rain Plan Location:  ${activityDto.activity.altLocation} <br><br>

<table class="gridtable">
  <tr>
  	<th></th>
    <th>Student </th>
  </tr>	 
<#list activityDto.students as student>
  <tr>
  	<td>${student_index +1}</td>
  	<td>
 		${student.name} 
 	</td>
  </tr>
</#list>
</table>

 <DIV style="page-break-after:always"></DIV>
</body>
</html>
</#list>
