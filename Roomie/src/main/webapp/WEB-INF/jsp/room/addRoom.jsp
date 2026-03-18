<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html><html><head><title>Add Room – Roomie</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head><body class="bg-light"><jsp:include page="../navbar.jsp"/>
<div class="container mt-4" style="max-width:600px;">
<h2>Add New Room</h2>
<c:if test="${error != null}"><div class="alert alert-danger">${error}</div></c:if>
<div class="card shadow"><div class="card-body">
<form method="post" action="/room/add">
<div class="mb-3"><label class="form-label">Room Number</label>
<input type="text" name="roomNumber" class="form-control" required></div>
<div class="mb-3"><label class="form-label">Type</label>
<select name="type" class="form-select">
<option>Standard</option><option>Deluxe</option><option>Suite</option></select></div>
<div class="mb-3"><label class="form-label">Price Per Night (LKR)</label>
<input type="number" step="0.01" name="pricePerNight" class="form-control" required></div>
<div class="mb-3"><label class="form-label">Capacity</label>
<input type="number" name="capacity" class="form-control" value="2" required></div>
<div class="mb-3"><label class="form-label">Description</label>
<textarea name="description" class="form-control" rows="2"></textarea></div>
<div class="mb-3"><label class="form-label">Amenities (pipe-separated, e.g. WiFi|AC|TV)</label>
<input type="text" name="amenities" class="form-control" placeholder="WiFi|AC|TV"></div>
<button type="submit" class="btn text-white" style="background:#2E5FA3;">Add Room</button>
<a href="/room/list" class="btn btn-outline-secondary ms-2">Cancel</a>
</form></div></div></div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body></html>
