public class Student {
    private String nim;
    private String name;
    private int age;
    private String major;

    public Student(String nim, String name, int age, String major) {
        this.nim = nim;
        this.name = name;
        this.age = age;
        this.major = major;
    }

    // Getter methods
    public String getNim() { return nim; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getMajor() { return major; }
}


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class StudentData {
    private ArrayList<Student> students;

    public StudentData(String filePath) {
        students = new ArrayList<>();
        loadFromCSV(filePath);
    }

    private void loadFromCSV(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String nim = values[0];
                String name = values[1];
                int age = Integer.parseInt(values[2]);
                String major = values[3];
                students.add(new Student(nim, name, age, major));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Student> getStudents() {
        return students;
    }
}


import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Comparator;

public class StudentTableModel extends AbstractTableModel {
    private ArrayList<Student> students;
    private final String[] columnNames = {"NIM", "Name", "Age", "Major"};

    public StudentTableModel(ArrayList<Student> students) {
        this.students = students;
        sortDataByNim();
    }

    @Override
    public int getRowCount() {
        return students.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Student student = students.get(rowIndex);
        switch (columnIndex) {
            case 0: return student.getNim();
            case 1: return student.getName();
            case 2: return student.getAge();
            case 3: return student.getMajor();
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public void sortDataByNim() {
        students.sort(Comparator.comparing(Student::getNim));
        fireTableDataChanged();
    }

    public void sortDataByName() {
        students.sort(Comparator.comparing(Student::getName));
        fireTableDataChanged();
    }

    public void sortDataByAge() {
        students.sort(Comparator.comparing(Student::getAge));
        fireTableDataChanged();
    }

    public void sortDataByMajor() {
        students.sort(Comparator.comparing(Student::getMajor));
        fireTableDataChanged();
    }
}


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentTableFrame extends JFrame {
    public StudentTableFrame() {
        StudentData studentData = new StudentData("students.csv");
        StudentTableModel tableModel = new StudentTableModel(studentData.getStudents());
        JTable table = new JTable(tableModel);

        JComboBox<String> sortOptions = new JComboBox<>(new String[]{"NIM", "Name", "Age", "Major"});
        sortOptions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedOption = (String) sortOptions.getSelectedItem();
                switch (selectedOption) {
                    case "NIM": tableModel.sortDataByNim(); break;
                    case "Name": tableModel.sortDataByName(); break;
                    case "Age": tableModel.sortDataByAge(); break;
                    case "Major": tableModel.sortDataByMajor(); break;
                }
            }
        });

        setLayout(new BorderLayout());
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(sortOptions, BorderLayout.NORTH);

        setTitle("Student Data");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StudentTableFrame frame = new StudentTableFrame();
            frame.setVisible(true);
        });
    }
}
