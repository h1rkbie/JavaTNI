package Panel;

import javax.swing.*;
import java.awt.*;

public class HomePanel extends JPanel {

    private MainFrame frame;
    private JLabel statusLabel; // showText for status

    public HomePanel(MainFrame frame) {
        this.frame = frame;
        CreateUI();
    }

    private void CreateUI() {
        // BorderLayout for separate space to NORTH / CENTER / SOUTH]
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20,40,20,40));

        //Topic and Heading
        JPanel topPanel = new JPanel(new GridLayout(2,1,0,4));
        JLabel title = new JLabel("INTERNET CAFE", SwingConstants.CENTER);
        title.setFont(new Font("Dialog", Font.BOLD, 22));
        statusLabel = new JLabel("Not Login Yet..", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Dialog", Font.PLAIN, 13));
        statusLabel.setForeground(Color.GRAY);

        topPanel.add(title);
        topPanel.add(statusLabel);
        add(topPanel, BorderLayout.NORTH);

        // MAIN CENTER
        JPanel buttonPanel = new JPanel(new GridLayout(3,2,12,12));

        buttonPanel.add(createButton("DEVICE STATUS", MainFrame.PAGE_SESSION));
        buttonPanel.add(createButton("COMMENCE / END SESSION", MainFrame.PAGE_SESSION));
        buttonPanel.add(createButton("MEMBER", MainFrame.PAGE_MEMBER));
        buttonPanel.add(createButton("REPORT", MainFrame.PAGE_REPORT));

        JButton exitButton = new JButton("PROGRAM EXIT");
        exitButton.setFont(new Font("Dialog", Font.PLAIN, 14));
        exitButton.addActionListener(e -> System.exit(0));
        buttonPanel.add(exitButton);

        add(buttonPanel, BorderLayout.CENTER);
    }

    // Helper for button creation that gonna switch when press on it
    private JButton createButton(String text, String targetPage) {
        JButton button = new JButton(text);
        button.setFont(new Font("Dialog", Font.PLAIN, 14));
        button.addActionListener(e -> frame.showPage(targetPage));
        return button;
    }

    //Called this method whenever user back to home page and desire to update status
    public void RefreshStatus() {
        if (frame.loggedInMember != null) {
            statusLabel.setText("Login: " + frame.loggedInMember.getName()
            + " [" + frame.loggedInMember.getMemberType() + " ]");
            statusLabel.setForeground(new Color(0,130,0));
        } else {
            statusLabel.setText("Not Logged yet...");
            statusLabel.setForeground(Color.gray);
        }
    }
}
