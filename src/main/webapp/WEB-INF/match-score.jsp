<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
 <head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <title>Текущий матч</title>
  <link rel ="stylesheet" type="text/css" href="css/match-score.css">
 </head>
 <body>
  <h1>Табло</h1>
  <form class="container" method="post" action="/match-score">
    <div class="tableau">
      <div class="inline">
          <label>Сет 1</label>
          <label class="panel">0</label>
          <label class="panel">0</label>
      </div>
      <div class="inline">
          <label>Сет 2</label>
          <label class="panel">0</label>
          <label class="panel">0</label>
      </div>
      <div class="inline">
        <label>Сет 3</label>
        <label class="panel">0</label>
        <label class="panel">0</label>
      </div>
      <div class="inline">
        <label>Гейм</label>
        <label class="panel">0</label>
        <label class="panel">0</label>
      </div>
      <div class="inline">
        <label>Участники</label>
        <label class="panel">${requestScope.player1}</label>
        <label class="panel">${requestScope.player2}</label>
      </div>
    </div>
    <div>
      <button type="submit" name="player1win" value="win">Первый игрок выиграл очко</button>
      <button type="submit" name="player2win" value="win">Второй игрок выиграл очко</button>
    </div>  
  </form>
  <img src="/images/referee.png">
 </body>
</html>