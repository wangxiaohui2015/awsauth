<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="js/jquery-2.1.3.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		setInterval(changeTime, 1000);
	});
	function changeTime() {
		for (var i = 0; i < 100; i++) {
			var timeObj = $("#second_" + i);
			if (timeObj.length <= 0) {
				return;
			}
			var second = parseInt(timeObj.text());
			if (second <= 1) {
				location.reload();
			}
			timeObj.text(second - 1);
		}
	}
	
</script>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table style="width: 600px" border="1">
		<tr>
			<td>
				Authentication Code
			</td>
			<td>
				Seconds
			</td>
			<td>
				Description
			</td>
		</tr>
		<c:forEach items="${secretCodes}" var="secretCode" varStatus="loop">
			<tr>
				<td style="font-size: 32px; font-weight: bold;">
					${secretCode.secretCode}
				</td>
				<td>
					<label id="second_${loop.index}">${30 - secretCode.second % 30}</label>
				</td>
				<td>
					Only used for user: '${secretCode.userName}'
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>