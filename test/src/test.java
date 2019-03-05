import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeMap;


public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
/*		String rr="1.00";
  Float e=Float.parseFloat(rr);
  Long e1=Long.parseLong("1");
  System.out.println(e);*/
		
		 Map<String, String> treeMap1 = new HashMap<String, String>(new Comparator<String>(){  
			  
	            /* 
	             * int compare(Object o1, Object o2) 返回一个基本类型的整型， 
	             * 返回负数表示：o1 小于o2， 
	             * 返回0 表示：o1和o2相等， 
	             * 返回正数表示：o1大于o2。 
	             */  
	            public int compare(String o1, String o2) {  
	              
	                //指定排序器按照降序排列  
	                return o2.compareTo(o1);  
	            }     
	        });   
	        treeMap1.put("未开始", "1");  
	        treeMap1.put("进行中", "2");  
	        treeMap1.put("已完成", "3");  
	        treeMap1.put("进行中", "4");  
	        System.out.println("treeMap1="+treeMap1);  
	        
	        
	}

}
