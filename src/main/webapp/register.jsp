<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Customer Registration</title>
    <style>
        * { box-sizing: border-box; margin: 0; padding: 0; }
        body { font-family: Arial, sans-serif; background: #eef2f7;
               display: flex; justify-content: center; align-items: center; min-height: 100vh; }
        .box { background: white; padding: 40px; border-radius: 12px;
               box-shadow: 0 6px 24px rgba(0,0,0,0.12); width: 400px; }
        h2 { text-align: center; color: #27ae60; margin-bottom: 24px; }
        label { display: block; font-size: 13px; color: #555; margin-bottom: 5px; }
        input[type=text], input[type=email], input[type=password] {
            width: 100%; padding: 9px 12px; margin-bottom: 16px;
            border: 1px solid #ccc; border-radius: 6px; font-size: 14px; }
        input[type=submit] {
            width: 100%; padding: 11px; background: #2ecc71; color: white;
            border: none; border-radius: 6px; font-size: 15px; cursor: pointer; }
        input[type=submit]:hover { background: #27ae60; }
        .links { text-align: center; margin-top: 14px; font-size: 13px; color: #777; }
        .links a { color: #27ae60; text-decoration: none; }
    </style>
</head>
<body>
<div class="box">
    <h2>&#128221; Customer Registration</h2>

    <form action="register" method="post">
        <label>Full Name</label>
        <input type="text" name="name" placeholder="Enter full name" required />

        <label>Email</label>
        <input type="email" name="email" placeholder="Enter email" required />

        <label>Phone</label>
        <input type="text" name="phone" placeholder="Enter phone number" required />

        <label>Address</label>
        <input type="text" name="address" placeholder="Enter address" />

        <label>Username</label>
        <input type="text" name="username" placeholder="Choose a username" required />

        <label>Password</label>
        <input type="password" name="password" placeholder="Choose a password" required />

        <input type="submit" value="Register" />
    </form>

    <div class="links">
        Already registered? <a href="login.jsp">Login here</a>
    </div>
</div>
</body>
</html>
