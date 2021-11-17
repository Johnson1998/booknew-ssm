<%--
  Created by IntelliJ IDEA.
  User: John
  Date: 2021/10/25
  Time: 17:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>智慧书城会员注册页面</title>
    <%@include file="/WEB-INF/jsp/common/head.jsp"%>

    <script>
        var InterValObj; //timer变量做时间控制
        var count = 60; // 间隔函数
        var curCount; //当前剩余时间
        $(function () {
            $("#sendCode").click(function () {
                if ($("#email").val()){
                    curCount = count;
                    $("#sendCode").attr("disabled", "true");
                    $("#sendCode").val(curCount+"秒重新发送");
                    InterValObj = window.setInterval(setRemainTime, 1000);
                    $.post("user/sendEmailCode", {email:""+$("#email").val()},
                        function (data) {
                            alert("发送成功");
                        } )
                }else {
                    alert("邮箱号不可为空");
                }
            });
            function setRemainTime(){
                if (curCount == 0){
                    window.clearInterval(InterValObj);
                    $("#sendCode").removeAttr("disabled")
                    $("#sendCode").val("重新发送验证码");
                }else{
                    curCount--;
                    $("#sendCode").val(curCount+"秒重新发送");
                }
            }
        });

        $(function () {
            $("#username").blur(function () {
                var username = this.value;
                $.getJSON("${basePath}userServlet", "action=ajaxExistsUsername&username=" +
                    username,
                    function
                        (data) {
                        if (data.existsUsername){
                            $("span.errorMsg").text("用户名已存在!");
                        } else {
                            $("span.errorMsg").text("用户名可用!");
                        }
                    })
            });
        });
        // 页面加载完成后
        $(function () {
            $("#code_img").click(function () {
                this.src = "${basePath}kaptcha.jpg?d=" + new Date();
            });
        });
        $(function () {
            // 给注册按钮添加单机事件
            $("#sub_btn").click(function () {
                // 验证用户名：必须由字母，数字下划线组成，并且长度为5到12位
                // 1.获取用户名输入框里的内容
                var usernameText = $("#username").val();

                // * 2.创建正则表达式对象
                var usernamePatt = /^\w{5,12}$/;


                // * 3.使用test方法验证
                if (!usernamePatt.test(usernameText)) {

                    // * 4.提示用户结果
                    $("span.errorMsg").text("用户名不合法！");
                    return false;
                }




                // 验证密码：必须由字母，数字下划线组成，并且长度为5到12位
                var passwordText = $("#password").val();
                var passwordPatt = /^\w{5,12}$/;
                if (!passwordPatt.test(passwordText)){
                    $("span.errorMsg").text("密码不合法");
                    return false;
                }

                // 验证确认密码：和密码相同
                // 1.获取确认密码内容
                var repwdText = $("#repwd").val();

                // 2.和密码相比较
                if (repwdText != passwordText) {

                    // 3.提示用户
                    $("span.errorMsg").text("确认密码和密码不一致");
                    return false;
                }

                // 邮箱验证：xxxxx@xxx.com
                // 1.获取邮箱里的内容
                var emailText = $("#email").val();
                // 2.创建正则表达式对象
                let emailPatt = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;

                // 3.使用test方法验证是否合法
                if (!emailPatt.test(emailText)){
                    $("span.errorMsg").text("邮箱不合法");
                    return false;
                }
                // 4.提示用户
                // 验证码：现在只需要验证用户已输入。因为还没讲到服务器。验证码生成。
                let codeText = $("#code").val();
                // 去掉验证码前后空格
                codeText = $.trim(codeText);
                if (codeText == null || codeText == ""){
                    $("span.errorMsg").text("验证码不合法");
                    return false;
                }

                $("span.errorMsg").text("");
            });
        });
    </script>

    <style type="text/css">
        .login_form{
            height:480px;
            margin-top: 25px;
        }
        #sendCode{
            font-size: 15px;
        }

    </style>
</head>
<body>
<div id="login_header">
    <img class="logo_img" alt="" src="static/img/logo.gif" >
</div>

<div class="login_banner">

    <div id="l_content">
        <span class="login_word">欢迎注册</span>
    </div>
    <div id="content">
        <div class="login_form">
            <div class="login_box">
                <div class="tit">
                    <h1>注册智慧书城会员</h1>
                    <span class="errorMsg">
<%--									<%=request.getAttribute("msg")==null?"":request.getAttribute("msg")%>--%>
										${ msg }
								</span>
                </div>
                <div class="form">
                    <form action="${pageContext.request.contextPath}/user/register" method="post">
                        <div>
                            <label>用户名称：</label>
                            <input class="itxt" type="text" placeholder="请输入用户名"
                                   autocomplete="off" tabindex="1" name="username" id="username"
                                   value="${ username }"
                            />
                        </div>

                        <div>
                            <label>用户密码：</label>
                            <input class="itxt" type="password" placeholder="请输入密码"
                                   autocomplete="off" tabindex="1" name="password" id="password" />
                        </div>

                        <div>
                            <label>确认密码：</label>
                            <input class="itxt" type="password" placeholder="确认密码"
                                   autocomplete="off" tabindex="1" name="repwd" id="repwd" />
                        </div>

                        <div>
                            <label>电子邮件：</label>
                            <input class="itxt" type="text" placeholder="请输入邮箱地址"
                                   autocomplete="off" tabindex="1" name="email" id="email"
                                   value="${ email }"
                            />

                        </div>

                        <div>
                            <label>邮箱验证：</label>
                            <input class="itxt" type="text" style="width: 100px;" name="emailCode" id="emailCode"
                            />
                            <input type="button" id="sendCode" value="发送验证码" style="
										margin-left: 10px; margin-top: 10px">
                        </div>

                        <div>
                            <label>&nbsp;&nbsp;&nbsp;验证码：</label>
                            <input class="itxt" type="text" style="width: 100px;" id="code" name="code" />
                            <img id="code_img" alt="" src="kaptcha.jpg" style="float: right; margin-right: 40px; width:
									110px;height: 35px;" id="code_img">
                        </div>
                        <br />
                        <input type="submit" value="注册" id="sub_btn" />

                    </form>
                </div>

            </div>
        </div>
    </div>
</div>

    <%@include file="/WEB-INF/jsp/common/footer.jsp"%>
</body>
</html>
