package Panel;

import Model.Computer;
import Model.Member;
import Model.Payment;
import Model.Session;
import Model.DataLogger;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    // Store reference of HomePanel for able to call refreshStatus
    private HomePanel homePanel;

    public ArrayList<Computer> computers = new ArrayList<>();
    public ArrayList<Member> members = new ArrayList<>();
    public ArrayList<Session> sessions = new ArrayList<>();
    public ArrayList<Payment> payments = new ArrayList<>();
    public Member loggedInMember = null;

    // Page name (Utilize with CardLayout)
    public static final String PAGE_HOME    = "HOME";
    public static final String PAGE_MEMBER  = "MEMBER";
    public static final String PAGE_SESSION = "SESSION";
    public static final String PAGE_REPORT  = "REPORT";

    public MainFrame() {
        setTitle("Internet Cafe Management System");
        setSize(700,520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        //Create logs folder since open program
        DataLogger.init();
        initData();
        initialUI(); // Create UI
        setVisible(true);
    }

    private void initData() {
        for (int i = 1; i <= 10; i++) {
            computers.add(new Computer("PC-" + i, 35.0));
        }
        // READ FILE : Load member who ever register to  membership before
        members = DataLogger.loadMembers();
    }

    public void initialUI() {
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        homePanel = new HomePanel(this); // Store reference

        // Add each page to CardLayout
        mainPanel.add(homePanel, PAGE_HOME);
        mainPanel.add(new SessionPanel(this), PAGE_SESSION);
        mainPanel.add(new MemberPanel(this), PAGE_MEMBER);
        mainPanel.add(new ReportPanel(this), PAGE_REPORT);

        add(mainPanel);
        showPage(PAGE_HOME);
    }

    // Call this method to change page
    public void showPage(String pageName) {
        cardLayout.show(mainPanel, pageName);
        if (pageName.equals(PAGE_HOME)) {
            homePanel.RefreshStatus();
        }
    }


}
