<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html><html lang="en"><head><meta charset="UTF-8"><title>Book a Room | Roomie</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
<style>body{background:#f0f4f8}.navbar{background:#1B2A4A!important}.card{border-radius:16px;border:none;box-shadow:0 4px 24px rgba(0,0,0,.08)}</style>
</head><body>
<nav class="navbar navbar-dark px-4 py-3">
  <span class="navbar-brand fw-bold"><i class="bi bi-building"></i> Roomie</span>
  <div><a href="/room/list" class="btn btn-outline-light btn-sm me-2">Rooms</a>
  <a href="/customer/logout" class="btn btn-danger btn-sm">Logout</a></div>
</nav>
<div class="container mt-4" style="max-width:540px">
  <c:if test="${not empty error}"><div class="alert alert-danger">${error}</div></c:if>
  <c:if test="${not empty success}"><div class="alert alert-success">${success}</div></c:if>
  <div class="card p-4">
    <h4 class="mb-4"><i class="bi bi-calendar-check"></i> Book a Room</h4>
    <form action="/reservation/book" method="post">
      <div class="mb-3">
        <label class="form-label fw-semibold">Select Room</label>
        <select name="roomId" class="form-select" required>
          <option value="">-- Choose a room --</option>
          <c:forEach var="room" items="${rooms}">
            <option value="${room.roomId}" ${room.roomId == selectedRoomId ? 'selected' : ''}>
              Room ${room.roomNumber} - ${room.type} - LKR ${room.pricePerNight}/night
            </option>
          </c:forEach>
        </select>
      </div>
      <div class="row g-3 mb-3">
        <div class="col-6"><label class="form-label fw-semibold">Check-In</label>
          <input type="date" name="checkInDate" class="form-control" required></div>
        <div class="col-6"><label class="form-label fw-semibold">Check-Out</label>
          <input type="date" name="checkOutDate" class="form-control" required></div>
      </div>
      <div class="alert alert-info small"><i class="bi bi-info-circle"></i> Total is auto-calculated from nights x price.</div>
      <button type="submit" class="btn btn-success w-100"><i class="bi bi-check-circle"></i> Confirm Booking</button>
    </form>
  </div>
</div>
</body></html>
