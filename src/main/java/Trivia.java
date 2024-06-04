

//Akshay
import java.util.ArrayList;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Trivia {
    String Trivia_Question; 
    ArrayList<String> Trivia_Answers = new ArrayList<String>();
    ArrayList<String> Questions = new ArrayList<String>();
    int correctIndex;
    public Trivia() throws FileNotFoundException, java.text.ParseException{
        //filler*
        Scanner s = new Scanner(new File("C:\\git-6\\Hunt-the-wumpus\\src\\main\\java\\trivia\\Wumpus_Trivia - Sheet1.tsv"));
        //s.nextLine();
        while(s.hasNextLine()){   
            Questions.add(s.nextLine());
        }
        s.close();
        getNewTrivia();
    }

    public void getNewTrivia(){
        String[] arr = Questions.get((int)(Math.random()*Questions.size())).split("\t");
        Trivia_Question = arr[0];
        correctIndex = Integer.parseInt(arr[1]);
        Trivia_Answers.clear();
        for(int i=2;i<arr.length;i++) Trivia_Answers.add(arr[i]);
    }

    public boolean checkAnswer(int guess) {
        return guess == correctIndex;
    }



}
