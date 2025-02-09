<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <title>Crypto Planet</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet"/>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&amp;display=swap" rel="stylesheet"/>
    <style>
        body {
            font-family: 'Inter', sans-serif;
            background: linear-gradient(135deg, #1a1a1a, #0d0d0d);
        }
        h1 {
            font-size: 3.5rem;
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.3);
        }
        h2 {
            font-size: 3rem;
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.3);
        }
        p {
            font-size: 1.2rem;
            line-height: 1.6;
        }
        img {
            transition: transform 0.3s ease, box-shadow 0.3s ease;
        }
        img:hover {
            transform: scale(1.05);
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.3);
        }
        a.bg-blue-600 {
            background: linear-gradient(45deg, #2563eb, #1e40af);
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.2);
            transition: background 0.3s ease, transform 0.3s ease;
        }
        a.bg-blue-600:hover {
            background: linear-gradient(45deg, #1e40af, #2563eb);
            transform: translateY(-2px);
        }
        @keyframes fadeIn {
            from { opacity: 0; transform: translateY(20px); }
            to { opacity: 1; transform: translateY(0); }
        }
        main {
            animation: fadeIn 1s ease-out;
        }
        .flex-wrap {
            justify-content: center;
            gap: 1.5rem;
        }
        img.rounded-lg {
            border: 2px solid rgba(255, 255, 255, 0.1);
        }
    </style>
</head>
<body class="text-white">
    <header class="flex justify-between items-center p-4 bg-gray-800">
        <div class="flex items-center">
            <img alt="Crypto Planet Logo" class="mr-2" src="https://bknsdinyqktmlaoqqxkv.supabase.co/storage/v1/object/public/avatars/img/cryptoz-favicon.png" width="40" height="40"/>
            <span class="text-xl font-bold">Crypto Planet</span>
        </div>
        <div class="flex items-center space-x-4">
            <a class="text-white" href="/auth/login">Log in</a>
            <a class="bg-blue-600 text-white px-4 py-2 rounded" href="/auth/register">Sign up</a>
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
            <h1>Buy &amp; Sell</h1>
            <h2 class="text-blue-500">Crypto Instant</h2>
            <p class="text-gray-400">
                Join world's biggest &amp; trusted Exchange.
            </p>
            <p class="text-gray-400">
                Trade in Bitcoin, Ethereum, Ripple and many more currencies.
            </p>
            
            <div class="flex flex-wrap space-x-4 mt-4">
                <img alt="Logoipsum 1" class="rounded-lg shadow-lg" src="https://bknsdinyqktmlaoqqxkv.supabase.co/storage/v1/object/public/avatars/img/sary1.png" width="100px" height="40px"/>
                <img alt="Logoipsum 2" class="rounded-lg shadow-lg" src="https://bknsdinyqktmlaoqqxkv.supabase.co/storage/v1/object/public/avatars/img/CRYPTO.jpeg" width="100px" height="40px"/>
            </div>
        </div>
        <div class="md:w-1/2 mt-8 md:mt-0">
            <div class="relative">
                <img alt="Laptop with trading dashboard" class="rounded-lg shadow-lg" src="https://bknsdinyqktmlaoqqxkv.supabase.co/storage/v1/object/public/avatars/img/sary2.png"/>
                <div class="absolute top-0 left-0 w-full h-full bg-blue-500 opacity-25 rounded-lg"></div>
            </div>
        </div>
    </main>
    <section class="bg-gray-800 py-12 mt-8">
        <div class="text-center">
            <h3 class="text-3xl font-bold">Why Choose Us?</h3>
            <p class="text-gray-400 mt-4">Join over 9 users worldwide.</p>
            <div class="flex justify-center space-x-8 mt-6">
                <div>
                    <span class="text-4xl font-bold text-blue-500">99%</span>
                    <p class="text-gray-400">Security</p>
                </div>
                <div>
                    <span class="text-4xl font-bold text-blue-500">24/7</span>
                    <p class="text-gray-400">Support</p>
                </div>
                <div>
                    <span class="text-4xl font-bold text-blue-500">9+</span>
                    <p class="text-gray-400">Users</p>
                </div>
            </div>
        </div>
    </section>
    <footer class="bg-gray-800 py-6 mt-8">
        <div class="text-center text-gray-400">
            <p>&copy; 2024 Crypto Planet. All rights reserved.</p>
        </div>
    </footer>
</body>
</html>