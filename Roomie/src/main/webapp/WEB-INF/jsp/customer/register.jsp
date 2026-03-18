<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Register | Roomie</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
  <style>
    body { background: #f0f4f8; }
    .card { border-radius: 16px; border: none; box-shadow: 0 4px 24px rgba(0,0,0,.1); }
    .btn-primary { background: #1B2A4A; border-color: #1B2A4A; }
    .btn-primary:hover { background: #2E5FA3; border-color: #2E5FA3; }
    .brand { color: #1B2A4A; font-weight: 700; }
  </style>
</head>
<body class="d-flex align-items-center min-vh-100">
<div class="container" style="max-width:520px">
  <div class="text-center mb-4">
    <h2 class="brand"><i class="bi bi-building"></i> Roomie</h2>
    <p class="text-muted">Create your account</p>
  </div>
  <div class="card p-4">
    <c:if test="${not empty error}">
      <div class="alert alert-danger"><i class="bi bi-exclamation-circle"></i> ${error}</div>
    </c:if>
    <c:if test="${not empty success}">
      <div class="alert alert-success"><i class="bi bi-check-circle"></i> ${success}</div>
    </c:if>
    <form action="/customer/register" method="post">
      <div class="row g-3">
        <div class="col-6">
          <label class="form-label fw-semibold">First Name</label>
          <input type="text" name="firstName" class="form-control" required placeholder="John">
        </div>
        <div class="col-6">
          <label class="form-label fw-semibold">Last Name</label>
          <input type="text" name="lastName" class="form-control" required placeholder="Doe">
        </div>
        <div class="col-12">
          <label class="form-label fw-semibold">Email</label>
          <input type="email" name="email" class="form-control" required placeholder="you@email.com">
        </div>
        <div class="col-12">
          <label class="form-label fw-semibold">Password</label>
          <input type="password" name="password" class="form-control" required minlength="6">
        </div>
        <div class="col-12">
          <label class="form-label fw-semibold">Phone</label>
          <input type="text" name="phone" class="form-control" placeholder="+94771234567">
        </div>
        <div class="col-12">
          <label class="form-label fw-semibold">Address</label>
          <input type="text" name="address" class="form-control" placeholder="42 Main St, Colombo">
        </div>
        <div class="col-12">
          <button type="submit" class="btn btn-primary w-100">
            <i class="bi bi-person-plus"></i> Register
          </button>
        </div>
      </div>
    </form>
    <hr>
    <p class="text-center mb-0">Already have an account? <a href="/customer/login">Login</a></p>
  </div>
</div>
</body>
</html>
