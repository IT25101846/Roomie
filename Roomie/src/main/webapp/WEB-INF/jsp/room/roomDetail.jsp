<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html><html><head><title>Room Details – Roomie</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head><body class="bg-light"><jsp:include page="../navbar.jsp"/>
<div class="container mt-4" style="max-width:700px;">
<div class="card shadow">
<div class="card-header text-white" style="background:#1B2A4A;"><h4>Room ${room.roomNumber} — ${room.type}</h4></div>
<div class="card-body">
<p><strong>Price:</strong> LKR ${room.pricePerNight} / night</p>
<p><strong>Capacity:</strong> ${room.capacity} persons</p>
<p><strong>Description:</strong> ${room.description}</p>
<p><strong>Amenities:</strong> ${room.amenities}</p>
<p><strong>Status:</strong> <span class="badge ${room.status=='AVAILABLE'?'bg-success':'bg-danger'}">${room.status}</span></p>
<c:if test="${room.status == 'AVAILABLE' && sessionScope.loggedCustomer != null}">
<a href="/reservation/book/${room.roomId}" class="btn text-white mt-2" style="background:#2E5FA3;">Book This Room</a>
</c:if>
<a href="/room/list" class="btn btn-outline-secondary mt-2 ms-2">Back</a>
</div></div></div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body></html>
