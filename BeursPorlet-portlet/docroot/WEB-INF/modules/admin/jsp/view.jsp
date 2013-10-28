<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@include file="view-js.jsp" %>
<portlet:defineObjects />

<form method="POST" class="form" action="${flowExecutionUrl}" >
<!-- 	<input type="hidden" class="state" name="_eventId" value="submit" /> -->
	<div class="beursAdmin">
		<div>
			<h1>Welkom admin, Selecteer hier u dranken</h1>
		</div>
		<div class="drinksContent">
			<div class="row">
				<div class="column  title">
					<p>Naam</p>
				</div>
				<div class="column  title">
					<p>Prijs</p>
				</div>
				<div class="column  title">
				</div>
				<div class="column  title">
					<p>Aantal</p>
				</div>
				<div class="column  title">
					<p>Subtotaal</p>
				</div>
			</div>
			<c:forEach items="${applData.drinks}" var="drink" varStatus="counter">
				<div class="row">
					<div class="column name">${drink.name}</div>
					<div class="column price">${drink.currentPrice}&nbsp;&euro;</div>
					<div class="column buttons">
						<input class="buttonOne" type="button" value="+1" />
						<input class="buttonFive" type="button" value="+5" />
						<input class="buttonTen" type="button" value="+10" />
					</div>
					<div class="column aantal"><input class="subtotalAmount number" type="text" value="0" name="drinks[${counter.count - 1}].amount"/></div>
					<div class="column subtotaal"><input class="subtotalPrice money" type="text" value="0"  name="drinks[${counter.count - 1}].subtotal"/></div>
				</div>
			</c:forEach>
			<div class="row">
				<div class="totaal">
					<p>Totaal: </p><input type="text" value="0.00" class="absoluutTotal money" name="totalAmount" />
					<input type="submit" class="verzenden" name="_eventId_submit" value="verzenden" />
					<input type="button" class="button reset" value="clear All" />
					<input type="submit" class="manipuleer" name="_eventId_manipulate" value="manipuleer" />
				</div>
			</div>
			<div class="row">
				<div class="totaal">
					<b>Vorig Totaal</b>
					<b style="color: red">${applData.previousTotal }</b>
				</div>
			</div>
		</div>
	</div>
</form>