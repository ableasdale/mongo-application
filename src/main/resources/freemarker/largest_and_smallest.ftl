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
                    <h2>Largest and Smallest City in each State</h2>
                </div>
                <div class="px-3 py-1 my-3">
                    <table class="table table-striped">
                      <thead>
                        <tr>
                          <th scope="col" colspan="2">Biggest City</th>
                          <th scope="col" colspan="2">Smallest City</th>
                          <th scope="col">State</th>
                        </tr>
                      </thead>
                      <tbody>
                        <#list aggregation_results as data>
                        <#assign result = data?eval_json>
                        <tr>
                            <td>${result.biggestCity.name}</td>
                            <td>${result.biggestCity.pop}</td>
                            <td>${result.smallestCity.name}</td>
                            <td>${result.smallestCity.pop}</td>
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