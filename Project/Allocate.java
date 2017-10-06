package pair_programming;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.json.JSONArray;
import org.json.JSONException;

public class Allocate {
	static int[][] SatisficalArray = new int[300][20];
	static boolean[][] judgeArray = new boolean[300][20];
	static Student[] students = new Student[300]; 
	static Department[] departments = new Department[20];
	
	public void allocate() throws JSONException, ParseException{
		for (int i = 0; i < ReadJson.StudentLength; i++) {
			JSONArray applications = ReadJson.jsonobjStudents[i].getJSONArray("applications_department");
			for (int k = 0; k < applications.length(); k++) {
				//System.out.print(" " + applications.getString(k));
				for (int j = 0; j < ReadJson.DepartmentLength; j++) {
					if(applications.getString(k).equals(ReadJson.jsonobjDepartments[j].getString("department_no"))){
						judgeArray[i][j] = true;
						SatisficalArray[i][j] = SatisficalArray[i][j] + 5;
				}
				}
			}
			
		}  //根据学生是否选择部门，在judgeArray关联矩阵中设相应的true
		for (int i = 0; i < ReadJson.StudentLength; i++){
			JSONArray StutagsArray = ReadJson.jsonobjStudents[i].getJSONArray("tags");
			for (int j = 0; j < ReadJson.DepartmentLength && judgeArray[i][j] == true; j++){
				//System.out.print(" " + judgeArray[i][j]);
				JSONArray DeptagsArray = ReadJson.jsonobjDepartments[j].getJSONArray("tags");
				for (int k = 0; k < StutagsArray.length(); k++) {
					String str1 = StutagsArray.getString(k);
					for (int l = 0; l < DeptagsArray.length(); l++) {
						String str2 = DeptagsArray.getString(l);
						if(str1.equals(str2)){
							SatisficalArray[i][j]++;
						}
					}
					
				}
			}
		}//根据兴趣使得 学生-部门 满意度矩阵对应位置自增1
		//Student[] students; 
		//students= new Student[300];//////////////////////////////////
		
		Calendar calendar1_start = Calendar.getInstance();
		Calendar calendar2_start = Calendar.getInstance();
		Calendar calendar1_end = Calendar.getInstance();
		Calendar calendar2_end = Calendar.getInstance();
		
		for (int i = 0; i < ReadJson.StudentLength; i++){
			JSONArray Stufree_time_Array = ReadJson.jsonobjStudents[i].getJSONArray("free_time");
			students[i]=new Student();//////////////////////////
			
			String xString = ReadJson.jsonobjStudents[i].getString("student_no");
			students[i].setStu_number(xString);
			
		//	System.out.println(students[i].getStu_number());
			
			for (int j = 0; j < Stufree_time_Array.length(); j++) {
				String[] Stutime = Stufree_time_Array.getString(j).split("\\.|~");
				//students[i].setStu_number(jsonobjStudents[i].getString("student_no"));
				
				for (int k = 0; k < ReadJson.DepartmentLength && judgeArray[i][k]; k++) {
					JSONArray Depfree_time_Array = ReadJson.jsonobjDepartments[k].getJSONArray("event_schedules");
					for (int l = 0; l < Depfree_time_Array.length(); l++) {
						String[] Deptime = Depfree_time_Array.getString(l).split("\\.|~");
						//System.out.print(Stutime[1] +"-"+(Deptime[1]));
						//System.out.print(" " + Stutime[1].compareTo(Deptime[1]));
						//System.out.print(" " + Stutime[2].compareTo(Deptime[2]));
						if(Stutime[0].equals(Deptime[0])){
							
							if(Stutime[1].compareTo(Deptime[1]) <= 0 && Stutime[2].compareTo(Deptime[2]) >= 0){
									SatisficalArray[i][k] = SatisficalArray[i][k] + 2 ;
							}
							else{
								calendar1_start.setTime(new SimpleDateFormat("HH:mm").parse(Stutime[1]));
								calendar2_start.setTime(new SimpleDateFormat("HH:mm").parse(Deptime[1]));
								calendar1_end.setTime(new SimpleDateFormat("HH:mm").parse(Stutime[2]));
								calendar2_end.setTime(new SimpleDateFormat("HH:mm").parse(Deptime[2]));
								if((Stutime[1].compareTo(Deptime[2]) <= 0 && Stutime[2].compareTo(Deptime[1]) >= 0) || Deptime[1].compareTo(Stutime[2]) <= 0 && Deptime[2].compareTo(Stutime[1]) >= 0)
								if(calendar1_end.getTimeInMillis() - calendar2_start.getTimeInMillis() >= 1800000 || calendar2_end.getTimeInMillis() - calendar1_start.getTimeInMillis() >= 1800000){
									SatisficalArray[i][k] = SatisficalArray[i][k] + 1 ;
								}
							}
						}
						
					}
				}
			}
		}
	/*	for(int i = 0; i < StudentLength ; i++){
			for(int j = 0; j < DepartmentLength; j++){
				System.out.print(" "+ SatisficalArray[i][j]);
			}
			System.out.println();
		}
		*/
		
		
		
		for (int j = 0; j < ReadJson.DepartmentLength; j++){
			departments[j] = new Department();
			departments[j].setLimit_number(ReadJson.jsonobjDepartments[j].getInt("member_limit"));
			departments[j].setDepartment_no(ReadJson.jsonobjDepartments[j].getString("department_no"));
			//System.out.print(departments[j].getLimit_number());
		}
		//部门对象初始化
	
		for (int i = 0; i < ReadJson.StudentLength ; i++){
			int max = 0;
			int flag = -1;
			boolean flag0 = false;
			for (int j = 0; j < ReadJson.DepartmentLength; j++){
				//System.out.print(" " + SatisficalArray[i][j]);
				if(judgeArray[i][j]){
					if(max < SatisficalArray[i][j]){
					max = SatisficalArray[i][j];
					flag = j;    //flag表示暂时分配给编号为flag的部门，并且max代表匹配度
					flag0 = true;
					}
				}//寻找最大匹配度的部门
			}
			
			
			
			if(!flag0){
				continue;
			}
			else{
				SatisficalArray[i][flag] = 0;
				if(departments[flag].menber < departments[flag].getLimit_number()){
					students[i].setIsunlucky(true);
					students[i].setAdmittedBy(departments[flag].getDepartment_no());
					departments[flag].Admittedstu[0][departments[flag].menber] = i;   //学生编号i
					departments[flag].Admittedstu[1][departments[flag].menber] = max;//匹配度max //纳入部门成员矩阵
					departments[flag].menber++;
				} //部门预选不满直接塞进去
				else {
					int temp;
					int min = 65535;
					int flagmin = -1;
					int flagtag = -1;
					for (int a = 0; a < 15 && departments[flag].Admittedstu[0][a] != 0; a++) {
						if( min > departments[flag].Admittedstu[1][a]){
							min = departments[flag].Admittedstu[1][a];
							flagmin = departments[flag].Admittedstu[0][a];//flagmin最小匹配度学生编号
							flagtag = a;
						}
					}
					if(max > min )
					{
						students[flagmin].setIsunlucky(false);
						students[flagmin].setAdmittedBy("");
						departments[flag].Admittedstu[1][flagtag] = max;
						departments[flag].Admittedstu[0][flagtag] = i;
						students[i].setIsunlucky(true);
						students[i].setAdmittedBy(departments[flag].getDepartment_no());
						
					}//匹配度比最小的大则替换
				}//部门预选满了先比较
			}
		}//第一轮预选,学生方面优先进入意向部门里面匹配度最大的部门，部门方面优先选择匹配度最大的学生
		//System.out.println(students[0].isIsunlucky());
		//System.out.println(students[1].isIsunlucky());
		/*for(int i = 0; i < StudentLength ; i++){
			for(int j = 0; j < DepartmentLength; j++){
				System.out.print(" "+ SatisficalArray[i][j]);
			}
			System.out.println();
		}*/
		for (int i = 0; i < ReadJson.StudentLength ; i++){
			if(students[i].isIsunlucky() == false){
			int max = 0;
			int flag = -1;
			boolean flag0 = false;
			for (int j = 0; j < ReadJson.DepartmentLength; j++){
				if(judgeArray[i][j]){
				//System.out.print(" " + SatisficalArray[i][j]);
				if(max < SatisficalArray[i][j]){
					max = SatisficalArray[i][j];
					flag = j;
					flag0 = true;
				}
			}
			}
			
			
			if(!flag0){
				continue;
			}
			else{
				SatisficalArray[i][flag] = 0;
				if(departments[flag].menber < departments[flag].getLimit_number()){
					students[i].setIsunlucky(true);
					students[i].setAdmittedBy(departments[flag].getDepartment_no());
					departments[flag].Admittedstu[0][departments[flag].menber] = i;
					departments[flag].Admittedstu[1][departments[flag].menber] = max;
					departments[flag].menber++;
				} //部门预选不满直接塞进去
				else {
					int temp;
					int min = 65535;
					int flagmin = -1;
					int flagtag = -1;
					for (int a = 0; a < 15 ; a++) {
						if(departments[flag].Admittedstu[0][a] != 0){
						if( min > departments[flag].Admittedstu[1][a]){
							min = departments[flag].Admittedstu[1][a];
							flagmin = departments[flag].Admittedstu[0][a];
							flagtag = a;
						}
						}
					}
					if(max > min )
					{
						students[flagmin].setIsunlucky(false);
						students[flagmin].setAdmittedBy("");
						departments[flag].Admittedstu[1][flagtag] = max;
						departments[flag].Admittedstu[0][flagtag] = i;
						students[i].setIsunlucky(true);
						students[i].setAdmittedBy(departments[flag].getDepartment_no());
						
					}//匹配度比最小的大则替换
				}//部门预选满了先比较
			}
		}
		}//第二轮预选,学生方面优先进入意向部门里面匹配度最大的部门，部门方面优先选择匹配度最大的学生
		//System.out.println(students[0].isIsunlucky());
		//System.out.println(students[1].isIsunlucky());
		for (int k1 = 0; k1 < 3; k1++) {
			for (int i = 0; i < ReadJson.StudentLength  ; i++){
				if(students[i].isIsunlucky() == false){
				int max = 0;
				int flag = -1;
				boolean flag0 = false;
				for (int j = 0; j < ReadJson.DepartmentLength ; j++){
					if(judgeArray[i][j] ){
					//System.out.print(" " + SatisficalArray[i][j]);
					if(max < SatisficalArray[i][j]){
						max = SatisficalArray[i][j];
						flag = j;
						flag0 = true;
					}
					}
				}
				
				
				if(!flag0){
					continue;
				}
				else{
					SatisficalArray[i][flag] = 0;
					if(departments[flag].menber < departments[flag].getLimit_number()){
						students[i].setIsunlucky(true);
						students[i].setAdmittedBy(departments[flag].getDepartment_no());
						departments[flag].Admittedstu[0][departments[flag].menber] = i;
						departments[flag].Admittedstu[1][departments[flag].menber] = max;
						departments[flag].menber++;
					} //部门预选不满直接塞进去
				}
			}
			}
		}//第三轮选择,只要有选择该部门并且还有空便塞进去，不进行匹配度配比，尽可能满足大多数学生都能够选到部门
		
	}
}
