import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileNotFoundException;

public class ButtonsSound extends JPanel implements ActionListener {
	String nick;
	JButton b1, b2, b3;
	Icon i1,i2,i3; //zwykle otwarte oczy
	Icon j1,j2,j3; //z mrugnieciem
	Icon happy = new ImageIcon("img/happy.png");
	Icon unhappy = new ImageIcon("img/unhappy.png");
	JFrame f;
	
	int prev; //poprzedni dzwiek aby sie nie powtarzaly po sobie
	int r=1; //aktualna runda
	int[] soundsChain = new int[50]; //tablica z odpowiednia kolejnoscia wystepujacych dzwiekow
	int clicks=0;
	boolean failRound=false;
	
	ButtonsSound(JFrame f) throws InterruptedException {
		b1 = new JButton("1");
		b2 = new JButton("2");
		b3 = new JButton("3");
		
		f.setLayout(new GridLayout(1, 3));
		f.add(b1);f.add(b2);f.add(b3);
		
		i1 = new ImageIcon("img/lion1.png");
		j1 = new ImageIcon("img/lion2.png");
		b1.setIcon(i1);
		
		i2 = new ImageIcon("img/pig1.png");
		j2 = new ImageIcon("img/pig2.png");
		b2.setIcon(i2);
		
		i3 = new ImageIcon("img/mouse1.png");
		j3 = new ImageIcon("img/mouse2.png");
		b3.setIcon(i3);
		
		
		
	}
	public void actionPerformed(ActionEvent e) {
		if(!failRound) {
			if(e.getSource()==b1&&soundsChain[clicks]==1) {
				//JOptionPane.showMessageDialog(this, "poprawnie1");
			
			}
			else if(e.getSource()==b2&&soundsChain[clicks]==2) {
				//JOptionPane.showMessageDialog(this, "poprawnie2");
			}
			else if(e.getSource()==b3&&soundsChain[clicks]==3) {
				//JOptionPane.showMessageDialog(this, "poprawnie3");
			}
			else {
				failRound=true;
				JOptionPane.showMessageDialog(this, "You made a mistake!", "Statement", JOptionPane.INFORMATION_MESSAGE, unhappy);
				FileRanking fa = new FileRanking();
				try {
					fa.freshArray(r-1, nick); //dodanie nowego wyniku do tabeli
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			}
			clicks++;
			if(clicks==r&&failRound==false) {
				JOptionPane.showMessageDialog(this, ("Congratulations! You are in the next round with score: "+r), "Statement", JOptionPane.INFORMATION_MESSAGE, happy);
	
				r++;
				clicks=0;
				setButtons(false);
				
				
				next_round(r);

			} else if(failRound==true) {
				StartWindow nr = new StartWindow();
				try {
					nr.show(f); //nowe menu po przegranej
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
				
		}
	}
	
	public void setButtons(boolean ready) {
		if(ready) {
			b1.addActionListener(this);
			b2.addActionListener(this);
			b3.addActionListener(this);
		} else {
			b1.removeActionListener(this);
			b2.removeActionListener(this);
			b3.removeActionListener(this);
		}
	}
	
	
	public void start_game(boolean roundFail, String nick) throws InterruptedException {
			this.nick = nick;
			next_round(r);
	}
	
	public void next_round(int round) {
		
		int whats=0; 
		for(int i=0; i<round; i++) {
			whats = play_sound(); //jaki dzwiek sie odegral
			soundsChain[i] = whats; //0-lew, 1-swinia, 2-mysz
			b1.setIcon(i1);
			b2.setIcon(i2);
			b3.setIcon(i3);
		}
		setButtons(true); //po odegraniu dzwiekow mozna juz wciskac przyciski
		
	}
	int play_sound() {
		int r = (new Random()).nextInt(3)+1;
		while(r==prev) r = (new Random()).nextInt(3)+1;
		prev = r;
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(r==1) {
			try {
				b1.setIcon(j1); //tu problem nie wyswietla sie
				JOptionPane.showMessageDialog(f, "test");
		        AudioInputStream audioInputStream1 = AudioSystem.getAudioInputStream(new File("lionwav.wav").getAbsoluteFile());
		        Clip clip1 = AudioSystem.getClip();
		        clip1.open(audioInputStream1);
		        clip1.start();
		    } catch(Exception ex) {
		        System.out.println("ERROR");
		    }
		}
		if(r==2) {
			try {
				b2.setIcon(j2); //tu problem nie wyswietla sie
				JOptionPane.showMessageDialog(f, "test");
		        AudioInputStream audioInputStream2 = AudioSystem.getAudioInputStream(new File("pigwav.wav").getAbsoluteFile());
		        Clip clip2 = AudioSystem.getClip();
		        clip2.open(audioInputStream2);
		        clip2.start();
		    } catch(Exception ex) {
		        System.out.println("ERROR");
		    }
		}
		if(r==3) {
			try {
				b3.setIcon(j3); //tu problem nie wyswietla sie
				JOptionPane.showMessageDialog(f, "test");
		        AudioInputStream audioInputStream3 = AudioSystem.getAudioInputStream(new File("mousewav.wav").getAbsoluteFile());
		        Clip clip3 = AudioSystem.getClip();
		        clip3.open(audioInputStream3);
		        clip3.start();
		    } catch(Exception ex) {
		        System.out.println("ERROR");
		    }
		}
		try {
			Thread.sleep(800);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}
}