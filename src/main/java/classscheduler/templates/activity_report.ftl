<#list reportDtos as activityDto>



<html>
<head>
</head>
<body>

<h1>
	${activityDto.activity.activityName},  ${activityDto.hour}
</h1>
Leader:  ${activityDto.activity.activityLeader} <br>

Location:  ${activityDto.activity.location} <br>
Alternate Location:  ${activityDto.activity.altLocation} <br><br>

<#list activityDto.students as student> <br>
 ${student.name} 
</#list>


 <DIV style="page-break-after:always"></DIV>
</body>
</html>
</#list>
