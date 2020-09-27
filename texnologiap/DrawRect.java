import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.event.*; 
import javax.swing.Timer;

public class DrawRect extends JPanel{
	public static int total_fl=0;
	public static int start_flag=0;
	public static final int columns = 60;
	public static final int rows = 30;
	private static int RECT_X = 16;
	private static int RECT_Y = 16;
	private static final int RECT_WIDTH = 16;
	private static final int RECT_HEIGHT = RECT_WIDTH;
	private static int count=0;
	
	public static final int  landspeed_m = 5;//6000000/60;
	public static final int  landspeed_t = 3;//6000000/100;
	public static final int  landspeed_j = 4;//6000000/140;

	/*public static final int  flyspeed_m = 6000000/110;
	public static final int  flyspeed_t = 6000000/220;
	public static final int  flyspeed_j = 6000000/280;*/

	public static final int  max_speed_m = 110;
	public static final int  max_speed_t= 220;
	public static final int  max_speed_j= 280;

	public static final int  max_fuel_m = 280;
	public static final int  max_fuel_t= 4200;
	public static final int  max_fuel_j= 16000;

	public static final int  max_height_m = 8000;
	public static final int  max_height_t= 16000;
	public static final int  max_height_j= 28000;

	public static final int  rythmosanod_m = 700;
	public static final int  rythmosanod_t= 1200;
	public static final int  rythmosanod_j= 2300;

	public static final int  katanalwsh_m = 3;
	public static final int  katanalwsh_t= 9;
	public static final int  katanalwsh_j= 15;


	public int mapid=0;
	public int[][] map = new int[30][60];
	public int[][] airports = new int[11][7];
	public String[][] flights = new String[10][9];
	public static String[] flight_name = new String [23];
	public static int[][] key = new int[23][23];
	File f = null;
	Scanner scan = null;

