package forms;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;

public class Class_PaintFondo extends JDesktopPane {
	private Image img;
	public Class_PaintFondo(String img) {
		this(new ImageIcon(img).getImage());
	}
	public Class_PaintFondo(Image img) {
	    this.img = img;
	    Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
	    setPreferredSize(size);
	    setMinimumSize(size);
	    setMaximumSize(size);
	    setSize(size);
	    
	    setLayout(null);
	  }
	public void paintComponent(Graphics g) {
		  Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		  g.drawImage(img, 0, 0,(int)screenSize.getWidth(), (int)screenSize.getHeight(), null);
	  }
}
