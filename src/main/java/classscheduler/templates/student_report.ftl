<html>

<body>

<#list reportDtos as dto>
<h1>${dto.student1[0].name} </h1>

${dto.student1[0].grade} <br>
${dto.student1[0].teacher} <br>

    <table border=1>
    <tr>
        <td>Hour </td>
        <td>Activity </td>
        <td>Location </td>
        <td>Teacher </td>
        <td>Alternate Location </td>
      </tr>	          	

	

<#list dto.student1 as x>
  	
       <tr>
	        <td> ${x.time} </td>      
	        <td>${x.activityName}</td>
	        <td>${x.location}</td>
	        <td>${x.activityLeader}</td>
	        <td>${x.altLocation}</td>
      </tr>
      
</#list> 
 


    </table>
    <br><br>
    <br><br>
    <br><br>
    
    
<h1>${dto.student2[0].name} </h1>

${dto.student2[0].grade} <br>
${dto.student2[0].teacher} <br>

    <table border=1>
    <tr>
        <td>Hour </td>
        <td>Activity </td>
        <td>Location </td>
        <td>Teacher </td>
        <td>Alternate Location </td>
      </tr>	          	

	

<#list dto.student2 as x>
  	
       <tr>
	        <td> ${x.time} </td>      
	        <td>${x.activityName}</td>
	        <td>${x.location}</td>
	        <td>${x.activityLeader}</td>
	        <td>${x.altLocation}</td>
      </tr>
      
</#list> 
 


    </table>
    <br><br>
    
    
    <DIV style="page-break-after:always"></DIV>

</#list>

 </body>
</html>
