<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.training.web.entity.Policy" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Policy</title>
    <style>
        body { font-family: Arial, sans-serif; background: #f0f4f8; margin: 0; }
        .top-nav { background: #2c3e50; color: white; padding: 12px 30px;
                   display: flex; justify-content: space-between; align-items: center; }
        .top-nav a { color: #ecf0f1; text-decoration: none; font-size: 14px; margin-left: 16px; }
        .top-nav a:hover { color: #e67e22; }
        .container { max-width: 860px; margin: 30px auto; padding: 0 20px; }
        .box { background: white; padding: 30px; border-radius: 10px;
               box-shadow: 0 4px 16px rgba(0,0,0,0.1); margin-bottom: 30px; }
        h2 { color: #2c3e50; margin-bottom: 20px; }
        label { display: block; font-size: 13px; color: #555; margin-bottom: 5px; }
        input[type=text], input[type=number] {
            width: 100%; padding: 9px 11px; margin-bottom: 16px;
            border: 1px solid #ccc; border-radius: 6px; font-size: 14px; box-sizing: border-box; }
        input[type=submit] {
            padding: 10px 26px; background: #e67e22; color: white;
            border: none; border-radius: 6px; font-size: 14px; cursor: pointer; }
        input[type=submit]:hover { background: #ca6f1e; }
        .msg { color: green; font-weight: bold; margin-bottom: 14px; }
        table { width: 100%; border-collapse: collapse; }
        th { background: #e67e22; color: white; padding: 11px 14px; text-align: left; }
        td { padding: 10px 14px; border-bottom: 1px solid #eee; font-size: 14px; }
        tr:hover td { background: #fdf3e7; }
    </style>
</head>
<body>

<div class="top-nav">
    <span>&#128737; Admin - Policy Management</span>
    <div><a href="home">Home</a></div>
</div>

<div class="container">

    <!-- Add Policy Form -->
    <div class="box">
        <h2>Add New Policy</h2>

        <% if (request.getAttribute("msg") != null) { %>
            <p class="msg">${msg}</p>
        <% } %>

        <form action="addPolicy" method="post">
            <label>Policy Name</label>
            <input type="text" name="policyName" placeholder="e.g. Health Shield" required />

            <label>Description</label>
            <input type="text" name="description" placeholder="Brief description" />

            <label>Premium Amount (Rs.)</label>
            <input type="number" name="premiumAmount" placeholder="e.g. 5000" step="0.01" required />

            <label>Duration (Months)</label>
            <input type="number" name="duration" placeholder="e.g. 12" required />

            <input type="submit" value="Add Policy" />
        </form>
    </div>

    <!-- View Policies Table -->
    <div class="box">
        <h2>All Policies</h2>
        <table>
            <tr>
                <th>#</th>
                <th>Policy Name</th>
                <th>Description</th>
                <th>Premium (Rs.)</th>
                <th>Duration (Months)</th>
            </tr>
            <%
                List<Policy> policyList = (List<Policy>) request.getAttribute("policyList");
                if (policyList != null && !policyList.isEmpty()) {
                    int i = 1;
                    for (Policy p : policyList) {
            %>
            <tr>
                <td><%= i++ %></td>
                <td><%= p.getPolicyName() %></td>
                <td><%= p.getDescription() %></td>
                <td>Rs. <%= p.getPremiumAmount() %></td>
                <td><%= p.getDuration() %></td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr><td colspan="5" style="text-align:center;color:#888;">No policies yet.</td></tr>
            <% } %>
        </table>
    </div>
</div>

</body>
</html>
