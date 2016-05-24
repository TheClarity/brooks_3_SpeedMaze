/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brooks_3_arrays;

/**
 *
 * @author Clarity
 */
public class Player {
   public static int x;
   public static int y;
   public static int HP;
   public char symbol = '@';
    int score = 0;
    int level = 0;
    int xp = 0;
    Player(int a , int b) {
        this.x = a;
        this.y = b;
        this.HP = 100;
    }
    
}
