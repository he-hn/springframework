<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> <!--태그 라이브러리 지시자. jsp에서 제공해주는 태그와 html에서 제공해주는 태그 외에 라이브러리가 제공해주는 태그가 있고, 그것을 alias 하여 사용한다-->

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="card m-2">
   <div class="card-header">
      유효성 검사
   </div>
   <div class="card-body">

      <div class="card m-2">
         <div class="card-header">
            POST 방식으로 요청
         </div>
         <div class="card-body">
            <form id="form0" method="post" action="method1" onsubmit="checkData(this)"> <!-- onsubmit="checkData(this)" 자바스크립트에서 하는 1차 유효성 검사. 여기서 this는 이벤트가 발생한 객체 target.value 이다 -->
               <div class="input-group">
                  <div class="input-group-prepend"><span class="input-group-text">param1</span></div>
                  <input type="text" name="param1" class="form-control" value="">
                  <span class="param1-error text-danger"></span> <!-- 해당 input 에서 문제 발생시 이 부분에 문제 발생에 대한 결과를 보여준다 -->
               </div>
               <div class="input-group">
                  <div class="input-group-prepend"><span class="input-group-text">param2</span></div>
                  <input type="text" name="param2" class="form-control" value="" >
                  <span class="param2-error text-danger"></span>
               </div>
               <div class="input-group">
                  <div class="input-group-prepend"><span class="input-group-text">param3</span></div>
                  <input type="text" name="param3" class="form-control" value="">
                  <span class="param3-error text-danger"></span>
               </div>
               <div class="input-group">
                  <div class="input-group-prepend"><span class="input-group-text">param4</span></div>
                  <div class="btn-group btn-group-toggle" data-toggle="buttons">
                     <label class="btn btn-secondary active"> <!-- 라디오 버튼. 기본적으로 check가 되어 있다. error가 날 이유가 없다. -->
                       <input type="radio" name="param4" checked value="true"> true
                     </label>
                     <label class="btn btn-secondary">
                       <input type="radio" name="param4" value="false"> false
                     </label>
                  </div>
               </div>
               <div class="input-group">
                  <div class="input-group-prepend"><span class="input-group-text">param5</span></div>
                  <input type="date" name="param5" class="form-control">
                  <span class="param5-error text-danger"></span>
               </div>
               <input class="mt-2 btn btn-info btn-sm" type="submit" value="요청"/>
            </form>
         </div>
         <script>
            function checkData(form) { /* this는 이벤트가 발생한 객체가 들어온다. 여기서 이벤트가 발생한 객체는 form 이기 때문에 form을 매개변수로 받는다. 매개변수가 없을 시 let form = event.target 으로 선언하여 사용해도 된다. */
               //form의 제출 기능을 off
               event.preventDefault();
               
               //유효성 검사 결과 변수
               let checkResult = true;
               
               //입력 길이 체크
               let param1 = form.param1.value;
               const param1Error = document.querySelector("#form0 .param1-error");
               param1Error.innerHTML = "";
               if(param1 === "") {
                  param1Error.innerHTML = "필수 입력 사항";
                  checkResult = false;
               } else {
                  if(param1.length<8 || param1.length>15) {
                     param1Error.innerHTML = "8자 이상, 15자 이하로 입력";
                     checkResult = false;
                  };
               }
               
               //정규 표현식을 이용한 전화번호 형식 체크
               let param2 = form.param2.value;
               const param2Error = document.querySelector("#form0 .param2-error");
               param2Error.innerHTML = "";
               if(param2 === "") {
                  param2Error.innerHTML = "필수 입력 사항";
                  checkResult = false;
               } else {
                  const pattern = /(010|011)-[0-9]{3,4}-[0-9]{4}/i;
                  const result = pattern.test(param2);
                  if(result === false) {
                     param2Error.innerHTML = "전화번호 형식이 아님";
                     checkResult = false;
                  }
               }
               
               //정규 표현식을 이용한 이메일 형식 체크
               let param3 = form.param3.value;
               const param3Error = document.querySelector("#form0 .param3-error");
               param3Error.innerHTML = "";
               if(param3 === "") {
                  param3Error.innerHTML = "필수 입력 사항";
                  checkResult = false;
               } else {
                  const pattern = /([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)/i;
                  const result = pattern.test(param3);
                  if(result === false) {
                     param3Error.innerHTML = "이메일 형식이 아님";
                     checkResult = false;
                  }
               }
               
               //날짜가 비었는지 체크
               let param5 = form.param5.value;
               console.log(param5);
               const param5Error = document.querySelector("#form0 .param5-error");
               param5Error.innerHTML = "";
               if(param5 === "") {
                  param5Error.innerHTML = "필수 입력 사항";
                  checkResult = false;
               } 
               
               //서버로 제출할지 말지 결정
               if(checkResult) {
                  form.submit();
               }
            }
         </script>
      </div>
   
      <div class="card m-2">
         <div class="card-header">
            AJAX로 요청
         </div>
         <div class="card-body">
            <form id="form1" name="form1">
               <div class="input-group">
                  <div class="input-group-prepend"><span class="input-group-text">param1</span></div>
                  <input type="text" id="param1" name="param1" class="form-control">
                  <span class="param1-error text-danger"></span>
               </div>
               <div class="input-group">
                  <div class="input-group-prepend"><span class="input-group-text">param2</span></div>
                  <input type="text" id="param2" name="param2" class="form-control">
                  <span class="param2-error text-danger"></span>
               </div>
               <div class="input-group">
                  <div class="input-group-prepend"><span class="input-group-text">param3</span></div>
                  <input type="text" id="param3" name="param3" class="form-control">
                  <span class="param3-error text-danger"></span>
               </div>
               <div class="input-group">
                  <div class="input-group-prepend"><span class="input-group-text">param4</span></div>
                  <div class="btn-group btn-group-toggle" data-toggle="buttons">
                     <label class="btn btn-secondary active">
                       <input type="radio" id="radio1" name="param4" checked value="true"> true
                     </label>
                     <label class="btn btn-secondary">
                       <input type="radio" id="radio2" name="param4" value="false"> false
                     </label>
                  </div>
               </div>
               <div class="input-group">
                  <div class="input-group-prepend"><span class="input-group-text">param5</span></div>
                  <input type="date" id="param5" name="param5" class="form-control" value="2030-12-05">
               </div>
            </form>
            <div class="mt-2">
               <button class="btn btn-info btn-sm" onclick="requestPost()">POST 방식 요청</button>
            </div>
         </div>
         <script>
            function requestPost() {
               const param1 = $("#param1").val(); //주민번호: xxxxxx-1,2,3,4xxxxxx
               const param2 = $("#param2").val(); //년월일: 19680315
               const param3 = $("#param3").val(); //패스워드: 알파벳으로시작 최소 8자 초대 10
               const param4 = $("#form1 input[name=param4]:checked").val();
               const param5 = $("#param5").val();
               
               let checkData = true;
               
               const param1Error = $("#form1 .param1-error");
               param1Error.html("");
               if(param1 === "") {
                  param1Error.html("필수 입력 사항");
                  checkData = false;
               } else {
                  const pattern = /^\d{2}([0]\d|[1][0-2])([0][1-9]|[1-2]\d|[3][0-1])[-]*[1-4][0-9]{6}$/;
                  const result = pattern.test(param1);
                  if(result === false) {
                     param1Error.html("주민번호 형식이 아님");
                     checkData = false;
                  }
               }
               
               if(checkData) {
                  $.ajax({
                     url:"method1",
                     method:"post",
                     data: {
                        param1:param1, 
                        param2, 
                        param3, 
                        param4, 
                        param5
                     }
                  })
                  .done(() => {});
               }
            }
         </script>
      </div>
      
      <div class="card m-2">
         <div class="card-header">
            서버측 유효성 검사
         </div>
         <div class="card-body">
            <div class="card m-2">
               <div class="card-header">
                  회원 가입 폼
               </div>
               <div class="card-body">         
                  <form method="post" action="join">
                     <div class="input-group">
                        <div class="input-group-prepend"><span class="input-group-text">mid</span></div>
                        <input type="text" name="mid" class="form-control" value="${joinForm.mid}" autocomplete="username"> <!-- Dto를 여기서도 사용할 수 있다. 객체의 데이터를 참조 할 때는 객체의 이름으로 부르면 되는데 이때 맨 앞자리는 소문자로 넣음으로 참조 할 수 있게 된다.  -->
                        <form:errors cssClass="text-danger" path="joinForm.mid"/> <!-- namespace가 길어서 alias를 이용한다 --> <!-- getmid 호출해 return 값을 받아서 넣는다. -->
                     </div>
                     <div class="input-group">
                        <div class="input-group-prepend"><span class="input-group-text">mpassword</span></div>
                        <input type="password" name="mpassword" class="form-control" value="${joinForm.mpassword}" autocomplete="current-password">
                        <form:errors cssClass="text-danger" path="joinForm.mpassword"/>
                     </div>
                     <div class="input-group">
                        <div class="input-group-prepend"><span class="input-group-text">memail</span></div>
                        <input type="text" name="memail" class="form-control" value="${joinForm.memail}">
                        <form:errors cssClass="text-danger" path="joinForm.memail"/>
                     </div>
                     <div class="input-group">
                        <div class="input-group-prepend"><span class="input-group-text">mtel</span></div>
                        <input type="text" name="mtel" class="form-control" value="${joinForm.mtel}">
                        <form:errors cssClass="text-danger" path="joinForm.mtel"/>
                     </div>
                     <input class="btn btn-info" type="submit" value="가입"/>
                  </form>
               </div>
            </div>
         
            <div class="card m-2">
               <div class="card-header">
                  로그인 폼
               </div>
               <div class="card-body">
                  <form method="post" action="login">
                     <div class="input-group">
                        <div class="input-group-prepend"><span class="input-group-text">mid</span></div>
                        <input type="text" name="mid" class="form-control" value="${loginForm.mid}"> <!-- defalut value 값이 있다. 그 이유는 만약 사용자가 잘못된 입력을 했을 때, 해당 입력이 남아 있어야 어떤 입력이 잘 못되었는지 알 수 있기 때문이다. -->
                        <form:errors cssClass="error" path="loginForm.mid"/>
                     </div>
                     <div class="input-group">
                        <div class="input-group-prepend"><span class="input-group-text">mpassword</span></div>
                        <input type="password" name="mpassword" class="form-control" value="${loginForm.mpassword}">
                        <form:errors cssClass="error" path="loginForm.mpassword"/>
                     </div>
                     <input class="btn btn-info" type="submit" value="로그인"/>
                  </form>
               </div>
            </div>
         </div>
      </div>
   </div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>