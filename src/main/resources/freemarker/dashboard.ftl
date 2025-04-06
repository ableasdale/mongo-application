<!doctype html>
<html lang="en">
    <#include "includes/header.ftl">
    <body>
        <!-- #include "includes/modal_spinner.ftl" -->
        <div class="container">
            <#include "includes/navigation.ftl">
            <main>
                <#include "includes/toast.ftl">
                <div class="px-3 py-1 my-3">
                    <h2>Dashboard <small class="text-muted">Explore the Sample Dataset</small></h2>
                </div>

 <div class="row">
     <div class="col-4">
     <div class="card">
<div class="card-header">
      Box 1
      </div>
   <div class="card-body">
     <h5 class="card-title">Total Document Count</h5>
     <p class="card-text">This collection currently contains NNNN documents.</p>
     <a href="#" class="btn btn-primary">Download the Sample Dataset</a>
   </div>
 </div>
     </div>

    <div class="col-4">
     <div class="card">
<div class="card-header">
      Current Indexes
      </div>
   <div class="card-body">
     <h5 class="card-title">Card title</h5>
     <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
     <a href="#" class="btn btn-primary">Go somewhere</a>
   </div>
 </div>
     </div>

    <div class="col-4">
     <div class="card">
      <div class="card-header">
      S3 Backups
      </div>
   <div class="card-body">
     <h5 class="card-title">Your most recent backup</h5>
     <p class="card-text">There are currently <strong>${s3_data?size}</strong> backups in S3.</p>

      <#assign lastItem = s3_data[s3_data?size - 1]>
     <ul>
     <li>Key: ${lastItem.key()}</li>
     <li>Last Modified Timestamp: ${lastItem.lastModified()}</li>
     </ul>
     <a href="https://s3.dualstack.us-east-1.amazonaws.com/ableasdale-tf-mongo-backup" class="btn btn-primary">View the S3 Bucket</a>
   </div>
 </div>
     </div>

 </div>


                <div class="px-3 py-1 my-3">
                    <h3>Current File: <small class="text-muted"></small></h2>
                    <div class="card" style="width: 35rem;">
                      <div class="card-header">
                        xyyzz
                      </div>
                      <ul class="list-group list-group-flush">
                          <li>i</li>
                      </ul>
                    </div>
                    <div class="card" style="width: 35rem;">
                      <div class="card-header">
                        aabbcc
                      </div>
                      <ul class="list-group list-group-flush">
                          <li>i</li>
                      </ul>
                    </div>
                </div>
            </main>
            <#include "includes/footer.ftl">
        </div>
    </body>
</html>