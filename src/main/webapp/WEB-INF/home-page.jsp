<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=0.8">
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <title>Бу! Не бойся. Давай сыграем!</title>
  <link rel ="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/home-page.css">
</head>
<body>
<h1>Симулятор табло теннисного матча</h1>
<div class="container">
  <label>Просмотр сыгранных матчей:
    <form action=${requestScope.matches_url}>
      <button>Результаты</button>
    </form>
  </label>
  <label>Сыграть новый матч:
    <form action=${requestScope.new_match_url}>
      <button>Играем!</button>
    </form>
  </label>
</div>
<img alt="tennis player" src="${pageContext.request.contextPath}/images/tennis player.png">
</body>
</html>