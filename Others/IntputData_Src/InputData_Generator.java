package pair_programming;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class InputData_Generator {
	

	/** 
	 * 随机指定范围内N个不重复的数 
	 * 在初始化的无重复待选数组中随机产生一个数放入结果中， 
	 * 将待选数组被随机到的数，用待选数组(len-1)下标对应的数替换 
	 * 然后从len-2里随机产生下一个随机数，如此类推 
	 * @param max  指定范围最大值 
	 * @param min  指定范围最小值 
	 * @param n  随机数个数 
	 * @return int[] 随机数结果集 
	 */   
    public static int[] randomCommon(int min, int max, int n){ 
    	 int len = max-min+1;  
         
    	    if(max < min || n > len){  
    	        return null;  
    	    }  
    	      
    	    //初始化给定范围的待选数组  
    	    int[] source = new int[len];  
    	       for (int i = min; i < min+len; i++){  
    	        source[i-min] = i;  
    	       }  
    	         
    	       int[] result = new int[n];  
    	       Random rd = new Random();  
    	       int index = 0;  
    	       for (int i = 0; i < result.length; i++) {  
    	        //待选数组0到(len-2)随机一个下标  
    	           index = Math.abs(rd.nextInt() % len--);  
    	           //将随机到的数放入结果集  
    	           result[i] = source[index];  
    	           //将待选数组中被随机到的数，用待选数组(len-1)下标对应的数替换  
    	           source[index] = source[len];  
    	       }  
    	       return result;  
    }  

	
	public static void main(String[] args) throws JSONException, IOException {
		
		JSONObject inputobj = new JSONObject();
		// new Random().nextInt(length); 生成0-(length-1)可重复随机数
		String[] tagsStrings = new String[]{"English","basketball","dance","programming","football","reading","music","programming","chess","film","study"};
		String[] Stu_dataStrings = new String[]{"Mon.8: 00~10: 00","Mon.10: 00~12: 00","Mon.12: 00~14: 00","Mon.14: 00~16: 00","Mon.20: 00~22: 00","Tues.8: 00~10: 00","Tues.10: 00~12: 00","Tues.12: 00~14: 00","Tues.14: 00~16: 00","Tues.20: 00~22: 00","Wed.8: 00~10: 00","Wed.10: 00~12: 00","Wed.12: 00~14: 00","Wed.14: 00~16: 00","Wed.20: 00~22: 00","Thur.8: 00~10: 00","Thur.10: 00~12: 00","Thur.12: 00~14: 00","Thur.14: 00~16: 00","Thur.20: 00~22: 00","Fri.8: 00~10: 00","Fri.10: 00~12: 00","Fri.12: 00~14: 00","Fri.14: 00~16: 00","Fri.20: 00~22: 00","Sat.8: 00~10: 00","Sat.10: 00~12: 00","Sat.12: 00~14: 00","Sat.14: 00~16: 00","Sat.20: 00~22: 00","Sun.8: 00~10: 00","Sun.10: 00~12: 00","Sun.12: 00~14: 00","Sun.14: 00~16: 00","Sun.20: 00~22: 00",};
		String[] Dep_dataStrings = new String[]{"Mon.8: 00~9: 00","Mon.9: 00~10: 00","Mon.10: 00~11: 00","Mon.11: 00~12: 00","Mon.14: 00~15: 00","Mon.15: 00~16: 00","Mon.20: 00~21: 00","Mon.21: 00~22: 00","Tues.8: 00~9: 00","Tues.9: 00~10: 00","Tues.10: 00~11: 00","Tues.11: 00~12: 00","Tues.14: 00~15: 00","Tues.15: 00~16: 00","Tues.20: 00~21: 00","Tues.21: 00~22: 00","Wed.8: 00~9: 00","Wed.9: 00~10: 00","Wed.10: 00~11: 00","Wed.11: 00~12: 00","Wed.14: 00~15: 00","Wed.15: 00~16: 00","Wed.20: 00~21: 00","Wed.21: 00~22: 00","Thur.8: 00~9: 00","Thur.9: 00~10: 00","Thur.10: 00~11: 00","Thur.11: 00~12: 00","Thur.14: 00~15: 00","Thur.15: 00~16: 00","Thur.20: 00~21: 00","Thur.21: 00~22: 00","Fri.8: 00~9: 00","Fri.9: 00~10: 00","Fri.10: 00~11: 00","Fri.11: 00~12: 00","Fri.14: 00~15: 00","Fri.15: 00~16: 00","Fri.20: 00~21: 00","Fri.21: 00~22: 00","Sat.8: 00~9: 00","Sat.9: 00~10: 00","Sat.10: 00~11: 00","Sat.11: 00~12: 00","Sat.14: 00~15: 00","Sat.15: 00~16: 00","Sat.20: 00~21: 00","Sat.21: 00~22: 00","Sun.8: 00~9: 00","Sun.9: 00~10: 00","Sun.10: 00~11: 00","Sun.11: 00~12: 00","Sun.14: 00~15: 00","Sun.15: 00~16: 00","Sun.20: 00~21: 00","Sun.21: 00~22: 00"};
		String[] stu_numberStrings = new String[]{"031502001","031502002","031502003","031502004","031502005","031502006","031502007","031502008","031502009","031502010","031502011","031502012","031502013","031502014","031502015","031502016","031502017","031502018","031502019","031502020","031502021","031502022","031502023","031502024","031502025","031502026","031502027","031502028","031502029","031502030","031502031","031502032","031502033","031502034","031502035","031502036","031502037","031502038","031502039","031502040","031502041","031502042","031502043","031502044","031502045","031502046","031502047","031502048","031502049","031502050","031502051","031502052","031502053","031502054","031502055","031502056","031502057","031502058","031502059","031502060","031502061","031502062","031502063","031502064","031502065","031502066","031502067","031502068","031502069","031502070","031502071","031502072","031502073","031502074","031502075","031502076","031502077","031502078","031502079","031502080","031502081","031502082","031502083","031502084","031502085","031502086","031502087","031502088","031502089","031502090","031502091","031502092","031502093","031502094","031502095","031502096","031502097","031502098","031502099","031502100","031502101","031502102","031502103","031502104","031502105","031502106","031502107","031502108","031502108","031502109","031502110","031502111","031502112","031502113","031502114","031502115","031502116","031502117","031502118","031502119","031502120","031502121","031502122","031502123","031502124","031502125","031502126","031502127","031502128","031502129","031502130","031502131","031502132","031502133","031502134","031502135","031502136","031502137","031502138","031502139","031502140","031502141","031502142","031502143","031502144","031502145","031502146","031502147","031502148","031502149","031502150","031502151","031502152","031502153","031502154","031502155","031502156","031502157","031502158","031502159","031502160","031502161","031502162","031502163","031502164","031502165","031502166","031502167","031502168","031502169","031502170","031502171","031502172","031502173","031502174","031502175","031502176","031502177","031502178","031502179","031502180","031502181","031502182","031502183","031502184","031502185","031502186","031502187","031502188","031502189","031502190","031502191","031502192","031502193","031502194","031502195","031502196","031502197","031502198","031502199","031502200","031502201","031502202","031502203","031502204","031502205","031502206","031502207","031502208","031502209","031502210","031502211","031502212","031502213","031502214","031502215","031502216","031502217","031502218","031502219","031502220","031502221","031502222","031502223","031502224","031502225","031502226","031502227","031502228","031502229","031502230","031502231","031502232","031502233","031502234","031502235","031502236","031502237","031502238","031502239","031502240","031502241","031502242","031502243","031502244","031502245","031502246","031502247","031502248","031502249","031502250","031502251","031502252","031502253","031502254","031502255","031502256","031502257","031502258","031502259","031502260","031502261","031502262","031502263","031502264","031502265","031502266","031502267","031502268","031502269","031502270","031502271","031502272","031502273","031502274","031502275","031502276","031502277","031502278","031502279","031502280","031502281","031502282","031502283","031502284","031502285","031502286","031502287","031502288","031502289","031502290","031502291","031502292","031502293","031502294","031502295","031502296","031502297","031502298","031502299","031502300"};
		String[] Dep_numberStrings = new String[]{"D001","D002","D003","D004","D005","D006","D007","D008","D009","D010","D011","D012","D013","D014","D015","D016","D017","D018","D019","D020"};
		JSONArray jsArray = new JSONArray();
		JSONArray jsArray1 = new JSONArray();
		for (int i = 0; i < 300; i++) {
			
			int randnum = new Random().nextInt(Stu_dataStrings.length - 1) + 1;
			int[] RandArray = randomCommon(0,Stu_dataStrings.length-1,randnum);
			ArrayList<String> arrayList = new ArrayList<String>();
			for (int j = 0; j < randnum ; j++){
				arrayList.add(Stu_dataStrings[RandArray[j]]);
				}
			
			ArrayList<String> arrayList1 = new ArrayList<String>();
			int randnum1 = new Random().nextInt(4) + 1;
			int[] RandArray1 = randomCommon(0,Dep_numberStrings.length-1,randnum1);
			for (int j = 0; j < randnum1; j++) {
				arrayList1.add(Dep_numberStrings[RandArray1[j]]);
			}
			
			ArrayList<String> arrayList2 = new ArrayList<String>();
			int randnum2 = new Random().nextInt(tagsStrings.length - 1) + 1;
			int[] RandArray2 = randomCommon(0,tagsStrings.length-1,randnum2);
			for (int j = 0; j < randnum2; j++) {
				arrayList2.add(tagsStrings[RandArray2[j]]);
			}
			
			
			
			JSONObject js = new JSONObject();
			js.put("free_time", arrayList);
			js.put("student_no",stu_numberStrings[i]);
			js.put("applications_department", arrayList1);
			js.put("tags", arrayList2);
			jsArray.put(js);
			
		}//300个学生数据生成
		inputobj.put("students", jsArray);
		
		for (int i = 0; i < 20; i++) {
			int randnum = new Random().nextInt(Dep_dataStrings.length - 1 ) + 1;
			int[] RandArray = randomCommon(0,Dep_dataStrings.length-1,randnum);
			ArrayList<String> arrayList = new ArrayList<String>();
			for (int j = 0; j < randnum ; j++){
				arrayList.add(Dep_dataStrings[RandArray[j]]);
				}
			
			
			
			ArrayList<String> arrayList2 = new ArrayList<String>();
			int randnum2 = new Random().nextInt(tagsStrings.length - 1) + 1;
			int[] RandArray2 = randomCommon(0,tagsStrings.length-1,randnum2);
			for (int j = 0; j < randnum2; j++) {
				arrayList2.add(tagsStrings[RandArray2[j]]);
			}
			
			
			
			JSONObject js = new JSONObject();
			js.put("event_schedules", arrayList);
			js.put("member_limit",new Random().nextInt(5) + 10);
			js.put("department_no", Dep_numberStrings[i]);
			js.put("tags", arrayList2);
			jsArray1.put(js);
			
			
		}
		//20个部门数据生成
		inputobj.put("departments", jsArray1);
		
		//System.out.println(inputobj);
		BufferedWriter bufw = new BufferedWriter(new FileWriter("InputData_generator.txt"));
		bufw.write(inputobj.toString());
		bufw.flush(); 
		}
		
	
}
