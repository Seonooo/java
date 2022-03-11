<!DOCTYPE html>
<html lang="ko" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" th:href="@{/css/bootstrap.css}" />
    <script type="text/javascript" th:src="@{/js/bootstrap.min.js}"></script>
    <title>메인</title>
    <style>
        button{
            transition-duration: 0.3ms;
            padding: 4px;
            background-color: white;
            border: none;
            margin-left: 10px;
        }
        button:hover{
            background-color: rgb(230, 228, 228);
        }
    </style> 
</head>
<body>
    <div style="padding: 20px;">
        <button type="button" onclick="location.href='/home'">메인</button>
        <button type="button" onclick="location.href='/item/insert'">물품등록</button>
        <button type="button" onclick="location.href='/item/select'">물품목록</button>
        <hr>
        <p>메인</p>
    </div>
    
</body>
</html>