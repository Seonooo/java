<!DOCTYPE html>
<html lang="ko" xmlns:th="http://www.thymeleaf.org">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>일괄수정</title>
    <link rel="stylesheet" type="text/css" th:href="@{/css/bootstrap.css}" />
    <script type="text/javascript" th:src="@{/js/bootstrap.min.js}"></script>
</head>

<body>
    <div style="padding:20px">
        <h3>일괄수정</h3>
        <hr />
        <form th:action="@{/admin/updatebatch}" method="post">
            <table class="table">
                <tr>
                    <th>코드</th>
                    <th>책제목</th>
                    <th>가격</th>
                    <th>저자</th>
                    <th>분류</th>
                    <th>등록일</th>
                </tr>

                <tr th:each="tmp : ${list}">
                    <td><input type="text" name="code" th:value="${tmp.code}" readonly></td>
                    <td><input type="text" name="title" th:value="${tmp.title}"></td>
                    <td><input type="text" name="price" th:value="${tmp.price}"></td>
                    <td><input type="text" name="writer" th:value="${tmp.writer}"></td>
                    <td>
                        <select name="category">
                            <option th:selected="${tmp.category == 'A'}">A</option>
                            <option th:selected="${tmp.category == 'B'}">B</option>
                            <option th:selected="${tmp.category == 'C'}">C</option>
                        </select>
                    </td>
                    <td th:text="${tmp.regdate}"></td>
                </tr>
            </table>

            <input type="submit" class="btn btn-primary" value="일괄수정" />
        </form>
    </div>
</body>

</html>