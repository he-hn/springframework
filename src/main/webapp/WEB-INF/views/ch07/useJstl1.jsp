<%@ page contentType="text/html; charset=UTF-8"%> <%-- 페이지 인코딩은 기본적으로 charset을 따라가기 때문에 생략해도 된다 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/WEB-INF/views/common/header.jsp" %> <!-- include는 url로 접근 하는 것이 아닌 애플리케이션 내부 경로로 접근해야 한다 -->

<div class="card m-2">
	<div class="card-header">
		JSTL을 이용해서 배열 반복 처리
	</div>
	<div class="card-body">
		<table class="table table-striped">
         <thead>
           <tr>
             <th>No</th>
             <th>Language</th>
           </tr>
         </thead>
         <tbody>
	         <c:forEach items="${langs}" var="lang" varStatus="status">
	           <tr>
	             <th>${status.count}</th>
	             <th>${lang}</th>
	           </tr>
	         </c:forEach>
         </tbody>
        </table>
	</div>
</div>
	
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
