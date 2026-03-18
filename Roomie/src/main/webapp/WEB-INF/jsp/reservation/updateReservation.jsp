<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html><html lang="en"><head><meta charset="UTF-8"><title>Update Reservation | Roomie</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
<style>body{background:#f0f4f8}.navbar{background:#1B2A4A!important}.card{border-radius:16px;border:none;box-shadow:0 4px 24px rgba(0,0,0,.08)}</style>
</head><body>
<nav class="navbar navbar-dark px-4 py-3">
  <span class="navbar-brand fw-bold"><i class="bi bi-building"></i> Roomie</span>
  <a href="/reservation/list" class="btn btn-outline-light btn-sm">Back</a>
</nav>
<div class="container mt-4" style="max-width:520px">
  <div class="card p-4">
    <h4 class="mb-4"><i class="bi bi-pencil-square"></i> Update Reservation</h4>
    <form action="/reservation/edit" method="post">
      <input type="hidden" name="reservationId" value="${reservation.reservationId}">
      <input type="hidden" name="customerId" value="${reservation.customerId}">
      <input type="hidden" name="roomId" value="${reservation.roomId}">
      <input type="hidden" name="totalPrice" value="${reservation.totalPrice}">
      <input type="hidden" name="createdAt" value="${reservation.createdAt}">
      <div class="mb-3"><label class="form-label fw-semibold">Reservation ID</label>
        <input class="form-control" value="${reservation.reservationId}" disabled></div>
      <div class="row g-3 mb-3">
        <div class="col-6"><label class="form-label fw-semibold">Check-In</label>
          <input type="date" name="checkInDate" value="${reservation.checkInDate}" class="form-control" required></div>
        <div class="col-6"><label class="form-label fw-semibold">Check-Out</label>
          <input type="date" name="checkOutDate" value="${reservation.checkOutDate}" class="form-control" required></div>
      </div>
      <div class="mb-3"><label class="form-label fw-semibold">Status</label>
        <select name="status" class="form-select">
          <option ${reservation.status=='CONFIRMED'?'selected':''}>CONFIRMED</option>
          <option ${reservation.status=='PENDING'?'selected':''}>PENDING</option>
          <option ${reservation.status=='CANCELLED'?'selected':''}>CANCELLED</option>
        </select></div>
      <button type="submit" class="btn btn-primary w-100" style="background:#1B2A4A;border-color:#1B2A4A">
        <i class="bi bi-save"></i> Update</button>
    </form>
  </div>
</div></body></html>
