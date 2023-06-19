import java.util.Scanner;
import java.util.Random;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean gameOver = false;
        System.out.println("The goal of this game is to get the generated stone to the end.");
        System.out.println("You cannot go through the places, marked with #.");
        System.out.println();
        System.out.println("The controls are as follows:");
        System.out.println("- type s to move down");
        System.out.println("- type w to move up");
        System.out.println("- type a to move left");
        System.out.println("- type d to move right");
        System.out.println();

        int difficulty;
        do {
            System.out.println("Please, insert the level you want to play on:");
            System.out.println("1 for easy difficulty");
            System.out.println("2 for medium difficulty");
            System.out.println("3 for hard difficulty");
            difficulty = sc.nextInt();
        } while (difficulty != 1 && difficulty != 2 && difficulty != 3);

        char[][] table;

        if (difficulty == 1) {
            table = generateWorld(10, 5);
            printWorld(table, 10);
            while (!gameOver) {
                char nextMove;
                nextMove = sc.next().charAt(0);
                nextMove(nextMove, table, 10);
            }
        } else if (difficulty == 2) {
            table = generateWorld(20, 10);
            printWorld(table, 20);
            while (!gameOver) {
                char nextMove;
                nextMove = sc.next().charAt(0);
                nextMove(nextMove, table, 20);
            }
        } else {
            table = generateWorld(30, 20);
            printWorld(table, 30);
            while (!gameOver) {
                char nextMove;
                nextMove = sc.next().charAt(0);
                nextMove(nextMove, table, 30);
            }
        }
    }
    static char[][] generateWorld(int tableSide, int chance) {
        Random rand = new Random();
        char[][] table = new char[tableSide][tableSide];
        String choices = "#";
        for (int i = 0; i < ((100 / chance) - 1); i++) {
            choices += "0";
        }
        for (int i = 0; i < tableSide; i++) {
            for (int j = 0; j < tableSide; j++) {
                if (i == tableSide - 1 && j == tableSide - 1) {
                    table[i][j] = 'E';
                } else {
                    table[i][j] = choices.charAt(rand.nextInt(100 / chance));
                }
            }
        }
        int humanI, humanJ;
        do {
            humanI = rand.nextInt(tableSide);
            humanJ = rand.nextInt(tableSide);
            table[tableSide - 1][tableSide - 1] = 'E';
        } while(humanI == tableSide - 1 && humanJ == tableSide - 1);
        table[humanI][humanJ] = 'H';

        int stoneI, stoneJ;

        do {
            stoneI = rand.nextInt(tableSide);
            stoneJ = rand.nextInt(tableSide);
            table[tableSide - 1][tableSide - 1] = 'E';
        } while(stoneJ == tableSide - 1 && stoneI == tableSide - 1 || stoneJ == 0 || stoneI == 0);
        table[stoneI][stoneJ] = 'S';
        return table;
    }
    static int findHumanJ(char[][] table){
        int humanJ = 0;
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                if(table[i][j] == 'H'){
                    humanJ = j;
                    break;
                }
            }
        }
        return humanJ;
    }
    static int findHumanI(char[][] table){
        int humanI = 0;
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                if(table[i][j] == 'H'){
                    humanI = i;
                    break;
                }
            }
        }
        return humanI;
    }
    static void printWorld(char[][] table, int tableSide){
        for (int i = 0; i < tableSide; i++) {
            for (int j = 0; j < tableSide; j++) {
                System.out.print(table[i][j] + " ");
            }
            System.out.println();
        }
    }
    static void moveDown(char[][] table, int tableSide) {
        int humanI = findHumanI(table);
        int humanJ = findHumanJ(table);

        if (humanI != tableSide - 1 && table[humanI + 1][humanJ] != '#' && table[humanI + 1][humanJ] != 'E') {
            if (table[humanI + 1][humanJ] == 'S') {
                if (table[humanI + 2][humanJ] == '#' || humanI == tableSide - 2) {
                    System.out.println("Invalid move");
                } else {
                    if (table[humanI + 2][humanJ] == 'E') {
                        System.out.println("Congratulations! You won!");
                    } else {
                        table[humanI + 2][humanJ] = 'S';
                        table[humanI + 1][humanJ] = 'H';
                        table[humanI][humanJ] = '0';
                        printWorld(table, tableSide);
                    }
                }
            } else {
                table[humanI + 1][humanJ] = 'H';
                table[humanI][humanJ] = '0';
                printWorld(table, tableSide);
            }
        } else {
            System.out.println("Invalid move");
        }
    }


    static void moveUp(char[][] table, int tableSide){
        int humanI = findHumanI(table);
        int humanJ = findHumanJ(table);

        if(humanI != 0 && table[humanI - 1][humanJ] != '#' && table[humanI - 1][humanJ] != 'E') {
            if (table[humanI - 1][humanJ] == 'S') {
                if(table[humanI - 2][humanJ] == '#' || humanI == 1){
                    System.out.println("Invalid move");
                }
                else {
                    if (table[humanI - 2][humanJ] == 'E') {
                        System.out.println("Congratulations! You won!");
                    } else {
                        table[humanI - 2][humanJ] = 'S';
                        table[humanI - 1][humanJ] = 'H';
                        table[humanI][humanJ] = '0';
                        printWorld(table, tableSide);
                    }
                }
            } else {
                table[humanI - 1][humanJ] = 'H';
                table[humanI][humanJ] = '0';
                printWorld(table, tableSide);
            }
        }
        else{
            System.out.println("Invalid move");
        }
    }
    static void moveRight(char[][] table, int tableSide){
        int humanI = findHumanI(table);
        int humanJ = findHumanJ(table);

        if(humanJ != tableSide - 1 && table[humanI][humanJ + 1] != '#' && table[humanI][humanJ + 1] != 'E') {
            if (table[humanI][humanJ + 1] == 'S') {
                if (table[humanI][humanJ + 2] == '#' || humanJ == tableSide - 2) {
                    System.out.println("Invalid move");
                }
                else {
                    if (table[humanI][humanJ + 2] == 'E') {
                        System.out.println("Congratulations! You won!");
                    } else {
                        table[humanI][humanJ + 2] = 'S';
                        table[humanI][humanJ + 1] = 'H';
                        table[humanI][humanJ] = '0';
                        printWorld(table, tableSide);
                    }
                }
            } else {
                table[humanI][humanJ + 1] = 'H';
                table[humanI][humanJ] = '0';
                printWorld(table, tableSide);
            }
        }
        else{
            System.out.println("Invalid move");
        }
    }
    static void moveLeft(char[][] table, int tableSide){
        int humanI = findHumanI(table);
        int humanJ = findHumanJ(table);

        if(humanJ != 0 && table[humanI][humanJ - 1] != '#' && table[humanI][humanJ - 1] != 'E'){
            if(table[humanI][humanJ - 1] == 'S') {
                if (table[humanI][humanJ - 2] == '#' || humanJ == 1) {
                    System.out.println("Invalid move");
                } else {
                    if (table[humanI][humanJ - 2] == 'E') {
                        System.out.println("Congratulations! You won!");
                    } else {
                        table[humanI][humanJ - 2] = 'S';
                        table[humanI][humanJ - 1] = 'H';
                        table[humanI][humanJ] = '0';
                        printWorld(table, tableSide);
                    }
                }
            }
            else{
                table[humanI][humanJ - 1] = 'H';
                table[humanI][humanJ] = '0';
                printWorld(table, tableSide);
            }
        }
        else{
            System.out.println("Invalid move");
        }
    }
    static void nextMove(char nextMove, char[][] table, int tableSide){
        if(nextMove == 'w' || nextMove == 'W'){
            moveUp(table, tableSide);
        }
        else if(nextMove == 'd' || nextMove == 'D'){
            moveRight(table, tableSide);
        } else if (nextMove == 'a' || nextMove == 'A') {
            moveLeft(table, tableSide);
        }
        else if(nextMove == 's' || nextMove == 'S'){
            moveDown(table, tableSide);
        }
        else{
            System.out.println("Invalid input");
        }
    }
}
