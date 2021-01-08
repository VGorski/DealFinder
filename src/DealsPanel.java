// authors: Victoria Gorski, Joseph Mannarino, and Julia Wilkinson
// description: The DealsPanel class is used to display a popup to the user in order to notify if any deals have been found or if no deals have been found

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DealsPanel extends JFrame {
	JPanel popUpPanel = new JPanel();

	public DealsPanel(){
		//set characteristics of Jframe
		super("Deals");
		Image icon = new javax.swing.ImageIcon("dealfinder_logo.jpg").getImage();
		setIconImage(icon);
		setMinimumSize(new Dimension(600, 475));
		popUpPanel.setBackground(Color.RED);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		popUpPanel.setLayout(new BoxLayout(popUpPanel, BoxLayout.Y_AXIS));
		add(popUpPanel);
		setVisible(true);
	}

	public void displayDeals(ArrayList<String> parsedData){
		//no deals were found
		if (parsedData.isEmpty()) {
			System.out.println("No deals were found");
			this.popUpPanel.add(new JLabel("No deals were found"));
		}
		//add deals found to jframe
		else {
			this.popUpPanel.removeAll();
			JLabel header = new JLabel("Deals found on www.dealsea.com: ");
			this.popUpPanel.add(header);
			System.out.println("Found " + parsedData.size() + " deals!");
			int count = 0;
			for (int j = 0; j < parsedData.size(); j++) {
				count++;
				JLabel newDeal = new JLabel("<html><font color=white size=4><b>" + count + ".  " + parsedData.get(j) +  "</b></html>");
				newDeal.setForeground(Color.white);
				JLabel space = new JLabel(" ");
				this.popUpPanel.add(space);
				newDeal.setFont(new Font("Verdana", Font.PLAIN, 18));
				this.popUpPanel.add(newDeal);
				
			}
		}
		pack();
	}
}
