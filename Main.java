import java.awt.Color;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class Main implements ActionListener {

    private static JFrame frame;
    private static JLabel Username_Label;
    private static JLabel Password_Label;
    private static JTextField User_Text_Field;
    private static JPasswordField Password_password_Field;
    private static JLabel Success_Label;
    private static JButton Login_Button;
    private static JButton Register_Button;

    private static Connection connection;  // class-level connection

    public static void main(String[] args) {
        try {
            // Load driver + connect
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/reg", "root", "Vinodhar12@");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database Connection Failed!");
            return;
        }
        
            
        // UI setup
        Username_Label = new JLabel("Username");
        Username_Label.setBounds(100, 200, 80, 25);
        Password_Label = new JLabel("Password");
        Password_Label.setBounds(100, 250, 80, 25);
        Success_Label = new JLabel("");
        Success_Label.setBounds(200, 350, 200, 25);

        User_Text_Field = new JTextField();
        User_Text_Field.setBounds(200, 200, 150, 25);
        Color myColor3 = new Color(218,204,175);  
        User_Text_Field.setBackground(myColor3);
        Password_password_Field = new JPasswordField();
        Password_password_Field.setBounds(200, 250, 150, 25);
        Color myColor4 = new Color(218,204,175);  
        Password_password_Field.setBackground(myColor4);

        Login_Button = new JButton("Login");
        Login_Button.setBounds(200, 300, 125, 25);
        Login_Button.addActionListener(new Main());
        Color myColor1 = new Color(204,180,155);  
        Login_Button.setBackground(myColor1);

        Register_Button = new JButton("REGISTER");
        Register_Button.setBounds(300, 30, 125, 25);
        Register_Button.addActionListener(e -> new Register_window());
        Color myColor2 = new Color(204,180,155);  
        Register_Button.setBackground(myColor2);


        JPanel panel = new JPanel();
        Color myColor = new Color(228,220,192);  
        panel.setBackground(myColor);
        panel.setLayout(null);
        panel.add(Username_Label);
        panel.add(User_Text_Field);
        panel.add(Password_Label);
        panel.add(Password_password_Field);
        panel.add(Login_Button);
        panel.add(Success_Label);
        panel.add(Register_Button);

        frame = new JFrame();
        frame.setTitle("Login Page");
        frame.setSize(500, 500);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String inputUsername = User_Text_Field.getText().trim();
        String inputPassword = new String(Password_password_Field.getPassword()).trim();

        try {
            Statement st = connection.createStatement();
            ResultSet re = st.executeQuery("SELECT username, password FROM registration");

            boolean found = false;
            while (re.next()) {
                String dbUsername = re.getString("username").trim();
                String dbPassword = re.getString("password").trim();

                if (inputUsername.equals(dbUsername) && inputPassword.equals(dbPassword)) {
                    found = true;
                    Success_Label.setText("Login Successful!");
                    new Details_Window(); // open next window
                    frame.dispose();
                    break;
                }
            }

            if (!found) {
                Success_Label.setText("Invalid Username/Password");
            }

            re.close();
            st.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error while checking login.");
        }
    }
}

class Register_window extends JFrame{
    private static JTextField Name_user_field, Age_user_field, Emailid_user_field, PhoneNumber_user_field, Username_user_field;
    private static JTextArea Address_user_area;
    private static JRadioButton Gender_user_Male, Gender_user_Female;
    private static JPasswordField Password_user_field;

