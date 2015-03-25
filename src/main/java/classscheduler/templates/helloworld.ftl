<#list systems as system>
<html>
<head>
    <title>${title}</title>
</head>
<body>
<h1>${title}</h1>

<p>${exampleObject.name} by ${exampleObject.developer}</p>

${system_index + 1}. ${system.name} from ${system.developer}</li>

</body>
</html>
</#list>