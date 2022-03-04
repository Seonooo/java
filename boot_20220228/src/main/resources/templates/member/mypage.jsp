<!DOCTYPE html>
<html lang="ko" xmlns:th="http://www.thymeleaf.org">

<head th:replace="~{/member/header :: headerFragment}">

</head>

<body>

    <h3>마이페이지</h3>
    <hr>

    <a th:href="@{/member/mypage?menu=1}">정보수정</a>
    <a th:href="@{/member/mypage?menu=2}">암호변경</a>
    <a th:href="@{/member/mypage?menu=3}">회원탈퇴</a>
    <hr>
    <p th:text="${session.USERID}"></p>
    <div th:if="${param.menu.toString.equals('1')}">
        <form th:action="@{/member/mypage(menu=1)}" method="post">
            아이디 : <input type="text" readonly th:value="${mem.userid}" name="userid"><br>
            이름 : <input type="text" th:value="${mem.username}" name="username"><br>
            나이 : <input type="text" th:value="${mem.userage}" name="userage"><br>
            <input type="submit" value="정보수정">
        </form>
    </div>
    <div th:if="${param.menu.toString.equals('2')}">
        <form th:action="@{/member/mypage(menu=2)}" method="post">
            현재암호 : <input type="password" name="userpw"><br>
            새암호 : <input type="password" name="newPw"><br>
            새암호확인 : <input type="password"><br>
            <input type="submit" value="암호변경">
        </form>
    </div>
    <div th:if="${param.menu.toString.equals('3')}">
        <form th:action="@{/member/mypage(menu=3)}" method="post">
            현재비밀번호 : <input type="password" name="userpw"><br>
            <input type="submit" value="회원탈퇴">
        </form>
    </div>




    <div th:replace="~{/member/footer :: footerFragment}"></div>

</body>

</html>