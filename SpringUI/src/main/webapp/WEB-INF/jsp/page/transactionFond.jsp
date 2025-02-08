<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="itu.p16.crypto.entity.TransactionFonds" %>
<%@ page import="itu.p16.crypto.entity.Users" %>
<%
    // Récupération de la liste des transactions (demandes) depuis l'attribut "demandes"
    List<TransactionFonds> tfs = (List<TransactionFonds>) request.getAttribute("demandes");
%>
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
       <a href="<%= request.getContextPath() %>/transaction/buy-sell">
        <i class="fas fa-history"></i> Acceuil
        </a>
       <a href="<%= request.getContextPath() %>/graphic/graphe">
        <i class="fas fa-chart-line"></i> Market
        </a>
        <%
            Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
            if(isAdmin != null && isAdmin) {
        %>
            <a href="<%= request.getContextPath() %>/admin/transactions">
                <i class="fas fa-check-circle"></i> Validation
            </a>
            <a href="<%= request.getContextPath() %>/analyse/commissions">
                <i class="fas fa-chart-percentages"></i> commissions
            </a>
            <a href="<%= request.getContextPath() %>/commission/modifier">
                <i class="fas fa-chart-percentages"></i> modifications commissions
            </a>
            <a href="<%= request.getContextPath() %>/analyse/transactions">
                <i class="fas fa-chart-line"></i> analyse transaction
            </a>
        <%
            }
        %>
        <a href="<%= request.getContextPath() %>/transaction/histotransaction">
        <i class="fas fa-history"></i> Trade History
        </a>
      </div>
      <div class="right-section">
        <label class="theme-toggle">
          <input id="themeToggle" onclick="toggleTheme()" type="checkbox" />
          <span class="slider"></span>
          <i class="fas fa-moon icon" id="themeIcon"></i>
        </label>
        <a href="<%= request.getContextPath() %>/transaction/depositWithdraw">
          <button class="btn btn-outline-light">
            <i class="fas fa-wallet"></i> Wallet
          </button>
        </a>
        <%
          Object userObj = session.getAttribute("user");
          String userName = (userObj != null) ? ((Users) userObj).getUsername() : "Invité";
        %>


        <div class="profile-dropdown" id="profileDropdown">
          <img alt="User Profile Picture" id="profileImage" src="/assets/img/profil.png" />
          <span id="profileName"><%= userName %></span>
          <div class="dropdown-menu" id="dropdownMenu">
            <a href="/auth/logout">Disconnect</a>
          </div>
        </div>
      </div>
    </nav>
    
    <div class="container">
      <!-- Affichage des messages de succès/erreur -->
      <% if (request.getAttribute("error") != null) { %>
        <div class="alert alert-danger" role="alert">
          <%= request.getAttribute("error") %>
        </div>
      <% } %>
      <% if (request.getAttribute("success") != null) { %>
        <div class="alert alert-success" role="alert">
          <%= request.getAttribute("success") %>
        </div>
      <% } %>
      
      <!-- Bloc affichant le solde actuel -->
      <div class="balance-card">
        <%-- Le solde est transmis dans l'attribut "balance" par le contrôleur --%>
        <h2>$<%= request.getAttribute("balance") != null ? request.getAttribute("balance") : "0.00" %></h2>
        <p>Current Balance</p>
      </div>
      
      <!-- Onglets pour Deposit et Withdraw -->
      <div class="buy-sell">
        <div class="tabs d-flex">
          <button class="active" onclick="showForm('deposit')">Deposit</button>
          <button onclick="showForm('withdraw')">Withdraw</button>
        </div>
        <!-- Formulaire Deposit -->
        <div id="deposit-form">
          <form action="<%= request.getContextPath() %>/transaction/deposit" method="post">
            <div class="form-group">
              <label for="amount-deposit">Amount (USD)</label>
              <input class="form-control" id="amount-deposit" name="amount" placeholder="Enter amount" type="number" step="0.01" required/>
            </div>
            <div class="form-group">
              <!-- Bouton de confirmation pour dépôt en bleu-roi (ici, vous pouvez choisir btn-primary) -->
              <button class="btn btn-primary btn-block" type="submit">Deposit Funds</button>
            </div>
          </form>
        </div>
        <!-- Formulaire Withdraw -->
        <div id="withdraw-form" style="display: none;">
          <form action="<%= request.getContextPath() %>/transaction/withdraw" method="post">
            <div class="form-group">
              <label for="amount-withdraw">Amount (USD)</label>
              <input class="form-control" id="amount-withdraw" name="amount" placeholder="Enter amount" type="number" step="0.01" required/>
            </div>
            <div class="form-group">
              <!-- Bouton de confirmation pour retrait en rouge -->
              <button class="btn btn-danger btn-block" type="submit">Withdraw Funds</button>
            </div>
          </form>
        </div>
      </div>
      
      <!-- Tableau des transactions (affichage des demandes de dépôt/retrait de l'utilisateur) -->
      <div class="coin-table">
        <div class="table-header d-flex">
          <div class="col">#</div>
          <div class="col">Operation</div>
          <div class="col">Amount (USD)</div>
          <div class="col">Date</div>
          <div class="col">Status</div>
        </div>
        <div class="table-body">
          <%
            if (tfs != null) {
              for (TransactionFonds tf : tfs) {
                  int status = tf.getIdStatut();
                  String status2 = "";
                  String statusColor="";
                  if(status==1) {
                      statusColor = "#ffc107";
                      status2="En attente";
                  } else if(status==2) {
                      statusColor = "#28a745";
                      status2="Valider";
                  } else {
                      statusColor = "#dc3545";
                      status2="Refuser";
                  }
                  int type=tf.getIdTypeTransaction();
                  String rep="";
                  if(type==1){
                    rep="Depot";
                  } else if (type==2) {
                    rep="Retrait";
                  }
          %>
          <div class="table-row d-flex">
            <div class="col"><%= tf.getIdTransactionFonds() %></div>
            <div class="col"><%= rep %></div>
            <div class="col">$<%= tf.getMontant() %></div>
            <div class="col"><%= tf.getDateTransaction() %></div>
            <div class="col" style="color: <%= statusColor %>;"><%= status2 %></div>
          </div>
          <%
              }
            }
          %>
        </div>
      </div>
    </div>
    
    <!-- Scripts JavaScript -->
    <script>
      function toggleTheme() {
        document.body.classList.toggle("light-theme");
        var themeIcon = document.getElementById("themeIcon");
        if (document.body.classList.contains("light-theme")) {
          themeIcon.classList.remove("fa-moon");
          themeIcon.classList.add("fa-sun");
        } else {
          themeIcon.classList.remove("fa-sun");
          themeIcon.classList.add("fa-moon");
        }
      }
      
      var profileDropdown = document.getElementById("profileDropdown");
      profileDropdown.addEventListener("click", function () {
        var dropdownMenu = document.getElementById("dropdownMenu");
        dropdownMenu.style.display = dropdownMenu.style.display === "block" ? "none" : "block";
      });
      document.addEventListener("click", function (event) {
        var dropdownMenu = document.getElementById("dropdownMenu");
        var profileDropdown = document.getElementById("profileDropdown");
        if (!profileDropdown.contains(event.target) && !dropdownMenu.contains(event.target)) {
          dropdownMenu.style.display = "none";
        }
      });
      
      function showForm(type) {
        if (type === "deposit") {
          document.getElementById("deposit-form").style.display = "block";
          document.getElementById("withdraw-form").style.display = "none";
          document.querySelector(".tabs button:nth-child(1)").classList.add("active");
          document.querySelector(".tabs button:nth-child(2)").classList.remove("active");
        } else {
          document.getElementById("deposit-form").style.display = "none";
          document.getElementById("withdraw-form").style.display = "block";
          document.querySelector(".tabs button:nth-child(1)").classList.remove("active");
          document.querySelector(".tabs button:nth-child(2)").classList.add("active");
        }
      }
    </script>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  </body>
</html>
