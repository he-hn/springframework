<%@ page contentType="text/html; charset=UTF-8" %>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="card m-2">
	<div class="card-header">
		회원 가입
	</div>
	<div class="card-body">
		<c:if test="${error != null}">
			<div class="alert alert-danger mb-2" role="alert">
			  ${error}
			</div>			
		</c:if>
	
		<form method="post" action="join">
			<div class="input-group">
				<div class="input-group-prepend"><span class="input-group-text">mid</span></div>
				<input type="text" name="mid" class="form-control" value="${ch14Member.mid}">
			</div>
			<div class="input-group">
				<div class="input-group-prepend"><span class="input-group-text">mname</span></div>
				<input type="text" name="mname" class="form-control value="${ch14Member.mname}">
			</div>
			<div class="input-group">
				<div class="input-group-prepend"><span class="input-group-text">mpassword</span></div>
				<input type="text" name="mpassword" class="form-control">
			</div>
			<div class="input-group">
				<div class="input-group-prepend"><span class="input-group-text">memail</span></div>
				<input type="email" name="memail" class="form-control value="${ch14Member.memail}">
			</div>
			<input class="btn btn-info btn-sm mt-2" type="submit" value="가입"/>
		</form>
	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>