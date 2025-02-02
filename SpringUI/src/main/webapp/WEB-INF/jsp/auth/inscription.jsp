<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8"/>
  <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
  <title>
    Crypto Planet - Sign Up
  </title>
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet"/>
  <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&amp;display=swap" rel="stylesheet"/>
  <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet"/>
  <script src="https://cdn.jsdelivr.net/npm/chart.js">
  </script>
  <style>
    body {
      margin: 0;
      font-family: 'Inter', sans-serif;
      background-color: #0d1117;
      color: #c9d1d9;
      transition: background-color 0.3s, color 0.3s;
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      height: 100vh;
    }
    .header {
      display: flex;
      align-items: center;
      margin-bottom: 2rem;
    }
    .header img {
      width: 40px;
      height: 40px;
      margin-right: 10px;
    }
    .header span {
      font-size: 1.5rem;
      font-weight: 700;
    }
    .signup-container {
      background-color: #161b22;
      padding: 2rem;
      border-radius: 10px;
      box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
      width: 100%;
      max-width: 400px;
      position: relative;
    }
    .signup-container h2 {
      margin-bottom: 1.5rem;
      text-align: center;
    }
    .signup-container .form-group {
      margin-bottom: 1rem;
    }
    .signup-container .form-control {
      background-color: #21262d;
      border: none;
      color: #c9d1d9;
    }
    .signup-container .form-control:focus {
      background-color: #21262d;
      color: #c9d1d9;
      border-color: #58a6ff;
      box-shadow: none;
    }
    .signup-container .btn-primary {
      background-color: #58a6ff;
      border: none;
      width: 100%;
    }
    .signup-container .btn-primary:hover {
      background-color: #007bff;
    }
    .signup-container .login-link {
      display: block;
      text-align: center;
      margin-top: 1rem;
      color: #58a6ff;
      text-decoration: none;
    }
    .signup-container .login-link:hover {
      color: #007bff;
    }
    .error-message {
      display: none;
      background-color: #ff4d4d;
      color: white;
      padding: 0.5rem;
      border-radius: 5px;
      margin-bottom: 1rem;
      text-align: center;
    }
    .success-message {
      display: none;
      background-color: #28a745;
      color: white;
      padding: 0.5rem;
      border-radius: 5px;
      margin-bottom: 1rem;
      text-align: center;
    }
    .light-theme {
      background-color: #f0f0f0;
      color: #000000;
    }
    .light-theme .signup-container {
      background-color: #e0e0e0;
    }
    .light-theme .form-control {
      background-color: #d0d0d0;
      color: #000000;
    }
    .light-theme .form-control:focus {
      background-color: #d0d0d0;
      color: #000000;
      border-color: #007bff;
    }
    .light-theme .btn-primary {
      background-color: #007bff;
    }
    .light-theme .btn-primary:hover {
      background-color: #0056b3;
    }
    .light-theme .login-link {
      color: #007bff;
    }
    .light-theme .login-link:hover {
      color: #0056b3;
    }
    .light-theme .error-message {
      background-color: #ff4d4d;
      color: white;
    }
    .light-theme .success-message {
      background-color: #28a745;
      color: white;
    }
  </style>
</head>
<body>
<div class="header">
  <img alt="Crypto Planet Logo" src="https://placehold.co/40x40"/>
  <span>
    Crypto Planet
   </span>
</div>
<div class="signup-container">
  <h2>
    Sign Up
  </h2>
  <div class="error-message" id="error-message" style="display: ${empty error ? 'none' : 'block'};">
    <%= request.getAttribute("error") != null ? request.getAttribute("error") : "" %>
  </div>

  <form method="post" action="/auth/register">
    <div class="form-group">
      <label for="username">
        Username
      </label>
      <input class="form-control" id="username" name="username" placeholder="Enter your username" type="text" required/>
    </div>
    <div class="form-group">
      <label for="email">
        Email
      </label>
      <input class="form-control" id="email" name="email" placeholder="Enter your email" type="email" required/>
    </div>
    <div class="form-group">
      <label for="password">
        Password
      </label>
      <input class="form-control" id="password" name="password" placeholder="Enter your password" type="password" required/>
    </div>
    <button class="btn btn-primary" type="submit">
      Sign Up
    </button>
    <a class="login-link" href="/auth/login">
      Already have an account? Login
    </a>
  </form>
</div>
<script>

  function toggleTheme() {
    document.body.classList.toggle('light-theme');
  }
</script>
</body>
</html>
