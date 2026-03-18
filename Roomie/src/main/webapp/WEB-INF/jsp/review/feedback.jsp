<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Leave a Review | Roomie</title>
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@600;700&family=DM+Sans:wght@300;400;500;600&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
  <style>
    :root {
      --navy: #1B2A4A;
      --navy-light: #243562;
      --yellow: #F5C518;
      --yellow-light: #FFF3CD;
      --white: #ffffff;
      --text: #1a1a2e;
      --muted: #6b7280;
      --border: #e8eaf0;
      --shadow: 0 4px 24px rgba(27,42,74,0.10);
      --shadow-hover: 0 8px 40px rgba(27,42,74,0.18);
    }
    *, *::before, *::after { box-sizing: border-box; margin: 0; padding: 0; }
    body {
      font-family: 'DM Sans', sans-serif;
      background: #f7f8fc;
      color: var(--text);
      min-height: 100vh;
    }

    /* ── Navbar ── */
    nav {
      background: var(--navy);
      padding: 0 2rem;
      height: 64px;
      display: flex;
      align-items: center;
      justify-content: space-between;
      position: sticky;
      top: 0;
      z-index: 100;
      box-shadow: 0 2px 16px rgba(27,42,74,0.18);
    }
    .brand {
      font-family: 'DM Sans',sans-serif;
      color: var(--white);
      font-size: 1.4rem;
      font-weight: 700;
      letter-spacing: -0.3px;
      display: flex;
      align-items: center;
      gap: 8px;
      text-decoration: none;
    }
    .brand-dot { color: var(--yellow); }
    .nav-links { display: flex; align-items: center; gap: 8px; }
    .btn-nav {
      background: transparent;
      border: 1.5px solid rgba(255,255,255,0.3);
      color: rgba(255,255,255,0.85);
      padding: 6px 16px;
      border-radius: 8px;
      font-family: 'DM Sans', sans-serif;
      font-size: 0.85rem;
      font-weight: 500;
      cursor: pointer;
      text-decoration: none;
      transition: all 0.2s;
      display: inline-flex;
      align-items: center;
      gap: 5px;
    }
    .btn-nav:hover { background: rgba(255,255,255,0.12); border-color: rgba(255,255,255,0.5); color: white; }
    .btn-nav-yellow {
      background: var(--yellow);
      border-color: var(--yellow);
      color: var(--navy);
      font-weight: 600;
    }
    .btn-nav-yellow:hover { background: #e8b800; border-color: #e8b800; color: var(--navy); }
    .btn-nav-danger { border-color: rgba(239,68,68,0.5); color: rgba(255,120,120,0.9); }
    .btn-nav-danger:hover { background: rgba(239,68,68,0.15); border-color: rgba(239,68,68,0.7); color: #fca5a5; }

    /* ── Main Layout ── */
    .page-wrapper {
      min-height: calc(100vh - 64px);
      background: #f7f8fc;
      display: flex;
      align-items: flex-start;
      justify-content: center;
      padding: 3rem 1rem;
    }
    .form-container { width: 100%; max-width: 540px; }

    /* ── Page Header ── */
    .page-header {
      margin-bottom: 2rem;
      animation: fadeUp 0.5s ease both;
    }
    .page-header h1 {
      font-family: 'DM Sans',sans-serif;
      font-size: 2rem;
      font-weight: 700;
      color: var(--navy);
      margin-bottom: 4px;
    }
    .page-header p { color: var(--muted); font-size: 0.95rem; }

    /* ── Card ── */
    .card {
      background: var(--white);
      border-radius: 20px;
      box-shadow: var(--shadow);
      padding: 2.5rem;
      animation: fadeUp 0.5s ease 0.1s both;
      border: 1px solid var(--border);
    }

    /* ── Form Elements ── */
    .form-group { margin-bottom: 1.5rem; }
    .form-label {
      display: block;
      font-weight: 600;
      font-size: 0.85rem;
      color: var(--navy);
      margin-bottom: 8px;
      text-transform: uppercase;
      letter-spacing: 0.5px;
    }
    .form-control, .form-select {
      width: 100%;
      padding: 12px 16px;
      border: 1.5px solid var(--border);
      border-radius: 12px;
      font-family: 'DM Sans', sans-serif;
      font-size: 0.95rem;
      color: var(--text);
      background: var(--white);
      transition: border-color 0.2s, box-shadow 0.2s;
      outline: none;
      appearance: none;
    }
    .form-control:focus, .form-select:focus {
      border-color: var(--navy);
      box-shadow: 0 0 0 3px rgba(27,42,74,0.08);
    }
    textarea.form-control { resize: vertical; min-height: 120px; line-height: 1.6; }
    .form-select {
      background-image: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='12' height='8' viewBox='0 0 12 8'%3E%3Cpath d='M1 1l5 5 5-5' stroke='%231B2A4A' stroke-width='1.5' fill='none' stroke-linecap='round'/%3E%3C/svg%3E");
      background-repeat: no-repeat;
      background-position: right 14px center;
      padding-right: 40px;
      cursor: pointer;
    }

    /* ── Star Rating ── */
    .star-rating {
      display: flex;
      flex-direction: row-reverse;
      gap: 4px;
      justify-content: flex-end;
    }
    .star-rating input { display: none; }
    .star-rating label {
      font-size: 2rem;
      color: #d1d5db;
      cursor: pointer;
      transition: color 0.15s, transform 0.15s;
      line-height: 1;
    }
    .star-rating label:hover,
    .star-rating label:hover ~ label,
    .star-rating input:checked ~ label { color: var(--yellow); }
    .star-rating label:hover { transform: scale(1.15); }
    .rating-hint { font-size: 0.8rem; color: var(--muted); margin-top: 6px; }

    /* ── Submit Button ── */
    .btn-submit {
      width: 100%;
      padding: 14px;
      background: var(--navy);
      color: var(--white);
      border: none;
      border-radius: 12px;
      font-family: 'DM Sans', sans-serif;
      font-size: 1rem;
      font-weight: 600;
      cursor: pointer;
      transition: all 0.25s;
      display: flex;
      align-items: center;
      justify-content: center;
      gap: 8px;
      margin-top: 0.5rem;
    }
    .btn-submit:hover {
      background: var(--navy-light);
      transform: translateY(-2px);
      box-shadow: 0 6px 20px rgba(27,42,74,0.25);
    }
    .btn-submit .icon { font-size: 1.1rem; }
    .btn-submit .yellow-bar {
      position: absolute;
      bottom: 0; left: 0; right: 0;
      height: 3px;
      background: var(--yellow);
      border-radius: 0 0 12px 12px;
      transform: scaleX(0);
      transition: transform 0.3s;
    }
    .btn-submit:hover .yellow-bar { transform: scaleX(1); }

    /* ── Alerts ── */
    .alert {
      padding: 12px 16px;
      border-radius: 12px;
      font-size: 0.9rem;
      margin-bottom: 1.5rem;
      display: flex;
      align-items: center;
      gap: 8px;
      animation: fadeUp 0.4s ease both;
    }
    .alert-success { background: #ecfdf5; color: #065f46; border: 1px solid #a7f3d0; }
    .alert-danger  { background: #fef2f2; color: #991b1b; border: 1px solid #fecaca; }

    /* ── Divider ── */
    .section-divider {
      height: 1px;
      background: var(--border);
      margin: 1.5rem 0;
    }

    @keyframes fadeUp {
      from { opacity: 0; transform: translateY(16px); }
      to   { opacity: 1; transform: translateY(0); }
    }
  </style>
</head>
<body>

<nav>
  <a href="/" class="brand"><i class="bi bi-building"></i> Roomie<span class="brand-dot">.</span></a>
  <div class="nav-links">
    <a href="/review/list" class="btn-nav"><i class="bi bi-star"></i> All Reviews</a>
    <a href="/review/my"   class="btn-nav"><i class="bi bi-person-lines-fill"></i> My Reviews</a>
    <a href="/customer/logout" class="btn-nav btn-nav-danger"><i class="bi bi-box-arrow-right"></i> Logout</a>
  </div>
</nav>

<div class="page-wrapper">
  <div class="form-container">

    <div class="page-header">
      <h1>Leave a Review</h1>
      <p>Share your experience and help others make better choices.</p>
    </div>

    <c:if test="${not empty success}">
      <div class="alert alert-success"><i class="bi bi-check-circle-fill"></i> ${success}</div>
    </c:if>
    <c:if test="${not empty error}">
      <div class="alert alert-danger"><i class="bi bi-exclamation-circle-fill"></i> ${error}</div>
    </c:if>

    <div class="card">
      <form action="/review/submit" method="post">

        <div class="form-group">
          <label class="form-label">Select Room</label>
          <select name="reservationId" class="form-select" required>
            <option value="">Choose a room&hellip;</option>
            <c:forEach var="room" items="${rooms}">
              <option value="${room.roomId}">Room ${room.roomNumber} &mdash; ${room.type}</option>
            </c:forEach>
          </select>
        </div>

        <div class="form-group">
          <label class="form-label">Your Rating</label>
          <div class="star-rating">
            <input type="radio" id="s5" name="rating" value="5"><label for="s5" title="Excellent">&#9733;</label>
            <input type="radio" id="s4" name="rating" value="4"><label for="s4" title="Good">&#9733;</label>
            <input type="radio" id="s3" name="rating" value="3"><label for="s3" title="Average">&#9733;</label>
            <input type="radio" id="s2" name="rating" value="2"><label for="s2" title="Poor">&#9733;</label>
            <input type="radio" id="s1" name="rating" value="1"><label for="s1" title="Very Poor">&#9733;</label>
          </div>
          <p class="rating-hint">Click a star to rate your stay from 1 (Very Poor) to 5 (Excellent)</p>
        </div>

        <div class="section-divider"></div>

        <div class="form-group">
          <label class="form-label">Your Comment</label>
          <textarea name="comment" class="form-control" rows="5" required
                    placeholder="Tell us about your stay — what did you love? What could be better?"></textarea>
        </div>

        <button type="submit" class="btn-submit">
          <i class="bi bi-send-fill icon"></i> Submit Review
        </button>

      </form>
    </div>

  </div>
</div>

</body>
</html>
