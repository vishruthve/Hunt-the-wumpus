//Akshay

import java.io.*;
import java.util.*;

public class Trivia {
    public static String Trivia_Question = ""; 
    public static String Trivia_Answer = "";
    public static ArrayList<String> Trivia_Answers = new ArrayList<String>();
    public static ArrayList<String> Questions = new ArrayList<String>();
    public static int correctIndex = 0;
    public static String goodAnswer = "";
    static File tr;
    static Scanner s;

    static{
        try{
            tr = new File("src/main/java/tfold/trivia.txt");
            s = new Scanner(tr);
            while(s.hasNextLine()) {   
                Questions.add(s.nextLine());
            }
            s.close();
            getNewTrivia();
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public Trivia(){}

    public static String getNewTrivia(){
        String[] arr = Questions.get((int)(Math.random()*Questions.size())).split("\t");
        Trivia_Question = arr[0];
        correctIndex = Integer.parseInt(arr[1]);
        Trivia_Answers.clear();
        for(int i=2;i<arr.length;i++) Trivia_Answers.add(arr[i]);
        arr = Questions.get((int)(Math.random()*Questions.size())).split("\t");
        Trivia_Answer = "The answer to the question \"" + arr[0] + "\" is: \""+ arr[Integer.parseInt(arr[1])+1]+ "\".";
        return Trivia_Question;
    }

    public static void checkAnswer(int ind, Player p){
        System.out.println(ind==correctIndex);
        p.decGold();
        goodAnswer = "Incorrect!";
        if(ind==correctIndex) {p.addArrow(); goodAnswer = "Correct!";}

    }


}
