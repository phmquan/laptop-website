<html lang="en">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    <%@page contentType="text/html" pageEncoding="UTF-8" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

            <head>
                <meta charset="utf-8" />
                <meta http-equiv="X-UA-Compatible" content="IE=edge" />
                <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
                <meta name="description" content="Hỏi Dân IT - Dự án laptopshop" />
                <meta name="author" content="Hỏi Dân IT" />
                <title>Create User</title>

                <link href="/css/styles.css" rel="stylesheet" />
                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
                <script>
                    $(document).ready(() => {
                        const avatarFile = $("#avatarFile");
                        avatarFile.change(function (e) {
                            const imgURL = URL.createObjectURL(e.target.files[0]);
                            $("#avatarPreview").attr("src", imgURL);
                            $("#avatarPreview").css({ "display": "block" });
                        });
                    });
                </script>
                <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
            </head>

            <body class="sb-nav-fixed">
                <jsp:include page="../layout/header.jsp" />
                <div id="layoutSidenav">
                    <jsp:include page="../layout/sidebar.jsp" />
                    <div id="layoutSidenav_content">
                        <main>
                            <div class="container-fluid px-4">
                                <h1 class="mt-4">Create Product</h1>
                                <ol class="breadcrumb mb-4">
                                    <li class="breadcrumb-item"><a href="/admin">Dashboard</a></li>
                                    <li class="breadcrumb-item active">Create A Product</li>
                                </ol>
                                <div class="container mt-5">
                                    <div class="row">
                                        <div class="col-12 mx-auto">
                                            <h3>Create a Product</h3>
                                            <form:form method="post" action="/admin/product/create"
                                                modelAttribute="newProduct" enctype="multipart/form-data">
                                                <div class="row g-3">
                                                    <div class="col mb-3">
                                                        <label for="exampleInputEmail1" class="form-label">
                                                            Name</label>
                                                        <form:input type="name" class="form-control" path="name" />
                                                    </div>
                                                    <div class="col mb-3">
                                                        <label for="exampleInputPassword1"
                                                            class="form-label">Price</label>
                                                        <form:input type="number" class="form-control" path="price" />
                                                    </div>
                                                </div>
                                                <div class="row g-3">
                                                    <div class="col mb-3">
                                                        <label for="exampleInputPassword1" class="form-label">Detail
                                                            Description</label>
                                                        <form:input type="text" class="form-control"
                                                            path="detailDesc" />
                                                    </div>
                                                </div>
                                                <div class="row g-3">
                                                    <div class="col mb-3">
                                                        <label for="exampleInputPassword1" class="form-label">Short
                                                            description</label>
                                                        <form:input type="text" class="form-control" path="shortDesc" />
                                                    </div>
                                                    <div class="col mb-3">
                                                        <label for="exampleInputPassword1" class="form-label">Quantity:
                                                        </label>
                                                        <form:input type="number" class="form-control"
                                                            path="quantity" />
                                                    </div>
                                                </div>

                                                <div class="row g-3">
                                                    <div class="mb-3 col">
                                                        <label class="mb-2">Factory: </label>
                                                        <form:select class="form-select col" path="factory">
                                                            <!-- <option selected></option> -->
                                                            <form:option value="APPLE">Apple (Macbook)</form:option>
                                                            <form:option value="ASUS">Asus</form:option>
                                                            <form:option value="LENOVO">Lenovo</form:option>
                                                            <form:option value="HP">HP</form:option>
                                                            <form:option value="DELL">Dell</form:option>
                                                        </form:select>
                                                    </div>
                                                    <div class="mb-3 col">
                                                        <label class="mb-2">Target: </label>
                                                        <form:select class="form-select col" path="target">
                                                            <!-- <option selected></option> -->
                                                            <form:option value="Gaming">Gaming
                                                            </form:option>
                                                            <form:option value="Graphic">Graphic design</form:option>
                                                            <form:option value="Workstation">Workstation</form:option>
                                                            <form:option value="SINHVIEN-VANPHONG">Sinh Viên - Văn Phòng
                                                            </form:option>
                                                            <form:option value="DOANHNHAN">Doanh Nhân</form:option>
                                                        </form:select>
                                                    </div>
                                                </div>
                                                <div class="row g-3">
                                                    <div class="mb-3 col">
                                                        <label for="formFile" class="form-label">Avatar: </label>
                                                        <input class="form-control" type="file" id="avatarFile"
                                                            accept=".png,.jpg,.jpeg" name="hoidanitFile" />
                                                    </div>
                                                </div>
                                                <div class="col-12 mb-3">
                                                    <img style="max-height: 250px;display: none" src=""
                                                        alt="avatar preview" id="avatarPreview" mutiple>
                                                </div>
                                                <div class="col-12 mb-5">
                                                    <button type="submit" class="btn btn-primary">Submit</button>
                                                </div>
                                            </form:form>
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