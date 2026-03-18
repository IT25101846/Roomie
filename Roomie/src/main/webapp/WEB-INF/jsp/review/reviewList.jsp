<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Guest Reviews | Roomie</title>
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@600;700&family=DM+Sans:wght@300;400;500;600&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
  <style>
    :root{--navy:#1B2A4A;--navy-light:#243562;--yellow:#F5C518;--yellow-light:#FFF9E0;--white:#ffffff;--text:#1a1a2e;--muted:#6b7280;--border:#e8eaf0;--shadow:0 4px 24px rgba(27,42,74,0.08)}
    *,*::before,*::after{box-sizing:border-box;margin:0;padding:0}
    body{font-family:'DM Sans',sans-serif;background:#fff;color:var(--text)}
    nav{background:var(--navy);padding:0 2rem;height:64px;display:flex;align-items:center;justify-content:space-between;position:sticky;top:0;z-index:100;box-shadow:0 2px 16px rgba(27,42,74,0.18)}
    .brand{font-family:'Playfair Display',serif;color:#fff;font-size:1.4rem;font-weight:700;text-decoration:none;display:flex;align-items:center;gap:8px}
    .brand-dot{color:var(--yellow)}
    .btn-nav{background:transparent;border:1.5px solid rgba(255,255,255,0.3);color:rgba(255,255,255,0.85);padding:6px 16px;border-radius:8px;font-family:'DM Sans',sans-serif;font-size:0.85rem;font-weight:500;cursor:pointer;text-decoration:none;transition:all 0.2s;display:inline-flex;align-items:center;gap:5px}
    .btn-nav:hover{background:rgba(255,255,255,0.12);border-color:rgba(255,255,255,0.5);color:white}
    .btn-nav-yellow{background:var(--yellow);border-color:var(--yellow);color:var(--navy);font-weight:600}
    .btn-nav-yellow:hover{background:#e8b800;border-color:#e8b800;color:var(--navy)}
    .hero{background:var(--navy);color:white;padding:4rem 2rem 3rem;text-align:center;position:relative;overflow:hidden}
    .hero::before{content:'';position:absolute;top:-60px;right:-60px;width:300px;height:300px;background:rgba(245,197,24,0.08);border-radius:50%}
    .hero::after{content:'';position:absolute;bottom:-80px;left:-40px;width:250px;height:250px;background:rgba(245,197,24,0.05);border-radius:50%}
    .hero h1{font-family:'Playfair Display',serif;font-size:2.6rem;font-weight:700;margin-bottom:0.5rem;position:relative;z-index:1}
    .hero h1 span{color:var(--yellow)}
    .hero p{color:rgba(255,255,255,0.65);font-size:1.05rem;position:relative;z-index:1}
    .hero-stars{color:var(--yellow);font-size:1.4rem;margin-bottom:1rem;letter-spacing:4px;position:relative;z-index:1}
    .content{max-width:1200px;margin:0 auto;padding:3rem 1.5rem}
    .review-grid{display:grid;grid-template-columns:repeat(auto-fill,minmax(320px,1fr));gap:1.5rem}
    .review-card{background:var(--white);border-radius:18px;padding:1.75rem;border:1px solid var(--border);box-shadow:var(--shadow);transition:transform 0.25s,box-shadow 0.25s;animation:fadeUp 0.5s ease both;position:relative;overflow:hidden}
    .review-card::before{content:'\201C';position:absolute;top:-10px;right:20px;font-size:7rem;color:rgba(245,197,24,0.12);font-family:Georgia,serif;line-height:1;pointer-events:none}
    .review-card:hover{transform:translateY(-4px);box-shadow:0 12px 40px rgba(27,42,74,0.13)}
    .review-card:nth-child(2){animation-delay:0.07s}
    .review-card:nth-child(3){animation-delay:0.14s}
    .review-card:nth-child(4){animation-delay:0.21s}
    .card-top{display:flex;justify-content:space-between;align-items:flex-start;margin-bottom:1rem}
    .reservation-badge{background:rgba(27,42,74,0.07);color:var(--navy);font-size:0.78rem;font-weight:600;padding:4px 10px;border-radius:20px}
    .stars{display:flex;gap:2px}
    .star-filled{color:var(--yellow);font-size:1rem}
    .star-empty{color:#d1d5db;font-size:1rem}
    .review-comment{font-size:0.95rem;color:#374151;line-height:1.65;margin-bottom:1.25rem}
    .card-footer-row{display:flex;align-items:center;justify-content:space-between;padding-top:0.75rem;border-top:1px solid var(--border)}
    .review-date{font-size:0.8rem;color:var(--muted);display:flex;align-items:center;gap:5px}
    .empty-state{text-align:center;padding:5rem 2rem;color:var(--muted)}
    .empty-icon{font-size:4rem;color:var(--border);margin-bottom:1rem;display:block}
    .empty-state h3{font-size:1.3rem;color:var(--navy);margin-bottom:0.5rem}
    @keyframes fadeUp{from{opacity:0;transform:translateY(18px)}to{opacity:1;transform:translateY(0)}}
  </style>
</head>
<body>
<nav>
  <a href="/" class="brand"><i class="bi bi-building"></i> Roomie<span class="brand-dot">.</span></a>
  <div>
    <c:if test="${not empty sessionScope.loggedCustomer}">
      <a href="/review/submit" class="btn-nav btn-nav-yellow"><i class="bi bi-plus-lg"></i> Write Review</a>
    </c:if>
  </div>
</nav>
<div class="hero">
  <div class="hero-stars">&#9733;&#9733;&#9733;&#9733;&#9733;</div>
  <h1>What Our <span>Guests Say</span></h1>
  <p>Real experiences from real guests — verified and honest.</p>
</div>
<div class="content">
  <c:choose>
    <c:when test="${empty reviews}">
      <div class="empty-state">
        <span class="empty-icon"><i class="bi bi-chat-square"></i></span>
        <h3>No reviews yet</h3>
        <p>Be the first to share your experience with us.</p>
      </div>
    </c:when>
    <c:otherwise>
      <div class="review-grid">
        <c:forEach var="r" items="${reviews}">
          <div class="review-card">
            <div class="card-top">
              <span class="reservation-badge"><i class="bi bi-door-open"></i> ${r.reservationId}</span>
              <div class="stars">
                <c:forEach begin="1" end="${r.rating}"><i class="bi bi-star-fill star-filled"></i></c:forEach>
                <c:forEach begin="${r.rating + 1}" end="5"><i class="bi bi-star star-empty"></i></c:forEach>
              </div>
            </div>
            <p class="review-comment">${r.comment}</p>
            <div class="card-footer-row">
              <span class="review-date"><i class="bi bi-calendar3"></i> ${r.feedbackDate}</span>
            </div>
          </div>
        </c:forEach>
      </div>
    </c:otherwise>
  </c:choose>
</div>
</body>
</html>
