<%@ page contentType="text/html; charset=UTF-8" %>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="card m-2">
	<div class="card-header">
		DTO 객체(Command Object)와 폼 연결
	</div>
	<div class="card-body">
		<form:form method="post" modelAttribute="member">
		  <div class="form-group">
		    <label for="mid">ID</label>
		    <form:input class="form-control" path="mid"/> <%-- path에 이름이 id와 name으로 들어간다 --%>
		  </div>
		  <div class="form-group">
		    <label for="mname">Name</label>
		    <form:input class="form-control" path="mname"/>
		  </div>
		  <div class="form-group">
		    <label for="mpassword">Password</label>
		    <form:password class="form-control" path="mpassword"/>
		  </div>
		   <form:hidden class="form-control" path="mnation"/>
		  <button class="btn btn-primary">Submit</button>
		</form:form>
	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>