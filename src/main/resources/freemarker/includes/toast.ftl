<!-- toast! -->
<div class="position-fixed bottom-0 end-0 p-3" style="z-index: 5">
    <div class="toast" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="toast-header">
          <img src="../assets/mongodb-icon.svg" class="rounded me-2" alt="Info" width="25" height="25">
          <strong class="me-auto">${toast_heading}</strong>
          <small>Just now..</small>
          <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
        </div>
        <div class="toast-body">
          ${toast_notification}
        </div>
    </div>
</div>