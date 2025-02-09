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
        <img alt="Crypto Planet Logo" class="mr-2" src="https://bknsdinyqktmlaoqqxkv.supabase.co/storage/v1/object/public/avatars/img/cryptoz-favicon.png"/>
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
<center>
<% if (request.getParameter("msg") != null && !request.getParameter("msg").isEmpty()) { %>
<div class="alert alert-danger" role="alert">
    <%= request.getParameter("msg") %>
</div>
<% } %>
</center>


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
            <img alt="Logoipsum 1" class="rounded-lg shadow-lg" src="https://bknsdinyqktmlaoqqxkv.supabase.co/storage/v1/object/public/avatars/img/sary1.png" width="100px" height="40px"/>
            <img alt="Logoipsum 2" class="rounded-lg shadow-lg" src="https://bknsdinyqktmlaoqqxkv.supabase.co/storage/v1/object/public/avatars/img/sary2.png" width="100px" height="40px"/>
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
