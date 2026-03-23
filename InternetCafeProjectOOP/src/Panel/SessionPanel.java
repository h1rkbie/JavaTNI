package Panel;

import Model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

// ==================================================
// Panel.SessionPanel.java — For usage device management
//
// Component that has been used :
//   JTable         = Show all device schedule
//   JScrollPane    = For scrolling list
//   JOptionPane    = popup for input name / notify
//   Timer          = Update list every 10 sec
// ==================================================

public class SessionPanel extends JPanel {
    private MainFrame frame;

    //device schedule
    private DefaultTableModel computerTabelModel;
    private JTable computerTable;

    //Active sessions schedule
    private DefaultTableModel sessionTabelModel;

    public SessionPanel(MainFrame frame) {
        this.frame = frame;
        CreateUI();

        //Update list every 10 seconds
        new Timer(10_000, e -> refreshTables()).start();
    }

    private void CreateUI () {
        setLayout(new BorderLayout(10,10));
        setBorder(BorderFactory.createEmptyBorder(16,16,16,16));

        add(makeTopBar("Device & Session Status"), BorderLayout.NORTH);

        // Separate & Device in Half = On top is the device list, Under is a active session
        JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
                CreateComputerTable(), CreateSessionTable());
        split.setDividerLocation(220);
        split.setResizeWeight(0.5);
        add(split, BorderLayout.CENTER);

