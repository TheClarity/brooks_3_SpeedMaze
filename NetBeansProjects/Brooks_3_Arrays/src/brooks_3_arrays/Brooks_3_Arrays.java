/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brooks_3_arrays;

import java.util.Scanner;

/**
 *
 * @author Clarity
 */
public class Brooks_3_Arrays {

    public static int maxX = 50;
    public static int maxY = 50;
    public static int disX = 50;
    public static int disY = 50;
    public static int x = (int) Math.floor(Math.random() * maxX -1);
    public static int y = (int) Math.floor(Math.random() * maxY -1);
    public static int nx = x;
    public static int ny = y;
    public static String direction;
    public static int dir;
    public static Boolean Blocked = false;
    public static String[][] map = new String[maxX][maxY];
    public static boolean[][] wall = new boolean[maxX][maxY];
    public static boolean[][] trap = new boolean[maxX][maxY];
    public static boolean[][] chest = new boolean[maxX][maxY];
    public static boolean[][] enemy = new boolean[maxX][maxY];
    public static boolean[][] stun = new boolean[maxX][maxY];
    public static boolean[][] enemy1 = new boolean[maxX][maxY];
    public static boolean game = true;
    public static int enemyx;
    public static int enemyy;
    public static int stunx;
    public static int stuny;
    public static boolean stunA = false;
    public static int enemy1x;
    public static int enemy1y;
    public static int chestx;
    public static int chesty;
    public static int wallx;
    public static int wally;

