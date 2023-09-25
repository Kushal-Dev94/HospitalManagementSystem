import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class HospitalManagementSystemGUI extends JFrame {
    private final List<Patient> patients = new ArrayList<>();
    private JList<String> patientList;
    private DefaultListModel<String> patientListModel;

    public HospitalManagementSystemGUI() {
        setTitle("Hospital Management System");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Create GUI components
        JTabbedPane tabbedPane = new JTabbedPane();

        // Create Home Page Tab
        JPanel homePagePanel = new JPanel();
        JLabel homePageLabel = new JLabel("Welcome to Hospital Management System");
        homePageLabel.setFont(new Font("Arial", Font.BOLD, 18));
        homePageLabel.setHorizontalAlignment(JLabel.CENTER);
        homePagePanel.add(homePageLabel);
        tabbedPane.addTab("Home", homePagePanel);

        // Create EHR Tab
        JPanel ehrPanel = createEHRPanel();
        tabbedPane.addTab("EHR", ehrPanel);

        // Create Inpatient Management Tab
        JPanel inpatientPanel = createInpatientPanel();
        tabbedPane.addTab("Inpatient & Outpatient Mgmnt", inpatientPanel);

        // Create Billing and Finance Tab
        JPanel billingAndFinancePanel = createBillingAndFinancePanel();
        tabbedPane.addTab("Billing and Finance", billingAndFinancePanel);

        add(tabbedPane);
    }

    private JPanel createEHRPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JTabbedPane ehrTabbedPane = new JTabbedPane();

        // Create Input Panel
        JPanel inputPanel = createInputPanel();
        ehrTabbedPane.addTab("Input", inputPanel);

        // Create View Patients Panel
        JPanel viewPatientsPanel = createViewPatientsPanel();
        ehrTabbedPane.addTab("View Patients", viewPatientsPanel);

        panel.add(ehrTabbedPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createInputPanel() {
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());

        GroupLayout layout = new GroupLayout(inputPanel);
        inputPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        // Input fields and button (same as previous code)
        JLabel nameLabel = new JLabel("Full Name:");
        JTextField nameField = new JTextField(20);
        JLabel ageLabel = new JLabel("Age:");
        JTextField ageField = new JTextField(5);
        JLabel diagnosisLabel = new JLabel("Diagnosis:");
        JTextField diagnosisField = new JTextField(20);
        JLabel treatmentLabel = new JLabel("Treatment Plan:");
        JTextField treatmentField = new JTextField(20);
        JLabel testResultsLabel = new JLabel("Test Results:");
        JTextField testResultsField = new JTextField(20);
        JLabel medicationsLabel = new JLabel("Medications:");
        JTextField medicationsField = new JTextField(20);
        JLabel progressNotesLabel = new JLabel("Progress Notes:");
        JTextArea progressNotesArea = new JTextArea(5, 20);
        JButton addButton = new JButton("Add Patient");

        JTextArea patientInfoArea = new JTextArea(15, 50);
        patientInfoArea.setEditable(false);
        JScrollPane infoScrollPane = new JScrollPane(patientInfoArea);

        addButton.addActionListener(e -> {
            // Retrieve data from input fields
            String name = nameField.getText().trim();
            String ageText = ageField.getText().trim();
            String diagnosis = diagnosisField.getText().trim();
            String treatment = treatmentField.getText().trim();
            String testResults = testResultsField.getText().trim();
            String medications = medicationsField.getText().trim();
            String progressNotes = progressNotesArea.getText().trim();

            // Check if required fields are empty
            if (name.isEmpty() || ageText.isEmpty() || diagnosis.isEmpty()) {
                JOptionPane.showMessageDialog(inputPanel, "Name, Age, and Diagnosis are required fields.", "Error", JOptionPane.ERROR_MESSAGE);
                return; // Don't proceed if required fields are empty
            }

            // Parse age as an integer, handle parsing error
            int age;
            try {
                age = Integer.parseInt(ageText);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(inputPanel, "Age must be a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                return; // Don't proceed if age is not a valid number
            }

            // Create a new patient and add it to the list
            Patient patient = new Patient(name, age, diagnosis, treatment, testResults, medications, progressNotes);
            patients.add(patient);

            // Update the patient information display
            //updatePatientInfoArea();

            // Update the patient list
            patientListModel.addElement(name);

            // Clear input fields
            nameField.setText("");
            ageField.setText("");
            diagnosisField.setText("");
            treatmentField.setText("");
            testResultsField.setText("");
            medicationsField.setText("");
            progressNotesArea.setText("");
        });

        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        hGroup.addGroup(layout.createParallelGroup()
                .addComponent(nameLabel)
                .addComponent(ageLabel)
                .addComponent(diagnosisLabel)
                .addComponent(treatmentLabel)
                .addComponent(testResultsLabel)
                .addComponent(medicationsLabel)
                .addComponent(progressNotesLabel)
        );
        hGroup.addGroup(layout.createParallelGroup()
                .addComponent(nameField)
                .addComponent(ageField)
                .addComponent(diagnosisField)
                .addComponent(treatmentField)
                .addComponent(testResultsField)
                .addComponent(medicationsField)
                .addComponent(progressNotesArea)
        );
        hGroup.addComponent(addButton);
        layout.setHorizontalGroup(hGroup);

        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(nameLabel)
                .addComponent(nameField)
        );
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(ageLabel)
                .addComponent(ageField)
        );
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(diagnosisLabel)
                .addComponent(diagnosisField)
        );
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(treatmentLabel)
                .addComponent(treatmentField)
        );
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(testResultsLabel)
                .addComponent(testResultsField)
        );
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(medicationsLabel)
                .addComponent(medicationsField)
        );
        vGroup.addGroup(layout.createParallelGroup()
                .addComponent(progressNotesLabel)
                .addComponent(progressNotesArea)
        );
        vGroup.addComponent(addButton);
        layout.setVerticalGroup(vGroup);

        inputPanel.add(infoScrollPane, BorderLayout.CENTER);
        inputPanel.add(addButton, BorderLayout.SOUTH);

        return inputPanel;
    }


    private JPanel createViewPatientsPanel() {
        JPanel viewPatientsPanel = new JPanel();
        viewPatientsPanel.setLayout(new BorderLayout());

        // Create a list of patients
        patientListModel = new DefaultListModel<>();
        patientList = new JList<>(patientListModel);

        // Add sample patients to the list
        Patient patient1 = new Patient("John Doe", 35, "Fever", "Rest and hydrate", "Negative", "Aspirin", "Patient feels tired.");
        Patient patient2 = new Patient("Jane Smith", 45, "Cough", "Antibiotics", "Positive", "Cough syrup", "Improving, less coughing.");
        Patient patient3 = new Patient("Alice Johnson", 28, "Fractured arm", "Surgery required", "N/A", "Painkillers", "Scheduled for surgery.");
        Patient patient4 = new Patient("Bob Brown", 60, "Heart condition", "Medications", "Stable", "Blood thinners", "Regular check-ups.");

        patients.add(patient1);
        patients.add(patient2);
        patients.add(patient3);
        patients.add(patient4);

        patientListModel.addElement(patient1.name());
        patientListModel.addElement(patient2.name());
        patientListModel.addElement(patient3.name());
        patientListModel.addElement(patient4.name());

        JScrollPane listScrollPane = new JScrollPane(patientList);

        // Create a panel to display patient details
        JPanel patientDetailsPanel = new JPanel(new BorderLayout());
        JTextArea patientDetailsArea = new JTextArea(15, 50);
        patientDetailsArea.setEditable(false);
        JScrollPane detailsScrollPane = new JScrollPane(patientDetailsArea);
        patientDetailsPanel.add(detailsScrollPane, BorderLayout.CENTER);

        // Add a selection listener to the patient list
        patientList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                // Get the selected patient name
                String selectedPatientName = patientList.getSelectedValue();
                // Find the patient by name
                Patient selectedPatient = null;
                for (Patient patient : patients) {
                    if (patient.name().equals(selectedPatientName)) {
                        selectedPatient = patient;
                        break;
                    }
                }
                if (selectedPatient != null) {
                    // Display patient details
                    patientDetailsArea.setText("Name: " + selectedPatient.name() + "\n"
                            + "Age: " + selectedPatient.age() + "\n"
                            + "Diagnosis: " + selectedPatient.diagnosis() + "\n"
                            + "Treatment Plan: " + selectedPatient.treatmentPlan() + "\n"
                            + "Test Results: " + selectedPatient.testResults() + "\n"
                            + "Medications: " + selectedPatient.medications() + "\n"
                            + "Progress Notes: " + selectedPatient.progressNotes() + "\n");
                }
            }
        });

        // Add components to the view patients panel
        viewPatientsPanel.add(listScrollPane, BorderLayout.WEST);
        viewPatientsPanel.add(patientDetailsPanel, BorderLayout.CENTER);

        return viewPatientsPanel;
    }

    private JPanel createInpatientPanel() {
        JPanel inpatientPanel = new JPanel();
        inpatientPanel.setLayout(new BorderLayout());

        JTabbedPane inpatientTabbedPane = new JTabbedPane();

        // Create Admission Subtab for Inpatients
        JPanel admissionPanel = createAdmissionPanel();
        inpatientTabbedPane.addTab("Admission", admissionPanel);

        // Create Discharge Subtab for Inpatients
        JPanel dischargePanel = createDischargePanel();
        inpatientTabbedPane.addTab("Discharge", dischargePanel);

        inpatientPanel.add(inpatientTabbedPane, BorderLayout.CENTER);

        return inpatientPanel;
    }

    private JPanel createAdmissionPanel() {
        JPanel admissionPanel = new JPanel();
        admissionPanel.setLayout(new BorderLayout());

        DefaultListModel<String> admissionListModel = new DefaultListModel<>();
        JList<String> admissionList = new JList<>(admissionListModel);
        JScrollPane admissionScrollPane = new JScrollPane(admissionList);

        // Create admission button
        JButton admissionButton = new JButton("Admit Patient");
        admissionButton.addActionListener(e -> {
            // Retrieve data from input fields
            String patientName = JOptionPane.showInputDialog(admissionPanel, "Enter patient name:");
            String admissionDate = JOptionPane.showInputDialog(admissionPanel, "Enter admission date (e.g., MM/DD/YYYY):");
            String bedNumber = JOptionPane.showInputDialog(admissionPanel, "Enter bed number:");

            // Check if any of the input fields are empty
            if (patientName == null || admissionDate == null || bedNumber == null ||
                    patientName.isEmpty() || admissionDate.isEmpty() || bedNumber.isEmpty()) {
                JOptionPane.showMessageDialog(admissionPanel, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
                return; // Don't proceed if any field is empty
            }

            // Update the admission list with patient information
            String admissionInfo = "Patient: " + patientName + ", Admission Date: " + admissionDate + ", Bed Number: " + bedNumber;
            admissionListModel.addElement(admissionInfo);
        });

        admissionPanel.add(admissionScrollPane, BorderLayout.CENTER);
        admissionPanel.add(admissionButton, BorderLayout.SOUTH);

        return admissionPanel;
    }

    // Create Discharge Subtab for Inpatients
    private JPanel createDischargePanel() {
        JPanel dischargePanel = new JPanel();
        dischargePanel.setLayout(new BorderLayout());

        DefaultListModel<String> dischargeListModel = new DefaultListModel<>();
        JList<String> dischargeList = new JList<>(dischargeListModel);
        JScrollPane dischargeScrollPane = new JScrollPane(dischargeList);

        // Create discharge button
        JButton dischargeButton = new JButton("Discharge Patient");
        dischargeButton.addActionListener(e -> {
            // Retrieve data from input fields
            String patientName = JOptionPane.showInputDialog(dischargePanel, "Enter patient name:");
            String dischargeDate = JOptionPane.showInputDialog(dischargePanel, "Enter discharge date (e.g., MM/DD/YYYY):");

            // Check if any of the input fields are empty
            if (patientName == null || dischargeDate == null || patientName.isEmpty() || dischargeDate.isEmpty()) {
                JOptionPane.showMessageDialog(dischargePanel, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
                return; // Don't proceed if any field is empty
            }

            // Update the discharge list with patient information
            String dischargeInfo = "Patient: " + patientName + ", Discharge Date: " + dischargeDate;
            dischargeListModel.addElement(dischargeInfo);
        });

        dischargePanel.add(dischargeScrollPane, BorderLayout.CENTER);
        dischargePanel.add(dischargeButton, BorderLayout.SOUTH);

        return dischargePanel;
    }

    private JPanel createBillingAndFinancePanel() {
        JPanel billingAndFinancePanel = new JPanel();
        billingAndFinancePanel.setLayout(new BorderLayout());

        JTabbedPane billingTabbedPane = new JTabbedPane();

        // Create Billing Subtab
        JPanel billingPanel = createBillingPanel();
        billingTabbedPane.addTab("Billing", billingPanel);

        // Create Invoicing Subtab
        JPanel invoicingPanel = createInvoicingPanel();
        billingTabbedPane.addTab("Invoicing", invoicingPanel);

        // Create Financial Transactions Subtab
        JPanel transactionsPanel = createTransactionsPanel();
        billingTabbedPane.addTab("Financial Transactions", transactionsPanel);

        // Create Billing Reports Subtab
        JPanel reportsPanel = createReportsPanel();
        billingTabbedPane.addTab("Billing Reports", reportsPanel);

        billingAndFinancePanel.add(billingTabbedPane, BorderLayout.CENTER);

        return billingAndFinancePanel;
    }

    private JPanel createBillingPanel() {
        JPanel billingPanel = new JPanel();
        billingPanel.setLayout(new BorderLayout());

        // Example: List of billed services
        DefaultListModel<String> billedServicesListModel = new DefaultListModel<>();
        JList<String> billedServicesList = new JList<>(billedServicesListModel);
        JScrollPane billedServicesScrollPane = new JScrollPane(billedServicesList);

        // Example: Button to add a billed service
        JButton addBilledServiceButton = new JButton("Add Billed Service");
        addBilledServiceButton.addActionListener(e -> {
            String billedService = JOptionPane.showInputDialog(billingPanel, "Enter the billed service:");
            if (billedService != null && !billedService.isEmpty()) {
                billedServicesListModel.addElement(billedService);
            }
        });

        billingPanel.add(billedServicesScrollPane, BorderLayout.CENTER);
        billingPanel.add(addBilledServiceButton, BorderLayout.SOUTH);

        return billingPanel;
    }

    private JPanel createInvoicingPanel() {
        JPanel invoicingPanel = new JPanel();
        invoicingPanel.setLayout(new BorderLayout());

        // Example: List of patient invoices
        DefaultListModel<String> patientInvoicesListModel = new DefaultListModel<>();
        JList<String> patientInvoicesList = new JList<>(patientInvoicesListModel);
        JScrollPane patientInvoicesScrollPane = new JScrollPane(patientInvoicesList);

        // Example: Button to generate patient invoices
        JButton generateInvoicesButton = new JButton("Generate Invoices");
        generateInvoicesButton.addActionListener(e -> {
            // Generate invoices for patients and add them to the list
            for (Patient patient : patients) {
                double totalAmount = 0.0;
                // Calculate the total amount based on services, surgeries, medications, etc.
                totalAmount += 1500.0; // Example: Base fee
                totalAmount += 300.0; // Example: Medications
                totalAmount += 500.0; // Example: Surgery
                totalAmount += 200.0; // Example: Lab tests

                String invoice = "Patient: " + patient.name() + ", Total Amount: $" + totalAmount;
                patientInvoicesListModel.addElement(invoice);
            }
        });

        invoicingPanel.add(patientInvoicesScrollPane, BorderLayout.CENTER);
        invoicingPanel.add(generateInvoicesButton, BorderLayout.SOUTH);

        return invoicingPanel;
    }

    private JPanel createTransactionsPanel() {
        JPanel transactionsPanel = new JPanel();
        transactionsPanel.setLayout(new BorderLayout());

        // Example: List of financial transactions
        DefaultListModel<String> transactionsListModel = new DefaultListModel<>();
        JList<String> transactionsList = new JList<>(transactionsListModel);
        JScrollPane transactionsScrollPane = new JScrollPane(transactionsList);

        // Example: Button to record a financial transaction
        JButton recordTransactionButton = new JButton("Record Transaction");
        recordTransactionButton.addActionListener(e -> {
            String transactionDescription = JOptionPane.showInputDialog(transactionsPanel, "Enter transaction description:");
            String transactionAmountText = JOptionPane.showInputDialog(transactionsPanel, "Enter transaction amount:");

            try {
                double transactionAmount = Double.parseDouble(transactionAmountText);
                String transactionRecord = "Description: " + transactionDescription + ", Amount: $" + transactionAmount;
                transactionsListModel.addElement(transactionRecord);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(transactionsPanel, "Invalid transaction amount.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        transactionsPanel.add(transactionsScrollPane, BorderLayout.CENTER);
        transactionsPanel.add(recordTransactionButton, BorderLayout.SOUTH);

        return transactionsPanel;
    }

    private JPanel createReportsPanel() {
        JPanel reportsPanel = new JPanel();
        reportsPanel.setLayout(new BorderLayout());

        // Example: Display billing reports
        JTextArea billingReportsArea = new JTextArea(15, 50);
        billingReportsArea.setEditable(false);
        JScrollPane billingReportsScrollPane = new JScrollPane(billingReportsArea);

        // Example: Button to generate billing reports
        JButton generateBillingReportsButton = new JButton("Generate Billing Reports");
        generateBillingReportsButton.addActionListener(e -> {
            // Generate billing reports and display them
            billingReportsArea.setText("Billing Report 1:\nTotal Revenue: $XXX\n\nBilling Report 2:\nTotal Revenue: $XXX");
            // Replace XXX with actual data
        });

        reportsPanel.add(billingReportsScrollPane, BorderLayout.CENTER);
        reportsPanel.add(generateBillingReportsButton, BorderLayout.SOUTH);

        return reportsPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HospitalManagementSystemGUI().setVisible(true));
    }
}

record Patient(String name, int age, String diagnosis, String treatmentPlan, String testResults, String medications,
               String progressNotes) {
}
