<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html><html lang="en"><head><meta charset="UTF-8"><title>Invoice | Roomie</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
<style>body{background:#f0f4f8}@media print{.no-print{display:none}.card{box-shadow:none}}</style>
</head><body>
<div class="container mt-4" style="max-width:600px">
  <div class="no-print mb-3 d-flex gap-2">
    <a href="/payment/history" class="btn btn-outline-secondary btn-sm">Back</a>
    <button onclick="window.print()" class="btn btn-primary btn-sm" style="background:#1B2A4A;border-color:#1B2A4A">
      <i class="bi bi-printer"></i> Print
    </button>
  </div>
  <div class="card p-4" style="border-radius:16px;border:none;box-shadow:0 4px 24px rgba(0,0,0,.08)">
    <div class="d-flex justify-content-between align-items-start mb-4">
      <div><h3 style="color:#1B2A4A"><i class="bi bi-building"></i> Roomie</h3>
        <p class="text-muted mb-0">Hotel Room Reservation System</p></div>
      <div class="text-end"><h5>INVOICE</h5>
        <span class="badge bg-success fs-6">${payment.status}</span></div>
    </div>
    <hr>
    <div class="row mb-3">
      <div class="col-6"><p><strong>Payment ID:</strong> ${payment.paymentId}<br>
        <strong>Date:</strong> ${payment.paymentDate}<br>
        <strong>Method:</strong> ${payment.method}</p></div>
      <div class="col-6"><p><strong>Reservation:</strong> ${reservation.reservationId}<br>
        <strong>Check-In:</strong> ${reservation.checkInDate}<br>
        <strong>Check-Out:</strong> ${reservation.checkOutDate}</p></div>
    </div>
    <hr>
    <div class="d-flex justify-content-between align-items-center mt-3">
      <h5>Total Amount</h5>
      <h4 style="color:#1B2A4A">LKR ${payment.amount}</h4>
    </div>
    <p class="text-muted small mt-3">Thank you for choosing Roomie. We hope to see you again!</p>
  </div>
</div></body></html>
