<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <title>
        Crypto Planet
    </title>
    <script src="https://cdn.tailwindcss.com">
    </script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet"/>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;700&amp;display=swap" rel="stylesheet"/>
    <style>
        body {
            font-family: 'Inter', sans-serif;
        }
    </style>
</head>
<body class="bg-gray-900 text-white">
<header class="flex justify-between items-center p-4 bg-gray-800">
    <div class="flex items-center">
        <img alt="Crypto Planet Logo" class="mr-2" src="https://placehold.co/40x40"/>
        <span class="text-xl font-bold">
     Crypto Planet
    </span>
    </div>
    <div class="flex items-center space-x-4">
        <a class="text-white" href="/auth/login">
            Log in
        </a>
        <a class="bg-blue-600 text-white px-4 py-2 rounded" href="/auth/register">
            Sign up
        </a>
    </div>
</header>

<% if (request.getParameter("msg") != null && !request.getParameter("msg").isEmpty()) { %>
<div id="alert-box" class="fixed top-5 left-1/2 transform -translate-x-1/2 bg-red-500 text-white px-6 py-3 rounded-lg shadow-lg flex items-center space-x-3 transition duration-500 ease-in-out">
    <i class="fas fa-times-circle"></i>
    <span><%= request.getParameter("msg") %></span>
    <button onclick="closeAlert()" class="ml-auto text-lg">
        &times;
    </button>
</div>
<% } %>



<main class="flex flex-col md:flex-row items-center justify-between p-8 md:p-16">
    <div class="md:w-1/2 space-y-4">
        <h1 class="text-4xl md:text-5xl font-bold">
            Buy &amp; Sell
        </h1>
        <h2 class="text-4xl md:text-5xl font-bold text-blue-500">
            Crypto Instant
        </h2>
        <p class="text-gray-400">
            Join world's biggest &amp; trusted Exchange. Trade in Bitcoin, Ethereum, Ripple and many more currencies.
        </p>
        <div class="flex flex-wrap space-x-4 mt-4">
            <img alt="Logoipsum 1" class="rounded-lg shadow-lg" src="large-coin-placed-top-black-computer-keyboard.jpg" width="100px" height="40px"/>
            <img alt="Logoipsum 2" class="rounded-lg shadow-lg" src="minimalistic-still-life-arrangement-with-cryptocurrency.jpg" width="100px" height="40px"/>
            <!-- <img alt="Logoipsum 3" src="https://placehold.co/100x40" width="100px"/> -->

        </div>
    </div>
    <div class="md:w-1/2 mt-8 md:mt-0">
        <div class="relative">
            <img alt="Laptop with trading dashboard" class="rounded-lg shadow-lg" src="portrait-happy-entrepreneur-showing-bitcoin-while-sitting-desk-with-laptop-computer-isolated-black.jpg"/>
            <div class="absolute top-0 left-0 w-full h-full bg-blue-500 opacity-25 rounded-lg">
            </div>
        </div>
    </div>
</main>
</body>
</html>

<script>
    function closeAlert() {
        let alertBox = document.getElementById("alert-box");
        if (alertBox) {
            alertBox.style.opacity = "0";
            setTimeout(() => {
                alertBox.style.display = "none";
            }, 500);
        }
    }

    // Disparition automatique après 5 secondes
    setTimeout(() => {
        closeAlert();
    }, 5000);
</script>
