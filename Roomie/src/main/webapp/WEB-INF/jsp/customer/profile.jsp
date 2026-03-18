<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html><html><head><title>My Profile – Roomie</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head><body class="bg-light"><jsp:include page="../navbar.jsp"/>
<div class="container mt-4" style="max-width:600px;">
<h2>My Profile</h2>
<c:if test="${param.updated == 'true'}"><div class="alert alert-success">Profile updated!</div></c:if>
<c:if test="${error != null}"><div class="alert alert-danger">${error}</div></c:if>
<div class="card shadow mb-3"><div class="card-body">
<p><strong>ID:</strong> ${customer.customerId}</p>
<p><strong>Email:</strong> ${customer.email}</p>
<p><strong>Since:</strong> ${customer.createdAt}</p>
</div></div>
<div class="card shadow"><div class="card-header text-white" style="background:#1B2A4A;"><b>Update Details</b></div>
<div class="card-body">
<form method="post" action="/customer/profile/update">
<div class="row"><div class="col mb-3"><label class="form-label">First Name</label>
<input type="text" name="firstName" class="form-control" value="${customer.firstName}" required></div>
<div class="col mb-3"><label class="form-label">Last Name</label>
<input type="text" name="lastName" class="form-control" value="${customer.lastName}" required></div></div>
<div class="mb-3"><label class="form-label">Phone</label>
<input type="text" name="phone" class="form-control" value="${customer.phone}"></div>
<div class="mb-3"><label class="form-label">Address</label>
<textarea name="address" class="form-control" rows="2">${customer.address}</textarea></div>
<button type="submit" class="btn text-white" style="background:#2E5FA3;">Save Changes</button>
</form></div></div></div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body></html>
