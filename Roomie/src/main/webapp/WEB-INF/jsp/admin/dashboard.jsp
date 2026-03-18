<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html><html lang="en"><head><meta charset="UTF-8"><title>Admin Dashboard | Roomie</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
<style>
  body{background:#f0f4f8}.navbar{background:#1B2A4A!important}
  .stat-card{border-radius:14px;border:none;box-shadow:0 2px 12px rgba(0,0,0,.08);transition:transform .2s}
  .stat-card:hover{transform:translateY(-4px)}
</style>
</head><body>
<nav class="navbar navbar-dark px-4 py-3">
  <span class="navbar-brand fw-bold"><i class="bi bi-shield-lock"></i> Roomie Admin</span>
  <div>
    <a href="/customer/list" class="btn btn-outline-light btn-sm me-2">Customers</a>
    <a href="/room/list" class="btn btn-outline-light btn-sm me-2">Rooms</a>
    <a href="/reservation/all" class="btn btn-outline-light btn-sm me-2">Reservations</a>
    <a href="/review/moderate" class="btn btn-outline-light btn-sm me-2">Reviews</a>
    <a href="/admin/manage" class="btn btn-outline-light btn-sm me-2">Admins</a>
    <a href="/admin/logout" class="btn btn-danger btn-sm">Logout</a>
  </div>
</nav>
<div class="container mt-4">
  <h4 class="mb-4"><i class="bi bi-speedometer2"></i> Dashboard Overview</h4>
  <div class="row g-4">
    <div class="col-md-3">
      <div class="card stat-card p-4 text-center">
        <i class="bi bi-people fs-1 mb-2" style="color:#2E5FA3"></i>
        <h2>${totalCustomers}</h2><p class="text-muted mb-0">Customers</p>
      </div>
    </div>
    <div class="col-md-3">
      <div class="card stat-card p-4 text-center">
        <i class="bi bi-door-open fs-1 mb-2" style="color:#198754"></i>
        <h2>${totalRooms}</h2><p class="text-muted mb-0">Rooms</p>
      </div>
    </div>
    <div class="col-md-3">
      <div class="card stat-card p-4 text-center">
        <i class="bi bi-calendar-check fs-1 mb-2" style="color:#fd7e14"></i>
        <h2>${totalReservations}</h2><p class="text-muted mb-0">Reservations</p>
      </div>
    </div>
    <div class="col-md-3">
      <div class="card stat-card p-4 text-center">
        <i class="bi bi-credit-card fs-1 mb-2" style="color:#dc3545"></i>
        <h2>${totalPayments}</h2><p class="text-muted mb-0">Payments</p>
      </div>
    </div>
  </div>
  <div class="row g-4 mt-2">
    <div class="col-md-4">
      <div class="card p-3 h-100" style="border-radius:14px;border:none;box-shadow:0 2px 12px rgba(0,0,0,.08)">
        <h6 class="fw-bold mb-3">Quick Actions</h6>
        <div class="d-grid gap-2">
          <a href="/room/add" class="btn btn-outline-success btn-sm"><i class="bi bi-plus"></i> Add New Room</a>
          <a href="/admin/manage" class="btn btn-outline-secondary btn-sm"><i class="bi bi-person-gear"></i> Manage Admins</a>
          <a href="/review/moderate" class="btn btn-outline-primary btn-sm"><i class="bi bi-chat-square-check"></i> Moderate Reviews</a>
        </div>
      </div>
    </div>
  </div>
</div></body></html>
