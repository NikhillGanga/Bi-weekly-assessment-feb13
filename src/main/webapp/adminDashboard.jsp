<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.training.web.entity.Policy" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
    <style>
        * { box-sizing: border-box; margin: 0; padding: 0; }
        body { font-family: Arial, sans-serif; background: #f5f6fa; }
        .top-nav { background: #c0392b; color: white; padding: 13px 30px;
                   display: flex; justify-content: space-between; align-items: center; }
        .top-nav span { font-size: 17px; font-weight: bold; }
        .top-nav a { color: white; text-decoration: none; font-size: 13px;
                     background: rgba(255,255,255,0.2); padding: 6px 14px;
                     border-radius: 5px; margin-left: 10px; }
        .top-nav a:hover { background: rgba(255,255,255,0.35); }
        .container { max-width: 950px; margin: 30px auto; padding: 0 20px; }
        .box { background: white; padding: 28px; border-radius: 10px;
               box-shadow: 0 3px 14px rgba(0,0,0,0.09); margin-bottom: 28px; }
        h2 { color: #2c3e50; margin-bottom: 20px; font-size: 17px; }
        .form-row { display: grid; grid-template-columns: 1fr 1fr; gap: 14px; }
        label { display: block; font-size: 13px; color: #555; margin-bottom: 4px; }
        input[type=text], input[type=number] {
            width: 100%; padding: 9px 11px; border: 1px solid #ddd;
            border-radius: 6px; font-size: 14px; margin-bottom: 14px; }
        input[type=submit] {
            padding: 10px 26px; background: #e74c3c; color: white;
            border: none; border-radius: 6px; font-size: 14px; cursor: pointer; }
        input[type=submit]:hover { background: #c0392b; }
        .msg-success { background: #eafaf1; color: #27ae60; padding: 10px 14px;
                       border-radius: 6px; margin-bottom: 16px; font-size: 13px; }
        table { width: 100%; border-collapse: collapse; }
        th { background: #c0392b; color: white; padding: 11px 14px; text-align: left; font-size: 14px; }
        td { padding: 10px 14px; border-bottom: 1px solid #eee; font-size: 14px; color: #333; }
        tr:hover td { background: #fef9f9; }
        .btn-delete { padding: 6px 14px; background: #e74c3c; color: white;
                      border: none; border-radius: 5px; cursor: pointer; font-size: 13px; }
        .btn-delete:hover { background: #c0392b; }
        .empty { color: #999; font-style: italic; text-align: center; padding: 16px; }
    </style>
</head>
<body>

<div class="top-nav">
    <span>&#128272; Admin Dashboard</span>
    <div>
        <a href="adminLogin.jsp">&#128274; Logout</a>
    </div>
</div>

<div class="container">

    <!-- Success message from redirect -->
    <% if (request.getParameter("msg") != null) { %>
        <div class="msg-success">&#10003; <%= request.getParameter("msg") %></div>
    <% } %>

    <!-- Add Policy Form -->
    <div class="box">
        <h2>&#10133; Add New Policy</h2>
        <form action="addPolicy" method="post">
            <div class="form-row">
                <div>
                    <label>Policy Name</label>
                    <input type="text" name="policyName" placeholder="e.g. Health Shield" required />
                </div>
                <div>
                    <label>Description</label>
                    <input type="text" name="description" placeholder="Brief description" />
                </div>
            </div>
            <div class="form-row">
                <div>
                    <label>Premium Amount (Rs.)</label>
                    <input type="number" name="premiumAmount" placeholder="e.g. 5000" step="0.01" required />
                </div>
                <div>
                    <label>Duration (Months)</label>
                    <input type="number" name="duration" placeholder="e.g. 12" required />
                </div>
            </div>
            <input type="submit" value="Add Policy" />
        </form>
    </div>

    <!-- Policy List with Delete -->
    <div class="box">
        <h2>&#128203; All Policies</h2>
        <table>
            <tr>
                <th>#</th>
                <th>Policy Name</th>
                <th>Description</th>
                <th>Premium (Rs.)</th>
                <th>Duration (Months)</th>
                <th>Action</th>
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
                <td>
                    <a href="deletePolicy?policyId=<%= p.getId() %>"
                       onclick="return confirm('Delete this policy?')">
                        <button type="button" class="btn-delete">&#128465; Delete</button>
                    </a>
                </td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr><td colspan="6" class="empty">No policies found. Add one above.</td></tr>
            <% } %>
        </table>
    </div>

</div>
</body>
</html>
