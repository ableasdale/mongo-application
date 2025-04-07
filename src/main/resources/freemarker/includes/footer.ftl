
<div class="container">
  <footer class="py-3 my-4 border-top">
    <ul class="nav justify-content-center border-bottom pb-3 mb-3">
      <li class="nav-item"><p class="nav-link px-2 text-muted">MDB Log: <strong>/var/log/mongodb/mongodb</strong></p></li>
      <li class="nav-item"><a href="https://github.com/ableasdale/mongo-application" class="nav-link px-2 text-muted">This Project on GitHub</a></li>
    </ul>
     <p class="text-center text-body-secondary">Mongo host DNS Name: <strong>${mongo_dns_name}</strong></p>
     <a href="https://www.mongodb.com/"><img class="d-block mx-auto" src="../assets/mongodb-icon.svg" alt="" width="60" height="60"></a>
  </footer>
</div>
<!- Javascript below -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

<script>
    // Toast
    var toastElList = [].slice.call(document.querySelectorAll('.toast'))
    var toastList = toastElList.map(function (toastEl) {
      return new bootstrap.Toast(toastEl)
    })
    toastList.forEach(toast => toast.show())
</script>