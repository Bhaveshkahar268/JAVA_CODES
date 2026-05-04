import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.*;

import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import javax.swing.table.*;
import javax.swing.event.*;
import java.awt.*;
import java.text.*;
import java.awt.event.*;

class City
{
private int code;
private String name;
public City()
{
}
public void setCode(int code)
{
this.code=code;
}
public int getCode()
{
return this.code;
}
public void setName(String name)
{
this.name=name;
}
public String getName()
{
return this.name;
}
}
class CityModel
{
private java.util.List<City> cities= new ArrayList<>();
public java.util.List<City> getCities()
{
//you write implementation
try
{
Class.forName("com.mysql.cj.jdbc.Driver");
Connection connection;
connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_data","luffy","2608");
Statement statement;
statement=connection.createStatement();
ResultSet resultSet;
int Scode;
String Sname;
resultSet=statement.executeQuery("select code,name from city");
while(resultSet.next())
{
City city=new City();

Scode=resultSet.getInt("code");
Sname=resultSet.getString("name");
city.setCode(Scode);
city.setName(Sname);
cities.add(city);
}
resultSet.close();
statement.close();
connection.close();
}catch(Exception e)
{
}
return cities;
} 
}
class Employee
{
private String empId;
private String name;
private java.sql.Date dateOfBirth;
private String cityName;
private char gender;
private int salary;
public Employee()
{
this.empId="";
this.name="";
this.dateOfBirth=null;
this.cityName="";
this.gender=(char)0;
this.salary=0;
}
public void setEmpId(String empId)
{
this.empId=empId;
}
public String getEmpId()
{
return this.empId;
}
public void setName(String name)
{
this.name=name;
}
public String getName()
{
return this.name;
}
public void setDateOfBirth(java.sql.Date dateOfBirth)
{
this.dateOfBirth=dateOfBirth;
}
public java.sql.Date getDateOfBirth()
{
return this.dateOfBirth;
}
public void setCityName(String cityName)
{
this.cityName=cityName;
}
public String getCityName()
{
return this.cityName;
}
public void setGender(char gender)
{
this.gender=gender;
}
public char getGender()
{
return this.gender;
}
public void setSalary(int salary)
{
this.salary=salary;
}
public int getSalary()
{
return this.salary;
}
}


class ModelException extends Exception
{
public ModelException(String Message)
{
super(Message);
}
}


class EmployeeModel extends AbstractTableModel
{
private java.util.List<Employee> employees;
private String [] titles;
public EmployeeModel()
{
this.populateEmployees();
}
public void populateEmployees()
{
this.titles=new String[3];
titles[0]="S.No";
titles[1]="Id";
titles[2]="Name";

employees=new ArrayList<Employee>();
try
{
Class.forName("com.mysql.cj.jdbc.Driver");
Connection connection;
connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_data","luffy","2608");
Statement statement;
statement=connection.createStatement();
ResultSet resultSet;
resultSet=statement.executeQuery(" select employee.emp_id,employee.name,employee.date_of_birth,city.name as city_name,employee.gender,employee.salary from employee inner join city on employee.city_code=city.code order by employee.name");
Employee e;
while(resultSet.next())
{
e=new Employee();
e.setEmpId(resultSet.getString("emp_id").trim());
e.setName(resultSet.getString("name").trim());
e.setDateOfBirth(resultSet.getDate("date_of_birth"));
e.setCityName(resultSet.getString("city_name").trim());
e.setGender(resultSet.getString("gender").charAt(0));
e.setSalary(resultSet.getInt("salary"));
employees.add(e);
}
resultSet.close();
statement.close();
connection.close();
}catch(Exception e)
{
System.out.println(e);
System.exit(0); //Application ends
}
}

public Employee getEmployee(int index) 
{
if(index<0 || index>=this.employees.size()) return null;
return this.employees.get(index);
}


public int getRowCount()
{
return this.employees.size();
}
public int getColumnCount()
{
return this.titles.length;
}
public String getColumnName(int columnIndex)
{
return this.titles[columnIndex];
}
public boolean isCellEditable(int rowIndex,int columnIndex)
{
return false;
}
public Object getValueAt(int rowIndex,int columnIndex)
{
if(columnIndex==0) return rowIndex+1;
else if(columnIndex==1) return employees.get(rowIndex).getEmpId();
else return employees.get(rowIndex).getName();
}


public Class getColumnClass(int columnIndex)
{
if(columnIndex==0) return Integer.class;
else if(columnIndex==1) return String.class;
else return String.class;
}

public int addEmployee(Employee employee) throws ModelException
{
try{
Employee e;
for(int i=0;i<employees.size();i++)
{
e=employees.get(i);
if(e.getEmpId().equalsIgnoreCase(employee.getEmpId())) throw new ModelException("Employee Id."+employee.getEmpId()+"exists.");
}
Class.forName("com.mysql.cj.jdbc.Driver");
Connection connection;
connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_data","luffy","2608");
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select * from city where name=?");
preparedStatement.setString(1,employee.getCityName());
ResultSet resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new ModelException("Invalid City : "+employee.getCityName());
}

int cityCode=resultSet.getInt("code");

resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("insert into employee values (?,?,?,?,?,?)");
preparedStatement.setString(1,employee.getEmpId());
preparedStatement.setString(2,employee.getName());
preparedStatement.setDate(3,employee.getDateOfBirth());
preparedStatement.setInt(4,cityCode);
preparedStatement.setString(5,String.valueOf(employee.getGender()));
preparedStatement.setInt(6,employee.getSalary());

preparedStatement.executeUpdate();
preparedStatement.close();
connection.close();
int x=0;
for(Employee emp : employees)
{
if(emp.getName().compareTo(employee.getName())>0)
{
employees.add(x,employee);
return x;
}
x++;
}
employees.add(employee);
return x;
}catch(SQLException sqlException)
{
throw new ModelException(sqlException.getMessage());
}
catch(ClassNotFoundException classNotFoundException)
{
throw new ModelException(classNotFoundException.getMessage());
}
}


