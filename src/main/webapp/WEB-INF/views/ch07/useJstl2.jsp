<%@ page contentType="text/html; charset=UTF-8"%> 

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="card m-2">
	<div class="card-header">
		JSTL을 이용해서 List 반복 처리
	</div>
	<div class="card-body">
		<table class="table table-striped">
         <thead>
           <tr>
             <th>No</th>
             <th>Title</th>
             <th>Content</th>
             <th>Writer</th>
             <th>Date</th>
           </tr>
         </thead>
         <tbody>
         	<c:forEach items="${boardList}" var="board" varStatus="status">
	           <tr>
	             <th>${board.no}</th> <%-- 필드 아니라 getter로 가져오는 것이다 --%>
	             <th>${board.title}</th>
	             <th>${board.content}</th>
	             <th>${board.writer}</th>
	             <th><fmt:formatDate value="${board.date}" pattern="yyyy-MM-dd"/></th>
	           </tr>            	
         	</c:forEach>      	
         </tbody>
        </table>
	</div>
</div>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
