<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            Поиск: <input 
                type="text"
                name="q"
                placeholder="Введите имя игрока"
                required
            >
            <button type="submit">Найти</button>
        </form>
    </div>
    <div class="tableau">
        <ol>
            <li>Трамп - <span class="winner">Путин</span></li>
            <li>Жуковский - Пауковский</li>
        </ol> 
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