package Panel;

import Model.DataLogger;
import Model.Member;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

// ==================================================
// Panel.MemberPanel.java — Page for membership management
//
// Component that has been used:
//   JTextField     = field for input message
//   JPasswordField = password input field (show ***)
//   JComboBox      = dropdown for select type of member
//   JTable         = List for show member name
//   JTabbedPane    = Tap switch between "apply" and "Login"
// ==================================================

public class MemberPanel extends JPanel {

    private MainFrame frame;

    // Field input for apply membership
    private JTextField regNameField;
    private JPasswordField regPassField;
    private JComboBox<String> regTypeBox;

    //Field input for login
    private JTextField loginNameField;
    private JPasswordField loginPassField;

    //List for show member name
    private DefaultTableModel tabelModel;

    //Label for show login status
    private JLabel loginStatusLabel;

    public MemberPanel(MainFrame frame) {
        this.frame = frame;
        CreateUI();
    }

    private void CreateUI() {
        setLayout(new BorderLayout(10,10));
        setBorder(BorderFactory.createEmptyBorder(16,24,16,24));


        // --- NORTH : TITLE + BACK BUTTON ----
        add(makeTopBar("Member Management"), BorderLayout.NORTH);

        // --- CENTER : JTabbedPane separate to 3 Tap ---
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Register membership", CreateRegisterTap());
        tabs.addTab("Login / Logout", CreateLoginTap());
        tabs.addTab("Membership List", CreateTableTap());
        add(tabs, BorderLayout.CENTER);
    }

    // ==== FIRST TAB : register membership ====
    private JPanel CreateRegisterTap() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20,40,20,40));
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(6,6,6,6);
        constraints.fill = GridBagConstraints.HORIZONTAL;

        //Row 0: Name
        constraints.gridx = 0; constraints.gridy = 0; constraints.weightx = 0.3;
        panel.add(new JLabel("Username: "), constraints);
        constraints.gridx = 1; constraints.weightx = 0.7;
        regNameField = new JTextField(16);
        panel.add(regNameField, constraints);

        //Row 1: Password
        constraints.gridx = 0; constraints.gridy = 1;
        panel.add(new JLabel("Password: "), constraints);
        constraints.gridx = 1;
        regPassField = new JPasswordField(16);
        panel.add(regPassField, constraints);

        //Row 2: Type
        constraints.gridx = 0; constraints.gridy = 2;
        panel.add(new JLabel("Type: "), constraints);
        constraints.gridx = 1;
        regTypeBox = new JComboBox<>(new String[] {"REGULAR", "VIP"});
        panel.add(regTypeBox, constraints);

        //Row 3: Register button
        constraints.gridx = 0; constraints.gridy = 3; constraints.gridwidth = 2;
        JButton registerButton = new JButton("Register Membership");
        registerButton.addActionListener(e -> doRegister());
        panel.add(registerButton, constraints);

        return panel;
    }

    private void doRegister() {
        String username = regNameField.getText().trim();
        String password = new String(regPassField.getPassword()).trim();
        String type = (String) regTypeBox.getSelectedItem();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all the fields!", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // always scrutinize name duplicate before add
        for (Member existing : frame.members) {
            if (existing.getName().equalsIgnoreCase(username)) {
                JOptionPane.showMessageDialog(this, "Username \"" + username + "\" is already taken!\nPlease " +
                        "choose a other name.", "Duplicate Username", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }

        Member member = new Member(username, type, password);
        frame.members.add(member);

        //WRITE FILE : Record entire membership into Members.txt immediately
        DataLogger.saveMember(frame.members);
        refreshTable();

        //Clear field after enrolment
        regNameField.setText("");
        regPassField.setText("");
        JOptionPane.showMessageDialog(this, "Register successfully!\n" + member ,"SUCCESS", JOptionPane.INFORMATION_MESSAGE);
    }

    // ==== SECOND TAB : Login / Logout ====
    private JPanel CreateLoginTap() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20,40,20,40));
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(6,6,6,6);
        constraints.fill = GridBagConstraints.HORIZONTAL;

        constraints.gridx = 0; constraints.gridy = 0; constraints.weightx = 0.3;
        panel.add(new JLabel("Username: "), constraints);
        constraints.gridx = 1; constraints.weightx = 0.7;
        loginNameField = new JTextField(16);
        panel.add(loginNameField, constraints);

        constraints.gridx = 0; constraints.gridy = 1;
        panel.add(new JLabel("Password: "), constraints);
        constraints.gridx = 1;
        loginPassField = new JPasswordField(16);
        panel.add(loginPassField, constraints);

        //Login Button
        constraints.gridx = 0; constraints.gridy = 2;  constraints.gridwidth = 2;
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> doLogin());
        panel.add(loginButton, constraints);

        // Status
        constraints.gridy = 3;
        loginStatusLabel = new JLabel("Not Logged in yet...", SwingConstants.CENTER);
        loginStatusLabel.setForeground(Color.GRAY);
        panel.add(loginStatusLabel, constraints);

        // Logout button
        constraints.gridy = 4;
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> doLogout());
        panel.add(logoutButton, constraints);

        return panel;
    }

    private void doLogin() {
        String username = loginNameField.getText().trim();
        String password = new String(loginPassField.getPassword()).trim();

        for (Member member : frame.members) {
            if (member.getName().equals(username) && member.checkPassword(password)) {
                frame.loggedInMember = member;
                loginStatusLabel.setText("Login : " + member.getName() + " [" + member.getMemberType() + "]");
                loginStatusLabel.setForeground(new Color(0,130,0));
                loginNameField.setText("");
                loginPassField.setText("");
                JOptionPane.showMessageDialog(this, "Welcome, " + member.getName());
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Name or Password is incorrect!", "Login unsuccessfully..", JOptionPane.ERROR_MESSAGE);
    }

    private void doLogout() {
        if (frame.loggedInMember == null) {
            JOptionPane.showMessageDialog(this, "Not logged in yet...", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String name = frame.loggedInMember.getName();
        frame.loggedInMember = null;
        loginStatusLabel.setText("Not Logged in yet...");
        loginStatusLabel.setForeground(Color.BLACK);
        JOptionPane.showMessageDialog(this, "Logout successfully (USER) : " + name);
        // Back to Home in order to RefreshStatus() active - show status "Not Logged yet..."
        frame.showPage(MainFrame.PAGE_HOME);
    }

    // ==== THIRD TAB : List name membership
    private JPanel CreateTableTap() {
        JPanel panel = new JPanel(new BorderLayout(0,8));
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        // JTabel require TabelModel to know what columns are present.
        tabelModel = new DefaultTableModel(
                new String[] {"ID", "Name", "Type"},0);
        JTable table = new JTable(tabelModel);
        table.setRowHeight(24);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> refreshTable());
        panel.add(refreshButton, BorderLayout.SOUTH);

        return panel;
    }

    public void refreshTable() {
        if (tabelModel == null) return;
        tabelModel.setRowCount(0);
        for (Member member : frame.members) {
            tabelModel.addRow(new Object[]{
                    member.getUserid(), member.getName(),
                    member.getMemberType()
            });
        }
    }

    //Helper : Top Bar, conducive for back button
    private JPanel makeTopBar(String title) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel(title, SwingConstants.CENTER);
        label.setFont(new Font("Dialog", Font.BOLD, 16));
        JButton backButton = new JButton("BACK");
        backButton.addActionListener(e -> frame.showPage(MainFrame.PAGE_HOME));
        panel.add(label, BorderLayout.CENTER);
        panel.add(backButton, BorderLayout.EAST);
        return panel;
    }

}
