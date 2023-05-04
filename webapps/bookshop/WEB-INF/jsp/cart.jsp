<%@ page language='java' contentType='text/html;charset=utf-8'%>
<%@ page import='bookshop.Book' %>
<%@ page import='java.util.List' %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Carro de la compra</title>
</head>
<body>
    <h1>Carro de la compra</h1>
    <table border="1">
        <thead>
            <tr>
                <th>ISBN</th>
                <th>Título</th>
            </tr>
        </thead>
        <tbody>
            <% 
                List<Book> cart = (List<Book>) session.getAttribute("cart");
                for (Book book : cart) {
            %>
                <tr>
                    <td><%= book.getIsbn() %></td>
                    <td><%= book.getTitle() %></td>
                </tr>
            <% } %>
        </tbody>
    </table>
</body>
</html>
