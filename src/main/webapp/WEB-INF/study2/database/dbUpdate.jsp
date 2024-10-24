<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>dbInput.jsp</title>
  <jsp:include page="/include/bs4.jsp" />
  <script type="text/javascript">
  	'use strict'
  	
  	function deleteOk() {
		let ans = confirm("정말 삭제하시겠습니까?");
		if(ans){
			myform.action = "${ctp}/study2/DbDeleteOk";
			myform.submit();
		}
	}
  	
  </script>
</head>
<body>
<jsp:include page="/include/header.jsp" />
<jsp:include page="/include/nav.jsp" />
<p><br /></p>
<div class="container">
  <h2>회원 정보 수정</h2>
  <form name="form-control" method="post" action="${ctp}/study2/database/DbInputOk">
  	<div>성명<input type="text" name="name" class="form-control" autofocus required /></div>
  	<div>나이<input type="number" name="age" class="form-control" required /></div>
  	<div>성별
	  	<div><input type="radio" name="gender" value="남자" checked />남자</div>
	  	<div><input type="radio" name="gender" value="여자" />여자</div>
  	</div>
  	<div class="mb-3">주소<input type="text" name="address" class="form-control" required /></div>
  	<div>
  		<button type="submit" class="btn btn-success">수정</button>
  		<button type="reset" class="btn btn-warning">다시 입력</button>
		<a href="${ctp}/study2/database/DbList" class="btn btn-info">돌아가기</a>  		
  		<button type="button" onclick="deleteOk()" class="btn btn-success">삭제</button>
  	</div>
  </form>
</div>
<p><br /></p>
<jsp:include page="/include/footer.jsp" />
</body>
</html>