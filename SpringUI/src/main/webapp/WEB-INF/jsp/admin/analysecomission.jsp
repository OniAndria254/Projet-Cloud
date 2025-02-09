<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="itu.p16.crypto.entity.*" %>
<% List<Cryptomonnaie> cryptos = (List<Cryptomonnaie>) request.getAttribute("cryptos"); %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Crypto Planet - Analyses</title>
  <!-- Bibliothèques et polices communes -->
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet" />
  <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&amp;display=swap" rel="stylesheet" />
  <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet" />
  <style>
    /* ----------------------- Styles communs ----------------------- */
    body {
      margin: 0;
      font-family: "Inter", sans-serif;
      background-color: #0d1117;
      color: #c9d1d9;
      transition: background-color 0.3s, color 0.3s;
    }

    /* Permettre le défilement du contenu sans afficher la barre */
    .analysis-table-wrapper {
      max-height: 300px;
      overflow-y: scroll;
      scrollbar-width: none; /* Firefox */
    }

    .analysis-table-wrapper::-webkit-scrollbar {
      display: none; /* Chrome, Safari et Edge */
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
    flex-wrap: nowrap;  
    overflow-x: auto;   
    white-space: nowrap; 
    padding: 0.5rem 0; 
    }

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

    /* Thème clair */
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

    /* ----------------------- Styles spécifiques à la page d'analyse ----------------------- */
    .container {
      padding: 2rem;
    }

    h1 {
      margin-bottom: 1rem; /* Réduit l'espace sous le titre pour remonter les champs */
    }

    .form-control {
      background-color: #21262d;
      color: #c9d1d9;
      border: none;
      border-radius: 5px;
    }

    .form-control::placeholder {
      color: #8b949e;
    }

    .btn {
      border: none;
      font-size: 16px;
      font-weight: 500;
      border-radius: 5px;
      cursor: pointer;
    }

    .btn-primary {
      background-color: #007bff;
      color: #fff;
    }

    .btn-primary:hover {
      background-color: #0056b3;
    }

    /* Positionnement des champs sur une seule ligne */
    .row > .col-md-3 {
      display: flex;
      flex-direction: column;
      justify-content: center;
    }

    /* Checkbox group en ligne, réparties sur deux lignes pour 12 cryptos */
    /* Styles pour les checkboxes cochées */
    input[type="checkbox"] {
      appearance: none;
      width: 16px;
      height: 16px;
      border: 2px solid #007bff;
      border-radius: 4px;
      position: relative;
      cursor: pointer;
      outline: none;
      background-color: transparent;
    }

    input[type="checkbox"]:checked {
      background-color: #007bff;
      border-color: #007bff;
    }

    input[type="checkbox"]:checked::after {
      content: "\2713"; /* Symbole de coche */
      font-size: 14px;
      color: white;
      position: absolute;
      top: 50%;
      left: 50%;
      transform: translate(-50%, -50%);
      font-weight: bold;
    }

    .checkbox-group {
      display: flex;
      flex-direction: column;
      gap: 0.5rem;
      margin-top: 1rem;
    }

    .checkbox-row {
      display: flex;
      gap: 1rem;
    }

    .checkbox-row label {
      display: flex;
      align-items: center;
      gap: 0.5rem;
      cursor: pointer;
    }

    /* Tableau scrollable avec en-tête fixe */
    .analysis-table-wrapper {
      max-height: 300px;
      overflow-y: auto;
      margin-top: 1rem; /* Espace réduit au-dessus du tableau */
    }

    .analysis-table thead th {
      position: sticky;
      top: 0;
      background-color: #21262d;
      color: #ffffff;
      z-index: 10;
    }

    .analysis-table th,
    .analysis-table td {
      text-align: center;
      padding: 0.75rem;
    }

    .analysis-table tbody td {
      color: #ffffff;
    }

    .light-theme .analysis-table thead th,
    .light-theme .analysis-table tbody td {
      color: #000;
    }
    .profile-dropdown img {
        width: 40px; /* Ajustez la largeur selon vos besoins */
        height: 40px; /* Ajustez la hauteur selon vos besoins */
        border-radius: 50%; /* Pour un effet arrondi */
        object-fit: cover; /* Pour s'assurer que l'image conserve ses proportions */
    }
  </style>
