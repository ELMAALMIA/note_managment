<%@ page import="estm.dsic.web01.Model.Notes" %>
<%@ page import="java.util.Vector" %>
<%@ page import="java.util.List" %>
<%@ page import="estm.dsic.web01.Model.User" %>
<%
  List<Notes> vectorNotes = (List<Notes>) session.getAttribute("notesList");
    User user = (User) session.getAttribute("user");
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
<%--  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">--%>
  <title> notes </title>
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/bootstrap-5.0.2-dist/css/bootstrap.css">
  <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
  <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/noteStyle.css">
</head>
<body>

<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <!-- Container wrapper -->
  <div class="container-fluid">
    <!-- Toggle button -->
    <!-- Collapsible wrapper -->
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <!-- Navbar brand -->
      <!-- Left links -->
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link" href="#"><%=user.getUsername() %></a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Projects</a>
        </li>
      </ul>
      <!-- Left links -->
    </div>
    <!-- Collapsible wrapper -->

    <!-- Right elements -->
    <div class="d-flex align-items-center">
      <!-- Icon -->
        <ul>
          <li>
            <a class="dropdown-item" href="#logoutdiv"  data-toggle="modal">Logout</a>
          </li>
        </ul>
    </div>
    <!-- Right elements -->
  </div>
  <!-- Container wrapper -->
</nav>
<!-- Navbar -->


<div class="container-xl">
  <div class="table-responsive">
    <div class="table-wrapper">
      <div class="table-title">
        <div class="row">
          <div class="col-sm-6">
            <h2>Manage <b>Note</b></h2>
          </div>
          <div class="col-sm-6">
<%--            <a href="#addnote" ata-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Save Change</span></a>--%>
           <form  action="NotesServlet" method="get">
             <button type="submit" name="action"   class="btn btn-info" value="save"><i class="material-icons">&#xE15C;</i> <span>Save change</span></button>
           </form>
  <a href="#addnote" class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Add New Note</span></a>
          </div>
        </div>
      </div>
      <table class="table table-striped table-hover">
        <thead>
        <tr>
          <th>Title</th>
          <th>Description</th>
          <th>Date</th>
          <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <% for (Notes note : vectorNotes) { %>
        <tr>
         <td><%= note.getTitle() %></td>
          <td><%= note.getDescription() %></td>
          <td><%= note.getDate() %></td>
          <td>
            <a href="#editnote" class="edit" data-toggle="modal" data-id="<%=vectorNotes.indexOf(note) %>"><i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>
            <a href="#deletenote" class="delete" data-toggle="modal" data-id="<%=vectorNotes.indexOf(note) %>"><i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>
          </td>
        </tr>
        <% } %>
        </tbody>
      </table>
      <div class="clearfix">
        <div class="hint-text">Showing <b>5</b> out of <b>25</b> entries</div>
        <ul class="pagination">
          <li class="page-item disabled"><a href="#">Previous</a></li>
          <li class="page-item"><a href="#" class="page-link">1</a></li>
          <li class="page-item"><a href="#" class="page-link">2</a></li>
          <li class="page-item active"><a href="#" class="page-link">3</a></li>
          <li class="page-item"><a href="#" class="page-link">4</a></li>
          <li class="page-item"><a href="#" class="page-link">5</a></li>
          <li class="page-item"><a href="#" class="page-link">Next</a></li>
        </ul>
      </div>
    </div>
  </div>
</div>


<!-- add note -->
<div id="addnote" class="modal fade">
  <div class="modal-dialog">
    <div class="modal-content">
      <form action="NotesServlet" method="get">
        <div class="modal-header">
          <h4 class="modal-title">Add Note</h4>
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        </div>

        <div class="modal-body">
          <div class="form-group">
            <label>Title</label>
            <input type="text" class="form-control" name="title" required>
          </div>
          <div class="form-group">
            <label>Description</label>
            <textarea class="form-control" name="description" required></textarea>
          </div>
        </div>
        <div class="modal-footer">
          <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
          <input type="submit" class="btn btn-success" name="action" value="AddNote">
        </div>
      </form>
    </div>
  </div>
</div>

<!-- Edit note -->
<div id="editnote" class="modal fade">
  <div class="modal-dialog">
    <div class="modal-content">
      <form action="NotesServlet" method="get">
        <div class="modal-header">
          <h4 class="modal-title">Edit Note</h4>
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        </div>
        <div class="modal-body">
          <input type="hidden" id="edit-id" name="edit-id" >
          <div class="form-group">
            <label>Title</label>
            <input type="text" name="edit-title" class="form-control" id="edit-title" required>
          </div>
          <div class="form-group">
            <label>Description</label>
            <textarea class="form-control" name="edit-description" id="edit-description" required ></textarea>
          </div>
        </div>
        <div class="modal-footer">
          <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
          <input type="submit" name="action" class="btn btn-info" value="modifyNote">
        </div>
      </form>
    </div>
  </div>
</div>

<!-- Delete note -->
<div id="deletenote" class="modal fade">
  <div class="modal-dialog">
    <div class="modal-content">
      <form action="NotesServlet" method="get">
        <div class="modal-header">
          <h4 class="modal-title">Delete note</h4>
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        </div>
        <input type="hidden" id="id_delete" name="id_delete" >
        <div class="modal-body">
          <p>Are you sure you want to delete this note?</p>
          <p class="text-warning"><small>This action cannot be undone.</small></p>
        </div>
        <div class="modal-footer">
          <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
          <input type="submit" class="btn btn-danger" value="deleteNote" name="action">
        </div>
      </form>
    </div>
  </div>
</div>
<div id="logoutdiv" class="modal fade">
  <div class="modal-dialog">
    <div class="modal-content">
      <form action="NotesServlet" method="get">
        <div class="modal-header">
          <h4 class="modal-title">confirmation</h4>
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        </div>
        <div class="modal-body">
          <p>Do you want to save all change ?</p>
          <p class="text-warning"><small>This action cannot be undone.</small></p>
        </div>
        <div class="modal-footer">
          <input type="submit" class="btn btn-danger"  name="action" value="Don't save">
          <input type="submit" class="btn btn-info" value="save change" name="action">
        </div>
      </form>
    </div>
  </div>
</div>
<script src="${pageContext.request.contextPath}/Js/TableJS.js"></script>
<script>
  $(document).on("click", ".edit", function () {
    var id = $(this).data("id");
    var title = $(this).closest("tr").find("td:nth-child(1)").text();
    var description = $(this).closest("tr").find("td:nth-child(2)").text();

    $("#edit-title").val(title);
    $("#edit-description").val(description);
    $("#edit-id").val(id); // hidden input for ID
  });
  $(document).on("click", ".delete", function () {
    var id = $(this).data("id");
    $("#id_delete").val(id); // hidden input for ID
  });
</script>

</body>
</html>