        // Buttons below
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 4));
        JButton startButton = new JButton("Commence Using (START)");
        JButton endButton = new JButton("Terminate Using (END)");
        JButton refreshButton = new JButton("REFRESH");

        startButton.addActionListener(e -> doStartSession());
        endButton.addActionListener(e -> doTerminateSession());
        refreshButton.addActionListener(e -> refreshTables());

        buttonPanel.add(startButton);
        buttonPanel.add(endButton);
        buttonPanel.add(refreshButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Show entire device list
    private JPanel CreateComputerTable() {
        JPanel panel = new JPanel(new BorderLayout(0,4));
        panel.setBorder(BorderFactory.createTitledBorder("Entire Computer"));

        computerTabelModel = new DefaultTableModel(
                new String[] {"Device", "Status", "Price/Hours."},0) {
            //made cell unable to revise schedule
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };
        computerTable = new JTable(computerTabelModel);
        computerTable.setRowHeight(24);

        // MouseAdapter is a class for implement MousListener to override specially as want
        computerTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // Double click == 2
                if (evt.getClickCount() == 2) {
                    int row = computerTable.getSelectedRow();
                    if (row < 0 || row >= frame.computers.size()) return;

                    //Fetch computer object follow row that has been clicked
                    Computer selectedComputer = frame.computers.get(row);
                    doStartSessionWithComputer(selectedComputer);
                }
            }
        });

        //Alternate color by follow status
        computerTable.setDefaultRenderer(Object.class, new StatusCellRenderer());
        panel.add(new JScrollPane(computerTable), BorderLayout.CENTER);
        return panel;
    }

    //Show active session lists
    private JPanel CreateSessionTable() {
        JPanel panel = new JPanel(new BorderLayout(0,4));
        panel.setBorder(BorderFactory.createTitledBorder("The session that has been used"));
        sessionTabelModel = new DefaultTableModel(
                new String[] {"Session ID", "User", "Device", "Time (Minutes)"},0) {
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };
        JTable sessionTable = new JTable(sessionTabelModel);
        sessionTable.setRowHeight(24);

        panel.add(new JScrollPane(sessionTable), BorderLayout.CENTER);
        return panel;
    }

    //Commence new session || has been called from START Button (find available device automatically)
    private void doStartSession() {
        //Find available device
        Computer free = null;
        for (Computer c : frame.computers) {
            if (c.isAvailable()) {
                free = c;
                break;
            }
        }
        if (free == null) {
            JOptionPane.showMessageDialog(this, "Not have any available computer..");
            return;
        }
        doStartSessionWithComputer(free); //Send device that has been selected
    }

    // Could call from START Button & Double-click in row
    private void doStartSessionWithComputer(Computer selected) {
        if (!selected.isAvailable()) {
            JOptionPane.showMessageDialog(this,
                    selected.getComputerId() + " is not available!\nStatus: " + selected.getStatus(),
                    "Device Unavailable", JOptionPane.WARNING_MESSAGE);
            return;
        }

        //inquire to user for using between Guest or Member
        String[] options = {"GUEST", "MEMBER"};
        int choice = JOptionPane.showOptionDialog(this,
                "Available Device: " + selected.getComputerId() + "\nSelect User Type:",
                "Commence Using", JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (choice == JOptionPane.CLOSED_OPTION) return; // Click X to close a popup

        User user;
        if (choice == 1) {
            if (frame.loggedInMember == null) {
                JOptionPane.showMessageDialog(this, "Please login first!");
                return;
            }
            user = frame.loggedInMember;
        } else {
            String name = JOptionPane.showInputDialog(this, "Guest Name:");
            if (name == null || name.trim().isEmpty()) return;
            user = new Guest(name.trim());
        }

        String sid = "S" + (frame.sessions.size() + 1);
        Session session = new Session(sid, user, selected);
        frame.sessions.add(session);

        DataLogger.logEvent("COMMENCE", session);
        DataLogger.saveActiveSessions(frame.sessions);

        refreshTables();
        JOptionPane.showMessageDialog(this,
                "Commence Using!\nSession: " + sid + "\nDevice: " + selected.getComputerId());
    }

    // Terminate session and then go to payment
    private void doTerminateSession() {
        //Find active session
        java.util.ArrayList<String> activeIds = new java.util.ArrayList<>();
        for (Session session : frame.sessions) {
            if (session.isActive()) activeIds.add(session.getSessionID());
        }

        if (activeIds.isEmpty()) {
            JOptionPane.showMessageDialog(this, "There is no active session!");
            return;
        }

        //Let select session from dropdown
        String sid = (String) JOptionPane.showInputDialog(this, "Select session to terminate:",
                "TERMINATE USAGE", JOptionPane.QUESTION_MESSAGE, null, activeIds.toArray(), activeIds.get(0));
        if (sid == null) {return;}

        Session target = null;
        for (Session session : frame.sessions) {
            if (session.getSessionID().equals(sid)) {
                target = session;
                break;
            }
        }
        if (target == null) return;

        target.close();

        // Record event in the end and update file "Active sessions"
        DataLogger.logEvent("TERMINATION", target);
        DataLogger.saveActiveSessions(frame.sessions);

        Payment payment = new Payment(target);
        frame.payments.add(payment);

        //Show receipt in JTextArea popup
        String receipt = payment.getReceiptText();
        JTextArea receiptArea = new JTextArea(receipt);
        receiptArea.setEditable(false);
        receiptArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        JOptionPane.showMessageDialog(this, new JScrollPane(receiptArea),
                "RECEIPT" , JOptionPane.INFORMATION_MESSAGE);

        //Receive money
        String cashStr = JOptionPane.showInputDialog(this, String.format("Total: %.2f Baht\nInput money:"
        , payment.getTotal()));
        if (cashStr != null) {
            try {
                double cash = Double.parseDouble(cashStr.trim());
                double change = payment.pay(cash);

                if (change >= 0) {
                    DataLogger.saveReceipt(payment, target);
                    JOptionPane.showMessageDialog(this, String.format("Change: %.2f Baht", change));
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a number...");
            }
        }
        refreshTables();
    }

    // ==== CellRenderer: Change color by following status ====
    // DefaultTableCellRenderer is a class that control status each Cell
    static class StatusCellRenderer extends DefaultTableCellRenderer {
        public Component getTableCellRendererComponent(
                JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int column) {

            Component component = super.getTableCellRendererComponent(
                    table, value, isSelected, hasFocus, row, column);

            //Sight a value in column "Status" (Col 1)
            Object status = table.getModel().getValueAt(row, 1);
            if (!isSelected) {
                if ("AVAILABLE".equals(status)) {
                    component.setBackground(new Color(220,255,220));
                } else if ("IN_USE".equals(status)) {
                    component.setBackground(new Color(255, 230, 200));
                } else {
                    component.setBackground(new Color(255,210, 210));
                }
            }
            return component;
        }
    }

    private JPanel makeTopBar(String title) {
        JPanel p = new JPanel(new BorderLayout());
        JLabel label = new JLabel(title, SwingConstants.CENTER);
        label.setFont(new Font("Dialog", Font.BOLD, 16));
        JButton back = new JButton("BACK");
        back.addActionListener(e -> {
            refreshTables();
            frame.showPage(MainFrame.PAGE_HOME);
        });
        p.add(label, BorderLayout.CENTER);
        p.add(back, BorderLayout.EAST);
        return p;
    }

    public void refreshTables() {
        // Update device status
        computerTabelModel.setRowCount(0);
        for (Computer c : frame.computers) {
            computerTabelModel.addRow(new Object[]{
                    c.getComputerId(), c.getStatus(),
                    String.format("%.0f Baht.", c.getRatePerHour())
            });
        }

        //Update session list
        sessionTabelModel.setRowCount(0);
        for (Session s : frame.sessions) {
            if (s.isActive()) {
                sessionTabelModel.addRow(new Object[]{
                        s.getSessionID(), s.getUser().getName(),
                        s.getComputer().getComputerId(), s.getElapsedMinutes()
                });
            }
        }
    }
}
