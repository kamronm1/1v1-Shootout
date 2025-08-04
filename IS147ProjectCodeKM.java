import java.util.Scanner; //Import Scanner

public class IS147ProjectCodeKM {
   public static void main(String[] args) {

        Scanner input = new Scanner(System.in); //Create new scanner
        
        //User Input Prompting
        
        // Player 1 setup - Name and team name
        System.out.print("Hello Player 1, please enter your name: ");
        String name1 = input.nextLine();
        System.out.print("Thank you " + name1 + ", now please enter your preferred team name: ");
        String teamName1 = input.nextLine();
        Player player1 = new Player(name1, teamName1);

        System.out.println(""); // spacing

        // Player 2 setup - Name and team name
        System.out.print("Hello Player 2, please enter your name: ");
        String name2 = input.nextLine();
        System.out.print("Thank you " + name2 + ", now please enter your preferred team name: ");
        String teamName2 = input.nextLine();
        Player player2 = new Player(name2, teamName2);

        // Display teams
        System.out.println("\nTeams are ready!");
        System.out.println("Team 1: " + player1.teamName + " - " + player1.name);
        System.out.println("Team 2: " + player2.teamName + " - " + player2.name);

        // Start prompt
        System.out.println("");
        System.out.print("Type 'start' to begin the game: ");
        String startCommand = input.nextLine();
        while (!startCommand.equalsIgnoreCase("start")) { // String method
            System.out.print("Please type 'start' to begin: "); //Make sure user types in 'start'
            startCommand = input.nextLine();
        }
        System.out.println("");
        System.out.println("Game is starting... First to 10 points wins!\n");

        // Arrays to track shot types
        String[] player1Shots = new String[20]; //Players 1 & 2 get 20 turns, just in case they miss a lot of their shots
        String[] player2Shots = new String[20];
        int turnCount1 = 0;
        int turnCount2 = 0;

        // Game loop
        while (true){
        
            turnCount1 = takeTurn(player1, input, player1Shots, turnCount1);
            
            if (player1.score >= 10){
                
                System.out.println("");
                System.out.println(player1.name + " from " + player1.teamName + " wins!");
                
                break;
            }

            turnCount2 = takeTurn(player2, input, player2Shots, turnCount2);
            if (player2.score >= 10) {
                System.out.println("");
                System.out.println(player2.name + " from " + player2.teamName + " wins!");
                break;
            }

            System.out.println("=============================="); // round divider
        }

        // Print shot history
        System.out.println("");
        System.out.println(player1.name + "'s Shot History:");
        for (int i = 0; i < turnCount1; i++) {
            System.out.println("Turn " + (i + 1) + ": " + player1Shots[i]);
        }
        System.out.println("");
        System.out.println(player2.name + "'s Shot History:");
        for (int i = 0; i < turnCount2; i++) {
            System.out.println("Turn " + (i + 1) + ": " + player2Shots[i]);
        }
    }

    // Method for player taking a turn
    public static int takeTurn(Player player, Scanner input, String[] shotHistory, int turnCount) {
        System.out.println("");
        System.out.println("------------------------------");
        System.out.println(player.name + ", it's your turn!");
        System.out.println("Choose a shot:");
        System.out.println("  1. Layup (80% chance, 1 point)");
        System.out.println("  2. Mid-range (50% chance, 2 points)");
        System.out.println("  3. 3-Point (30% chance, 3 points)");
        System.out.print("Enter 1, 2, or 3: ");

        int choice = input.nextInt();
        input.nextLine(); // clear newline

        boolean madeShot = false;
        int points = 0;
        String shotType = "";
        double rand = Math.random();
        
         // Choosing the type of shot
        if (choice == 1) {
            madeShot = rand < 0.8;
            
            points = 1;
            
            shotType = "Layup";
            
        } else if (choice == 2){
        
            madeShot = rand < 0.5;
            
            points = 2;
            
            shotType = "Mid-range";
            
        } else if (choice == 3) {
        
            madeShot = rand < 0.3;
            
            points = 3;
            
            shotType = "3 Pointer";
            
        } else {
            System.out.println("Invalid choice. Turn skipped.\n");
            
            return turnCount;
        }

        // Record shot type
        shotHistory[turnCount] = shotType;
        turnCount++;

        if (madeShot) {
            if (points == 1) {
                System.out.println("Nice shot! You made the shot and earned 1 point!");
            } else {
                System.out.println("Nice shot! You made the shot and earned " + points + " points!");
            }
            player.score += points;
        } else {
            System.out.println("Missed the shot.");
        }

        System.out.println(player.name + "'s score: " + player.score);
        return turnCount;
    }
}

// Player class
         class Player {
             String name;
             String teamName;
             int score;

         public Player(String name, String teamName) {
            this.name = name;
            this.teamName = teamName;
            this.score = 0;
    }
}
