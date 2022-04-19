<%@ page contentType="text/html; charset=UTF-8" %>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="card">
	<div class="card-header">
		ViewName을 객체 이름으로 해석
	</div>
	
	<div class="card-body">
		<h6>다운로드 파일 목록(<span id="totalFileNum" class="text-danger">0</span>)</h6>
		<div id="fileList"></div>
	</div>
	<script>
		$(function() {
			$.ajax({
				url: "fileList"
			}).done(data => {
				console.log(data);
				//{"totalFileNum":20, "fileList": ["a.jpg", "b.jpg", ... ]}
				var ulTag = "<ul>";
				for (var fileName of data.fileList) {
					ulTag += "<li>";
					ulTag += " <a href='fileDownload?fileName=" + fileName + "'>" + fileName + "</a>";
					ulTag += "</li>"
				}
				ulTag += "</ul>"
				$("#totalFileNum").html(data.totalFileNum);
				$("#fileList").html(ulTag);
			});
		})
	</script>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>
