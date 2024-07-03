<html lang="en">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Delete User ${id}</title>
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
                        <h3 class="border-bottom border-2 border-light">Delete User With ID ${id}</h3>
                        <div class="alert alert-danger " role="alert">
                            Please confirm that you want to delete user ${id}
                        </div>
                        <form:form method="post" action="/admin/user/delete" modelAttribute="user">
                            <div class="mb-3" style="display:none">
                                <label for="exampleInputPassword1" class="form-label">ID</label>
                                <form:input type="text" class="form-control" path="id" value="${id}" />
                            </div>
                            <button class="btn btn-danger">Delete</button>
                        </form:form>
                    </div>
                </div>
            </div>
        </body>

</html>