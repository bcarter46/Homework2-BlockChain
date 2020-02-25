import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class cracker {
	public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
		
	
	String pathname="/Users/benba/Desktop/10-million-password-list-top-1000000.txt";
	File Pass= new File(pathname);
	BufferedReader br = new BufferedReader(new FileReader(Pass));
	BufferedReader br1 = new BufferedReader(new FileReader(Pass));
	BufferedReader br2 = new BufferedReader(new FileReader(Pass));
	long StartTime=System.currentTimeMillis();
	String input1="b7a875fc1ea228b9061041b7cec4bd3c52ab3ce3";
	String input2="801cdea58224c921c21fd2b183ff28ffa910ce31";
	String input3="ece4bb07f2580ed8b39aa52b7f7f918e43033ea1";
	String input4="34302959e138917ce9339c0b30ec50e650ce6b40";
	String salt= "f0744d60dd500c92c0d37c16174cc58d3c4bdd8e";
	String currentGuess;
	int count=0;
	int i=0;
	boolean visited1=false;
	boolean visited2=false;
	boolean visited3=false;
	while((currentGuess=br.readLine()) != null) {
		count++;
		String saltedGuess= currentGuess+salt;
		MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] messageDigest = md.digest(currentGuess.getBytes()); 
        BigInteger no = new BigInteger(1, messageDigest); 
        String hashtext = no.toString(16); 
        while (hashtext.length() < 32) { 
            hashtext = "0" + hashtext; 
        } 
       
        if (hashtext.equals(input1)&&visited1==false) {
        	visited1=true;
        	System.out.println("Input 1:");
        	System.out.println("The plaintext of input 1 is: "+currentGuess);
        	System.out.println("The time elapsed is "+(-1*(StartTime-System.currentTimeMillis())));
        	System.out.println("Number of tries: "+count);
        	System.out.println("--------------------------");
        }
        if(hashtext.equals(input2)&&visited2==false) {
        	visited2=true;
        	System.out.println("Input 2:");
        	System.out.println("The plaintext of input 2 is: "+currentGuess);
        	System.out.println("The time elapsed is "+(-1*(StartTime-System.currentTimeMillis())));
        	System.out.println("Number of tries: "+count);
        	System.out.println("--------------------------");
        } 
        if (hashtext.equals(salt)&& visited3==false) {
        	visited3=true;
        	String plainSalt=currentGuess;
        	System.out.println("Input 3:");
        	System.out.println("The plaintext of the salt is: "+ plainSalt);
        	int counts=0;
        	String guess;
        	while((guess=br1.readLine()) != null) {
        		counts++;
        		String saltedGuess1= plainSalt+guess;
        		MessageDigest md1 = MessageDigest.getInstance("SHA-1");
                byte[] messageDigest1 = md1.digest(saltedGuess1.getBytes()); 
                BigInteger no1 = new BigInteger(1, messageDigest1); 
                String hashtext1 = no1.toString(16); 
                while (hashtext1.length() < 32) { 
                    hashtext1 = "0" + hashtext1; 
                } 
                if(hashtext1.equals(input3)) {
                	System.out.println("The plaintext of input 3 is: "+guess);
                	System.out.println("The time elapsed is "+(-1*(StartTime-System.currentTimeMillis())));
                	System.out.println("Number of tries: "+counts);
                }
        } System.out.println("--------------------------");
        }

        
       
	
	}
	//below will find the answer when two different words are separated by a space
	// sorry for contrived code here. last minute code hustle to get this part functioning
	List<String> list = new ArrayList<>();
 	 String stupidGuessing;
	while((stupidGuessing=br2.readLine())!= null) {
    
     	count++;
     	list.add(stupidGuessing);
	}
	String Guess;
	int x=0;
	while(x<list.size()-1) {
		x++;
		count++;
		MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] messageDigest = md.digest(list.get(x).getBytes()); 
        BigInteger no = new BigInteger(1, messageDigest); 
        String hashtext = no.toString(16); 
        while (hashtext.length() < 32) { 
            hashtext = "0" + hashtext; 
        } 
	int y=0;
	while (y<list.size()-1) {
		y++;
      	String doublephrase= list.get(x)+" "+list.get(y);
  		MessageDigest md2 = MessageDigest.getInstance("SHA-1");
          byte[] messageDigest2 = md2.digest(doublephrase.getBytes()); 
          BigInteger no2 = new BigInteger(1, messageDigest2); 
          String hashtext3 = no2.toString(16); 
          while (hashtext3.length() < 32) { 
              hashtext3 = "0" + hashtext3; 
          } 
          
          if(hashtext3.equals(input4)) {
          	System.out.println("Input 4:");
          	System.out.println("The plaintext of input 4 is: "+list.get(x)+" "+list.get(y));
          	System.out.println("The time elapsed is "+(-1*(StartTime-System.currentTimeMillis())));
          	System.out.println("Number of tries: "+count);
          	System.out.println("--------------------------");
          	
          }
      	//System.out.println("debug"+count+" "+list.get(x)+" "+list.get(y));        
      	}

	}
     
  } 
  
}