	/**
	*Returns the number of valid flights that can be used later on a loop.
	*The mapid argument must be an integer that is provided either from a popup window,
	*either from inside of the code.
	*<p>
	*This method always reads the .txt files(world,airports,flights) and then parse them
	*into arrays(map[][],airports[][],flights[][]).
	*Finally, it does a great job calculating our key[][] array that consists of all the right 
	*values of valid flights.
	*
	*@param mapid an integer that translates in world_mapid.txt,airports_0.txt,flights_mapid.txt
	*@return total_fl the number of valid flights
	*/
	public int readthat(int mapid) {	
		int total_airp = 0; 
		try{
			f = new File("world_"+mapid+".txt");
			scan = new Scanner(f);
		}	
		catch(Exception e){
			System.exit(0);
		}	
		
		for(int i=0;i<30;i++){
			String s;
			s = scan.nextLine();
			for(int j=0;j<60;j++){
			String[] var = s.split(",");
			map[i][j] =  Integer.parseInt(var[j]);
			}
		}
		scan.close();

		try{
		f = new File("airports_"+mapid+".txt");
		scan = new Scanner(f);
		}	
		catch(Exception e){
			System.exit(0);
		}	
		while(scan.hasNextLine()){
			String s;
			s = scan.nextLine();
			for(int j=0;j<7;j++){
			String[] var = s.split(",");
			if(j==3) airports[total_airp][j]=0;
			else airports[total_airp][j] =  Integer.parseInt(var[j]);
			}
			total_airp++;
		}
		scan.close();
		try{
		f = new File("flights_"+mapid+".txt");
		scan = new Scanner(f);
		}	
		catch(Exception e){
			System.exit(0);
		}	
		
		
		while(scan.hasNextLine()){
			String s;
			s = scan.nextLine();
			for(int j=0;j<9;j++){
			String[] var = s.split(",");
			flights[total_fl][j] = var[j];
		}
		total_fl++;
	}
		scan.close();

	for(int i=0; i<total_fl; i++){
		for(int y=0;y<total_airp;y++){
			//////////////////////////elegxos anoiktwn/kleistwn aerodromiwn
			if ((Integer.parseInt(flights[i][2])==airports[y][0] )||(Integer.parseInt(flights[i][3])==airports[y][0])){
				if (airports[y][6]==0) flights[i][0]="-1";
			}
			/////////////elegxos an to aerodromio apogeiwshs h prosgeiwshs yposthrizei ton typo aeroplanou ths pthshs
			if (((Integer.parseInt(flights[i][5]))==1)&&((Integer.parseInt(flights[i][2])==airports[y][0])||(Integer.parseInt(flights[i][3])==airports[y][0]))){
				if (airports[y][5]==2) flights[i][0]="-1";
			}
			if (((Integer.parseInt(flights[i][5]))==2)&&((Integer.parseInt(flights[i][2])==airports[y][0])||(Integer.parseInt(flights[i][3])==airports[y][0]))){
				if (airports[y][5]==1) flights[i][0]="-1";
			}
			if (((Integer.parseInt(flights[i][5]))==3)&&((Integer.parseInt(flights[i][2])==airports[y][0])||(Integer.parseInt(flights[i][3])==airports[y][0]))){
				if (airports[y][5]==1) flights[i][0]="-1";
			}


		}
		////////elegxos taxyththas
		if ((Integer.parseInt(flights[i][5]))==1){
				if (Integer.parseInt(flights[i][6])>max_speed_m) flights[i][0]="-1";
			}
		if ((Integer.parseInt(flights[i][5]))==2){
				if (Integer.parseInt(flights[i][6])>max_speed_t) flights[i][0]="-1";
			}
		if ((Integer.parseInt(flights[i][5]))==3){
				if (Integer.parseInt(flights[i][6])>max_speed_j) flights[i][0]="-1";
			}
			////////////////////////////////////////////////// elegxos ypsous
		if ((Integer.parseInt(flights[i][5]))==1){
				if (Integer.parseInt(flights[i][7])>max_height_m) flights[i][0]="-1";
			}
		if ((Integer.parseInt(flights[i][5]))==2){
				if (Integer.parseInt(flights[i][7])>max_height_t) flights[i][0]="-1";
			}
		if ((Integer.parseInt(flights[i][5]))==3){
				if (Integer.parseInt(flights[i][7])>max_height_j) flights[i][0]="-1";
			}
	//System.out.print(flights[i][0]);

	}
	int counter=-1;
	for (int i=0; i<total_fl; i++){
		if (flights[i][0]!="-1") {
			counter++;
			flight_name[counter] = flights[i][4];
			System.out.println("The flight '" + flights[i][4] + "' will take off.");
		}
		else{
			System.out.println("The flight '" + flights[i][4] + "' will be canceled.");
		}
		for (int y=0; y<total_airp; y++){
			if (flights[i][0]!= "-1"){
				if (Integer.parseInt(flights[i][2])==airports[y][0]){
					key[counter][0]= airports[y][1];  // Y apogeiwshs
					key[counter][1]= airports[y][2];  // X apogeiwshs
					key[counter][2]=airports[y][4];  // prosanatolismos apogeiwshs
				}
				if (Integer.parseInt(flights[i][3])==airports[y][0]){
					key[counter][3]= airports[y][1];  // Y prosgeiwshs
					key[counter][4]= airports[y][2];  // X prosgeiwshs
					if (airports[y][4]==1) {  // panw apo to aerodromio (X, Y-1)
						key[counter][12]=airports[y][1]-1;  // Y-1
						key[counter][13]=airports[y][2];  // X
					}
					if (airports[y][4]==2) {  // deksia apo to aerodromio (X+1, Y)
						key[counter][12]=airports[y][1];
						key[counter][13]=airports[y][2]+1;
					}
					if (airports[y][4]==3) {  // katw apo to aerodromio  (X, Y+1)
						key[counter][12]=airports[y][1]+1;
						key[counter][13]=airports[y][2];
					}
					if (airports[y][4]==4) {  // aristera apo to aerodromio  (X-1, Y)
						key[counter][12]=airports[y][1];
						key[counter][13]=airports[y][2]-1;
					}
					//System.out.println("Y= " + key[counter][12] +" kai X= " + key[counter][13]);
					key[counter][5]= airports[y][4];  // prosanatolismos prosgeiwshs
				}
				key[counter][6]=5*Integer.parseInt(flights[i][1]);  // Xronos ekkinhshs pthshs
				key[counter][7]=Integer.parseInt(flights[i][6]);	//  Taxuthta pthshs
				key[counter][8]=Integer.parseInt(flights[i][5]);  // Tupos aeroplanou (gia eikona)
				key[counter][9]= Integer.parseInt(flights[i][8]);  // Kausima aeroplanou
				
				// Taxuthta prosgeiwshsh & apogeiwshs sto key[10] kai katanalwsh aeropolanou sto key[11]
				if (Integer.parseInt(flights[i][5])==1) {
					key[counter][10]=landspeed_m;
					key[counter][11]=katanalwsh_m;
				}
				if (Integer.parseInt(flights[i][5])==2) {
					key[counter][10]=landspeed_t;
					key[counter][11]=katanalwsh_t;
				}
				if (Integer.parseInt(flights[i][5])==3) {
					key[counter][10]=landspeed_j;
					key[counter][11]=katanalwsh_j;
				}
				//Trexousa 8esh aeroplanou Y=key[14] kai X=key[15]
				key[counter][14]=key[counter][0];
				key[counter][15]=key[counter][1];

				// Taxuthta aeroplanou ana koutaki / refresh rate (speed) / TIMER COUNTER LIMIT= key[16]
				key[counter][16]=/*6000000/*/key[counter][7]; //!!!!!!!!!!!!!!!!!!!
				key[counter][17]=0;  // key[17] = kinisewn_counter
				//key[18]= Direction aeroplanou
				//key[19]=finish (an teleiwse ton orizontia metakinish tou to aeroplano)-->Etoimo gia katakorufh metakinhsh
				key[counter][20]=total_fl;
				
				key[counter][22]=key[counter][10];  //key[22]=Trexon taxuthta aeroplanou (key[10] h' key[16])
				key[counter][21]=key[counter][22];  //key[21]=timer counter
				//key[counter][23]=1;
			}
			
		}
		
	}	
	//System.out.println(total_fl);
	//System.out.println(counter);
		key[0][20]=counter+1;
		/*
		System.out.println(key[0][20]);
		for(int i=0; i<23;i++){
			System.out.println(key[0][i]);
		}	
		for(int i=0; i<23;i++){
			System.out.println(key[1][i]);
		}	*/
		return total_fl;
	}

	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// draw the rectangle here

