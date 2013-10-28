<script type="text/javascript">
	$(document).ready(function() {
		$('.buttonOne').click(function(){
			addValueToSubtotaalAmount($(this), 1);
		});
		$('.buttonFive').click(function(){
			addValueToSubtotaalAmount($(this), 5);
		});
		$('.buttonTen').click(function(){
			addValueToSubtotaalAmount($(this), 10);
		});
		$('.money').each(function(){
			formatMoney($(this));
		});
		$('.money').blur(function(){
			formatMoney($(this));
		});
		$('.subtotalAmount').blur(function(){
			calculateSubtotal($(this));
		});
		$('.verzenden').click(function(){
// 			$('state').val('submit');
			$('form').submit();
		});
		$('.manipuleer').click(function(){
// 			$('state').val('manipulate');
			$('form').submit();
		});
		
		$('.number').blur(function(){
			if(!isNumber($(this))){
				$(this).val("0");
			}
		});
		$('.reset').click(function(){
			$('.subtotalAmount').each(function(){
				$(this).val('0');
			});
			$('.subtotalPrice').each(function(){
				$(this).val('0,00');
			});
			$('.subtotalPrice').val('0,00');
			$('.absoluutTotal').val('0,00');
		});
		
	});
	function updateTotaal(){
		var totaal = 0.00;
		$('.subtotalPrice').each(function(){
			var tmp = $(this).val().replace(',', '.');
			totaal = parseFloat(totaal) + parseFloat(tmp);
		});
		var tot = String(totaal);
		$('.absoluutTotal').val(tot);
		formatMoney($('.absoluutTotal'));
	}
	function calculateSubtotal(obj){
		var price=0;
		$(obj).parent().siblings().each(function(){
			if($(this).attr('class') == 'column price'){
				price = $(this).html();
				return;
			}
		});
		var newVal = price.replace(',', '.');
		var subtotal = parseInt($(obj).val()) * parseFloat(newVal);
		$(obj).parent().siblings().each(function(){
			if($(this).attr('class') == 'column subtotaal'){
				$(this).children().val(subtotal);
				formatMoney($(this).children());
				updateTotaal();
				return;
			}
		});
		
	}
	
	function addValueToSubtotaalAmount(obj, val){
		$(obj).parent().siblings().each(function(){
			if($(this).attr('class') == 'column aantal'){
				$(this).children().val(parseInt($(this).children().val()) + parseInt(val));  
				calculateSubtotal($(this).children());
			}
		});
	}
	function formatMoney(obj){
		if(isNumber(obj)){
			var indexDot = $(obj).val().indexOf('.');
			var indexKomma = $(obj).val().indexOf(',');
			var newVal = '0,00';
			if(indexDot !=-1 ){
				newVal = formatDecimal ($(obj).val(), '.');
			} else if(indexKomma !=-1 ){
				newVal = formatDecimal ($(obj).val(), ',');
			} else{
				newVal = $(obj).val() + ",00";
			}
			$(obj).val(newVal);
		}
		else{
			$(obj).val("0,00");
		}
	}
	function formatDecimal(val, sep){
		var n=val.split(sep);
		var newVal = '0,00';
		if(n[1].length < 2){
			newVal = val + "0";
		} else if(n[1].length == 2){
			newVal =  val;
		} else if(n[1].length > 2){
			newVal = n[0] + ','+ n[1].substring(0,2);
		}
		newVal = newVal.replace('.', ',');
		return newVal;
		
	}
	function isNumber(obj){
		var value = $(obj).val();
		value = value.replace(',','.');
		if(isNaN(value)){
			return false;
		}
		else{
			return true;
		}
	}
</script>