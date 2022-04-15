<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="card m-2">
	<div class="card-header">
		Session Support
	</div>
	<div class="card-body">
		<div class="card">
			<div class="card-header">
				세션 원리: JSESSIONID 쿠키
			</div>
			<div class="card-body">
				<p>서버: 세션 객체 생성->JSESSIONID 쿠기 발생</p>
				<p>브라우저: JSESSIONID 쿠키 전송 -> 세션 객체 찾음 -> 세션 객체 이용</p>
				<a href="javascript:saveData()" class="btn btn-info btn-sm">세션에 데이터 저장</a>
				<a href="javascript:readData()" class="btn btn-info btn-sm">세션에 데이터 읽기</a>
			</div>
			<script>
				function saveData() {
					$.ajax({
						url: "saveData",
						data: {name:"홍길동"} /* get 방식이다. default는 get방식. <a href="saveData()?name=홍길동"> 과 같다.*/
					})
					.done((data) => { /* {"result":"success"} 형태의 결과가 넘어온다고 가정 */
						console.log(data);
					});
				}
				
				function readData() {
					$.ajax({
						url: "readData"
					})
					.done((data) => {
						console.log(data); //{name:"홍길동"}
						console.log(data.name);
					});
				}
			</script>
		</div>
		
		<div class="card">
			<div class="card-header">
				form을 통한 login 처리
			</div>
			<div class="card-body">
				<c:if test="${sessionMid == null}"> <%-- session에 sessionMid 가 저장되어 있지 않다면 실행 --%>
					<a href="login" class="btn btn-info btn-sm">로그인 폼 요청</a>
				</c:if>
				<c:if test="${sessionMid != null}">
					<a href="logout" class="btn btn-info btn-sm">로그아웃</a> <%-- session에 sessionMid 가 저장되어 있다면 실행 --%>
					<a href="userinfo" class="btn btn-info btn-sm">사용자 정보</a>
				</c:if>
			</div>
		</div>
		
		<div class="card">
			<div class="card-header">
				AJAX을 통한 login 처리
			</div>
			<div class="card-body">
				<c:if test="${sessionMid == null}">
					<form>
						<div class="input-group">
							<div class="input-group-prepend"><span class="input-group-text">mid</span></div>
							<input id="mid" type="text" name="mid" class="form-control">
							<span id="mid-error" class="error"></span>
						</div>
						<div class="input-group">
							<div class="input-group-prepend"><span class="input-group-text">mpassword</span></div>
							<input id="mpassword" type="password" name="mpassword" class="form-control">
							<span id="mpassword-error" class="error"></span>
						</div>
					</form>
				</c:if>
				<div class="mt-2">
					<c:if test="${sessionMid == null}">
						<a href="javascript:login()" class="btn btn-info btn-sm">로그인</a>
					</c:if>
					<c:if test="${sessionMid != null}">
						<a href="javascript:logout()" class="btn btn-info btn-sm">로그아웃</a>
					</c:if>
				</div>
				<script>
					function login() {
						let mid = $("#mid").val();
						let mpassword = $("#mpassword").val();
						$.ajax({
							url: "loginAjax",
							data: {mid, mpassword}, //변수명과 속성명이 같다면 생략이 가능하다
							method: "post"
						}).done((data) => {
							//data = {result:"success"}
							//data = {result:"wrongMid"}
							//data = {result:"wrongMpassword"}
							
							const midError = $("#mid-error");
							const mpasswordError = $("#mpassword-error");
							midError.html("");
							mpasswordError.html("");
							
							if(data.result==="success") {
								//현재 페이지 전체를 다시 서버에서 받아오도록 함
								window.location.reload(); //reload() 메소드는 현재 페이지를 refresh 하는 효과가 있다. 페이지의 갱신이 필요하기 떄문이다. ex) 로그인 -> 로그아웃
							} else if(data.result==="wrongMid") {
								midError.html("아이디가 잘못됨");
							} else if(data.result==="wrongMpassword") {
								mpasswordError.html("패스워드가 잘못됨");
							}
						});
					}
					
					function logout() {
						$.ajax({
							url: "logoutAjax"
						}).done((data) => {
							//data = {result:"success"}
							//현재 페이지 전체를 다시 서버에서 받아오도록 함
							window.location.reload(); //refresh 한다. ex) 로그아웃 -> 로그인
						});
					}
				</script>
			</div>
		</div>
	
		<div class="card">
			<div class="card-header">
				@SesseionAttributes를 이용한 다단계 입력처리
			</div>
			<div class="card-body">
				<a href="inputStep1" class="btn btn-info btn-sm">1단계 입력</a>
			</div>
		</div>
	
	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>