		//fortwnw eikona
		BufferedImage img = null;
		BufferedImage airplane_m_up = null;
		BufferedImage airplane_m_right = null;
		BufferedImage airplane_m_down = null;
		BufferedImage airplane_m_left = null;

		BufferedImage airplane_t_up = null;
		BufferedImage airplane_t_right = null;
		BufferedImage airplane_t_down = null;
		BufferedImage airplane_t_left = null;

		BufferedImage airplane_j_up = null;
		BufferedImage airplane_j_right = null;
		BufferedImage airplane_j_down = null;
		BufferedImage airplane_j_left = null;

		BufferedImage airplane_crashed = null;
		try {
		img = ImageIO.read(new File("SimulationIcons/airport.png"));

		airplane_m_right = ImageIO.read(new File("SimulationIcons/small_e.png"));
		airplane_m_up = ImageIO.read(new File("SimulationIcons/small_n.png"));
		airplane_m_down = ImageIO.read(new File("SimulationIcons/small_s.png"));
		airplane_m_left = ImageIO.read(new File("SimulationIcons/small_w.png"));

		airplane_t_right = ImageIO.read(new File("SimulationIcons/middle_e.png"));
		airplane_t_up = ImageIO.read(new File("SimulationIcons/middle_n.png"));
		airplane_t_down = ImageIO.read(new File("SimulationIcons/middle_s.png"));
		airplane_t_left = ImageIO.read(new File("SimulationIcons/middle_w.png"));

		airplane_j_right = ImageIO.read(new File("SimulationIcons/big_e.png"));
		airplane_j_up = ImageIO.read(new File("SimulationIcons/big_n.png"));
		airplane_j_down = ImageIO.read(new File("SimulationIcons/big_s.png"));
		airplane_j_left = ImageIO.read(new File("SimulationIcons/big_w.png"));

		airplane_crashed = ImageIO.read(new File("SimulationIcons/rsz_crash.png"));
		} catch (IOException e) {
		}
		//
		Color myColor1 =new Color(0,0,255);
		Color myColor2 =new Color(60,179,113);    
		Color myColor3 =new Color(46,139,87);
		Color myColor4 =new Color(34,139,34);
		Color myColor5 =new Color(222,184,135);
		Color myColor6 =new Color(205,133,63);
		Color myColor7 =new Color(145,80,20);
	  
	  
		for (int i = 0; i < rows; i++) {
			for (int y = 0; y < columns; y++) {
				if(map[i][y]==0){ 
					//System.out.println(map[i][y]);
					g.setColor(myColor1);
				}
				else if((map[i][y]>0)&&(map[i][y]<=200)){
					g.setColor(myColor2);
				}
				else if((map[i][y]>200)&&(map[i][y]<=400)){
					g.setColor(myColor3);
				}
				else if((map[i][y]>400)&&(map[i][y]<=700)){
					g.setColor(myColor4);
				}
				else if((map[i][y]>700)&&(map[i][y]<=1500)){
					g.setColor(myColor5);
				}
				else if((map[i][y]>1500)&&(map[i][y]<=3500)){
					g.setColor(myColor6);
				}
				else if(map[i][y]>3500) g.setColor(myColor7);

				g.fillRect(16*y, 16*i, RECT_WIDTH, RECT_HEIGHT);
			}
		}
		//emfanish aerodromiwn
		for(int i=0;i<11;i++){
			g.drawImage(img,RECT_X*airports[i][2],RECT_Y*airports[i][1],null);		
		}
		/////
		//aeroplano

