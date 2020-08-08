import javax.swing.*;
import java.io.FileNotFoundException;

public class StartWindow extends JPanel{

	JTextField tf;
	public void show(JFrame f) throws FileNotFoundException {
		
		Object[] options = { "PLAY", "RULES", "EXIT" };
		JPanel panel = new JPanel();
		JPanel landtf = new JPanel();
		
		landtf.add(new JLabel("Your nickname"));
		tf = new JTextField(10);
		landtf.add(tf);
		panel.add(landtf);
		
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		
		String columns[]={"POINTS", "NICKNAME"};
		FileRanking fr = new FileRanking();

		
		String t[][] = fr.createArray();
		JTable jt = new JTable(t, columns);
		JScrollPane sp = new JScrollPane(jt); 
		panel.add(sp);

		JLabel ns = new JLabel("Score: ");
		panel.add(ns);
		
		ns.setText("Score: ");
		
		int a = JOptionPane.showOptionDialog(null, panel, "Enter your name and enjoy the game :)", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);
		if(a==JOptionPane.YES_OPTION) {
			String check = tf.getText();
			if(check.length()<1) {
				JOptionPane.showMessageDialog(f,"Set your name!!!","Alert",JOptionPane.WARNING_MESSAGE);
				show(f);
			} else {
				play(f);
			}
		} else if(a==JOptionPane.NO_OPTION) {
			JOptionPane.showMessageDialog(f, "LISTEN THE SOUND SEQUENCES,\n THEN REPEAT");
			show(f);
		} else if (a==JOptionPane.CANCEL_OPTION) {
			System.exit(0);
		} else {
			show(f);
		}

		
	}
	public void play(JFrame f) {
		try {
			ButtonsSound g = new ButtonsSound(f);
			f.setVisible(true);
			String nick = tf.getText();
			g.start_game(false, nick);
		} catch (InterruptedException e) {
			System.out.println("blad ktory nigdy nie wyskoczy");
		}
	}



}
