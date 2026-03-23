package Panel;

import Model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;

// ==================================================
// ReportPanel.java — Page for conclusion report
//
// Component that has been used :
//   JTextArea    = Show all revenue (Message)
//   JTable       = list payment record
//   JTabbedPane  = Separate tab between "Conclude" and "Record"
// ==================================================

public class ReportPanel extends JPanel {

    private MainFrame frame;
    private JTextArea summaryArea;
    private DefaultTableModel recordModel;

    public ReportPanel(MainFrame frame) {
        this.frame = frame;
        CreateUI();
    }

    private void CreateUI() {
        setLayout(new BorderLayout(10,10));
        setBorder(BorderFactory.createEmptyBorder(16,16,16,16));

        add(makeTopBar("BRIEF REPORT"), BorderLayout.NORTH);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Total Revenue", CreateSummaryTab());
        tabs.addTab("Payment Record", CreateHistoryTab());
        add(tabs, BorderLayout.CENTER);

        // Below button: Refresh + Export Daily Report
        JPanel bottomBar = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 12, 4));

        JButton refreshButton = new JButton("Refresh Report");
        refreshButton.addActionListener(e -> refreshEntire());

        JButton exportButton = new JButton("Export Daily Report (.txt)");
        exportButton.addActionListener(e -> exportDailyReport());

        bottomBar.add(refreshButton);
        bottomBar.add(exportButton);
        add(bottomBar, BorderLayout.SOUTH);
    }

    // Conclusion revenue tab (Utilize JTextArea)
    private JPanel CreateSummaryTab() {
        JPanel panel = new JPanel(new BorderLayout(0,6));
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        summaryArea = new JTextArea();
        summaryArea.setEditable(false);
        summaryArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        summaryArea.setText("Press 'Refresh Report' For looking information");
        panel.add(new JScrollPane(summaryArea), BorderLayout.CENTER);
        return panel;
    }

    //Tab for record payment (Utilize JTable)
    private JPanel CreateHistoryTab() {
        JPanel panel = new JPanel(new BorderLayout(0,6));
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        recordModel = new DefaultTableModel(
                new String[] {"Payment ID", "User", "Device", "Time(Minutes)", "Total(Baht)",
                        "Status"}, 0) {
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };
        JTable table = new JTable(recordModel);
        table.setRowHeight(24);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        return panel;
    }

    private void refreshEntire() {
        refreshSummary();
        refreshRecord();

        // Update UserData.txt every time when press refresh
        DataLogger.saveActiveSessions(frame.sessions);
    }

    private void refreshSummary() {
        int totalSessions = frame.sessions.size();
        int activeSessions = 0;
        int paidCount = 0;
        double totalRevenue = 0;

        for (Session session : frame.sessions) {
            if (session.isActive()) activeSessions++;
        }

        for (Payment payment : frame.payments) {
            if (payment.isPaid()) {
                paidCount++;
                totalRevenue += payment.getTotal();
            }
        }

        //Count each device status
        int avail = 0, inUse = 0;
        for (Computer computer : frame.computers) {
            switch (computer.getStatus()) {
                case "AVAILABLE": avail++; break;
                case "IN_USE": inUse++; break;
            }
        }

        // Find the device that is used the most
        java.util.HashMap<String, Integer> pcCount = new java.util.HashMap<>();
        for (Session s : frame.sessions) {
            String id = s.getComputer().getComputerId();
            pcCount.put(id, pcCount.getOrDefault(id, 0) + 1);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("============ BRIEF REPORT ============\n");
        sb.append("DATE : ").append(LocalDate.now()).append("\n\n");
        sb.append(String.format("Available Device    : %d Device\n", avail));
        sb.append(String.format("Device using        : %d Device\n", inUse));
        sb.append(String.format("Entire Sessions     : %d Times\n", totalSessions));
        sb.append(String.format("Active Sessions     : %d Times\n\n", activeSessions));
        sb.append(String.format("Successful Payment  : %d List\n", paidCount));
        sb.append(String.format("Total Revenue       : %.2f Baht\n\n", totalRevenue));
        sb.append(String.format("Membership          : %d person\n", frame.members.size()));
        sb.append("======================================");

        summaryArea.setText(sb.toString());
    }

    private void refreshRecord() {
        recordModel.setRowCount(0);
        for (Payment p : frame.payments) {
            Session session = p.getSession();
            recordModel.addRow(new Object[] {
                    p.getPaymentId(),session.getUser().getName(),session.getComputer().getComputerId(),
                    session.getElapsedMinutes(),String.format("%.2f", p.getTotal()),p.isPaid() ? "PAID" : "PENDING"
            });
        }
    }

    // ===== Export Daily Report into a File ====
    private void exportDailyReport() {

        // always update UserData.txt before export
        DataLogger.saveActiveSessions(frame.sessions);
        DataLogger.saveDailyReport(
                frame.sessions,frame.payments,frame.members,frame.computers
        );

        // Tell user where the file it is
        JOptionPane.showMessageDialog(this,
                "Export successfully!\n" +
                        "=> UserData.txt     — Active User Data\n" +
                        "=> DailyReport.txt  — DailyReport",
                "Export Complete", JOptionPane.INFORMATION_MESSAGE);

    }

    private JPanel makeTopBar(String title) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel(title, SwingConstants.CENTER);
        label.setFont(new Font("Dialog", Font.BOLD, 16));
        JButton back = new JButton("Back");
        back.addActionListener(e -> frame.showPage(MainFrame.PAGE_HOME));
        panel.add(label, BorderLayout.CENTER);
        panel.add(back, BorderLayout.EAST);
        return panel;
    }
}