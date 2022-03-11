<!DOCTYPE html>
<html lang="ko" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>물품목록</title>
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
    <div style="padding:20px">
        <h3>물품목록</h3>
        <hr />

        <button type="button" onclick="location.href='/home'">메인</button>
        <button type="button" onclick="location.href='/item/insert'">물품등록</button>
        <button type="button" onclick="location.href='/item/select'">물품목록</button>

        <hr />

            <table class="table table-sm">
                <tr>
                    <th>코드</th>
                    <th>물품명</th>
                    <th>물품내용</th>
                    <th>가격</th>
                    <th>수량</th>
                    <th>등록일</th>
                    <th>버튼</th>
                </tr>
                <tr th:each="tmp : ${list}">
                    <form th:action="@{/item/action}" method="post">
                        <input type="hidden" name="code" th:value="${tmp.code}">
                        <td th:text="${tmp.code}"></td>
                        <td th:text="${tmp.name}"></td>
                        <td><input type="text" name="content" th:value="${tmp.content}" /></td>
                        <td><input type="text" name="price" th:value="${tmp.price}" /></td>
                        <td><input type="text" name="quantity" th:value="${tmp.quantity}"></td>
                        <td th:text="${tmp.regdate}"></td>
                        <td>
                            <input type="submit" name="btn" value="1개수정" />
                            <input type="submit" name="btn" value="1개삭제" />
                        </td>
                    </form>
                </tr>
            </table>
        
        
        <th:block th:each="i : ${#numbers.sequence(1,pages)}">
            <a th:href="@{/item/select(page=${i})}" 
                th:text="${i}"></a>
        </th:block>

    </div>
</body>
</html>