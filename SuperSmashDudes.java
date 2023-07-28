

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.*;
public class SuperSmashDudes {
	
		static AudioInputStream audioStream;
		static Clip music;
	
	    // Game Window properties
	    static JFrame gameWindow;
	    static GraphicsPanel canvas;
	    static final int WIDTH = 1920;
	    static final int HEIGHT = 1080;
	    // key listener
	    static MyKeyListener keyListener = new MyKeyListener();    
	    
	    //--------------------------------------------------------------------------
	    // declare the properties of all game objects here
	    //--------------------------------------------------------------------------
	    static final int VOID = 1080;
	    static final int GROUNDY = 500; //ground level
	    static final int GROUNDX = 370;
	    static final int GROUNDW = 1170;
	    
	    static final int PLATFORM1Y = 330;
	    static final int PLATFORM1X = 509;
	    static final int PLATFORM1W = 281;
	    
	    static final int PLATFORM2X = 1134;
	    static final int PLATFORM2Y = 330;
	    static final int PLATFORM2W = 281;
	    
	    static final int PLATFORM3X = 826;
	    static final int PLATFORM3Y = 154;
	    static final int PLATFORM3W = 273;
	    
	    static final int RUN_SPEED = 10;
	    static final int JUMP_SPEED = -30;
	    static final int GRAVITY = 2;
	    static final int MIDDLE = WIDTH/2;
	    
	    //map design 
	    //static final int platform_one = 
	    static int counterp1 = 0;
	    static int counterp2 = 0;
	    static int jumpsp1 = 0;
	    static int jumpsp2 = 0;
	    
	    //player1 and player2
	    static int player1W = 50; //objectW
	    static int player1H = 50; // objectH
	    static int player1X = 620; //objectX
        static int player1Y = PLATFORM1Y - player1H-50; //object1Y
	    static int player1Vx = 0; //objectVx
	    static int player1Vy = 0; //player1Vy
	    static Rectangle player1Box = new Rectangle(player1X, player1Y, player1W, player1H);
	    static int player1DeathCounter = 0;
	    
	    static int player2W = 50; //objectW
	    static int player2H = 50; // objectH
	    static int player2X = 1255; //objectX
        static int player2Y = PLATFORM2Y - player2H-50; //object2Y
	    static int player2Vx = 0; //objectVx
	    static int player2Vy = 0; //objectVy
	    static Rectangle player2Box = new Rectangle(player2X, player2Y, player2W, player2H);
	    static int player2DeathCounter = 0;
	    
	    // left bullets properties P1
	    static int numBullets = 40; 
	    static int bulletDirectionP1 = 1;   
	    static int[] bulletXLeftP1 = new int[numBullets];
	    static int[] bulletYLeftP1 = new int[numBullets];
	    static boolean[] bulletVisibleLeftP1 = new boolean[numBullets];
	    static int bulletWLeftP1 = 10;
	    static int bulletHLeftP1 = 10;
	    static int bulletSpeedLeftP1 = -20;
	    static int currentBulletLeftP1 = 0;
	    static Rectangle bulletBoxLeftP1 = null;
	    	    
	    //right bullets properties P1
	    static int[] bulletXRightP1 = new int[numBullets];
	    static int[] bulletYRightP1 = new int[numBullets];
	    static boolean[] bulletVisibleRightP1 = new boolean[numBullets];
	    static int bulletWRightP1 = 10;
	    static int bulletHRightP1 = 10;
	    static int bulletSpeedRightP1 = 20;
	    static int currentBulletRightP1 = 0;
	    static Rectangle bulletBoxRightP1 = null;
	    
	    // left bullets properties P2
	    static int bulletDirectionP2 = 1;   
	    static int[] bulletXLeftP2 = new int[numBullets];
	    static int[] bulletYLeftP2 = new int[numBullets];
	    static boolean[] bulletVisibleLeftP2 = new boolean[numBullets];
	    static int bulletWLeftP2 = 10;
	    static int bulletHLeftP2 = 10;
	    static int bulletSpeedLeftP2 = -20;
	    static int currentBulletLeftP2 = 0;
	    static Rectangle bulletBoxLeftP2 = null;
	    
	    //right bullets properties P2
	    static int[] bulletXRightP2 = new int[numBullets];
	    static int[] bulletYRightP2 = new int[numBullets];
	    static boolean[] bulletVisibleRightP2 = new boolean[numBullets];
	    static int bulletWRightP2 = 10;
	    static int bulletHRightP2 = 10;
	    static int bulletSpeedRightP2 = 20;
	    static int currentBulletRightP2 = 0;
	    static Rectangle bulletBoxRightP2 = null;
	    
	 // left punch properties P1
	    static int numPunch = 40; 
	    static int punchDirectionP1 = 1;   
	    static int[] punchXLeftP1 = new int[numPunch];
	    static int[] punchYLeftP1 = new int[numPunch];
	    static boolean[] punchVisibleLeftP1 = new boolean[numPunch];
	    static int punchWLeftP1 = 70;
	    static int punchHLeftP1 = 40;
	    static int currentPunchLeftP1 = 0;
	    static int punchCounterP1 = 0;
	    static int punchTimerP1 = 0;
	    static int punchTimeP1 = 0;
	    static int punchHitP1 = 0;
	    static int bulletAttackP1 = 0;
	    static Rectangle punchBoxLeftP1 = null;
	    
