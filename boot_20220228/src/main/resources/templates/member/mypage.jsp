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
    <div th:if="${param.menu.toString.equals('1')}">
        정보수정
    </div>
    <div th:if="${param.menu.toString.equals('2')}">
        암호변경
    </div>
    <div th:if="${param.menu.toString.equals('3')}">
        회원탈퇴
    </div>


    
    
    <div th:replace="~{/member/footer :: footerFragment}"></div>

</body>
</html>