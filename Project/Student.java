package pair_programming;

public class Student {
	
	private String stu_number = null; 
	
	private boolean Isunlucky = false;
	private String admittedBy = null;
	
	
	public String getStu_number() {
		return stu_number;
	}
	public void setStu_number(String stu_number) {
		this.stu_number = stu_number;
	}
	public boolean isIsunlucky() {
		return Isunlucky;
	}
	public void setIsunlucky(boolean isunlucky) {
		Isunlucky = isunlucky;
	}
	public String getAdmittedBy() {
		return admittedBy;
	}
	public void setAdmittedBy(String admittedBy) {
		this.admittedBy = admittedBy;
	}
	
	
}
