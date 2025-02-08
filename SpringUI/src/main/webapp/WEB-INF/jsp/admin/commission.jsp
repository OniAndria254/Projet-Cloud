<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, itu.p16.crypto.entity.Commission" %>
<%@ page import="itu.p16.crypto.entity.Cryptomonnaie" %>
<%
    List<Commission> commissions = (List<Commission>) request.getAttribute("commissions");
    List<Cryptomonnaie> crytos = (List<Cryptomonnaie>) request.getAttribute("crypto");
%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Modifier les Commissions</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* body {
            font-family: "Inter", sans-serif;
            background-color: #0d1117;
            color: #c9d1d9;
            padding: 20px;
        }
        .container {
            max-width: 600px;
            margin: auto;
            background-color: #161b22;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0px 4px 6px rgba(0,0,0,0.1);
        }
        .form-control, .btn {
            border-radius: 5px;
        }
        .btn-primary {
            background-color: #007bff;
            border: none;
        }
        .btn-primary:hover {
            background-color: #0056b3;
        }
        table {
            width: 100%;
            margin-top: 20px;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #21262d;
        } */
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
    </style>
</head>
<body>
    <nav class="navbar">
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
        </div>
      </nav>
    <div class="container">
        <h1 class="text-center">Modifier les Commissions</h1>
        
        <% String message = (String) request.getAttribute("message");
           if (message != null) { %>
            <div class="alert alert-success" role="alert"><%= message %></div>
        <% } %>

        <form action="${pageContext.request.contextPath}/commission/modifier" method="post">
            <div class="form-group">
                <label for="idCrypto">Cryptomonnaie :</label>
                <select id="idCrypto" name="idCrypto" class="form-control">
                    <%
                       if (crytos != null) {
                           for (Cryptomonnaie crypto : crytos) { %>
                        <option value="<%= crypto.getIdCryptomonnaie() %>"><%= crypto.getNom() %> ( <%= crypto.getSymbole() %> )</option>
                    <% } } %>
                </select>
            </div>
            <div class="form-group">
                <label for="commissionAchat">Commission d'Achat (%) :</label>
                <input type="number" step="0.01" id="commissionAchat" name="commissionAchat" class="form-control" required>
            </div>
            <div class="form-group">
                <label for="commissionVente">Commission de Vente (%) :</label>
                <input type="number" step="0.01" id="commissionVente" name="commissionVente" class="form-control" required>
            </div>
            <button type="submit" class="btn btn-primary btn-block">Modifier</button>
        </form>

        <h2 class="mt-4">Liste des Commissions</h2>
        <table class="table table-dark table-striped">
            <thead>
                <tr>
                    <th>ID Cryptomonnaie</th>
                    <th>Commission Achat</th>
                    <th>Commission Vente</th>
                    <th>Date Modification</th>
                </tr>
            </thead>
            <tbody>
                <% if (commissions != null) {
                       for (Commission commission : commissions) { %>
                    <tr>
                        <td><%= commission.getIdCryptomonnaie() %></td>
                        <td><%= commission.getCommissionAchat() %></td>
                        <td><%= commission.getCommissionVente() %></td>
                        <td><%= commission.getDateModification() %></td>
                    </tr>
                <% } } %>
            </tbody>
        </table>
    </div>
</body>
</html>
