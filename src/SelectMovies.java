import java.sql.*;
import java.util.*;
public class SelectMovies {
	public static void main(String args[]) {
		try {
		Scanner sc=new Scanner(System.in);
		Class.forName("org.sqlite.JDBC");
		Connection con=DriverManager.getConnection("jdbc:sqlite:product1.db");
		if(con!=null) {
			
			Statement stmt=con.createStatement();
			
			//creating table
			stmt.executeUpdate("create table movies(name text primary key,actor text,actress text,director text,year int)");
			System.out.println("table created");
			
			//inserting data to movies table 
			PreparedStatement pstmt=con.prepareStatement("insert into movies values(?,?,?)");
			System.out.println("Enter the Movie Name");
			String name=sc.next();
			System.out.println("Enter actor Name");
			String actor=sc.next();
			System.out.println("Enter actress Name");
			String actress=sc.next();
			System.out.println("Enter director Name");
			String director=sc.next();
			System.out.println("Enter year of release");
			int year=sc.nextInt();
			pstmt.setString(1,name);
			pstmt.setString(2,actor);
			pstmt.setString(3,actress);
			pstmt.setString(4,director);
			pstmt.setInt(5,year);
			pstmt.executeUpdate();
			stmt.executeUpdate("insert into movies values('"+name+"','"+actor+"','"+actress+"','"+director+"',"+year+")");
			
			//query to select full table
			PreparedStatement pstmt2=con.prepareStatement("select * from movies");
			ResultSet rs=pstmt2.executeQuery();
			System.out.println("printing table: Movies\n");
			System.out.println("name\tactor\tactress\tdirector\tyear");
			System.out.println("______________________________________________");
			while(rs.next()) {
			System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4)+"\t"+rs.getInt(5));}
			
			
			//query with parameter like actor name to select movies based on the actor's name
			System.out.println("\n\nprinting details related to sahoo:\n");
			PreparedStatement pstmt1=con.prepareStatement("select * from movies where name='sahoo'");
			ResultSet rs1=pstmt1.executeQuery();
			while(rs1.next()) {
				System.out.println(rs1.getString(1)+"\t"+rs1.getString(2)+"\t"+rs1.getString(3)+"\t"+rs1.getString(4)+"\t"+rs1.getInt(5));}


		}
		
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}


