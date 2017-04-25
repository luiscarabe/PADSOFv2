/**
* @author Luis Carabe 
* @author Alejo Polania 
*/

package es.uam.eps.padsof.p3.educagram;

import es.uam.eps.padsof.emailconnection.EmailSystem;
import es.uam.eps.padsof.p3.course.Course;
import es.uam.eps.padsof.p3.user.Professor;
import es.uam.eps.padsof.p3.user.User;
import es.uam.eps.padsof.p3.user.Student;
import java.util.*;
import java.io.*;


public class Educagram implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Course> courses;
	private List<Student> students;
	private Professor professor;
	private User currentUser;
	private int firstLogin = 0;
	
	private static final Educagram Instance = new Educagram();
	
	/**
	 * Private constructor of Educagram, it creates an instance of the
	 * several ArrayLists
	 */
	
	private Educagram(){
		courses = new ArrayList<Course>();
		students = new ArrayList<Student>();
		professor = new Professor("Teacher", "teacher@teadu.com", "lovingPADSOF");
		currentUser = null;
	}
    
	/**
	 * @return
	 */
	public static Educagram getInstance(){
		return Educagram.Instance;
	}
	/**
	 * @return the courses
	 */
	public List<Course> getCourses() {
		return courses;
	}
	/**
	 * @param courses the courses to set
	 */
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	/**
	 * @return the students
	 */
	public List<Student> getStudents() {
		return students;
	}
	/**
	 * @param students the students to set
	 */
	public void setStudents(List<Student> students) {
		this.students = students;
	}
	/**
	 * @return the professor
	 */
	public Professor getProfessor() {
		return professor;
	}
	/**
	 * @param professor the professor to set
	 */
	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	
	/**
	 * @return the currentUser
	 */
	public User getCurrentUser() {
		return currentUser;
	}

	/**
	 * @param currentUser the currentUser to set
	 */
	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	
	/**
	 * @return the firstLogin
	 */
	public int getFirstLogin() {
		return firstLogin;
	}

	/* Methods */
	/**
	 * Method that search a course by name
	 * @param title
	 * @return Course c if the course exists or null if not
	 */
	
	public Course searchCourse(String title){
		for (Course c: courses){
			if(c.getTitle().equals(title)){
				return c;
			}
		}
		return null;
	}
	
	/**
	 * Method that search a student by name
	 * @param name
	 * @return Student s if exists null if not
	 */
	public Student searchStudent(String name){
		for (Student s: students){
			if (s.getName().equals(name)){
				return s;
			}
		}
		return null;
	}
	
	/**
	 * Method that checks if a user is valid
	 * @param email
	 * @param psw
	 * @return The user if the email and psw match and null if not
	 * @throws  
	 */
	
	public User signIn(String email, String psw) throws Exception{
		Educagram.readEducagram();
		if (this.currentUser != null){
			return null;
		}
		if (professor.getEmail().equals(email)){
			if (psw.equals(professor.getPassword())){
				this.currentUser = professor;
				return professor;
			}
		}
		
		for (Student s: students){
			if (s.getEmail().equals(email)){
				if (psw.equals(s.getPassword())){
					this.currentUser = s;
					return s;
				}
			}	
		}
		return null;
	}
	
	/**
	 * Method to sign out from educagram
	 * @return true if it was a current user false if not
	 * @throws IOException 
	 */
	public boolean signOut() throws IOException{
		if(this.currentUser == null){
			return false;
		}
		this.currentUser = null;
		Educagram.writeEducagram();
		return true;
	}
	
	/**
	 * Method that reads the information of the students at the app from a file and saves it
	 * @return true if successful, false if not 
	 * @throws java.io.IOException
	 */
	public boolean readFile() throws java.io.IOException{
		String cadena;
		String[] str = new String[5];
		StringTokenizer tokens;
		int i;
		
		if(this.firstLogin == 1){
			return true;
		}
		FileReader f = new FileReader("Emails.txt");
	    BufferedReader b = new BufferedReader(f);
	    b.readLine();
	    

	    while((cadena = b.readLine())!=null) {
	        tokens = new StringTokenizer(cadena, ";");
	        i = 0;
	        while(tokens.hasMoreTokens() && i < 5){
	        	str[i] = tokens.nextToken();
	        	i++;
	        }
	        if(EmailSystem.isValidEmailAddr(str[2])){
	        	Student s = new Student(str[0] + " " + str[1], str[2], str[4]);
		        this.students.add(s);
	        }
	    }
	    b.close();
	    if(this.students.isEmpty()){
	    	return false;
	    }
	    this.firstLogin = 1;
	    return true;
	}
	/**
	 * Method to write all the data in Educagram.objectData
	 * @throws IOException
	 */
	public static void writeEducagram() throws IOException {
		ObjectOutputStream outputObject = new ObjectOutputStream(new FileOutputStream("Educagram.objectData") );
		outputObject.writeObject(Educagram.getInstance());
		outputObject.close();
	}
	/**
	 * Method to read all the data in Educagram.objectData
	 * @throws Exception
	 */
	
	public static Educagram readEducagram() throws Exception {
		Educagram e;
		ObjectInputStream inputObject = new ObjectInputStream( new FileInputStream("Educagram.objectData") );
		e = (Educagram)inputObject.readObject();
		inputObject.close();
		System.out.println("Educagram readed.");
		return e;
	}	
}
