<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html><html lang="en"><head><meta charset="UTF-8"><title>Payment History | Roomie</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
<style>body{background:#f0f4f8}.navbar{background:#1B2A4A!important}</style>
</head><body>
<nav class="navbar navbar-dark px-4 py-3">
  <span class="navbar-brand fw-bold"><i class="bi bi-building"></i> Roomie</span>
  <div><a href="/reservation/list" class="btn btn-outline-light btn-sm me-2">My Bookings</a>
  <a href="/customer/logout" class="btn btn-danger btn-sm">Logout</a></div>
</nav>
<div class="container mt-4">
  <h4 class="mb-3"><i class="bi bi-receipt"></i> Payment History</h4>
  <c:if test="${not empty success}"><div class="alert alert-success">${success}</div></c:if>
  <c:choose>
    <c:when test="${empty payments}">
      <div class="text-center py-5 text-muted"><i class="bi bi-receipt fs-1"></i><p>No payments yet.</p></div>
    </c:when>
    <c:otherwise>
      <div class="table-responsive">
        <table class="table bg-white table-hover shadow-sm">
          <thead style="background:#1B2A4A;color:white">
            <tr><th>Payment ID</th><th>Reservation</th><th>Amount (LKR)</th><th>Date</th><th>Method</th><th>Status</th><th>Actions</th></tr>
          </thead>
          <tbody>
            <c:forEach var="p" items="${payments}">
              <tr>
                <td>${p.paymentId}</td><td>${p.reservationId}</td>
                <td>${p.amount}</td><td>${p.paymentDate}</td><td>${p.method}</td>
                <td><span class="badge ${p.status=='COMPLETED'?'bg-success':'bg-warning'}">${p.status}</span></td>
                <td>
                  <a href="/payment/invoice/${p.paymentId}" class="btn btn-sm btn-outline-primary me-1">Invoice</a>
                  <a href="/payment/delete/${p.paymentId}" class="btn btn-sm btn-outline-danger"
                     onclick="return confirm('Delete this payment record?')">Delete</a>
                </td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>
    </c:otherwise>
  </c:choose>
</div></body></html>
