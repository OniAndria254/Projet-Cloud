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
         <a href="<%= request.getContextPath() %>/admin/transactions">
           <i class="fas fa-check-circle"></i> Validation
         </a>
         <a href="<%= request.getContextPath() %>/graphic/graphe">
           <i class="fas fa-chart-line"></i> Market
         </a>
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
               <img alt="User Profile Picture" id="profileImage" src="/assets/img/profil.png" />
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
                    <img alt="Crypto Logo" id="cryptoLogo" src="" />
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
    const totalLines = 10; // 10 lignes verticales
    const centerLineIndex = Math.floor(totalLines / 2); // 5ème ligne (milieu)
    const initialData = Array(totalLines).fill(null); // Données initiales vides

    let cryptoChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: Array.from({ length: totalLines }, (_, i) => `Line ${i + 1}`), // 10 labels statiques
            datasets: [{
                label: 'Price',
                data: initialData,
                borderColor: '#58a6ff',
                backgroundColor: 'rgba(88, 166, 255, 0.2)',
                fill: true,
                tension: 0.1
            }]
        },
        options: {
            responsive: true,
            scales: {
                x: {
                    title: {
                        display: true,
                        text: 'Instances',
                    },
                    min: 0,
                    max: totalLines - 1,
                    ticks: {
                        display: false,
                        stepSize: 1,
                    },
                    grid: {
                        display: false,
                        color: '#21262d'
                    }
                },
                y: {
                    title: {
                        display: true,
                        text: 'Price (USD)',
                    },
                    beginAtZero: true,
                    min: 0,
                    max: 20000, // Plafond à 200
                    ticks: {
                        stepSize: 50, // Espacement de 50
                    },
                    grid: {
                        color: '#21262d'
                    }
                }
            },
            plugins: {
                annotation: {
                    annotations: [
                        // Lignes horizontales fixes (50, 100, 150, 200)
                        { type: 'line', mode: 'horizontal', scaleID: 'y', value: 50, borderColor: '#ff5733', borderWidth: 1 },
                        { type: 'line', mode: 'horizontal', scaleID: 'y', value: 100, borderColor: '#ff5733', borderWidth: 1 },
                        { type: 'line', mode: 'horizontal', scaleID: 'y', value: 150, borderColor: '#ff5733', borderWidth: 1 },
                        { type: 'line', mode: 'horizontal', scaleID: 'y', value: 200, borderColor: '#ff5733', borderWidth: 1 },
                    ],
                },
                tooltip: {
                    callbacks: {
                        label: function(context) {
                            return `(${context.label}, ${context.raw})`;
                        }
                    }
                }
            },
            animation: {
                duration: 0, // Désactive l'animation pour un défilement fluide
            },
        }
    });

    let lastTimestamp = null; // Stocke le dernier timestamp pour ne récupérer que les nouvelles données

    // Fonction pour récupérer l'ID de la cryptomonnaie depuis l'URL
    function getCryptoIdFromURL() {
        const urlParams = new URLSearchParams(window.location.search);
        const cryptoId = urlParams.get('cryptoId');
        return cryptoId ? parseInt(cryptoId, 10) : 1; // Retourne 1 si l'ID n'est pas présent dans l'URL
    }

    async function fetchCryptoData() {
        try {
            const cryptoId = getCryptoIdFromURL(); // Récupère l'ID de la cryptomonnaie depuis l'URL
            updateCryptoDetails(cryptoId);
            let url = '/api/crypto/graph';
            if (lastTimestamp) {
                url += `?since=${lastTimestamp}`;
            }
            const response = await fetch(url);
            const data = await response.json();
            const filteredData = data.filter(item => item.idCryptomonnaie === cryptoId); // Filtre les données pour la cryptomonnaie spécifique

            if (filteredData.length > 0) {
                lastTimestamp = filteredData[filteredData.length - 1].timestamp; // Met à jour le dernier timestamp

                filteredData.forEach((item) => {
                    // Ajoute les nouvelles données au milieu du tableau
                    cryptoChart.data.datasets[0].data.splice(centerLineIndex, 0, item.prix);
                    // Supprime la première valeur pour décaler vers la gauche
                    cryptoChart.data.datasets[0].data.shift();
                    updateCryptoPrice(item.prix);
                });

                cryptoChart.update(); // Met à jour le graphique
            }

            setTimeout(fetchCryptoData, 10000); // Récupère les données toutes les 500 ms
        } catch (error) {
            console.error('Error fetching data:', error);
            setTimeout(fetchCryptoData, 10000); // Réessaye en cas d'erreur
        }
    }

    // Démarrage du graphique et de la récupération des données
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

    document.getElementById('cryptoSelect').addEventListener('change', function(event) {
        const selectedCryptoId = event.target.value;
        updateChartWithCrypto(selectedCryptoId);
    });

    async function updateChartWithCrypto(cryptoId) {
        // Redirige vers la même page avec l'ID de la cryptomonnaie dans l'URL
        const url = new URL(window.location.href); // Crée un objet URL basé sur l'URL actuelle
        url.searchParams.set('cryptoId', cryptoId); // Met à jour ou ajoute le paramètre `cryptoId`
        window.history.pushState({}, '', url); // Modifie l'URL dans la barre d'adresse sans recharger la page
    }

    document.addEventListener('DOMContentLoaded', function() {
        fetchCryptoOptions();
    });

    async function fetchCryptoOptions() {
        try {
            const response = await fetch('/api/crypto/getcrypto');
            const cryptos = await response.json();

            const cryptoSelect = document.getElementById('cryptoSelect');
            cryptoSelect.innerHTML = ''; // Clear existing options

            cryptos.forEach(crypto => {
                const option = document.createElement('option');
                option.value = crypto.idCryptomonnaie;
                option.textContent = crypto.nom;
                cryptoSelect.appendChild(option);
            });

            // Sélectionne l'option correspondant à l'ID dans l'URL
            const cryptoId = getCryptoIdFromURL();
            if (cryptoId) {
                cryptoSelect.value = cryptoId;
            }
        } catch (error) {
            console.error('Error fetching crypto options:', error);
        }
    }
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

        // Définir dynamiquement la valeur max de l'axe Y
        const newMax = Math.ceil(price * 1.5);

        // Générer dynamiquement des lignes horizontales
        const step = Math.ceil(newMax / 5); // Espacement entre les lignes (5 lignes)
        const dynamicAnnotations = [];

        for (let i = step; i <= newMax; i += step) {
            dynamicAnnotations.push({
                type: 'line',
                mode: 'horizontal',
                scaleID: 'y',
                value: i,
                borderColor: '#ff5733',
                borderWidth: 1
            });
        }

        // Mettre à jour l'échelle Y et les annotations
        cryptoChart.options.scales.y.max = newMax;
        cryptoChart.options.plugins.annotation.annotations = dynamicAnnotations;

        // Appliquer les modifications et mettre à jour le graphique
        cryptoChart.update();
    }

    </script>
</body>
</html>