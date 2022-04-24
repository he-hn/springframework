<%@ page contentType="text/html; charset=UTF-8" %>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="card m-2">
	<div class="card-header">
		Data Access
	</div>
	<div class="card-body">
		<div class="card">
			<div class="card-header">
				MyBatis를 이용하는 Dao
			</div>
			<div class="card-body">
				<a href="join" class="btn btn-info btn-sm">회원 가입</a>
				<hr/>
				<c:if test="${sessionMid == null}">
					<a href="login" class="btn btn-info btn-sm">로그인</a>
				</c:if>
				<c:if test="${sessionMid != null}">
					<a href="logout" class="btn btn-info btn-sm">로그아웃</a>
				</c:if>
				<hr/>
				<a href="boardList" class="btn btn-info btn-sm">게시판</a>
			</div>
		</div>
	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>