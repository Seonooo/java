<!DOCTYPE html>
<html lang="ko" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원정보수정</title>
    <link rel="stylesheet" type="text/css" th:href="@{/css/bootstrap.css}" />
    <script type="text/javascript" th:src="@{/js/bootstrap.min.js}"></script>    
</head>

<body>
    <div style="padding: 20px;">
        <h3>회원정보수정</h3>
        <hr>
        <form th:action="@{/member/update}" method="post">
            아이디 : <input type="text" th:value="${member.userid}" readonly name="userid"><br>
            이름 : <input type="text" th:value="${member.username}" name="username"><br>
            나이 : <input type="text" th:value="${member.userage}" name="userage"><br>
            <input type="submit" value="수정하기">
        </form>
        
    </div>
    
</body>
</html>