    public Register_window(){
        JLabel Name_user=new JLabel("Name");
        Name_user.setBounds(50,50,125,20);
        JLabel Address_user=new JLabel("Address");
        Address_user.setBounds(50,100,125,25);
        JLabel Gender_user=new JLabel("Gender"); 
        Gender_user.setBounds(50,200,125,25);
        JLabel Age_user=new JLabel("Age");
        Age_user.setBounds(50,250,125,25);
        JLabel Emailid_user=new JLabel("Email id");
        Emailid_user.setBounds(50,300,125,25);
        JLabel PhoneNumber_user=new JLabel("Phone Number");
        PhoneNumber_user.setBounds(50,350,125,25);
        JLabel Username_user=new JLabel("Username");
        Username_user.setBounds(50,400,125,25);
        JLabel Password_user=new JLabel("Password");
        Password_user.setBounds(50,450,125,25);


        Name_user_field =new JTextField();
        Name_user_field.setBounds(200,50,125,25);
        Color myColor8 = new Color(202,225,240);  
        Name_user_field.setBackground(myColor8);
        Address_user_area=new JTextArea();
        Address_user_area.setBounds(200,100,125,75);
        Color myColor7 = new Color(202,225,240);  
        Address_user_area.setBackground(myColor7);
        Gender_user_Male=new JRadioButton("Male");
        Gender_user_Male.setBounds(200,200,125,25);
        Gender_user_Female=new JRadioButton("Female");
        Gender_user_Female.setBounds(350,200,125,25);
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(Gender_user_Male);
        genderGroup.add(Gender_user_Female);
        Age_user_field =new JTextField();
        Age_user_field.setBounds(200,250,125,25);
        Color myColor2 = new Color(202,225,240);  
        Age_user_field.setBackground(myColor2);
        Emailid_user_field =new JTextField();
        Emailid_user_field.setBounds(200,300,200,25);
        Color myColor3 = new Color(202,225,240);  
        Emailid_user_field.setBackground(myColor3);
        PhoneNumber_user_field =new JTextField();
        PhoneNumber_user_field.setBounds(200,350,120,25);
        Color myColor4 = new Color(202,225,240);  
        PhoneNumber_user_field.setBackground(myColor4);
        Username_user_field =new JTextField();
        Username_user_field.setBounds(200,400,200,25);
        Color myColor5 = new Color(202,225,240);  
        Username_user_field.setBackground(myColor5);
        Password_user_field=new JPasswordField();
        Password_user_field.setBounds(200,450,125,25);
        Color myColor6 = new Color(202,225,240);  
        Password_user_field.setBackground(myColor6);


        JButton Submit_user=new JButton("SUBMIT");
        Submit_user.setBounds(170,550,125,25);
        Submit_user.addActionListener(e -> Registration());
        Color myColor1 = new Color(180,201,230);  
        Submit_user.setBackground(myColor1);


        

        JPanel panel1=new JPanel();
        Color myColor = new Color(218,239,247);  
        panel1.setBackground(myColor);
        panel1.setLayout(null);
        panel1.add(Name_user);
        panel1.add(Address_user);
        panel1.add(Gender_user);
        panel1.add(Age_user);
        panel1.add(Emailid_user);
        panel1.add(PhoneNumber_user);
        panel1.add(Username_user);
        panel1.add(Password_user);
        panel1.add(Name_user_field);
        panel1.add(Address_user_area);
        panel1.add(Gender_user_Male);
        panel1.add(Gender_user_Female);
        panel1.add(Age_user_field);
        panel1.add(Emailid_user_field);
        panel1.add(PhoneNumber_user_field);
        panel1.add(Username_user_field);
        panel1.add(Password_user_field);
        panel1.add(Submit_user);
        

        JFrame frame1=new JFrame();
        frame1.setVisible(true); //sets the frame to be visible
        frame1.setTitle("Registration  Page"); //sets a Title fro the Frame
        frame1.setSize(500,700); //sets the Size of the Frame
        frame1.setResizable(false);//prevents the frame from being resized
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exits out of the application
        frame1.add(panel1);

    }

