<%@ page contentType="text/html; charset=UTF-8" %> <!-- 응답의 타입을 명시 -->

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="card m-2">
	<div class="card-header">
		Controller/Forward & Redirect
	</div>
	<div class="card-body">
		<a href="forward" class="btn btn-info btn-sm">JSP 포워드</a>
		<a href="redirect" class="btn btn-info btn-sm">홈으로 리다이렉트</a>
		
		<hr/>
		
		<p>[AJAX 요청은 리다이렉트를 하면 안됨]</p>
		<a href="javascript:ajax1()" class="btn btn-info btn-sm">AJAX 요청(HTML 조각 얻기)</a>
		<a href="javascript:ajax2()" class="btn btn-info btn-sm">AJAX 요청(JSON)</a>
		<a href="javascript:ajax3()" class="btn btn-info btn-sm">AJAX 요청(JSON)</a>
		<a href="javascript:ajax4()" class="btn btn-info btn-sm">AJAX 요청(리다이렉트) - (x)</a>
		<div id="content" class="mt-2"></div>
		<script> 
			function ajax1() { /* html 조각을 받는다 */
				console.log("ajax1() 실행");
				$.ajax({
					url:"getFragmentHtml" /* 상대 경로 이므로 ch06까지 생략 된다 */
				})
				.done((data) => { /* 응답의 내용이 들어온다 */
					$("#content").html(data); /* 응답의 내용을 받아서 응답을 바로 제공해줘야 한다. 그렇기 때문에 redirect 할 수 없다. */
				});
			}
			
			function ajax2() { /* json을 받는다 */
				console.log("ajax2() 실행");
				$.ajax({
					url:"getJson1"
				})
				.done((data) => { /* 응답이 json으로 들어온다 */
					$("#content").html(
						"<img src='${pageContext.request.contextPath}/resources/images/" +  /* 응답이 들어오면 img 태그를 만든다 */
						data.fileName + "' width='200px'/>"); /* 파싱전: {"fileName":"photo6.jpg"} 파싱후: {fileName:"photo6.jpg"} 본문에 있는 문자열을 파싱해서 js 객체로 변환 */
				});
			}
			
			function ajax3() {
				console.log("ajax3() 실행");
				$.ajax({
					url:"getJson2"
				})
				.done((data) => {
					$("#content").html(
						"<img src='${pageContext.request.contextPath}/resources/images/" + 
						data.fileName + "' width='200px'/>");
				});
			}
			
			function ajax4() {
				console.log("ajax4() 실행");
				$.ajax({
					url:"getJson3"
				})
				.done((data) => {
					console.log(data);
					$("#content").html(
						"<img src='${pageContext.request.contextPath}/resources/images/" + 
						data.fileName + "' width='200px'/>");
				});
			}
		</script>
	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>