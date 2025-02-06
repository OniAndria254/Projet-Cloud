<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Login Page</title>
  <link rel="stylesheet" href="/assets/style.css">
</head>
<body>
<<<<<<< Updated upstream
=======
<div class="header">
  <img alt="Crypto Planet Logo" src="https://placehold.co/40x40"/>
  <span>
    Crypto Planet
   </span>
</div>
<div class="login-container">
  <h2>
    Login user
  </h2>
>>>>>>> Stashed changes


  <div class="login-container">
    <div class="logo-container">
      <img src="/assets/img/cryptoz-favicon.png" alt="Logo" class="logo">
      <h1>CRYPTO</h1>
    </div>
    <h2>Login</h2>
    <form action="/login" method="post">
      <div class="form-group">
        <label for="email">Email</label>
        <input type="email" id="email" name="email" required>
      </div>
      <div class="form-group">
        <label for="password">Password</label>
        <input type="password" id="password" name="password" required>
      </div>
      <button type="submit" class="login-button">
        <span>Login</span>
      </button>
    </form>
    <p>Don't have an account? <a href="/register">Sign Up</a></p>
  </div>
<<<<<<< Updated upstream
=======
  <form action="/auth/login" method="post">
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
      Login
    </button>
    <a class="signup-link" href="/auth/register">
      S'inscrire?
    </a>
  </form>
</div>

<hr>

<div class="login-container">
  <h2>
    Login admin
  </h2>

  <form action="/auth/loginAdmin" method="post">
    <div class="form-group">
      <label for="email2">
        Email
      </label>
      <input class="form-control" id="email2" name="email" placeholder="Enter your email" type="email" value="admin@gmail.com" required/>
    </div>
    <div class="form-group">
      <label for="password2">
        Password
      </label>
      <input class="form-control" id="password2" name="password" placeholder="Enter your password" type="password" value="admin123" required/>
    </div>
    <button class="btn btn-primary" type="submit">
      Login
    </button>
  </form>
</div>
<script>
  function toggleTheme() {
    document.body.classList.toggle('light-theme');
  }
</script>
>>>>>>> Stashed changes
</body>
</html>
