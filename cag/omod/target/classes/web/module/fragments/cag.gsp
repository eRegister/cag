
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
