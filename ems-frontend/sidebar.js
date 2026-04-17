const SidebarBuilder = {
  subPanelOpen: false,
  currentSubPanel: null,

  init() {
    if (localStorage.getItem('isLoggedIn') !== 'true') {
      window.location.href = 'login.html';
      return;
    }

    const role = localStorage.getItem('userRole');
    const name = localStorage.getItem('currentUserName') || 'User';
    let page = window.location.pathname.split('/').pop().split('?')[0] || 'index.html';

    this.buildSidebar(role, page);
    this.buildSubSidebar(role, page); 
    this.setupTopbar(name);
  },

  buildSidebar(role, page) {
    const nav = document.getElementById('sidebarNav');
    if (!nav) return;

    let html = '';

    html += this.sectionLabel('Main');
    html += this.link('index.html', 'fa-house', 'Dashboard', page);
    
    html += this.expandableLink('attendance.html', 'fa-clock', 'Time & Attendance', page, 'attendance');
    html += this.expandableLink('leaves.html', 'fa-plane-departure', 'Leave Management', page, 'leaves');
    
    html += this.link('calendar.html', 'fa-calendar-days', 'Calendar', page);
    html += this.link('chat.html', 'fa-comments', 'Communications', page);

    // --- CONSOLIDATED MANAGEMENT HUB ---
    if (role === 'Manager') {
      html += this.sectionLabel('Control Center');
      html += this.expandableLink('team.html', 'fa-laptop-file', 'Management Hub', page, 'team');
      html += this.link('reports.html', 'fa-chart-pie', 'Reports', page);
    } 

    // Regular Employee Workspace
    if (role === 'Employee') {
      html += this.sectionLabel('My Space');
      html += this.expandableLink('workspace.html', 'fa-laptop-code', 'My Workspace', page, 'space');
    }

    // Boss Specific View
    if (role === 'Boss') {
      html += this.sectionLabel('Operations');
      html += this.link('employees.html', 'fa-users-gear', 'Directory', page);
      html += this.expandableLink('team.html', 'fa-users-viewfinder', 'Team Hub', page, 'team');
      html += this.expandableLink('payroll.html', 'fa-money-check-dollar', 'Payroll', page, 'payroll');
      html += this.sectionLabel('Executive');
      html += this.link('admin.html', 'fa-chess-king', 'Command Center', page);
    }

    html += this.sectionLabel('System');
    html += this.link('settings.html', 'fa-gear', 'Settings', page);

    nav.innerHTML = html;
  },

  buildSubSidebar(role, page) {
    const sub = document.getElementById('subSidebar');
    if (!sub) return;

    const urlParams = new URLSearchParams(window.location.search);
    const viewMode = urlParams.get('view');
    const tabMode = urlParams.get('tab');

    const showAtt = page === 'attendance.html' ? 'block' : 'none';
    const showLeave = page === 'leaves.html' ? 'block' : 'none';
    const showPayroll = (page === 'payroll.html' || page === 'payroll-engine.html') ? 'block' : 'none';
    const showTeam = page === 'team.html' ? 'block' : 'none';
    const showSpace = page === 'workspace.html' ? 'block' : 'none';

    let subHtml = `<div class="sub-sidebar-header">Navigation</div>`;
      
    // --- ATTENDANCE PANEL ---
    subHtml += `<div id="subAttPanel" class="sub-panel-section" style="display: ${showAtt};">
        <div style="padding: 14px 18px 6px; font-size: 10px; font-weight: 600; letter-spacing: 1px; text-transform: uppercase; color: rgba(255,255,255,0.3);">Attendance</div>`;
    if (role === 'Boss') {
      subHtml += `<a href="attendance.html?tab=daily" class="sub-nav-link ${tabMode === 'daily' || !tabMode ? 'active' : ''}"><i class="fa-solid fa-calendar-day fa-fw"></i> Daily Overview</a>
                  <a href="attendance.html?tab=history" class="sub-nav-link ${tabMode === 'history' ? 'active' : ''}"><i class="fa-solid fa-timeline fa-fw"></i> Employee History</a>`;
    } else if (role === 'Manager') {
      subHtml += `<a href="attendance.html" class="sub-nav-link ${viewMode !== 'all' ? 'active' : ''}"><i class="fa-solid fa-user-clock fa-fw"></i> My Attendance</a>
                  <a href="attendance.html?view=all&tab=daily" class="sub-nav-link ${viewMode === 'all' && (tabMode === 'daily' || !tabMode) ? 'active' : ''}"><i class="fa-solid fa-calendar-day fa-fw"></i> Team Overview</a>`;
    } else {
      subHtml += `<a href="attendance.html" class="sub-nav-link active"><i class="fa-solid fa-user-clock fa-fw"></i> My Attendance</a>`;
    }
    subHtml += `</div>`;

    // --- LEAVES PANEL ---
    subHtml += `<div id="subLeavePanel" class="sub-panel-section" style="display: ${showLeave};">
        <div style="padding: 14px 18px 6px; font-size: 10px; font-weight: 600; letter-spacing: 1px; text-transform: uppercase; color: rgba(255,255,255,0.3);">Leaves</div>`;
    
    // FIX: Hide "All Leaves" for Employees
    if (role === 'Boss') {
      subHtml += `<a href="leaves.html" class="sub-nav-link ${viewMode !== 'mine' ? 'active' : ''}"><i class="fa-solid fa-list fa-fw"></i> All Leaves</a>`;
    } else if (role === 'Manager') {
      subHtml += `<a href="leaves.html" class="sub-nav-link ${viewMode !== 'mine' ? 'active' : ''}"><i class="fa-solid fa-list fa-fw"></i> All Leaves</a>
                  <a href="leaves.html?view=mine" class="sub-nav-link ${viewMode === 'mine' ? 'active' : ''}"><i class="fa-solid fa-user-clock fa-fw"></i> My Leaves</a>`;
    } else {
      // Employee only sees "My Leaves"
      subHtml += `<a href="leaves.html?view=mine" class="sub-nav-link active"><i class="fa-solid fa-user-clock fa-fw"></i> My Leaves</a>`;
    }
    subHtml += `</div>`;

    // --- MANAGEMENT HUB PANEL ---
    subHtml += `<div id="subTeamPanel" class="sub-panel-section" style="display: ${showTeam};">
        <div style="padding: 14px 18px 6px; font-size: 10px; font-weight: 600; letter-spacing: 1px; text-transform: uppercase; color: rgba(255,255,255,0.3);">Management Hub</div>`;
    subHtml += `
      <a href="team.html?tab=directory" class="sub-nav-link ${tabMode === 'directory' || !tabMode ? 'active' : ''}"><i class="fa-solid fa-address-book fa-fw"></i> Team Directory</a>
      <a href="team.html?tab=docs" class="sub-nav-link ${tabMode === 'docs' ? 'active' : ''}"><i class="fa-solid fa-vault fa-fw"></i> Document Vault</a>
      <a href="team.html?tab=my-assets" class="sub-nav-link ${tabMode === 'my-assets' ? 'active' : ''}"><i class="fa-solid fa-laptop fa-fw"></i> My Assets</a>
      <a href="team.html?tab=hardware" class="sub-nav-link ${tabMode === 'hardware' ? 'active' : ''}"><i class="fa-solid fa-microchip fa-fw"></i> Hardware Assignment</a>
      <a href="team.html?tab=notes" class="sub-nav-link ${tabMode === 'notes' ? 'active' : ''}"><i class="fa-solid fa-clipboard-user fa-fw"></i> 1:1 Private Notes</a>
      <a href="team.html?tab=reimbursement" class="sub-nav-link ${tabMode === 'reimbursement' ? 'active' : ''}"><i class="fa-solid fa-file-invoice-dollar fa-fw"></i> Reimbursement</a>
      <a href="team.html?tab=approvals" class="sub-nav-link ${tabMode === 'approvals' ? 'active' : ''}"><i class="fa-solid fa-check-to-slot fa-fw"></i> Approvals Queue</a>
    </div>`;

    // --- PAYROLL PANEL ---
    subHtml += `<div id="subPayrollPanel" class="sub-panel-section" style="display: ${showPayroll};">
        <div style="padding: 14px 18px 6px; font-size: 10px; font-weight: 600; letter-spacing: 1px; text-transform: uppercase; color: rgba(255,255,255,0.3);">Payroll</div>`;
    if (role === 'Boss') {
      subHtml += `<a href="payroll.html" class="sub-nav-link ${viewMode !== 'personal' ? 'active' : ''}"><i class="fa-solid fa-book-open fa-fw"></i> Payroll Ledger</a>
                  <a href="payroll-engine.html" class="sub-nav-link ${page === 'payroll-engine.html' ? 'active' : ''}"><i class="fa-solid fa-calculator fa-fw"></i> Run Engine</a>`;
    } else {
      subHtml += `<a href="payroll.html?view=personal" class="sub-nav-link active"><i class="fa-solid fa-file-invoice-dollar fa-fw"></i> My Payslips</a>`;
    }
    subHtml += `</div>`;

    // --- MY WORKSPACE (EMPLOYEE ONLY) ---
    subHtml += `<div id="subSpacePanel" class="sub-panel-section" style="display: ${showSpace};">
        <div style="padding: 14px 18px 6px; font-size: 10px; font-weight: 600; letter-spacing: 1px; text-transform: uppercase; color: rgba(255,255,255,0.3);">My Workspace</div>`;
    subHtml += `
      <a href="workspace.html?tab=assets" class="sub-nav-link ${tabMode === 'assets' || !tabMode ? 'active' : ''}"><i class="fa-solid fa-laptop fa-fw"></i> My Assets</a>
      <a href="workspace.html?tab=docs" class="sub-nav-link ${tabMode === 'docs' ? 'active' : ''}"><i class="fa-solid fa-vault fa-fw"></i> Document Vault</a>
      <a href="workspace.html?tab=expense" class="sub-nav-link ${tabMode === 'expense' ? 'active' : ''}"><i class="fa-solid fa-receipt fa-fw"></i> Reimbursements</a>
    </div>`;

    sub.innerHTML = subHtml;
  },

  sectionLabel(text) { return `<div class="nav-section-label" style="color: rgba(255,255,255,0.5);">${text}</div>`; },
  
  link(href, icon, label, page) {
    const isActive = page === href || 
                     (href === 'payroll.html' && page === 'payroll-engine.html') ||
                     (href === 'team.html' && page === 'team.html') ||
                     (href === 'workspace.html' && page === 'workspace.html');
    const activeClass = isActive ? 'active' : '';
    return `<a href="${href}" class="nav-link ${activeClass}" style="color: #ffffff;"><i class="fa-solid ${icon} fa-fw"></i><span>${label}</span></a>`;
  },

  expandableLink(href, icon, label, page, panelType) {
    const isActive = page === href || 
                     (href === 'payroll.html' && page === 'payroll-engine.html') ||
                     (href === 'team.html' && page === 'team.html') ||
                     (href === 'workspace.html' && page === 'workspace.html');
    const activeClass = isActive ? 'active expanded' : '';
    return `<button class="nav-link ${activeClass}" style="color: #ffffff;" onclick="SidebarBuilder.toggleSubPanel('${panelType}', this)"><i class="fa-solid ${icon} fa-fw"></i><span>${label}</span><i class="fa-solid fa-chevron-right chevron"></i></button>`;
  },

  toggleSubPanel(panelType, btnElement) {
    const sub = document.getElementById('subSidebar');
    const main = document.getElementById('mainContent');
    if (this.subPanelOpen && this.currentSubPanel === panelType) {
      this.subPanelOpen = false; this.currentSubPanel = null;
      sub.classList.remove('open'); main.classList.remove('sub-open');
      if (btnElement) btnElement.classList.remove('expanded');
      return;
    }
    this.subPanelOpen = true; this.currentSubPanel = panelType;
    
    document.getElementById('subAttPanel').style.display = 'none';
    document.getElementById('subLeavePanel').style.display = 'none';
    document.getElementById('subPayrollPanel').style.display = 'none';
    const subTeam = document.getElementById('subTeamPanel'); if(subTeam) subTeam.style.display = 'none';
    const subSpace = document.getElementById('subSpacePanel'); if(subSpace) subSpace.style.display = 'none';
    
    document.querySelectorAll('.nav-link.expanded').forEach(btn => btn.classList.remove('expanded'));
    
    if (panelType === 'attendance') document.getElementById('subAttPanel').style.display = 'block';
    else if (panelType === 'leaves') document.getElementById('subLeavePanel').style.display = 'block';
    else if (panelType === 'payroll') document.getElementById('subPayrollPanel').style.display = 'block';
    else if (panelType === 'team') { if(subTeam) subTeam.style.display = 'block'; }
    else if (panelType === 'space') { if(subSpace) subSpace.style.display = 'block'; }
    
    if (btnElement) btnElement.classList.add('expanded');
    sub.classList.add('open'); main.classList.add('sub-open');
  },

  setupTopbar(name) {
    const nameEl = document.getElementById('topbarUserName');
    const avatarEl = document.getElementById('topbarAvatar');
    if (nameEl) nameEl.textContent = name;
    if (avatarEl) avatarEl.src = `https://ui-avatars.com/api/?name=${encodeURIComponent(name)}&background=3949ab&color=ffffff&size=80`;
  }
};

function logout() {
  localStorage.clear();
  window.location.href = 'login.html';
}

function avatarUrl(name, bg) { return `https://ui-avatars.com/api/?name=${encodeURIComponent(name)}&background=${bg || 'random'}&color=fff&size=80`; }
function formatDate(dateStr) { if (!dateStr) return '-'; const d = new Date(dateStr + 'T00:00:00'); return d.toLocaleDateString('en-IN', { day: '2-digit', month: 'short', year: 'numeric' }); }
function formatDay(dateStr) { if (!dateStr) return '-'; return new Date(dateStr + 'T00:00:00').toLocaleDateString('en-US', { weekday: 'short' }); }
function todayStr() { return new Date().toLocaleDateString('en-CA'); }
function openModal(id) { const el = document.getElementById(id); if (el) el.classList.add('open'); }
function closeModal(id) { const el = document.getElementById(id); if (el) el.classList.remove('open'); }