	    //right punch p1
	    static int[] punchXRightP1 = new int[numPunch];
	    static int[] punchYRightP1 = new int[numPunch];
	    static boolean[] punchVisibleRightP1 = new boolean[numPunch];
	    static int punchWRightP1 = 70;
	    static int punchHRightP1 = 40;
	    static int currentPunchRightP1 = 0;
	    static Rectangle punchBoxRightP1 = null;
	    
	    //left punch p2
	    static int punchDirectionP2 = 1;   
	    static int[] punchXLeftP2 = new int[numPunch];
	    static int[] punchYLeftP2 = new int[numPunch];
	    static boolean[] punchVisibleLeftP2 = new boolean[numPunch];
	    static int punchWLeftP2 = 70;
	    static int punchHLeftP2 = 40;
	    static int currentPunchLeftP2 = 0;
	    static int punchCounterP2 = 0;
	    static int punchTimerP2 = 0;
	    static int punchTimeP2 = 0;
	    static int punchHitP2 = 0;
	    static int bulletAttackP2 = 0;
	    static Rectangle punchBoxLeftP2 = null;
	    
	    //right punch p2
	    static int[] punchXRightP2 = new int[numPunch];
	    static int[] punchYRightP2 = new int[numPunch];
	    static boolean[] punchVisibleRightP2 = new boolean[numPunch];
	    static int punchWRightP2 = 70;
	    static int punchHRightP2 = 40;
	    static int currentPunchRightP2 = 0;
	    static Rectangle punchBoxRightP2 = null;
	    
	    //health length p1
	    static int healthLengthP1 = 350;
	    static int healthCounterP1 = 0;
	    
	    //health p2
	    static int healthXP2 = 1520;
	    static int healthLengthP2 = 350;
	    static int healthCounterP2 = 0;
	    
	    //P2 win text X
	    static int P2WinX = 810;
	    static int P1WinX = 841;
	    
	    static Image therock = null;
	    static Image kevinhart = null;
	    static Image background = null;
	    static Image cannonball = null;
	    static Image punchLeft = null;
	    static Image punchRight = null;
	//------------------------------------------------------------------------------    
	    public static void main(String[] args) {
	    	
	    	 try {
		    		File audioFile = new File ("Smash Theme.wav"); //download audio file and copy pathname to quotations
		    		audioStream = AudioSystem.getAudioInputStream(audioFile);
		    		music = AudioSystem.getClip();
		    		music.open(audioStream);
		    	} catch (Exception ex) {}
		    	music.start();
		    	music.loop(Clip.LOOP_CONTINUOUSLY);

	        gameWindow = new JFrame("Game Window");
	        gameWindow.setSize(WIDTH,HEIGHT);
	        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			try {
				background = ImageIO.read(new File("backbros.png")); //donwload files and copy pathname of image to quotations
				cannonball = ImageIO.read(new File("cannonball.png"));
				therock = ImageIO.read(new File("therock.png"));
				kevinhart = ImageIO.read(new File("kevinhart.png"));
				punchLeft = ImageIO.read(new File("leftpunch.png"));
				punchRight = ImageIO.read(new File("punch.png"));
			} catch (IOException e) {
				
				e.printStackTrace();
			}	        
	        canvas = new GraphicsPanel();
	        canvas.addKeyListener(keyListener);
	        gameWindow.add(canvas); 
	       
	        
	     // generate bullets P1
	        Arrays.fill(bulletXLeftP1,0);
	        Arrays.fill(bulletYLeftP1,0);
	        Arrays.fill(bulletVisibleLeftP1,false);
	        bulletBoxLeftP1 = new Rectangle(bulletXLeftP1[0], bulletYLeftP1[0], bulletWLeftP1, bulletHLeftP1);
	        Arrays.fill(bulletXRightP1,0);
	        Arrays.fill(bulletYRightP1,0);
	        Arrays.fill(bulletVisibleRightP1,false);
	        bulletBoxRightP1 = new Rectangle(bulletXRightP1[0], bulletYRightP1[0], bulletWRightP1, bulletHRightP1);
	        
	        //generate bullets P2
	        Arrays.fill(bulletXLeftP2,0);
	        Arrays.fill(bulletYLeftP2,0);
	        Arrays.fill(bulletVisibleLeftP2,false);
	        bulletBoxLeftP2 = new Rectangle(bulletXLeftP2[0], bulletYLeftP2[0], bulletWLeftP2, bulletHLeftP2);
	        Arrays.fill(bulletXRightP2,0);
	        Arrays.fill(bulletYRightP2,0);
	        Arrays.fill(bulletVisibleRightP2,false);
	        bulletBoxRightP2 = new Rectangle(bulletXRightP2[0], bulletYRightP2[0], bulletWRightP2, bulletHRightP2);
	        
	        //generate punch left and right P1
	        Arrays.fill(punchXLeftP1,0);
	        Arrays.fill(punchYLeftP1,0);
	        Arrays.fill(punchVisibleLeftP1,false);
	        punchBoxLeftP1 = new Rectangle(punchXLeftP1[0], punchYLeftP1[0], punchWLeftP1, punchHLeftP1);
	        Arrays.fill(punchXRightP1,0);
	        Arrays.fill(punchYRightP1,0);
	        Arrays.fill(punchVisibleRightP1,false);
	        punchBoxRightP1 = new Rectangle(punchXRightP1[0], punchYRightP1[0], punchWRightP1, punchHRightP1);
	        
	      //generate punch left and right P1
	        Arrays.fill(punchXLeftP2,0);
	        Arrays.fill(punchYLeftP2,0);
	        Arrays.fill(punchVisibleLeftP2,false);
	        punchBoxLeftP2 = new Rectangle(punchXLeftP2[0], punchYLeftP2[0], punchWLeftP2, punchHLeftP2);
	        Arrays.fill(punchXRightP2,0);
	        Arrays.fill(punchYRightP2,0);
	        Arrays.fill(punchVisibleRightP2,false);
	        punchBoxRightP2 = new Rectangle(punchXRightP2[0], punchYRightP2[0], punchWRightP2, punchHRightP2);
	        
	        gameWindow.setVisible(true);
	        runGameLoop();
	        
	       
	    } // main method end
	    
