
import java.util.ArrayList;
import java.util.Scanner;

public class DotcomBust {
    GameHelper helper= new GameHelper();
    Scanner scn = new Scanner(System.in);
    ArrayList<DotCom> dotComList=new ArrayList<>();
    int numGuesses=0;

    public void  setUpGame(){//create initialize dotCom obj+instructions

        DotCom obj1=new DotCom();
        obj1.setName("Pets.com");
        DotCom obj2=new DotCom();
        obj2.setName("eToys.com");
        DotCom obj3=new DotCom();
        obj3.setName("Go2.com");
        dotComList.add(obj1);
        dotComList.add(obj2);
        dotComList.add(obj3);
        System.out.println("Your goal is to sink three dot coms.");
        System.out.println("Pets.com, eToys.com, Go2.com");
        System.out.println("Try to sink them all in the fewest number of guesses");

        for (DotCom dotComToSet:dotComList){
            ArrayList<String> newLocation=helper.placeDotCom(3);
            dotComToSet.setLocationCells(newLocation);
        }


    }
    public void startPlaying(){ //asks for guesses+calls checkGuess()
        while (!dotComList.isEmpty()){
            System.out.println("Enter a guess: ");
            String userGuess= scn.nextLine();
            checkUserGuess(userGuess);
        }
        finishGame();
    }
    public void checkUserGuess(String userGuess){ //loops through and calls checkYourself()
        numGuesses++;
        String result="miss";
        for(DotCom dotComToTest : dotComList){
            result=dotComToTest.checkYourself(userGuess);
            if (result.equals("hit")){
                break;
            }
            if (result.equals("kill")){
                break;
            }
        }
        System.out.println(result);
    }
    public void finishGame(){
        System.out.println("All Dot Coms are dead! Your stock is now worthless.");
        if(numGuesses<18){
            System.out.println("It only rook you "+numGuesses+" guesses");
            System.out.println("You saved what was left ggs");
        }else{
            System.out.println("Took you long enough... "+numGuesses+" guesses");
            System.out.println("My grandma can guess better than you...");
        }
    }

}
