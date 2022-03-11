<!DOCTYPE html>
<html lang="ko" xmlns:th="http://www.thymeleaf.org">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <script th:inline="javascript">
            /*<![CDATA[*/
            const msg = [[${msg}]];
            alert(msg); 
            window.location.replace( [[${url}]] );
            /*]]*/ 
        </script>
    </head>
</html>