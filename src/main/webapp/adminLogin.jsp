<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Login</title>
    <style>
        * { box-sizing: border-box; margin: 0; padding: 0; }
        body { font-family: Arial, sans-serif; background: #fdecea;
               display: flex; justify-content: center; align-items: center; height: 100vh; }
        .box { background: white; padding: 40px; border-radius: 12px;
               box-shadow: 0 6px 24px rgba(0,0,0,0.12); width: 360px; }
        h2 { text-align: center; color: #c0392b; margin-bottom: 24px; }
        label { display: block; font-size: 13px; color: #555; margin-bottom: 5px; }
        input[type=text], input[type=password] {
            width: 100%; padding: 9px 12px; margin-bottom: 16px;
            border: 1px solid #ccc; border-radius: 6px; font-size: 14px; }
        input[type=submit] {
            width: 100%; padding: 11px; background: #e74c3c; color: white;
            border: none; border-radius: 6px; font-size: 15px; cursor: pointer; }
        input[type=submit]:hover { background: #c0392b; }
        .msg  { color: red; font-size: 13px; margin-bottom: 14px; text-align: center; }
        .back { text-align: center; margin-top: 14px; font-size: 13px; }
        .back a { color: #e74c3c; text-decoration: none; }
    </style>
</head>
<body>
<div class="box">
    <h2>&#128272; Admin Login</h2>

    <% if (request.getAttribute("msg") != null) { %>
        <p class="msg">${msg}</p>
    <% } %>

    <form action="adminLogin" method="post">
        <label>Admin Username</label>
        <input type="text" name="username" placeholder="Enter admin username" required />

        <label>Password</label>
        <input type="password" name="password" placeholder="Enter password" required />

        <input type="submit" value="Login as Admin" />
    </form>

    <div class="back"><a href="index.jsp">&#8592; Back to Home</a></div>
</div>
</body>
</html>