		for(int k=0;k<key[0][20];k++){
			if(start_flag==1) {  // 1 --> right, 2 --> left, 3 --> down, 4 --> up, 5 --> crashed
				if(key[k][8]==1){
					if(key[k][18]==1) g.drawImage(airplane_m_right,key[k][15]*16-8,key[k][14]*16-8,null);
					if(key[k][18]==2) g.drawImage(airplane_m_left,key[k][15]*16-8,key[k][14]*16-8,null);
					if(key[k][18]==3) g.drawImage(airplane_m_down,key[k][15]*16-8,key[k][14]*16-8,null);
					if(key[k][18]==4) g.drawImage(airplane_m_up,key[k][15]*16-8,key[k][14]*16-8,null);
					if(key[k][18]==5) g.drawImage(airplane_crashed,key[k][15]*16-8,key[k][14]*16-8,null);
				}
				else if(key[k][8]==2){
					if(key[k][18]==1) g.drawImage(airplane_t_right,key[k][15]*16-8,key[k][14]*16-8,null);
					if(key[k][18]==2) g.drawImage(airplane_t_left,key[k][15]*16-8,key[k][14]*16-8,null);
					if(key[k][18]==3) g.drawImage(airplane_t_down,key[k][15]*16-8,key[k][14]*16-8,null);
					if(key[k][18]==4) g.drawImage(airplane_t_up,key[k][15]*16-8,key[k][14]*16-8,null);
					if(key[k][18]==5) g.drawImage(airplane_crashed,key[k][15]*16-8,key[k][14]*16-8,null);
				}
				else if (key[k][8]==3){
					if(key[k][18]==1) g.drawImage(airplane_j_right,key[k][15]*16-8,key[k][14]*16-8,null);
					if(key[k][18]==2) g.drawImage(airplane_j_left,key[k][15]*16-8,key[k][14]*16-8,null);
					if(key[k][18]==3) g.drawImage(airplane_j_down,key[k][15]*16-8,key[k][14]*16-8,null);
					if(key[k][18]==4) g.drawImage(airplane_j_up,key[k][15]*16-8,key[k][14]*16-8,null);
					if(key[k][18]==5) g.drawImage(airplane_crashed,key[k][15]*16-8,key[k][14]*16-8,null);
				}
			}
		
		}
	}
	/**
	*Returns the new dimension that can be used for our window frame.
	*<p>
	* Creates the preferred sized window giving new Dimensions for frame. 
	*of our JFrame window in pixels (int x, int y).
	*
	*@return the new Dimension in pixels
	*/
   	@Override
   	public Dimension getPreferredSize() {
   		return new Dimension(16*60, 16*30);
   	}



   	
   	// create the GUI explicitly on the Swing event thread
   	private static void createAndShowGui() {
			
		DrawRect mainPanel = new DrawRect();
		mainPanel.readthat(0);
	
		JFrame frame = new JFrame("MediaLab Flight Simulator");
		JLabel textLabel = new JLabel("Simulated Time:0 secs                                              Total Aircrafts:0                                                     Collisions:0                         Landings:0",SwingConstants.LEFT);
		JLabel textLabel3 = new JLabel("<html>Load World...<br/>Load Airports...</html>",SwingConstants.LEFT);
		

		JMenuItem start,stop,load,exit,airports,aircrafts,flights,about;

		JMenuBar mb = new JMenuBar();
	        	JMenu menu1 = new JMenu("Game");
	        	mb.add(menu1);
	        	JMenu menu2 = new JMenu("Simulation");
	        	mb.add(menu2);
	        	JMenu menu3 = new JMenu("Help");
	        	mb.add(menu3);
	           
         		
	        	Timer timer = new Timer(1000, new ActionListener() {
	        	/**
	        	*This method does not return anything and its void.
	        	*The method simulates every movement that the
	        	*happens in the airplanes using the key[][] array. 
	        	*<p>
	        	*Actually the ActionEvent is waiting the Timer to go off and then
	        	*it does every move that is inside the actionPerformed method.
	        	*@param ActionEvent e an event that waits to happen
	        	*/	
                	@Override
                	public void actionPerformed(ActionEvent e) {
                		for(int i=0; i<key[0][20]; i++){
                			if(count>=key[i][6]){ 
                				
                				System.out.println(key[i][21]);
                				System.out.println(key[i][22]);
                				if(key[i][21]==key[i][22]){  // timer counter == limit
                					key[i][21]=0;

		                			if (key[i][17]==0){
		                				
		                				textLabel3.setText("The flight  '" + flight_name[i] + "' has started!");
		                				key[i][15] = key[i][1];
						         		key[i][14] = key[i][0];
						         		if (key[i][2]==1) key[i][18]=4;  // 1 --> right, 2 --> left, 3 --> down, 4 --> up
						         		else if (key[i][2]==2) key[i][18]=1;
						         		else if (key[i][2]==3) key[i][18]=3;
						         		else if (key[i][2]==4) key[i][18]=2;
						         		
						         		key[i][17]++;
						         		key[i][22]=key[i][10]; // 8etoume limit xronou thn taxythta apogeiwshs
		                			}
		                			else if(key[i][17]==1){
		                				
						         		if (key[i][18]==1) key[i][15] ++;// 1 --> right, 2 --> left, 3 --> down, 4 --> up
						         		else if (key[i][18]==2) key[i][15]--;
						         		else if (key[i][18]==3) key[i][14]++;
						         		else if (key[i][18]==4) key[i][14]--;
						         		//key[i][9]=key[i][9]-key[i][11]*20;  // meiwsh kausimwn aeroplanou kata thn apogeiwsh
						         		key[i][17]++;
						         		key[i][22]=key[i][16];
		                			}


		                			else if(key[i][17]==2){
				                		if (key[i][9]>0 && !(key[i][15]==key[i][13] && key[i][14]==key[i][12])) {
				                			
				                				//key[i][9]=key[i][9]-key[i][11]*20;  // meiwsh kausimwn aeroplanou
				                				
						                		if(key[i][15]<key[i][13]) {
						                			key[i][15]++;
						                			key[i][18]=1;  // direction 1 --> right (deksia)
						                		}	
						                		if(key[i][15]>key[i][13]) {
						                			key[i][15]--;
						                			key[i][18]=2;  // direction 2 --> left (aristera)
						                		}

						                		if(key[i][14]<key[i][12] && key[i][19]==1) {
						                			key[i][14]++;
						                			key[i][18]=3;  // direction 3 --> down (katw)
						                		}	
						                		if(key[i][14]>key[i][12] && key[i][19]==1) {
						                			key[i][14]--;
						                			key[i][18]=4;  // direction 4 --> up (panw)
						                		}

						                		if (key[i][15]==key[i][13]) key[i][19]=1;
						              		                	
						                		if (key[i][15]==key[i][13] && key[i][14]==key[i][12]){
						                		key[i][17]++;
						                		}
						                }	
					                }
				                	else if (key[i][17]==3){
		                				key[i][15] = key[i][13];
						         		key[i][14] = key[i][12];
						         		if (key[i][5]==1) key[i][18]=3;  // 1 --> right, 2 --> left, 3 --> down, 4 --> up
						         		else if (key[i][5]==2) key[i][18]=2;
						         		else if (key[i][5]==3) key[i][18]=4;
						         		else if (key[i][5]==4) key[i][18]=1;
						         		key[i][17]++;
						         		key[i][22]=key[i][10];
		                			}
		                			else if(key[i][17]==4){
		                				
						         		if (key[i][18]==1) key[i][15] ++;// 1 --> right, 2 --> left, 3 --> down, 4 --> up
						         		else if (key[i][18]==2) key[i][15]--;
						         		else if (key[i][18]==3) key[i][14]++;
						         		else if (key[i][18]==4) key[i][14]--;
						         		key[i][17]++;
						         		textLabel3.setText("The flight  '" + flight_name[i] + "' has arrived!");
		                			}
		                			if (key[i][9]<=0) {
					                			textLabel3.setText("Flight  '" + flight_name[i] + "' (plane type " + key[i][8] + ") left out of fuel.");
					                			key[i][18]=5;  // direction 5 --> crashed
					                			key[i][17]=6;
					                }	
                				}
                				key[i][21]++;
                			}
                		} 

                		
                		//System.out.println(kinisewn_counter);
                		frame.repaint();                    		
                	}
            		});


	        	Timer timer2 = new Timer(1000, new ActionListener() {
	        	/**
		*This method starts a timer that acts as a global simulation timer.
		*<p>
		*Additionaly, it appears selected messages on the textLabel3 part of screen,
		*and the time count every 1000ms on textLabel.
		*<p>
		*In the end repaints the frame
		*
		*@param evt waits untill timer2 is started.
		*/
                	@Override
                	public void actionPerformed(ActionEvent e) {
                		String result = "Simulated Time: "+(Integer.toString(count+1))+" secs                                              Total Aircrafts:0                                                     Collisions:0                         Landings:0";
                		count++;
                		textLabel.setText(result);	
                		//System.out.println(count);
                		frame.repaint();                    		
                	}
            		});



        		///epiloges Game
	        	start = menu1.add("Start");
	        	start.addActionListener(new java.awt.event.ActionListener() {
	        	/**
		*This method starts the simulation and starts every timer for our airplanes.
		*<p>
		*Additionaly, it appears selected messages on the textLabel3 part of screen
		*
		*@param evt waits the event of selecting and clicking the "Start" choice.
		*/
	        	@Override
             	public void actionPerformed(ActionEvent evt) {
	                	textLabel3.setText("Simulation has been started");
	                	timer2.start();
	                	timer.start();
	                	start_flag=1;
             	}
        		});

	    	stop = menu1.add("Stop");
	    	stop.addActionListener(new java.awt.event.ActionListener() {
	    	/**
		*This method stops the simulation and cancels every timer that is currently running.
		*<p>
		*Additionaly, it appears selected messages on the textLabel3 part of screen, returns 
		*planes in the starting airplane positions and repaints the frame so we can see the 
		*correct frame
		*
		*@param evt waits the event of selecting and clicking the "Stop" choice.
		*/
	        	@Override
             	public void actionPerformed(ActionEvent evt) {
             		for(int j=0;j<total_fl;j++){
	                		key[j][15]=key[j][1];
	                		key[j][14]=key[j][0];
	               	}
	                	timer2.stop();
	                	timer.stop();
	                	count=0;
	               	String result = "Simulated Time:"+(Integer.toString(count++))+"                                               Total Aircrafts:0                                                     Collisions:0                         Landings:0";
	                	textLabel.setText(result);
	                	textLabel3.setText("Simulation has been stopped");	
	                	frame.repaint();
             	}
        		});

	    	load = menu1.add("Load");
	    	load.addActionListener(new java.awt.event.ActionListener() {
	    	/**
		*The method loads new mapid files and stops all the timers
		*<p>
		*Additionaly, it appears selected messages on the textLabel3 part of screen
		*
		*@param arg0 waits the event of selecting and clicking the "Load" choice.
		*/
             	@Override
             	public void actionPerformed(ActionEvent evt) {
	                	int mapid = Integer.parseInt(JOptionPane.showInputDialog("Give The MAPID", null));	      
	                	mainPanel.readthat(mapid);
	                	timer2.stop();
	                	timer.stop();
	                	
	                	count=0;
	       		String result = "Simulated Time:"+(Integer.toString(count++))+"                                               Total Aircrafts:0                                                     Collisions:0                         Landings:0";
	                	textLabel.setText(result);
	                	textLabel3.setText("Map: world_"+mapid+" has been loaded");
	                	for(int j=0;j<total_fl;j++){
	                		key[j][15]=key[j][1];
	                		key[j][14]=key[j][0];
	               	}	
	                	frame.repaint();
             	}
        		});


	    	exit = menu1.add("Exit");
	    	exit.addActionListener(new ActionListener() {
	    	/**
		*The method exits and kills the process
		*
		*@param arg0 waits the event of selecting and clicking the "Exit" choice
		*/
    		@Override
    		public void actionPerformed(ActionEvent arg0) {
        			System.exit(0);
   	 	}
		});

	    	airports = menu2.add("Airports");
	    	aircrafts = menu2.add("Aircrafts");
	    	flights = menu2.add("Flights");

	    	about = menu3.add("About Us");
		about.addActionListener(new ActionListener() {
		/**
		*The method creates a messageDialog that 
		*appears when pressing Help -> About Us menu.
		*
		*@param arg0 waits the event of selecting and clicking the "About Us" choice
		*/
    		@Override
    		public void actionPerformed(ActionEvent arg0) {
        	 	JOptionPane.showMessageDialog(null, "<html><center>MediaLab Flight Simulator<br/>Τεχνολογία Πολυμέσων<br/><br/>&copy 2018</center></html>", "About Us", JOptionPane.INFORMATION_MESSAGE);
   	 	}
		});



	        	frame.setJMenuBar(mb);  
		
		textLabel.setPreferredSize(new Dimension(10, 10));
		textLabel.setBackground(Color.lightGray);
		textLabel.setOpaque(true);

		textLabel3.setPreferredSize(new Dimension(250, 50));
		textLabel3.setBackground(Color.white);
		textLabel3.setOpaque(true);
		textLabel3.setVerticalAlignment(SwingConstants.TOP);
		

		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(textLabel, BorderLayout.NORTH);
		frame.getContentPane().add(mainPanel,BorderLayout.CENTER);
		frame.getContentPane().add(textLabel3, BorderLayout.EAST);


		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		frame.pack();
		frame.setLocationByPlatform(true);

		ImageIcon img_app = new ImageIcon("SimulationIcons/middle_s.png");

		frame.setIconImage(img_app.getImage());

		////emfanish sto kentro ths othonhs///////////
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);

		frame.setVisible(true);
		}
	/**
	*The main function its void and does not return anything
	*the main functionality is the inside run() method that creates
	*a thread that runs createAndShowGui.
	*
	*@param args arguments that can be passed from command line
	*/
	public static void main(String[] args) {
	    	SwingUtilities.invokeLater(new Runnable() {
	    	/**
	    	*The run method utilizes the Swingutilities.invokeLater
	    	*that runs as a thread the createAndShowGui() method
	    	*
	    	*@see We see the Gui painted on frame. 
	    	*/
	         	public void run() {
	            		createAndShowGui();
	         	}
	      	});
	}
	}