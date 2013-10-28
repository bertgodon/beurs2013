<script type="text/javascript">
	$(document).ready(function() {
		$('.verzenden').click(function(){
			$('.state').val('submit');
			$('.form').submit();		
		});
		$('.back').click(function(){
			$('.state').val('back');
			$('.form').submit();		
		});
		
		$('.money').each(function(){
			formatMoney($(this));
		});
		$('.money').blur(function(){
			formatMoney($(this));
		});
	});
	
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