<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${title}</title>
<link href="style.css" type="text/css" rel="stylesheet" />
</head>
<body>
<div id="header">${title}</div>
<div id="status">
	<c:if test="${score != null}">
		<span style="color: green">You scored: ${score} out of ${fn:length(questions)}</span>
	</c:if>
	<c:if test="${error != null}">
		<span style="color: red">${error}</span>
	</c:if>
</div>
<div id="content">
<form method="post">
	<table>	
		<c:forEach items="${questions}" var="question" varStatus="qstat">
			<tr><td colspan="2"><b>${qstat.count}. ${question.question}</b></td></tr>
		
			<c:set var="pname" value="a${qstat.count}" />
			<c:forEach items="${question.options}" var="option" varStatus="astat">
				<tr>
					<td>${option}</td>
					<td>
						<input type="radio" name="${pname}" value="${astat.count}" ${(param[pname] == astat.count) ? "checked" : ""} />
					
						<c:if test="${score != null}">
							<td>
								<c:if test="${question.correctOption == (astat.count - 1)}">
									<span style="color: ${(param[pname] == astat.count) ? 'green' : 'red'}">&#9786;</span>
								</c:if>
							</td>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		
			<tr><td colspan="2">&nbsp;</td></tr>
		</c:forEach>
	</table>
	<input type="submit" value="Submit" />
	<input type="button" value="Restart" onclick="location.href = location.href" />
</form>
</div>
<div id="footer">Powered by QuizGen 2009</div>
</body>
</html>