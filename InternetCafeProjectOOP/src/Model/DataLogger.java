package Model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

// ==================================================
// DataLogger.java — write 3 files at project loot
//
//   UserData.txt      — User data + active session
//                       (Overwrite every time when has a change)
//
//   Receipt.txt       — Record receipt that has been paid successfully
//                       (append to the end every time when payment)
//
//   DailyReport.txt   — Daily Report
//                       (Overwrite whenever press 'Export button' )
//
// All of 3 files has been expose at root
// ==================================================

public class DataLogger {

    private static final String USER_FILE = "UserData.txt";
    private static final String RECEIPT_FILE = "Receipt.txt";
    private static final String REPORT_FILE = "DailyReport.txt";
    private static final String MEMBER_FILE = "Members.txt";

    private static final DateTimeFormatter DT_FMT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static void init() {}

    // ==================================================
    // 1. UserData.txt
    //    Overwrite every time when session commence or terminate
    //    show all active user data
    // ==================================================
    public static void saveActiveSessions(ArrayList<Session> activeSessions) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(USER_FILE, false))) {

            bw.write("========================================");
            bw.newLine();
            bw.write("  INTERNET CAFE — ACTIVE SESSION LOG");
            bw.newLine();
            bw.write("  Updated: " + LocalDateTime.now().format(DT_FMT));
            bw.newLine();
            bw.write("========================================");
            bw.newLine();
            bw.newLine();

            //Loop write every active session
            boolean hasActive = false;
            for (Session session : activeSessions) {
                if (!session.isActive()) continue;
                hasActive = true;

                bw.write("Session ID   : " + session.getSessionID());
                bw.newLine();
                bw.write("Name         : " + session.getUser().getName());
                bw.newLine();

                //If was MEMBER show ID and type
                if (session.getUser() instanceof Member) {
                    Member member = (Member) session.getUser();
                    bw.write("Member ID    : " + member.getUserid());
                    bw.newLine();
                    bw.write("Member Type  : " + member.getMemberType());
                    bw.newLine();
                    bw.write("Discount     : " + (int)(member.getDiscount() * 100) + "%");
                    bw.newLine();
                }
                else {
                    bw.write("Member ID   : (Guest)");
                    bw.newLine();
                    bw.write("Member Type : GUEST");
                    bw.newLine();
                    bw.write("Discount    : 0%");
                    bw.newLine();
                }

                bw.write("Device       : " + session.getComputer().getComputerId());
                bw.newLine();
                bw.write("Time Used    : " + session.getElapsedMinutes() + " Minutes");
                bw.newLine();
                bw.write(String.format("Current Fee  : %.2f Baht", session.CalculateFee()));
                bw.newLine();
                bw.write("----------------------------------------");
                bw.newLine();
            }

            if (!hasActive) {
                bw.write("  (No active sessions at this time)");
                bw.newLine();
            }

            bw.newLine();
            bw.write("=> Total Active: " + activeSessions.stream().filter(Session::isActive).count() +
                    " sessions(s)");
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error!!: Cannot write file.." + e.getMessage());
        }
    }

    // ==================================================
    // 2. Receipt.txt
    //    append to the end every time when paid successfully
    //    Call from SessionPanel after press Terminate + paid
    // ==================================================
    public static void saveReceipt(Payment payment, Session session) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(RECEIPT_FILE, true))) {

            bw.write("============================================");
            bw.newLine();
            bw.write("           INTERNET CAFE - RECEIPT");
            bw.newLine();
            bw.write("           Issued : " + LocalDateTime.now().format(DT_FMT));
            bw.newLine();
            bw.write("============================================");
            bw.newLine();
            bw.write("Payment ID   : " + payment.getPaymentId());
            bw.newLine();
            bw.write("Name         : " + session.getUser().getName());
            bw.newLine();

            if (session.getUser() instanceof Member) {
                Member m = (Member) session.getUser();
                bw.write("Member ID    : " + m.getUserid());
                bw.newLine();
                bw.write("Member Type  : " + m.getMemberType());
                bw.newLine();
            } else {
                bw.write("Member Type  : GUEST");
                bw.newLine();
            }

            bw.write("Device       : " + session.getComputer().getComputerId());
            bw.newLine();
            bw.write("Time Used    : " + session.getElapsedMinutes() + " Minutes");
            bw.newLine();

            double discount = session.getUser().getDiscount();
            if (discount > 0) {
                bw.write(String.format("Discount     : %.0f%%", discount * 100));
                bw.newLine();
            }

            bw.write(String.format("Total        : %.2f Baht", payment.getTotal()));
            bw.newLine();
            bw.write("Status       : " + (payment.isPaid() ? "PAID" : "PENDING"));
            bw.newLine();
            bw.write("============================================");
            bw.newLine();
            bw.newLine();

        } catch (IOException e) {
            System.out.println("[DataLogger] Cannot write " + RECEIPT_FILE + " : " + e.getMessage());
        }
    }


    // ==================================================
    // 3. Members.txt — WRITE
    //    Record entire member into file
    //    It has been called every time when have a new register
    //    Each Model : NAME|MEMBERTYPE|PASSWORD
    // ==================================================
    public static void saveMember(ArrayList<Member> members) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(MEMBER_FILE, false))) {
            for (Member member : members) {

                // Separate each field with a | so that loadMembers() can easily separate them
                bw.write(member.getName() + "|" + member.getMemberType() + "|" + member.getPassword());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("[DataLogger] Cannot write " + MEMBER_FILE + " : " + e.getMessage());
        }
    }

    // ==================================================
    // 4. Members.txt — READ
    //    Read member from file that return to ArrayList
    //    has been called when open program in MainFrame
    //    If not have the file (Firstly) will return Available ArrayList
    // ==================================================
    public static ArrayList<Member> loadMembers() {
        ArrayList<Member> list = new ArrayList<>();
        java.io.File file = new java.io.File(MEMBER_FILE);

        if (!file.exists()) return list;

        try (java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;


                //Separate field by using '|'
                String[] parts = line.split("\\|");
                if (parts.length == 3) {
                    String name = parts[0];
                    String memberType = parts[1];
                    String password = parts[2];
                    list.add(new Member(name, memberType, password));
                }
            }
        } catch (IOException e) {
            System.out.println("[DataLogger] Cannot read " + MEMBER_FILE + " : " + e.getMessage());
        }
        return list;
    }

    //
    // append the file (append = true) to store the history
    public static void logEvent(String eventType, Session session) {
    }

    // ==== Create Daily Report File ====
    // Record to daily_report_dd-MM-yyyy.txt
    public static void saveDailyReport(ArrayList<Session> sessions,ArrayList<Payment> payments,
                                       ArrayList<Member> members, ArrayList<Computer> computers) {
        String today = LocalDateTime.now().format(DATE_FMT);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(REPORT_FILE, false))) {

            // Report header
            bw.write("============================================");
            bw.newLine();
            bw.write("       INTERNET CAFE — DAILY REPORT");
            bw.newLine();
            bw.write("       Date: " + today);
            bw.newLine();
            bw.write("       Generated: " + LocalDateTime.now().format(DT_FMT));
            bw.newLine();
            bw.write("============================================");
            bw.newLine();
            bw.newLine();

            // ===== First Part : Conclusion of device ====
            bw.write("[ DEVICE SUMMARY ]");
            bw.newLine();
            int avail = 0, inUse = 0;
            for (Computer computer : computers) {
                if (computer.isAvailable()) avail++; else inUse++;
            }
            bw.write(String.format("  Available  : %d devices%n", avail));
            bw.write(String.format("  In Use     : %d devices%n", inUse));
            bw.write(String.format("  Total      : %d devices%n", computers.size()));
            bw.newLine();

            // ==== Second Part : Conclusion of session ====
            bw.write("[ SESSION SUMMARY ]");
            bw.newLine();
            int totalSession = sessions.size();
            long activeSession = sessions.stream().filter(Session::isActive).count();
            bw.write(String.format("  Total Sessions  : %d%n", totalSession));
            bw.write(String.format("  Active Now      : %d%n",activeSession));
            bw.write(String.format("  Ended Sessions  : %d%n", totalSession - (int)activeSession));
            bw.newLine();

            // ===== Third Part : Conclusion of revenue =====
            bw.write("[ REVENUE SUMMARY ]");
            bw.newLine();
            int    paidCount = 0;
            double revenue   = 0;
            for (Payment p : payments) {
                if (p.isPaid()) { paidCount++; revenue += p.getTotal(); }
            }
            bw.write(String.format("  Paid Transactions : %d%n", paidCount));
            bw.write(String.format("  Total Revenue     : %.2f Baht%n", revenue));
            bw.newLine();

            // ===== Fourth Part : Conclusion of membership =====
            bw.write("[ MEMBERSHIP SUMMARY ]");
            bw.newLine();
            long regular = members.stream()
                    .filter(m -> m.getMemberType().equals("REGULAR")).count();
            long vip = members.stream()
                    .filter(m -> m.getMemberType().equals("VIP")).count();
            bw.write(String.format("  Total Members : %d%n", members.size()));
            bw.write(String.format("  Regular       : %d%n", regular));
            bw.write(String.format("  VIP           : %d%n", vip));
            bw.newLine();

            // ===== Fifth Part : Conclusion of each session =====
            bw.write("[ SESSION DETAILS ]");
            bw.newLine();
            bw.write(String.format("  %-10s %-15s %-10s %-12s %-10s%n",
                    "SessionID", "Name", "Device", "Minutes", "Fee(Baht)"));
            bw.write("  " + "-".repeat(60));
            bw.newLine();

            for (Session s : sessions) {
                bw.write(String.format("   %-8s  %-16s  %-8s  %-9d    %.2f%n",
                        s.getSessionID(),
                        s.getUser().getName(),
                        s.getComputer().getComputerId(),
                        s.getElapsedMinutes(),
                        s.CalculateFee()));
            }
            bw.newLine();

            // ===== Sixth Part : Payment Detail =====
            bw.write("[ PAYMENT DETAILS ]");
            bw.newLine();
            bw.write(String.format("  %-10s %-12s %s%n",
                    "PaymentID", "Total(Baht)", "Status"));
            bw.write("  " + "-".repeat(38));
            bw.newLine();
            for (Payment p : payments) {
                bw.write(String.format("  %-12s %-10.2f %-12s%n",
                        p.getPaymentId(), p.getTotal(),
                        p.isPaid() ? "PAID" : "PENDING"));
            }
            bw.newLine();

            bw.write("============================================");

        } catch (IOException e) {
            System.out.println("[DataLogger] Cannot write " + REPORT_FILE + " : " + e.getMessage());
        }
    }

}
