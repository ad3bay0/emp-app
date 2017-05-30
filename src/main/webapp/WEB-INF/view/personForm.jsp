<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>

<html lang="en">
<head>

<title>Person Home</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css"/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/lib/bootstrap.min.css"/>"/>
</head>
<body>
	<form:form action="addPerson" id="myform" method="post" modelAttribute="person">

		<h3>
		<c:if test="${person.pid==0}">
         
         Add New Person

         </c:if>

			<c:if test="${person.pid!=0 }">

Update <c:out value="${person.username}" />
     <form:hidden path="pid"/>

			</c:if>
		</h3>

		<table>
			<tr>
				<td>User Name:</td>
				<td><form:input path="username" /> <form:errors path="username"
						cssClass="error-msg" /></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><form:input path="password" /> <form:errors path="password"
						cssClass="error-msg" /></td>
			</tr>
			<tr>
				<td>Age:</td>
				<td><form:input path="age" /> <form:errors path="age"
						cssClass="error-msg" /></td>
			</tr>
			<tr>
				<td>Gender:</td>
				<td><form:radiobuttons path="gender" items="${genderOptions }" />

					<form:errors path="gender" cssClass="error-msg"/></td>
			</tr>

			<tr>
				<td>City:</td>
				<td><form:select path="city">

						<form:option value="" label="---Select City---"></form:option>
						<form:options items="${cityMap}" />

					</form:select> <form:errors path="city" cssClass="error-msg"/></td>
			</tr>
			
			<tr>
			<td>
			<c:if test="${person.pid == 0 }">
			
			<input type="button" value="Add" id="btn-add" />
			
			</c:if>
			
			<c:if test="${person.pid !=0 }">
			
			<input type="button" value="Update" id="btn-update">
			</c:if>
			
	
			</td>
			
			
			</tr>
			
			<tr>
			
			<td class="success-msg"><c:out value="${msg}"></c:out></td>
			
			</tr>



		</table>
		
		<table>
		
		<tr>
		<td>ID</td>
		<td>User Name</td>
		<td>Age</td>
		<td>Gender</td>
		<td>City</td>
		
		
		</tr>
		
		<c:forEach var="obj" items="${allPersons}">
		
		<tr>
		
		<td><c:out value="${obj.pid }"/></td>
		<td><c:out value="${obj.username }"/></td>
		<td><c:out value="${obj.age }"/></td>
		<td><c:out value="${obj.gender }"/></td>
		<td><c:out value="${obj.city }"/></td>
		<td>
		
		
		<a href='<c:url value="/deletePerson?pid=${obj.pid}" ></c:url>'>Delete</a>
		<a href='<c:url value="/personById?pid=${obj.pid}" ></c:url>'>Edit</a>
		
		
		<!--<a href="${pageContext.request.contextPath}/deletePerson?pid=${obj.pid}">Delete</a> 
		
		<a href="${pageContext.request.contextPath}/personById?pid=${obj.pid}">Edit</a>
		 -->
		
		
		</td>
		
		</tr>
		
		
		
		
		</c:forEach>
		
		
		</table>
		
		
		

	</form:form>
	
	<script src="<c:url value="/resources/js/lib/jquery-3.2.1.min.js"/>"></script>
	<script src="<c:url value="/resources/js/myapp.js"/>"></script>
	
	
	



</body>


</html>