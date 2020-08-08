import javax.swing.JFrame;
import javax.swing.JPanel;
import java.io.FileNotFoundException;

public class MyFrame{
	JFrame f;
	public MyFrame() {
		f = new JFrame("SoundRemember 1.0");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//JPanel panel1 = new ButtonsSound(f, false);
		//f.add(panel1);
		
		f.pack();
		f.setSize(1920,1080);
		f.setResizable(false);
		f.setVisible(true);
		
		try {
			StartWindow window = new StartWindow();
			window.show(f);
		} catch (FileNotFoundException e) {
			System.out.println("Nie znaleziono pliku");
			System.exit(0);
		}
		

	}
	public static void main(String[] args) throws FileNotFoundException{
			new MyFrame();
	}
}