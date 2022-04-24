<%@ page contentType="text/html; charset=UTF-8" %>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="card m-2">
	<div class="card-header">
		게시물 내용
	</div>
	<div class="card-body">
		<div>
			<div>
				<div>
					<p>
						<span>번호:</span> 
						<span>${board.bno}</span>
					</p>
					
					<p>
						<span>제목:</span> 
						<span>${board.btitle}</span>
					</p>
					
					<p>
						<span>글쓴이:</span> 
						<span>${board.mid}</span>
					<p>
					
					<p>
						<span>날짜:</span> 
						<span><fmt:formatDate value="${board.bdate}" pattern="yyyy-MM-dd HH.mm.ss"/></span> <br/>
					</p>
					
					<c:if test="${board.battachoname !=null}">
						<p>
							<span>첨부:</span> 
							<span>${board.battachoname}<a href="filedownload?bno=${board.bno}" class="btn btn-info btn-sm ml-2">다운로드</a></a></span> <br/>
						</p>
					</c:if>
				</div>
				
				<div>
					<span class="title">내용:</span> <br/>
					<textarea style="width:100%" readonly>${board.bcontent}</textarea>
				</div>
				
				<a class="btn btn-info btn-sm mt-2" href="boardList">목록</a>
				<a class="btn btn-info btn-sm mt-2" href="boardUpdateForm?bno=${board.bno}">수정</a>
				<a class="btn btn-info btn-sm mt-2" href="boardDelete?bno=${board.bno}">삭제</a>
				
			</div>
		</div>
	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>