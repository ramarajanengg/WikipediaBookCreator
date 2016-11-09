package utility;

public class sample {
public static void main(String[] args) {
	String str = "ID$this is ID";
	String str2[] = str.split("\\$");
	 
	for (String a : str2)
	{
		System.out.println(a);
	}
}
}
