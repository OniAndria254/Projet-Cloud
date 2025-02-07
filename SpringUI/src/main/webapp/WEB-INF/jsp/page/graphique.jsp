<%@ page import="itu.p16.crypto.entity.Users" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <title>Crypto Planet</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet"/>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;4000;600;700&display=swap" rel="stylesheet"/>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet"/>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chartjs-plugin-annotation@1.1.0"></script>
    <style>
        body {
            margin: 0;
            font-family: 'Inter', sans-serif;
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
        .container {
            padding: 2rem;
        }
        .market-stats, .chart-section {
            background-color: #161b22;
            padding: 1.5rem;
            border-radius: 10px;
            margin-bottom: 1.5rem;
            transition: background-color 0.3s;
        }
        .market-stats .header, .chart-section .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 1rem;
        }
        .market-stats .header .title, .chart-section .header .title {
            display: flex;
            align-items: center;
            gap: 0.5rem;
        }
        .market-stats .header .title img {
            width: 30px;
        }
        .market-stats .header .title .badge {
            background-color: #21262d;
            padding: 0.2rem 0.5rem;
            border-radius: 5px;
            font-size: 0.8rem;
            transition: background-color 0.3s;
        }
        .market-stats .header .title .star {
            color: #f0a500;
        }
        .market-stats .price {
            font-size: 2rem;
            font-weight: 600;
        }
        .market-stats .price-change {
            color: #2ea043;
            font-size: 1rem;
        }
        .market-stats .stats {
            display: flex;
            justify-content: space-between;
            margin-top: 1rem;
        }
        .market-stats .stats .stat {
            background-color: #21262d;
            padding: 1rem;
            border-radius: 10px;
            text-align: center;
            flex: 1;
            margin: 0 0.5rem;
            transition: background-color 0.3s;
        }
        .market-stats .stats .stat:first-child {
            margin-left: 0;
        }
        .market-stats .stats .stat:last-child {
            margin-right: 0;
        }
        .market-stats .stats .stat .value {
            font-size: 1.2rem;
            font-weight: 4000;
        }
        .market-stats .stats .stat .change {
            font-size: 0.9rem;
        }
        .chart-section .chart {
            background-color: #21262d;
            padding: 1rem;
            border-radius: 10px;
            transition: background-color 0.3s;
            height: 750px; /* hauteur doublée */
        }

        /* Si besoin de forcer la hauteur du canvas lui-même */
        #cryptoChart {
            height: 100%;
        }
        .chart-section .chart canvas {
            width: 100%;
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
            content: '';
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
        .light-theme {
            background-color: #f0f0f0;
            color: #000000;
        }
        .light-theme .navbar {
            background-color: #e0e0e0;
        }
        .light-theme .navbar .nav-links a {
            color: #000000;
        }
        .light-theme .navbar .nav-links a:hover {
            color: #007bff;
        }
        .light-theme .market-stats, .light-theme .chart-section {
            background-color: #e0e0e0;
        }
        .light-theme .market-stats .header .title .badge, .light-theme .market-stats .stats .stat {
            background-color: #d0d0d0;
        }
        .light-theme .theme-toggle .icon {
            color: #000000;
        }
        .light-theme .btn-outline-light {
            color: #000000;
            border-color: #000000;
        }
        .light-theme .btn-outline-light:hover {
            background-color: #000000;
            color: #ffffff;
        }
        @media (max-width: 768px) {
            .navbar .nav-links {
                display: none;
            }
            .navbar .right-section {
                display: none;
            }
        }
    </style>
</head>
<body>
    <nav class="navbar">
       <div class="nav-links">
         <a href="<%= request.getContextPath() %>/transaction/buy-sell">
        <i class="fas fa-dollar-sign"></i> Transaction
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
        <a href="#">
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
               <span id="profileName"><%= userName %></span>
               <div class="dropdown-menu" id="dropdownMenu">
                   <a href="/auth/logout">Disconnect</a>
               </div>
         </div>
       </div>
     </nav>
    <div class="container">
        <div class="market-stats">
            <div class="header">
                <div class="title">
                    <img alt="Crypto Logo" id="cryptoLogo" src=""/>
                    <span id="cryptoName"></span>
                    <span class="badge" id="cryptoSymbol"></span>
                    <i class="fas fa-star star"></i>
                </div>
                <div class="price">
                    <span id="cryptoPrice"></span>
                </div>
                <div class="search">
                    <div class="search">
                        <select class="form-control" id="cryptoSelect">
                            <!-- Les options seront ajoutées dynamiquement ici -->
                        </select>
                    </div>
                </div>
            </div>
        </div>

        <div class="chart-section">
            <div class="header">
                <div class="title">
                    <span>Crypto Price Chart</span>
                </div>
            </div>
            <div class="chart">
                <canvas id="cryptoChart"></canvas>
            </div>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script>
    const ctx = document.getElementById('cryptoChart').getContext('2d');
    const totalLines = 15;
    const initialData = [];
    const initialLabels = [];
    let currentCryptoId = getCryptoIdFromURL();
    let lastTimestamp = null;

    let cryptoChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: [],
            datasets: [{
                label: 'Price',
                data: [],
                borderColor: '#58a6ff',
                borderWidth: 2,
                tension: 0, // Pour une courbe fluide
                fill: false,
            }]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            scales: {
                x: {
                    title: { display: true, text: "Date & Heure d'insertion" },
                    grid: { display: false },
                    offset: true,
                    bounds: 'data',
                },
                y: {
                    title: { display: true, text: 'Price (USD)' },
                    beginAtZero: false,
                    grace: '15%',
                    ticks: {
                        callback: function(value) {
                            return '$' + value.toFixed(2);
                        }
                    },
                    grid: { color: '#21262d' }
                }
            },
            plugins: {
                annotation: {
                    annotations: []
                },
                tooltip: {
                    callbacks: {
                        label: function(context) {
                            return `(${context.label}, ${context.raw})`;
                        }
                    }
                }
            },
            animation: { duration: 0 }
        }
    });

    function getCryptoIdFromURL() {
        const urlParams = new URLSearchParams(window.location.search);
        const cryptoId = urlParams.get('cryptoId');
        return cryptoId ? parseInt(cryptoId, 10) : 1;
    }

    function updateChartWithCrypto(selectedCryptoId) {
        currentCryptoId = parseInt(selectedCryptoId, 10);
        cryptoChart.data.datasets[0].data = [];
        cryptoChart.data.labels = [];
        lastTimestamp = null;
        updateCryptoDetails(currentCryptoId);
        cryptoChart.update();
    }

    document.getElementById('cryptoSelect').addEventListener('change', function(event) {
        const selectedCryptoId = event.target.value;
        updateChartWithCrypto(selectedCryptoId);
    });

    async function fetchCryptoData() {
        try {
            // Utilisation de currentCryptoId, qui a été mis à jour lors du changement
            const cryptoId = currentCryptoId;
            updateCryptoDetails(cryptoId);

            let url = '/api/crypto/historique';
            if (lastTimestamp) { 
                url += `?since=${lastTimestamp}`; 
            }

            const response = await fetch(url);
            const data = await response.json();
            // On filtre les données pour n'avoir que celles correspondant à la crypto affichée
            const filteredData = data.filter(item => item.idCryptomonnaie === cryptoId);

            if (filteredData.length > 0) {
                // Mettre à jour lastTimestamp pour la prochaine requête
                lastTimestamp = filteredData[filteredData.length - 1].timestamp;
                filteredData.forEach((item) => {
                    // Conversion de la date en label lisible (heure d'arrivée)
                    const dateObj = new Date(item.dateEnregistrement);
                    const options = { day: '2-digit', month: '2-digit', year: 'numeric', hour: '2-digit', minute: '2-digit' };
                    const labelDateTime = dateObj.toLocaleString('fr-FR', options);

                    // Si le nombre maximum de points est atteint, supprimer le premier point
                    if (cryptoChart.data.datasets[0].data.length >= totalLines) {
                        cryptoChart.data.datasets[0].data.shift();
                        cryptoChart.data.labels.shift();
                    }

                    // Ajout de la nouvelle donnée et de son label
                    cryptoChart.data.datasets[0].data.push(item.prix);
                    cryptoChart.data.labels.push(item.prix);
                    updateCryptoPrice(item.prix);
                });

                // Ajustement automatique des axes
                cryptoChart.options.scales.x.min = cryptoChart.data.labels[0];
                cryptoChart.options.scales.x.max = cryptoChart.data.labels[cryptoChart.data.labels.length - 1];

                // Mise à jour silencieuse puis classique du graphique
                cryptoChart.update('quiet');
                cryptoChart.update();
            }
            // Replanification de l'appel
            setTimeout(fetchCryptoData, 10000); // ou 10000ms si c'est toutes les 10 secondes
        } catch (error) {
            console.error('Error fetching data:', error);
            setTimeout(fetchCryptoData, 10000);
        }
    }

    // Démarrage de la récupération périodique des données
    fetchCryptoData();


    function toggleTheme() {
        document.body.classList.toggle('light-theme');
        const themeIcon = document.getElementById('themeIcon');
        if (document.body.classList.contains('light-theme')) {
            themeIcon.classList.remove('fa-moon');
            themeIcon.classList.add('fa-sun');
        } else {
            themeIcon.classList.remove('fa-sun');
            themeIcon.classList.add('fa-moon');
        }
    }

    document.addEventListener('DOMContentLoaded', function() {
        fetchCryptoOptions();
    });

    async function fetchCryptoOptions() {
        try {
            const response = await fetch('/api/crypto/getcrypto');
            const cryptos = await response.json();
            const cryptoSelect = document.getElementById('cryptoSelect');
            cryptoSelect.innerHTML = '';
            cryptos.forEach(crypto => {
                const option = document.createElement('option');
                option.value = crypto.idCryptomonnaie;
                option.textContent = crypto.nom;
                cryptoSelect.appendChild(option);
            });
            const cryptoId = getCryptoIdFromURL();
            if (cryptoId) { cryptoSelect.value = cryptoId; }
        } catch (error) {
            console.error('Error fetching crypto options:', error);
        }
    }

    /*function updateCryptoDetails(cryptoId) {
        fetch('/api/crypto/getcrypto')
            .then(response => response.json())
            .then(cryptos => {
                const selectedCrypto = cryptos.find(crypto => crypto.idCryptomonnaie === parseInt(cryptoId, 10));
                if (selectedCrypto) {
                    document.getElementById('cryptoName').textContent = selectedCrypto.nom;
                } else {
                    console.error('Crypto not found for ID:', cryptoId);
                }
            })
            .catch(error => console.error('Error fetching crypto details:', error));
    }*/

    function updateCryptoDetails(cryptoId) {
        fetch('/api/crypto/getcrypto') // Récupère les données des cryptomonnaies
            .then(response => response.json())
            .then(cryptos => {
                // Trouve la cryptomonnaie correspondante
                const selectedCrypto = cryptos.find(crypto => crypto.idCryptomonnaie === parseInt(cryptoId, 10));
                if (selectedCrypto) {
                    // Met à jour le nom et le symbole
                    document.getElementById('cryptoName').textContent = selectedCrypto.nom;
                    document.getElementById('cryptoSymbol').textContent = selectedCrypto.symbole || "N/A";

                    // Construit le chemin de l'image
                    const imagePath = '/assets/img/' + selectedCrypto.icon;

                    console.log(selectedCrypto.symbole);
                    // Met à jour l'image de la cryptomonnaie
                    const cryptoLogo = document.getElementById('cryptoLogo');
                    cryptoLogo.src = imagePath;

                    // Gestion des erreurs si l'image n'existe pas
                    cryptoLogo.onerror = () => {
                        cryptoLogo.src = '/assets/img/default.png'; // Image par défaut
                    };
                } else {
                    console.error('Crypto not found for ID:', cryptoId);
                }
            })
            .catch(error => console.error('Error fetching crypto details:', error));
    }
    

    function updateCryptoPrice(price) {
        const priceElement = document.getElementById('cryptoPrice');
        if (priceElement) {
            priceElement.textContent = '$' + price.toFixed(2);
        }

        // Mise à jour dynamique de l'échelle Y
        const currentData = cryptoChart.data.datasets[0].data;
        const minPrice = Math.min(...currentData);
        const maxPrice = Math.max(...currentData);

        // Ajustement des limites de l'axe Y avec une marge de 15%
        cryptoChart.options.scales.y.min = minPrice * 0.85;
        cryptoChart.options.scales.y.max = maxPrice * 1.15;

        // Mise à jour silencieuse du graphique
        cryptoChart.update('quiet');
    }
    </script>
</body>
</html>