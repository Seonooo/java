<!DOCTYPE html>
<html lang="ko" xmlns:th="http://www.thymeleaf.org">
<head th:replace="~{/member/header :: headerFragment}">

</head>
<body>

    <h3>로그인</h3>
    <hr>
    <form th:action="@{/member/login}" method="post">
        아이디 : <input type="text" name="userid"><br/>
        암호 : <input type="password" name="userpw"><br/>
        <input type="submit" class="btn btn-primary"
            value="로그인">
    </form>
    <div th:replace="~{/member/footer :: footerFragment}"></div>

</body>
</html>