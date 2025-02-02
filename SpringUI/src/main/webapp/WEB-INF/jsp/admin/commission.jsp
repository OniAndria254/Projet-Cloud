<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="itu.p16.crypto.entity.Cryptomonnaie" %>
<%
    // Récupération de la liste des cryptomonnaies depuis l'attribut "cryptos"
    List<Cryptomonnaie> cryptos = (List<Cryptomonnaie>) request.getAttribute("cryptos");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Modifier les Commissions</title>
    <!-- Bibliothèques et polices communes -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet" />
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&amp;display=swap" rel="stylesheet" />
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet" />
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
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
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
        /* Styles spécifiques à cette page */
        .container {
            padding: 2rem;
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
    </style>
</head>
<body>
    <!-- Barre de navigation -->
    <nav class="navbar">
        <div class="nav-links">
            <a href="<%= request.getContextPath() %>/commission">
                <i class="fas fa-percentage"></i> Commission
            </a>
            <a href="<%= request.getContextPath() %>/admin/transactions">
                <i class="fas fa-check-circle"></i> Validation
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
        <h1>Modifier les Commissions</h1>
        <form action="<%= request.getContextPath() %>/api/commission/modifier" method="post">
            <div class="form-group">
                <label for="crypto">Crypto:</label>
                <select id="crypto" name="idCrypto" class="form-control">
                    <%
                        if (cryptos != null) {
                            for (Cryptomonnaie crypto : cryptos) {
                    %>
                    <option value="<%= crypto.getIdCryptomonnaie() %>">
                     <img src="/assets/img/<%= crypto.getIcon() %>" alt="<%= crypto.getNom() %>" style="width:20px; margin-right:10px;">
                            <%= crypto.getNom() %> (<%= crypto.getSymbole() %>)
                    </option>
                    <%
                            }
                        }
                    %>
                </select>
            </div>
            <div class="form-group">
                <label for="commissionAchat">Commission d'Achat (%):</label>
                <input type="number" step="0.01" id="commissionAchat" name="commissionAchat" class="form-control" />
            </div>
            <div class="form-group">
                <label for="commissionVente">Commission de Vente (%):</label>
                <input type="number" step="0.01" id="commissionVente" name="commissionVente" class="form-control" />
            </div>
            <button type="submit" class="btn btn-primary">Valider</button>
        </form>
    </div>
    
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
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
    </script>
</body>
</html>
