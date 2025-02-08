<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="itu.p16.crypto.entity.Cryptomonnaie" %>
<%@ page import="itu.p16.crypto.entity.Users" %>
<%
    // Récupération de la liste des cryptomonnaies depuis l'attribut "cryptos"
    List<Cryptomonnaie> cryptos = (List<Cryptomonnaie>) request.getAttribute("cryptos");
%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta content="width=device-width, initial-scale=1.0" name="viewport" />
    <title>Crypto Planet</title>
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
      .light-theme .market-coins,
      .light-theme .buy-sell,
      .light-theme .coin-table {
        background-color: #e0e0e0;
      }
      .light-theme .coin-item,
      .light-theme .table-row {
        background-color: #d0d0d0;
      }
      .light-theme .table-header {
        background-color: #d0d0d0;
      }
      .light-theme .form-group input,
      .light-theme .form-group select {
        background-color: #fff;
        color: #000;
      }
      .light-theme .form-group input::placeholder {
        color: #8b949e;
      }
      .light-theme .table-body {
        background-color: #e0e0e0;
      }
      /* ----------------------- Styles spécifiques à cette page ----------------------- */
      .container {
        padding: 2rem;
      }
      .market-coins,
      .buy-sell,
      .coin-table {
        background-color: #161b22;
        border-radius: 10px;
        padding: 20px;
        margin: 20px 0;
        transition: background-color 0.3s;
      }
      .market-coins h2,
      .buy-sell h2 {
        font-size: 24px;
        font-weight: 500;
      }
      .market-coins p {
        color: #8b949e;
      }
      .coin-item,
      .table-row {
        background-color: #21262d;
        border-radius: 10px;
        padding: 10px;
        margin: 10px 0;
        display: flex;
        justify-content: space-between;
        align-items: center;
        transition: background-color 0.3s;
      }
      .coin-item img,
      .table-row img {
        width: 30px;
        margin-right: 10px;
      }
      .coin-info {
        display: flex;
        align-items: center;
      }
      .coin-info h3 {
        margin: 0;
        font-size: 18px;
        font-weight: 500;
      }
      .coin-info p {
        margin: 0;
        color: #8b949e;
      }
      .coin-price {
        margin-left: 10px;
        font-size: 16px;
        font-weight: 500;
      }
      .coin-change {
        margin-left: 10px;
        font-size: 14px;
        color: #2ea043;
      }
      .coin-change.negative {
        color: #da3633;
      }
      .coin-chart {
        width: 50px;
        height: 30px;
        background-color: #0d1117;
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
      .btn-buy {
        background-color: #28a745;
        border: none;
        color: #fff;
      }
      .btn-sell {
        background-color: #dc3545;
        border: none;
        color: #fff;
      }
      .form-group input,
      .form-group select {
        background-color: #21262d;
        border: none;
        color: #c9d1d9;
        font-size: 16px;
        border-radius: 5px;
      }
      .form-group input::placeholder {
        color: #8b949e;
      }
      .form-group button {
        border: none;
        font-size: 16px;
        font-weight: 500;
        border-radius: 5px;
        cursor: pointer;
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
      .coin-symbol {
        background-color: #21262d;
        padding: 2px 5px;
        border-radius: 5px;
        font-size: 12px;
        color: #c9d1d9;
      }
      .coin-symbol.btc {
        background-color: #f7931a;
      }
      .coin-symbol.eth {
        background-color: #3c3c3d;
      }
      .coin-symbol.usdt {
        background-color: #26a17b;
      }
      .coin-symbol.bnb {
        background-color: #f0b90b;
      }
      /* Animation de rafraîchissement : effet de remontée/flou */
      @keyframes fadeUp {
        0% {
          opacity: 0;
          transform: translateY(10px);
          filter: blur(2px);
        }
        100% {
          opacity: 1;
          transform: translateY(0);
          filter: blur(0);
        }
      }
      .refresh-animation {
        animation: fadeUp 0.5s ease-out;
      }
    </style>
  </head>
  <body>
     <nav class="navbar">
       <div class="nav-links">
        <a href="<%= request.getContextPath() %>/transaction/histotransaction">
          <i class="fas fa-history"></i> Trade History
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
       <%
         Object successMessage = session.getAttribute("successMessage");
         if (successMessage != null) {
       %>
       <div class="alert alert-success" role="alert">
         <%= successMessage %>
       </div>
       <%
           session.removeAttribute("successMessage"); // Supprime après affichage
         }

         Object errorMessage = session.getAttribute("errorMessage");
         if (errorMessage != null) {
       %>
       <div class="alert alert-danger" role="alert">
         <%= errorMessage %>
       </div>
       <%
           session.removeAttribute("errorMessage"); // Supprime après affichage
         }
       %>

       
       <!-- Bloc affichant le solde actuel -->
       <div class="balance-card">
         <h2>$<%= request.getAttribute("balance") != null ? request.getAttribute("balance") : "0.00" %></h2>
         <p>Current Balance</p>
       </div>
       
       <!-- Section Market Coins -->
       <div class="market-coins">
         <h2>Market Coins</h2>
<%--         <p>Lorem Ipsum is simply dummy text of the printing.</p>--%>
         <div class="row">
           <!-- Trois blocs d'affichage -->
           <div class="col-md-4">
             <div class="coin-item" id="coin-item-0">
               <div class="coin-info">
                 <img alt="Crypto Logo" id="coin-img-0" src="" />
                 <div>
                   <h3 id="coin-name-0"></h3>
                   <p class="coin-price" id="coin-price-0"></p>
                   <p class="coin-change" id="coin-change-0"></p>
                 </div>
               </div>
               <div class="coin-chart"></div>
             </div>
           </div>
           <div class="col-md-4">
             <div class="coin-item" id="coin-item-1">
               <div class="coin-info">
                 <img alt="Crypto Logo" id="coin-img-1" src="" />
                 <div>
                   <h3 id="coin-name-1"></h3>
                   <p class="coin-price" id="coin-price-1"></p>
                   <p class="coin-change" id="coin-change-1"></p>
                 </div>
               </div>
               <div class="coin-chart"></div>
             </div>
           </div>
           <div class="col-md-4">
             <div class="coin-item" id="coin-item-2">
               <div class="coin-info">
                 <img alt="Crypto Logo" id="coin-img-2" src="" />
                 <div>
                   <h3 id="coin-name-2"></h3>
                   <p class="coin-price" id="coin-price-2"></p>
                   <p class="coin-change" id="coin-change-2"></p>
                 </div>
               </div>
               <div class="coin-chart"></div>
             </div>
           </div>
         </div>
       </div>
       
       <!-- Section Buy/Sell (achat/vente de crypto) -->
       <div class="buy-sell">
         <div class="tabs d-flex">
           <button class="active" onclick="showForm('buy')">Buy Crypto</button>
           <button onclick="showForm('sell')">Sell Crypto</button>
         </div>
         
         <!-- Formulaire Buy -->
         <div id="buy-form">
           <form action="<%= request.getContextPath() %>/transaction/buy" method="post">
             <div class="form-group">
               <label for="crypto-select-buy">Select Crypto</label>
               <select class="form-control" id="crypto-select-buy" name="cryptoId">
                 <%
                   if (cryptos != null) {
                     for (Cryptomonnaie crypto : cryptos) {
                 %>
                 <option value="<%= crypto.getIdCryptomonnaie() %>">
                   <%= crypto.getNom() %> (<%= crypto.getSymbole() %>)
                 </option>
                 <%
                     }
                   }
                 %>
               </select>
             </div>
             <input type="hidden" id="price-buy-hidden" name="price" value="0.00" />   
             <div class="form-group">
               <label for="quantity-buy">Quantity (Crypto)</label>
               <input class="form-control" id="quantity-buy" name="quantity" step="0.001" placeholder="0" type="number" required/>
             </div>
             <h2 id="dynamic-price">$0.00</h2>
             <div class="form-group">
               <button class="btn btn-buy btn-block" type="submit">Buy Crypto</button>
             </div>
           </form>
         </div>
         
         <!-- Formulaire Sell -->
         <div id="sell-form" style="display: none;">
           <form action="<%= request.getContextPath() %>/transaction/sell" method="post">
             <div class="form-group">
               <label for="crypto-select-sell">Select Crypto</label>
               <select class="form-control" id="crypto-select-sell" name="cryptoId">
                 <%
                   if (cryptos != null) {
                     for (Cryptomonnaie crypto : cryptos) {
                 %>
                 <option value="<%= crypto.getIdCryptomonnaie() %>">
                   <%= crypto.getNom() %> (<%= crypto.getSymbole() %>)
                 </option>
                 <%
                     }
                   }
                 %>
               </select>
             </div>
             <input type="hidden" id="price-sell-hidden" name="price" value="0.00" />
             <div class="form-group">
               <label for="quantity-sell">Quantity (Crypto)</label>
               <input class="form-control" id="quantity-sell" name="quantity" step="0.001" placeholder="0" type="number" required />
             </div>
             <h2 id="dynamic-price-sell">$0.00</h2>
             <div class="form-group">
               <button class="btn btn-sell btn-block" type="submit">Sell Crypto</button>
             </div>
           </form>
         </div>
       </div>
       
       <!-- (Optionnel) Tableau des transactions -->
       <%-- <div class="coin-table">
         <div class="table-header d-flex">
           <div class="col">#</div>
           <div class="col">Coin Name</div>
           <div class="col">Coin Price</div>
           <div class="col">24%</div>
           <div class="col">24h High</div>
           <div class="col">24h Low</div>
           <div class="col">Chart</div>
         </div>
         <div class="table-body">
           <!-- Contenu du tableau -->
         </div>
       </div> --%>
     </div>
     
     <!-- Transformation de la liste Java en tableau JavaScript -->
     <script>
       var cryptosData = [
         <% if (cryptos != null) {
              for (int i = 0; i < cryptos.size(); i++) {
                  Cryptomonnaie c = cryptos.get(i);
         %>
         {
           id: <%= c.getIdCryptomonnaie() %>,
           nom: "<%= c.getNom() %>",
           symbole: "<%= c.getSymbole() %>",
           price: "<%= c.getCurrentPrice() != null ? String.format("%.2f", c.getCurrentPrice()) : "0.00" %>",
           icon: "<%= c.getIcon() %>" 
         }<%= (i < cryptos.size()-1) ? "," : "" %>
         <%   }
            } %>
       ];
       
       // Fonction qui met à jour un bloc donné avec une crypto choisie aléatoirement 
       // et affiche le prix réel provenant de la base (stocké dans cryptosData.price)
       // Le pourcentage de variation reste généré aléatoirement pour l'effet d'animation.
       function updateCoin(blockIndex) {
         var randomIndex = Math.floor(Math.random() * cryptosData.length);
         var crypto = cryptosData[randomIndex];
         
         // Utilise le vrai prix depuis la base
         var price = crypto.price;
         
         // Pour l'animation, une variation aléatoire
         var change = (Math.random() * 10 - 5).toFixed(2);
         var changeText = (change >= 0 ? "+" : "") + change + "%";
         
         var coinItem = document.getElementById("coin-item-" + blockIndex);
         coinItem.classList.add("refresh-animation");
         
         document.getElementById("coin-name-" + blockIndex).innerText = crypto.nom;
         document.getElementById("coin-price-" + blockIndex).innerText = "$" + price;
         document.getElementById("coin-change-" + blockIndex).innerText = changeText;
         // Utilisation du chemin vers l'icône depuis le contexte de l'application
         document.getElementById("coin-img-" + blockIndex).src = "assets/img/" + crypto.icon;

         // Définition du chemin de l'image
         var imgElement = document.getElementById("coin-img-" + blockIndex);
         imgElement.src = "/assets/img/" + crypto.icon;

         setTimeout(function(){
           coinItem.classList.remove("refresh-animation");
         }, 500);
       }
       
       // Initialisation : mise à jour de tous les blocs dès le chargement
       for (var i = 0; i < 3; i++) {
         updateCoin(i);
       }
       
       // Rafraîchissement : mise à jour d'un bloc choisi aléatoirement toutes les 3 secondes
       setInterval(function(){
         var blockIndex = Math.floor(Math.random() * 3);
         updateCoin(blockIndex);
       }, 3000);
     </script>
     
     <!-- Script pour le calcul dynamique du prix total dans le formulaire Buy -->
     <script>
       function updateDynamicPrice() {
         var select = document.getElementById("crypto-select-buy");
         var quantityInput = document.getElementById("quantity-buy");
         var selectedId = select.value;
         var quantity = parseFloat(quantityInput.value) || 0;
         // Recherche la crypto dans cryptosData par son id
         var crypto = cryptosData.find(function(c) {
           return c.id == selectedId;
         });
         if (crypto) {
           var unitPrice = parseFloat(crypto.price);
           var total = (unitPrice * quantity).toFixed(2);
           document.getElementById("dynamic-price").innerText = "$" + total;
           // Met à jour le champ caché pour transmettre le prix unitaire
           document.getElementById("price-buy-hidden").value = unitPrice.toFixed(2);
         } else {
           document.getElementById("dynamic-price").innerText = "$0.00";
           document.getElementById("price-buy-hidden").value = "0.00";
         }
       }
       
       document.getElementById("crypto-select-buy").addEventListener("change", updateDynamicPrice);
       document.getElementById("quantity-buy").addEventListener("input", updateDynamicPrice);
       updateDynamicPrice();
     </script>
     
     <!-- Script pour le calcul dynamique du prix total dans le formulaire Sell -->
     <script>
       function updateDynamicPriceSell() {
         var selectSell = document.getElementById("crypto-select-sell");
         var quantitySellInput = document.getElementById("quantity-sell");
         var selectedId = selectSell.value;
         var quantity = parseFloat(quantitySellInput.value) || 0;
         var crypto = cryptosData.find(function(c) {
           return c.id == selectedId;
         });
         if (crypto) {
           var unitPrice = parseFloat(crypto.price);
           var total = (unitPrice * quantity).toFixed(2);
           document.getElementById("dynamic-price-sell").innerText = "$" + total;
           document.getElementById("price-sell-hidden").value = unitPrice.toFixed(2);
         } else {
           document.getElementById("dynamic-price-sell").innerText = "$0.00";
           document.getElementById("price-sell-hidden").value = "0.00";
         }
       }
       
       document.getElementById("crypto-select-sell").addEventListener("change", updateDynamicPriceSell);
       document.getElementById("quantity-sell").addEventListener("input", updateDynamicPriceSell);
       updateDynamicPriceSell();
     </script>
     
     <!-- Scripts JavaScript pour la page -->
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
         if (type === "buy") {
           document.getElementById("buy-form").style.display = "block";
           document.getElementById("sell-form").style.display = "none";
           document.querySelector(".tabs button:nth-child(1)").classList.add("active");
           document.querySelector(".tabs button:nth-child(2)").classList.remove("active");
         } else {
           document.getElementById("buy-form").style.display = "none";
           document.getElementById("sell-form").style.display = "block";
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
