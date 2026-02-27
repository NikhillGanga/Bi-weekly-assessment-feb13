<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Customer Login</title>
    <style>
        * { box-sizing: border-box; margin: 0; padding: 0; }
        body { font-family: Arial, sans-serif; background: #eef2f7;
               display: flex; justify-content: center; align-items: center; height: 100vh; }
        .box { background: white; padding: 40px; border-radius: 12px;
               box-shadow: 0 6px 24px rgba(0,0,0,0.12); width: 360px; }
        h2 { text-align: center; color: #2980b9; margin-bottom: 24px; }
        label { display: block; font-size: 13px; color: #555; margin-bottom: 5px; }
        input[type=text], input[type=password] {
            width: 100%; padding: 9px 12px; margin-bottom: 16px;
            border: 1px solid #ccc; border-radius: 6px; font-size: 14px; }
        input[type=submit] {
            width: 100%; padding: 11px; background: #3498db; color: white;
            border: none; border-radius: 6px; font-size: 15px; cursor: pointer; }
        input[type=submit]:hover { background: #2980b9; }
        .msg  { color: red; font-size: 13px; margin-bottom: 14px; text-align: center; }
        .success { color: green; font-size: 13px; margin-bottom: 14px; text-align: center; }
        .links { text-align: center; margin-top: 14px; font-size: 13px; color: #777; }
        .links a { color: #3498db; text-decoration: none; }
    </style>
</head>
<body>
<div class="box">
    <h2>&#128100; Customer Login</h2>

    <% if (request.getAttribute("msg") != null) { %>
        <% String m = (String) request.getAttribute("msg"); %>
        <p class="<%= m.contains("Successful") ? "success" : "msg" %>">${msg}</p>
    <% } %>

    <form action="login" method="post">
        <label>Username</label>
        <input type="text" name="username" placeholder="Enter username" required />

        <label>Password</label>
        <input type="password" name="password" placeholder="Enter password" required />

        <input type="submit" value="Login" />
    </form>

    <div class="links">
        New user? <a href="register.jsp">Register here</a> &nbsp;|&nbsp;
        <a href="index.jsp">&#8592; Back</a>
    </div>
</div>
</body>
</html>
