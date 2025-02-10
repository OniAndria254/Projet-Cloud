<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, com.example.demo.entity.Commission" %>
<!DOCTYPE html>
<html>
<head>
    <title>Modifier les Commissions</title>
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
            <img id="profileImage" src="<%= ((Users)userObj).getAvatar()%>" />
            <span id="profileName"><%= userName %></span>
            <div class="dropdown-menu" id="dropdownMenu">
              <a href="/auth/logout">Disconnect</a>
            </div>
          </div>
        </div>
      </nav>
    <h1>Modifier les Commissions</h1>

    <!-- Afficher un message de succès -->
    <%
        String message = (String) request.getAttribute("message");
        if (message != null) {
    %>
        <p style="color: green;"><%= message %></p>
    <%
        }
    %>

    <!-- Formulaire de modification -->
    <form action="${pageContext.request.contextPath}/commission/modifier" method="post">
        <label for="idCrypto">Cryptomonnaie :</label>
        <select id="idCrypto" name="idCrypto">
            <%
                List<Commission> commissions = (List<Commission>) request.getAttribute("commissions");
                if (commissions != null) {
                    for (Commission commission : commissions) {
            %>
                        <option value="<%= commission.getIdCryptomonnaie() %>">Cryptomonnaie <%= commission.getIdCryptomonnaie() %></option>
            <%
                    }
                }
            %>
        </select><br><br>

        <label for="commissionAchat">Commission d'Achat (%) :</label>
        <input type="number" step="0.01" id="commissionAchat" name="commissionAchat" required><br><br>

        <label for="commissionVente">Commission de Vente (%) :</label>
        <input type="number" step="0.01" id="commissionVente" name="commissionVente" required><br><br>

        <button type="submit">Modifier</button>
    </form>

    <!-- Afficher la liste des commissions -->
    <h2>Liste des Commissions</h2>
    <table border="1">
        <tr>
            <th>ID Cryptomonnaie</th>
            <th>Commission Achat</th>
            <th>Commission Vente</th>
            <th>Date Modification</th>
        </tr>
        <%
            if (commissions != null) {
                for (Commission commission : commissions) {
        %>
                    <tr>
                        <td><%= commission.getIdCryptomonnaie() %></td>
                        <td><%= commission.getCommissionAchat() %></td>
                        <td><%= commission.getCommissionVente() %></td>
                        <td><%= commission.getDateModification() %></td>
                    </tr>
        <%
                }
            }
        %>
    </table>
</body>
</html>