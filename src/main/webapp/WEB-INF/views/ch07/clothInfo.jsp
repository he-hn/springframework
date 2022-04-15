<%@ page contentType="text/html; charset=UTF-8"%> 

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="card m-2">
	<div class="card-header">
		ModelAttribute를 이용한 데이터 전달
	</div>
	<div class="card-body">
		<p>cloth: ${kind}</p>
		<p>cloth sex: ${sex}</p>
		
		<hr/>
		
		<%-- <p>cloth: ${ch07Cloth.kind}</p>
		<p>cloth sex: ${ch07Cloth.sex}</p> --%>
		
		<p>cloth: ${cloth.kind}</p>
		<p>cloth sex: ${cloth.sex}</p>
	</div>
</div>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
