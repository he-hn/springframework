<%@ page contentType="text/html; charset=UTF-8" %>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="card m-2">
   <div class="card-header">
      Controller/Request Parameter
   </div>
   <div class="card-body">
      <div class="card m-2">
         <div class="card-header">
            GET 방식으로 요청
         </div>
         <div class="card-body">
            <a class="btn btn-info btn-sm" 
               href="method1?param1=문자열 &param3=3.14&param4=true&param5=2021-08-27">요청</a>
            <hr/>
            <form method="get" action="method1">
               <div class="input-group">
                  <div class="input-group-prepend"><span class="input-group-text">param1</span></div>
                  <input type="text" name="param1" class="form-control" value="문자열">
               </div>
               <div class="input-group">
                  <div class="input-group-prepend"><span class="input-group-text">param2</span></div>
                  <input type="text" name="param2" class="form-control" value="5" >
               </div>
               <div class="input-group">
                  <div class="input-group-prepend"><span class="input-group-text">param3</span></div>
                  <input type="text" name="param3" class="form-control" value="3.14">
               </div>
               <div class="input-group">
                  <div class="input-group-prepend"><span class="input-group-text">param4</span></div>
                  <div class="btn-group btn-group-toggle" data-toggle="buttons">
                     <label class="btn btn-secondary active">
                       <input type="radio" name="param4" checked value="true"> true
                     </label>
                     <label class="btn btn-secondary">
                       <input type="radio" name="param4" value="false"> false
                     </label>
                  </div>
               </div>
               <div class="input-group">
                  <div class="input-group-prepend"><span class="input-group-text">param5</span></div>
                  <input type="date" name="param5" class="form-control" value="2030-12-05">
               </div>
               <input class="mt-2 btn btn-info btn-sm" type="submit" value="요청"/>
            </form>
         </div>
      </div>
      
      <div class="card m-2">
         <div class="card-header">
            POST 방식으로 요청
         </div>
         <div class="card-body">
            <form method="post" action="method2">
               <div class="input-group">
                  <div class="input-group-prepend"><span class="input-group-text">param1</span></div>
                  <input type="text" name="param1" class="form-control" value="문자열">
               </div>
               <div class="input-group">
                  <div class="input-group-prepend"><span class="input-group-text">param2</span></div>
                  <input type="text" name="param2" class="form-control" value="5" >
               </div>
               <div class="input-group">
                  <div class="input-group-prepend"><span class="input-group-text">param3</span></div>
                  <input type="text" name="param3" class="form-control" value="3.14">
               </div>
               <div class="input-group">
                  <div class="input-group-prepend"><span class="input-group-text">param4</span></div>
                  <div class="btn-group btn-group-toggle" data-toggle="buttons">
                     <label class="btn btn-secondary active">
                       <input type="radio" name="param4" checked value="true"> true
                     </label>
                     <label class="btn btn-secondary">
                       <input type="radio" name="param4" value="false"> false
                     </label>
                  </div>
               </div>
               <div class="input-group">
                  <div class="input-group-prepend"><span class="input-group-text">param5</span></div>
                  <input type="date" name="param5" class="form-control" value="2030-12-05">
               </div>
               <input class="mt-2 btn btn-info btn-sm" type="submit" value="요청"/>
            </form>
         </div>
      </div>
   
      <div class="card m-2">
         <div class="card-header">
            AJAX로 요청
         </div>
         <div class="card-body">
            <form id="form1" name="form1">
               <div class="input-group">
                  <div class="input-group-prepend"><span class="input-group-text">param1</span></div>
                  <input type="text" id="param1" name="param1" class="form-control" value="문자열">
               </div>
               <div class="input-group">
                  <div class="input-group-prepend"><span class="input-group-text">param2</span></div>
                  <input type="text" id="param2" name="param2" class="form-control" value="5" >
               </div>
               <div class="input-group">
                  <div class="input-group-prepend"><span class="input-group-text">param3</span></div>
                  <input type="text" id="param3" name="param3" class="form-control" value="3.14">
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
               <button class="btn btn-info btn-sm" onclick="requestGet()">GET 방식 요청</button>
               <button class="btn btn-info btn-sm" onclick="requestPost()">POST 방식 요청</button>
            </div>
         </div>
         <script>
            function requestGet() {
               const param1 = document.form1.param1.value; /* form에 name 이 있으면 name으로 접근할 수 있다 docment form의 이름, form에 param1의 value 값*/
               const param2 = document.querySelector("#param2").value; /* id를 이용해서 선택 */
               const param3 = $("#param3").val(); /* 제이쿼리 이용. 제이쿼리 객체이므로 .val()이라는 메소드를 이용해야 한다. id가 param3인것 */
               const param4 = $("#form1 input[name=param4]:checked").val();
               const param5 = $("#param5").val();
               
               console.log("param1:", param1);
               console.log("param2:", param2);
               console.log("param3:", param3);
               console.log("param4:", param4);
               console.log("param5:", param5);
               
               $.ajax({
                  url:"method1",
                  method:"get",
                  data: {
                     param1:param1, /* 속성이름과 변수명이 같을 때는 하나만 적어도 된다 */
                     param2, 
                     param3, 
                     param4, 
                     param5
                  }
               })
               .done(() => {}); /* .done() 성공적으로 응답이 되었을 때, 콜백 된다 */
            }
            
            function requestPost() {
               const param1 = document.form1.param1.value;
               const param2 = document.querySelector("#param2").value;
               const param3 = $("#param3").val();
               const param4 = $("#form1 input[name=param4]:checked").val();
               const param5 = $("#param5").val();
               
               $.ajax({
                  url:"method2",
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
         </script>
      </div>
   
   	  <div class="card m-2">
         <div class="card-header">
            필수 파라미터
         </div>
         <div class="card-body">
         	<a href="method3?&param1=문자열1&param2=문자열2" class="btn btn-info btn0-sm">요청</a>
       	</div>
      </div>
      
       <div class="card m-2">
         <div class="card-header">
            디폴트 값을 갖는 매개변수
         </div>
         <div class="card-body">
         	<a href="method4" class="btn btn-info btn0-sm">요청</a>
       	</div>
      </div>
      
      <div class="card m-2">
         <div class="card-header">
            객체로 파라미터 값 받기
         </div>
         <div class="card-body">
         	<form method="post" action="method5">
               <div class="input-group">
                  <div class="input-group-prepend"><span class="input-group-text">param1</span></div>
                  <input type="text" name="param1" class="form-control" value="문자열">
               </div>
               <div class="input-group">
                  <div class="input-group-prepend"><span class="input-group-text">param2</span></div>
                  <input type="text" name="param2" class="form-control" value="5" >
               </div>
               <div class="input-group">
                  <div class="input-group-prepend"><span class="input-group-text">param3</span></div>
                  <input type="text" name="param3" class="form-control" value="3.14">
               </div>
               <div class="input-group">
                  <div class="input-group-prepend"><span class="input-group-text">param4</span></div>
                  <div class="btn-group btn-group-toggle" data-toggle="buttons">
                     <label class="btn btn-secondary active">
                       <input type="radio" name="param4" checked value="true"> true
                     </label>
                     <label class="btn btn-secondary">
                       <input type="radio" name="param4" value="false"> false
                     </label>
                  </div>
               </div>
               <div class="input-group">
                  <div class="input-group-prepend"><span class="input-group-text">param5</span></div>
                  <input type="date" name="param5" class="form-control" value="2030-12-05">
               </div>
               <input class="mt-2 btn btn-info btn-sm" type="submit" value="요청"/>
            </form>
       	</div>
      </div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>