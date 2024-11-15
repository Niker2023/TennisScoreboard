<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
 <head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <title>Создание нового матча</title>
  <link rel="stylesheet" type="text/css" href="css/new-match.css">
 </head>
 <body>
  <h1>Создание нового матча</h1>
  <c:if test="${requestScope.error}">
      <p class="error">${requestScope.error_message}</p>
  </c:if>
  <form class="container" method="post" action="/new-match">
    <label for="player1">Введите имя первого игрока: 
      <input id="player1" name="player1" type="text" required size="20"></label>
    <label for="player2">Введите имя второго игрока: 
      <input id="player2" name="player2" type="text" required size="20"></label>
     <button>Создать игру</button>
  </form>
  <img alt="referee" src="/images/referee.png">
 </body>
</html>
