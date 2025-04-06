<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <!-- nav class="navbar navbar-expand-lg bg-light" -->
    <div class="container-fluid">
      <a class="navbar-brand" href="/"><img src="../assets/mdb_white.png" alt="Logo" width="120" height="30"></a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="/">Dashboard</a>
          </li>
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">View</a>
            <ul class="dropdown-menu">
                <li><a class="dropdown-item" href="/databases">MongoDB Databases</a></li>
                <li><a class="dropdown-item" href="/logs/distributedHerder">Distributed Herder Activity</a></li>

            </ul>
          </li>
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">States</a>
            <ul class="dropdown-menu">
                <li><a class="dropdown-item" href="/state/AL">Alabama</a></li>
                <li><a class="dropdown-item" href="/state/AK">Alaska</a></li>
                <li><a class="dropdown-item" href="/state/AZ">Arizona</a></li>
                <li><a class="dropdown-item" href="/state/AR">Arkansas</a></li>
                <li><a class="dropdown-item" href="/state/CA">California</a></li>
                <li><a class="dropdown-item" href="/state/CO">Colorado</a></li>
                <li><a class="dropdown-item" href="/state/CT">Connecticut</a></li>
                <li><a class="dropdown-item" href="/state/DE">Delaware</a></li>
                <li><a class="dropdown-item" href="/state/FL">Florida</a></li>
                <li><a class="dropdown-item" href="/state/GA">Georgia</a></li>
                <li><a class="dropdown-item" href="/state/HI">Hawaii</a></li>
                <li><a class="dropdown-item" href="/state/ID">Idaho</a></li>
                <li><a class="dropdown-item" href="/state/IL">Illinois</a></li>
                <li><a class="dropdown-item" href="/state/IN">Indiana</a></li>
                <li><a class="dropdown-item" href="/state/IA">Iowa</a></li>
                <li><a class="dropdown-item" href="/state/KS">Kansas</a></li>
                <li><a class="dropdown-item" href="/state/KY">Kentucky</a></li>
                <li><a class="dropdown-item" href="/state/LA">Louisiana</a></li>
                <li><a class="dropdown-item" href="/state/ME">Maine</a></li>
                <li><a class="dropdown-item" href="/state/MD">Maryland</a></li>
                <li><a class="dropdown-item" href="/state/MA">Massachusetts</a></li>
                <li><a class="dropdown-item" href="/state/MI">Michigan</a></li>
                <li><a class="dropdown-item" href="/state/MN">Minnesota</a></li>
                <li><a class="dropdown-item" href="/state/MS">Mississippi</a></li>
                <li><a class="dropdown-item" href="/state/MO">Missouri</a></li>
                <li><a class="dropdown-item" href="/state/MT">Montana</a></li>
                <li><a class="dropdown-item" href="/state/NE">Nebraska</a></li>
                <li><a class="dropdown-item" href="/state/NV">Nevada</a></li>
                <li><a class="dropdown-item" href="/state/NH">New Hampshire</a></li>
                <li><a class="dropdown-item" href="/state/NJ">New Jersey</a></li>
                <li><a class="dropdown-item" href="/state/NM">New Mexico</a></li>
                <li><a class="dropdown-item" href="/state/NY">New York</a></li>
                <li><a class="dropdown-item" href="/state/NC">North Carolina</a></li>
                <li><a class="dropdown-item" href="/state/ND">North Dakota</a></li>
                <li><a class="dropdown-item" href="/state/OH">Ohio</a></li>
                <li><a class="dropdown-item" href="/state/OK">Oklahoma</a></li>
                <li><a class="dropdown-item" href="/state/OR">Oregon</a></li>
                <li><a class="dropdown-item" href="/state/PA">Pennsylvania</a></li>
                <li><a class="dropdown-item" href="/state/RI">Rhode Island</a></li>
                <li><a class="dropdown-item" href="/state/SC">South Carolina</a></li>
                <li><a class="dropdown-item" href="/state/SD">South Dakota</a></li>
                <li><a class="dropdown-item" href="/state/TN">Tennessee</a></li>
                <li><a class="dropdown-item" href="/state/TX">Texas</a></li>
                <li><a class="dropdown-item" href="/state/UT">Utah</a></li>
                <li><a class="dropdown-item" href="/state/VT">Vermont</a></li>
                <li><a class="dropdown-item" href="/state/VA">Virginia</a></li>
                <li><a class="dropdown-item" href="/state/WA">Washington</a></li>
                <li><a class="dropdown-item" href="/state/WV">West Virginia</a></li>
                <li><a class="dropdown-item" href="/state/WI">Wisconsin</a></li>
                <li><a class="dropdown-item" href="/state/WY">Wyoming</a></li>
            </ul>
          </li>
          <!-- li class="nav-item">
            <a class="nav-link disabled">Disabled</a>
          </li -->
        </ul>
        <!-- form class="d-inline-flex" role="search">
          <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
          <button class="btn btn-outline-success" type="submit">Search the database</button>
        </form -->
        <div class="col-md-6">
            <form action="/search" method="post">
              <div class="search-container">
                <input name="term" type="text" class="form-control search-input" placeholder="Search...">
                <i class="fas fa-search search-icon"></i>
              </div>
            </form>
        </div>
      </div>
    </div>
  </nav>