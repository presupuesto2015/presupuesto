<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="mainb">
                
		<g:set var="entityName" value="\${message(code: '${domainClass.propertyName}.label', default: '${className}')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
            <div class="container">
		<a href="#create-${domainClass.propertyName}" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="navbar" role="navigation">
			<ul class="nav">
				<li><a class="home" href="\${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="create-${domainClass.propertyName}" class="content scaffold-create" role="main">
			<h1><g:message code="default.create.label" args="[entityName]" /></h1>
			<g:if test="\${flash.message}">
			<div class="message" role="status">\${flash.message}</div>
			</g:if>
			<g:hasErrors bean="\${${propertyName}}">
			<ul class="errors" role="alert">
				<g:eachError bean="\${${propertyName}}" var="error">
				<li <g:if test="\${error in org.springframework.validation.FieldError}">data-field-id="\${error.field}"</g:if>><g:message error="\${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
                        
			<g:form url="[resource:${propertyName}, action:'save']" <%= multiPart ? ' enctype="multipart/form-data"' : '' %>>
				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>
				<fieldset class="form-actions">
					<g:submitButton name="create" class="btn btn-primary" value="\${message(code: 'default.button.create.label', default: 'Create')}" />
				</fieldset>
			</g:form>
		</div>
            </div>
	</body>
</html>
