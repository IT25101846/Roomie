<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Rooms | Roomie</title>
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@600;700&family=DM+Sans:wght@300;400;500;600&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
  <style>
    :root{--navy:#1B2A4A;--navy-light:#243562;--yellow:#F5C518;--yellow-light:#FFF9E0;--white:#ffffff;--text:#1a1a2e;--muted:#6b7280;--border:#e8eaf0;--shadow:0 4px 24px rgba(27,42,74,0.08)}
    *,*::before,*::after{box-sizing:border-box;margin:0;padding:0}
    body{font-family:'DM Sans',sans-serif;background:#ffffff;color:var(--text);min-height:100vh}

    /* Nav */
    nav{background:var(--navy);padding:0 2rem;height:64px;display:flex;align-items:center;justify-content:space-between;position:sticky;top:0;z-index:100;box-shadow:0 2px 16px rgba(27,42,74,0.18)}
    .brand{font-family:'Playfair Display',serif;color:#fff;font-size:1.4rem;font-weight:700;text-decoration:none;display:flex;align-items:center;gap:8px}
    .brand-dot{color:var(--yellow)}
    .nav-links{display:flex;align-items:center;gap:8px}
    .btn-nav{background:transparent;border:1.5px solid rgba(255,255,255,0.3);color:rgba(255,255,255,0.85);padding:6px 16px;border-radius:8px;font-family:'DM Sans',sans-serif;font-size:0.85rem;font-weight:500;cursor:pointer;text-decoration:none;transition:all 0.2s;display:inline-flex;align-items:center;gap:5px}
    .btn-nav:hover{background:rgba(255,255,255,0.12);border-color:rgba(255,255,255,0.5);color:white}
    .btn-nav-yellow{background:var(--yellow);border-color:var(--yellow);color:var(--navy);font-weight:600}
    .btn-nav-yellow:hover{background:#e8b800;border-color:#e8b800;color:var(--navy)}
    .btn-nav-danger{border-color:rgba(239,68,68,0.5);color:rgba(255,120,120,0.9)}
    .btn-nav-danger:hover{background:rgba(239,68,68,0.15);border-color:rgba(239,68,68,0.7);color:#fca5a5}

    /* Hero strip */
    .hero-strip{background:var(--navy);padding:3rem 2rem 2.5rem;position:relative;overflow:hidden}
    .hero-strip::after{content:'';position:absolute;right:-80px;top:-80px;width:320px;height:320px;background:rgba(245,197,24,0.07);border-radius:50%}
    .hero-inner{max-width:1200px;margin:0 auto;position:relative;z-index:1}
    .hero-strip h1{font-family:'Playfair Display',serif;font-size:2.2rem;color:white;margin-bottom:0.5rem}
    .hero-strip h1 span{color:var(--yellow)}
    .hero-strip p{color:rgba(255,255,255,0.6);font-size:1rem;margin-bottom:1.5rem}

    /* Filter bar */
    .filter-bar{display:flex;align-items:center;gap:10px;flex-wrap:wrap}
    .filter-select{padding:9px 36px 9px 14px;border:1.5px solid rgba(255,255,255,0.2);border-radius:10px;font-family:'DM Sans',sans-serif;font-size:0.88rem;background:rgba(255,255,255,0.1);color:white;outline:none;cursor:pointer;appearance:none;background-image:url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='12' height='8' viewBox='0 0 12 8'%3E%3Cpath d='M1 1l5 5 5-5' stroke='white' stroke-width='1.5' fill='none' stroke-linecap='round'/%3E%3C/svg%3E");background-repeat:no-repeat;background-position:right 12px center;transition:border-color 0.2s}
    .filter-select option{background:var(--navy);color:white}
    .filter-select:focus{border-color:var(--yellow)}
    .btn-filter{padding:9px 20px;background:var(--yellow);border:none;color:var(--navy);border-radius:10px;font-family:'DM Sans',sans-serif;font-size:0.88rem;font-weight:700;cursor:pointer;transition:all 0.2s;display:inline-flex;align-items:center;gap:6px}
    .btn-filter:hover{background:#e8b800;transform:translateY(-1px)}

    /* Content */
    .content{max-width:1200px;margin:0 auto;padding:2.5rem 1.5rem}
    .alert{padding:12px 16px;border-radius:12px;font-size:0.9rem;margin-bottom:1.5rem;display:flex;align-items:center;gap:8px}
    .alert-success{background:#ecfdf5;color:#065f46;border:1px solid #a7f3d0}
    .alert-danger{background:#fef2f2;color:#991b1b;border:1px solid #fecaca}

    /* Room grid */
    .room-grid{display:grid;grid-template-columns:repeat(auto-fill,minmax(340px,1fr));gap:1.5rem}
    .room-card{background:var(--white);border-radius:20px;border:1px solid var(--border);box-shadow:var(--shadow);overflow:hidden;transition:transform 0.25s,box-shadow 0.25s;animation:fadeUp 0.5s ease both}
    .room-card:hover{transform:translateY(-5px);box-shadow:0 14px 44px rgba(27,42,74,0.14)}
    .room-card:nth-child(2){animation-delay:0.07s}
    .room-card:nth-child(3){animation-delay:0.14s}
    .room-card:nth-child(4){animation-delay:0.21s}
    .room-card:nth-child(5){animation-delay:0.28s}
    .room-card:nth-child(6){animation-delay:0.35s}
    .card-image-placeholder{height:160px;background:linear-gradient(135deg,var(--navy) 0%,var(--navy-light) 100%);display:flex;align-items:center;justify-content:center;position:relative;overflow:hidden}
    .card-image-placeholder::before{content:'';position:absolute;bottom:-30px;right:-30px;width:140px;height:140px;background:rgba(245,197,24,0.1);border-radius:50%}
    .card-image-placeholder i{font-size:3.5rem;color:rgba(255,255,255,0.2);position:relative;z-index:1}
    .room-number-chip{position:absolute;top:14px;left:14px;background:var(--yellow);color:var(--navy);font-weight:700;font-size:0.85rem;padding:5px 12px;border-radius:20px;font-family:'DM Sans',sans-serif;z-index:2}
    .status-chip{position:absolute;top:14px;right:14px;font-weight:600;font-size:0.75rem;padding:5px 12px;border-radius:20px;z-index:2}
    .status-available{background:#dcfce7;color:#166534}
    .status-booked{background:#fecaca;color:#991b1b}
    .status-maintenance{background:#fef9c3;color:#854d0e}
    .card-body{padding:1.4rem 1.5rem 1.5rem}
    .card-type-row{display:flex;align-items:center;justify-content:space-between;margin-bottom:0.75rem}
    .room-type{font-weight:700;font-size:1.05rem;color:var(--navy)}
    .room-capacity{font-size:0.82rem;color:var(--muted);display:flex;align-items:center;gap:4px}
    .room-desc{font-size:0.88rem;color:#6b7280;line-height:1.55;margin-bottom:0.75rem;display:-webkit-box;-webkit-line-clamp:2;-webkit-box-orient:vertical;overflow:hidden}
    .amenities{font-size:0.82rem;color:var(--muted);display:flex;align-items:center;gap:5px;margin-bottom:1.2rem;overflow:hidden;text-overflow:ellipsis;white-space:nowrap}
    .card-footer{display:flex;align-items:center;justify-content:space-between;padding-top:1rem;border-top:1px solid var(--border)}
    .price{font-family:'Playfair Display',serif;font-size:1.3rem;font-weight:700;color:var(--navy)}
    .price small{font-family:'DM Sans',sans-serif;font-size:0.75rem;font-weight:400;color:var(--muted)}
    .card-actions{display:flex;gap:6px;align-items:center}
    .btn-icon{width:34px;height:34px;border-radius:9px;display:inline-flex;align-items:center;justify-content:center;border:1.5px solid var(--border);background:transparent;color:var(--muted);text-decoration:none;transition:all 0.18s;font-size:0.85rem}
    .btn-icon:hover{border-color:var(--navy);color:var(--navy);background:rgba(27,42,74,0.05)}
    .btn-icon-danger:hover{border-color:#dc2626;color:#dc2626;background:#fef2f2}
    .btn-book{background:var(--navy);color:white;border:none;padding:8px 18px;border-radius:9px;font-family:'DM Sans',sans-serif;font-size:0.85rem;font-weight:600;cursor:pointer;text-decoration:none;transition:all 0.2s;display:inline-flex;align-items:center;gap:5px}
    .btn-book:hover{background:var(--navy-light);transform:translateY(-1px)}

    /* Empty */
    .empty-state{text-align:center;padding:5rem 2rem;color:var(--muted)}
    .empty-icon{font-size:4rem;color:var(--border);margin-bottom:1rem;display:block}
    .empty-state h3{font-size:1.3rem;color:var(--navy);margin-bottom:0.5rem}

    @keyframes fadeUp{from{opacity:0;transform:translateY(18px)}to{opacity:1;transform:translateY(0)}}
  </style>
</head>
<body>

<nav>
  <a href="/" class="brand"><i class="bi bi-building"></i> Roomie<span class="brand-dot">.</span></a>
  <div class="nav-links">
    <a href="/" class="btn-nav"><i class="bi bi-house"></i> Home</a>
    <c:choose>
      <c:when test="${not empty sessionScope.loggedAdmin}">
        <a href="/review/moderate" class="btn-nav"><i class="bi bi-chat-square-check"></i> Reviews</a>
      </c:when>
      <c:otherwise>
        <a href="/review/submit" class="btn-nav"><i class="bi bi-star"></i> Review</a>
      </c:otherwise>
    </c:choose>
    <a href="/reservation/book" class="btn-nav btn-nav-yellow"><i class="bi bi-calendar-check"></i> Book Now</a>
    <c:choose>
      <c:when test="${not empty sessionScope.loggedAdmin}">
        <a href="/admin/dashboard" class="btn-nav"><i class="bi bi-speedometer2"></i> Dashboard</a>
        <a href="/admin/logout"    class="btn-nav btn-nav-danger"><i class="bi bi-box-arrow-right"></i> Logout</a>
      </c:when>
      <c:otherwise>
        <a href="/customer/profile" class="btn-nav"><i class="bi bi-person"></i> Profile</a>
        <a href="/customer/logout"  class="btn-nav btn-nav-danger"><i class="bi bi-box-arrow-right"></i> Logout</a>
      </c:otherwise>
    </c:choose>
  </div>
</nav>

<!-- Hero -->
<div class="hero-strip">
  <div class="hero-inner">
    <h1>Find Your <span>Perfect Room</span></h1>
    <p>Browse our selection of comfortable and well-equipped rooms.</p>
    <form class="filter-bar" method="get" action="/room/list">
      <select name="type" class="filter-select">
        <option value="">All Types</option>
        <option value="Standard"    ${filterType=='Standard'    ? 'selected' : ''}>Standard</option>
        <option value="Deluxe"      ${filterType=='Deluxe'      ? 'selected' : ''}>Deluxe</option>
        <option value="Suite"       ${filterType=='Suite'       ? 'selected' : ''}>Suite</option>
      </select>
      <button type="submit" class="btn-filter"><i class="bi bi-funnel-fill"></i> Filter</button>
    </form>
  </div>
</div>

<div class="content">

  <c:if test="${not empty success}">
    <div class="alert alert-success"><i class="bi bi-check-circle-fill"></i> ${success}</div>
  </c:if>
  <c:if test="${not empty error}">
    <div class="alert alert-danger"><i class="bi bi-exclamation-circle-fill"></i> ${error}</div>
  </c:if>

  <c:choose>
    <c:when test="${empty rooms}">
      <div class="empty-state">
        <span class="empty-icon"><i class="bi bi-inbox"></i></span>
        <h3>No rooms found</h3>
        <p>Try adjusting your filter or check back later.</p>
      </div>
    </c:when>
    <c:otherwise>
      <div class="room-grid">
        <c:forEach var="room" items="${rooms}">
          <div class="room-card">
            <div class="card-image-placeholder">
              <span class="room-number-chip">Room ${room.roomNumber}</span>
              <span class="status-chip status-${room.status.toLowerCase()}">${room.status}</span>
              <i class="bi bi-door-open"></i>
            </div>
            <div class="card-body">
              <div class="card-type-row">
                <span class="room-type">${room.type}</span>
                <span class="room-capacity"><i class="bi bi-people-fill"></i> ${room.capacity} guests</span>
              </div>
              <p class="room-desc">${room.description}</p>
              <p class="amenities"><i class="bi bi-wifi"></i> ${room.amenities}</p>
              <div class="card-footer">
                <div class="price">LKR ${room.pricePerNight} <small>/ night</small></div>
                <div class="card-actions">
                  <a href="/room/edit/${room.roomId}"   class="btn-icon" title="Edit"><i class="bi bi-pencil"></i></a>
                  <a href="/room/delete/${room.roomId}" class="btn-icon btn-icon-danger" title="Delete"
                     onclick="return confirm('Delete this room?')"><i class="bi bi-trash"></i></a>
                  <c:if test="${room.status == 'AVAILABLE'}">
                    <a href="/reservation/book?roomId=${room.roomId}" class="btn-book">
                      <i class="bi bi-calendar-check"></i> Book
                    </a>
                  </c:if>
                </div>
              </div>
            </div>
          </div>
        </c:forEach>
      </div>
    </c:otherwise>
  </c:choose>

</div>
</body>
</html>
