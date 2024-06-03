

//Akshay
import java.util.ArrayList;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Trivia {
    String Trivia_Question = new String(); 
    ArrayList<String> Trivia_Answers = new ArrayList<String>();
    ArrayList<String> Questions = new ArrayList<String>();
    int correctIndex;
    int randomIndex = (int)( Math.random() * 14 );
    //Object Arrow = new Object(); 
    int numArrows; 
    public Trivia() throws FileNotFoundException, java.text.ParseException{
        //filler*
        Scanner s = new Scanner(new File("src/main/java/trivia/Wumpus_Trivia - Sheet1.tsv"));
        s.nextLine();
        while(s.hasNextLine()){
            
            Questions.add(s.nextLine());
            

        //add more later 
        }
        s.close();
        String[] arr = Questions.get(0).split("\t");
        Trivia_Question = arr[0];
        correctIndex = Integer.parseInt(arr[1]);
        Trivia_Answers.add(arr[2]);
        Trivia_Answers.add(arr[3]);
        Trivia_Answers.add(arr[4]);
        Trivia_Answers.add(arr[5]);
    }

    public boolean checkAnswer(int guess) {
        return guess == correctIndex;
    }

    public void useArrows() {
        numArrows --; 
    }


}
