package ForestHunt_brooks;

import java.util.ArrayList;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.tiled.TiledMap;

class item {

    public int x;
    public int y;
    public boolean isvisible = true;
    Image currentImage;
    Shape hitbox;
    Image healthpotion = new Image(
            "res/health_potion.png");

    item(int a, int b) throws SlickException {
        this.x = a;
        this.y = b;
        this.hitbox = new Rectangle(a, b, 32, 32);// 64 is the width of the item
        this.currentImage = healthpotion;

    }

}

class item1 {

    public int x;
    public int y;
    public boolean isvisible = true;
    Image currentImage;
    Shape hitbox;
    Image healthpotion = new Image("res/speed_potion.png");

    item1(int a, int b) throws SlickException {
        this.x = a;
        this.y = b;
        this.hitbox = new Rectangle(a, b, 32, 32);// 64 is the width of the item
        this.currentImage = healthpotion;
    }

}

class itemwin {

    public int x;
    public int y;
    public static boolean isvisible = true;
    Image currentImage;
    Shape hitbox;
    Image antidote = new Image("res/antidote.png");

    itemwin(int a, int b) throws SlickException {
        this.x = a;
        this.y = b;
        this.hitbox = new Rectangle(a, b, 32, 32);// 64 is the width of the item
        this.currentImage = antidote;
    }

}

public class SpeedMaze extends BasicGameState {

    public Player player;
    public item healthpotion, healthpotion1;
    public item1 speedpotion, speedpotion1;
    public itemwin antidote;
    public Orb magic8ball, orb1;
    public Enemy jackson;
    //public Orb theBestOrbEver;
    //  public ArrayList<Orb> orbList = new ArrayList();
    public ArrayList<item> stuff = new ArrayList();
    public ArrayList<item1> stuff1 = new ArrayList();
    public ArrayList<itemwin> stuffwin = new ArrayList();
    public ArrayList<Enemy> enemiez = new ArrayList();
    private boolean[][] hostiles;
    private static TiledMap grassMap;
    private static AppGameContainer app;
    private static Camera camera;
    public static int counter = 0;
    // private Animation sprite, up, down, left, right, wait;
    private static final int SIZE = 64;
    private static final int SCREEN_WIDTH = 1000;
    private static final int SCREEN_HEIGHT = 750;

    public SpeedMaze(int xSize, int ySize) {

    }

