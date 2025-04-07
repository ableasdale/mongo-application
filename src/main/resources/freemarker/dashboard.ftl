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
                <!-- Database / Collection information -->
                <div class="row">
                    <div class="col-lg-4 d-flex align-items-stretch">
                        <div class="card">
                            <div class="card-header">The ${database_name} Database</div>
                            <div class="card-body">
                                <h5 class="card-title">Total Document Count</h5>
                                <p class="card-text">The <strong>${collection_name}</strong> collection currently contains <strong>${collection_size}</strong> documents.</p>
                                <a href="https://media.mongodb.org/zips.json" class="btn btn-primary">Download the Sample Dataset from MongoDB</a>
                            </div>
                        </div>
                    </div>
                    <!-- Configured Index information -->
                    <div class="col-lg-4 d-flex align-items-stretch">
                        <div class="card">
                            <div class="card-header">Current Indexes</div>
                            <div class="card-body">
                                <h5 class="card-title">Card title</h5>
                                <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                                <a href="#" class="btn btn-primary">Go somewhere</a>
                            </div>
                        </div>
                    </div>

    <div class="col-lg-4 d-flex align-items-stretch">
     <div class="card">
      <div class="card-header">
      S3 Backups
      </div>
   <div class="card-body">
     <h5 class="card-title">Your most recent backup</h5>
     <#if s3_data?size gt 0>
     <p class="card-text">There are currently <strong>${s3_data?size}</strong> backups in S3.</p>
      <#assign lastItem = s3_data[s3_data?size - 1]>
     <ul>
     <li>Key: ${lastItem.key()}</li>
     <li>Last Modified Timestamp: ${lastItem.lastModified()}</li>
     </ul>
     <#else>
     <p><strong>There are NO S3 Backups of your data!</strong></p>
     </#if>
     <a href="https://s3.dualstack.us-east-1.amazonaws.com/ableasdale-tf-mongo-backup" class="btn btn-primary">View the S3 Bucket</a>
   </div>
 </div>
     </div>

 </div>
            </main>
            <#include "includes/footer.ftl">
        </div>
    </body>
</html>