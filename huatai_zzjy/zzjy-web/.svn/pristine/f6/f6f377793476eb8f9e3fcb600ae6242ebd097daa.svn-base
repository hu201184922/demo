<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- <%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables" %> --%>
<meta name="decorator" content="nodecorate" />

<datatables:table id="userListTable" data="${userList }" row="user" stateSave="true" processing="true" paginationType="full_numbers" cssClass="data-tbl-striped table table-striped table-bordered">
   <datatables:column title="姓名" property="name" sortInit="asc" />
   <datatables:column title="登录名" property="loginName" searchable="false" sortable="false" />
   <datatables:column title="岗位" searchable="false" sortable="false" >${user.person.post.name }</datatables:column>
   <datatables:column title="隶属于" >${user.person.org.name }</datatables:column>
   <datatables:column title="操作" cssClass="center" sortable="false" searchable="false" >
   		<a href="javascript:void(0)" class="candidateUser" userId="${user.id }" userName="${user.name }"><i class="icon-edit"></i> 选中</a>
   </datatables:column>
</datatables:table>