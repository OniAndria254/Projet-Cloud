<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8"/>
  <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
  <title>
    Crypto Planet - PIN Confirmation
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
      justify-content: center;
      align-items: center;
      height: 100vh;
    }
    .pin-container {
      background-color: #161b22;
      padding: 2rem;
      border-radius: 10px;
      box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
      width: 100%;
      max-width: 400px;
      position: relative;
    }
    .pin-container h2 {
      margin-bottom: 1.5rem;
      text-align: center;
    }
    .pin-container .form-group {
      margin-bottom: 1rem;
    }
    .pin-container .form-control {
      background-color: #21262d;
      border: none;
      color: #c9d1d9;
      text-align: center;
      font-size: 1.5rem;
    }
    .pin-container .form-control:focus {
      background-color: #21262d;
      color: #c9d1d9;
      border-color: #58a6ff;
      box-shadow: none;
    }
    .pin-container .btn-primary {
      background-color: #58a6ff;
      border: none;
      width: 100%;
      margin-bottom: 1rem;
    }
    .pin-container .btn-primary:hover {
      background-color: #007bff;
    }
    .pin-container .btn-secondary {
      background-color: #6c757d;
      border: none;
      width: 100%;
    }
    .pin-container .btn-secondary:hover {
      background-color: #5a6268;
    }
    .pin-container .resend-link {
      display: block;
      text-align: center;
      margin-top: 1rem;
      color: #58a6ff;
      text-decoration: none;
    }
    .pin-container .resend-link:hover {
      color: #007bff;
    }
    .light-theme {
      background-color: #f0f0f0;
      color: #000000;
    }
    .light-theme .pin-container {
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
    .light-theme .btn-secondary {
      background-color: #6c757d;
    }
    .light-theme .btn-secondary:hover {
      background-color: #5a6268;
    }
    .light-theme .resend-link {
      color: #007bff;
    }
    .light-theme .resend-link:hover {
      color: #0056b3;
    }
  </style>
</head>
<body>
<div class="pin-container">
  <h2>
    PIN Confirmation
  </h2>
  <form action="/auth/confirm-pin" method="post">
    <div class="form-group">
      <center>
      <label for="pin">
        Enter your 6-digit PIN
      </label>
      </center>
      <br>
      <input type="hidden" name="user_id" value="${user_id}">
      <input class="form-control" id="pin" name="pin" maxlength="6" placeholder="Enter PIN" type="number" required/>
    </div>
    <button class="btn btn-primary" type="submit">
      Confirm
    </button>
    <a class="btn btn-secondary" href="#">
      Annuler
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