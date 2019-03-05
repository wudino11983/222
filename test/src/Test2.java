import java.util.Calendar;


public class Test2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		java.util.Calendar cal = java.util.Calendar.getInstance();
		int monthDays = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
		System.out.println(monthDays);
		
		Calendar cal1 = Calendar.getInstance();
    	cal1.set(Calendar.YEAR,2015);
    	cal1.set(Calendar.MONTH, 2 - 1);//Java月份才0开始算
    	int dateOfMonth = cal.getActualMaximum(Calendar.DATE);
    	System.out.println(dateOfMonth);
	}

}
