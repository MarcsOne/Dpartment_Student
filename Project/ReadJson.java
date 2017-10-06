package pair_programming;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ReadJson {
	static JSONObject[] jsonobjStudents = new JSONObject[300];
	static JSONObject[] jsonobjDepartments = new JSONObject[20];
	String s = null;
	String v = null ;
	static int StudentLength = 0;
	static int DepartmentLength = 0;
	public  void read(){
		try(BufferedReader br = new BufferedReader(new FileReader("input_data.txt")))
		{
			v= br.readLine();
			while((s = br.readLine())!=null)
			{
				v+=s;
			}
		}catch(IOException exc) {
			System.out.println("I/O Error:"+exc);
		}
		
		//读取文件中的内容
		
			try {
				JSONObject jsonobj = new JSONObject(v);
			
				JSONArray jsonarray = jsonobj.getJSONArray("students"); 
				//System.out.println(jsonarray.toString()); 
				String studengs = null;
				 //System.out.println(jsonarray.length()); 
				for (int i = 0; i < jsonarray.length(); i++) {
		        studengs = jsonarray.getString(i); 
//		        System.out.println(studengs); 
		        jsonobjStudents[i] = new JSONObject(studengs);
		        StudentLength++;
		       // System.out.println(jsonobjStudents[i].toString()); 
		        } 
				jsonarray = jsonobj.getJSONArray("departments"); 
				studengs = null;
				for (int i = 0; i < jsonarray.length(); i++) { 
		        studengs = jsonarray.getString(i); 
		        jsonobjDepartments[i] = new JSONObject(studengs);
		        DepartmentLength++;
		      //  System.out.println(jsonobjDepartments[i].toString()); 
		        }
			}catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//对文件内容两次解析构造
			//学生对象放在jsonobjStudents数组
			//部门对象放在jsonobjDepartments数组
	}
}
