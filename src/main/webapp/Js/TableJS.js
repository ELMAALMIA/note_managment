$(document).ready(function(){
    // Activate tooltip
    $('[data-toggle="tooltip"]').tooltip();

    // Select/Deselect checkboxes
    var checkbox = $('table tbody input[type="checkbox"]');
    $("#selectAll").click(function(){
        if(this.checked){
            checkbox.each(function(){
                this.checked = true;
            });
        } else{
            checkbox.each(function(){
                this.checked = false;
            });
        }
    });
    checkbox.click(function(){
        if(!this.checked){
            $("#selectAll").prop("checked", false);
        }
    });
});


$(document).on("click", ".edit", function () {
    var id = $(this).data("id");
    var title = $(this).closest("tr").find("td:nth-child(1)").text();
    var description = $(this).closest("tr").find("td:nth-child(2)").text();

    $("#edit-title").val(title);
    $("#edit-description").val(description);
    $("#edit-id").val(id); // hidden input for ID
});
