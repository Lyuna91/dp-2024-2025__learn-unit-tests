package fr.anthonyquere.fizzbuzz;

import java.util.ArrayList;
import java.util.List;

public class FizzBuzz {

    public static void main(String[] args) {
        System.out.println("Hello, World! What's up?");
    }

    public static List<String> startFizzBuzz(int count) {
        List<String> fizzbuzz = new ArrayList<>();
        for (int i=0; i<count; ++i){
            var index = i+1;
            if(index % 3 == 0 && index % 5 == 0){
                fizzbuzz.add("FizzBuzz");
            }
            else if(index % 3 == 0){
                fizzbuzz.add("Fizz");
            }
            else if(index % 5 == 0) {
                fizzbuzz.add("Buzz");
            }
            else{
                fizzbuzz.add(Integer.toString(index));
            }
        }
        return fizzbuzz;
    }


}
