package es.uam.eps.padsof.p3.main;
import java.time.LocalDate;

import es.uam.eps.padsof.p3.course.*;
import es.uam.eps.padsof.p3.exercise.*;
import es.uam.eps.padsof.p3.educagram.*;
import es.uam.eps.padsof.p3.stat.*;
import es.uam.eps.padsof.p3.user.*;


public class Main {

	@SuppressWarnings("unused")
	public static void main(String args[]) throws Exception{
		Application a = null;
		Answer as, as2;
		Educagram educa;
		Professor p;
		Student s1, s2, s3, saux;
		Course c1, caux;
		Unit u1, u2, u3, u4;
		Note n1, n2, n3;
		Boolean b;
		Exercise e;
		OpenQuestion oq;
		MultiQuestion mq;
		UniqQuestion uq;
		TFQuestion tq;
		LocalDate d1, d2;
		Option o1, o2, o3, o4, o5, o6, o7, o8, o9, o10, o11, o12;
		CMark cm1, cm2;
		CourseStat cs1;
		ExerciseStat es1;
		int i;

		
		educa = Educagram.getInstance();
		educa.readFile();
		
		/* The professor signs in and creates a course*/
		p = (Professor) educa.signIn("teacher@teadu.com", "lovingPADSOF");
		System.out.println("Professor has signed in (" + p.getName() + ")");
		c1 = p.createCourse("PADSOF", "Description");
		if (educa.getCourses().contains(c1)){
			System.out.println("Course " + c1.getTitle() + " " + c1.getDesc() + " created successfully.");
		}
		
		/*The professor creates two units, one subunit and a note*/
		u1 = (Unit) c1.createUnit("Unit 1", "Desc of the unit", false);
		u2 = u1.createSubUnit("PADSOF subunit", "Description 2", false);
		u3 = (Unit) c1.createUnit("Unit 2", "Desc", false);
		System.out.println("The course now has two units ( " + u1.getTitle() + " and " + u3.getTitle() + " ).");
		System.out.println("And unit 1 has the subunit " + u2.getTitle());
		n1 = u1.createNote("Note 1", "Description of the note.", false, "Content of the note");
		System.out.println("Note created: " + n1.getText());
		
		/*The professor modifies the note and unit 1 and signs out*/
		n1.setText("Modified content of the note");
		u1.setDesc("Modified desc of the unit");
		System.out.println("New content of note " + n1.getText());
		System.out.println("New desc of the unit " + n1.getDesc());
		
		educa.signOut();
		
		/*The student 1 signs in, search the course and applies*/
		
		
		s1 = (Student) educa.signIn("Rosa.Moreno@esdu.es", "Mor");
		System.out.println("\nStudent " + s1.getName() + " has signed in.");
		caux = educa.searchCourse("PADSOF");
		System.out.println("The student searchs the course "+caux.getTitle());
		b = s1.applyCourse(caux);
		if (b == true){
			System.out.println("The student applies the course: " + s1.getAppliedCourses().get(0).getCourse()
					.getTitle());
		}
		if(s1.getEnrolledCourses().isEmpty() && s1.getExpelledCourses().isEmpty()){
			System.out.println("The student does not have enrolled or expelled courses.");
		}
		
		educa.signOut();
		
		/*The student 2 signs in, search the course and applies*/
		
		s2 = (Student) educa.signIn("Juan.Valle@esdu.es", "ualle@esd");
		System.out.println("\nStudent " + s2.getName() + " has signed in.");
		caux = educa.searchCourse("PADSOF");
		System.out.println("The student searchs the course "+caux.getTitle());
		b = s2.applyCourse(caux);
		if (b == true){
			System.out.println("The student applies the course: " + s2.getAppliedCourses().get(0).getCourse()
					.getTitle());
		}
		if(s2.getEnrolledCourses().isEmpty() && s2.getExpelledCourses().isEmpty()){
			System.out.println("The student does not have enrolled or expelled courses.");
		}
		
		educa.signOut();
		
		/*The student 3 signs in, search the course and applies*/
		
		s3 = (Student) educa.signIn("Estrella.Palma @aadu.es", "Est.P");
		System.out.println("\nStudent " + s3.getName() + " has signed in.");
		caux = educa.searchCourse("PADSOF");
		System.out.println("The student searchs the course "+caux.getTitle());
		b = s3.applyCourse(caux);
		if (b == true){
			System.out.println("The student applies the course: " + s3.getAppliedCourses().get(0).getCourse()
					.getTitle());
		}
		if(s3.getEnrolledCourses().isEmpty() && s3.getExpelledCourses().isEmpty()){
			System.out.println("The student does not have enrolled or expelled courses.");
		}
		
		educa.signOut();
		
		/*The professor signs in and accept two apps and rejects one*/
		
		p = (Professor) educa.signIn("teacher@teadu.com", "lovingPADSOF");
		System.out.println("\nProfessor has signed in (" + p.getName() + ")");
		saux = educa.searchStudent("Estrella Palma");
		if (saux.equals(s3)){
			System.out.println("The professor search a Student correctly.");
		}
		/*Professor searches the applications of the student*/
		a = c1.searchApplication(s3);
		System.out.println("The professor has searched the application of "+a.getAppliedStudent().getName());
		p.confirmApplication(a, true);
		
		saux = educa.searchStudent("Rosa Moreno");
		if (saux.equals(s1)){
			System.out.println("The professor search a Student correctly.");
		}
		/*Professor searches the applications of the student*/
		a = c1.searchApplication(s1);
		System.out.println("The professor has searched the application of "+a.getAppliedStudent().getName());
		p.confirmApplication(a, false);
		
		saux = educa.searchStudent("Juan Valle");
		if (saux.equals(s2)){
			System.out.println("The professor search a Student correctly.");
		}
		/*Professor searches the applications of the student*/
		a = c1.searchApplication(s2);
		System.out.println("The professor has searched the application of "+a.getAppliedStudent().getName());
		p.confirmApplication(a, true);
		
		/*Professor expels a student*/
		p.expellStudent(s2, c1);
		if(c1.getExpelledStudents().contains(s2)){
			System.out.println("Student "+s2.getName()+" has been correctly expelled");
		}

		educa.signOut();
		
		/* Student1 sign in and see that she's been accepted*/
		
		s1 = (Student) educa.signIn("Rosa.Moreno@esdu.es", "Mor");
		System.out.println("\nStudent " + s1.getName() + " has signed in.");
		if(!s1.getEnrolledCourses().contains(c1)){
			System.out.println("Student has not "+c1.getTitle()+" in his enrolled courses");
		}
		b = s1.applyCourse(c1);
		if (b == true){
			System.out.println("The student applies the course: " + s1.getAppliedCourses().get(0).getCourse()
					.getTitle());
		}
		educa.signOut();

		
		/* Student2 sign in and see that she's been accepted*/
		
		s2 = (Student) educa.signIn("Juan.Valle@esdu.es", "ualle@esd");
		System.out.println("\nStudent " + s2.getName() + " has signed in.");
		if(s2.getExpelledCourses().contains(c1)){
			System.out.println("Student has "+c1.getTitle()+" in his expelled courses");
		}
		educa.signOut();
		
		/* Student3 sign in and see that she's been accepted*/
		
		s3 = (Student) educa.signIn("Estrella.Palma @aadu.es", "Est.P");
		System.out.println("\nStudent " + s3.getName() + " has signed in.");
		if(s3.getEnrolledCourses().contains(c1)){
			System.out.println("Student has "+c1.getTitle()+" in her enrolled courses");
			if (n1.isHidden()==false){
				System.out.println("I'm seeing the unhidden note: " + n1.getText());
			}
		}
		educa.signOut();
		
		/*The professor sign in and readmits student 2 and accepts student 3*/
		
		p = (Professor) educa.signIn("teacher@teadu.com", "lovingPADSOF");
		System.out.println("\nProfessor has signed in (" + p.getName() + ")");
		p.readmitStudent(s2, c1);
		if(c1.getEnrolledStudents().contains(s2) && !c1.getExpelledStudents().contains(s2)){
			System.out.println("Student "+s2.getName()+" has been readmited");	
		}
		p.confirmApplication(s1.getAppliedCourses().get(0), true);
		if(c1.getEnrolledStudents().contains(s1) && !c1.getExpelledStudents().contains(s1)){
			System.out.println("Student "+s1.getName()+" has been accepted");	
		}
		n1.setHidden(true);
		System.out.println("The professor has modify the note, now, hidden is:"+n1.isHidden());
		
		System.out.println("\nThe professor is going to make an exercise\n");
		e = u1.createExercise("Exercise1", "Description1", true);
		e.setPenalty(0.5);
		e.setWeight(10);
		e.setRandomness(false);
		
		/*Open Question*/
		oq = new OpenQuestion("How many bits are contained in on byte", 2.5, e);
		System.out.println("\nThe professor creates an Open Question: "+oq.getTitle());
		/*The professor modifies the question*/
		oq.setTitle("How many bits are contained in on byte");
		System.out.println("The professor modifies the title of the OpenQuestion");
		o1 = new Option("8");
		o2 = new Option("eight");
		oq.addSolution(o1);
		oq.addSolution(o2);
		System.out.println("OpenQuestion created with "+ oq.getSolution().size()+" solutions.");
		
		/*TF Question*/
		tq = new TFQuestion("True or false: You are going to pass this test", 2.2, e);
		System.out.println("\nThe professor creates an TF Question: "+tq.getTitle());
		/*The professor modifies the question*/
		tq.setWeight(2.5);
		System.out.println("The professor modifies the weight of the TF Question");
		o3 = new Option("f");
		tq.setSolution(o3);
		System.out.println("OpenQuestion created  with the solution:"+ tq.getSolution().get(0).getOption());
		
		/*Multi Question*/
		mq = new MultiQuestion("Select the natural numbers", 2.5, true, e);
		System.out.println("\nThe professor creates a Multi Question: "+ mq.getTitle());
		o4 = mq.addOption("-2");
		o5 = mq.addOption("2");
		o6 = mq.addOption("3.5");
		/*The professor modifies an option*/
		o6.setOption("3");
		System.out.println("The professor modifies an option of the Multi Question");
		mq.addSolution(o5);
		mq.addSolution(o6);
		System.out.println("Multi Question created with "+mq.getNumAns()+" num Answers and "+ mq.getNumSol()+" num Solutions");
		
		/*Uniq Question*/
		uq = new UniqQuestion("Do you like this test?", 2.5, false, e);
		System.out.println("\nThe professor creates a Uniq Question: "+uq.getTitle());
		o7 = uq.addOption("Yes");
		o8 = uq.addOption("No");
		uq.addSolution(o8);
		/*The professor modifies a solution*/
		uq.deleteSolution(o8);
		uq.addSolution(o7);
		System.out.println("The professor modifies the solution of the Uniq Question");
		System.out.println("Uniq Question created with the solution: " + uq.getSolution().get(0).getOption());
		
		e.addQuestion(oq);
		e.addQuestion(tq);
		e.addQuestion(mq);
		e.addQuestion(uq);
		d1 = LocalDate.now();
		d2 = LocalDate.now();
		d2 = d2.plusDays(15);
		e.addExpirationDate(d2);
		e.addStartDate(d1);
		System.out.println("\nExercise "+e.getTitle()+" with "+e.getNumQues()+" number of questions");
		/*The professor wants to modify the exercise*/
		if(e.isModifiable()){
			e.setTitle("Exercise one");
			e.addExpirationDate(e.getExpDate().plusDays(5));
			e.setHidden(false);
			System.out.println("Professor can modify exercise ("+e.isModifiable()+") so he has modified the title, the exp date and its visibility");
		}
		/*The professor creates a unit with a two notes, deletes one and then hides the unit*/
		
		u4 = (Unit) c1.createUnit("New useless unit", "Useless description", false);
		n2 = u4.createNote("Note 2", "desc", false, "Great note");
		n3 = u4.createNote("Title", "desc", true, "Hello there");
		System.out.println("New two notes: "+ n2.getTitle()+" and " +n3.getTitle());
		u4.deleteNote(n3);
		if(!u4.getCourseElements().contains(n3)){
			System.out.println("Note deleted successfully");
		}
		u4.unitHide();
		if(u4.isHidden()==true && n2.isHidden()==true){
			System.out.println("Unit hidden successfully");
		}
		educa.signOut();
		
		
		/*The student 1 signs in, search the course and applies*/
		
		s1 = (Student) educa.signIn("Rosa.Moreno@esdu.es", "Mor");
		System.out.println("\nStudent " + s1.getName() + " has signed in.");
		if(n1.isHidden()==true){
			System.out.println("The student can't see the hidden note");
		}
		if(e.canTakeExercise(s1)){
			System.out.println("The student can do the exercise: "+ e.getTitle());
		}
		as = e.takeExercise(s1);
		System.out.println("The penalty of this exercise is: "+e.getPenalty());
		
		o9 = new Option("8");
		as.getSpecificAnswer().get(0).chooseAnswer(o9);
		System.out.println("The student has answered the question "+as.getSpecificAnswer().get(0).getQuestion().getTitle());
		
		o10 = new Option("t");
		as.getSpecificAnswer().get(1).chooseAnswer(o10);
		System.out.println("The student has answered the question "+as.getSpecificAnswer().get(1).getQuestion().getTitle());
		
		as.getSpecificAnswer().get(2).chooseAnswer(o5);
		as.getSpecificAnswer().get(2).chooseAnswer(o6);
		System.out.println("The student has answered the question "+as.getSpecificAnswer().get(2).getQuestion().getTitle());
		
		as.getSpecificAnswer().get(3).chooseAnswer(o7);
		System.out.println("The student has answered the question "+as.getSpecificAnswer().get(3).getQuestion().getTitle());
		
		/*Auto-correction*/
		for(SpecificAnswer s: as.getSpecificAnswer()){
			s.calculateMark();
			System.out.println("Auto-correction (not seen by student yet) of Question "+ s.getQuestion().getTitle() + " , the mark is: "+s.getMarkOutWeight());
		}
		as.calculateMark();
		System.out.println("And the average mark of the exercise is: " + as.getMarkOutWeight());
		educa.signOut();
		
		/*Student 2 signs in */
		
		s2 = (Student) educa.signIn("Juan.Valle@esdu.es", "ualle@esd");
		System.out.println("\nStudent " + s2.getName() + " has signed in.");
		
		if(e.canTakeExercise(s1)){
			System.out.println("The student can do the exercise: "+ e.getTitle());
		}
		as2 = e.takeExercise(s2);
		System.out.println("The penalty of this exercise is: "+e.getPenalty());
		;
		as2.getSpecificAnswer().get(0).chooseAnswer(o1);
		System.out.println("The student has answered the question "+as2.getSpecificAnswer().get(0).getQuestion().getTitle());
		
		as2.getSpecificAnswer().get(1).chooseAnswer(o1);
		System.out.println("The student has answered the question "+as2.getSpecificAnswer().get(1).getQuestion().getTitle());
		
		System.out.println("The student has answered the question "+as2.getSpecificAnswer().get(2).getQuestion().getTitle());
		
		as2.getSpecificAnswer().get(3).chooseAnswer(o1);
		System.out.println("The student has answered the question "+as2.getSpecificAnswer().get(3).getQuestion().getTitle());
		
		/*Auto-correction*/
		for(SpecificAnswer s: as2.getSpecificAnswer()){
			s.calculateMark();
			System.out.println("Auto-correction (not seen by student yet) of Question "+ s.getQuestion().getTitle() + " , the mark is: "+s.getMarkOutWeight());
		}
		as2.calculateMark();
		System.out.println("And the average mark of the exercise is: " + as2.getMarkOutWeight());
		educa.signOut();
		
		/*We simulate that time has passed*/
		System.out.println("Can students see the exercise? -> "+e.isAllowedToShow());
		System.out.println("We simulate that time has passed");
		d1 = d1.plusDays(-1);
		e.setExpDate(d1);
		
		/*The system calculates the Course Mark*/
		System.out.println("\nThe system calculates all stats");
		
		es1 = new ExerciseStat(e);
		es1.setAll();
		System.out.println("Exercise stats setted");
		
		cm1 = new CMark(c1, s1);
		s1.getcMarks().add(cm1);
		cm1.calculateCMark();
		cm2 = new CMark(c1, s2);
		s2.getcMarks().add(cm2);
		cm2.calculateCMark();
		System.out.println("Course marks setted");
		
		cs1 = new CourseStat();
		cs1.getcMarks().add(cm1);
		cs1.getcMarks().add(cm2);
		cs1.calculateCstat();
		System.out.println("Course average mark setted");
		
		
		p = (Professor) educa.signIn("teacher@teadu.com", "lovingPADSOF");
		System.out.println("\nProfessor has signed in (" + p.getName() + ")");
		System.out.println("The average mark of the course "+c1.getTitle()+ " is "+cs1.getAverageMark());
		System.out.println("The average mark of "+s1.getName()+" in that course is "+cm1.getCourseMark());
		System.out.println("The average mark of "+s2.getName()+" in that course is "+cm2.getCourseMark());
		System.out.println("Stats of Exercise: "+ e.getTitle());
		for(i = 1; i <= e.getNumQues(); i++){
			System.out.println("The number of students who have answered question "+i+" is "+es1.getqAnswered()[i-1]);
			System.out.println("The number of students who have not answered question "+i+" is "+es1.getqNotAnswered()[i-1]);
			System.out.println("The number of students who have answered (right) question "+i+" is "+es1.getRightAns()[i-1]);
			System.out.println("The number of students who have answered (wrong) question "+i+" is "+es1.getWrongAns()[i-1]);
		}
		educa.signOut();
		/*We simulate that the app is closed, saving all the data */
		
	}

}
