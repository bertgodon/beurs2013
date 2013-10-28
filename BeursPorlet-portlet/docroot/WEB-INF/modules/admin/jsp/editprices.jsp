<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@include file="editprices-js.jsp" %>
<portlet:defineObjects />

<form method="POST" class="form"  action="${flowExecutionUrl}" >
	<input type="hidden" class="state" name="_eventId" value="back" />
	<c:forEach items="${applData.drinks}" var="drink" varStatus="counter">
		<div class="row">
			<div class="column name">${drink.name}</div>
			<div class="column newprice"><input class="subtotalPrice money" type="text" value="${drink.currentPrice}"  name="drinks[${counter.count - 1}].currentPrice"/></div>
		</div>
	</c:forEach>
	<input type="button" class="verzenden" name="verzenden" value="verzenden" />
	<input type="button" class="back button reset" value="back" />
</form>