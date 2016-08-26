<input type="hidden" id="userId" value="${userId}"/>
<script>
var userId = document.getElementById("userId").value;
var url="${mediaHost}/app/list/"+userId;
window.location.href=url;
</script>
