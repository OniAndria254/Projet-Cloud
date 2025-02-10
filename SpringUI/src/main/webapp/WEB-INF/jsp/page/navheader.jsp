<%@ page import="itu.p16.crypto.entity.Users" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta content="width=device-width, initial-scale=1.0" name="viewport" />
    <title>Crypto Planet - Deposit/Withdraw</title>
    <!-- Bibliothèques et polices communes -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet" />
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&amp;display=swap" rel="stylesheet" />
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet" />
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <style>
      /* ----------------------- Styles communs ----------------------- */
      body {
        margin: 0;
        font-family: "Inter", sans-serif;
        background-color: #0d1117;
        color: #c9d1d9;
        transition: background-color 0.3s, color 0.3s;
      }
      .navbar {
        display: flex;
        justify-content: space-between;
        align-items: center;
        padding: 1rem 2rem;
        background-color: #161b22;
        transition: background-color 0.3s;
      }
      .navbar .nav-links {
        display: flex;
        align-items: center;
        gap: 1.5rem;
      }
      .navbar .nav-links a {
        color: #c9d1d9;
        text-decoration: none;
        font-size: 18px;
        display: flex;
        align-items: center;
        gap: 0.5rem;
      }
      .navbar .nav-links a:hover {
        color: #58a6ff;
      }
      .navbar .right-section {
        display: flex;
        align-items: center;
        gap: 1rem;
        position: relative;
      }
      .navbar .right-section .profile-dropdown {
        display: flex;
        align-items: center;
        cursor: pointer;
      }
      .navbar .right-section img {
        width: 40px;
        border-radius: 50%;
      }
      .dropdown-menu {
        display: none;
        position: absolute;
        top: 60px;
        right: 0;
        background-color: #21262d;
        border-radius: 5px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        z-index: 1000;
      }
      .dropdown-menu a {
        display: block;
        padding: 10px 20px;
        color: #c9d1d9;
        text-decoration: none;
      }
      .dropdown-menu a:hover {
        background-color: #161b22;
      }
      .theme-toggle {
        display: flex;
        align-items: center;
        cursor: pointer;
      }
      .theme-toggle input {
        display: none;
      }
      .theme-toggle .slider {
        width: 50px;
        height: 25px;
        background-color: #ccc;
        border-radius: 25px;
        position: relative;
        transition: background-color 0.3s;
      }
      .theme-toggle .slider:before {
        content: "";
        position: absolute;
        width: 20px;
        height: 20px;
        background-color: #fff;
        border-radius: 50%;
        top: 50%;
        left: 5px;
        transform: translateY(-50%);
        transition: transform 0.3s;
      }
      .theme-toggle input:checked + .slider {
        background-color: #007bff;
      }
      .theme-toggle input:checked + .slider:before {
        transform: translate(25px, -50%);
      }
      .theme-toggle .icon {
        font-size: 1.5rem;
        margin-left: 10px;
        color: #c9d1d9;
      }
      /* ----------------------- Thème clair ----------------------- */
      .light-theme {
        background-color: #f0f0f0;
        color: #000;
      }
      .light-theme .navbar {
        background-color: #e0e0e0;
      }
      .light-theme .navbar .nav-links a {
        color: #000;
      }
      .light-theme .navbar .nav-links a:hover {
        color: #007bff;
      }
      .light-theme .btn-outline-light {
        color: #000;
        border-color: #000;
      }
      .light-theme .btn-outline-light:hover {
        background-color: #000;
        color: #fff;
      }
      .container {
        padding: 2rem;
      }
      /* ----------------------- Styles spécifiques à cette page ----------------------- */
      .balance-card {
        background-color: #21262d;
        border-radius: 10px;
        padding: 20px;
        margin-bottom: 20px;
        text-align: center;
      }
      .balance-card h2 {
        margin: 0;
        font-size: 24px;
        color: #58a6ff;
      }
      .balance-card p {
        margin: 0;
        font-size: 16px;
      }
      .buy-sell {
        background-color: #161b22;
        border-radius: 10px;
        padding: 20px;
        margin: 20px 0;
        transition: background-color 0.3s;
      }
      .tabs {
        margin-bottom: 20px;
      }
      .tabs button {
        flex: 1;
        padding: 10px;
        background-color: #21262d;
        border: none;
        color: #c9d1d9;
        font-size: 16px;
        font-weight: 500;
        cursor: pointer;
        transition: background-color 0.3s;
      }
      .tabs button.active {
        background-color: #58a6ff;
      }
      .form-group {
        margin-bottom: 15px;
      }
      .alert {
        margin-bottom: 20px;
      }
      .coin-table {
        margin-top: 20px;
      }
      .table-header,
      .table-body {
        width: 100%;
      }
      .table-header {
        background-color: #21262d;
        padding: 10px;
        border-radius: 10px 10px 0 0;
      }
      .table-header div {
        text-align: center;
        font-weight: 500;
      }
      .table-body {
        background-color: #161b22;
        border-radius: 0 0 10px 10px;
      }
      .table-row {
        padding: 10px;
        border-bottom: 1px solid #21262d;
      }
      .table-row:last-child {
        border-bottom: none;
      }
      .table-row div {
        text-align: center;
      }
    </style>
  </head>
  <body>
    <!-- Barre de navigation commune -->
    <nav class="navbar">
      <div class="nav-links">
        <%
          Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
          if(isAdmin != null && isAdmin) {
        %>
        <a href="<%= request.getContextPath() %>/transaction/histotransaction">
          <i class="fas fa-history"></i>  Transaction list
        </a>
        <a href="<%= request.getContextPath() %>/graphic/graphe">
          <i class="fas fa-chart-line"></i> Market
        </a>
        <a href="<%= request.getContextPath() %>/analyse/transactions">
          <i class="fas fa-chart-line"></i> Transaction analysis
        </a>
        <a href="<%= request.getContextPath() %>/admin/transactions">
          <i class="fas fa-check-circle"></i> Validation
        </a>
        <a href="<%= request.getContextPath() %>/commission/modifier">
          <i class="as fa-percentage"></i> Commissions modifications
        </a>
        <a href="<%= request.getContextPath() %>/analyse/commissions">
          <i class="fas fa-percentage"></i> Commissions
        </a>
        <a href="<%= request.getContextPath() %>/transaction2/analysis">
          <i class="fas fa-table"></i> Table
        </a>
        <script>
          // Set light theme by default for admin
          document.body.classList.add("light-theme");
          var themeIcon = document.getElementById("themeIcon");
          themeIcon.classList.remove("fa-moon");
          themeIcon.classList.add("fa-sun");
          // Set checkbox to checked state
          document.getElementById("themeToggle").checked = true;
        </script>
        <%
          }
        %>
      </div>
      <div class="right-section">
        <%
        if (isAdmin != null && !isAdmin) {
        %>
        <a href="<%= request.getContextPath() %>/transaction/histotransaction">
          <i class="fas fa-history"></i>  Transaction list
        </a>
        <a href="<%= request.getContextPath() %>/graphic/graphe">
          <i class="fas fa-chart-line"></i> Market
        </a>
        <a href="<%= request.getContextPath() %>/transaction/buy-sell">
          <i class="fas fa-dollar-sign"></i> Transaction
        </a>
        <label class="theme-toggle">
          <input id="themeToggle" onclick="toggleTheme()" type="checkbox" />
          <span class="slider"></span>
          <i class="fas fa-moon icon" id="themeIcon"></i>
        </label>
        <a href="/transaction/depositWithdraw">
          <button class="btn btn-outline-light">
            <i class="fas fa-wallet"></i> Wallet
          </button>
        </a>
        <%
          }
        %>
        <%
          Object userObj = session.getAttribute("user");
          String userName = (userObj != null) ? ((Users) userObj).getUsername() : "Invité";
        %>
        <div class="profile-dropdown" id="profileDropdown">
          <img id="profileImage" src="<%= ((Users)userObj).getAvatar()%>" />
          <span id="profileName"><%= userName %></span>
          <div class="dropdown-menu" id="dropdownMenu">
            <a href="/auth/logout">Disconnect</a>
          </div>
        </div>
      </div>
    </nav>
    