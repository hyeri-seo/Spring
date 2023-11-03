<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게사판글상세</title>
<style type="text/css">
h2 {
	text-align: center;
}
table {
	margin: auto;
	width: 450px;
}
.td_left {
	width: 150px;
	background: orange;
}
.td_right {
	width: 300px;
	background: skyblue;
}
#commandCell {
	text-align: center;
}
#content {
	height: 200px;
}
</style>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script type="text/javascript">
$(function() {
	$('#heart').click(function() {
		$.ajax({
			url:'${contextPath}/like',
			type:'post',
			data:{'num':'<c:out value="${board.num}"/>'},
			success: function(res) {
				if(res==="true") {
					$("#heart").attr("src","${contextPath}/resources/img/redheart.png")
					$("#likecount").text(+$("#likecount").text()+1);
				} else {
					$("#heart").attr("src","${contextPath}/resources/img/blackheart.png")
					$("#likecount").text(+$("#likecount").text()-1);
				}
			},
			error:function(err) {
				console.log(err);
			}
		})
	})
})	

</script>
</head>
<body>
	<section id="./writeForm">
		<h2>게사판글상세</h2>
			<table border="1">
				<tr>
					<td class="td_left"><label for="writer">글쓴이</label></td>
					<td class="td_right">
					<%-- <input type="text" name="writer" id="writer" readonly="readonly" value="${board.writer}"/></td> --%>
					<span>${board.writer}</span></td>
				</tr>
				<tr>
					<td class="td_left"><label for="subject">제목</label></td>
					<td class="td_right">
					<%-- <input name="subject" type="text" id="subject" readonly="readonly" value="${board.subject}"/></td> --%>
					<span>${board.subject}</span>
					</td>
				</tr>
				<tr>
					<td class="td_left"><label for="content">내용</label></td>
					<%-- <td><textarea id="content" name="content" readonly="readonly" cols="40" rows="15">${board.content}</textarea></td> --%>
					<td><div id="content">${board.content}</div></td>
				</tr>
				<tr>
					<td class="td_left"><label>이미지</label></td>
					<td>
					<c:choose>
						<c:when test="${board.fileurl ne null}">
							<img src="${contextPath}/image/${board.fileurl}" width="100px" height="100px"/>
						</c:when>
						<c:otherwise>
							<img src="${contextPath}/resources/img/images.png" width="100px" height="100px"/>
						</c:otherwise>
					</c:choose>
					</td>
				</tr>
				
			</table>
			<section id="commandCell">
				<c:if test="${user.id eq board.writer }">
				<a href="${contextPath}/boardmodify/${board.num}">수정</a>&nbsp;&nbsp;
				</c:if>
				<a href="${contextPath}/boardlist">목록</a>&nbsp;&nbsp;
				좋아요(<span id="likecount">${board.likecount}</span>)&nbsp;&nbsp;
				<c:if test="${user ne Empty}">
					<c:choose>
						<c:when test="${select == true}">
							<img id="heart" src="${contextPath}/resources/img/redheart.png" width="20px" height="20px"/>
						</c:when>
						<c:otherwise>
							<img id="heart" src="${contextPath}/resources/img/blackheart.png" width="20px" height="20px"/>
						</c:otherwise>
					</c:choose>
				</c:if>
			</section>
	</section>
</body>
</html>