	//------------------------------------------------------------------------------   
	    public static void runGameLoop(){
	        while (true) {
	        	player1Box = new Rectangle(player1X, player1Y, player1W, player1H);
	        	player2Box = new Rectangle(player2X, player2Y, player2W, player2H);
	        	punchTimerP1 += 20;
	        	if (punchCounterP1 == 1) {
	        		punchTimeP1 += 25;
	        	}
	        	punchTimerP2 += 20;
	        	if (punchCounterP2 == 1) {
	        		punchTimeP2 += 25;
	        	}
	            gameWindow.repaint();
	            try  {Thread.sleep(20);} catch(Exception e){}
	            //------------------------------------------------------------------
	            // implement the game functionality here
	            //------------------------------------------------------------------
	            player1X = player1X + player1Vx;
	            player1Vy = player1Vy + GRAVITY;
	            player1Y = player1Y + player1Vy;
	            if (player1Y+player1H >= VOID){
	                player1Y = VOID - player1H;
	                player1Vy = 0;
	            }
	            player2X = player2X + player2Vx;
	            player2Vy = player2Vy + GRAVITY;
	            player2Y = player2Y + player2Vy;
	            if (player2Y+player2H >= VOID){
	                player2Y = VOID - player2H;
	                player2Vy = 0;
	            }
	            
	            //player1
	            if (player1X+player1W>GROUNDX && player1X<GROUNDX+GROUNDW && player1Y+player1H>GROUNDY && player1Y+player1H-45<GROUNDY && player1Vy>0) {
	            	player1Y = GROUNDY - player1H;
	            	player1Vy = 0;
			        jumpsp1 = 0;	            	            				            		            		            	
	            }
	            else if (player1X+player1W>PLATFORM1X && player1X<PLATFORM1X+PLATFORM1W && player1Y+player1H>PLATFORM1Y && player1Y+player1H-40<PLATFORM1Y && player1Vy>0){
	            	player1Y = PLATFORM1Y - player1H;
	            	player1Vy = 0;
	            	jumpsp1 = 0;
	            }
            	else if (player1X+player1W>PLATFORM2X && player1X<PLATFORM2X+PLATFORM2W && player1Y+player1H>PLATFORM2Y && player1Y+player1H-40<PLATFORM2Y && player1Vy>0){
	            	player1Y = PLATFORM2Y - player1H;
	            	player1Vy = 0;
	            	jumpsp1 = 0;
	            }     
            	else if (player1X+player1W>PLATFORM3X && player1X<PLATFORM3X+PLATFORM3W && player1Y+player1H>PLATFORM3Y && player1Y+player1H-33<PLATFORM3Y && player1Vy>0){
	            	player1Y = PLATFORM3Y - player1H;
	            	player1Vy = 0;
	            	jumpsp1 = 0;
	            }	            
	            else if (jumpsp1<2){
	            	counterp1=1;
	            }
	            
	            //player2
	            if (player2X+player2W>GROUNDX && player2X<GROUNDX+GROUNDW && player2Y+player2H>GROUNDY && player2Y+player2H<GROUNDY+45 && player2Vy>0){
	            	player2Y = GROUNDY - player2H;
	            	player2Vy = 0;
	            	jumpsp2 = 0;
	            }
	            else if (player2X+player2W>PLATFORM1X && player2X<PLATFORM1X+PLATFORM1W && player2Y+player2H>PLATFORM1Y && player2Y+player2H-40<PLATFORM1Y && player2Vy>0){
	            	player2Y = PLATFORM1Y - player2H;
	            	player2Vy = 0;
	            	jumpsp2 = 0;
	            }
	            
	            else if (player2X+player2W>PLATFORM2X && player2X<PLATFORM2X+PLATFORM2W && player2Y+player2H>PLATFORM2Y && player2Y+player2H-40<PLATFORM2Y && player2Vy>0){
	            	player2Y = PLATFORM2Y - player2H;
	            	player2Vy = 0;
	            	jumpsp2 = 0;
	            }
	            
	            else if (player2X+player2W>PLATFORM3X && player2X<PLATFORM3X+PLATFORM3W && player2Y+player2H>PLATFORM3Y && player2Y+player2H-33<PLATFORM3Y && player2Vy>0){
	            	player2Y = PLATFORM3Y - player2H;
	            	player2Vy = 0;
	            	jumpsp2 = 0;
	            }
	            else if (jumpsp2<2){
	            	counterp2=1;
	            }	            
	            
	            //move bullets P1

	            for (int i=0; i<numBullets; i++){
		            if (bulletVisibleLeftP1[i]){
		            	bulletXLeftP1[i] = bulletXLeftP1[i] + bulletSpeedLeftP1;
		            	bulletBoxLeftP1 = new Rectangle(bulletXLeftP1[i], bulletYLeftP1[i], bulletWLeftP1, bulletHLeftP1);
		            	if (bulletXLeftP1[i]<0 || bulletXLeftP1[i]>WIDTH || bulletBoxLeftP1.intersects(player2Box)) {
				            bulletVisibleLeftP1[i] = false;
				            if(bulletBoxLeftP1.intersects(player2Box)) {
				            	bulletBoxLeftP1 = new Rectangle(1920, bulletYLeftP1[i], bulletWLeftP1, bulletHLeftP1);
				            	if (healthCounterP2 == 0 && healthLengthP2 > 18) {
				            		healthLengthP2 -= 18;
					            	healthXP2 += 18;
					            	healthCounterP2 = 1;
				            	}
				            	else if (healthCounterP2 == 1 && healthLengthP2 > 17){
				            		healthLengthP2 -= 17;
					            	healthXP2 += 17;
					            	healthCounterP2 = 0;
				            	}
				            	else {
				            		player2DeathCounter += 1;
				            		System.out.println(player2DeathCounter);
				            		healthLengthP2 = 350;
                                    healthXP2 = 1520;
                                    player2X = WIDTH/2-23;
                                    player2Y = PLATFORM3Y-player2H-100;
				            	}
				            }
		            	}		            	
			        }
			    }
	            
	            for (int i=0; i<numBullets; i++){
		            if (bulletVisibleRightP1[i]){
		            	bulletXRightP1[i] = bulletXRightP1[i] + bulletSpeedRightP1;
		            	bulletBoxRightP1 = new Rectangle(bulletXRightP1[i], bulletYRightP1[i], bulletWRightP1, bulletHRightP1);
				        if (bulletXRightP1[i]<0 || bulletXRightP1[i]>WIDTH || bulletBoxRightP1.intersects(player2Box)) {
				            bulletVisibleRightP1[i] = false;
				            if(bulletBoxRightP1.intersects(player2Box)) {
				            	bulletBoxRightP1 = new Rectangle(1920, bulletYRightP1[i], bulletWRightP1, bulletHRightP1);
				            	if (healthCounterP2 == 0 && healthLengthP2 > 18) {
				            		healthLengthP2 -= 18;
					            	healthXP2 += 18;
					            	healthCounterP2 = 1;
				            	}
				            	else if (healthCounterP2 == 1 && healthLengthP2 > 17){
				            		healthLengthP2 -= 17;
					            	healthXP2 += 17;
					            	healthCounterP2 = 0;
				            	}
				            	else {
				            		player2DeathCounter += 1;
				            		healthLengthP2 = 350;
                                    healthXP2 = 1520;
                                    player2X = WIDTH/2-23;
                                    player2Y = PLATFORM3Y-player2H-100;
				            	}
				            }
		            	}
			        }
			    }
	            
	            //move bullets P2
	            for (int i=0; i<numBullets; i++){
		            if (bulletVisibleLeftP2[i]){
		            	bulletXLeftP2[i] = bulletXLeftP2[i] + bulletSpeedLeftP2;
		            	bulletBoxLeftP2 = new Rectangle(bulletXLeftP2[i], bulletYLeftP2[i], bulletWLeftP2, bulletHLeftP2);
				        if (bulletXLeftP2[i]<0 || bulletXLeftP2[i]>WIDTH || bulletBoxLeftP2.intersects(player1Box)) {
				            bulletVisibleLeftP2[i] = false;		 
				            if(bulletBoxLeftP2.intersects(player1Box)) {
				            	bulletBoxLeftP2 = new Rectangle(1920, bulletYLeftP2[i], bulletWLeftP2, bulletHLeftP2);
				            	if (healthCounterP1 == 0 && healthLengthP1 > 18) {
				            		healthLengthP1 -= 18;
					            	healthCounterP1 = 1;
				            	}
				            	else if (healthCounterP1 == 1 && healthLengthP1 > 17){
				            		healthLengthP1 -= 17;
					            	healthCounterP1 = 0;
				            	}
				            	else {
				            		player1DeathCounter += 1;
				            		healthLengthP1 = 350;
                                    player1X = WIDTH/2-23;
                                    player1Y = PLATFORM3Y-player1H-100;
				            	}
				            }
				        }
			        }
			    }
	            
	            for (int i=0; i<numBullets; i++){
		            if (bulletVisibleRightP2[i]){
		            	bulletXRightP2[i] = bulletXRightP2[i] + bulletSpeedRightP2;
		            	bulletBoxRightP2 = new Rectangle(bulletXRightP2[i], bulletYRightP2[i], bulletWRightP2, bulletHRightP2);
				        if (bulletXRightP2[i]<0 || bulletXRightP2[i]>WIDTH || bulletBoxRightP2.intersects(player1Box)) {
				            bulletVisibleRightP2[i] = false;
				            if(bulletBoxRightP2.intersects(player1Box)) {
				            	bulletBoxRightP2 = new Rectangle(1920, bulletYRightP2[i], bulletWRightP2, bulletHRightP2);
				            	if (healthCounterP1 == 0 && healthLengthP1 > 18) {
				            		healthLengthP1 -= 18;
					            	healthCounterP1 = 1;
				            	}
				            	else if (healthCounterP1 == 1 && healthLengthP1 > 17){
				            		healthLengthP1 -= 17;
					            	healthCounterP1 = 0;
				            	}
				            	else {
				            		player1DeathCounter += 1;
				            		healthLengthP1 = 350;
                                    player1X = WIDTH/2-23;
                                    player1Y = PLATFORM3Y-player1H-100;
				            	}
				            }	            		
				        }
			        }
			    }
	            
	            //punch P1 left
	            for (int i=0; i<numPunch; i++){
		            if (punchVisibleLeftP1[i]){
		            	punchBoxLeftP1 = new Rectangle(punchXLeftP1[i], punchYLeftP1[i], punchWLeftP1, punchHLeftP1);
		            	if (punchTimeP1 >= 50 || bulletAttackP1 == 1) {
				            punchVisibleLeftP1[i] = false;
				            punchTimeP1 = 0;
				            punchCounterP1 = 0;
		            	}
				        if(punchBoxLeftP1.intersects(player2Box) && punchHitP1 == 0) {
				        	punchHitP1 = 1;
				            punchBoxLeftP1 = new Rectangle(1920, punchYLeftP1[i], punchWLeftP1, punchHLeftP1);
				            if (healthLengthP2 > 35) {
				            	healthLengthP2 -= 35;
					            healthXP2 += 35;
				            }
				            else {
				            	player2DeathCounter += 1;
				            	healthLengthP2 = 350;
				            	healthXP2 = 1520;
				            	player2X = WIDTH/2-23;
                                player2Y = PLATFORM3Y-player2H-100;
				            }
				        }            			            	
			        }
			    }
	            
	            //punch P1 right
	            for (int i=0; i<numPunch; i++){
		            if (punchVisibleRightP1[i]){
		            	punchBoxRightP1 = new Rectangle(punchXRightP1[i], punchYRightP1[i], punchWRightP1, punchHRightP1);
		            	if (punchTimeP1 >= 50 || bulletAttackP1 == 1) {
				            punchVisibleRightP1[i] = false;
				            punchTimeP1 = 0;
				            punchCounterP1 = 0;
		            	}
				        if(punchBoxRightP1.intersects(player2Box) && punchHitP1 == 0) {
				        	punchHitP1 = 1;
				            punchBoxRightP1 = new Rectangle(1920, punchYRightP1[i], punchWRightP1, punchHRightP1);
				            if (healthLengthP2 > 35) {
				            	healthLengthP2 -= 35;
					            healthXP2 += 35;
				            }
				            else {
				            	player2DeathCounter += 1;
				            	healthLengthP2 = 350;
				            	healthXP2 = 1520;
				            	player2X = WIDTH/2-23;
                                player2Y = PLATFORM3Y-player2H-100;
				            }
				        }            			            	
			        }
			    }
	            
	          //punch P2 left
	            for (int i=0; i<numPunch; i++){
		            if (punchVisibleLeftP2[i]){
		            	punchBoxLeftP2 = new Rectangle(punchXLeftP2[i], punchYLeftP2[i], punchWLeftP2, punchHLeftP2);
		            	if (punchTimeP2 >= 50 || bulletAttackP2 == 1) {
				            punchVisibleLeftP2[i] = false;
				            punchTimeP2 = 0;
				            punchCounterP2 = 0;
		            	}
				        if(punchBoxLeftP2.intersects(player1Box) && punchHitP2 == 0) {
				        	punchHitP2 = 1;
				            punchBoxLeftP2 = new Rectangle(1920, punchYLeftP2[i], punchWLeftP2, punchHLeftP2);
				            if (healthLengthP1 > 35) {
				            	healthLengthP1 -= 35;
				            }
				            else {
				            	player1DeathCounter += 1;
				            	healthLengthP1 = 350;
				            	player1X = WIDTH/2-23;
                                player1Y = PLATFORM3Y-player1H-100;
				            }
				        }            			            	
			        }
			    }
	            
	            //punch P2 right
	            for (int i=0; i<numPunch; i++){
		            if (punchVisibleRightP2[i]){
		            	punchBoxRightP2 = new Rectangle(punchXRightP2[i], punchYRightP2[i], punchWRightP2, punchHRightP2);
		            	if (punchTimeP2 >= 50 || bulletAttackP2 == 1) {
				            punchVisibleRightP2[i] = false;
				            punchTimeP2 = 0;
				            punchCounterP2 = 0;
		            	}
				        if(punchBoxRightP2.intersects(player1Box) && punchHitP2 == 0) {
				        	punchHitP2 = 1;
				            punchBoxRightP2 = new Rectangle(1920, punchYRightP2[i], punchWRightP2, punchHRightP2);
				            if (healthLengthP1 > 35) {
				            	healthLengthP1 -= 35;
				            }
				            else {
				            	player1DeathCounter += 1;
				            	healthLengthP1 = 350;
				            	player1X = WIDTH/2-23;
                                player1Y = PLATFORM3Y-player1H-100;
				            }
				        }            			            	
			        }
			    }
	            
	            //respawn
	            if (player1Y+player1H == HEIGHT){
	            	player1DeathCounter += 1;
                    player1X = WIDTH/2-23;
                    player1Y = PLATFORM3Y-player1H-100;
                }

                else if (player2Y+player2H == HEIGHT){
                	player2DeathCounter += 1;
                    player2X = WIDTH/2-23;
                    player2Y = PLATFORM3Y-player2H-100;
                }
	            
	            
	            	            	            
	        } //while loop
	        
	    } // runGameLoop method end
	    
