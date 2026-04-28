<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en" data-theme="dark">
<head>
    <meta charset="UTF-8"><meta name="viewport" content="width=device-width,initial-scale=1.0">
    <title>Book Room – Roomie</title>
    <link href="https://fonts.googleapis.com/css2?family=Outfit:wght@400;600;700;800&family=Plus+Jakarta+Sans:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <style>
        *,*::before,*::after{box-sizing:border-box;margin:0;padding:0;}
        :root{--bg:#060a12;--bg2:#0a1020;--surface:rgba(255,255,255,0.04);--gold:#c9a84c;--gold-light:#e4c470;--text:#e2e8f0;--text-muted:#8ba3c2;--border:rgba(201,168,76,0.12);--radius:20px;--nav-height:70px;--shadow:0 8px 32px rgba(0,0,0,0.5);--glow:0 0 60px rgba(201,168,76,0.12);--glass-bg:rgba(255,255,255,0.04);--glass-border:rgba(255,255,255,0.08);--glass-blur:blur(24px);}
        [data-theme="light"]{--bg:#f0f4f8;--bg2:#e8edf5;--surface:rgba(255,255,255,0.7);--text:#1e293b;--text-muted:#4b6080;--border:rgba(30,58,95,0.1);--shadow:0 8px 32px rgba(0,0,0,0.08);--glow:0 0 60px rgba(201,168,76,0.08);--glass-bg:rgba(255,255,255,0.5);--glass-border:rgba(30,58,95,0.1);--glass-blur:blur(20px);}
        body{font-family:'Plus Jakarta Sans',sans-serif;background:var(--bg);color:var(--text);padding-top:var(--nav-height);overflow-x:hidden;transition:background .5s,color .5s;}
        .container{max-width:900px;margin:0 auto;padding:3rem 1.5rem;}
        .back-link{color:var(--gold);text-decoration:none;font-size:.87rem;display:inline-flex;align-items:center;gap:.4rem;margin-bottom:2rem;animation:fadeUp .6s ease both;}
        .page-title{font-family:'Outfit',sans-serif;font-size:2rem;font-weight:700;margin-bottom:.5rem;animation:fadeUp .7s ease both;}
        .page-sub{color:var(--text-muted);font-size:.9rem;margin-bottom:2.5rem;animation:fadeUp .7s .1s ease both;}
        .booking-grid{display:grid;grid-template-columns:1.2fr 1fr;gap:2.5rem;align-items:start;}
        @media(max-width:700px){.booking-grid{grid-template-columns:1fr;}}
        .room-preview{background:var(--glass-bg);backdrop-filter:var(--glass-blur);border:1px solid var(--glass-border);border-radius:var(--radius);overflow:hidden;box-shadow:var(--shadow);animation:fadeUp .7s .15s ease both;}
        .room-preview img{width:100%;height:200px;object-fit:cover;}
        .room-preview-body{padding:1.5rem;}
        .room-type{font-size:.76rem;color:var(--gold);letter-spacing:2px;text-transform:uppercase;margin-bottom:.4rem;}
        .room-name-p{font-family:'Outfit',sans-serif;font-size:1.3rem;color:var(--text);}
        .room-price-p{color:var(--gold);font-size:1.1rem;font-weight:700;margin-top:.5rem;}
        .booking-form-card{background:var(--glass-bg);backdrop-filter:var(--glass-blur);border:1px solid var(--glass-border);border-radius:var(--radius);padding:2rem;box-shadow:var(--shadow);position:relative;overflow:hidden;animation:fadeUp .7s .2s ease both;}
        .booking-form-card::before{content:'';position:absolute;top:0;left:0;right:0;height:2px;background:linear-gradient(90deg,transparent,var(--gold),transparent);}
        .form-group{margin-bottom:1.3rem;}
        label{display:block;font-size:.8rem;color:var(--text-muted);margin-bottom:.5rem;font-weight:500;}
        input[type=date]{width:100%;padding:.8rem 1rem;border-radius:12px;border:1.5px solid var(--border);background:rgba(8,14,26,.5);color:var(--text);font-size:.9rem;transition:border-color .3s,box-shadow .3s;}
        [data-theme="light"] input[type=date]{background:#f0f4f8;}
        input[type=date]:focus{outline:none;border-color:var(--gold);box-shadow:0 0 0 3px rgba(201,168,76,.12);}
        .price-row{display:flex;justify-content:space-between;padding:.5rem 0;font-size:.87rem;color:var(--text-muted);}
        .price-total{display:flex;justify-content:space-between;padding:.7rem 0;font-size:1rem;color:var(--gold);font-weight:700;border-top:1px solid var(--border);margin-top:.5rem;}
        .btn-book{display:block;width:100%;padding:1rem;background:linear-gradient(135deg,var(--gold),var(--gold-light));color:#0a0e18;border:none;border-radius:25px;font-size:1rem;font-weight:700;cursor:pointer;transition:all .4s cubic-bezier(.23,1,.32,1);margin-top:1.5rem;box-shadow:0 4px 25px rgba(201,168,76,.35);}
        .btn-book:hover{transform:translateY(-3px) scale(1.02);box-shadow:0 8px 40px rgba(201,168,76,.5);}
        .flash{padding:.85rem 1rem;border-radius:12px;margin-bottom:1.5rem;font-size:.86rem;border-left:4px solid;backdrop-filter:blur(12px);}
        .flash-error{background:rgba(220,38,38,.1);border-color:#dc2626;color:#f87171;}
        .form-section-title{font-size:.78rem;letter-spacing:2px;text-transform:uppercase;color:var(--gold);margin-bottom:1.2rem;font-weight:600;}
        @keyframes fadeUp{from{opacity:0;transform:translateY(35px)}to{opacity:1;transform:translateY(0)}}
    </style>
</head>
<body>
<jsp:include page="../navbar.jsp"/>
<div class="container">
    <a href="/room/detail/${room.roomId}" class="back-link">← Back to room details</a>
    <h1 class="page-title">Reserve Your Room</h1>
    <p class="page-sub">Fill in your dates and confirm your booking.</p>
    <c:if test="${not empty flash_error}"><div class="flash flash-error">${flash_error}</div></c:if>
    <div class="booking-grid">
        <div class="room-preview">
            <img src="/images/${room.imageFile}" alt="${room.displayType} Room">
            <div class="room-preview-body">
                <div class="room-type">${room.displayType} Room</div>
                <div class="room-name-p">Room ${room.roomNumber}</div>
                <div style="color:var(--text-muted);font-size:.85rem;margin-top:.4rem;">${room.description} view · ${room.capacity} guests max</div>
                <div class="room-price-p">LKR ${room.pricePerNight} <span style="font-size:.8rem;color:var(--text-muted);font-weight:400;">/night</span></div>
                <hr style="border:none;border-top:1px solid var(--border);margin:1rem 0;">
                <div style="font-size:.8rem;color:var(--text-muted);"><c:forEach var="a" items="${room.amenities}"><span style="margin-right:.5rem;">✓ ${a}</span></c:forEach></div>
            </div>
        </div>
        <div class="booking-form-card">
            <div class="form-section-title">Select Dates</div>
            <form action="/reservation/book" method="post" id="bookForm">
                <input type="hidden" name="roomId" value="${room.roomId}">
                <div class="form-group"><label for="checkIn">Check-In Date</label><input type="date" id="checkIn" name="checkIn" required></div>
                <div class="form-group"><label for="checkOut">Check-Out Date</label><input type="date" id="checkOut" name="checkOut" required></div>
                <div id="priceSummary" style="display:none;">
                    <div class="price-row"><span id="nightsLabel">Nights</span><span id="nightsCost">—</span></div>
                    <div class="price-total"><span>Total</span><span id="totalPrice">—</span></div>
                </div>
                <button type="submit" class="btn-book">Confirm Booking →</button>
            </form>
        </div>
    </div>
</div>
<script>
const t=localStorage.getItem('roomie-theme')||'dark';document.documentElement.setAttribute('data-theme',t);
const ci=document.getElementById('checkIn'),co=document.getElementById('checkOut');
const today=new Date().toISOString().split('T')[0];
if(ci){ci.min=today;co.min=today;}
function update(){if(!ci.value||!co.value)return;const n=Math.round((new Date(co.value)-new Date(ci.value))/864e5);if(n<=0)return;const p=${room.pricePerNight};document.getElementById('nightsLabel').textContent=n+' night'+(n>1?'s':'');document.getElementById('nightsCost').textContent='LKR '+p*n;document.getElementById('totalPrice').textContent='LKR '+(p*n).toLocaleString();document.getElementById('priceSummary').style.display='block';}
if(ci){ci.addEventListener('change',update);co.addEventListener('change',update);}
</script>
</body>
</html>