    static void Registration() {

            String name = Name_user_field.getText().trim(); 
            if(name.matches("[a-zA-Z\\s]+")) {
                System.out.println("Valid name: " + name);
            } else {
                JOptionPane.showMessageDialog(null, "Name should not contain numbers or special characters!");
            }

            String address = Address_user_area.getText();
            String gender = "";
            if (Gender_user_Male.isSelected()) {
                gender = "Male";
            } else if (Gender_user_Female.isSelected()) {
                gender = "Female";
            }   

            int age;
            try {
                age = Integer.parseInt(Age_user_field.getText());
                if (age <= 0 || age > 100) {
                    JOptionPane.showMessageDialog(null, "Invalid Age! Age must be between 1 and 100.");
                    return; // stop execution
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Age must be a number!");
                return;
            }
            String email_id = Emailid_user_field.getText().trim();
            if (!email_id.matches("[a-zA-Z]+[a-zA-Z0-9._]*@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
                JOptionPane.showMessageDialog(null, 
                    "Invalid Email! Email must contain letters before '@' and be in valid format.");
                return;
            }
            String phone = PhoneNumber_user_field.getText().trim();
            if (!phone.matches("\\d{10}")) {  
                JOptionPane.showMessageDialog(null, "Invalid Phone Number! Must be exactly 10 digits.");
                return;
            }

            String username = Username_user_field.getText();
            String password = new String(Password_user_field.getPassword());
            

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/reg", "root", "Vinodhar12@");
                String sql = "INSERT INTO registration (username, name, address, gender, age, email_id, phone, password) " + "VALUES (?,?,?,?,?,?,?,?)";

                PreparedStatement pstmt = connection.prepareStatement(sql);
                pstmt.setString(1, username);
                pstmt.setString(2, name);
                pstmt.setString(3, address);
                pstmt.setString(4, gender);
                pstmt.setInt(5, age);
                pstmt.setString(6, email_id);
                pstmt.setString(7, phone);
                pstmt.setString(8, password);

                int rows = pstmt.executeUpdate();
                if (rows > 0) {
                    System.out.println(" Registration successful!");
                }

                

            } catch (Exception e) {
            e.printStackTrace();
            }
        
    }
}

class Details_Window extends JFrame{
    private static JComboBox Budget_Combo, Activities_combo, Grouptype_combo, Hours_Combo;
    private static Connection connection;
    private static JLabel Success_Label;
    public Details_Window(){
        JLabel Budget_Range=new JLabel("Budget range per person");
        Budget_Range.setBounds(100,75,150,20);
        JLabel Activities=new JLabel("Activities");
        Activities.setBounds(100,150,150,20);
        JLabel Grouptype=new JLabel("Grouptype");
        Grouptype.setBounds(100,225,150,20);
        JLabel SpendingHours=new JLabel("Spending Hours");
        SpendingHours.setBounds(100,300,150,20);
        Success_Label = new JLabel("");
        Success_Label.setBounds(200, 400, 200, 75);


        String[] bud={"0-100","100-300","300-500","500-700","700-900","1000-2000","2000-3000","3000+"};
        Budget_Combo=new JComboBox<>(bud);
        Budget_Combo.setBounds(250,75,150,25);
        Color myColor2 = new Color(223,208,240);  
        Budget_Combo.setBackground(myColor2);
        String[] act={"Adventure activities","Cultural activities","Relaxing activities","Nightlife","Beach","Mountains","House of Worship","Shopping Area","Others"};
        Activities_combo=new JComboBox<>(act);
        Activities_combo.setBounds(250,150,150,25);
        Color myColor3 = new Color(223,208,240);  
        Activities_combo.setBackground(myColor3);
        String[] type={"Solo","Couple","Family","Friends","Others"};
        Grouptype_combo=new JComboBox<>(type);
        Grouptype_combo.setBounds(250,225,150,25);
        Color myColor4 = new Color(223,208,240);  
        Grouptype_combo.setBackground(myColor4);
        String[] hou={"1-3","3-5","5-7","7-9","9-11","12+"};
        Hours_Combo=new JComboBox<>(hou);
        Hours_Combo.setBounds(250,300,150,25);
        Color myColor5 = new Color(223,208,240);  
        Hours_Combo.setBackground(myColor5);

        JButton Submit_details=new JButton("SUBMIT");
        Submit_details.setBounds(170,350,125,25);
        Submit_details.addActionListener(e -> compare());
        Color myColor1 = new Color(205,180,219);  
        Submit_details.setBackground(myColor1);
        

        JPanel panel1=new JPanel();
        Color myColor = new Color(238,230,255);  
        panel1.setBackground(myColor);
        panel1.setLayout(null);
        panel1.add(Budget_Range);
        panel1.add(Budget_Combo);
        panel1.add(Activities);
        panel1.add(Activities_combo);
        panel1.add(Grouptype);
        panel1.add(Grouptype_combo);
        panel1.add(SpendingHours);
        panel1.add(Hours_Combo);
        panel1.add(Submit_details);
        panel1.add(Success_Label);


        setTitle("Details Window");
        setSize(500,500);
        setVisible(true);
        setResizable(false); //prevents the frame from being resized
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        add(panel1);
    }
    void compare() {
        String Budget_Selected = (String) Budget_Combo.getSelectedItem();
        String Category_Selected = (String) Activities_combo.getSelectedItem();
        String Spending_Selected = (String) Hours_Combo.getSelectedItem();

        ArrayList<String> matchedPlaces = new ArrayList<>();

        
        try {
            // Load driver + connect
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Driver not found!");
            return;
        }

        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/reg", "root", "Vinodhar12@");
            Statement st = connection.createStatement();
            ResultSet re = st.executeQuery("SELECT name,category, budget, hoursspending FROM viewplaces")) {

        
            while (re.next()) {
                String dbcategory = re.getString("category");
                String dbbudget = re.getString("budget");
                String dbhours = re.getString("hoursspending");
                String dbplace = re.getString("name");

                if (Budget_Selected.equals(dbbudget)
                        && Category_Selected.equals(dbcategory)
                        && Spending_Selected.equals(dbhours)) {
                    
                    matchedPlaces.add(dbplace); 
                }
            }
            
            
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error while checking places.");
            return;
        }
        if (!matchedPlaces.isEmpty()) {
            Success_Label.setText("Places found");
            new List_Of_Places_Window(matchedPlaces);
            dispose();
        } else {
            Success_Label.setText("Places not found");
        }

    }

}


 class List_Of_Places_Window extends JFrame {
    public List_Of_Places_Window(List<String> places) {
        setTitle("Select a Place");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(232,244,234));

        JList<String> placeList = new JList<>(places.toArray(new String[0]));
        JScrollPane scrollPane = new JScrollPane(placeList);
        scrollPane.setBounds(50, 50, 200, 150);
        Color myColor = new Color(211,232,215);  
        scrollPane.setBackground(myColor);

        JButton selectButton = new JButton("Select");
        selectButton.setBounds(100, 220, 100, 30);
        selectButton.addActionListener(e -> {
            String selectedPlace = placeList.getSelectedValue();
            if (selectedPlace != null) {
                new View_Place_Details(selectedPlace);
            } else {
                JOptionPane.showMessageDialog(this, "Please select a place!");
            }
        });
        Color myColor1 = new Color(184,216,190);  
        selectButton.setBackground(myColor1);


        

        add(scrollPane);
        add(selectButton);

        setVisible(true);
    }
}
class View_Place_Details extends JFrame{
    public View_Place_Details(String placeName){
        setTitle("Place Details");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(255, 182, 193));