	//------------------------------------------------------------------------------  
	    static class GraphicsPanel extends JPanel{
	        public GraphicsPanel(){
	            setFocusable(true);
	            requestFocusInWindow();
	        }
	        public void paintComponent(Graphics g){ 
	            super.paintComponent(g); //required
	            //------------------------------------------------------------------
	            // do all your drawings here 
	            //------------------------------------------------------------------
	            
	            //background
				g.drawImage(background, 0, 0, null);
				
				//text for name and controls
				g.setColor(Color.orange);

                g.setFont(new Font ("SansSerif", Font.BOLD,35));
                g.drawString("BIG BOI", 210, 850);
                g.drawString("SMALL BOI", 1510, 850);

                g.setColor(Color.black);
                g.setFont(new Font ("SansSerif", Font.BOLD,35));
                g.drawString("BIG BOI CONTROLS", 70, 100);
                g.drawString("SMALL BOI CONTROLS", 1500, 100);

                g.setFont(new Font ("SansSerif", Font.PLAIN,20));
                g.drawString("UP - W", 70, 130);
                g.drawString("RIGHT - A", 70, 155);
                g.drawString("LEFT - D", 70, 180);
                g.drawString("SHOOT - T", 70, 205);
                g.drawString("PUNCH - Y", 70, 230);

                g.drawString("UP - UP ARROW", 1500, 130);
                g.drawString("RIGHT - RIGHT ARROW", 1500, 155);
                g.drawString("LEFT - LEFT ARROW", 1500, 180);
                g.drawString("SHOOT - >", 1500, 205);
                g.drawString("PUNCH - <", 1500, 230);
                
	            //player 1				
				g.drawImage(therock,player1X,player1Y,player1W, player1H, canvas);
				
				//player 2
	            g.drawImage(kevinhart,player2X,player2Y,player2W, player2H, canvas);
	            
	            //profile above health the rock
	            if(player1DeathCounter == 0) {
	            	g.drawImage(therock, 40, 775, 150, 150, canvas);
	                g.drawImage(therock, 210, 865, 60, 60, canvas);
	                g.drawImage(therock, 290, 865, 60, 60, canvas);
	            }
	            else if(player1DeathCounter == 1) {
	            	g.drawImage(therock, 40, 775, 150, 150, canvas);
	                g.drawImage(therock, 210, 865, 60, 60, canvas);
	            }
	            else if (player1DeathCounter >= 2) {
	            	g.drawImage(therock, 40, 775, 150, 150, canvas);
	            }
                
                //profile above health kevin hart
	            if(player2DeathCounter == 0) {
		            g.drawImage(kevinhart, 1730, 775, 150, 150, canvas);
	                g.drawImage(kevinhart, 1650, 865, 60, 60, canvas);
	                g.drawImage(kevinhart, 1570, 865, 60, 60, canvas);
	            }
	            else if (player2DeathCounter == 1) {
		            g.drawImage(kevinhart, 1730, 775, 150, 150, canvas);
	                g.drawImage(kevinhart, 1650, 865, 60, 60, canvas);
	            }
	            else if (player2DeathCounter >= 2) {
		            g.drawImage(kevinhart, 1730, 775, 150, 150, canvas);
	            }

	            
	            //health bar  p1
	            g.setColor(Color.red);
                g.fillRect(50, 950, healthLengthP1, 20);
                
                //health bar p2
                g.fillRect(healthXP2, 950, healthLengthP2, 20);
                
              //left outline health box
                g.setColor(Color.black); 
                g.fillRect(40, 940, 370, 10);
                g.fillRect(40, 970, 370, 10);
                g.fillRect(40, 940, 10, 30);
                g.fillRect(400, 940, 10, 30);

                //right outline health box
                g.fillRect(1510, 940, 370, 10);
                g.fillRect(1510, 970, 370, 10);
                g.fillRect(1510, 940, 10, 30);
                g.fillRect(1870, 940, 10, 30);
	            
	         // draw the bullets P1 left	            
	            for (int i=0; i<numBullets; i++){
	                if (bulletVisibleLeftP1[i])
	                	g.drawImage(cannonball, bulletXLeftP1[i], bulletYLeftP1[i], bulletWLeftP1, bulletHLeftP1, canvas);  
	            }
	         // draw the bullets P1 right	
	            for (int i=0; i<numBullets; i++){
	                if (bulletVisibleRightP1[i])
	                	g.drawImage(cannonball, bulletXRightP1[i], bulletYRightP1[i], bulletWRightP1, bulletHRightP1, canvas);
	            }
	            
	         // draw the bullets P2 left
	            for (int i=0; i<numBullets; i++){
	                if (bulletVisibleLeftP2[i])
	                	g.drawImage(cannonball, bulletXLeftP2[i], bulletYLeftP2[i], bulletWLeftP2, bulletHLeftP2, canvas);
	            }
	         // draw the bullets P2 right	
	            for (int i=0; i<numBullets; i++){
	                if (bulletVisibleRightP2[i])
	                	g.drawImage(cannonball, bulletXRightP2[i], bulletYRightP2[i], bulletWRightP2, bulletHRightP2, canvas);
	            }
	            
	            //draw punch left P1
	            for (int i=0; i<numPunch; i++){
	                if (punchVisibleLeftP1[i])
	                	g.drawImage(punchLeft, punchXLeftP1[i], punchYLeftP1[i], punchWLeftP1, punchHLeftP1, canvas);  
	            }
	            
	            //draw punch right P1
	            for (int i=0; i<numPunch; i++){
	                if (punchVisibleRightP1[i])
	                	g.drawImage(punchRight, punchXRightP1[i], punchYRightP1[i], punchWRightP1, punchHRightP1, canvas);  
	            }
	            
	          //draw punch left P2
	            for (int i=0; i<numPunch; i++){
	                if (punchVisibleLeftP2[i])
	                	g.drawImage(punchLeft, punchXLeftP2[i], punchYLeftP2[i], punchWLeftP2, punchHLeftP2, canvas);  
	            }
	            
	            //draw punch right P2
	            for (int i=0; i<numPunch; i++){
	                if (punchVisibleRightP2[i])
	                	g.drawImage(punchRight, punchXRightP2[i], punchYRightP2[i], punchWRightP2, punchHRightP2, canvas);  
	            }
	            
	            //draw winner
	            g.setFont(new Font ("Dialogue", Font.BOLD,35));
	            if (player1DeathCounter == 3) {
	            	g.setColor(Color.red);
	            	g.drawString("S", P2WinX, 255);
	            	g.setColor(Color.orange);
	            	g.drawString("M", P2WinX+22, 255);
	            	g.setColor(Color.yellow);
	            	g.drawString("A", P2WinX+52, 255);
	            	g.setColor(Color.green);
	            	g.drawString("L", P2WinX+76, 255);
	            	g.setColor(Color.blue);
	            	g.drawString("L", P2WinX+97, 255);
	            	g.setColor(Color.magenta);
	            	g.drawString("B", P2WinX+132, 255);
	            	g.setColor(Color.red);
	            	g.drawString("O", P2WinX+156, 255);
	            	g.setColor(Color.orange);
	            	g.drawString("I", P2WinX+182, 255);
	            	g.setColor(Color.yellow);
	            	g.drawString("W", P2WinX+207, 255);
	            	g.setColor(Color.green);
	            	g.drawString("I", P2WinX+242, 255);
	            	g.setColor(Color.blue);
	            	g.drawString("N", P2WinX+251, 255);
	            	g.setColor(Color.magenta);
	            	g.drawString("S", P2WinX+275, 255);
	            }
	            else if (player2DeathCounter == 3){
	            	g.setColor(Color.red);
	            	g.drawString("B", P1WinX, 255);
	            	g.setColor(Color.orange);
	            	g.drawString("I", P1WinX+24, 255);
	            	g.setColor(Color.yellow);
	            	g.drawString("G ", P1WinX+33, 255);
	            	g.setColor(Color.green);
	            	g.drawString("B", P1WinX+71, 255);
	            	g.setColor(Color.blue);
	            	g.drawString("O", P1WinX+95, 255);
	            	g.setColor(Color.magenta);
	            	g.drawString("I", P1WinX+125, 255);
	            	g.setColor(Color.red);
	            	g.drawString("W", P1WinX+146, 255);
	            	g.setColor(Color.orange);
	            	g.drawString("I", P1WinX+181, 255);
	            	g.setColor(Color.yellow);
	            	g.drawString("N", P1WinX+190, 255);
	            	g.setColor(Color.green);
	            	g.drawString("S", P1WinX+214, 255);
	            }
	        } // paint method end
	    } // GraphicsPanel class end
	    
