<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html><html lang="en"><head><meta charset="UTF-8"><title>Admin Login | Roomie</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
<style>body{background:#1B2A4A}.card{border-radius:16px;border:none;box-shadow:0 8px 32px rgba(0,0,0,.3)}</style>
</head><body class="d-flex align-items-center min-vh-100">
<div class="container" style="max-width:400px">
  <div class="card p-4">
    <div class="text-center mb-4">
      <h3 style="color:#1B2A4A"><i class="bi bi-shield-lock"></i> Admin Panel</h3>
      <p class="text-muted">Roomie Administration</p>
    </div>
    <c:if test="${not empty error}"><div class="alert alert-danger">${error}</div></c:if>
    <form action="/admin/login" method="post">
      <div class="mb-3"><label class="form-label fw-semibold">Username</label>
        <input type="text" name="username" class="form-control" required></div>
      <div class="mb-4"><label class="form-label fw-semibold">Password</label>
        <input type="password" name="password" class="form-control" required></div>
      <button type="submit" class="btn w-100 text-white" style="background:#1B2A4A">
        <i class="bi bi-box-arrow-in-right"></i> Login as Admin</button>
    </form>
    <hr><p class="text-center mb-0"><a href="/customer/login">Customer Login</a></p>
  </div>
</div></body></html>
