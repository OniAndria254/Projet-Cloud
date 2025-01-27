<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>CRYPTO - Retrait</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    body {
      background-color: #f8f9fa;
      font-family: Arial, sans-serif;
    }

    /* Entête */
    .navbar {
      background-color: #fff;
      border-bottom: 1px solid #ddd;
      box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    }

    .navbar-brand {
      display: flex;
      align-items: center;
      font-size: 1.5rem;
      font-weight: bold;
      color: #004d99;
    }

    .navbar-brand img {
      width: 40px;
      height: 40px;
      margin-right: 10px;
    }

    .user-balance {
      font-size: 1rem;
      color: #333;
      font-weight: bold;
    }
    /* Style du bouton */
.btn-primary {
  background-color: #28a745; /* Vert similaire au symbole */
  border-color: #28a745;
  color: #fff;
  position: relative;
  overflow: hidden;
}

.btn-primary:hover {
  background-color: #218838; /* Vert plus foncé au survol */
  border-color: #218838;
}

/* Effet étincelant */
.btn-primary::before {
  content: '';
  position: absolute;
  top: -50%;
  left: -50%;
  width: 200%;
  height: 200%;
  background: rgba(242, 252, 113, 0.747);
  transform: rotate(45deg);
  transition: transform 0.5s, opacity 0.5s;
  opacity: 0;
  z-index: 1;
}

.btn-primary:hover::before {
  transform: rotate(90deg);
  opacity: 1;
}

/* Texte CRYPTO */
.navbar-brand {
  color: #28a745 !important; /* Même vert que le bouton */
}

.navbar-brand:hover {
  color: #218838 !important; /* Vert légèrement plus foncé */
}


    .main-content {
      margin: 20px auto;
      max-width: 800px;
      background: #fff;
      padding: 20px;
      border-radius: 8px;
      box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
    }

    .main-content h1 {
      font-size: 1.8rem;
      color: #0ea55a;
    }

    

    
    /* FAQ Styling */
    .faq {
      margin-top: 30px;
    }

    .faq h3 {
      font-size: 1.2rem;
      color: #333;
    }

    .faq .accordion-button {
      background-color: #f8f9fa;
      color: #2bdf49;
    }

    .faq .accordion-button:focus {
      box-shadow: none;
    }
  </style>
</head>
<body>

<!-- Barre de navigation -->
<nav class="navbar navbar-expand-lg">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">
      <img src="assets/img/cryptoz-favicon.png" alt="Logo"> CRYPTO
    </a>
    <div class="d-flex align-items-center" id="navbarNav">
      <ul class="navbar-nav">
        <!-- Portefeuille -->
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="portefeuilleDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Portefeuille
          </a>
          <ul class="dropdown-menu" aria-labelledby="portefeuilleDropdown">
            <li><a class="dropdown-item" href="welcome.html">Fond</a></li>
          </ul>
        </li>
        
        <!-- Transaction -->
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="transactionDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Transaction
          </a>
          <ul class="dropdown-menu" aria-labelledby="transactionDropdown">
            <li><a class="dropdown-item" href="#nouveau">Acheter Crypto</a></li>
            <li><a class="dropdown-item" href="#update">Vendre</a></li>
            <li><a class="dropdown-item" href="retrait.html">Retrait</a></li>
            <li><a class="dropdown-item" href="depot.html">Dépôt</a></li>
          </ul>
        </li>
      </ul>
    </div>
    <div class="d-flex align-items-center">
      <span class="user-balance me-3">Solde : 0.00 USD</span>
      <button class="btn btn-danger">Déposer</button>
    </div>
  </div>
</nav>

<!-- Contenu principal -->
<div class="main-content">
  <h1>Retrait</h1>
  <p class="text-muted">Sélectionnez la crypto-monnaie et entrez le montant à retirer.</p>

  <form>
    <!-- Sélection de la crypto -->
    <div class="mb-3">
      <label for="crypto" class="form-label">Crypto-monnaie</label>
      <select id="crypto" class="form-select">
        <option value="btc">Bitcoin (BTC)</option>
        <option value="eth">Ethereum (ETH)</option>
        <option value="usdt">Tether (USDT)</option>
        <option value="bnb">Binance Coin (BNB)</option>
      </select>
    </div>

    <!-- Montant -->
    <div class="mb-3">
      <label for="amount" class="form-label">Montant</label>
      <input type="number" id="amount" class="form-control" placeholder="Entrez le montant" required>
    </div>

    <button type="submit" class="btn btn-primary w-100">Continuer</button>
  </form>
</div>

<!-- Bootstrap JS Bundle -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
