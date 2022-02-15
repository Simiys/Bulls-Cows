package bullscows;
import java.lang.Math;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder hiddenNum = new StringBuilder();
        StringBuilder num = new StringBuilder();
        StringBuilder hiddenNumProto = new StringBuilder();
        int turns = 1;
        int numberLength = 0;
        int symbol = 0;
        

        while(true) {
            //System.out.println("loop a");
            boolean isBreak = false;
            while (true) {
                System.out.println("Please, enter the secret code's length:");
                String str =  scanner.next();
                try{
                    numberLength = Integer.parseInt(str);
                }catch(NumberFormatException e){
                    System.out.println("Error: " + str + " isn't a valid number.");
                    isBreak = true;
                    break;
                }
                if (numberLength > 36 || numberLength <= 0) {
                    System.out.println("Error");
                    isBreak = true;
                    break;
                } else {
                    break;
                }
            }
            if(isBreak){System.exit(0);}


            while (true) {
                System.out.println("Input the number of possible symbols in the code:");
                String str  = scanner.next();
                try{
                    symbol = Integer.parseInt(str);
                }catch(NumberFormatException e){
                    System.out.println("Error: " + str + " isn't a valid number.");
                    isBreak = true;
                    break;
                }
                if (symbol <= 0) {
                    System.out.println("Error");
                    isBreak = true;
                    break;
                } else if(symbol > 36){
                    System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z)");
                    isBreak = true;
                    break;
                }else {
                    break;
                }
            }
            if(isBreak){System.exit(0);}

            if(numberLength > symbol){
                System.out.println("Error: it's not possible to generate a code with a length of "+ numberLength +  " with "+ symbol + " unique symbols.");
                System.exit(0);
            }else {
                break;
            }
        }



        hiddenNumProto.append(generateRndomNumber_v4(symbol,numberLength));
        hiddenNum.append(generateCharachkters(hiddenNumProto,symbol,numberLength));
        if(symbol <= 10){
            System.out.print("The secret is prepared: " + "*".repeat(numberLength) + " ");
            System.out.print(" (0-");
            System.out.print(symbol - 1);
            System.out.println(")");
        }else{
            System.out.print("The secret is prepared: " + "*".repeat(numberLength) + " ");
            System.out.print(" (0-9,");
            System.out.print("a-");
            int plusChar = symbol - 11;
            char p = 'a';
            p+= plusChar;
            System.out.print(p);
            System.out.println(")");
        }



        //System.out.println(hiddenNum);
        System.out.println("Okay, let's start a game!");

        while(true) {
            int bulls = 0,cows = 0;
           // System.out.println("Loop 2");
            System.out.println("Turn" + turns + ":");
            turns += 1;
            num.append(scanner.next());
            for (int i = 0; i < num.length(); i++) {
                for (int j = 0; j < hiddenNum.length(); j++) {
                    if ((num.charAt(i) == hiddenNum.charAt(j)) && i == j) {
                        bulls++;
                    } else if (num.charAt(i) == hiddenNum.charAt(j)) {
                        cows++;
                    }
                }
            }


            if (bulls == 0 && cows != 0) {
                System.out.println("Grade: " + cows + " cow(s)");
            }else if (cows == 0 && bulls != 0) {
                System.out.println("Grade: " + bulls + " bull(s)");
            }else if (bulls == 0 && cows == 0) {
                System.out.println("Grade: None.");
            }else {
                System.out.println("Grade:" + bulls + " bull(s) and " + cows + " cow(s)");
            }
            if(bulls == num.length()){
                System.out.println("Congratulations! You guessed the secret code.");
                break;
            }
            num.setLength(0);

        }
    }

    public static StringBuilder generateCharachkters (StringBuilder s,int l,int n){
        if(l <= 10){
            return s;
        }
        int counter = n/2;
        Random random = new Random(54034);
        ArrayList arrayList = new ArrayList<Character>();
        int numberOfChars = random.nextInt(counter);
        while (counter !=0) {
            char protoChar = 'a';
            int randomChar = random.nextInt(l - 11);
            protoChar += randomChar;
            int charPos = random.nextInt(n);
            if(!arrayList.contains(protoChar)){
                s.delete(charPos, charPos + 1).insert(charPos, protoChar);
                arrayList.add(protoChar);
                counter--;
            }

        }

        return s;
    }

   public static StringBuilder generateRndomNumber_v4(int l,int n){
        ArrayList<Integer> arrayList = new ArrayList<>();
        StringBuilder s =new StringBuilder();
        Random random = new Random();
        int border = 9;
        if(l < 10){
            border = l;
        }
        while(n != 0){
            //System.out.println("Loop 5");
            int randomInt = random.nextInt(border);
            if(arrayList.contains(randomInt)){
                continue;
            }else{
                s.append(randomInt);
                arrayList.add(randomInt);
                n--;
            }
        }
        return s;
   }

}



