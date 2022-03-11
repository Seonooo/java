<!DOCTYPE html>
<html lang="ko" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시판조회</title>
    <link rel="stylesheet" type="text/css" th:href="@{/css/bootstrap.css}" />
    <script type="text/javascript" th:src="@{/js/bootstrap.min.js}"></script>    
</head>

<body>
    <div style="padding:20px">
        <h3>게시판조회</h3>
        
        <hr />
        <h5>1. 정확하게 일치하는 항목 가져오기</h5>
        <form th:action="@{/book/find}" method="get">
            <select name="type">
                <option value="title">제목</option>
                <option value="writer">작성자</option>
                <option value="hit">조회수</option>
            </select>
            <input type="text" name="text" placeholder="검색어" />
            <input type="submit" value="검색" />
        </form>

        <hr />
        <h5>2. 조회수가 이상, 미만 조회하기 </h5>
        <form th:action="@{/book/find}" method="get">
            <select name="type1">
                <option value="1">이상</option>
                <option value="2">미만</option>
            </select>
            <input type="text" name="hit" placeholder="검색어" />
            <input type="submit" value="검색" />
        </form>
        
        <hr />
        <h5>3. 포함, 포함하지 않음 </h5>
        <form th:action="@{/book/find}" method="get">
            <select name="type2">
                <option value="1">포함</option>
                <option value="2">포함하지않음</option>
            </select>
            <input type="text" name="no" placeholder="글번호1" />
            <input type="text" name="no" placeholder="글번호2" />
            <input type="text" name="no" placeholder="글번호3" />
            <input type="submit" value="검색" />
        </form>
        
        <hr />
        <table class="table table-sm">
            <tr>
                <th>번호</th>
                <th>제목</th>
                <th>가격</th>
                <th>작성자</th>
                <th>분류</th>
                <th>날짜</th>
            </tr>
            <tr th:each="tmp, idx : ${list}">
                <td th:text="${tmp.code}"></td>
                <td th:text="${tmp.title}"></td>
                <td th:text="${tmp.price}"></td>
                <td th:text="${tmp.writer}"></td>
                <td th:text="${tmp.category}"></td>
                <td th:text="${tmp.regdate}"></td>
            </tr>
        </table>
    </div>
</body>
</html>