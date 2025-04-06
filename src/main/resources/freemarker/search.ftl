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
                    <h2>Search Results: <small class="text-muted">${term}</small></h2>
                </div>
                <div class="px-3 py-1 my-3">
                    <table class="table table-striped">
                      <thead>
                        <tr>
                          <th scope="col">ID</th>
                          <th scope="col">City</th>
                          <th scope="col">Longitude</th>
                          <th scope="col">Latitude</th>
                          <th scope="col">Population</th>
                          <th scope="col">State</th>
                        </tr>
                      </thead>
                      <tbody>
                        <#list searchResults as result>
                        <!-- #assign data = result?eval_json -->
                        <tr>
                            <td>${result._id}</td>
                            <td>${result.city}</td>
                            <td>${result.loc[0]}</td>
                            <td>${result.loc[1]}</td>
                            <td>${result.pop}</td>
                            <td>${result.state}</td>
                        </tr>
                        </#list>
                      </tbody>
                    </table>
                </div>
            </main>
            <#include "includes/footer.ftl">
        </div>
    </body>
</html>