        JLabel labelTitle = new JLabel("Details for: " + placeName);
        labelTitle.setBounds(50, 20, 400, 30);
        add(labelTitle);

        JTextArea infoArea = new JTextArea();
        infoArea.setBounds(50, 70, 800, 400);
        infoArea.setEditable(false);
        add(infoArea);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/reg", "root", "Vinodhar12@");

            String query = "SELECT * FROM viewplaces WHERE name = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, placeName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                String getname=rs.getString("name");
                String getcategory=rs.getString("category");
                String getlocation=rs.getString("location");
                String getabout=rs.getString("about");
                String gettimings=rs.getString("timings");
                String getbudget=rs.getString("budget");
                String getactivities=rs.getString("activities");
                String gethoursspending=rs.getString("hoursspending");

                infoArea.setText("Name             "+getname+"\n"+
                                 "Category         "+getcategory+"\n"+
                                 "Location         "+getlocation+"\n"+
                                 "About Place      "+getabout+"\n"+
                                 "Timings          "+gettimings+"\n"+
                                 "Budget           "+getbudget+"\n"+
                                 "Activities       "+getactivities+"\n"+
                                 "Hours Spending   "+gethoursspending
                );
            }else {
                infoArea.setText("No details found for this place.");
            }
            rs.close();
            ps.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching details!");
        }

        setVisible(true);
    }
}