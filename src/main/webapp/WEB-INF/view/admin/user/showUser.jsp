<html lang="en">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Document</title>
            <!-- Latest compiled and minified CSS -->
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
            <!-- Latest compiled JavaScript -->
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

        </head>

        <body>
            <div class="container container-md mt-5">
                <div class="row">
                    <div class="col-12 mx-auto">
                        <div class="border-bottom border-2 border-primary">
                            <div class="d-flex justify-content-between">
                                <h2>Table Users</h2>
                                <a href="user/create" class="btn btn-primary mb-1">Create User</a>
                            </div>
                        </div>

                        <div class="my-3">
                            <table class="table table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <th scope="col">#</th>
                                        <th scope="col">Full Name</th>
                                        <th scope="col">Address</th>
                                        <th scope="col">Phone</th>
                                        <th scope="col">Email</th>
                                        <th scope="col">Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="user" items="${users}">
                                        <tr>
                                            <td>${user.id}</td>
                                            <td>${user.fullName}</td>
                                            <td>${user.address}</td>
                                            <td>${user.phone}</td>
                                            <td>${user.email}</td>
                                            <td>

                                                <button class="btn btn-success">View</button>
                                                <button class="btn btn-warning mx-2">Update</button>
                                                <button class="btn btn-danger">Delete</button>

                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>



                    </div>

                </div>
            </div>

        </body>

</html>
<!-- <td>
    <button class="btn btn-success">View</button>
    <button class="btn btn-warning mx-2">Update</button>
    <button class="btn btn-danger">Delete</button>
</td> -->