<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Review Moderation | Roomie Admin</title>
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@600;700&family=DM+Sans:wght@300;400;500;600&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
  <style>
    :root{--navy:#1B2A4A;--navy-light:#243562;--yellow:#F5C518;--yellow-light:#FFF9E0;--white:#ffffff;--text:#1a1a2e;--muted:#6b7280;--border:#e8eaf0;--shadow:0 2px 16px rgba(27,42,74,0.08)}
    *,*::before,*::after{box-sizing:border-box;margin:0;padding:0}
    body{font-family:'DM Sans',sans-serif;background:#fff;color:var(--text);min-height:100vh}
    nav{background:var(--navy);padding:0 2rem;height:64px;display:flex;align-items:center;justify-content:space-between;position:sticky;top:0;z-index:100;box-shadow:0 2px 16px rgba(27,42,74,0.18)}
    .brand{font-family:'Playfair Display',serif;color:#fff;font-size:1.4rem;font-weight:700;text-decoration:none;display:flex;align-items:center;gap:8px}
    .brand-dot{color:var(--yellow)}
    .nav-links{display:flex;align-items:center;gap:8px}
    .btn-nav{background:transparent;border:1.5px solid rgba(255,255,255,0.3);color:rgba(255,255,255,0.85);padding:6px 16px;border-radius:8px;font-family:'DM Sans',sans-serif;font-size:0.85rem;font-weight:500;cursor:pointer;text-decoration:none;transition:all 0.2s;display:inline-flex;align-items:center;gap:5px}
    .btn-nav:hover{background:rgba(255,255,255,0.12);border-color:rgba(255,255,255,0.5);color:white}
    .btn-nav-danger{border-color:rgba(239,68,68,0.5);color:rgba(255,120,120,0.9)}
    .btn-nav-danger:hover{background:rgba(239,68,68,0.15);border-color:rgba(239,68,68,0.7);color:#fca5a5}
    /* Sidebar + layout */
    .layout{display:flex;min-height:calc(100vh - 64px)}
    .sidebar{width:220px;background:var(--navy);padding:2rem 1rem;flex-shrink:0}
    .sidebar-title{font-size:0.7rem;font-weight:700;color:rgba(255,255,255,0.35);text-transform:uppercase;letter-spacing:1px;padding:0 0.75rem;margin-bottom:0.75rem;margin-top:1.5rem}
    .sidebar-title:first-child{margin-top:0}
    .sidebar-link{display:flex;align-items:center;gap:9px;padding:9px 12px;border-radius:10px;color:rgba(255,255,255,0.7);text-decoration:none;font-size:0.9rem;font-weight:500;transition:all 0.2s;margin-bottom:2px}
    .sidebar-link:hover{background:rgba(255,255,255,0.1);color:white}
    .sidebar-link.active{background:var(--yellow);color:var(--navy);font-weight:600}
    .main{flex:1;padding:2.5rem;overflow-x:auto;background:#f7f8fc}
    .page-header{margin-bottom:2rem;animation:fadeUp 0.4s ease both}
    .page-header h1{font-family:'Playfair Display',serif;font-size:1.9rem;color:var(--navy);margin-bottom:4px}
    .page-header p{color:var(--muted);font-size:0.95rem}
    .alert{padding:12px 16px;border-radius:12px;font-size:0.9rem;margin-bottom:1.5rem;display:flex;align-items:center;gap:8px;animation:fadeUp 0.4s ease both}
    .alert-success{background:#ecfdf5;color:#065f46;border:1px solid #a7f3d0}
    .alert-danger{background:#fef2f2;color:#991b1b;border:1px solid #fecaca}
    /* Table */
    .table-card{background:var(--white);border-radius:18px;box-shadow:var(--shadow);border:1px solid var(--border);overflow:hidden;animation:fadeUp 0.5s ease 0.1s both}
    table{width:100%;border-collapse:collapse;font-size:0.9rem}
    thead tr{background:var(--navy);color:white}
    thead th{padding:14px 16px;font-weight:600;font-size:0.82rem;text-transform:uppercase;letter-spacing:0.5px;white-space:nowrap}
    tbody tr{border-bottom:1px solid var(--border);transition:background 0.15s}
    tbody tr:last-child{border-bottom:none}
    tbody tr:hover{background:#f7f8fc}
    td{padding:14px 16px;vertical-align:middle}
    .id-cell{font-weight:600;color:var(--navy);font-size:0.85rem;font-family:monospace}
    .guest-cell{font-weight:500;color:var(--text)}
    .res-cell{color:var(--muted);font-size:0.85rem}
    .rating-cell{white-space:nowrap}
    .star-filled{color:var(--yellow)}
    .star-empty{color:#d1d5db}
    .comment-cell{max-width:200px;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;color:#374151}
    .date-cell{color:var(--muted);font-size:0.85rem;white-space:nowrap}
    .badge{display:inline-flex;align-items:center;gap:4px;font-size:0.75rem;font-weight:600;padding:4px 10px;border-radius:20px;white-space:nowrap}
    .badge-verified{background:#dcfce7;color:#166534}
    .badge-pending{background:var(--yellow-light);color:#92400e}
    .actions{display:flex;gap:6px;align-items:center;white-space:nowrap}
    .btn-action{display:inline-flex;align-items:center;gap:4px;padding:6px 12px;border-radius:8px;font-family:'DM Sans',sans-serif;font-size:0.8rem;font-weight:600;cursor:pointer;text-decoration:none;transition:all 0.18s;border:none;white-space:nowrap}
    .btn-verify{background:#dcfce7;color:#166534}
    .btn-verify:hover{background:#166534;color:white}
    .btn-unverify{background:var(--yellow-light);color:#92400e}
    .btn-unverify:hover{background:var(--yellow);color:var(--navy)}
    .btn-edit-action{background:rgba(27,42,74,0.07);color:var(--navy)}
    .btn-edit-action:hover{background:var(--navy);color:white}
    .btn-delete{background:#fef2f2;color:#991b1b}
    .btn-delete:hover{background:#dc2626;color:white}
    .empty-state{text-align:center;padding:4rem 2rem;color:var(--muted)}
    .empty-icon{font-size:3.5rem;color:var(--border);margin-bottom:1rem;display:block}
    @keyframes fadeUp{from{opacity:0;transform:translateY(18px)}to{opacity:1;transform:translateY(0)}}
  </style>
</head>
<body>
<nav>
  <a href="/admin/dashboard" class="brand"><i class="bi bi-shield-lock-fill"></i> Roomie<span class="brand-dot">.</span> <small style="font-size:0.7rem;opacity:0.6;font-family:'DM Sans',sans-serif;font-weight:400">Admin</small></a>
  <div class="nav-links">
    <a href="/admin/dashboard" class="btn-nav"><i class="bi bi-speedometer2"></i> Dashboard</a>
    <a href="/admin/logout"    class="btn-nav btn-nav-danger"><i class="bi bi-box-arrow-right"></i> Logout</a>
  </div>
</nav>

<div class="layout">
  <!-- Sidebar -->
  <aside class="sidebar">
    <p class="sidebar-title">Reviews</p>
    <a href="/review/moderate" class="sidebar-link active"><i class="bi bi-chat-square-check"></i> Moderation</a>
    <p class="sidebar-title">Management</p>
    <a href="/admin/dashboard"  class="sidebar-link"><i class="bi bi-speedometer2"></i> Dashboard</a>
    <a href="/customer/list"    class="sidebar-link"><i class="bi bi-people"></i> Customers</a>
    <a href="/room/list"        class="sidebar-link"><i class="bi bi-door-open"></i> Rooms</a>
  </aside>

  <!-- Main Content -->
  <main class="main">
    <div class="page-header">
      <h1>Review Moderation</h1>
      <p>Approve, reject or edit guest reviews before they go public.</p>
    </div>

    <c:if test="${not empty success}">
      <div class="alert alert-success"><i class="bi bi-check-circle-fill"></i> ${success}</div>
    </c:if>
    <c:if test="${not empty error}">
      <div class="alert alert-danger"><i class="bi bi-exclamation-circle-fill"></i> ${error}</div>
    </c:if>

    <div class="table-card">
      <c:choose>
        <c:when test="${empty reviews}">
          <div class="empty-state">
            <span class="empty-icon"><i class="bi bi-inbox"></i></span>
            <p>No reviews to moderate.</p>
          </div>
        </c:when>
        <c:otherwise>
          <table>
            <thead>
              <tr>
                <th>ID</th>
                <th>Guest</th>
                <th>Reservation</th>
                <th>Rating</th>
                <th>Comment</th>
                <th>Date</th>
                <th>Status</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach var="r" items="${reviews}">
                <tr>
                  <td class="id-cell">${r.feedbackId}</td>
                  <td class="guest-cell">${r.guestId}</td>
                  <td class="res-cell">${r.reservationId}</td>
                  <td class="rating-cell">
                    <c:forEach begin="1" end="${r.rating}"><i class="bi bi-star-fill star-filled"></i></c:forEach>
                    <c:forEach begin="${r.rating + 1}" end="5"><i class="bi bi-star star-empty"></i></c:forEach>
                  </td>
                  <td class="comment-cell" title="${r.comment}">${r.comment}</td>
                  <td class="date-cell">${r.feedbackDate}</td>
                  <td>
                    <span class="badge ${r.verified ? 'badge-verified' : 'badge-pending'}">
                      <i class="bi ${r.verified ? 'bi-check-circle-fill' : 'bi-clock-history'}"></i>
                      ${r.verified ? 'Verified' : 'Pending'}
                    </span>
                  </td>
                  <td>
                    <div class="actions">
                      <a href="/review/moderate/${r.feedbackId}/APPROVED" class="btn-action btn-verify" title="Verify">
                        <i class="bi bi-check-lg"></i> Verify
                      </a>
                      <a href="/review/moderate/${r.feedbackId}/REJECTED" class="btn-action btn-unverify" title="Unverify">
                        <i class="bi bi-x-lg"></i>
                      </a>
                      <a href="/review/admin/edit/${r.feedbackId}" class="btn-action btn-edit-action" title="Edit">
                        <i class="bi bi-pencil-square"></i>
                      </a>
                      <a href="/review/delete/${r.feedbackId}" class="btn-action btn-delete" title="Delete"
                         onclick="return confirm('Delete this review permanently?')">
                        <i class="bi bi-trash"></i>
                      </a>
                    </div>
                  </td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
        </c:otherwise>
      </c:choose>
    </div>
  </main>
</div>
</body>
</html>
