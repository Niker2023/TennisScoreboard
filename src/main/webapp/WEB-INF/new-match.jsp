<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
 <head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <title>Создание нового матча</title>
  <link rel ="stylesheet" type="text/css" href="css/new-match.css">
 </head>
 <body>
  <h1>Создание нового матча</h1>
  <form class="container" method="post" action="/match-score">
    <label for="player1">Введите имя первого игрока: 
      <input id="player1" name="player1" type="text" required size="20"></label>
    <label for="player2">Введите имя второго игрока: 
      <input id="player2" name="player2" type="text" required size="20"></label>
     <button>Создать игру</button>
  </form>
  <img alt="referee" src="/images/referee.png">
 </body>
</html>
