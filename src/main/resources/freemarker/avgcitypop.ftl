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
                    <h2>Average City Population (Grouped by State)</h2>
                </div>
                <div class="px-3 py-1 my-3">
                    <table class="table table-striped">
                      <thead>
                        <tr>
                          <th scope="col">State</th>
                          <th scope="col">Average City Population (Grouped by State)</th>
                        </tr>
                      </thead>
                      <tbody>
                        <#list aggregation_results as data>
                        <#assign result = data?eval_json>
                        <tr>
                            <td>${result._id}</td>
                            <td>${result.avgCityPop}</td>
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