<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="utf-8" />
            <meta http-equiv="X-UA-Compatible" content="IE=edge" />
            <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
            <meta name="description" content="Hỏi Dân IT - Dự án laptopshop" />
            <meta name="author" content="Hỏi Dân IT" />
            <title>Order</title>

            <link href="/css/styles.css" rel="stylesheet" />
            <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
        </head>

        <body class="sb-nav-fixed">
            <jsp:include page="../layout/header.jsp" />
            <div id="layoutSidenav">
                <jsp:include page="../layout/sidebar.jsp" />
                <div id="layoutSidenav_content">
                    <main>
                        <div class="container-fluid px-4 mt-5 mb-5">

                            <ol class="breadcrumb mb-4">
                                <li class="breadcrumb-item"><a href="/admin">Dashboard</a></li>
                                <li class="breadcrumb-item active">Product Detail</li>
                            </ol>
                            <div class="container mt-5">
                                <div class="col-8 row mx-auto">
                                    <div class="mx-auto">
                                        <div class="form-group row">
                                            <div
                                                class="d-flex justify-content-between mb-3 border-bottom border-4 border-light">
                                                <h2>Product Information With ID = ${product.id}
                                                </h2>

                                            </div>

                                            <div class="border-top border-light border-2">
                                                <img src="/images/product/${product.image}" class="w-50">
                                                <div class="card">
                                                    <div class="card-header">
                                                        User Information
                                                    </div>

                                                    <ul class="list-group list-group-flush">
                                                        <li class="list-group-item">Name: ${product.name}</li>
                                                        <li class="list-group-item">Price: ${product.price}</li>
                                                        <li class="list-group-item">Factory: ${product.factory}</li>
                                                        <li class="list-group-item">Target: ${product.target}</li>
                                                        <li class="list-group-item">Quantity: ${product.quantity}</li>
                                                    </ul>

                                                </div>
                                                <a href="/admin/product" class="btn btn-success">Back</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </main>
                    <jsp:include page="../layout/footer.jsp" />
                </div>
            </div>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
                crossorigin="anonymous"></script>
            <script src="js/scripts.js"></script>

        </body>

        </html>