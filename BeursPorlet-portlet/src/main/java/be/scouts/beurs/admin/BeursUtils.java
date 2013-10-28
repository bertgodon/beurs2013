package be.scouts.beurs.admin;

public class BeursUtils {

	public static float FromStringToFloat(String in){
		String formatted = in.replace(",", ".");
		return Float.parseFloat(formatted);
	}
	public static String FromFloatToString(float num){
		String fl = String.valueOf(num);
		return fl.replace(".",",");
	}
	public static String roundAmount(String in){
		String[] a = in.split(",");
		if(a[1].length() > 2){
			if(Integer.parseInt(a[1].substring(2, 3))>=5){
				return a[0] +"," +a[1].substring(0, 1) + sum(Integer.parseInt(a[1].substring(1, 2)),1);
			}
			else{ //omdat we gierig zijn
				return a[0] +"," +a[1].substring(0, 1) + sum(Integer.parseInt(a[1].substring(1, 2)),1);
			}
		}
		else{
			return in;
		}
	}
	public static int sum(int i, int j){
		return i + j ;
	}
	public static void main(String[] args){
		System.out.println("main started");
//		String var1 = "30,01";
//		String var2 = "20,003";
//		String var3 = "120,1";
//		String var4 = "40,985";
//		String var5 = "40,988";
//		String var6 = "40,984";
//		System.out.println(BeursUtils.roundAmount(var1));
//		System.out.println(BeursUtils.roundAmount(var2));
//		System.out.println(BeursUtils.roundAmount(var3));
//		System.out.println(BeursUtils.roundAmount(var4));
//		System.out.println(BeursUtils.roundAmount(var5));
//		System.out.println(BeursUtils.roundAmount(var6));
		String amount = "1";
		int weight = 4;
//		float res = Integer.parseInt(amount) * weight / 100 / (data.getDrinks().size()+1);
	}
}
