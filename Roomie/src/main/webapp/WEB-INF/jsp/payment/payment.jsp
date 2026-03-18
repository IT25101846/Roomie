<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html><html lang="en"><head><meta charset="UTF-8"><title>Payment | Roomie</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
<style>body{background:#f0f4f8}.navbar{background:#1B2A4A!important}.card{border-radius:16px;border:none;box-shadow:0 4px 24px rgba(0,0,0,.08)}</style>
</head><body>
<nav class="navbar navbar-dark px-4 py-3">
  <span class="navbar-brand fw-bold"><i class="bi bi-building"></i> Roomie</span>
  <a href="/reservation/list" class="btn btn-outline-light btn-sm">Back to Reservations</a>
</nav>
<div class="container mt-4" style="max-width:520px">
  <c:if test="${not empty error}"><div class="alert alert-danger">${error}</div></c:if>
  <c:if test="${not empty reservation}">
    <div class="alert alert-info">
      <strong>Reservation:</strong> ${reservation.reservationId} |
      Room: ${reservation.roomId} |
      <strong>Total Due: LKR ${reservation.totalPrice}</strong>
    </div>
  </c:if>
  <div class="card p-4">
    <h4 class="mb-4"><i class="bi bi-credit-card"></i> Record Payment</h4>
    <form action="/payment/record" method="post">
      <input type="hidden" name="reservationId" value="${reservation.reservationId}">
      <div class="mb-3"><label class="form-label fw-semibold">Amount (LKR)</label>
        <input type="number" name="amount" class="form-control" value="${reservation.totalPrice}" step="0.01" required></div>
      <div class="mb-3"><label class="form-label fw-semibold">Payment Method</label>
        <select name="method" class="form-select" required>
          <option value="CASH">Cash</option>
          <option value="CREDIT_CARD">Credit Card</option>
          <option value="BANK_TRANSFER">Bank Transfer</option>
        </select></div>
      <div class="mb-3"><label class="form-label fw-semibold">Payment Date</label>
        <input type="date" name="paymentDate" class="form-control"></div>
      <button type="submit" class="btn btn-success w-100"><i class="bi bi-check-circle"></i> Record Payment</button>
    </form>
  </div>
</div></body></html>
