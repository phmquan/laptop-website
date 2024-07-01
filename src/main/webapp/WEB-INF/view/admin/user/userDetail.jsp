<html lang="en">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>User Information ${id}</title>
            <!-- Latest compiled and minified CSS -->
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
            <!-- Latest compiled JavaScript -->
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

        </head>

        <body>
            <div class="container container-md mt-5">
                <div class="row">
                    <div class="col-8 mx-auto">
                        <div class="form-group row">
                            <div class="d-flex justify-content-between mb-3 border-bottom border-4 border-light">
                                <h2>User Information With ID = ${user.id}
                                </h2>
                                <a href="/admin/user" class="btn btn-success">Back</a>
                            </div>

                            <div class="border-top border-light border-2">
                                <div class="card">
                                    <div class="card-header">
                                        User Information
                                    </div>
                                    <ul class="list-group list-group-flush">
                                        <li class="list-group-item">Full Name: ${user.fullName}</li>
                                        <li class="list-group-item">Email: ${user.email}</li>
                                        <li class="list-group-item">Phone Number: ${user.phone}</li>
                                        <li class="list-group-item">Address: ${user.address}</li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </body>

</html>