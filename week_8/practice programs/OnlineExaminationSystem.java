import java.util.*;
abstract class Question {
    String text, correct; int points;
    Question(String text,String correct,int points){this.text=text;this.correct=correct;this.points=points;}
    abstract boolean evaluate(String answer);
}
class MCQQuestion extends Question {
    MCQQuestion(String t,String c,int p){super(t,c,p);}
    boolean evaluate(String a){return correct.equalsIgnoreCase(a);}
}
class TrueFalseQuestion extends Question {
    TrueFalseQuestion(String t,String c,int p){super(t,c,p);}
    boolean evaluate(String a){return correct.equalsIgnoreCase(a);}
}
class Attempt {
    private final String student, exam;
    private final Map<Question,String> answers=new LinkedHashMap<>();
    private boolean submitted=false;
    Attempt(String student,String exam){this.student=student;this.exam=exam;System.out.println(exam+" started by "+student+".");}
    void answer(Question q,String answer) {
        if(submitted){System.out.println("Cannot change answers for a submitted examination.");return;}
        answers.put(q,answer); System.out.println("Answer recorded for "+q.text+".");
    }
    void submit() {
        if(submitted){System.out.println("Exam already submitted.");return;}
        submitted=true; int total=0,score=0;
        for(Question q:answers.keySet()){total+=q.points;int got=q.evaluate(answers.get(q))?q.points:0;score+=got;
            System.out.println(q.text+": "+(got>0?"Correct":"Incorrect")+" ("+got+" points)");}
        System.out.println(exam+" submitted by "+student+". Total score: "+score+"/"+total+".");
    }
}
public class OnlineExaminationSystem {
    public static void main(String[] args) {
        Question q1=new MCQQuestion("Question 1","C",5), q2=new TrueFalseQuestion("Question 2","False",5);
        Attempt a=new Attempt("Student 1","Exam A");
        a.answer(q1,"C"); a.answer(q2,"True"); a.submit(); a.answer(q1,"A");
    }
}
