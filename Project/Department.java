package pair_programming;

public class Department {
	private int limit_number;
	private String department_no = null;
	public int[][] Admittedstu = new int[2][15];
	public int menber = 0;
	public int getLimit_number() {
		return limit_number;
	}
	public void setLimit_number(int limit_number) {
		this.limit_number = limit_number;
	}
	public String getDepartment_no() {
		return department_no;
	}
	public void setDepartment_no(String department_no) {
		this.department_no = department_no;
	}
}
