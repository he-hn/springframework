<%@ page contentType="text/html; charset=UTF-8" %>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="card m-2">
	<div class="card-header">
		예외 처리
	</div>
	<div class="card-body">
		<div class="card">
			<div class="card-header">
				try-catch를 이용한 예외 처리
			</div>
			<div class="card-body">
				<a href="handlingException1" class="btn btn-info btn-sm">try-catch</a> <%-- try-catch를 쓰면 메소드마다 추가해야 하므로 번거로움 --%>
			</div>
		</div>
		
		<div class="card">
			<div class="card-header">
				예외 처리 클래스를 이용한 예외 처리
			</div>
			<div class="card-body">
				<a href="handlingException2" class="btn btn-info btn-sm">NullPointerException</a>
				<a href="handlingException3" class="btn btn-info btn-sm">ClassCastException</a>
				<a href="handlingException4" class="btn btn-info btn-sm">Ch10SoldOutException</a>				
				<a href="handlingException5" class="btn btn-info btn-sm">Exception</a>
			</div>
		</div>
	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>