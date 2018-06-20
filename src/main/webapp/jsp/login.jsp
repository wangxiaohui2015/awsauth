<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>AWS Auth</title>
<base href="<%=basePath%>">
</head>
<body>
	<form action="login" method="post">
		<table width="300px">
			<tr>
				<td colspan="2" style="text-align: center; font-size: 30px;">
					AWS Auth Login
				</td>
			</tr>
			<tr>
				<td width="100px">
					User Name:
				</td>
				<td>
					<input type="text" name="username" maxlength="20" value="${userName}"/>
				</td>
			</tr>
			<tr>
				<td>
					Password:
				</td>
				<td>
					<input type="password" name="password" maxlength="20" value="${password}"/>
				</td>
			</tr>
			<c:if test="${loginResult eq -1}">
				<tr>
					<td colspan="2" style="color: red;">
						User name or password is incorrect!
					</td>
				</tr>
			</c:if>
			<tr>
				<td colspan="2">
					<input type="submit" value="Submit">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>