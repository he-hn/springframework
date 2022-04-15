<%@ page contentType="text/html; charset=UTF-8" %>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="card m-2">
	<div class="card-header">
		FileUpload & FileDownload
	</div>
	<div class="card-body">
		<div class="card">
			<div class="card-header">
				Form 태그를 이용한 FileUpload
			</div>
			<div class="card-body">
				<form method="post" enctype="multipart/form-data" action="fileupload"> <!-- enctype="multipart/form-data" 을 사용하려면 무조건 post 여야 한다 -->
					<div class="form-group">
						<label for="title">File Title</label> 
						<input type="text" class="form-control" id="title" name="title" placeholder="제목">
					</div>
					<div class="form-group">
						<label for="desc">File Description</label> 
						<input type="text" class="form-control" id="desc" name="desc" placeholder="설명">
					</div>
					<div class="form-group">
					    <label for="attach">Example file input</label>
					    <input type="file" class="form-control-file" id="attach" name="attach" multiple>
				  	</div>

					<button class="btn btn-info btn-sm">Form 파일 업로드</button>
				  	<a href="javascript:fileupload()" class="btn btn-info btn-sm">AJAX 파일 업로드</a>
				</form>
			</div>
			<script>
				function fileupload() {
					//입력된 정보를 얻기
					const title = $("#title").val();
					const desc = $("#desc").val();
					//const attach = document.querySelector("#attach").files[0]; //file에 multiple 속성이 들어가면 여러개 선택이 된다. multiple이 없으면 하나만 선택이 된다. 그렇기 때문에 배열의 형태로 저장이 되는 속성이 있기 때문에 files[0]으로 파일 하나를 선택한다
					const attach = $("#attach")[0].files[0];
					
					//Multipart/form-data
					const formData = new FormData();
					formData.append("title", title); //문자파트
					formData.append("desc", desc); //문자파트
					formData.append("attach", attach); //파일파트
					
					//Ajax로 서버로 전송
					$.ajax({
						url: "fileuploadAjax",
						method: "post",
						data: formData, //객체를 바로 준다
						//cache, processData, contentType 모두 필요하다 하나라도 없으면 돌아가지 않는다.
						cache: false, 		//파일이 포함되어 있으니, 브라우저 메모리에 저장하지 마라
						processData: false, //title=xxx&desc=yyy& 식으로 만들지 마라
						contentType: false	//파트마다 Content-Type이 포함되기 때문에 따로 헤더에 Content-Type에 추가하지 마라 
					}).done((data) => {
						console.log(data);
						if(data.result === "success") {
							window.alert("파일 전송이 성공됨");
						}
					});
				}
			</script>
		</div>
	
		<div class="card">
			<div class="card-header">
				File Downlaod
			</div>
			<div class="card-body">
				<a href="filedownload?fileNo=1"
				   class="btn btn-info btn-sm">파일 다운로드</a>
				<hr/>
				<img src="filedownload?fileNo=1" width="200px"/>
			</div>
		</div>
	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>