
package com.mycompany.hotel;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.*;


public class mainfram extends javax.swing.JFrame {
    private UserManager userManager;
    private RoomServiceManager roomManger;
    private HashMap<Integer, Room> rooms;
    private HashMap<Integer, Customers> customers ;
    private HashMap<Integer,Employee> employees;
    private HashMap<Integer,Service> services;
    private HashMap<Integer,Reservation> reservations ;
    private CheckLogin username = new CheckLogin();

    
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(mainfram.class.getName());

    public mainfram() {
        userManager = new UserManager();
        roomManger = new RoomServiceManager();
        customers = userManager.getCustomers() ;
        employees = userManager.getEmployees() ;
        rooms = roomManger.getRooms() ;
        services = roomManger.getServices();
        reservations = roomManger.getReservations();
        roomManger.loadRoomsFromFile();
        roomManger.loadServicesFromFile();
        roomManger.loadReservationsFromFile(customers);
        populateFiltersAndServices();

        initComponents();
        loadEmployeesToTable();
        loadCustomersToTable();
        loadServicesToTable();
        loadRoomsToTable();
        loadUsersToTable();
        loadReservationsToTable();
        
    }

    private void loadEmployeesToTable() {
        DefaultTableModel model = (DefaultTableModel) EmployeeTable.getModel();
        model.setRowCount(0);
        for (Employee e : employees.values()) {
            model.addRow(new Object[]{e.getId(), e.getName(), e.getPhone(), e.getEmail()});
        }
    }

    private void loadCustomersToTable() {
    DefaultTableModel model = (DefaultTableModel) CustomerTable.getModel();
    model.setRowCount(0);
    for (Customers c : customers.values()) {
        model.addRow(new Object[]{c.getId(), c.getName(), c.getPhone(), c.getEmail()});
    }
    }

    private void loadServicesToTable() {
    DefaultTableModel model = (DefaultTableModel) jTableServices.getModel();
    model.setRowCount(0);
    for (Service s : services.values()) {
        model.addRow(new Object[]{s.getServiceId(), s.getName(), s.getDescription(), s.getPrice()});
    }
}

    private void loadUsersToTable() {
    DefaultTableModel model = (DefaultTableModel) UserTable.getModel();
    model.setRowCount(0);
    for (Login login : username.getUserPassList()) {
        model.addRow(new Object[]{login.getUsername(), login.getPassword()});
    }
}

    private void loadRoomsToTable() {
    DefaultTableModel model = (DefaultTableModel) RoomTable.getModel();
    model.setRowCount(0);
    for (Room room : rooms.values()) {
        model.addRow(new Object[]{
            room.getRoomId(),
            room.getRoomType(),
            room.getPrice(),
            room.isBusy()
        });
    }
}

    private void populateFiltersAndServices() {

        TypeFilter.removeAllItems();
        TypeFilter.addItem("All");
        HashSet<String> seenTypes = new HashSet<>();
        for (Room room : rooms.values()) {
            String type = room.getRoomType();
            if (type != null && seenTypes.add(type)) {
                TypeFilter.addItem(type);
            }
        }


        BusyFilter.removeAllItems();
        BusyFilter.addItem("All");
        BusyFilter.addItem("Available");
        BusyFilter.addItem("Busy");


        ServiceBox.removeAllItems();
        for (Service s : services.values()) {
            ServiceBox.addItem(s.getName());
        }
    }

