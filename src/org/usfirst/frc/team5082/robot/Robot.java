package org.usfirst.frc.team5082.robot;


//import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
//import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
____________  _____   _____                      _____  _____ _____  _____  
|  ___| ___ \/  __ \ |_   _|                    |  ___||  _  |  _  |/ __  \ 
| |_  | |_/ /| /  \/   | | ___  __ _ _ __ ___   |___ \ | |/' |\ V / `' / /' 
|  _| |    / | |       | |/ _ \/ _` | '_ ` _ \      \ \|  /| |/ _ \   / /   
| |   | |\ \ | \__/\   | |  __/ (_| | | | | | | /\__/ /\ |_/ / |_| |./ /___ 
\_|   \_| \_| \____/   \_/\___|\__,_|_| |_| |_| \____/  \___/\_____/\_____/ 
 _____  _____  __   _____             _           _   _                                                      
/ __  \|  _  |/  | |  ___|           | |         | | (_)                                                     
`' / /'| |/' |`| | |___ \   _ __ ___ | |__   ___ | |_ _  ___ ___   _ __  _ __ ___   __ _ _ __ __ _ _ __ ___  
  / /  |  /| | | |     \ \ | '__/ _ \| '_ \ / _ \| __| |/ __/ __| | '_ \| '__/ _ \ / _` | '__/ _` | '_ ` _ \ 
./ /___\ |_/ /_| |_/\__/ / | | | (_) | |_) | (_) | |_| | (__\__ \ | |_) | | | (_) | (_| | | | (_| | | | | | |
\_____/ \___/ \___/\____/  |_|  \___/|_.__/ \___/ \__|_|\___|___/ | .__/|_|  \___/ \__, |_|  \__,_|_| |_| |_|
                                                                  | |               __/ |                    
                                                                  |_|              |___/                                                                                       
   ___                             ____   ___ __  _         
  / _ )___  ___  ___ ____ ____    / __/__/ (_) /_(_)__  ___ 
 / _  / _ \/ _ \/ _ `/ _ `/ _ \  / _// _  / / __/ / _ \/ _ \
/____/\___/\___/\_, /\_,_/_//_/ /___/\_,_/_/\__/_/\___/_//_/
               /___/                                                                                                          
Programmed entirely by: Pimp Thomas 
Other grunt work done by: Zack, Logan, Max Gruschka , and Tanner
Suck it LabView
 */
public class Robot extends IterativeRobot {
	Talon garage; //defines the garage motor using a talon motor controller
	RobotDrive chasisMotors; //defines the motors in the chassis 
	Joystick stick; //defines the left stick
	//Joystick stick2; //defines the right stick
	int timerCounter; //creates a makeshift timer
	DoubleSolenoid armSolenoid;
//	Servo horizontalCameraServo;
//	Servo verticalCameraServo;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	garage = new Talon(4);
    	chasisMotors = new RobotDrive(2,3,0,1); //defines what PWM port the motors are in
    	armSolenoid = new DoubleSolenoid(0,1); //this uses port 0 on the pneumatic controller instead of directly on the RIO itself
    	stick = new Joystick(0); //defines the left joyStick port
    //	stick2 = new Joystick(1); //defines the right joyStick port
    //	horizontalCameraServo = new Servo(7);
    //	verticalCameraServo = new Servo(6);
//    	CameraServer server = CameraServer.getInstance();
  //  	server.setQuality(50);  
    //	server.startAutomaticCapture("cam0");
    }
    
    /**
     * This function is run once each time the robot enters autonomous mode
     */
    public void autonomousInit() {
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	
       for (timerCounter = 0; timerCounter < 1500; timerCounter++) //sets the limits for the timer
		{
			if (timerCounter < 200)
			{
				//chasisMotors.arcadeDrive(-0.65, 0.0); //forward and reset
				garage.set(-1);
			}
			else if (timerCounter <250 )
			{
				armSolenoid.set(DoubleSolenoid.Value.kReverse);
			}
			else if (timerCounter < 305) //lift up
			{
				armSolenoid.set(DoubleSolenoid.Value.kReverse);
				garage.set(1);
			}
			else if (timerCounter < 350) //turn right
				
				chasisMotors.arcadeDrive(0.0, -0.655);
				
			else if (timerCounter < 500) //forward (gotta go fast)
			{
				chasisMotors.arcadeDrive(-0.85, 0.0);
				garage.set(0);
			}
			else if (timerCounter < 650) //lower arm				
				
				garage.set(-1);
			
			else if (timerCounter < 700) //open up and release tote	
				
				armSolenoid.set(DoubleSolenoid.Value.kForward);
			
			else if (timerCounter < 750) //back up and finish
				
				chasisMotors.arcadeDrive(0.5, 0.0);
				
			else 
				
				chasisMotors.arcadeDrive(0.0, 0.0);
			 	//garage.set(0);
			 	//armSolenoid.set(DoubleSolenoid.Value.kReverse);
			
			Timer.delay(0.01);
		}
    }
    
    /**
     * This function is called once each time the robot enters tele-operated mode
     */
    public void teleopInit(){

    }
    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {   	
//        chasisMotors.tankDrive(stick, stick2);
        //chasisMotors.arcadeDrive(rotation*0.75, speed]*0.75);
    	if (stick.getRawButton(2)) {
        chasisMotors.arcadeDrive(stick);
    	 }
    	 else {
    			double rotation = stick.getY();
    			double speed = stick.getX();
    			chasisMotors.arcadeDrive(rotation*0.75, speed*0.75);
    	 }
        if (stick.getRawButton(7)) {
        	garage.set(1); //up
        }
        else if (stick.getButton(Joystick.ButtonType.kTrigger)) {
        	garage.set(-1); //down
        }
        else {
        	garage.set(0 );
        }
        
        if (stick.getRawButton(4)) { //refers to buttons numbered on top of the joyStick.
        	armSolenoid.set(DoubleSolenoid.Value.kReverse);; //inwards
        }
        else if (stick.getRawButton(5)) {
        	armSolenoid.set(DoubleSolenoid.Value.kForward);; //outwards
        }
        else{
        	armSolenoid.set(DoubleSolenoid.Value.kOff);
        }
    }     
/*
        if (stick.getRawButton(6)) {
        	horizontalCameraServo.set(0.25);
        }
        else if (stick.getRawButton(11)) {
        	horizontalCameraServo.set(0);
        }
        else {
        		horizontalCameraServo.set(0.5);
        	}
        
        if (stick.getRawButton(9)) {
        	verticalCameraServo.set(1.0);
        }
        else if (stick.getRawButton(8)) {
        	verticalCameraServo.set(0.7);
        	}
        else {
    		verticalCameraServo.set(0.85);
    	}
    }
 **/       
        
    
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    	LiveWindow.run();
    }
    
}