	//------------------------------------------------------------------------------     
	    static class MyKeyListener implements KeyListener{   
	        public void keyPressed(KeyEvent e){
	            int key = e.getKeyCode();
	            //control for arrow key player
	            if (key == KeyEvent.VK_A && player1DeathCounter < 3 && player2DeathCounter < 3){
	                player1Vx = -RUN_SPEED;
	                bulletDirectionP1 = 1;
	                punchDirectionP1 = 1;
	            } else if (key == KeyEvent.VK_D && player1DeathCounter < 3 && player2DeathCounter < 3){
	                player1Vx = RUN_SPEED;
	                bulletDirectionP1 = 0;
	                punchDirectionP1 = 0;
	            } else if (key == KeyEvent.VK_W && ((player1Y == GROUNDY-player1H) || (player1Y == PLATFORM1Y-player1H && ((player1X+player1W>PLATFORM1X && player1X<PLATFORM1X+PLATFORM1W) || (player1X+player1W>PLATFORM2X && player1X<PLATFORM2X+PLATFORM2W)))  || player1Y == PLATFORM3Y-player1H) && player1DeathCounter < 3 && player2DeathCounter < 3){
	            	player1Vy = JUMP_SPEED;
	                jumpsp1 += 1;
	            } else if (key == KeyEvent.VK_W && counterp1 ==1 && player1DeathCounter < 3 && player2DeathCounter < 3){
	            	player1Vy = JUMP_SPEED;
	            	counterp1 = 0;
	            	jumpsp1 += 2;
	            } else if (key == KeyEvent.VK_T && player1DeathCounter < 3 && player2DeathCounter < 3){
	            	bulletAttackP1 = 1;
	                // assign the coordinates of the middle point of the character to the current bullet
	                if (bulletDirectionP1 == 1) {
	                	bulletXLeftP1[currentBulletLeftP1] = player1X + player1W/2-bulletWLeftP1;
		                bulletYLeftP1[currentBulletLeftP1] = player1Y + player1H/2 -bulletWLeftP1/2;
		                bulletVisibleLeftP1[currentBulletLeftP1] = true;
		                currentBulletLeftP1 = (currentBulletLeftP1 + 1)%numBullets;
	                }
	                else if (bulletDirectionP1 == 0) {
	                	bulletXRightP1[currentBulletRightP1] = player1X + player1W/2;
		                bulletYRightP1[currentBulletRightP1] = player1Y + player1H/2 - bulletWRightP1/2; 
		                bulletVisibleRightP1[currentBulletRightP1] = true;
		                currentBulletRightP1 = (currentBulletRightP1 + 1)%numBullets;
	                }               	            	
	            } else if (key == KeyEvent.VK_Y && punchTimerP1 >= 225 && player1DeathCounter < 3 && player2DeathCounter < 3) {
	            	bulletAttackP1 = 0;
	            	punchHitP1 = 0;
	            	punchTimerP1 = 0;
	            	punchCounterP1 = 1;
	            	if (punchDirectionP1 == 1) {
	            		punchXLeftP1[currentPunchLeftP1] = player1X + player1W/2-punchWLeftP1;
	            		punchYLeftP1[currentPunchLeftP1] = player1Y + player1H/2 -35;
	            		punchVisibleLeftP1[currentPunchLeftP1] = true;
		                currentPunchLeftP1 = (currentPunchLeftP1 + 1)%numPunch;
	                }
	                else if (punchDirectionP1 == 0) {
	                	punchXRightP1[currentPunchRightP1] = player1X + player1W/2;
	                	punchYRightP1[currentPunchRightP1] = player1Y + player1H/2 - punchWRightP1/2; 
	                	punchVisibleRightP1[currentPunchRightP1] = true;
		                currentPunchRightP1 = (currentPunchRightP1 + 1)%numPunch;
	                }
	            }
	            //control for WASD player
	            
	            if (key == KeyEvent.VK_LEFT && player1DeathCounter < 3 && player2DeathCounter < 3){
	                player2Vx = -RUN_SPEED;
	                bulletDirectionP2 = 1;
	                punchDirectionP2 = 1;
	            } else if (key == KeyEvent.VK_RIGHT && player1DeathCounter < 3 && player2DeathCounter < 3){
	                player2Vx = RUN_SPEED;
	                bulletDirectionP2 = 0;
	                punchDirectionP2 = 0;
	            } else if (key == KeyEvent.VK_UP && ((player2Y == GROUNDY-player2H) || (player2Y == PLATFORM1Y-player2H && ((player2X+player2W>PLATFORM1X && player2X<PLATFORM1X+PLATFORM1W) || (player2X+player2W>PLATFORM2X && player2X<PLATFORM2X+PLATFORM2W)))  || player2Y == PLATFORM3Y-player2H) && player1DeathCounter < 3 && player2DeathCounter < 3){
	                player2Vy = JUMP_SPEED;
	                jumpsp2 += 1;
	            } else if (key == KeyEvent.VK_UP && counterp2 ==1 && player1DeathCounter < 3 && player2DeathCounter < 3){
	            	player2Vy = JUMP_SPEED;
	            	counterp2 = 0;
	            	jumpsp2 += 2;
	            } else if (key == KeyEvent.VK_PERIOD && player1DeathCounter < 3 && player2DeathCounter < 3){
	                // assign the coordinates of the middle point of the character to the current bullet
	            	if (bulletDirectionP2 == 1) {
	                	bulletXLeftP2[currentBulletLeftP2] = player2X + player2W/2-bulletWLeftP2;
		                bulletYLeftP2[currentBulletLeftP2] = player2Y + player2H/2 -bulletWLeftP2/2;
		                bulletVisibleLeftP2[currentBulletLeftP2] = true;
		                currentBulletLeftP2 = (currentBulletLeftP2 + 1)%numBullets;
	                }
	                else if (bulletDirectionP2 == 0) {
	                	bulletXRightP2[currentBulletRightP2] = player2X + player2W/2;
		                bulletYRightP2[currentBulletRightP2] = player2Y + player2H/2 -bulletWRightP2/2; 
		                bulletVisibleRightP2[currentBulletRightP2] = true;
		                currentBulletRightP2 = (currentBulletRightP2 + 1)%numBullets;
	                }
	            } else if (key == KeyEvent.VK_COMMA && punchTimerP2 >= 225 && player1DeathCounter < 3 && player2DeathCounter < 3) {
	            	bulletAttackP2 = 0;
	            	punchHitP2 = 0;
	            	punchTimerP2 = 0;
	            	punchCounterP2 = 1;
	            	if (punchDirectionP2 == 1) {
	            		punchXLeftP2[currentPunchLeftP2] = player2X + player2W/2-punchWLeftP2;
	            		punchYLeftP2[currentPunchLeftP2] = player2Y + player2H/2 -35;
	            		punchVisibleLeftP2[currentPunchLeftP2] = true;
		                currentPunchLeftP2 = (currentPunchLeftP2 + 1)%numPunch;
	                }
	                else if (punchDirectionP2 == 0) {
	                	punchXRightP2[currentPunchRightP2] = player2X + player2W/2;
	                	punchYRightP2[currentPunchRightP2] = player2Y + player2H/2 - punchWRightP2/2; 
	                	punchVisibleRightP2[currentPunchRightP2] = true;
		                currentPunchRightP2 = (currentPunchRightP2 + 1)%numPunch;
	                }
	            }
	        }
	            
	        public void keyReleased(KeyEvent e){ 
	            int key = e.getKeyCode();
	            if (key == KeyEvent.VK_A || key == KeyEvent.VK_D){
	               player1Vx = 0;
	             }   
	            else if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT){
	            	player2Vx = 0;
	            }
	        }   
	        public void keyTyped(KeyEvent e){
	            //char keyChar = e.getKeyChar();
	        }           
	    } // MyKeyListener class end   
	    
}	// class end


