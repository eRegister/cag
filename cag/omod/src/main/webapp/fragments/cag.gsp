
<table>
    <tr>
        <th>Id</th>
        <th>Cag Name</th>
        <th>Description</th>
    </tr>
    <% if (cag) { %>
    <tr>
        <td>${ cag.id }</td>
        <td>${ cag.name }</td>
        <td>${ cag.description }</td>
    </tr>
    <% } %>
</table>

</hr>
<h1>CAGs LIST</h1>
<table>
    <tr>
        <th>Id</th>
        <th>Cag Name</th>
        <th>Description</th>
        <th>Voided</th>
        <th>Voided By</th>
        <th>Void Reason</th>
    </tr>
    <% if (cagslist) {%>
    <% cagslist.each { %>
    <tr>
        <td>${ it.id }</td>
        <td>${ it.name }</td>
        <td>${ it.description }</td>
        <td>${ it.voided }</td>
        <td>${ it.voidedBy }</td>
        <td>${ it.voidReason }</td>
    </tr>
    <% } %>
    <% }%>
</table>
