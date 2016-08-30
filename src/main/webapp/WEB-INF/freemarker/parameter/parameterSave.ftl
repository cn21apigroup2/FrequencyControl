<input type="hidden" id="userId" value="${userId}"/>
<input type="hidden" id="appId" value="${appId}"/>
<input type="hidden" id="interfaceId" value="${interfaceId}"/>
<script>
var userId = document.getElementById("userId").value;
var appId = document.getElementById("appId").value;
var interfaceId = document.getElementById("interfaceId").value;
var url="${mediaHost}/parameter/list/"+userId+"/"+appId+"/"+interfaceId;
window.location.href=url;
</script>
