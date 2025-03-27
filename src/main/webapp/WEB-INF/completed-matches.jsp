<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
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
                title="Допустимы для ввода: буквы, пробел, точка, дефис. Максимальное длина имени 20 символов."
            <c:if test="${not empty requestScope.filterByPlayerName}">
                value="${requestScope.filterByPlayerName}"
            </c:if>
            >
            </label>
            <button type="button" onclick="clearInput()">X</button>
            <button type="submit">Найти</button>
        </form>
    </div>
    <div class="tableau">
        <c:choose>
            <c:when test="${requestScope.IncorrectNameException}">
                    <p class="error">Имя игрока введено некорректно! <br>
                        Допустимы для ввода: буквы, пробел, точка, дефис.<br>
                        Максимальное длина имени 20 символов.<br>
                        Для вывода всех сыгранных матчей осуществите<br>
                        поиск вез ввода символов.</p>
            </c:when>
            <c:when test="${requestScope.NoResultException}">
                <p>Данным игроком еще не сыграно матчей.</p>
            </c:when>
            <c:when test="${empty requestScope.matches}">
                <p>Еще не сыграно ни одного матча.</p>
            </c:when>
            <c:otherwise>
                <ol start="${requestScope.numberOfLinesPerPage * (requestScope.currentPage - 1) + 1}">
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
        <c:choose>
            <c:when test="${requestScope.IncorrectNameException}">
            </c:when>
            <c:when test="${requestScope.totalPages < 2}">
            </c:when>
            <c:otherwise>
                <form method="get" action="${pageContext.request.contextPath}/matches">
                    <c:if test="${not empty requestScope.filterByPlayerName}">
                        <input type="hidden"  name="playerName" value="${requestScope.filterByPlayerName}"/>
                    </c:if>
                    Страницы:
                    <c:if test="${requestScope.currentPage > 2}">
                        <button type="submit" name="page" value="1" title="Перейти к первой станице">&lt;&lt;</button>
                    </c:if>
                    <c:if test="${requestScope.currentPage > 1}">
                        <button type="submit" name="page" value="${requestScope.currentPage - 1}" title="Перейти на страницу назад">&lt;</button>
                    </c:if>
                    <label>
                        <c:if test="${requestScope.currentPage > 1}">
                            1
                        </c:if>
                        <c:if test="${requestScope.currentPage > 2}">
                            . .
                        </c:if>
                        <span class="winner">${requestScope.currentPage}</span>
                        <c:if test="${(requestScope.totalPages - requestScope.currentPage) > 1}">
                            . .
                        </c:if>
                        <c:if test="${(requestScope.totalPages - requestScope.currentPage) > 0}">
                              ${requestScope.totalPages}
                        </c:if>
                    </label>
                    <c:if test="${(requestScope.totalPages - requestScope.currentPage) > 0}">
                        <button type="submit" name="page" value="${requestScope.currentPage + 1}" title="Перейти на страницу вперед">&gt;</button>
                    </c:if>
                    <c:if test="${(requestScope.totalPages - requestScope.currentPage) > 1}">
                        <button type="submit" name="page" value="${requestScope.totalPages}" title="Перейти к последней странице">&gt;&gt;</button>
                    </c:if>
                </form>
            </c:otherwise>
        </c:choose>
    </div>
    <form action=${requestScope.new_match_url}>
        <button>Вернуться на главную страницу</button>
    </form>
</div>
<img src="${pageContext.request.contextPath}/images/racket.png" alt="">
<script>
    function clearInput() {
        document.getElementById('playerName').value = '';
    }
</script>
</body>
</html>