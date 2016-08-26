<input type="hidden" id="userId" value="${userId}"/>
<input type="hidden" id="appId" value="${appId}"/>
<script>
var userId = document.getElementById("userId").value;
var appId = document.getElementById("appId").value;
var url="${mediaHost}/interface/list/"+userId+"/"+appId;
window.location.href=url;
</script>
