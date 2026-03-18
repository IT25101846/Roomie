<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html><html lang="en"><head><meta charset="UTF-8"><title>All Customers | Roomie</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
<style>body{background:#f0f4f8}.navbar{background:#1B2A4A!important}</style>
</head><body>
<nav class="navbar navbar-dark px-4 py-3">
  <span class="navbar-brand fw-bold"><i class="bi bi-shield-lock"></i> Roomie Admin</span>
  <a href="/admin/dashboard" class="btn btn-outline-light btn-sm">Dashboard</a>
</nav>
<div class="container mt-4">
  <h4 class="mb-3"><i class="bi bi-people"></i> All Customers</h4>
  <c:if test="${not empty success}"><div class="alert alert-success">${success}</div></c:if>
  <div class="table-responsive">
    <table class="table bg-white table-hover shadow-sm">
      <thead style="background:#1B2A4A;color:white">
        <tr><th>ID</th><th>Name</th><th>Email</th><th>Phone</th><th>Joined</th><th>Actions</th></tr>
      </thead>
      <tbody>
        <c:forEach var="c" items="${customers}">
          <tr>
            <td>${c.customerId}</td><td>${c.fullName}</td><td>${c.email}</td>
            <td>${c.phone}</td><td>${c.createdAt}</td>
            <td><a href="/customer/delete/${c.customerId}" class="btn btn-sm btn-outline-danger"
               onclick="return confirm('Delete this customer?')"><i class="bi bi-trash"></i></a></td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
  </div>
</div></body></html>
