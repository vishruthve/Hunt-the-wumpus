

//Akshay
import java.util.ArrayList;
 

public class Trivia {
    ArrayList<String> Trivia_Questions = new ArrayList<String>(); 
    ArrayList<String> Trivia_Answers = new ArrayList<String>();
    int index = (int) Math.random() * Trivia_Questions.size();
    //Object Arrow = new Object(); 
    int numArrows; 
    public Trivia() {
        //filler
        Trivia_Questions.add("In what country did the first Starbucks open outside of North America?");
        Trivia_Questions.add("What does CODA stand for?");
        Trivia_Questions.add("In a website browser address bar, what does “www” stand for?");
        Trivia_Answers.add("Japan");
        Trivia_Answers.add("Child of Deaf Adults");
        Trivia_Answers.add("World Wide Web");
        //add more later 
    }
    public String getTrivia() {

        return Trivia_Questions.get(index); 

    }
    public int checkAnswer(String guess) {
        if (Trivia_Answers.get(index).equals(guess)) numArrows++; 
        return numArrows;
    }

    public void useArrows() {
        numArrows --; 
    }





}
