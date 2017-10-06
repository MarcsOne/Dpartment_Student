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
			
		}  //����ѧ���Ƿ�ѡ���ţ���judgeArray��������������Ӧ��true
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
		}//������Ȥʹ�� ѧ��-���� ����Ⱦ����Ӧλ������1
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
		//���Ŷ����ʼ��
	
		for (int i = 0; i < ReadJson.StudentLength ; i++){
			int max = 0;
			int flag = -1;
			boolean flag0 = false;
			for (int j = 0; j < ReadJson.DepartmentLength; j++){
				//System.out.print(" " + SatisficalArray[i][j]);
				if(judgeArray[i][j]){
					if(max < SatisficalArray[i][j]){
					max = SatisficalArray[i][j];
					flag = j;    //flag��ʾ��ʱ��������Ϊflag�Ĳ��ţ�����max����ƥ���
					flag0 = true;
					}
				}//Ѱ�����ƥ��ȵĲ���
			}
			
			
			
			if(!flag0){
				continue;
			}
			else{
				SatisficalArray[i][flag] = 0;
				if(departments[flag].menber < departments[flag].getLimit_number()){
					students[i].setIsunlucky(true);
					students[i].setAdmittedBy(departments[flag].getDepartment_no());
					departments[flag].Admittedstu[0][departments[flag].menber] = i;   //ѧ�����i
					departments[flag].Admittedstu[1][departments[flag].menber] = max;//ƥ���max //���벿�ų�Ա����
					departments[flag].menber++;
				} //����Ԥѡ����ֱ������ȥ
				else {
					int temp;
					int min = 65535;
					int flagmin = -1;
					int flagtag = -1;
					for (int a = 0; a < 15 && departments[flag].Admittedstu[0][a] != 0; a++) {
						if( min > departments[flag].Admittedstu[1][a]){
							min = departments[flag].Admittedstu[1][a];
							flagmin = departments[flag].Admittedstu[0][a];//flagmin��Сƥ���ѧ�����
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
						
					}//ƥ��ȱ���С�Ĵ����滻
				}//����Ԥѡ�����ȱȽ�
			}
		}//��һ��Ԥѡ,ѧ���������Ƚ�������������ƥ������Ĳ��ţ����ŷ�������ѡ��ƥ�������ѧ��
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
				} //����Ԥѡ����ֱ������ȥ
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
						
					}//ƥ��ȱ���С�Ĵ����滻
				}//����Ԥѡ�����ȱȽ�
			}
		}
		}//�ڶ���Ԥѡ,ѧ���������Ƚ�������������ƥ������Ĳ��ţ����ŷ�������ѡ��ƥ�������ѧ��
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
					} //����Ԥѡ����ֱ������ȥ
				}
			}
			}
		}//������ѡ��,ֻҪ��ѡ��ò��Ų��һ��пձ�����ȥ��������ƥ�����ȣ���������������ѧ�����ܹ�ѡ������
		
	}
}
