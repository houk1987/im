<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2014/10/29
  Time: 9:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <script type="text/javascript">

        function validate() {
            var nick = document.getElementById("nick").value;
            if (nick.length ==0) {
                alert('昵称不能为空!');
                return false;
            }

            var password = document.getElementById("password").value;
            if (password.length ==0) {
                alert('密码不能为空!');
                return false;
            }

            var pass_again = document.getElementById("pass_again").value;
            if (pass_again.length ==0) {
                alert('确认密码不能为空!');
                return false;
            }
            return true;
        }

    </script>
</head>
<body>
<form action="index.jsp" onsubmit="return validate()">
    <input id="nick">
    <input id="password">
    <input id="pass_again">
    <input class="" type="submit" value="立即注册" title="立即注册" tabindex="21" id="submit">
</form>
</body>
</html>
