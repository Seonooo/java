<!DOCTYPE html>
<html lang="ko" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>물품등록</title>
    <link rel="stylesheet" type="text/css" th:href="@{/css/bootstrap.css}" />
    <script type="text/javascript" th:src="@{/js/bootstrap.min.js}"></script>  
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
    <div style="padding:20px;">
       <h3>물품등록</h3>
       <hr>
       <button type="button" onclick="location.href='/home'">메인</button>
        <button type="button" onclick="location.href='/item/insert'">물품등록</button>
        <button type="button" onclick="location.href='/item/select'">물품목록</button>
       <hr />

       <form th:action="@{/item/insert}" method="post">
           <div></div>
            물품명<br><input type="text" name="name" /><br />
            물품내용<br><input type="text" name="content" /><br />
            물품가격<br><input type="text" name="price" /><br />
            물품수량<br><input type="text" name="quantity" /><br />
            <input type="submit" value="등록" style="margin-top: 20px;" />
       </form>
    </div>
</body>
</html>