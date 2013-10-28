<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<script type="text/javascript">
	$(document).ready(function() {
		$('.dockbar').hide();
		$('.refresh').click(function(){
			getNewData();
		});	
		var lineChartData = {
				labels : ["January","February","March","April","May","June","July"],  //dit moeten uren worden
				datasetFill : false,
				datasets : [
					{
						strokeColor : "rgba(220,220,220,1)",
						pointColor : "rgba(220,220,220,1)",
						pointStrokeColor : "#fff",
						data : [65,59,90,81,56,55,40]
					},
					{
						strokeColor : "rgba(151,187,205,1)",
						pointColor : "rgba(151,187,205,1)",
						pointStrokeColor : "#fff",
						data : [28,48,40,19,96,27,100]
					}
				]
				
			}
		var ctx = document.getElementById("canvas").getContext("2d");
		var myLine = new Chart(ctx).Line(lineChartData);
		
		var timerId = setInterval(function() {
			getNewData();
		}, 1000); // 1000 makes this code execute every 1 second
	});
	function getNewData(){
		var url = '<portlet:resourceURL id="drinks" />';
		$.getJSON(url, function(data){
			updatePrice(data);
		});
	}
	function updatePrice(data){
		for(index in data){
			$('.drink').each(function(){
				if($(this).children('.name').html() == data[index].name){
					$(this).children('.price').html(data[index].currentPrice +"&nbsp;&euro;"  );
				}
			});
		}
	}
</script>

<portlet:defineObjects />

<div class="beursAdmin">
	<div>
		<h1>Overzicht van de prijzen in de beurs</h1>
	</div>
	<div class="listofdrinks">
		<c:forEach items="${drinks}" var="drink" varStatus="counter">
			<div class="drink">
				<div class="picture  Beer${counter.count}"></div>
				<div class="name">${drink.name}</div>
				<div class="price">${drink.currentPrice}  &euro;</div>
			</div>
		</c:forEach>
	</div>
	<div class="graph">
		<canvas id="canvas" height="450" width="600"></canvas>
	</div>
<!-- 		<input type="button" class="refresh" value="Prijzen vernieuwen"/> -->
	
</div>