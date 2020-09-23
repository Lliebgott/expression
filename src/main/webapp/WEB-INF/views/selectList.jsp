<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/9/23
  Time: 16:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="../../static/js/jquery-3.3.1.min.js"></script>
    <script>
        function check() {
            $.ajax({
                type: 'get',
                url: 'select',
                data : {

                },
                dataType: 'json',
                success: function (data) {
                    var html = "";
                    html += "<table>";
                    $.each(data, function (index, item) {
                        html += "<tr><td>姓名:</td><td>" + item.name + "</td></tr>";
                        html += "<tr><td>年龄:</td><td>" + item.age + "</td></tr>";
                    })
                    html += "</table>";
                    $('#div_T').append(html);
                },
                error: function () {

                }
            })
        }
    </script>
</head>
<body>
    <div id="div_T">
        <button onclick="check()">查询</button>
    </div>
</body>
</html>