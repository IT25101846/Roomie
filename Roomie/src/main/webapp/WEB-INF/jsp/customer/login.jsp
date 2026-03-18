<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Login | Roomie</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
  <style>
    body { background: #f0f4f8; }
    .card { border-radius: 16px; border: none; box-shadow: 0 4px 24px rgba(0,0,0,.1); }
    .btn-primary { background: #1B2A4A; border-color: #1B2A4A; }
    .btn-primary:hover { background: #2E5FA3; }
    .brand { color: #1B2A4A; font-weight: 700; }
  </style>
</head>
<body class="d-flex align-items-center min-vh-100">
<div class="container" style="max-width:420px">
  <div class="text-center mb-4">
    <h2 class="brand"><i class="bi bi-building"></i> Roomie</h2>
    <p class="text-muted">Sign in to your account</p>
  </div>
  <div class="card p-4">
    <c:if test="${not empty error}">
      <div class="alert alert-danger"><i class="bi bi-exclamation-circle"></i> ${error}</div>
    </c:if>
    <c:if test="${not empty success}">
      <div class="alert alert-success">${success}</div>
    </c:if>
    <form action="/customer/login" method="post">
      <div class="mb-3">
        <label class="form-label fw-semibold">Email</label>
        <input type="email" name="email" class="form-control" required>
      </div>
      <div class="mb-4">
        <label class="form-label fw-semibold">Password</label>
        <input type="password" name="password" class="form-control" required>
      </div>
      <button type="submit" class="btn btn-primary w-100">
        <i class="bi bi-box-arrow-in-right"></i> Login
      </button>
    </form>
    <hr>
    <p class="text-center mb-0">No account? <a href="/customer/register">Register</a></p>
    <p class="text-center mb-0"><a href="/admin/login">Admin Login</a></p>
  </div>
</div>
</body>
</html>
