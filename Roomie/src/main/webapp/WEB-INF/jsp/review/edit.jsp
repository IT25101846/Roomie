<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Edit Review | Roomie</title>
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@600;700&family=DM+Sans:wght@300;400;500;600&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
  <style>
    :root{--navy:#1B2A4A;--navy-light:#243562;--yellow:#F5C518;--yellow-light:#FFF9E0;--white:#ffffff;--text:#1a1a2e;--muted:#6b7280;--border:#e8eaf0;--shadow:0 4px 24px rgba(27,42,74,0.10)}
    *,*::before,*::after{box-sizing:border-box;margin:0;padding:0}
    body{font-family:'DM Sans',sans-serif;background:#f7f8fc;color:var(--text);min-height:100vh}
    nav{background:var(--navy);padding:0 2rem;height:64px;display:flex;align-items:center;justify-content:space-between;position:sticky;top:0;z-index:100;box-shadow:0 2px 16px rgba(27,42,74,0.18)}
    .brand{font-family:'Playfair Display',serif;color:#fff;font-size:1.4rem;font-weight:700;text-decoration:none;display:flex;align-items:center;gap:8px}
    .brand-dot{color:var(--yellow)}
    .nav-links{display:flex;align-items:center;gap:8px}
    .btn-nav{background:transparent;border:1.5px solid rgba(255,255,255,0.3);color:rgba(255,255,255,0.85);padding:6px 16px;border-radius:8px;font-family:'DM Sans',sans-serif;font-size:0.85rem;font-weight:500;cursor:pointer;text-decoration:none;transition:all 0.2s;display:inline-flex;align-items:center;gap:5px}
    .btn-nav:hover{background:rgba(255,255,255,0.12);border-color:rgba(255,255,255,0.5);color:white}
    .btn-nav-danger{border-color:rgba(239,68,68,0.5);color:rgba(255,120,120,0.9)}
    .btn-nav-danger:hover{background:rgba(239,68,68,0.15);border-color:rgba(239,68,68,0.7);color:#fca5a5}
    .page-wrapper{min-height:calc(100vh - 64px);display:flex;align-items:flex-start;justify-content:center;padding:3rem 1rem}
    .form-container{width:100%;max-width:560px}
    .page-header{margin-bottom:2rem;animation:fadeUp 0.4s ease both}
    .page-header h1{font-family:'Playfair Display',serif;font-size:2rem;color:var(--navy);margin-bottom:4px}
    .page-header p{color:var(--muted);font-size:0.95rem}
    .alert{padding:12px 16px;border-radius:12px;font-size:0.9rem;margin-bottom:1.5rem;display:flex;align-items:center;gap:8px}
    .alert-danger{background:#fef2f2;color:#991b1b;border:1px solid #fecaca}
    .card{background:var(--white);border-radius:20px;box-shadow:var(--shadow);padding:2.5rem;animation:fadeUp 0.5s ease 0.1s both;border:1px solid var(--border)}
    .form-group{margin-bottom:1.5rem}
    .form-label{display:block;font-weight:600;font-size:0.82rem;color:var(--navy);margin-bottom:8px;text-transform:uppercase;letter-spacing:0.5px}
    .form-control,.form-select{width:100%;padding:12px 16px;border:1.5px solid var(--border);border-radius:12px;font-family:'DM Sans',sans-serif;font-size:0.95rem;color:var(--text);background:var(--white);transition:border-color 0.2s,box-shadow 0.2s;outline:none;appearance:none}
    .form-control:focus,.form-select:focus{border-color:var(--navy);box-shadow:0 0 0 3px rgba(27,42,74,0.08)}
    .form-control:disabled{background:#f3f4f6;color:var(--muted);cursor:not-allowed}
    textarea.form-control{resize:vertical;min-height:120px;line-height:1.6}
    .form-select{background-image:url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='12' height='8' viewBox='0 0 12 8'%3E%3Cpath d='M1 1l5 5 5-5' stroke='%231B2A4A' stroke-width='1.5' fill='none' stroke-linecap='round'/%3E%3C/svg%3E");background-repeat:no-repeat;background-position:right 14px center;padding-right:40px;cursor:pointer}
    .star-rating{display:flex;flex-direction:row-reverse;gap:4px;justify-content:flex-end}
    .star-rating input{display:none}
    .star-rating label{font-size:2rem;color:#d1d5db;cursor:pointer;transition:color 0.15s,transform 0.15s;line-height:1}
    .star-rating label:hover,.star-rating label:hover ~ label,.star-rating input:checked ~ label{color:var(--yellow)}
    .star-rating label:hover{transform:scale(1.15)}
    .status-notice{display:flex;align-items:flex-start;gap:10px;background:var(--yellow-light);border:1px solid rgba(245,197,24,0.35);border-radius:12px;padding:12px 16px;margin-bottom:1.5rem}
    .status-notice .icon{font-size:1.1rem;color:#92400e;margin-top:2px;flex-shrink:0}
    .status-notice p{font-size:0.87rem;color:#92400e;line-height:1.5}
    .status-notice strong{font-weight:600}
    .status-badge-inline{display:inline-flex;align-items:center;gap:4px;font-size:0.78rem;font-weight:600;padding:3px 10px;border-radius:20px;margin-bottom:4px}
    .status-verified{background:#dcfce7;color:#166534}
    .status-pending{background:var(--yellow-light);color:#92400e}
    .form-footer{display:flex;justify-content:space-between;align-items:center;margin-top:0.5rem;padding-top:1.5rem;border-top:1px solid var(--border)}
    .btn-cancel{background:transparent;border:1.5px solid var(--border);color:var(--muted);padding:11px 22px;border-radius:12px;font-family:'DM Sans',sans-serif;font-size:0.95rem;font-weight:500;cursor:pointer;text-decoration:none;transition:all 0.2s;display:inline-flex;align-items:center;gap:6px}
    .btn-cancel:hover{border-color:var(--navy);color:var(--navy)}
    .btn-save{background:var(--navy);border:none;color:white;padding:11px 28px;border-radius:12px;font-family:'DM Sans',sans-serif;font-size:0.95rem;font-weight:600;cursor:pointer;transition:all 0.25s;display:inline-flex;align-items:center;gap:6px}
    .btn-save:hover{background:var(--navy-light);transform:translateY(-2px);box-shadow:0 6px 20px rgba(27,42,74,0.22)}
    .divider{height:1px;background:var(--border);margin:1.5rem 0}
    @keyframes fadeUp{from{opacity:0;transform:translateY(16px)}to{opacity:1;transform:translateY(0)}}
  </style>
</head>
<body>
<nav>
  <a href="/" class="brand"><i class="bi bi-building"></i> Roomie<span class="brand-dot">.</span></a>
  <div class="nav-links">
    <a href="/review/my"   class="btn-nav"><i class="bi bi-arrow-left"></i> My Reviews</a>
    <a href="/review/list" class="btn-nav"><i class="bi bi-star"></i> All Reviews</a>
    <a href="/customer/logout" class="btn-nav btn-nav-danger"><i class="bi bi-box-arrow-right"></i> Logout</a>
  </div>
</nav>
<div class="page-wrapper">
  <div class="form-container">
    <div class="page-header">
      <h1>Edit Your Review</h1>
      <p>Update your feedback — it will go back to pending after saving.</p>
    </div>

    <c:if test="${not empty error}">
      <div class="alert alert-danger"><i class="bi bi-exclamation-circle-fill"></i> ${error}</div>
    </c:if>

    <div class="card">
      <form action="/review/edit" method="post">
        <input type="hidden" name="feedbackId" value="${review.feedbackId}">

        <div class="form-group">
          <label class="form-label">Reservation</label>
          <input type="text" class="form-control" value="${review.reservationId}" disabled>
          <small style="font-size:0.8rem;color:var(--muted);margin-top:6px;display:block">
            <i class="bi bi-lock-fill"></i> Reservation cannot be changed for an existing review.
          </small>
        </div>

        <div class="divider"></div>

        <div class="form-group">
          <label class="form-label">Your Rating</label>
          <div class="star-rating">
            <input type="radio" id="s5" name="rating" value="5" ${review.rating == 5 ? 'checked' : ''}><label for="s5">&#9733;</label>
            <input type="radio" id="s4" name="rating" value="4" ${review.rating == 4 ? 'checked' : ''}><label for="s4">&#9733;</label>
            <input type="radio" id="s3" name="rating" value="3" ${review.rating == 3 ? 'checked' : ''}><label for="s3">&#9733;</label>
            <input type="radio" id="s2" name="rating" value="2" ${review.rating == 2 ? 'checked' : ''}><label for="s2">&#9733;</label>
            <input type="radio" id="s1" name="rating" value="1" ${review.rating == 1 ? 'checked' : ''}><label for="s1">&#9733;</label>
          </div>
        </div>

        <div class="form-group">
          <label class="form-label">Comment</label>
          <textarea name="comment" class="form-control" rows="5" required>${review.comment}</textarea>
        </div>

        <div class="status-notice">
          <i class="bi bi-info-circle-fill icon"></i>
          <div>
            <div>
              <span class="status-badge-inline ${review.verified ? 'status-verified' : 'status-pending'}">
                <i class="bi ${review.verified ? 'bi-check-circle-fill' : 'bi-clock-history'}"></i>
                Current status: ${review.verified ? 'Verified' : 'Pending'}
              </span>
            </div>
            <p>After saving, your review will be <strong>reset to pending</strong> until an admin approves it again.</p>
          </div>
        </div>

        <div class="form-footer">
          <a href="/review/my" class="btn-cancel"><i class="bi bi-x-lg"></i> Cancel</a>
          <button type="submit" class="btn-save"><i class="bi bi-save2-fill"></i> Save Changes</button>
        </div>
      </form>
    </div>
  </div>
</div>
</body>
</html>
