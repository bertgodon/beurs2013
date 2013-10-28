<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<script type="text/javascript">
	var colors;
	var values;
	var beers;
	var beerPref;
	var data;
	var json;
	var line;
	function updateGraph(number, obj){
		if($(obj).is(':checked')){
			beerPref[number-1] = true;
		}
		else{
			beerPref[number-1] = false;
		}
		createGraph();
	}
	
	function createLine(values){
		var count  = 0;
		json = new Array();
		if(beerPref[0]){
			json[count] ={
					fillColor : "rgba(220,220,220,0.0)",
					strokeColor : "rgba(220,220,220,1)",
					pointColor : "rgba(220,220,220,1)",
					pointStrokeColor : "#fff",
					data : values[0]
			};
			count++;
		}
		if(beerPref[1]){
			json[count] ={
					fillColor : "rgba(151,187,205,0.0)",
					strokeColor : "rgba(151,187,205,1)",
					pointColor : "rgba(151,187,205,1)",
					pointStrokeColor : "#fff",
					data : values[1]
			};
			count++;
		}
		if(beerPref[2]){
			json[count] ={
					fillColor : "rgba(0,100,0,0.0)",
					strokeColor : "rgba(0,100,0,1)",
					pointColor : "rgba(0,100,0,1)",
					pointStrokeColor : "#fff",
					data : values[2]
			};
			count++;
		}
		if(beerPref[3]){
			json[count] ={
					fillColor : "rgba(255,255,0,0.0)",
					strokeColor : "rgba(255,255,0,1)",
					pointColor : "rgba(255,255,0,1)",
					pointStrokeColor : "#fff",
					data : values[3]
			};
			count++;
		}
		if(beerPref[4]){
			json[count] ={
					fillColor : "rgba(188,143,143,0.0)",
					strokeColor : "rgba(188,143,143,1)",
					pointColor : "rgba(188,143,143,1)",
					pointStrokeColor : "#fff",
					data : values[4]
			};
			count++;
		}
		if(beerPref[5]){
			json[count] ={
					fillColor : "rgba(178,34,34,0.0)",
					strokeColor : "rgba(178,34,34,1)",
					pointColor : "rgba(178,34,34,1)",
					pointStrokeColor : "#fff",
					data : values[5]
			};
			count++;
		}
		if(beerPref[6]){
			json[count] ={
					fillColor : "rgba(255,0,0,0.0)",
					strokeColor : "rgba(255,0,0,1)",
					pointColor : "rgba(255,0,0,1)",
					pointStrokeColor : "#fff",
					data : values[6]
			};
			count++;
		}
		if(beerPref[7]){
			json[count] ={
					fillColor : "rgba(147,112,219,0.0)",
					strokeColor : "rgba(147,112,219,1)",
					pointColor : "rgba(147,112,219,1)",
					pointStrokeColor : "#fff",
					data : values[7]
			};
			count++;
		}
		return json;
	
	}
	function createLineCharts(values, data){
		line = {
				labels : data, 
				datasetFill : false, 
				datasets : createLine(values)
				} ;
		return line;
	}
	function createGraph(){
		data = ${graphData.horizontalValue};
		values = ${graphData.lineValue};
		beers = '${graphData.beers}';
		var lineChartData = createLineCharts(values, data);
		var ctx = document.getElementById("canvas").getContext("2d");
		var config = new Object();
		config.animation = false;
		var myLine = new Chart(ctx).Line(lineChartData,config);
	}
	
	$(document).ready(function() {
		colors = [];
		beerPref = [true, true, true, true,true, true, true, true]; 
		$('.refresh').click(function(){
		    location.reload();
		});		
		
		data = ${graphData.horizontalValue};
		values = ${graphData.lineValue};
		beers = '${graphData.beers}';
		var lineChartData = createLineCharts(values, data);
		var ctx = document.getElementById("canvas").getContext("2d");
		var myLine = new Chart(ctx).Line(lineChartData);
	});
	
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
				<div class="price">${drink.currentPrice}  &#128;</div>
			</div>
		</c:forEach>
	</div>
	
	<div class="graph">
		<canvas id="canvas" height="450" width="600"></canvas>
	</div>
	<div class="legende">
		<c:forEach items="${drinks}" var="wink"  varStatus="count">
			<div class="item" style="color : rgb(${colors[count.count-1]})">
				<input type="checkbox" checked="checked" id="checkbox"  onclick="updateGraph(${count.count}, $(this))" >
				${wink.name}
			</div>
		</c:forEach>
	</div>
	<input type="button" class="refresh mobile" value="Prijzen vernieuwen"/>
</div>