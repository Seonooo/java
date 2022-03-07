<!DOCTYPE html>
<html lang="ko" xmlns:th="http://www.thymeleaf.org">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시물 수정</title>
    <link rel="stylesheet" type="text/css" th:href="@{/css/bootstrap.css}" />
    <script type="text/javascript" th:src="@{/js/bootstrap.min.js}"></script>
</head>

<body>
    <div style="padding: 20px;">
        <h3>게시물 수정</h3>
        <hr>
        <form th:action="@{/board/update}" method="post">
            제목 : <input type="text" minlength="1" th:value="${board.title}" name="title"><br />
            내용 : <input type="text" minlength="1" th:value="${board.content}" name="content"><br />
            작성자 : <input type="text" minlength="1" th:value="${board.writer}" name="writer"><br />
            <input type="submit" class="btn btn-primary" value="게시물등록">
        </form>
    </div>

</body>

</html>