</head>
<body>
  <!-- Barre de navigation -->
  <nav class="navbar">
    <div class="nav-links">
      <a href="<%= request.getContextPath() %>/transaction/histotransaction">
        <i class="fas fa-history"></i>  Transaction list
      </a>
      <a href="<%= request.getContextPath() %>/graphic/graphe">
        <i class="fas fa-chart-line"></i> Market
      </a>
      <%
        Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
        if(isAdmin != null && isAdmin) {
      %>
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
        if(isAdmin == null && !isAdmin) {
      %>
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
  <div class="container">
    <h1>Analyses</h1>

    <!-- Update the form -->
<form id="analysisForm" action="javascript:void(0);">
  <div class="row">
      <!-- <div class="col-md-3">
          <div class="form-group">
              <label for="typeAnalyse">Type d'analyse:</label>
              <select id="typeAnalyse" name="typeAnalyse" class="form-control">
                  <option value="somme">Somme</option>
                  <option value="moyenne">Moyenne</option>
              </select>
          </div>
      </div> -->
      <div class="col-md-3">
          <div class="form-group">
              <label for="cryptoSelect">Cryptos:</label>
              <select id="cryptoSelect" name="idCrypto" class="form-control">
                  <option value="all">Tous</option>
                  <% for(Cryptomonnaie crypto : cryptos) { %>
                      <option value="<%= crypto.getIdCryptomonnaie() %>"><%= crypto.getNom() %></option>
                  <% } %>
              </select>
          </div>
      </div>
      <div class="col-md-3">
        <div class="form-group">
          <label for="dateMin">Date et heure min:</label>
          <input type="datetime-local" id="dateMin" name="dateMin" class="form-control" />
        </div>
      </div>
      <div class="col-md-3">
        <div class="form-group">
          <label for="dateMax">Date et heure max:</label>
          <input type="datetime-local" id="dateMax" name="dateMax" class="form-control" />
        </div>
      </div>
    </div>
  
    <!-- Bouton Valider -->
    <div class="row mt-3">
      <div class="col-md-12 text-right">
        <button type="submit" class="btn btn-primary">Valider</button>
      </div>
  </div>
</form>

    <!-- Tableau scrollable -->
    <div class="analysis-table-wrapper">
      <table class="table analysis-table">
        <thead>
          <tr>
            <th>Crypto</th>
            <th>somme_commission_achat</th>
            <th>somme_commission_vente</th>
            <th>moyenne_commission_achat</th>
            <th>moyenne_commission_vente</th>
        
          </tr>
        </thead>
        <tbody>
          <!-- <tr>
            <td>Crypto1</td>
            <td>$65,000</td>
            <td>$35,000</td>
            <td>$15,000</td>
          </tr> -->
        </tbody>
      </table>
    </div>
  </div>

  <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  <script>
    document.querySelector("#analysisForm").addEventListener("submit", function(e) {
      e.preventDefault();
      
      const dateMin = document.getElementById("dateMin").value;
      const dateMax = document.getElementById("dateMax").value;
      const idCrypto = document.getElementById("cryptoSelect").value;
      
      fetch("<%= request.getContextPath() %>/analyse/commissions/analyse?dateMin=" + dateMin + 
            "&dateMax=" + dateMax + 
            (idCrypto !== "all" ? "&idCrypto=" + idCrypto : ""))
          .then(response => response.json())
          .then(data => {
              const resultRow = "<tr>" +
                  "<td>" + (idCrypto === "all" ? "Toutes" : document.getElementById("cryptoSelect").selectedOptions[0].text) + "</td>" +
                  "<td>" + (data.somme_commission_achat || "N/A") + "</td>" +
                  "<td>" + (data.somme_commission_vente || "N/A") + "</td>" +
                  "<td>" + (data.moyenne_commission_achat || "N/A") + "</td>" +
                  "<td>" + (data.moyenne_commission_vente || "N/A") + "</td>" +
                  "</tr>";
              document.querySelector(".analysis-table tbody").innerHTML = resultRow;
          })
          .catch(error => console.error("Error:", error));
  });


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
  </script>
</body>
</html>