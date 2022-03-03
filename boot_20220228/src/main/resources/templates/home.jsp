<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>HOME</title>
    <link rel="stylesheet" type="text/css" th:href="@{/css/bootstrap.css}" />
    <script type="text/javascript" th:src="@{/js/bootstrap.min.js}"></script>
</head>
<body>
    <div style="padding: 10px;">
        <h3>홈화면</h3>
        <hr>
        <div th:unless="${session.USERID}">
            <a th:href="@{/member/login}">로그인</a>
        </div>
        <div th:if="${session.USERID}">
            <a th:href="@{/member/logout}">로그아웃</a>
        </div>
        <div th:if="${session.USERID}">
            <a th:href="@{/member/mypage}">마이페이지</a>
        </div>
    </div>
</body>
</html>