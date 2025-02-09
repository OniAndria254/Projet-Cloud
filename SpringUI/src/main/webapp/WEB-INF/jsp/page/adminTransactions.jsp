<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="itu.p16.crypto.entity.*" %>
<%
    List<TransactionFonds> demandes = (List<TransactionFonds>) request.getAttribute("demandes");
%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta content="width=device-width, initial-scale=1.0" name="viewport" />
    <title>Admin - Validation des transactions</title>
    <!-- Bibliothèques et polices communes -->
    <link
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
      rel="stylesheet"
    />
    <link
      href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&amp;display=swap"
      rel="stylesheet"
    />
    <link
      href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
      rel="stylesheet"
    />
    <style>
      /* Styles communs */
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
          flex-wrap: nowrap;  /* Empêche le retour à la ligne */
          overflow-x: auto;   /* Permet le défilement horizontal si nécessaire */
          white-space: nowrap; /* Empêche le texte de se wrapper */
          padding: 0.5rem 0;  /* Ajoute un peu d'espace pour le défilement */
      }
      .profile-dropdown img {
        width: 40px; /* Ajustez la largeur selon vos besoins */
        height: 40px; /* Ajustez la hauteur selon vos besoins */
        border-radius: 50%; /* Pour un effet arrondi */
        object-fit: cover; /* Pour s'assurer que l'image conserve ses proportions */
      }

      /* Pour une meilleure expérience de défilement */
      .navbar .nav-links::-webkit-scrollbar {
          height: 4px;
      }

      .navbar .nav-links::-webkit-scrollbar-thumb {
          background-color: rgba(255, 255, 255, 0.2);
          border-radius: 2px;
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
      /* Optionnel : styles pour le thème clair */
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
      /* Styles spécifiques à la page admin */
      .container {
        padding: 2rem;
      }
      .coin-table {
        margin-top: 20px;
      }
      .alert {
        margin-bottom: 20px;
      }
    </style>
  </head>
  <body>
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
          <img id="profileImage" src="https://bknsdinyqktmlaoqqxkv.supabase.co/storage/v1/object/public/avatars/img/<%= ((Users)userObj).getAvatar()%>" />
          <span id="profileName"><%= userName %></span>
          <div class="dropdown-menu" id="dropdownMenu">
            <a href="/auth/logout">Disconnect</a>
          </div>
        </div>
      </div>
    </nav>

    <div class="container mt-5">
      <h1>Validation des demandes de transaction</h1>
      
      <!-- Zone d'affichage des messages -->
      <%
        Object successMessage = request.getAttribute("successMessage");
        if (successMessage != null) {
      %>
        <div class="alert alert-success" role="alert">
          <%= successMessage %>
        </div>
      <%
        }
        Object errorMessage = request.getAttribute("errorMessage");
        if (errorMessage != null) {
      %>
        <div class="alert alert-danger" role="alert">
          <%= errorMessage %>
        </div>
      <%
        }
      %>
      
      <table class="table">
        <thead>
          <tr>
            <th>#</th>
            <th>Type</th>
            <th>Montant (USD)</th>
            <th>Date</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          <%
            if(demandes != null){
              for(TransactionFonds tf : demandes) {
                  int type=tf.getIdTypeTransaction();
                  String rep="";
                  if(type==1){
                    rep="Depot";
                  } else if (type==2) {
                    rep="Retrait";
                  }
          %>
          <tr>
            <td><%= tf.getIdTransactionFonds() %></td>
            <td><%= rep %></td>
            <td>$<%= tf.getMontant() %></td>
            <td><%= tf.getDateTransaction() %></td>
            <td>
              <form action="<%= request.getContextPath() %>/admin/validateTransaction" method="post" style="display:inline-block;">
                <input type="hidden" name="transactionId" value="<%= tf.getIdTransactionFonds() %>" />
                <input type="hidden" name="action" value="accept" />
                <button type="submit" class="btn btn-success btn-sm">Accepter</button>
              </form>
              <form action="<%= request.getContextPath() %>/admin/validateTransaction" method="post" style="display:inline-block;">
                <input type="hidden" name="transactionId" value="<%= tf.getIdTransactionFonds() %>" />
                <input type="hidden" name="action" value="refuse" />
                <button type="submit" class="btn btn-danger btn-sm">Refuser</button>
              </form>
            </td>
          </tr>
          <%
              }
            }
          %>
        </tbody>
      </table>
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
    </script>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  </body>
</html>