public int updateEmployee(Employee employee) throws ModelException
{
try
{
int removeAtIndex;
EmployeeModel employeeModel =new EmployeeModel();
removeAtIndex=employeeModel.getEmployeeIndex(employee.getEmpId());
employees.remove(removeAtIndex);
Class.forName("com.mysql.cj.jdbc.Driver");
Connection connection;
connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_data","luffy","2608");
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select * from city where name=?");
preparedStatement.setString(1,employee.getCityName());
ResultSet resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new ModelException("Invalid City : "+ employee.getCityName());
}

int cityCode=resultSet.getInt("code");
System.out.println("done done");
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("update employee set name=? ,date_of_birth=? ,city_code=? ,gender=? ,salary=? where emp_id=?");
preparedStatement.setString(1,employee.getName());
preparedStatement.setDate(2,employee.getDateOfBirth());
preparedStatement.setInt(3,cityCode);
preparedStatement.setString(4,String.valueOf(employee.getGender()));
preparedStatement.setInt(5,employee.getSalary());
preparedStatement.setString(6,employee.getEmpId());
preparedStatement.executeUpdate();
preparedStatement.close();
connection.close();
int x=0;
for(Employee emp : employees)
{
if(emp.getName().compareTo(employee.getName())>0)
{
employees.add(x,employee);
return x;
}
x++;
}
employees.add(employee);
return x;
}catch(SQLException sqlException)
{
throw new ModelException(sqlException.getMessage());
}
catch(ClassNotFoundException classNotFoundException)
{
throw new ModelException(classNotFoundException.getMessage());
}
}



public int getIndexOfEmployeeWhoseEmpIdStartsBy(String empId)
{
if(empId==null) return -1;
int index=0;
for(Employee emp:employees)
{
if(emp.getEmpId().toUpperCase().startsWith(empId.toUpperCase()))
{
return index;
}
index++;
}
return -1;
}

public int getIndexOfEmployeeWhoseNameStartsBy(String name)
{
if(name==null) return -1;
int index=0;
for(Employee emp:employees)
{
if(emp.getName().toUpperCase().startsWith(name.toUpperCase()))
{
return index;
}
index++;
}
return -1;
}



public int getEmployeeIndex(String empId)
{
int x=0;
for(Employee emp:employees)
{
if(empId.equals(emp.getEmpId())) return x;
x++;
}
return -1;
}

public void deleteEmployee(Employee employee) throws ModelException
{
try
{
Class.forName("com.mysql.cj.jdbc.Driver");
Connection connection;
connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_data","luffy","2608");
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("delete from employee where emp_id=?");
preparedStatement.setString(1,employee.getEmpId());
preparedStatement.executeUpdate();
preparedStatement.close();
connection.close();
employees.remove(employee);
}catch(SQLException sqlException)
{
throw new ModelException(sqlException.getMessage());
}
catch(ClassNotFoundException classNotFoundException)
{
throw new ModelException(classNotFoundException.getMessage());
}
}

public void sortEmployeeTableByEmpId()
{
for(int i=0;i<employees.size()-1;i++)
{
for(int j=i+1;j<employees.size();j++)
{
if(employees.get(i).getEmpId().compareTo(employees.get(j).getEmpId())>0)
{
Collections.swap(employees, i, j);

System.out.println("somthing happen");

}

}
}
}

public void sortEmployeeTableByName()
{
for(int i=0;i<employees.size()-1;i++)
{
for(int j=i+1;j<employees.size();j++)
{
if(employees.get(i).getName().compareTo(employees.get(j).getName())>0)
{
Collections.swap(employees, i, j);

System.out.println("somthing happen");

}

}
}
}



