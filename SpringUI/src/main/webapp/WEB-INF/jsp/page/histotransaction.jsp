<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="itu.p16.crypto.entity.*" %>
<!DOCTYPE html>
<html lang="fr">
  <head>
    <meta charset="UTF-8" />
    <meta content="width=device-width, initial-scale=1.0" name="viewport" />
    <title>Historique des opérations</title>
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
      /* Styles pour le mode clair */
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
      /* Styles spécifiques à la page d'historique */
      .container {
        padding: 2rem;
      }
      .filter-label {
        font-weight: bold;
      }
      .operation-item {
        background-color: #161b22;
        border: none;
        margin-bottom: 1rem;
        border-radius: 5px;
        padding: 1rem;
        transition: background-color 0.3s, color 0.3s;
        cursor: pointer;
      }
      .operation-item:hover {
        background-color: #21262d;
      }
      .user-img {
        width: 50px;
        height: 50px;
        border-radius: 50%;
        object-fit: cover;
        margin-right: 1rem;
      }
      .operation-header {
        display: flex;
        align-items: center;
        justify-content: space-between;
      }
      .operation-user {
        display: flex;
        align-items: center;
      }
      /* Règles spécifiques pour le mode clair appliquées aux opérations */
      .light-theme .operation-item {
        background-color: #f8f9fa;
        color: #000;
      }
      .light-theme .operation-item:hover {
        background-color: #e9ecef;
      }
      .profile-dropdown img {
        width: 40px;
        height: 40px;
        border-radius: 50%;
        object-fit: cover;
      }
    </style>
  </head>
  <body>
    <!-- Nav-Bar -->
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
      <h1>Historique des opérations</h1>
      <div class="row my-4">
        <div class="col-md-3">
          <label for="dateFilter" class="filter-label">Avant la date</label>
          <input type="datetime-local" id="dateFilter2" class="form-control" />
        </div>
        <div class="col-md-3">
          <label for="dateFilter2" class="filter-label">Après la date</label>
          <input type="datetime-local" id="dateFilter" class="form-control" />
        </div>
        <div class="col-md-3">
          <label for="cryptoFilter" class="filter-label">Filtrer par Crypto</label>
          <select id="cryptoFilter" class="form-control">
            <!-- L'option "Tous" reste statique -->
            <option value="">Tous</option>
          </select>
        </div>
      </div>
      <div class="row mb-4">
        <div class="col-md-12 text-right">
          <button id="applyFilters" class="btn btn-primary">Valider</button>
          <button id="showAll" class="btn btn-secondary ml-2">Afficher tout</button>
        </div>
      </div>

      <div id="historyList">
      </div>
    </div>

    <script>
      // Variable globale pour mémoriser le filtre utilisateur actif
      let activeUserFilter = null;

      // Basculer entre mode sombre et clair
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

      function renderTransactions(data) {
        var historyList = document.getElementById("historyList");
        historyList.innerHTML = ""; // Réinitialiser la liste

        // Appeler toutes les API en parallèle
        Promise.all([
          fetch("/api/users/all").then(response => response.json()),
          fetch("/api/crypto/getcrypto").then(response => response.json()),
          fetch("/api/typeTransaction/all").then(response => response.json())
        ])
        .then(([users, cryptos, types]) => {
          // Créer les maps pour un accès rapide
          const userMap = {};
          users.forEach(user => {
            userMap[user.id_users] = user.username;
          });

          const cryptoMapCrypto = {};
          cryptos.forEach(cryptomonnaie => {
            cryptoMapCrypto[cryptomonnaie.idCryptomonnaie] = cryptomonnaie.nom;
          });

          const typeMap = {};
          types.forEach(type => {
            typeMap[type.idTypeTransaction] = type.nom;
          });

          // Parcourir les transactions et créer les éléments
          data.forEach(transaction => {
            var operationItem = document.createElement("div");
            operationItem.classList.add("operation-item");

            var dateObj = new Date(transaction.dateTransaction);
            var formattedDate = dateObj.toLocaleString("fr-FR");

            const headerDiv = document.createElement("div");
            headerDiv.classList.add("operation-header");

            const userDiv = document.createElement("div");
            userDiv.classList.add("operation-user");

            const h5 = document.createElement("h5");
            h5.classList.add("mb-0");
            h5.textContent = userMap[transaction.idUtilisateur] || "Utilisateur " + transaction.idUtilisateur;

            userDiv.appendChild(h5);
            headerDiv.appendChild(userDiv);

            const small = document.createElement("small");
            small.textContent = formattedDate;
            headerDiv.appendChild(small);

            const paragraph = document.createElement("p");
            paragraph.classList.add("mt-2", "mb-0");

            let operationText = "";
            operationText += typeMap[transaction.idTypeTransaction] || "Type inconnu";
            operationText += " ";
            operationText += cryptoMapCrypto[transaction.idCryptomonnaie] || "crypto inconnue";
            operationText += " : montant " + transaction.montantTotal;
            paragraph.textContent = operationText;

            operationItem.appendChild(headerDiv);
            operationItem.appendChild(paragraph);

            // Lors du clic sur une transaction, appliquer le filtre par utilisateur
            operationItem.addEventListener("click", () => {
              activeUserFilter = transaction.idUtilisateur;
              var params = new URLSearchParams();
              params.append("utilisateurId", activeUserFilter);
              fetch("/api/transactions/filter?" + params.toString())
                .then(response => response.json())
                .then(data => renderTransactions(data))
                .catch(error =>
                  console.error("Erreur lors du filtre par utilisateur :", error)
                );
            });

            historyList.appendChild(operationItem);
          });
        })
        .catch(error => console.error("Erreur lors de la récupération des données :", error));
      }

      // Au chargement de la page, récupérer et afficher toutes les transactions
      document.addEventListener("DOMContentLoaded", function () {
        fetch("/api/transactions/all")
          .then(response => response.json())
          .then(data => renderTransactions(data))
          .catch(error =>
            console.error("Erreur lors de la récupération des transactions :", error)
          );

        // Remplissage dynamique du select Cryptomonnaie/api/
        fetch("/api/crypto/getcrypto")
          .then(response => response.json())
          .then(data => {
            let cryptoSelect = document.getElementById("cryptoFilter");
            data.forEach(function (crypto) {
              let option = document.createElement("option");
              option.value = crypto.idCryptomonnaie; // Utilise l'identifiant
              option.text = crypto.nom + " (" + crypto.symbole + ")";
              cryptoSelect.appendChild(option);
            });
          })
          .catch(error =>
            console.error("Erreur lors de la récupération des cryptomonnaies :", error)
          );
      });

      // Gestion du bouton de validation des filtres (ajoute aussi le filtre utilisateur s'il est actif)
      document.getElementById("applyFilters").addEventListener("click", function () {
        var date = document.getElementById("dateFilter").value;
        var date2 = document.getElementById("dateFilter2").value;
        var crypto = document.getElementById("cryptoFilter").value;

        var params = new URLSearchParams();
        if (date) params.append("startDate", date);
        if (date2) params.append("endDate",date2);
        if (crypto) params.append("cryptoId", crypto);
        // Si un utilisateur est déjà sélectionné, le rajouter aux filtres
        if (activeUserFilter) {
          params.append("utilisateurId", activeUserFilter);
        }
        // Si aucun filtre n'est renseigné, on appelle l'endpoint "all"
        if ([...params].length === 0) {
          fetch("/api/transactions/all")
            .then(response => response.json())
            .then(data => renderTransactions(data))
            .catch(error => console.error("Erreur :", error));
          return;
        }
        fetch("/api/transactions/filter?" + params.toString())
          .then(response => response.json())
          .then(data => renderTransactions(data))
          .catch(error => console.error("Erreur lors de l'appel du filtre :", error));
      });

      // Bouton pour afficher toutes les transactions sans aucun filtre (réinitialise aussi le filtre utilisateur)
      document.getElementById("showAll").addEventListener("click", function () {
        activeUserFilter = null;
        document.getElementById("dateFilter").value = "";
        document.getElementById("dateFilter2").value = "";
        document.getElementById("cryptoFilter").value = "";
        fetch("/api/transactions/all")
          .then(response => response.json())
          .then(data => renderTransactions(data))
          .catch(error =>
            console.error("Erreur lors de la récupération des transactions :", error)
          );
      });
    </script>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  </body>
</html>
