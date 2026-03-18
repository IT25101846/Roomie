<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html><html lang="en"><head><meta charset="UTF-8"><title>Reservations | Roomie</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
<style>body{background:#f0f4f8}.navbar{background:#1B2A4A!important}</style>
</head><body>
<nav class="navbar navbar-dark px-4 py-3">
  <span class="navbar-brand fw-bold"><i class="bi bi-building"></i> Roomie</span>
  <div><a href="/reservation/book" class="btn btn-warning btn-sm me-2">+ New Booking</a>
  <a href="/customer/logout" class="btn btn-danger btn-sm">Logout</a></div>
</nav>
<div class="container mt-4">
  <h4 class="mb-3"><i class="bi bi-list-check"></i> My Reservations</h4>
  <c:if test="${not empty success}"><div class="alert alert-success">${success}</div></c:if>
  <c:if test="${not empty error}"><div class="alert alert-danger">${error}</div></c:if>
  <c:choose>
    <c:when test="${empty reservations}">
      <div class="text-center py-5 text-muted"><i class="bi bi-calendar-x fs-1"></i>
        <p>No reservations yet. <a href="/reservation/book">Book now!</a></p></div>
    </c:when>
    <c:otherwise>
      <div class="table-responsive">
        <table class="table bg-white table-hover rounded shadow-sm">
          <thead style="background:#1B2A4A;color:white">
            <tr><th>ID</th><th>Room</th><th>Check-In</th><th>Check-Out</th><th>Total (LKR)</th><th>Status</th><th>Actions</th></tr>
          </thead>
          <tbody>
            <c:forEach var="r" items="${reservations}">
              <tr>
                <td>${r.reservationId}</td><td>${r.roomId}</td>
                <td>${r.checkInDate}</td><td>${r.checkOutDate}</td><td>${r.totalPrice}</td>
                <td><span class="badge ${r.status=='CONFIRMED'?'bg-success':r.status=='CANCELLED'?'bg-danger':'bg-warning'}">${r.status}</span></td>
                <td>
                  <a href="/reservation/edit/${r.reservationId}" class="btn btn-sm btn-outline-secondary me-1">Edit</a>
                  <a href="/payment/form/${r.reservationId}" class="btn btn-sm btn-outline-success me-1">Pay</a>
                  <c:if test="${r.status != 'CANCELLED'}">
                    <a href="/reservation/cancel/${r.reservationId}" class="btn btn-sm btn-outline-danger"
                       onclick="return confirm('Cancel this booking?')">Cancel</a>
                  </c:if>
                </td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>
    </c:otherwise>
  </c:choose>
</div></body></html>
