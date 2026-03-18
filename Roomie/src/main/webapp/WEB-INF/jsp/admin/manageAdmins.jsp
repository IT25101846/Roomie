<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html><html lang="en"><head><meta charset="UTF-8"><title>Manage Admins | Roomie</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
<style>body{background:#f0f4f8}.navbar{background:#1B2A4A!important}.card{border-radius:16px;border:none;box-shadow:0 4px 24px rgba(0,0,0,.08)}</style>
</head><body>
<nav class="navbar navbar-dark px-4 py-3">
  <span class="navbar-brand fw-bold"><i class="bi bi-shield-lock"></i> Roomie Admin</span>
  <a href="/admin/dashboard" class="btn btn-outline-light btn-sm">Dashboard</a>
</nav>
<div class="container mt-4">
  <c:if test="${not empty success}"><div class="alert alert-success">${success}</div></c:if>
  <c:if test="${not empty error}"><div class="alert alert-danger">${error}</div></c:if>
  <div class="row g-4">
    <div class="col-md-4">
      <div class="card p-4">
        <h5 class="mb-3"><i class="bi bi-person-plus"></i> Add Admin</h5>
        <form action="/admin/create" method="post">
          <div class="mb-2"><input type="text" name="username" class="form-control" placeholder="Username" required></div>
          <div class="mb-2"><input type="password" name="password" class="form-control" placeholder="Password" required></div>
          <div class="mb-2"><input type="email" name="email" class="form-control" placeholder="Email" required></div>
          <div class="mb-3"><select name="role" class="form-select">
            <option value="SUPER_ADMIN">Super Admin</option>
            <option value="MANAGER">Manager</option>
          </select></div>
          <button type="submit" class="btn btn-success w-100">Create Admin</button>
        </form>
      </div>
    </div>
    <div class="col-md-8">
      <div class="card p-4">
        <h5 class="mb-3"><i class="bi bi-people"></i> All Admins</h5>
        <table class="table table-hover">
          <thead style="background:#1B2A4A;color:white">
            <tr><th>ID</th><th>Username</th><th>Email</th><th>Role</th><th>Created</th><th>Action</th></tr>
          </thead>
          <tbody>
            <c:forEach var="a" items="${admins}">
              <tr>
                <td>${a.adminId}</td><td>${a.username}</td><td>${a.email}</td>
                <td><span class="badge bg-primary">${a.role}</span></td>
                <td>${a.createdAt}</td>
                <td><a href="/admin/delete/${a.adminId}" class="btn btn-sm btn-outline-danger"
                       onclick="return confirm('Delete admin?')"><i class="bi bi-trash"></i></a></td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</div></body></html>
