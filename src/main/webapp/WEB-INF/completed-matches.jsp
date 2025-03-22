<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Страница завершенных матчей</title>
    <link rel ="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/completed-matches.css">
</head>
<body>
<h1>Сыгранные матчи:</h1>
<div class="container">
    <div>
        <form action="${pageContext.request.contextPath}/matches" method="get">
            Поиск: <label for="playerName"><input
                id="playerName"
                type="text"
                name="playerName"
                placeholder="Введите имя игрока"
            >
            </label>
            <button type="submit">Найти</button>
        </form>
    </div>
    <div class="tableau">
        <c:choose>
            <c:when test="${requestScope.NoResultException}">
                <p>Данным игроком еще не сыграно матчей.</p>
            </c:when>
            <c:when test="${empty requestScope.matches}">
                <p>Еще не сыграно ни одного матча.</p>
            </c:when>
            <c:otherwise>
                <ol>
                    <c:forEach var="matchResult" items="${requestScope.matches}">
                        <c:choose>
                            <c:when test="${matchResult.player1 eq matchResult.winner}">
                                <li><span class="winner">${matchResult.player1}</span> - <span>${matchResult.player2}</span></li>
                            </c:when>
                            <c:when test="${matchResult.player2 eq matchResult.winner}">
                                <li><span>${matchResult.player1}</span> - <span class="winner">${matchResult.player2}</span></li>
                            </c:when>
                        </c:choose>
                    </c:forEach>
                </ol>
            </c:otherwise>
        </c:choose>
    </div>
    <div>
        <form method="post" action="${pageContext.request.contextPath}/match-score?uuid=${requestScope.uuid}">
            <button type="submit" name="winner" value="player1">&lt;&lt;</button>
            <label>..3..</label>
            <button type="submit" name="winner" value="player2">&gt;&gt;</button>
        </form>
    </div>
</div>
<img src="${pageContext.request.contextPath}/images/racket.png">
</body>
</html>