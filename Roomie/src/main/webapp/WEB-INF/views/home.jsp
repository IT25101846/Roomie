<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Roomie – Your Perfect Stay</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
    <style>
        body { background: #f0f4f8; }
        .hero { background: linear-gradient(135deg, #1B2A4A, #2E5FA3); color: #fff; padding: 80px 0; }
        .room-card { border: none; border-radius: 14px; box-shadow: 0 2px 14px rgba(0,0,0,.09); transition: .2s; }
        .room-card:hover { transform: translateY(-4px); }
    </style>
</head>
<body>
<jsp:include page="navbar.jsp"/>

<div class="hero text-center">
    <div class="container">
        <h1 class="fw-bold display-4"><i class="bi bi-building-fill me-3"></i>Roomie</h1>
        <p class="lead mb-4">Discover comfort. Book with confidence.</p>
        <a href="/room/list" class="btn btn-warning btn-lg px-5 me-3">Browse Rooms</a>
        <a href="/customer/register" class="btn btn-outline-light btn-lg px-5">Sign Up Free</a>
    </div>
</div>

<div class="container py-5">
    <h3 class="mb-4 fw-bold">Available Rooms</h3>
    <c:choose>
        <c:when test="${empty featuredRooms}">
            <div class="alert alert-info">No rooms available right now. Check back soon!</div>
        </c:when>
        <c:otherwise>
            <div class="row g-4">
                <c:forEach var="room" items="${featuredRooms}">
                    <div class="col-md-4">
                        <div class="card room-card h-100 p-4">
                            <div class="d-flex justify-content-between align-items-start mb-2">
                                <h5 class="fw-bold mb-0">Room ${room.roomNumber}</h5>
                                <span class="badge bg-primary">${room.type}</span>
                            </div>
                            <p class="text-muted small mb-1">
                                <i class="bi bi-people me-1"></i>Capacity: ${room.capacity}
                            </p>
                            <p class="text-muted small mb-3">${room.description}</p>
                            <div class="d-flex justify-content-between align-items-center mt-auto">
                <span class="fw-bold text-success">
                  LKR ${room.pricePerNight}<small class="text-muted">/night</small>
                </span>
                                <a href="/room/detail/${room.roomId}" class="btn btn-outline-primary btn-sm">
                                    View Details
                                </a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:otherwise>
    </c:choose>
</div>

<footer class="text-center py-4" style="background:#1B2A4A;">
    <span style="color:#aaa">&copy; 2026 Roomie – SE1020 OOP Project | SLIIT</span>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>