    public static int bt;
    public static int stunTime = 3;
    public static boolean enemyA = true;
    public static boolean enemy1A = true;
    public static boolean count = true;
    public static int health = 15;
    /**
     * @param args the command line arguments
     */
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("You are on a mission to find the original dank meme. Find the '[]' which contains the meme, avoid the 'X's who are");
        System.out.println("trying to kill you. The #'s are traps that will hurt you, and the |'s are walls. Good Luck");
        setup();
    }
    public static void setup() {
                if (trap[y][x] == true) {
            x = (int) Math.floor(Math.random() * maxX);
            y = (int) Math.floor(Math.random() * maxY);
        }
        for (int t = 0; t < 50; t++) {
            int trapx = (int) Math.floor(Math.random() * maxX);
            int trapy = (int) Math.floor(Math.random() * maxY);
            trap[trapx][trapy] = true;
        }
        for (int w = 0; w < 50; w++) {
            wallx = (int) Math.floor(Math.random() * maxX);
            wally = (int) Math.floor(Math.random() * maxY);
            wall[wallx][wally] = true;
        }
        enemyx = (int) Math.floor(Math.random() * maxX);
        enemyy = (int) Math.floor(Math.random() * maxY);
        enemy[enemyx][enemyy] = true;
        enemy1x = (int) Math.floor(Math.random() * maxX);
        enemy1y = (int) Math.floor(Math.random() * maxY);
        enemy1[enemy1x][enemy1y] = true;
        chestx = (int) Math.floor(Math.random() * maxX);
        chesty = (int) Math.floor(Math.random() * maxY);
        chest[chestx][chesty] = true;
        while (game) {
            printArray(map);
            
            input();
//            trap();
            enemy();
            chest();
            edge();
            move();
            
        }
    }

    public static void printArray(String[][] map) {
//        map[stuny][stunx] = " X";
        map[y][x] = " @";
        map[enemyy][enemyx] = " X";
        map[wally][wallx] = " |";
        map[enemy1y][enemy1x] = " X";
        for (int i = 0; i <= map[0].length - 1; i++) {
            for (int j = 0; j <= map[1].length - 1; j++) {
                if (j < map[1].length - 1 ) {
                    if (trap[j][i]) {
                        map[i][j] = " #";
                    }
                    if (chest[j][i]) {
                        map[i][j] = "[]";
                    }
                    if (wall[j][i]) {
                        map[i][j] = " |";
                    }
//                    if (stun[j][i]) {
//                        map[i][j] = " *";
//                    }
                    if (enemy[j][i] || enemy1[j][i]) {
                        map[i][j] = " X";
                    }
                    if (map[i][j] != " @" && map[i][j] != " #" && map[i][j] != " X" && map[i][j] != "[]" && map[i][j] != " |" && map[i][j] != " *"
                            && map[i][j] != " -" && map[i][j] != "| " && map[i][j] != " +") {
                        System.out.print("  ");
                    } else {
                        System.out.print(map[i][j]);
                    }
                } else if (map[i][j] != " @") {
                    System.out.println("  ");
                } else {
                    System.out.println(map[i][j]);
                }
            }
        }

    }

    public static void input() {
        System.out.println("Which way would you like to go, N(W), E(A), S(S), or W(D). Press B to drop a stun.");
        direction = sc.nextLine();
        if (direction.contains("w")) {
            dir = 1;
            if (stunA == true) {
                stunBlow();
            }

        }
        if (direction.equalsIgnoreCase("a")) {
            dir = 2;
            if (stunA == true) {
                stunBlow();
            }
        }
        if (direction.equalsIgnoreCase("s")) {
            dir = 3;
            if (stunA == true) {
                stunBlow();
            }
        }
        if (direction.equalsIgnoreCase("d")) {
            dir = 4;
            if (stunA == true) {
                stunBlow();
            }
        }
        if (direction.equalsIgnoreCase("b")) {

            stun();

        }

    }

    public static void edge() {
        if (x == maxX - 1 && dir == 4) {
            Blocked = true;
        } else if (x == 0 && dir == 2) {
            Blocked = true;
        } else if (y == maxY - 1 && dir == 3) {
            Blocked = true;
        } else if (y == 0 && dir == 1) {
            Blocked = true;
        } else if (wall[nx + 1][ny] && dir == 4) {
            Blocked = true;
        } else if (wall[nx - 1][ny] && dir == 2) {
            Blocked = true;
        } else if (wall[nx][ny + 1] && dir == 3) {
            Blocked = true;
        } else if (wall[nx][ny - 1] && dir == 1) {
            Blocked = true;
        } else {
            Blocked = false;
        }

    }

    public static void stun() {
        stunA = true;
        bt = 4;
        System.out.println(bt);
        if (dir == 1) {
            map[ny + 1][nx] = " *";
            stuny = ny + 1;
            stunx = nx;
        } else {
        }
        if (dir == 2) {
            map[ny][nx + 1] = " *";
            stuny = ny;
            stunx = nx + 1;
        } else {
        }
        if (dir == 3) {
            map[ny - 1][nx] = " *";
            stuny = ny - 1;
            stunx = nx;

        } else {
        }
        if (dir == 4) {
            map[ny][nx - 1] = " *";
            stuny = ny;
            stunx = nx - 1;
        } else {
        }

    }

    public static void stunBlow() {
        if (stunA == true && bt <= 4 && bt > 0) {

            System.out.println(bt);
        } else {
            System.out.println(bt);
            bt = 0;
            System.out.println("boom");
            stunA = false;

            map[stuny + 1][stunx + 1] = " +";
            map[stuny - 1][stunx] = " -";
            map[stuny + 1][stunx] = " -";
            map[stuny - 1][stunx - 1] = " +";
            map[stuny + 1][stunx - 1] = " +";
            map[stuny - 1][stunx + 1] = " +";
            map[stuny][stunx + 1] = " |";
            map[stuny][stunx - 1] = " |";
            stun[stuny - 1][stunx] = true;
            stun[stuny + 1][stunx] = true;
            stun[stuny - 1][stunx - 1] = true;
            stun[stuny + 1][stunx - 1] = true;
            stun[stuny - 1][stunx + 1] = true;
            stun[stuny][stunx + 1] = true;
            stun[stuny][stunx - 1] = true;

        }

    }

    public static void countDown() {
         if (stunA) {
            count = false;
            stunTime = 3;
//            stunA = true;

            map[stuny + 1][stunx + 1] = "  ";

            map[stuny - 1][stunx] = "  ";
            map[stuny + 1][stunx] = "  ";
            map[stuny - 1][stunx - 1] = "  ";
            map[stuny + 1][stunx - 1] = "  ";
            map[stuny - 1][stunx + 1] = "  ";
            map[stuny][stunx + 1] = "  ";
            map[stuny][stunx - 1] = "  ";
            stun[stuny - 1][stunx] = false;
            stun[stuny + 1][stunx] = false;
            stun[stuny - 1][stunx - 1] = false;
            stun[stuny + 1][stunx - 1] = false;
            stun[stuny - 1][stunx + 1] = false;
            stun[stuny][stunx + 1] = false;
            stun[stuny][stunx - 1] = false;
        }

    }

    public static void trap() {
        if (trap[nx][ny]) {
            health -= 5;
            die();

        } else {

        }
        if (trap[enemyx][enemyy]) {
            enemyA = false;
        } else {

        }
        if (stun[enemyy][enemyx]) {
            enemyA = false;
            count = true;
        } else {

        }
        if (trap[enemy1x][enemy1y]) {
            enemy1A = false;
        } else {

        }
        if (stun[enemy1y][enemy1x]) {
            enemy1A = false;
            count = true;
        }
    }

    public static void enemy() {

        if (map[ny][nx] == " X") {
            System.out.println("__   __            _                   \n" +
"\\ \\ / /__  _   _  | |    ___  ___  ___ \n" +
" \\ V / _ \\| | | | | |   / _ \\/ __|/ _ \\\n" +
"  | | (_) | |_| | | |__| (_) \\__ \\  __/\n" +
"  |_|\\___/ \\__,_| |_____\\___/|___/\\___|");
            game = false;
        }
    }

    public static void chest() {

        if (map[ny][nx] == "[]") {
            win();
            game = false;
        }
    }

    public static void die() {
        if (health == 0) {
            System.out.println("__   __            _                   \n" +
"\\ \\ / /__  _   _  | |    ___  ___  ___ \n" +
" \\ V / _ \\| | | | | |   / _ \\/ __|/ _ \\\n" +
"  | | (_) | |_| | | |__| (_) \\__ \\  __/\n" +
"  |_|\\___/ \\__,_| |_____\\___/|___/\\___|");
            game = false;
        }
    }

    public static void move() {
        if (!Blocked && dir == 1) {
            map[y][x] = "  ";
            ny--;
            countDown();
            trap();
            enemy();
            y--;
            if (count && stunTime > 0 && bt > 0) {

            System.out.println(count);
            stunTime--;

        }
            bt--;
        } else {

        }
        if (!Blocked && dir == 2) {
            map[y][x] = "  ";
            nx--;
            countDown();
            trap();
            enemy();
            if (count && stunTime > 0 && bt > 0) {

            System.out.println(count);
            stunTime--;

        }
            x--;
            bt--;
        } else {

        }
        if (!Blocked && dir == 3) {
            map[y][x] = "  ";
            ny++;
            countDown();
            trap();
            enemy();
            if (count && stunTime > 0 && bt > 0) {

            System.out.println(count);
            stunTime--;

        }
            y++;
            bt--;
        } else {

        }
        if (!Blocked && dir == 4) {
            map[y][x] = "  ";
            nx++;
            countDown();
            trap();
            enemy();
            if (count && stunTime > 0 && bt > 0) {

            System.out.println(count);
            stunTime--;

        }
            x++;
            bt--;

        } else {

        }
        if (enemyA == true) {
            if (enemyx > nx) {
                map[enemyy][enemyx] = "  ";
                enemyx--;
            } else {

            }
            if (enemyy > ny) {
                map[enemyy][enemyx] = "  ";
                enemyy--;
            } else {

            }
            if (enemyx < nx) {
                map[enemyy][enemyx] = "  ";
                enemyx++;
            } else {

            }
            if (enemyy < ny) {
                map[enemyy][enemyx] = "  ";
                enemyy++;
            } else {

            }
        }
        if (enemy1A == true) {

            if (enemy1x > nx) {
                map[enemy1y][enemy1x] = "  ";
                enemy1x--;
            } else {

            }
            if (enemy1y > ny) {
                map[enemy1y][enemy1x] = "  ";
                enemy1y--;
            } else {

            }
            if (enemy1x < nx) {
                map[enemy1y][enemy1x] = "  ";
                enemy1x++;
            } else {

            }
            if (enemy1y < ny) {
                map[enemy1y][enemy1x] = "  ";
                enemy1y++;
            }
        } else {

        }
    }

    public static void win() {
        System.out.println("__   __           __        ___       \n" +
"\\ \\ / /__  _   _  \\ \\      / (_)_ __  \n" +
" \\ V / _ \\| | | |  \\ \\ /\\ / /| | '_ \\ \n" +
"  | | (_) | |_| |   \\ V  V / | | | | |\n" +
"  |_|\\___/ \\__,_|    \\_/\\_/  |_|_| |_|");
    }

}