    public void init(GameContainer gc, StateBasedGame sbg)
            throws SlickException {

        gc.setTargetFrameRate(60);
        gc.setShowFPS(false);
        grassMap = new TiledMap("res/d3.tmx");
        System.out.println("Tile map is this wide: " + grassMap.getWidth());
        camera = new Camera(gc, grassMap);

        player = new Player();
        Blocked.blocked = new boolean[grassMap.getWidth()][grassMap.getHeight()];

        System.out.println("The grassmap is " + grassMap.getWidth() + "by "
                + grassMap.getHeight());

        for (int xAxis = 0; xAxis < grassMap.getWidth(); xAxis++) {
            for (int yAxis = 0; yAxis < grassMap.getHeight(); yAxis++) {
                // int tileID = grassMap.getTileId(xAxis, yAxis, 0);
                // Why was this changed?
                // It's a Different Layer.
                // You should read the TMX file. It's xml, i.e.,human-readable
                // for a reason
                int tileID = grassMap.getTileId(xAxis, yAxis, 1);
                String value = grassMap.getTileProperty(tileID,
                        "blocked", "false");
                if ("true".equals(value)) {

                    System.out.println("The tile at x " + xAxis + " andy axis "
                            + yAxis + " is blocked.");
                    Blocked.blocked[xAxis][yAxis] = true;
                }
            }
        }
        System.out.println("Array length" + Blocked.blocked[0].length);
        // A remarkably similar process for finding hostiles
        hostiles = new boolean[grassMap.getWidth()][grassMap.getHeight()];

        for (int xAxis = 0; xAxis < grassMap.getWidth(); xAxis++) {
            for (int yAxis = 0; yAxis < grassMap.getHeight(); yAxis++) {
                int xBlock = (int) xAxis;
                int yBlock = (int) yAxis;
                if (!Blocked.blocked[xBlock][yBlock]) {
                    if (yBlock % 7 == 0 && xBlock % 15 == 0) {
                        item i = new item(xAxis * SIZE, yAxis * SIZE);
                        stuff.add(i);
                        //stuff1.add(h);
                        hostiles[xAxis][yAxis] = true;
                    }
                }
            }
        }

        for (int xAxis = 0; xAxis < grassMap.getWidth(); xAxis++) {
            for (int yAxis = 0; yAxis < grassMap.getHeight(); yAxis++) {
                int xBlock = (int) xAxis;
                int yBlock = (int) yAxis;
                if (!Blocked.blocked[xBlock][yBlock]) {
                    if (xBlock % 9 == 0 && yBlock % 25 == 0) {
                        item1 h = new item1(xAxis * SIZE, yAxis * SIZE);
                        //	stuff.add(i);
                        stuff1.add(h);
                        hostiles[xAxis][yAxis] = true;
                    }
                }
            }
        }

        for (int xAxis = 0; xAxis < grassMap.getWidth(); xAxis++) {
            for (int yAxis = 0; yAxis < grassMap.getHeight(); yAxis++) {
                int xBlock = (int) xAxis;
                int yBlock = (int) yAxis;
                if (!Blocked.blocked[xBlock][yBlock]) {
                    if (yBlock % 7 == 0 && xBlock % 15 == 0) {
                        Enemy e = new Enemy(xAxis * SIZE, yAxis * SIZE);
                        enemiez.add(e);
                        //stuff1.add(h);
                        hostiles[xAxis][yAxis] = true;
                    }
                }
            }
        }

        healthpotion = new item(100, 100);
        healthpotion1 = new item(450, 400);
        stuff.add(healthpotion);
        //stuff.add(healthpotion1);
        jackson = new Enemy((int) player.x + 142, (int) player.y + 142);
        orb1 = new Orb((int) player.x, (int) player.y);
        // magic8ball = new Orb((int) Player.x + 5, (int) Player.y - 10);
        //theBestOrbEver = new Orb(player.x + 10, player.y - 10);
        //theBestOrbEver = new Orb( (int) player.x + 10, (int) player.y - 10);
        speedpotion = new item1(100, 150);
        speedpotion1 = new item1(450, 100);
        stuff1.add(speedpotion);
        stuff1.add(speedpotion1);
        antidote = new itemwin(3004, 92);
        stuffwin.add(antidote);
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
            throws SlickException {
        camera.centerOn((int) player.x, (int) player.y);
        camera.drawMap();
        camera.translateGraphics();
        // it helps to add status reports to see what's going on
        // but it gets old quickly
        // System.out.println("Current X: " +Player.x + " \n Current Y: "+ y);
        player.sprite.draw((int) player.x, (int) player.y);
        //g.drawString("x: " + (int)Player.x + "y: " +(int)Player.y , Player.x, Player.y - 10);
        g.drawString("Health: " + player.health / 1000, camera.cameraX + 10,
                camera.cameraY + 10);

        g.drawString("speed: " + (int) (player.speed * 10), camera.cameraX + 10,
                camera.cameraY + 25);

        //g.draw(Player.rect);
        g.drawString("time passed: " + counter / 1000, camera.cameraX + 600, camera.cameraY);
        // moveenemies();

//        if (theBestOrbEver.isIsVisible()) {
//            theBestOrbEver.orbpic.draw(theBestOrbEver.getX(), theBestOrbEver.getY());
//        }
        for (item i : stuff) {
            if (i.isvisible) {
                i.currentImage.draw(i.x, i.y);
                // draw the hitbox
                //g.draw(i.hitbox);

            }
        }

        for (item1 h : stuff1) {
            if (h.isvisible) {
                h.currentImage.draw(h.x, h.y);
                // draw the hitbox
                //g.draw(h.hitbox);

            }
        }

        for (itemwin w : stuffwin) {
            if (w.isvisible) {
                w.currentImage.draw(w.x, w.y);
                // draw the hitbox
                //g.draw(w.hitbox);

            }
        }
//        if (magic8ball.isIsVisible()) {
//            magic8ball.orbpic.draw(magic8ball.getX(), magic8ball.getY());
//        }
        if (orb1.isIsVisible()) {
            orb1.orbpic.draw(orb1.getX(), orb1.getY());
        }

        for (Enemy e : enemiez) {
            if (e.isVisible) {
                e.currentanime.draw(e.Bx, e.By);
            }
        }
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta)
            throws SlickException {

        counter += delta;
        Input input = gc.getInput();
        float fdelta = delta * player.speed;
        player.setpdelta(fdelta);
        double rightlimit = (grassMap.getWidth() * SIZE) - (SIZE * 0.75);
        // System.out.println("Right limit: " + rightlimit);
        float projectedright = player.x + fdelta + SIZE;
        boolean cangoright = projectedright < rightlimit;
        // there are two types of fixes. A kludge and a hack. This is a kludge.
        if (input.isKeyDown(Input.KEY_UP)) {
            player.setDirection(0);
            player.sprite = player.up;
            float fdsc = (float) (fdelta - (SIZE * .15));
            if (!(isBlocked(player.x, player.y - fdelta) || isBlocked(
                    (float) (player.x + SIZE + 1.5), player.y - fdelta))) {
                player.sprite.update(delta);
                // The lower the delta the slower the sprite will animate.
                player.y -= fdelta;
            }

        } else if (input.isKeyDown(Input.KEY_DOWN)) {
            player.setDirection(2);
            player.sprite = player.down;
            if (!isBlocked(player.x, player.y + SIZE + fdelta)
                    || !isBlocked(player.x + SIZE - 1, player.y + SIZE + fdelta)) {
                player.sprite.update(delta);
                player.y += fdelta;
            }

        } else if (input.isKeyDown(Input.KEY_LEFT)) {
            player.setDirection(3);
            player.sprite = player.left;
            if (!(isBlocked(player.x - fdelta, player.y) || isBlocked(player.x
                    - fdelta, player.y + SIZE - 1))) {
                player.sprite.update(delta);
                player.x -= fdelta;
            }

        } else if (input.isKeyDown(Input.KEY_RIGHT)) {
            player.setDirection(1);
            player.sprite = player.right;
            // the boolean-kludge-implementation
            if (cangoright
                    && (!(isBlocked(player.x + SIZE + fdelta,
                            player.y) || isBlocked(player.x + SIZE + fdelta, player.y
                            + SIZE - 1)))) {
                player.sprite.update(delta);
                player.x += fdelta;
            } // else { System.out.println("Right limit reached: " +
            // rightlimit);}
        } else if (input.isKeyPressed(Input.KEY_SPACE)) {
            //orb1.setLocation(player.x, player.y);
//            orb1.setDirection(player.getDirection());
            orb1.settimeExists(1000);
            orb1.setX((int) player.x);
            orb1.setY((int) player.y);
            orb1.hitbox.setX(orb1.getX());
            orb1.hitbox.setY(orb1.getY());
            orb1.setIsVisible(!orb1.isIsVisible());
            //magic8ball.setIsVisible(true);
        }

        player.rect.setLocation(player.getPlayershitboxX(),
                player.getPlayershitboxY());

        for (item i : stuff) {

            if (player.rect.intersects(i.hitbox)) {
                //System.out.println("yay");
                if (i.isvisible) {

                    player.health += 10000;
                    i.isvisible = false;
                }

            }
        }

        for (item1 h : stuff1) {

            if (player.rect.intersects(h.hitbox)) {
                //System.out.println("yay");
                if (h.isvisible) {

                    player.speed += .4f;
                    h.isvisible = false;
                }

            }
        }

        for (itemwin w : stuffwin) {

            if (player.rect.intersects(w.hitbox)) {
                //System.out.println("yay");
                if (w.isvisible) {
                    w.isvisible = false;
                    makevisible();
                    sbg.enterState(3, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));

                }

            }
        }

        for (Enemy e : enemiez) {
            if (orb1.hitbox.intersects(e.rect)) {
                e.isVisible = false;
            }
        }

        if (orb1.isIsVisible()) {

            if (orb1.gettimeExists() > 0) {
                if (player.getDirection() == 0) {

                    orb1.setX((int) player.x);
                    orb1.setY(orb1.getY() - 5);
                } else if (player.getDirection() == 2) {
                    orb1.setX((int) player.x);
                    orb1.setY(orb1.getY() + 5);
                } else if (player.getDirection() == 3) {
                    orb1.setX(orb1.getX() - 5);
                    orb1.setY(orb1.getY());
                } else if (player.getDirection() == 1) {
                    orb1.setX(orb1.getX() + 5);
                    orb1.setY(orb1.getY());
                }

                orb1.hitbox.setX(orb1.getX());
                orb1.hitbox.setY(orb1.getY());
                orb1.countdown();
            } else {
                orb1.setIsVisible(false);
            }
        }

        player.health -= counter / 1000;
        if (player.health <= 0) {
            makevisible();
            sbg.enterState(2, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
        }

    }

    public int getID() {

        return 1;

    }

    public void makevisible() {
        for (item1 h : stuff1) {

            h.isvisible = true;
        }

        for (item i : stuff) {

            i.isvisible = true;
        }
    }

    private boolean isBlocked(float tx, float ty) {

        int xBlock = (int) tx / SIZE;

        int yBlock = (int) ty / SIZE;

        return Blocked.blocked[xBlock][yBlock];

        // this could make a better kludge
    }

}