public void exportToPdf(String filePath)
{
if(!(filePath.toLowerCase().endsWith(".pdf")));
{
filePath = filePath+".pdf";
}
try
{
int pageNumber =0;
int sno=1;
PdfPTable table=null;
Document document = new Document();
PdfWriter.getInstance(document, new FileOutputStream(filePath));
document.open();
Boolean prepareHeading=true;


for(Employee emp:employees)
{
if(prepareHeading)
{
pageNumber++;
com.itextpdf.text.Font boldFont =new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 12, com.itextpdf.text.Font.BOLD);
Paragraph heading=new Paragraph("Employees List",boldFont);
heading.setAlignment(Element.ALIGN_CENTER);
//heading.getFont().setStyle(com.itextpdf.text.Font.BOLD);
document.add(heading);
Paragraph pageNo=new Paragraph("Page : "+pageNumber,boldFont);
pageNo.setAlignment(Element.ALIGN_RIGHT);
document.add(pageNo);
document.add(new Paragraph("  "));

Phrase serialNo = new Phrase("S.No. ", boldFont);
Phrase id = new Phrase("Emp.Id ", boldFont);
Phrase name = new Phrase("Name", boldFont);
table=new PdfPTable(3);
table.addCell(serialNo);
table.addCell(id);
table.addCell(name);
prepareHeading=false;
}
table.addCell(sno+".");
table.addCell(emp.getEmpId());
table.addCell(emp.getName());
if(sno%3330==0 || sno==employees.size())
{
table.setWidths(new int[]{1, 1, 2});
document.add(table);
prepareHeading=true;
if(sno!=employees.size()) 
{
document.newPage();
}
}
sno++;
}


document.close();
JOptionPane.showMessageDialog(null, "PDF saved successfully!");

}catch(Exception ex)
{
ex.printStackTrace();
JOptionPane.showMessageDialog(null, "Failed to save PDF: " + ex.getMessage());
}

}
}




class EmployeeDetailPanel extends JPanel   
{
private Employee employee=null;
private JLabel idCaptionLabel;
private JLabel idLabel;
private JLabel nameCaptionLabel;
private JLabel nameLabel;
private JLabel genderCaptionLabel;
private JLabel genderLabel;
private JLabel dateOfBirthCaptionLabel;
private JLabel dateOfBirthLabel;
private JLabel cityCaptionLabel;
private JLabel cityLabel;
private JLabel salaryCaptionLabel;
private JLabel salaryLabel;

public EmployeeDetailPanel()
{
idCaptionLabel=new JLabel("Id :  ");
idLabel=new JLabel("");
nameCaptionLabel=new JLabel("Name :  ");
nameLabel=new JLabel("");
genderCaptionLabel=new JLabel("Gender :  ");
genderLabel=new JLabel("");
dateOfBirthCaptionLabel=new JLabel("Date of Birth :  ");
dateOfBirthLabel=new JLabel("");
cityCaptionLabel=new JLabel("City :  ");
cityLabel=new JLabel("");
salaryCaptionLabel=new JLabel("Salary :   ");
salaryLabel=new JLabel("");

setLayout(new GridLayout(6,2));
add(idCaptionLabel);
add(idLabel);
add(nameCaptionLabel);
add(nameLabel);
add(genderCaptionLabel);
add(genderLabel);
add(dateOfBirthCaptionLabel);
add(dateOfBirthLabel);
add(cityCaptionLabel);
add(cityLabel);
add(salaryCaptionLabel);
add(salaryLabel);

idCaptionLabel.setFont(new java.awt.Font("Times New Roman",java.awt.Font.BOLD,18));
idLabel.setFont(new java.awt.Font("Times New Roman",java.awt.Font.PLAIN,18));
nameCaptionLabel.setFont(new java.awt.Font("Times New Roman",java.awt.Font.BOLD,18));
nameLabel.setFont(new java.awt.Font("Times New Roman",java.awt.Font.PLAIN,18));
genderCaptionLabel.setFont(new java.awt.Font("Times New Roman",java.awt.Font.BOLD,18));
genderLabel.setFont(new java.awt.Font("Times New Roman",java.awt.Font.PLAIN,18));
dateOfBirthCaptionLabel.setFont(new java.awt.Font("Times New Roman",java.awt.Font.BOLD,18));
dateOfBirthLabel.setFont(new java.awt.Font("Times New Roman",java.awt.Font.PLAIN,18));
cityCaptionLabel.setFont(new java.awt.Font("Times New Roman",java.awt.Font.BOLD,18));
cityLabel.setFont(new java.awt.Font("Times New Roman",java.awt.Font.PLAIN,18));
salaryCaptionLabel.setFont(new java.awt.Font("Times New Roman",java.awt.Font.BOLD,18));
salaryLabel.setFont(new java.awt.Font("Times New Roman",java.awt.Font.PLAIN,18));

idCaptionLabel.setHorizontalAlignment(SwingConstants.RIGHT);
nameCaptionLabel.setHorizontalAlignment(SwingConstants.RIGHT);
genderCaptionLabel.setHorizontalAlignment(SwingConstants.RIGHT);
dateOfBirthCaptionLabel.setHorizontalAlignment(SwingConstants.RIGHT);
cityCaptionLabel.setHorizontalAlignment(SwingConstants.RIGHT);
salaryCaptionLabel.setHorizontalAlignment(SwingConstants.RIGHT);

}

public void setEmployee(Employee employee)
{
idLabel.setText("");
nameLabel.setText("");
genderLabel.setText("");
dateOfBirthLabel.setText("");
cityLabel.setText("");
salaryLabel.setText("");


idLabel.setText(employee.getEmpId());
nameLabel.setText(employee.getName());
if(employee.getGender()=='M') genderLabel.setText("Male");
if(employee.getGender()=='F') genderLabel.setText("Female");
cityLabel.setText(employee.getCityName());
salaryLabel.setText(String.valueOf(employee.getSalary()));

SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");

dateOfBirthLabel.setText(simpleDateFormat.format(employee.getDateOfBirth()));

//Just for Knowledge
// hh for hours, mm for minutes, ss for seconds
// MM for Months, dd for day of month and yy or yyyy for year, MMM for Month name
}

}




