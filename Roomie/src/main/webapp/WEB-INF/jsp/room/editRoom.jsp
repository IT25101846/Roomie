<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html><html><head><title>Edit Room – Roomie</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head><body class="bg-light"><jsp:include page="../navbar.jsp"/>
<div class="container mt-4" style="max-width:600px;">
<h2>Edit Room ${room.roomNumber}</h2>
<c:if test="${error != null}"><div class="alert alert-danger">${error}</div></c:if>
<div class="card shadow"><div class="card-body">
<form method="post" action="/room/edit/${room.roomId}">
<div class="mb-3"><label class="form-label">Type</label>
<select name="type" class="form-select">
<option ${room.type=='Standard'?'selected':''}>Standard</option>
<option ${room.type=='Deluxe'?'selected':''}>Deluxe</option>
<option ${room.type=='Suite'?'selected':''}>Suite</option></select></div>
<div class="mb-3"><label class="form-label">Price Per Night (LKR)</label>
<input type="number" step="0.01" name="pricePerNight" class="form-control" value="${room.pricePerNight}" required></div>
<div class="mb-3"><label class="form-label">Capacity</label>
<input type="number" name="capacity" class="form-control" value="${room.capacity}" required></div>
<div class="mb-3"><label class="form-label">Description</label>
<textarea name="description" class="form-control" rows="2">${room.description}</textarea></div>
<div class="mb-3"><label class="form-label">Status</label>
<select name="status" class="form-select">
<option ${room.status=='AVAILABLE'?'selected':''}>AVAILABLE</option>
<option ${room.status=='BOOKED'?'selected':''}>BOOKED</option>
<option ${room.status=='MAINTENANCE'?'selected':''}>MAINTENANCE</option></select></div>
<div class="mb-3"><label class="form-label">Amenities</label>
<input type="text" name="amenities" class="form-control" value="${room.amenities}"></div>
<button type="submit" class="btn text-white" style="background:#2E5FA3;">Update Room</button>
<a href="/room/list" class="btn btn-outline-secondary ms-2">Cancel</a>
</form></div></div></div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body></html>
