<html lang="en">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

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
        <div class="container mt-5">
            <div class="row">
                <div class="col-12 mx-auto">
                    <h3>Create a User</h3>
                    <form:form method="post" action="/admin/user/create" modelAttribute="newUser">
                        <div class="mb-3">
                            <label for="exampleInputEmail1" class="form-label">Email address</label>
                            <form:input type="email" class="form-control" path="email" />

                        </div>
                        <div class="mb-3">
                            <label for="exampleInputPassword1" class="form-label">Password</label>
                            <form:input type="password" class="form-control" path="password" />
                        </div>
                        <div class="mb-3">
                            <label for="exampleInputPassword1" class="form-label">Full Name</label>
                            <form:input type="text" class="form-control" path="fullName" />
                        </div>
                        <div class="mb-3">
                            <label for="exampleInputPassword1" class="form-label">Adress</label>
                            <form:input type="text" class="form-control" path="address" />
                        </div>
                        <div class="mb-3">
                            <label for="exampleInputPassword1" class="form-label">Phone</label>
                            <form:input type="text" class="form-control" path="phone" />
                        </div>

                        <button type="submit" class="btn btn-primary">Submit</button>
                    </form:form>
                </div>
            </div>
        </div>
    </body>

</html>