//Akshay

import java.io.*;
import java.util.*;

public class Trivia {
    public static String Trivia_Question = ""; 
    public static ArrayList<String> Trivia_Answers = new ArrayList<String>();
    public static ArrayList<String> Questions = new ArrayList<String>();
    public static int correctIndex = 0;

    static{
        File tr;
        try{
            tr = new File("src/main/java/tfold/trivia.txt");
        } catch (Exception e){
            System.out.println(e);
        }
        Scanner s = new Scanner(tr);
        System.out.println(s.nextLine());
        System.out.println("SHITTY ASS");
        while(s.hasNextLine()){   
            Questions.add(s.nextLine());
        }
        s.close();
        getNewTrivia();
    }

    public Trivia(){}

    public static String getNewTrivia(){
        String[] arr = Questions.get((int)(Math.random()*Questions.size())).split("\t");
        Trivia_Question = arr[0];
        correctIndex = Integer.parseInt(arr[1]);
        Trivia_Answers.clear();
        for(int i=2;i<arr.length;i++) Trivia_Answers.add(arr[i]);
        return Trivia_Question;
    }


}