class EmployeeCRUDUI extends JFrame implements ListSelectionListener,ActionListener,DocumentListener
{
//Components for Search feature
private JLabel searchLabel;
private JTextField searchTextField;
private ButtonGroup searchByButtonGroup;
private JRadioButton searchByEmpIdRadioButton;
private JRadioButton searchByNameRadioButton;
private JPanel searchPanel;
private JButton printPdfButton;

private JComboBox sortComboBox;
private JButton refreshButton;

private EmployeeModel employeeModel;
private JTable employeesTable;
private JScrollPane employeesTablePane;
private Container container;
private EmployeeDetailPanel employeeDetailPanel;
private ActionButtonPanel actionButtonPanel;
private SouthPanel southPanel;
private JButton addButton;
private JButton editButton;
private JButton deleteButton;

private AddPanel addPanel;
private EditPanel editPanel;
class ActionButtonPanel extends JPanel
{
ActionButtonPanel()
{
setLayout(new GridLayout(1,7));
add(new JLabel("  "));
add(addButton);
add(new JLabel("  "));
add(editButton);
add(new JLabel("  "));
add(deleteButton);
add(new JLabel("  "));
}
}

class SouthPanel extends JPanel
{
SouthPanel()
{
setLayout(new BorderLayout());
add(employeeDetailPanel,BorderLayout.CENTER);
add(actionButtonPanel,BorderLayout.SOUTH);
}
}

class AddPanel extends JPanel implements ActionListener
{
private JLabel titleLabel;

private JLabel empIdLabel;
private JTextField empIdTextField;

private JLabel nameLabel;
private JTextField nameTextField;

private JLabel dateLabel;
private JTextField dateTextField;

private JLabel cityLabel;
private JComboBox cityComboBox;

private JLabel genderLabel;
private ButtonGroup genderGroup;
private JRadioButton maleRadioButton;
private JRadioButton femaleRadioButton;

private JLabel salaryLabel;
private JTextField salaryTextField;

private JButton saveButton;
private JButton cancelButton;


AddPanel()
{
this.titleLabel=new JLabel("    Add Employee   ");

this.empIdLabel=new JLabel("Employee Id : ");
empIdTextField=new JTextField(2);

this.nameLabel=new JLabel("Name : ");
nameTextField= new JTextField(2);

this.dateLabel=new JLabel("Date Of Birth : ");
dateTextField= new JTextField(10);

this.cityLabel=new JLabel("City : ");
cityComboBox= new JComboBox();
CityModel cityModel=new CityModel();
java.util.List<City> cities= cityModel.getCities();

//after creating ComboBox
//iterate the list and add entries to combo box
cityComboBox.addItem("< Select city >");
for(City c : cities)
{
cityComboBox.addItem(c.getName());
}


genderGroup=new ButtonGroup();
this.genderLabel=new JLabel("Gender : ");
maleRadioButton=new JRadioButton("Male",true);
femaleRadioButton=new JRadioButton("Female");
genderGroup.add(maleRadioButton);
genderGroup.add(femaleRadioButton);

Panel p=new Panel();
p.setLayout(new GridLayout(1,2));
p.add(maleRadioButton);
p.add(femaleRadioButton);

this.salaryLabel=new JLabel("Salary : ");
salaryTextField= new JTextField(2);

saveButton=new JButton("Save");
cancelButton=new JButton("Cancel");

Panel b1= new Panel();
Panel b2=new Panel();
b1.add(saveButton);
b2.add(cancelButton);



this.setLayout(new GridLayout(8,2));
this.add(this.titleLabel,BorderLayout.NORTH);
this.add(new JLabel(""));
this.add(this.empIdLabel);
this.add(this.empIdTextField);
this.add(this.nameLabel);
this.add(this.nameTextField);
this.add(this.genderLabel);
this.add(p);
this.add(this.dateLabel);
this.add(this.dateTextField);
this.add(this.cityLabel);
this.add(this.cityComboBox);
this.add(this.salaryLabel);
this.add(this.salaryTextField);
this.add(b1);
this.add(b2);

titleLabel.setHorizontalAlignment(SwingConstants.RIGHT);
empIdLabel.setHorizontalAlignment(SwingConstants.RIGHT);
nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
genderLabel.setHorizontalAlignment(SwingConstants.RIGHT);
dateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
cityLabel.setHorizontalAlignment(SwingConstants.RIGHT);
salaryLabel.setHorizontalAlignment(SwingConstants.RIGHT);



salaryTextField.addKeyListener(new KeyAdapter() 
{
public void keyTyped(KeyEvent e) 
{
char c = e.getKeyChar();
if(!Character.isDigit(c))
{
e.consume();
}
}
});


/*
nameTextField.addKeyListener(new KeyAdapter() 
{
public void keyTyped(KeyEvent e)
{
char c = e.getKeyChar();
if(!Character.isLetter(c)) 
{
e.consume();
}
}
});
*/

saveButton.addActionListener(this);
cancelButton.addActionListener(this);


//add rest of the Components, so everthing should look and clean
//call addActionListener for save and cancel button
}
public void actionPerformed(ActionEvent ev)
{



if(ev.getSource()==saveButton)
{
String empId= empIdTextField.getText();
String name= nameTextField.getText();
String salaryString= salaryTextField.getText();
String city= cityComboBox.getSelectedItem().toString();
String date=dateTextField.getText();
char gender;

if(maleRadioButton.isSelected())
{
gender='M';
}
else 
{
gender='F';
}

//vaildating inputs

if(empId.length()==0)
{
JOptionPane.showMessageDialog(this,"Employee Id. required");
empIdTextField.requestFocus();
return;
} 
if(empId.length()>10)
{
JOptionPane.showMessageDialog(this,"Length of employee Id. cannot exceed 10");
empIdTextField.requestFocus();
return;
}

if(name.length()==0)
{
JOptionPane.showMessageDialog(this,"Name required");
nameTextField.requestFocus();
return;
} 
if(name.length()>50)
{
JOptionPane.showMessageDialog(this,"Length of name cannot exceed to 50");
nameTextField.requestFocus();
return;
}
if(date.length()==0)
{
JOptionPane.showMessageDialog(this,"Date required");
dateTextField.requestFocus();
return;
}

SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/mm/yyyy");
java.util.Date utilDate;
java.sql.Date sqlDate;
try
{
utilDate = simpleDateFormat.parse(date);
sqlDate = new java.sql.Date(utilDate.getYear(),utilDate.getMonth(),utilDate.getDate());
}catch(ParseException pe)
{
JOptionPane.showMessageDialog(this,"Invalid date, dd/mm/yyyy format is required");
dateTextField.setText("");
dateTextField.requestFocus();
return;
}
String splits[]=date.split("/");
int yyyy,mm,dd;
dd=Integer.parseInt(splits[0]);
mm=Integer.parseInt(splits[1]);
yyyy=Integer.parseInt(splits[2]);
if(mm<1 || mm>12)
{
JOptionPane.showMessageDialog(this,"Invalid date, dd/mm/yyyy format is required");
dateTextField.setText("");
dateTextField.requestFocus();
return;
}

int daysInMonth[]={31,28,31,30,31,30,31,31,30,31,30,31};
java.util.Date today=new java.util.Date();
if(yyyy<1930 || yyyy>today.getYear()+1900)
{
JOptionPane.showMessageDialog(this,"Invalid date, dd/mm/yyyy format is required and year should be (1930-"+(today.getYear()+1900)+")");
dateTextField.setText("");
dateTextField.requestFocus();
return;
}
if(yyyy%4==0)
{
if(yyyy%400==0)
{
daysInMonth[1]=29;
} 
}
if(dd<1 || dd>daysInMonth[mm-1])
{
JOptionPane.showMessageDialog(this,"Invalid date, dd/mm/yyyy format is required and days of month should be valid");
dateTextField.setText("");
dateTextField.requestFocus();
return;
}


if(cityComboBox.getSelectedIndex()==0)
{
JOptionPane.showMessageDialog(this,"Select city");
cityComboBox.requestFocus();
return;
}

if(salaryString.length()==0)
{
JOptionPane.showMessageDialog(this,"Salary required");
salaryTextField.requestFocus();
return;
}
int salary;
try
{
salary=Integer.parseInt(salaryString);
}catch(NumberFormatException nfe)
{
JOptionPane.showMessageDialog(this,"Invalid salary");
salaryTextField.setText("");
salaryTextField.requestFocus();
return;
}


Employee employee=new Employee();
employee.setEmpId(empId);
employee.setName(name);
employee.setGender(gender);
employee.setDateOfBirth(sqlDate);
employee.setCityName(city);
employee.setSalary(salary);
try
{
int addedAtIndex;
addedAtIndex=employeeModel.addEmployee(employee);
employeeModel.fireTableDataChanged(); //very very important
empIdTextField.setText("");
nameTextField.setText("");
salaryTextField.setText("");
dateTextField.setText("");
cityComboBox.setSelectedIndex(0);

container.remove(addPanel);
employeesTable.setEnabled(true);
container.add(southPanel,BorderLayout.SOUTH);
container.revalidate(); 
container.repaint();
JOptionPane.showMessageDialog(this,"Employee added");
employeesTable.setRowSelectionInterval(addedAtIndex,addedAtIndex);
employeesTable.scrollRectToVisible(employeesTable.getCellRect(addedAtIndex,addedAtIndex,true));

}catch(ModelException modelException)
{
JOptionPane.showMessageDialog(this,modelException.getMessage());
}
}

if(ev.getSource()==cancelButton)
{
//code to hide/Remove Jtable and south panel and display the panel with component to add emp

container.remove(addPanel);
employeesTable.setEnabled(true);  //disabled ho jayegi,click nhi hogi
container.add(southPanel,BorderLayout.SOUTH);

empIdTextField.setText("");
nameTextField.setText("");
salaryTextField.setText("");
dateTextField.setText("");
cityComboBox.setSelectedIndex(0);

// following are the 2 lines are very very important to Update View
container.revalidate(); 
container.repaint();
}
}

}

class EditPanel extends JPanel implements ActionListener
{
private JLabel titleLabel;

private JLabel nameLabel;
private JTextField nameTextField;

private JLabel dateLabel;
private JTextField dateTextField;

private JLabel cityLabel;
private JComboBox cityComboBox;

private JLabel genderLabel;
private ButtonGroup genderGroup;
private JRadioButton maleRadioButton;
private JRadioButton femaleRadioButton;

private JLabel salaryLabel;
private JTextField salaryTextField;

private JButton saveButton;
private JButton cancelButton;



EditPanel()
{
this.titleLabel=new JLabel("    Edit Employee   ");
this.nameLabel=new JLabel("Name : ");
nameTextField= new JTextField(2);

this.dateLabel=new JLabel("Date Of Birth : ");
dateTextField= new JTextField(10);

this.cityLabel=new JLabel("City : ");
cityComboBox= new JComboBox();
CityModel cityModel=new CityModel();
java.util.List<City> cities= cityModel.getCities();

//after creating ComboBox
//iterate the list and add entries to combo box
cityComboBox.addItem("< Select city >");
for(City c : cities)
{
cityComboBox.addItem(c.getName());
}


genderGroup=new ButtonGroup();
this.genderLabel=new JLabel("Gender : ");
maleRadioButton=new JRadioButton("Male",true);
femaleRadioButton=new JRadioButton("Female");
genderGroup.add(maleRadioButton);
genderGroup.add(femaleRadioButton);

Panel p=new Panel();
p.setLayout(new GridLayout(1,2));
p.add(maleRadioButton);
p.add(femaleRadioButton);

this.salaryLabel=new JLabel("Salary : ");
salaryTextField= new JTextField(2);

saveButton=new JButton("Save");
cancelButton=new JButton("Cancel");

Panel b1= new Panel();
Panel b2=new Panel();
b1.add(saveButton);
b2.add(cancelButton);


this.setLayout(new GridLayout(7,2));
this.add(this.titleLabel,BorderLayout.NORTH);
this.add(new JLabel(""));
this.add(this.nameLabel);
this.add(this.nameTextField);
this.add(this.genderLabel);
this.add(p);
this.add(this.dateLabel);
this.add(this.dateTextField);
this.add(this.cityLabel);
this.add(this.cityComboBox);
this.add(this.salaryLabel);
this.add(this.salaryTextField);
this.add(b1);
this.add(b2);

titleLabel.setHorizontalAlignment(SwingConstants.RIGHT);
nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
genderLabel.setHorizontalAlignment(SwingConstants.RIGHT);
dateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
cityLabel.setHorizontalAlignment(SwingConstants.RIGHT);
salaryLabel.setHorizontalAlignment(SwingConstants.RIGHT);



salaryTextField.addKeyListener(new KeyAdapter()
{
public void keyTyped(KeyEvent e)
{
char c = e.getKeyChar();
if(!Character.isDigit(c))
{
e.consume();
}
}
});


saveButton.addActionListener(this);
cancelButton.addActionListener(this);


//add rest of the Components, so everthing should look and clean
//call addActionListener for save and cancel button
}
public void actionPerformed(ActionEvent ev)
{
if(ev.getSource()==saveButton)
{
String name= nameTextField.getText();
String salaryString= salaryTextField.getText();
String city= cityComboBox.getSelectedItem().toString();
String date=dateTextField.getText();
char gender;

if(maleRadioButton.isSelected())
{
gender='M';
}
else
{
gender='F';
}

//vaildating inputs

if(name.length()==0)
{
JOptionPane.showMessageDialog(this,"Name required");
nameTextField.requestFocus();
return;
}
if(name.length()>50)
{
JOptionPane.showMessageDialog(this,"Length of name cannot exceed to 50");
nameTextField.requestFocus();
return;
}
if(date.length()==0)
{
JOptionPane.showMessageDialog(this,"Date required");
dateTextField.requestFocus();
return;
}
SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/mm/yyyy");
java.util.Date utilDate;
java.sql.Date sqlDate;
try
{
utilDate = simpleDateFormat.parse(date);
sqlDate = new java.sql.Date(utilDate.getYear(),utilDate.getMonth(),utilDate.getDate());
}catch(ParseException pe)
{
JOptionPane.showMessageDialog(this,"Invalid date, dd/mm/yyyy format is required");
dateTextField.setText("");
dateTextField.requestFocus();
return;
}
String splits[]=date.split("/");
int yyyy,mm,dd;
dd=Integer.parseInt(splits[0]);
mm=Integer.parseInt(splits[1]);
yyyy=Integer.parseInt(splits[2]);
if(mm<1 || mm>12)
{
JOptionPane.showMessageDialog(this,"Invalid date, dd/mm/yyyy format is required");
dateTextField.setText("");
dateTextField.requestFocus();
return;
}

int daysInMonth[]={31,28,31,30,31,30,31,31,30,31,30,31};
java.util.Date today=new java.util.Date();

if(yyyy<1930 || yyyy>today.getYear()+1900)
{
JOptionPane.showMessageDialog(this,"Invalid date, dd/mm/yyyy format is required and year should be (1930-"+(today.getYear()+1900)+")");
dateTextField.setText("");
dateTextField.requestFocus();
return;
}
if(yyyy%4==0)
{
if(yyyy%400==0)
{
daysInMonth[1]=29;
}
}
if(dd<1 || dd>daysInMonth[mm-1])
{
JOptionPane.showMessageDialog(this,"Invalid date, dd/mm/yyyy format is required and days of month should be valid");
dateTextField.setText("");
dateTextField.requestFocus();
return;
}


if(cityComboBox.getSelectedIndex()==0)
{
JOptionPane.showMessageDialog(this,"Select city");
cityComboBox.requestFocus();
return;
}
if(salaryString.length()==0)
{
JOptionPane.showMessageDialog(this,"Salary required");
salaryTextField.requestFocus();
return;
}
int salary;
try
{
salary=Integer.parseInt(salaryString);
}catch(NumberFormatException nfe)
{
JOptionPane.showMessageDialog(this,"Invalid salary");
salaryTextField.setText("");
salaryTextField.requestFocus();
return;
}

int x=employeesTable.getSelectedRow();
System.out.println(x);
Employee employee= employeeModel.getEmployee(x);
employee.setGender(gender);
employee.setDateOfBirth(sqlDate);
employee.setCityName(city);
employee.setSalary(salary);
employee.setName(name);
try
{
int addedAtIndex;
addedAtIndex=employeeModel.updateEmployee(employee);
employeeModel.fireTableDataChanged(); //very very important
nameTextField.setText("");
salaryTextField.setText("");
dateTextField.setText("");
cityComboBox.setSelectedIndex(0);

container.remove(editPanel);
employeesTable.setEnabled(true);
container.add(southPanel,BorderLayout.SOUTH);
container.revalidate();
container.repaint();
JOptionPane.showMessageDialog(this,"Employee updated");
employeesTable.setRowSelectionInterval(addedAtIndex,addedAtIndex);
employeesTable.scrollRectToVisible(employeesTable.getCellRect(addedAtIndex,addedAtIndex,true));

}catch(ModelException modelException)
{
JOptionPane.showMessageDialog(this,modelException.getMessage());
}
}

if(ev.getSource()==cancelButton)
{
//code to hide/Remove Jtable and south panel and display the panel with component to add emp

container.remove(editPanel);
employeesTable.setEnabled(true);  //disabled ho jayegi,click nhi hogi
container.add(southPanel,BorderLayout.SOUTH);

nameTextField.setText("");
salaryTextField.setText("");
dateTextField.setText("");
cityComboBox.setSelectedItem(0);
// following are the 2 lines are very very important to Update View
container.revalidate();
container.repaint();
}
}

}
public EmployeeCRUDUI()
{
super("Employees master");
setIconImage(new ImageIcon("task-management.png").getImage());
employeeModel=new EmployeeModel();
employeesTable=new JTable(employeeModel);
employeesTable.setRowHeight(30);

employeesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
employeesTable.getColumnModel().getColumn(0).setPreferredWidth(25);
employeesTable.getColumnModel().getColumn(1).setPreferredWidth(25);
employeesTable.getColumnModel().getColumn(2).setPreferredWidth(350);
employeesTable.getTableHeader().setResizingAllowed(false);
employeesTable.getTableHeader().setReorderingAllowed(false);
employeesTable.setFont(new java.awt.Font("Times New Roman",java.awt.Font.BOLD,18));
employeesTable.getTableHeader().setFont(new java.awt.Font("Times New Roman",java.awt.Font.BOLD,18));


employeesTablePane=new JScrollPane
(employeesTable,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);


employeeDetailPanel=new EmployeeDetailPanel();
addButton=new JButton("Add");
editButton=new JButton("Edit");
deleteButton=new JButton("Delete");

actionButtonPanel=new ActionButtonPanel();
southPanel=new SouthPanel();
addPanel=new AddPanel();
editPanel=new EditPanel();

container=getContentPane();
container.setLayout(new BorderLayout());


sortComboBox= new JComboBox();
sortComboBox.addItem("Name");
sortComboBox.addItem("EmpId");


refreshButton = new JButton("Refresh");


searchLabel=new JLabel("Search");
searchByButtonGroup=new ButtonGroup();
searchByEmpIdRadioButton= new JRadioButton("Emp.Id.");
searchByNameRadioButton= new JRadioButton("Name",true);
searchByButtonGroup.add(searchByEmpIdRadioButton);
searchByButtonGroup.add(searchByNameRadioButton);
searchTextField= new JTextField(30);
printPdfButton=new JButton("Print PDF");
searchTextField.getDocument().addDocumentListener(this);

searchPanel= new JPanel();
searchPanel.setLayout(new FlowLayout());
searchPanel.add(searchLabel);
searchPanel.add(searchByEmpIdRadioButton);
searchPanel.add(searchByNameRadioButton);
searchPanel.add(searchTextField);
searchPanel.add(new Label("  "));
searchPanel.add(new Label("Sort By "));
searchPanel.add(sortComboBox);
searchPanel.add(new Label("  "));
searchPanel.add(printPdfButton);
searchPanel.add(new Label("  "));
searchPanel.add(refreshButton);

container.add(searchPanel,BorderLayout.NORTH);
container.add(employeesTablePane,BorderLayout.CENTER);
container.add(southPanel,BorderLayout.SOUTH);


Dimension desktopDimension=Toolkit.getDefaultToolkit().getScreenSize();
this.setSize(1000,700);
this.setLocation(desktopDimension.width/2-this.getWidth()/2,desktopDimension.height/2-this.getHeight()/2);
this.setVisible(true);
this.setDefaultCloseOperation(EXIT_ON_CLOSE);

addEventListeners();
}

public void changedUpdate(DocumentEvent ev)
{
performSearch();
}

public void removeUpdate(DocumentEvent ev)
{
performSearch();
}

public void insertUpdate(DocumentEvent ev)
{
performSearch();
}


public void performSearch()
{
searchTextField.setForeground(Color.black);
String whatToSearch=searchTextField.getText().trim();
if(whatToSearch.length()==0) return;
int index;
if(searchByNameRadioButton.isSelected())
{
index=employeeModel.getIndexOfEmployeeWhoseNameStartsBy(whatToSearch);
if(index==-1)
{
searchTextField.setForeground(Color.red);
return;
}
employeesTable.setRowSelectionInterval(index,index);
employeesTable.scrollRectToVisible(employeesTable.getCellRect(index,index,true));
}

if(searchByEmpIdRadioButton.isSelected())
{
index=employeeModel.getIndexOfEmployeeWhoseEmpIdStartsBy(whatToSearch);
if(index==-1)
{
searchTextField.setForeground(Color.red);
return;
}
employeesTable.setRowSelectionInterval(index,index);
employeesTable.scrollRectToVisible(employeesTable.getCellRect(index,index,true));
}

}

public void valueChanged(ListSelectionEvent event)
{
int x=employeesTable.getSelectedRow();
Employee employee= employeeModel.getEmployee(x);
if(employee==null) return;
employeeDetailPanel.setEmployee(employee);
}

public void actionPerformed(ActionEvent ev)
{

if(ev.getSource()==addButton)
{
//code to hide/Remove Jtable and south panel and display the panel with component to add emp

this.container.remove(southPanel);
this.employeesTable.setEnabled(false);  //disabled ho jayegi,click nhi hogi
this.container.add(addPanel,BorderLayout.SOUTH);

// following are the 2 lines are very very important to Update View
this.container.revalidate(); // kick karke utha denge 
this.container.repaint();

} 
if(ev.getSource()==editButton)
{
if(employeesTable.getSelectedRow()==-1)
{
JOptionPane.showMessageDialog(this,"Select Employee before Edit");
return;
}

this.container.remove(southPanel);
this.employeesTable.setEnabled(false);
this.container.add(editPanel,BorderLayout.SOUTH);

this.container.revalidate();
this.container.repaint();


}
if(ev.getSource()==deleteButton)
{
if(employeesTable.getSelectedRow()==-1)
{
JOptionPane.showMessageDialog(this,"Select Employee before delete");
return;
}
int x=employeesTable.getSelectedRow();
Employee employee= employeeModel.getEmployee(x);

int response = JOptionPane.showConfirmDialog(this, "Do you want to delete employee : "+employee.getName(), "Confirmation", JOptionPane.YES_NO_OPTION);
if (response == JOptionPane.YES_OPTION) 
{
try
{
employeeModel.deleteEmployee(employee);
employeesTable.setRowSelectionInterval(x-1,x-1);
this.container.revalidate();
this.container.repaint();
}catch(Exception ex)
{
}
}
if (response == JOptionPane.NO_OPTION)
{
return;
}
}


if(ev.getSource()==printPdfButton)
{
JFileChooser fileChooser = new JFileChooser();
FileNameExtensionFilter fileType =new FileNameExtensionFilter("PDF File","pdf");
fileChooser.setDialogTitle("Choose where to save the PDF");
fileChooser.addChoosableFileFilter(fileType);

int userSelection = fileChooser.showSaveDialog(this);
if (userSelection == JFileChooser.APPROVE_OPTION)
{
String filePath = fileChooser.getSelectedFile().getAbsolutePath();
employeeModel.exportToPdf(filePath);
}

}


if(ev.getSource()==refreshButton)
{
employeeModel.fireTableDataChanged();
}

String sortType= (String)sortComboBox.getSelectedItem();
if(sortType=="EmpId")
{
System.out.println("EmpId is selected");
employeeModel.sortEmployeeTableByEmpId();
//employeeModel.fireTableDataChanged();   // do not use it here
this.container.revalidate();
this.container.repaint();
}
if(sortType=="Name")
{
employeeModel.sortEmployeeTableByName();
System.out.println("Name is selected");
//employeeModel.fireTableDataChanged(); //do not use it here
this.container.revalidate();
this.container.repaint();
}






}


private void addEventListeners()
{
this.employeesTable.getSelectionModel().addListSelectionListener(this);
this.addButton.addActionListener(this);
this.editButton.addActionListener(this);
this.deleteButton.addActionListener(this);
sortComboBox.addActionListener(this);
printPdfButton.addActionListener(this);
refreshButton.addActionListener(this);
}


}

class EmployeeCRUD
{
public static void main(String gg[])
{
EmployeeCRUDUI e =new EmployeeCRUDUI();
}
}

