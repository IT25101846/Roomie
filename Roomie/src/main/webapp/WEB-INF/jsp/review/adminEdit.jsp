<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Edit Review | Roomie Admin</title>
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
    .form-container{width:100%;max-width:600px}
    .breadcrumb{display:flex;align-items:center;gap:8px;font-size:0.85rem;color:var(--muted);margin-bottom:1.5rem;animation:fadeUp 0.4s ease both}
    .breadcrumb a{color:var(--navy);text-decoration:none;font-weight:500}
    .breadcrumb a:hover{text-decoration:underline}
    .breadcrumb-sep{color:var(--border)}
    .page-header{margin-bottom:2rem;animation:fadeUp 0.4s ease 0.05s both}
    .page-header h1{font-family:'Playfair Display',serif;font-size:2rem;color:var(--navy);margin-bottom:4px}
    .page-header p{color:var(--muted);font-size:0.95rem}
    .card{background:var(--white);border-radius:20px;box-shadow:var(--shadow);padding:2.5rem;animation:fadeUp 0.5s ease 0.1s both;border:1px solid var(--border)}
    .row-2{display:grid;grid-template-columns:1fr 1fr;gap:1.5rem}
    .form-group{margin-bottom:1.5rem}
    .form-label{display:block;font-weight:600;font-size:0.82rem;color:var(--navy);margin-bottom:8px;text-transform:uppercase;letter-spacing:0.5px}
    .form-control,.form-select{width:100%;padding:12px 16px;border:1.5px solid var(--border);border-radius:12px;font-family:'DM Sans',sans-serif;font-size:0.95rem;color:var(--text);background:var(--white);transition:border-color 0.2s,box-shadow 0.2s;outline:none;appearance:none}
    .form-control:focus,.form-select:focus{border-color:var(--navy);box-shadow:0 0 0 3px rgba(27,42,74,0.08)}
    .form-control:disabled{background:#f3f4f6;color:var(--muted);cursor:not-allowed}
    textarea.form-control{resize:vertical;min-height:130px;line-height:1.6}
    .form-select{background-image:url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='12' height='8' viewBox='0 0 12 8'%3E%3Cpath d='M1 1l5 5 5-5' stroke='%231B2A4A' stroke-width='1.5' fill='none' stroke-linecap='round'/%3E%3C/svg%3E");background-repeat:no-repeat;background-position:right 14px center;padding-right:40px;cursor:pointer}
    .admin-badge{display:inline-flex;align-items:center;gap:6px;background:rgba(27,42,74,0.07);color:var(--navy);font-size:0.78rem;font-weight:600;padding:5px 12px;border-radius:20px;margin-bottom:1.5rem}
    .divider{height:1px;background:var(--border);margin:1.5rem 0}
    .form-footer{display:flex;justify-content:space-between;align-items:center;padding-top:1.5rem;border-top:1px solid var(--border)}
    .btn-cancel{background:transparent;border:1.5px solid var(--border);color:var(--muted);padding:11px 22px;border-radius:12px;font-family:'DM Sans',sans-serif;font-size:0.95rem;font-weight:500;cursor:pointer;text-decoration:none;transition:all 0.2s;display:inline-flex;align-items:center;gap:6px}
    .btn-cancel:hover{border-color:var(--navy);color:var(--navy)}
    .btn-save{background:var(--navy);border:none;color:white;padding:11px 28px;border-radius:12px;font-family:'DM Sans',sans-serif;font-size:0.95rem;font-weight:600;cursor:pointer;transition:all 0.25s;display:inline-flex;align-items:center;gap:6px}
    .btn-save:hover{background:var(--navy-light);transform:translateY(-2px);box-shadow:0 6px 20px rgba(27,42,74,0.22)}
    .verified-toggle{display:flex;gap:1rem}
    .toggle-opt{flex:1}
    .toggle-opt input{display:none}
    .toggle-opt label{display:flex;align-items:center;justify-content:center;gap:8px;padding:12px;border:1.5px solid var(--border);border-radius:12px;cursor:pointer;transition:all 0.2s;font-weight:500;font-size:0.9rem}
    .toggle-opt input:checked + label.verified-label{background:#dcfce7;border-color:#86efac;color:#166534}
    .toggle-opt input:checked + label.pending-label{background:var(--yellow-light);border-color:rgba(245,197,24,0.4);color:#92400e}
    .toggle-opt label:hover{border-color:var(--navy)}
    @keyframes fadeUp{from{opacity:0;transform:translateY(16px)}to{opacity:1;transform:translateY(0)}}
  </style>
</head>
<body>
<nav>
  <a href="/admin/dashboard" class="brand"><i class="bi bi-shield-lock-fill"></i> Roomie<span class="brand-dot">.</span> <small style="font-size:0.7rem;opacity:0.6;font-family:'DM Sans',sans-serif;font-weight:400">Admin</small></a>
  <div class="nav-links">
    <a href="/review/moderate"  class="btn-nav"><i class="bi bi-arrow-left"></i> Back</a>
    <a href="/admin/dashboard"  class="btn-nav"><i class="bi bi-speedometer2"></i> Dashboard</a>
    <a href="/admin/logout"     class="btn-nav btn-nav-danger"><i class="bi bi-box-arrow-right"></i> Logout</a>
  </div>
</nav>

<div class="page-wrapper">
  <div class="form-container">

    <div class="breadcrumb">
      <a href="/admin/dashboard">Dashboard</a>
      <span class="breadcrumb-sep">/</span>
      <a href="/review/moderate">Moderation</a>
      <span class="breadcrumb-sep">/</span>
      <span>Edit Review</span>
    </div>

    <div class="page-header">
      <h1>Edit Review</h1>
      <p>Modify review content and set its verification status.</p>
    </div>

    <div class="card">
      <span class="admin-badge"><i class="bi bi-shield-check"></i> Admin Edit &mdash; ${review.feedbackId}</span>

      <form action="/review/admin/edit" method="post">
        <input type="hidden" name="feedbackId" value="${review.feedbackId}">

        <div class="row-2">
          <div class="form-group">
            <label class="form-label">Guest ID</label>
            <input type="text" class="form-control" value="${review.guestId}" disabled>
          </div>
          <div class="form-group">
            <label class="form-label">Reservation ID</label>
            <input type="text" class="form-control" value="${review.reservationId}" disabled>
          </div>
        </div>

        <div class="divider"></div>

        <div class="form-group">
          <label class="form-label">Rating (1 – 5)</label>
          <select name="rating" class="form-select" required>
            <c:forEach begin="1" end="5" var="i">
              <option value="${i}" ${i == review.rating ? 'selected' : ''}>${i} star${i > 1 ? 's' : ''}</option>
            </c:forEach>
          </select>
        </div>

        <div class="form-group">
          <label class="form-label">Comment</label>
          <textarea name="comment" class="form-control" rows="5" required>${review.comment}</textarea>
        </div>

        <div class="form-group">
          <label class="form-label">Verification Status</label>
          <div class="verified-toggle">
            <div class="toggle-opt">
              <input type="radio" name="verified" id="v-true"  value="true"  ${review.verified ? 'checked' : ''}>
              <label for="v-true" class="verified-label"><i class="bi bi-check-circle-fill"></i> Verified</label>
            </div>
            <div class="toggle-opt">
              <input type="radio" name="verified" id="v-false" value="false" ${!review.verified ? 'checked' : ''}>
              <label for="v-false" class="pending-label"><i class="bi bi-clock-history"></i> Not Verified</label>
            </div>
          </div>
        </div>

        <div class="form-footer">
          <a href="/review/moderate" class="btn-cancel"><i class="bi bi-x-lg"></i> Cancel</a>
          <button type="submit" class="btn-save"><i class="bi bi-save2-fill"></i> Save Changes</button>
        </div>
      </form>
    </div>
  </div>
</div>
</body>
</html>
