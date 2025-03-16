<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
 <head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <title>Текущий матч</title>
  <link rel ="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/match-score.css">
 </head>
 <body>
  <h1>Табло</h1>
  <div class="container">
    <div class="tableau">
      <div class="inline">
          <label>Сет 1</label>
          <label class="panel">${requestScope.set1ScorePlayer1}</label>
          <label class="panel">${requestScope.set1ScorePlayer2}</label>
      </div>
      <div class="inline">
          <label>Сет 2</label>
          <label class="panel">${requestScope.set2ScorePlayer1}</label>
          <label class="panel">${requestScope.set2ScorePlayer2}</label>
      </div>
      <div class="inline">
        <label>Сет 3</label>
        <label class="panel">${requestScope.set3ScorePlayer1}</label>
        <label class="panel">${requestScope.set3ScorePlayer2}</label>
      </div>
      <div class="inline">
        <label>Гейм</label>
        <label class="panel">${requestScope.pointsScorePlayer1}</label>
        <label class="panel">${requestScope.pointsScorePlayer2}</label>
      </div>
      <div class="inline">
        <label>Участники</label>
        <label class="panel">${requestScope.player1}</label>
        <label class="panel">${requestScope.player2}</label>
      </div>
    </div>
  <c:choose>
      <c:when test="${requestScope.winnerName eq '0'}">
          <div>
              <form method="post" action="${pageContext.request.contextPath}/match-score?uuid=${requestScope.uuid}">
                  <button type="submit" name="winner" value="player1">Первый игрок выиграл очко</button>
                  <button type="submit" name="winner" value="player2">Второй игрок выиграл очко</button>
              </form>
          </div>
      </div>
          <img src="${pageContext.request.contextPath}/images/referee.png">
      </c:when>
      <c:otherwise>
          <div>
              <label>Вернуться на начальную странцу?
                  <form action=home-page>
                      <button>Вперед!</button>
                  </form>
              </label>
          </div>
      </div>
          <h1>Поздравляем ${requestScope.winnerName} с победой! </h1>
          <img src="${pageContext.request.contextPath}/images/win%20match.png">
      </c:otherwise>
  </c:choose>
 </body>
</html>