<!DOCTYPE html>
<html lang="ko" xmlns:th="http://www.thymeleaf.org">
<head th:replace="~{/member/header :: headerFragment}"></head> 

<body>
    <div style="padding:20px">
        <h3>글수정</h3>
        <hr />
        <form th:action="@{/board/update}" method="post">
            <input type="hidden" name="no" th:value="${board.no}" />
            제목 : <input type="text" name="title" th:value="${board.title}" /><br />
            내용 : <input type="text" name="content" th:value="${board.content}" /><br />
            작성자 : <input type="text" name="writer" th:value="${board.writer}" /><br />
            <input type="submit" value="글수정" />
            <a th:href="@{/board/selectlist}">글목록</a>
        </form>
    </div>
</body>
</html>