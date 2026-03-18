<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<nav class="navbar navbar-expand-lg navbar-dark px-4 py-3" style="background:#1B2A4A;">
    <a class="navbar-brand fw-bold" href="/"><i class="bi bi-building-fill me-2"></i>Roomie</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#nav">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="nav">
        <ul class="navbar-nav me-auto">
            <li class="nav-item"><a class="nav-link" href="/room/list">Rooms</a></li>
            <li class="nav-item"><a class="nav-link" href="/review/all">Reviews</a></li>
        </ul>
        <ul class="navbar-nav ms-auto">
            <c:choose>
                <c:when test="${not empty sessionScope.loggedCustomer}">
                    <li class="nav-item"><a class="nav-link" href="/reservation/my">My Bookings</a></li>
                    <li class="nav-item"><a class="nav-link" href="/customer/profile">${sessionScope.loggedCustomer.firstName}</a></li>
                    <li class="nav-item"><a class="nav-link text-warning" href="/customer/logout">Logout</a></li>
                </c:when>
                <c:when test="${not empty sessionScope.loggedAdmin}">
                    <li class="nav-item"><a class="nav-link" href="/admin/dashboard">Dashboard</a></li>
                    <li class="nav-item"><a class="nav-link text-warning" href="/admin/logout">Logout</a></li>
                </c:when>
                <c:otherwise>
                    <li class="nav-item"><a class="nav-link" href="/customer/login">Login</a></li>
                    <li class="nav-item">
                        <a class="nav-link btn btn-warning btn-sm text-dark px-3 ms-2"
                           href="/customer/register">Sign Up</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</nav>