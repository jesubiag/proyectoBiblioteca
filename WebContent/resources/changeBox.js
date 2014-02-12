$(function() {
    $('.select1').change(function() {
        $('#changeBox').toggle($(this).val() > 1);
        if(document.getElementById("prestamito").value == 1){
			var today = new Date();
        	var dd = today.getDate();
        	var mm = today.getMonth()+1; //January is 0!

        	var yyyy = today.getFullYear();
        	if(dd<10){dd='0'+dd;} if(mm<10){mm='0'+mm;} today = yyyy+'-'+mm+'-'+dd;
        	var elem = document.getElementById("fecha");
        	elem.value = today;
        	}
        else
        	{
        	var elem = document.getElementById("fecha");
        	elem.value = "";
        	}
    });
});