<html>

<body>

<#list reportDtos as dto>
<h1>${dto.student1[0].name} </h1>

Grade:  ${dto.student1[0].grade} <br>
Teacher:  ${dto.student1[0].teacher} <br>

    <table border=1>
    <tr>
        <td>Session </td>
        <td>Session Class </td>
        <td>Location </td>
        <td>Rain Plan Location </td>
      </tr>	          	

	

<#list dto.student1 as x>
  	
       <tr>
	        <td> ${x.hour} </td>      
	        <td>${x.activityName}</td>
	        <td>${x.location}</td>
	        <td>${x.altLocation}</td>
      </tr>
      
</#list> 
 


    </table>
    <br><br>
    <br><br>
    <br><br>
    
    
<h1>${dto.student2[0].name} </h1>

Grade:  ${dto.student2[0].grade} <br>
Teacher:  ${dto.student2[0].teacher} <br>

    <table border=1>
    <tr>
        <td>Session </td>
        <td>Session Class </td>
        <td>Location </td>
        <td>Rain Plan Location </td>
      </tr>	          	

	

<#list dto.student1 as x>
  	
       <tr>
	        <td> ${x.hour} </td>      
	        <td>${x.activityName}</td>
	        <td>${x.location}</td>
	        <td>${x.altLocation}</td>
      </tr>
      
</#list> 
 


    </table>
    <br><br>
    
    
    <DIV style="page-break-after:always"></DIV>

</#list>

 </body>
</html>
