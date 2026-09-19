public class LibraryMembership {
 static class LibraryMember { String id,course; int limit,books; LibraryMember(String id,int limit){if(id==null||id.trim().length()<4)throw new IllegalArgumentException();this.id=id;this.limit=limit;} void borrowBook(){if(books<limit)books++;} int getBooksBorrowed(){return books;} void displayInfo(){System.out.println("General Member | Books Borrowed: "+books);} }
 static class StudentMember extends LibraryMember { StudentMember(String id,int l,String c){super(id,l);course=c;} @Override void displayInfo(){System.out.println("Student Member | Course: "+course+" | Books Borrowed: "+books);} }
 static class HonorsStudentMember extends StudentMember { int bonus; HonorsStudentMember(String id,int l,String c,int b){super(id,l,c);bonus=b;} @Override void displayInfo(){System.out.println("Honors Student Member | Course: "+course+" | Bonus Limit: "+bonus+" | Books Borrowed: "+books);} }
 static class FacultyMember extends LibraryMember { String dept; FacultyMember(String id,int l,String d){super(id,l);dept=d;} @Override void displayInfo(){System.out.println("Faculty Member | Department: "+dept+" | Books Borrowed: "+books);} }
 static String enrollBatch(String[] ids,int limit){int e=0,r=0;for(String id:ids)try{new LibraryMember(id,limit);e++;}catch(IllegalArgumentException x){r++;}return "Enrolled: "+e+" | Rejected: "+r;}
 static String classifyGeneration(LibraryMember m){if(m instanceof HonorsStudentMember)return "Multilevel descendant (3 generations deep)";if(m instanceof FacultyMember)return "Hierarchical sibling (independent branch)";if(m instanceof StudentMember)return "Student Member";return "General Member";}
 static int getTotalBooksBorrowed(LibraryMember[] a){int t=0;for(LibraryMember m:a)t+=m.getBooksBorrowed();return t;}
 public static void main(String[]x){System.out.println(enrollBatch(new String[]{"STU1","LB1","STU2"," ","STU3"},3));}
}