    private void loadReservationsToTable() {
    DefaultTableModel model = (DefaultTableModel) ReservationTable.getModel();
    model.setRowCount(0);

    for (Reservation r : roomManger.getReservations().values()) {

        String servicesStr = "";
        if (r.getServices() != null && !r.getServices().isEmpty()) {
            for (Service s : r.getServices()) {
                servicesStr += s.getName() + ", ";
            }
            servicesStr = servicesStr.substring(0, servicesStr.length() - 2);
        }

        model.addRow(new Object[]{
                r.getReservationId(),
                r.getRoom().getRoomId(),
                r.getCustomer().getId(),
                r.getCheckIn(),
                r.getCheckOut(),
                servicesStr
        });
    }
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        RoomTap = new javax.swing.JTabbedPane();
        EmployeesPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        Eid = new javax.swing.JTextField();
        Ename = new javax.swing.JTextField();
        Ephone = new javax.swing.JTextField();
        Empemail = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        EmployeeTable = new javax.swing.JTable();
        AddEmployee = new javax.swing.JButton();
        DeleteEmployees = new javax.swing.JButton();
        UpdateEmployee = new javax.swing.JButton();
        back = new javax.swing.JButton();
        CustomersPanel = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        CusId = new javax.swing.JTextField();
        CusName = new javax.swing.JTextField();
        CusPhone = new javax.swing.JTextField();
        CusEmail = new javax.swing.JTextField();
        Addcus = new javax.swing.JButton();
        Deletecus = new javax.swing.JButton();
        updatecus = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        CustomerTable = new javax.swing.JTable();
        RoomPanel = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel8 = new javax.swing.JPanel();
        GetCheckOuts = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        ReservationID = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        ServiceBox = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        ReservationTable = new javax.swing.JTable();
        AddRoomPanel = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        RoomID = new javax.swing.JTextField();
        RoomType = new javax.swing.JTextField();
        RoomPrice = new javax.swing.JTextField();
        IsBusy = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        RoomTable = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        DeleteRoom = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        RoomID2 = new javax.swing.JTextField();
        CustomerId2 = new javax.swing.JTextField();
        Checkin = new javax.swing.JTextField();
        Checkout = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        RoomTable2 = new javax.swing.JTable();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        TypeFilter = new javax.swing.JComboBox<>();
        BusyFilter = new javax.swing.JComboBox<>();
        Applybtn = new javax.swing.JButton();
        RoomToGuest = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        ServicesPanel = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        ServiceId = new javax.swing.JTextField();
        ServiceName = new javax.swing.JTextField();
        ServiceDescription = new javax.swing.JTextField();
        ServicePrice = new javax.swing.JTextField();
        AddServicebtn = new javax.swing.JButton();
        ServiceDelete = new javax.swing.JButton();
        ServiceUpdate = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableServices = new javax.swing.JTable();
        AddUserPanel = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        Username = new javax.swing.JTextField();
        UserPass = new javax.swing.JTextField();
        Adduser = new javax.swing.JButton();
        UpdateUser = new javax.swing.JButton();
        DeleteUser = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        UserTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Id");

        jLabel2.setText("Name");

        jLabel3.setText("Phone");

        jLabel4.setText("Email");

        EmployeeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Name", "Phone", "Email"
            }
        ));
        jScrollPane1.setViewportView(EmployeeTable);

        AddEmployee.setText("Add");
        AddEmployee.addActionListener(this::AddEmployeeActionPerformed);

        DeleteEmployees.setText("Delete");
        DeleteEmployees.addActionListener(this::DeleteEmployeesActionPerformed);

        UpdateEmployee.setText("Update");
        UpdateEmployee.addActionListener(this::UpdateEmployeeActionPerformed);

        back.setText("Logout");
        back.addActionListener(this::backActionPerformed);

        javax.swing.GroupLayout EmployeesPanelLayout = new javax.swing.GroupLayout(EmployeesPanel);
        EmployeesPanel.setLayout(EmployeesPanelLayout);
        EmployeesPanelLayout.setHorizontalGroup(
            EmployeesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EmployeesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(EmployeesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(EmployeesPanelLayout.createSequentialGroup()
                        .addGroup(EmployeesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(EmployeesPanelLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(40, 40, 40)
                                .addComponent(Eid, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(EmployeesPanelLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Ephone))
                            .addGroup(EmployeesPanelLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Empemail))
                            .addGroup(EmployeesPanelLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Ename, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(232, 232, 232)
                        .addGroup(EmployeesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(AddEmployee)
                            .addComponent(DeleteEmployees)
                            .addComponent(UpdateEmployee))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 466, Short.MAX_VALUE)
                        .addComponent(back)))
                .addContainerGap())
        );
        EmployeesPanelLayout.setVerticalGroup(
            EmployeesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EmployeesPanelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(EmployeesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(Eid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddEmployee))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(EmployeesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(Ename, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DeleteEmployees))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(EmployeesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(EmployeesPanelLayout.createSequentialGroup()
                        .addGroup(EmployeesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(Ephone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(UpdateEmployee))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(EmployeesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(Empemail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EmployeesPanelLayout.createSequentialGroup()
                        .addComponent(back)
                        .addGap(27, 27, 27)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 737, Short.MAX_VALUE)
                .addContainerGap())
        );

        RoomTap.addTab("Employees", EmployeesPanel);

        jLabel5.setText("Id");

        jLabel6.setText("Name");

        jLabel7.setText("phome");

        jLabel8.setText("Email");

        CusId.addActionListener(this::CusIdActionPerformed);

        CusName.addActionListener(this::CusNameActionPerformed);

        Addcus.setText("Add");
        Addcus.addActionListener(this::AddcusActionPerformed);

        Deletecus.setText("Delete");
        Deletecus.addActionListener(this::DeletecusActionPerformed);

        updatecus.setText("update");
        updatecus.addActionListener(this::updatecusActionPerformed);

        CustomerTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Name", "Phone", "Email"
            }
        ));
        jScrollPane2.setViewportView(CustomerTable);

        javax.swing.GroupLayout CustomersPanelLayout = new javax.swing.GroupLayout(CustomersPanel);
        CustomersPanel.setLayout(CustomersPanelLayout);
        CustomersPanelLayout.setHorizontalGroup(
            CustomersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CustomersPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CustomersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CustomersPanelLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                        .addComponent(CusPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(CustomersPanelLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(CusEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CustomersPanelLayout.createSequentialGroup()
                        .addGroup(CustomersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(CustomersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(CusId)
                            .addComponent(CusName, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE))))
                .addGap(395, 395, 395)
                .addGroup(CustomersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Addcus)
                    .addComponent(Deletecus)
                    .addComponent(updatecus))
                .addGap(353, 353, 353))
            .addComponent(jScrollPane2)
        );
        CustomersPanelLayout.setVerticalGroup(
            CustomersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CustomersPanelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(CustomersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(CusId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Addcus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(CustomersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(CusName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Deletecus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(CustomersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(CusPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updatecus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(CustomersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(CusEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 737, Short.MAX_VALUE))
        );

        RoomTap.addTab("Customers", CustomersPanel);

        GetCheckOuts.setText("view near check outs");
        GetCheckOuts.addActionListener(this::GetCheckOutsActionPerformed);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Reservation Id", "Room Id", "Customer Id", "Customer Name", "Check out date", "Days Until"
            }
        ));
        jScrollPane8.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(383, 383, 383)
                .addComponent(GetCheckOuts, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane8)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(GetCheckOuts)
                .addGap(55, 55, 55)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 749, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Near Checkouts", jPanel8);

        jLabel25.setText("Reservation Id");

        jLabel26.setText("Service");

        jButton2.setText("Add service to Reservation");
        jButton2.addActionListener(this::jButton2ActionPerformed);

        ReservationTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Room Id", "Customer Id", "Check in", "Check out", "Service"
            }
        ));
        jScrollPane7.setViewportView(ReservationTable);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(315, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel25)
                                .addGap(18, 18, 18)
                                .addComponent(ReservationID, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel26)
                                .addGap(173, 173, 173)
                                .addComponent(ServiceBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(352, 352, 352))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(402, 402, 402))))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ReservationID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25))
                .addGap(15, 15, 15)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ServiceBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 717, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Room Service", jPanel9);

        jLabel15.setText("Room Id");

        jLabel16.setText("Room type");

        jLabel17.setText("Price");

        jLabel18.setText("Is Busy");

        RoomTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Room Type", "Price", "Is Busy"
            }
        ));
        jScrollPane5.setViewportView(RoomTable);

        jButton1.setText("Add");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        DeleteRoom.setText("Delete");
        DeleteRoom.addActionListener(this::DeleteRoomActionPerformed);

        jButton3.setText("Update");
        jButton3.addActionListener(this::jButton3ActionPerformed);

        javax.swing.GroupLayout AddRoomPanelLayout = new javax.swing.GroupLayout(AddRoomPanel);
        AddRoomPanel.setLayout(AddRoomPanelLayout);
        AddRoomPanelLayout.setHorizontalGroup(
            AddRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddRoomPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AddRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AddRoomPanelLayout.createSequentialGroup()
                        .addGroup(AddRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(AddRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(IsBusy, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                            .addComponent(RoomPrice, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                            .addComponent(RoomID)
                            .addComponent(RoomType))
                        .addGap(377, 377, 377)
                        .addGroup(AddRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(AddRoomPanelLayout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(DeleteRoom))
                            .addComponent(jButton3))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane5)))
        );
        AddRoomPanelLayout.setVerticalGroup(
            AddRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddRoomPanelLayout.createSequentialGroup()
                .addGroup(AddRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AddRoomPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(AddRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(jButton1)
                            .addComponent(DeleteRoom))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AddRoomPanelLayout.createSequentialGroup()
                        .addComponent(RoomID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(AddRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(RoomType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(AddRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(RoomPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(AddRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(IsBusy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 738, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Add room", AddRoomPanel);

        CustomerId2.addActionListener(this::CustomerId2ActionPerformed);

        jLabel19.setText("Room Id");

        jLabel20.setText("Customer Id");

        jLabel21.setText("Check in (yyyy-mm-dd)");

        jLabel22.setText("check out (yyyy-mm-dd)");

        RoomTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Room Type", "Price", "Is Busy"
            }
        ));
        jScrollPane6.setViewportView(RoomTable2);

        jLabel23.setText("Filter by room type");

        jLabel24.setText("filter by Availabilitty");

        TypeFilter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All" }));

        BusyFilter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All" }));

        Applybtn.setText("Apply");
        Applybtn.addActionListener(this::ApplybtnActionPerformed);

        RoomToGuest.setText("Assing Room to Guest");
        RoomToGuest.addActionListener(this::RoomToGuestActionPerformed);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(0, 6, Short.MAX_VALUE)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 1076, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21)
                            .addComponent(jLabel22)
                            .addComponent(jLabel20)
                            .addComponent(jLabel19))
                        .addGap(36, 36, 36))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addComponent(Applybtn))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel23)
                                .addGap(18, 18, 18)
                                .addComponent(TypeFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel24)
                                .addGap(18, 18, 18)
                                .addComponent(BusyFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(RoomToGuest)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(RoomID2)
                        .addComponent(CustomerId2)
                        .addComponent(Checkin)
                        .addComponent(Checkout, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)))
                .addGap(53, 53, 53))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(RoomID2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel19)))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel23)
                                    .addComponent(TypeFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CustomerId2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(Checkin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel21))
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel24)
                                .addComponent(BusyFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Checkout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22))
                        .addGap(48, 48, 48))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(RoomToGuest)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(Applybtn)
                .addGap(36, 36, 36)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 628, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Reserve", jPanel7);

        jButton4.setText("Get Invoice");
        jButton4.addActionListener(this::jButton4ActionPerformed);

        jLabel27.setText("Reservation Id");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane9.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(308, 308, 308)
                .addComponent(jLabel27)
                .addGap(18, 18, 18)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(307, Short.MAX_VALUE))
            .addComponent(jScrollPane9)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4)
                .addGap(467, 467, 467))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27))
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addGap(16, 16, 16)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 749, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Invoice", jPanel10);

        javax.swing.GroupLayout RoomPanelLayout = new javax.swing.GroupLayout(RoomPanel);
        RoomPanel.setLayout(RoomPanelLayout);
        RoomPanelLayout.setHorizontalGroup(
            RoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );
        RoomPanelLayout.setVerticalGroup(
            RoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 889, Short.MAX_VALUE)
        );

        RoomTap.addTab("Room", RoomPanel);

        jLabel9.setText("Service Id");

        jLabel10.setText("Name");

        jLabel11.setText("Description");

        jLabel12.setText("Price");

        AddServicebtn.setText("Add");
        AddServicebtn.addActionListener(this::AddServicebtnActionPerformed);

        ServiceDelete.setText("Delete");
        ServiceDelete.addActionListener(this::ServiceDeleteActionPerformed);

        ServiceUpdate.setText("Update");
        ServiceUpdate.addActionListener(this::ServiceUpdateActionPerformed);

        jTableServices.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Service Id", "Name", "Description", "Price"
            }
        ));
        jScrollPane3.setViewportView(jTableServices);

        javax.swing.GroupLayout ServicesPanelLayout = new javax.swing.GroupLayout(ServicesPanel);
        ServicesPanel.setLayout(ServicesPanelLayout);
        ServicesPanelLayout.setHorizontalGroup(
            ServicesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ServicesPanelLayout.createSequentialGroup()
                .addGroup(ServicesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(ServicesPanelLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ServiceDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ServicesPanelLayout.createSequentialGroup()
                        .addGroup(ServicesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel12))
                        .addGap(18, 18, 18)
                        .addGroup(ServicesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ServiceId)
                            .addComponent(ServiceName)
                            .addComponent(ServicePrice, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 515, Short.MAX_VALUE)
                .addGroup(ServicesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AddServicebtn, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ServiceDelete, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ServiceUpdate, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(233, 233, 233))
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        ServicesPanelLayout.setVerticalGroup(
            ServicesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ServicesPanelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(ServicesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(ServiceId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddServicebtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ServicesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(ServiceName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ServiceDelete))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ServicesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(ServiceDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ServiceUpdate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ServicesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(ServicePrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 747, Short.MAX_VALUE))
        );

        RoomTap.addTab("Services", ServicesPanel);

        jLabel13.setText("username");

        jLabel14.setText("password");

        Adduser.setText("Add");
        Adduser.addActionListener(this::AdduserActionPerformed);

        UpdateUser.setText("Update");
        UpdateUser.addActionListener(this::UpdateUserActionPerformed);

        DeleteUser.setText("Delete");
        DeleteUser.addActionListener(this::DeleteUserActionPerformed);

        UserTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Username", "Password"
            }
        ));
        jScrollPane4.setViewportView(UserTable);

        javax.swing.GroupLayout AddUserPanelLayout = new javax.swing.GroupLayout(AddUserPanel);
        AddUserPanel.setLayout(AddUserPanelLayout);
        AddUserPanelLayout.setHorizontalGroup(
            AddUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddUserPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AddUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addGap(37, 37, 37)
                .addGroup(AddUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Username)
                    .addComponent(UserPass, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 350, Short.MAX_VALUE)
                .addComponent(DeleteUser)
                .addGap(36, 36, 36)
                .addGroup(AddUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Adduser)
                    .addComponent(UpdateUser))
                .addGap(263, 263, 263))
            .addComponent(jScrollPane4)
        );
        AddUserPanelLayout.setVerticalGroup(
            AddUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddUserPanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(AddUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(Username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Adduser)
                    .addComponent(DeleteUser))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(AddUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(UserPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UpdateUser))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 799, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        RoomTap.addTab("Add users", AddUserPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(RoomTap)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(RoomTap)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void AddEmployeeActionPerformed(java.awt.event.ActionEvent evt) {
        try{
            int id = Integer.parseInt(Eid.getText());
            String name = Ename.getText();
            String phone = Ephone.getText();
            String email = Empemail.getText();
            employees.put(id, new Employee(id, name, phone, email));
            userManager.saveEmployeesToFile();
            loadEmployeesToTable();
        }
            catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());

        }
        
    }                                           

    private void DeleteEmployeesActionPerformed(java.awt.event.ActionEvent evt) {
        Integer id = null;
        int row = EmployeeTable.getSelectedRow();
        if (row != -1) id = Integer.parseInt(EmployeeTable.getValueAt(row, 0).toString());
        else if (!Eid.getText().isEmpty()) id = Integer.parseInt(Eid.getText());
        if (id == null || !employees.containsKey(id)) return;
        int confirm = JOptionPane.showConfirmDialog(this,"Are you sure?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;
        employees.remove(id);
        userManager.saveEmployeesToFile();
        loadEmployeesToTable();
    }                                               

    private void UpdateEmployeeActionPerformed(java.awt.event.ActionEvent evt) {
        Integer id = null;
        int row = EmployeeTable.getSelectedRow();
        if (row != -1) id = Integer.parseInt(EmployeeTable.getValueAt(row, 0).toString());
        else if (!Eid.getText().isEmpty()) id = Integer.parseInt(Eid.getText());
        if (id == null || !employees.containsKey(id)) return;
        String name = Ename.getText();
        String phone = Ephone.getText();
        String email = Empemail.getText();
        if (name.isEmpty() || phone.isEmpty() || email.isEmpty()) return;
        employees.put(id, new Employee(id, name, phone, email));
        userManager.saveEmployeesToFile();
        loadEmployeesToTable();
    }                                              

    private void CusNameActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void CusIdActionPerformed(java.awt.event.ActionEvent evt) {
    }                                     

    private void AddcusActionPerformed(java.awt.event.ActionEvent evt) {
        try{
            int id = Integer.parseInt(CusId.getText());

            String name = CusName.getText();
            String phone = CusPhone.getText();
            String email = CusEmail.getText();
            customers.put(id, new Customers(id, name, phone, email));
            userManager.saveCustomersToFile();
            loadCustomersToTable();
        } catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }


    }                                      

    private void DeletecusActionPerformed(java.awt.event.ActionEvent evt) {
        Integer id = null;
        int row = CustomerTable.getSelectedRow();
        if (row != -1) id = Integer.parseInt(CustomerTable.getValueAt(row, 0).toString());
        else if (!CusId.getText().isEmpty()) id = Integer.parseInt(CusId.getText());
        if (id == null || !customers.containsKey(id)) return;
        int confirm = JOptionPane.showConfirmDialog(this,"Are you sure?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;
        customers.remove(id);
        userManager.saveCustomersToFile();


    loadCustomersToTable();
    }                                         

    private void updatecusActionPerformed(java.awt.event.ActionEvent evt)  {
            Integer id = null;
        int row = CustomerTable.getSelectedRow();
        if (row != -1) id = Integer.parseInt(CustomerTable.getValueAt(row, 0).toString());
        else if (!CusId.getText().isEmpty()) id = Integer.parseInt(CusId.getText());
        if (id == null || !customers.containsKey(id)) return;
        String name = CusName.getText();
        String phone = CusPhone.getText();
        String email = CusEmail.getText();
        if (name.isEmpty() || phone.isEmpty() || email.isEmpty()) return;
        customers.put(id, new Customers(id, name, phone, email));
        userManager.saveCustomersToFile();
        loadCustomersToTable();
    }                                         

    private void AddServicebtnActionPerformed(java.awt.event.ActionEvent evt) {
        try{int id = Integer.parseInt(ServiceId.getText());
        String name = ServiceName.getText();
        String desc = ServiceDescription.getText();
        double price = Double.parseDouble(ServicePrice.getText());
        services.put(id, new Service(id, name, desc, price));
        roomManger.saveServicesToFile();
        loadServicesToTable();} catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }                                             

    private void ServiceDeleteActionPerformed(java.awt.event.ActionEvent evt) {
           Integer id = null;
        int row = jTableServices.getSelectedRow();
        if (row != -1) id = Integer.parseInt(jTableServices.getValueAt(row, 0).toString());
        else if (!ServiceId.getText().isEmpty()) id = Integer.parseInt(ServiceId.getText());
        if (id == null || !services.containsKey(id)) return;
        int confirm = JOptionPane.showConfirmDialog(this,"Are you sure?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;
        services.remove(id);
        roomManger.saveServicesToFile();
        loadServicesToTable();
    }                                             

    private void ServiceUpdateActionPerformed(java.awt.event.ActionEvent evt) {
                Integer id = null;
        int row = jTableServices.getSelectedRow();
        if (row != -1) id = Integer.parseInt(jTableServices.getValueAt(row, 0).toString());
        else if (!ServiceId.getText().isEmpty()) id = Integer.parseInt(ServiceId.getText());
        if (id == null || !services.containsKey(id)) return;
        String name = ServiceName.getText();
        String desc = ServiceDescription.getText();
        double price = Double.parseDouble(ServicePrice.getText());
        services.put(id, new Service(id, name, desc, price));
        roomManger.saveServicesToFile();
        loadServicesToTable();
    }                                             

    private void AdduserActionPerformed(java.awt.event.ActionEvent evt) {
        String uname = Username.getText();
        String pass = UserPass.getText();

        if (uname.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required");
            return;
        }

        boolean added = username.addUser(uname, pass); // use CheckLogin method
        if (!added) {
            JOptionPane.showMessageDialog(this, "Username already exists");
            return;
    }

    loadUsersToTable(); // refresh JTable
    JOptionPane.showMessageDialog(this, "User added successfully");
    }                                       

    private void DeleteUserActionPerformed(java.awt.event.ActionEvent evt) {
         int row = UserTable.getSelectedRow();
    if (row == -1) return;

    String uname = UserTable.getValueAt(row, 0).toString();

   

    username.getUserPassList().removeIf(u -> u.getUsername().equals(uname));
    username.SaveUsers();
    loadUsersToTable();
    }                                          

    private void UpdateUserActionPerformed(java.awt.event.ActionEvent evt) {
                int row = UserTable.getSelectedRow();
        if (row == -1) return;

        String oldUname = UserTable.getValueAt(row, 0).toString();
        String newUname = Username.getText();
        String newPass = UserPass.getText();

        if (newUname.isEmpty() || newPass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required");
            return;
        }

        for (Login login : username.getUserPassList()) {
            if (login.getUsername().equals(oldUname)) {
                login.setUsername(newUname);
                login.setPassword(newPass);
                break;
            }
        }

        username.SaveUsers();
        loadUsersToTable();
    }                                          

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        try {
        int id = Integer.parseInt(RoomID.getText());
        String type = RoomType.getText();
        double price = Double.parseDouble(RoomPrice.getText());
        boolean isBusy = Boolean.parseBoolean(IsBusy.getText());

        if (type.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Room type is required");
            return;
        }

        rooms.put(id, new Room(id, type, price, isBusy));
        roomManger.saveRoomsToFile(); 
        loadRoomsToTable();          
        JOptionPane.showMessageDialog(this, "Room added successfully");
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Invalid input!");
    }
    }                                        

    private void DeleteRoomActionPerformed(java.awt.event.ActionEvent evt) {
             int row = RoomTable.getSelectedRow();
        if (row == -1) return;

        int id = Integer.parseInt(RoomTable.getValueAt(row, 0).toString());

        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;

        rooms.remove(id);
        roomManger.saveRoomsToFile();
        loadRoomsToTable();
    }                                          

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        int row = RoomTable.getSelectedRow();
        if (row == -1) return;

        try {
            int id = Integer.parseInt(RoomTable.getValueAt(row, 0).toString());
            String type = RoomType.getText();
            double price = Double.parseDouble(RoomPrice.getText());
            boolean isBusy = Boolean.parseBoolean(IsBusy.getText());

            if (type.isEmpty()) return;

            rooms.put(id, new Room(id, type, price, isBusy));
            roomManger.saveRoomsToFile();
            loadRoomsToTable();
            JOptionPane.showMessageDialog(this, "Room updated successfully");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input!");
        }
    }                                        

    private void CustomerId2ActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void RoomToGuestActionPerformed(java.awt.event.ActionEvent evt) {
        try {
        int roomId = Integer.parseInt(RoomID2.getText()); 
        int customerId = Integer.parseInt(CustomerId2.getText());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate checkIn;
        LocalDate checkOut;
        try {
            checkIn = LocalDate.parse(Checkin.getText().trim(), formatter);
            checkOut = LocalDate.parse(Checkout.getText().trim(), formatter);
        } catch (java.time.format.DateTimeParseException e) {
            JOptionPane.showMessageDialog(this,
                    "Invalid date format. Please use yyyy-MM-dd (e.g. 2025-01-31).",
                    "Invalid Date",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        Customers customer = customers.get(customerId);
        if (customer == null) {
            JOptionPane.showMessageDialog(this, "Customer not found");
            return;
        }

        boolean success = roomManger.assignRoomToGuest(roomId, customer, checkIn, checkOut);

        if (success) {
            JOptionPane.showMessageDialog(this, "Room assigned successfully!");
            loadRoomsToTable(); 
            
        } else {
            JOptionPane.showMessageDialog(this, "Room is not available or invalid!");
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Invalid input: " + e.getMessage());
    }
    }

    private void ApplybtnActionPerformed(java.awt.event.ActionEvent evt) {
         String roomType = TypeFilter.getSelectedItem().toString();
        if(roomType.equals("All")) roomType = null;

        String status = BusyFilter.getSelectedItem().toString();
        Boolean isBusy = null;
        if(status.equals("Available")) isBusy = false;
        else if(status.equals("Busy")) isBusy = true;

        ArrayList<Room> filteredRooms = roomManger.filterRooms(roomType, isBusy, null);

        DefaultTableModel model = (DefaultTableModel) RoomTable2.getModel();
        model.setRowCount(0);
        for(Room r : filteredRooms) {
            model.addRow(new Object[]{r.getRoomId(), r.getRoomType(), r.getPrice(), r.isBusy()});
        }

    }

    private void backActionPerformed(java.awt.event.ActionEvent evt) {
        LoginGui bac = new LoginGui();
        bac.setVisible(true);
        this.dispose();
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        try {

            int resId = Integer.parseInt(ReservationID.getText());
            Reservation res = roomManger.getReservations().get(resId);

            if (res == null) {
                JOptionPane.showMessageDialog(this, "Reservation not found");
                return;
            }


            String selectedName = (String) ServiceBox.getSelectedItem();
            if (selectedName == null || selectedName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Select a service");
                return;
            }

            Service selectedService = null;
            for (Service s : services.values()) {
                if (selectedName.equals(s.getName())) {
                    selectedService = s;
                    break;
                }
            }
            if (selectedService == null) {
                JOptionPane.showMessageDialog(this, "Selected service not found");
                return;
            }


            if (!res.getServices().contains(selectedService)) {
                res.getServices().add(selectedService);
                roomManger.updateReservation(resId, res);
            } else {
                JOptionPane.showMessageDialog(this, "Service already assigned");
            }


            loadReservationsToTable();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Enter a valid Reservation ID");
        }
        
    }                                        

    private void GetCheckOutsActionPerformed(java.awt.event.ActionEvent evt) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        LocalDate now = LocalDate.now();



        for (Reservation r : roomManger.getReservations().values()) {
            int reservationId = r.getReservationId();
            int roomId = r.getRoom().getRoomId();
            int customerId = r.getCustomer().getId();
            String customerName = r.getCustomer().getName();
            LocalDate checkOutDate = r.getCheckOut();
            LocalDate CheckInDate = r.getCheckIn();
            long daysUntil = java.time.temporal.ChronoUnit.DAYS.between( now,checkOutDate);

            model.addRow(new Object[]{
                reservationId,
                roomId,
                customerId,
                customerName,
                checkOutDate,
                daysUntil
            });
    }
    }                                            

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
                                                  
    
    int resId;
    try {
        resId = Integer.parseInt(jTextField1.getText());
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Invalid Reservation ID");
        return;
    }

    Reservation r = roomManger.getReservations().get(resId);
    if (r == null) {
        JOptionPane.showMessageDialog(this, "Reservation not found");
        return;
    }

    long days = java.time.temporal.ChronoUnit.DAYS.between(
            r.getCheckIn(), r.getCheckOut()
    );

    double roomPrice = r.getRoom().getPrice();
    double roomTotal = roomPrice * days;

    double servicesTotal = 0;
    if (r.getServices() != null) {
        for (Service s : r.getServices()) {
            servicesTotal += s.getPrice();
        }
    }

    double total = roomTotal + servicesTotal;

    String invoice =
            "Invoice\n" +
            "-----------------------\n" +
            "Reservation ID: " + r.getReservationId() + "\n" +
            "Customer: " + r.getCustomer().getName() + "\n" +
            "Room ID: " + r.getRoom().getRoomId() + "\n" +
            "Days: " + days + "\n" +
            "Room Total: " + roomTotal + "\n" +
            "Services Total: " + servicesTotal + "\n" +
            "-----------------------\n" +
            "TOTAL: " + total;

    JOptionPane.showMessageDialog(this, invoice);


    }                                        


    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddEmployee;
    private javax.swing.JPanel AddRoomPanel;
    private javax.swing.JButton AddServicebtn;
    private javax.swing.JPanel AddUserPanel;
    private javax.swing.JButton Addcus;
    private javax.swing.JButton Adduser;
    private javax.swing.JButton Applybtn;
    private javax.swing.JComboBox<String> BusyFilter;
    private javax.swing.JTextField Checkin;
    private javax.swing.JTextField Checkout;
    private javax.swing.JTextField CusEmail;
    private javax.swing.JTextField CusId;
    private javax.swing.JTextField CusName;
    private javax.swing.JTextField CusPhone;
    private javax.swing.JTextField CustomerId2;
    private javax.swing.JTable CustomerTable;
    private javax.swing.JPanel CustomersPanel;
    private javax.swing.JButton DeleteEmployees;
    private javax.swing.JButton DeleteRoom;
    private javax.swing.JButton DeleteUser;
    private javax.swing.JButton Deletecus;
    private javax.swing.JTextField Eid;
    private javax.swing.JTextField Empemail;
    private javax.swing.JTable EmployeeTable;
    private javax.swing.JPanel EmployeesPanel;
    private javax.swing.JTextField Ename;
    private javax.swing.JTextField Ephone;
    private javax.swing.JButton GetCheckOuts;
    private javax.swing.JTextField IsBusy;
    private javax.swing.JTextField ReservationID;
    private javax.swing.JTable ReservationTable;
    private javax.swing.JTextField RoomID;
    private javax.swing.JTextField RoomID2;
    private javax.swing.JPanel RoomPanel;
    private javax.swing.JTextField RoomPrice;
    private javax.swing.JTable RoomTable;
    private javax.swing.JTable RoomTable2;
    private javax.swing.JTabbedPane RoomTap;
    private javax.swing.JButton RoomToGuest;
    private javax.swing.JTextField RoomType;
    private javax.swing.JComboBox<String> ServiceBox;
    private javax.swing.JButton ServiceDelete;
    private javax.swing.JTextField ServiceDescription;
    private javax.swing.JTextField ServiceId;
    private javax.swing.JTextField ServiceName;
    private javax.swing.JTextField ServicePrice;
    private javax.swing.JButton ServiceUpdate;
    private javax.swing.JPanel ServicesPanel;
    private javax.swing.JComboBox<String> TypeFilter;
    private javax.swing.JButton UpdateEmployee;
    private javax.swing.JButton UpdateUser;
    private javax.swing.JTextField UserPass;
    private javax.swing.JTable UserTable;
    private javax.swing.JTextField Username;
    private javax.swing.JButton back;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTableServices;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton updatecus;
    // End of variables declaration//GEN-END:variables
}
