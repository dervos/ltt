/*
 *  
 *  
 * 
 */
package view;

import main.CustomException;

import java.awt.Color;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import utility.PDFGenerator;

/**
 *
 * @author Haris, Tomas Slaman
 */
public class RegistrationPassenger extends javax.swing.JPanel {

    /**
     * Creates new form RegistrationPassenger
     */
    public RegistrationPassenger() {
        initComponents();
        setDocumentListeners();
        fillYearOfBirth();
    }

    /**
     * Fills YEAROFBIRTH combobox with years.
     */
    public void fillYearOfBirth() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        this.YEAROFBIRTH.removeAllItems();
        this.YEAROFBIRTH.addItem("YYYY");
        for (int i = year; i >= year - 150; i--) {
            this.YEAROFBIRTH.addItem(i);
        }
    }

    /**
     * Sets document listeners, so text can be copied from home address fields
     * to temp address fields if needed.
     */
    public void setDocumentListeners() {
        HOME_CITY_INPUT.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                if (EQUALCHECKBOX.isSelected()) {
                    TEMP_CITY_INPUT.setText(HOME_CITY_INPUT.getText());
                }
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                if (EQUALCHECKBOX.isSelected()) {
                    TEMP_CITY_INPUT.setText(HOME_CITY_INPUT.getText());
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (EQUALCHECKBOX.isSelected()) {
                    TEMP_CITY_INPUT.setText(HOME_CITY_INPUT.getText());
                }
            }
        });

        HOME_POSTAL_CODE_INPUT.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                if (EQUALCHECKBOX.isSelected()) {
                    TEMP_POSTAL_CODE_INPUT.setText(HOME_POSTAL_CODE_INPUT.getText());
                }
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                if (EQUALCHECKBOX.isSelected()) {
                    TEMP_POSTAL_CODE_INPUT.setText(HOME_POSTAL_CODE_INPUT.getText());
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (EQUALCHECKBOX.isSelected()) {
                    TEMP_POSTAL_CODE_INPUT.setText(HOME_POSTAL_CODE_INPUT.getText());
                }
            }
        });

        HOME_STREET_INPUT.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                if (EQUALCHECKBOX.isSelected()) {
                    TEMP_STREET_INPUT.setText(HOME_STREET_INPUT.getText());
                }
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                if (EQUALCHECKBOX.isSelected()) {
                    TEMP_STREET_INPUT.setText(HOME_STREET_INPUT.getText());
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (EQUALCHECKBOX.isSelected()) {
                    TEMP_STREET_INPUT.setText(HOME_STREET_INPUT.getText());
                }
            }
        });
    }

    /**
     * Creates a passenger model out of the info in the edit panel.
     *
     * @return
     * @throws CustomException
     */
    public model.Passenger createPassenger() throws CustomException {
        String name = NAME_INPUT.getText();
        String insertion = SURNAME_TUSSENVOEGSEL_INPUT.getText();
        String surname = SURNAME_INPUT.getText();
        Date dob;
        String gender = MALE_BUTTON.isSelected() ? "m" : "f";
        String homePhone = HOME_PHONE_NUMBER_INPUT.getText();
        String mobPhone = MOBILE_PHONE_NUMBER_INPUT.getText();

        //String[] dateContent = strDob.split("-");
        if (name.length() > 20) {
            throw new CustomException("First name length is too long, 20 characters maximum. You've got: " + name.length(), NAME_INPUT);
        } else if (name.length() == 0) {
            throw new CustomException("First name has to be filled in.", NAME_INPUT);
        }
        if (hasDigit(name)) {
            throw new CustomException("First name can't contain digits.", NAME_INPUT);
        }
        if (insertion.length() > 8) {
            throw new CustomException("Insertion length is too long, 8 characters maximum. You've got: " + insertion.length(), SURNAME_TUSSENVOEGSEL_INPUT);
        }
        if (hasDigit(insertion)) {
            throw new CustomException("Insertion can't contain digits.", SURNAME_TUSSENVOEGSEL_INPUT);
        }
        if (surname.length() > 35) {
            throw new CustomException("Surname length is too long, 35 characters maximum. You've got: " + surname.length(), SURNAME_INPUT);
        } else if (surname.length() == 0) {
            throw new CustomException("Surname has to be filled in.", SURNAME_INPUT);
        }
        if (hasDigit(surname)) {
            throw new CustomException("Surname can't contain digits.", SURNAME_INPUT);
        }

        if (this.DAYOFBIRTH.getSelectedIndex() == 0) {
            throw new CustomException("Please select a valid month for the passenger's day of birth.");
        }
        if (this.DAYOFBIRTH.getSelectedIndex() == 0) {
            throw new CustomException("Please select a valid day for the passenger's day of birth.");
        }
        if (this.YEAROFBIRTH.getSelectedIndex() == 0) {
            throw new CustomException("Please select a valid year for the passenger's day of birth.");
        }

        if (homePhone.length() == 0 && mobPhone.length() == 0) {
            throw new CustomException("Atleast 1 phone number has to be filled in.");
        }

        if (homePhone.length() > 12) {
            throw new CustomException("Phone numbers can't be longer than 12 characters. You've got: " + homePhone.length(), HOME_PHONE_NUMBER_INPUT);
        }
        if (mobPhone.length() > 12) {
            throw new CustomException("Phone numbers can't be longer than 12 characters. You've got: " + mobPhone.length(), MOBILE_PHONE_NUMBER_INPUT);
        }
        if (hasAlpha(homePhone)) {
            throw new CustomException("Phone numbers cannot contain letters", HOME_PHONE_NUMBER_INPUT);
        }
        if (hasAlpha(mobPhone)) {
            throw new CustomException("Phone numbers cannot contain letters", HOME_PHONE_NUMBER_INPUT);
        }

        int year = Integer.parseInt(YEAROFBIRTH.getSelectedItem().toString());
        int month = Integer.parseInt(MONTHOFBIRTH.getSelectedItem().toString());
        int day = Integer.parseInt(DAYOFBIRTH.getSelectedItem().toString());
        Calendar cal = GregorianCalendar.getInstance();
        cal.set(year, month - 1, day);

        dob = cal.getTime();

        model.Passenger tempPassenger = new model.Passenger();

        tempPassenger.setName(name);
        tempPassenger.setInsertion(insertion);
        tempPassenger.setSurname(surname);
        tempPassenger.setDob(dob);
        tempPassenger.setGender(gender);
        tempPassenger.setHomephone(homePhone);
        tempPassenger.setMobphone(mobPhone);

        return tempPassenger;
    }

    /**
     *
     * @return @throws CustomException
     */
    public model.Address createHomeAddressFromForms() throws CustomException {
        String city = HOME_CITY_INPUT.getText();
        String country = HOME_COUNTRY_INPUT.getSelectedItem().toString();
        String streetName = HOME_STREET_INPUT.getText();
        String zipCode = HOME_POSTAL_CODE_INPUT.getText();

        if (HOME_COUNTRY_INPUT.getSelectedIndex() == 0) {
            throw new CustomException("A country has to be selected.", HOME_COUNTRY_INPUT);
        }
        if (city.length() == 0) {
            throw new CustomException("A city has to be filled in!", HOME_CITY_INPUT);
        }
        if (city.length() > 20) {
            throw new CustomException("City can't be longer than 20 characters, you've got: " + city.length(), HOME_CITY_INPUT);
        }
        if (streetName.length() == 0) {
            throw new CustomException("A street has to be filled in.", HOME_STREET_INPUT);
        }
        if (streetName.length() > 25) {
            throw new CustomException("Street name can't be longer than 25 characters, you've got: " + streetName.length(), HOME_STREET_INPUT);
        }
        if (zipCode.length() == 0) {
            throw new CustomException("A zipcode has to be filled in.", HOME_POSTAL_CODE_INPUT);
        }
        if (zipCode.length() > 6) {
            throw new CustomException("Zipcode can't be longer than 6 characters, you've got: " + zipCode.length(), HOME_POSTAL_CODE_INPUT);
        }

        if (city.length() == 0 || HOME_COUNTRY_INPUT.getSelectedIndex() == 0 || streetName.length() == 0 || zipCode.length() == 0) {
            throw new CustomException("Not all fields of home address are filled in. Please complete all fields.");
        }

        model.Address address = new model.Address();
        address.setCity(city);
        address.setCountry(country);
        address.setStreetname(streetName);
        address.setZipcode(zipCode);
        return address;
    }

    /**
     *
     * @return @throws CustomException
     */
    public model.Address createTempAddressFromForms() throws CustomException {
        String city = TEMP_CITY_INPUT.getText();
        String country = TEMP_COUNTRY_INPUT.getSelectedItem().toString();
        String streetName = TEMP_STREET_INPUT.getText();
        String zipCode = TEMP_POSTAL_CODE_INPUT.getText();

        if (TEMP_COUNTRY_INPUT.getSelectedIndex() == 0) {
            throw new CustomException("A country has to be selected.", TEMP_COUNTRY_INPUT);
        }

        if (city.length() == 0) {
            throw new CustomException("A city has to be filled in!", TEMP_CITY_INPUT);
        }
        if (city.length() > 20) {
            throw new CustomException("City can't be longer than 20 characters, you've got: " + city.length(), TEMP_CITY_INPUT);
        }

        if (streetName.length() == 0) {
            throw new CustomException("A street has to be filled in.", TEMP_STREET_INPUT);
        }
        if (streetName.length() > 25) {
            throw new CustomException("Street name can't be longer than 25 characters, you've got: " + streetName.length(), TEMP_STREET_INPUT);
        }
        if (zipCode.length() == 0) {
            throw new CustomException("A zipcode has to be filled in.", TEMP_POSTAL_CODE_INPUT);
        }
        if (zipCode.length() > 6) {
            throw new CustomException("Zipcode can't be longer than 6 characters, you've got: " + zipCode.length(), TEMP_POSTAL_CODE_INPUT);
        }

        if (city.length() == 0 || TEMP_COUNTRY_INPUT.getSelectedIndex() == 0 || streetName.length() == 0 || zipCode.length() == 0) {
            throw new CustomException("Not all fields of temp address are filled in. Please complete all fields.");
        }

        model.Address address = new model.Address();
        address.setCity(city);
        address.setCountry(country);
        address.setStreetname(streetName);
        address.setZipcode(zipCode);
        return address;
    }

    /**
     * Clears all fields in the registration passenger panel.
     */
    public void clearFields() {
        SURNAME_INPUT.setText("");
        SURNAME_TUSSENVOEGSEL_INPUT.setText("");
        NAME_INPUT.setText("");
        //DATE_OF_BIRTH_INPUT.setText("####-##-##");
        //DATE_OF_BIRTH_INPUT.setValue(null);
        //main.LuggageTrackerTool2.getInstance().createJFTFMask(getDateOfBirthControl(), "yyyy-MM-dd", "####-##-##");
        HOME_PHONE_NUMBER_INPUT.setText("");
        MOBILE_PHONE_NUMBER_INPUT.setText("");

        HOME_CITY_INPUT.setText("");
        HOME_COUNTRY_INPUT.setSelectedIndex(1);
        HOME_STREET_INPUT.setText("");
        HOME_POSTAL_CODE_INPUT.setText("");

        TEMP_CITY_INPUT.setText("");
        TEMP_COUNTRY_INPUT.setSelectedIndex(1);
        TEMP_STREET_INPUT.setText("");
        TEMP_POSTAL_CODE_INPUT.setText("");
    }

    /**
     * Sets background colors of the fields in registration passenger panel.
     *
     * @param c
     */
    public void setBackgroundColors(Color c) {
        SURNAME_INPUT.setBackground(c);
        SURNAME_TUSSENVOEGSEL_INPUT.setBackground(c);
        NAME_INPUT.setBackground(c);
        //DATE_OF_BIRTH_INPUT.setBackground(c);
        HOME_PHONE_NUMBER_INPUT.setBackground(c);
        MOBILE_PHONE_NUMBER_INPUT.setBackground(c);

        HOME_CITY_INPUT.setBackground(c);
        HOME_STREET_INPUT.setBackground(c);
        HOME_POSTAL_CODE_INPUT.setBackground(c);

        if (!EQUALCHECKBOX.isSelected()) {
            TEMP_CITY_INPUT.setBackground(c);
            TEMP_STREET_INPUT.setBackground(c);
            TEMP_POSTAL_CODE_INPUT.setBackground(c);
        }
    }

    /**
     * Safely convert string to int.
     *
     * @param text
     * @return
     */
    private int tryParseInt(String text) {
        int result = -1;
        try //Try catch to prevent the application from crashing when the inserted text isnt a number
        {
            result = Integer.parseInt(text); //try to parse text to int
        } catch (NumberFormatException nfe) {
            return result;
        }

        return result;
    }

    /**
     * checks if string anything else besides a digit.
     *
     * @param text
     * @return
     */
    public boolean hasAlpha(String text) {
        char[] characters = text.toCharArray();
        for (int i = 0; i < characters.length; i++) {
            boolean isDigit = Character.isDigit(characters[i]);
            if (!isDigit) {
                if (!(i == 0 && characters[i] == '+')) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks if string contains digit
     *
     * @param text
     * @return
     */
    public boolean hasDigit(String text) {
        char[] characters = text.toCharArray();
        for (int i = 0; i < characters.length; i++) {
            if (Character.isDigit(characters[i])) {
                return true;
            }
        }

        return false;
    }

    /**
     * Compares if home & temp address are equal.
     *
     * @return
     */
    public boolean addressesAreEqual() {
        return ((this.HOME_COUNTRY_INPUT.getSelectedIndex() == this.TEMP_COUNTRY_INPUT.getSelectedIndex())
                && (this.HOME_CITY_INPUT.getText().equals(this.TEMP_CITY_INPUT.getText()))
                && (this.HOME_POSTAL_CODE_INPUT.getText().equals(this.TEMP_POSTAL_CODE_INPUT.getText()))
                && (this.HOME_STREET_INPUT.getText().equals(this.TEMP_STREET_INPUT.getText())));
    }

    /**
     *
     * @return Can be accessed from LTT2 class
     * .sideBar.getPassengerControl().getDayOfBirthControl();
     */
    /*public javax.swing.JFormattedTextField getDateOfBirthControl() {
     return this.DATE_OF_BIRTH_INPUT;
     }*/
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        jToggleButton1 = new javax.swing.JToggleButton();
        BASIC_INFORMATION_TITLE = new javax.swing.JLabel();
        NAME = new javax.swing.JLabel();
        NAME_INPUT = new javax.swing.JTextField();
        SURNAME = new javax.swing.JLabel();
        SURNAME_TUSSENVOEGSEL_INPUT = new javax.swing.JTextField();
        SURNAME_INPUT = new javax.swing.JTextField();
        DATE_OF_BIRTH = new javax.swing.JLabel();
        GENDER = new javax.swing.JLabel();
        MALE_BUTTON = new javax.swing.JRadioButton();
        FEMALE_BUTTON = new javax.swing.JRadioButton();
        HOME_PHONE_NUMBER = new javax.swing.JLabel();
        HOME_PHONE_NUMBER_INPUT = new javax.swing.JTextField();
        MOBILE_PHONE_NUMBER = new javax.swing.JLabel();
        MOBILE_PHONE_NUMBER_INPUT = new javax.swing.JTextField();
        HOME_ADDRESS_TITLE = new javax.swing.JLabel();
        HOME_COUNTRY = new javax.swing.JLabel();
        HOME_COUNTRY_INPUT = new javax.swing.JComboBox();
        HOME_CITY = new javax.swing.JLabel();
        HOME_CITY_INPUT = new javax.swing.JTextField();
        HOME_STREET = new javax.swing.JLabel();
        HOME_STREET_INPUT = new javax.swing.JTextField();
        HOME_POSTAL_CODE = new javax.swing.JLabel();
        HOME_POSTAL_CODE_INPUT = new javax.swing.JTextField();
        TEMPORARY_ADDRESS_TITLE = new javax.swing.JLabel();
        TEMP_COUNTRY = new javax.swing.JLabel();
        TEMP_COUNTRY_INPUT = new javax.swing.JComboBox();
        TEMP_CITY = new javax.swing.JLabel();
        TEMP_CITY_INPUT = new javax.swing.JTextField();
        TEMP_STREET = new javax.swing.JLabel();
        TEMP_STREET_INPUT = new javax.swing.JTextField();
        TEMP_POSTAL_CODE = new javax.swing.JLabel();
        TEMP_POSTAL_CODE_INPUT = new javax.swing.JTextField();
        PAIR_PASSENGER_LUGGAGE = new javax.swing.JCheckBox();
        PRINT = new javax.swing.JButton();
        REGISTER = new javax.swing.JButton();
        EQUALCHECKBOX = new javax.swing.JCheckBox();
        MONTHOFBIRTH = new javax.swing.JComboBox();
        DAYOFBIRTH = new javax.swing.JComboBox();
        YEAROFBIRTH = new javax.swing.JComboBox();

        jToggleButton1.setText("jToggleButton1");

        setBorder(javax.swing.BorderFactory.createTitledBorder("Register Passenger"));

        BASIC_INFORMATION_TITLE.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        BASIC_INFORMATION_TITLE.setText("Basic Information");

        NAME.setText("Name");

        SURNAME.setText("Surname");

        SURNAME_TUSSENVOEGSEL_INPUT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SURNAME_TUSSENVOEGSEL_INPUTActionPerformed(evt);
            }
        });

        DATE_OF_BIRTH.setText("Date of Birth");

        GENDER.setText("Gender");

        MALE_BUTTON.setSelected(true);
        MALE_BUTTON.setText("Male");
        MALE_BUTTON.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MALE_BUTTONActionPerformed(evt);
            }
        });

        FEMALE_BUTTON.setText("Female");
        FEMALE_BUTTON.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FEMALE_BUTTONActionPerformed(evt);
            }
        });

        HOME_PHONE_NUMBER.setText("Home Phone Number");

        MOBILE_PHONE_NUMBER.setText("Mobile Phone Number");

        HOME_ADDRESS_TITLE.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        HOME_ADDRESS_TITLE.setText("Home Address");

        HOME_COUNTRY.setText("Country");

        HOME_COUNTRY_INPUT.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Please select your country ..", "Belgium", "Albania", "Bosnia & Herzegovina", "Bulgaria", "Croatia", "Cyprus", "Czech Republic", "Denmark", "Estonia", "Finland", "France", "Georgia", "Germany", "Greece", "Hungary", "Ireland", "Italy", "The Netherlands", "Norway", "Poland", "Portugal", "Russia", "San Marino", "Serbia", "Slovakia", "Slovenia", "Spain", "Sweden", "Switzerland", "Turkey", "Ukraine", "United Kingdom" }));
        HOME_COUNTRY_INPUT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HOME_COUNTRY_INPUTActionPerformed(evt);
            }
        });

        HOME_CITY.setText("City");

        HOME_STREET.setText("Street");

        HOME_POSTAL_CODE.setText("Postal Code");

        TEMPORARY_ADDRESS_TITLE.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        TEMPORARY_ADDRESS_TITLE.setText("Temporary Address");

        TEMP_COUNTRY.setText("Country");

        TEMP_COUNTRY_INPUT.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Please select your country ..", "Belgium", "Albania", "Bosnia & Herzegovina", "Bulgaria", "Croatia", "Cyprus", "Czech Republic", "Denmark", "Estonia", "Finland", "France", "Georgia", "Germany", "Greece", "Hungary", "Ireland", "Italy", "The Netherlands", "Norway", "Poland", "Portugal", "Russia", "San Marino", "Serbia", "Slovakia", "Slovenia", "Spain", "Sweden", "Switzerland", "Turkey", "Ukraine", "United Kingdom" }));

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, EQUALCHECKBOX, org.jdesktop.beansbinding.ELProperty.create("${!selected}"), TEMP_COUNTRY_INPUT, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        TEMP_COUNTRY_INPUT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TEMP_COUNTRY_INPUTActionPerformed(evt);
            }
        });

        TEMP_CITY.setText("City");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, EQUALCHECKBOX, org.jdesktop.beansbinding.ELProperty.create("${!selected}"), TEMP_CITY_INPUT, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        TEMP_STREET.setText("Street");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, EQUALCHECKBOX, org.jdesktop.beansbinding.ELProperty.create("${!selected}"), TEMP_STREET_INPUT, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        TEMP_POSTAL_CODE.setText("Postal Code");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, EQUALCHECKBOX, org.jdesktop.beansbinding.ELProperty.create("${!selected}"), TEMP_POSTAL_CODE_INPUT, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        PAIR_PASSENGER_LUGGAGE.setSelected(true);
        PAIR_PASSENGER_LUGGAGE.setText("Pair passenger with luggage");

        PRINT.setText("Print");
        PRINT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PRINTActionPerformed(evt);
            }
        });

        REGISTER.setText("Submit");
        REGISTER.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                REGISTERActionPerformed(evt);
            }
        });

        EQUALCHECKBOX.setSelected(true);
        EQUALCHECKBOX.setText("Same as Home Address");
        EQUALCHECKBOX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EQUALCHECKBOXActionPerformed(evt);
            }
        });

        MONTHOFBIRTH.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "MM", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        MONTHOFBIRTH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MONTHOFBIRTHActionPerformed(evt);
            }
        });

        DAYOFBIRTH.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "DD", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        YEAROFBIRTH.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "YYYY" }));
        YEAROFBIRTH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                YEAROFBIRTHActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(NAME))
                    .addComponent(BASIC_INFORMATION_TITLE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(TEMPORARY_ADDRESS_TITLE)
                                        .addGap(60, 60, 60))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(20, 20, 20)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(DATE_OF_BIRTH)
                                                    .addComponent(GENDER)
                                                    .addComponent(TEMP_CITY)
                                                    .addComponent(TEMP_COUNTRY)
                                                    .addComponent(TEMP_STREET)
                                                    .addComponent(TEMP_POSTAL_CODE)
                                                    .addComponent(HOME_PHONE_NUMBER)
                                                    .addComponent(MOBILE_PHONE_NUMBER)
                                                    .addComponent(SURNAME)))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(21, 21, 21)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(HOME_CITY)
                                                    .addComponent(HOME_COUNTRY)
                                                    .addComponent(HOME_STREET)
                                                    .addComponent(HOME_POSTAL_CODE))))
                                        .addGap(43, 43, 43)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TEMP_COUNTRY_INPUT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(PAIR_PASSENGER_LUGGAGE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(MALE_BUTTON)
                                                .addGap(18, 18, 18)
                                                .addComponent(FEMALE_BUTTON))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(1, 1, 1)
                                                .addComponent(EQUALCHECKBOX))
                                            .addComponent(REGISTER))
                                        .addGap(95, 95, 95))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(HOME_ADDRESS_TITLE)
                                .addGap(94, 94, 94)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(TEMP_POSTAL_CODE_INPUT, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TEMP_STREET_INPUT, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(HOME_POSTAL_CODE_INPUT, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(HOME_STREET_INPUT, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(HOME_CITY_INPUT, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(HOME_COUNTRY_INPUT, javax.swing.GroupLayout.Alignment.LEADING, 0, 256, Short.MAX_VALUE)
                                    .addComponent(HOME_PHONE_NUMBER_INPUT, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(MOBILE_PHONE_NUMBER_INPUT, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TEMP_CITY_INPUT, javax.swing.GroupLayout.Alignment.LEADING)))
                            .addComponent(PRINT, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(188, 188, 188)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(NAME_INPUT, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(SURNAME_TUSSENVOEGSEL_INPUT, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(SURNAME_INPUT, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(MONTHOFBIRTH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(DAYOFBIRTH, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(YEAROFBIRTH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(24, 24, 24))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BASIC_INFORMATION_TITLE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(NAME)
                            .addComponent(NAME_INPUT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(SURNAME_TUSSENVOEGSEL_INPUT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(SURNAME_INPUT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(SURNAME)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MONTHOFBIRTH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DAYOFBIRTH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(YEAROFBIRTH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DATE_OF_BIRTH))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(GENDER)
                    .addComponent(MALE_BUTTON)
                    .addComponent(FEMALE_BUTTON))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(HOME_PHONE_NUMBER)
                    .addComponent(HOME_PHONE_NUMBER_INPUT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MOBILE_PHONE_NUMBER)
                    .addComponent(MOBILE_PHONE_NUMBER_INPUT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(HOME_ADDRESS_TITLE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(HOME_COUNTRY)
                    .addComponent(HOME_COUNTRY_INPUT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(HOME_CITY)
                    .addComponent(HOME_CITY_INPUT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(HOME_STREET)
                    .addComponent(HOME_STREET_INPUT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(HOME_POSTAL_CODE)
                    .addComponent(HOME_POSTAL_CODE_INPUT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TEMPORARY_ADDRESS_TITLE)
                    .addComponent(EQUALCHECKBOX))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TEMP_COUNTRY)
                    .addComponent(TEMP_COUNTRY_INPUT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TEMP_CITY)
                    .addComponent(TEMP_CITY_INPUT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TEMP_STREET)
                    .addComponent(TEMP_STREET_INPUT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TEMP_POSTAL_CODE)
                    .addComponent(TEMP_POSTAL_CODE_INPUT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(PAIR_PASSENGER_LUGGAGE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PRINT)
                    .addComponent(REGISTER))
                .addContainerGap())
        );

        bindingGroup.bind();
    }// </editor-fold>//GEN-END:initComponents

    private void MALE_BUTTONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MALE_BUTTONActionPerformed
        if (FEMALE_BUTTON.isSelected()) {
            FEMALE_BUTTON.setSelected(false);
        } else {
            FEMALE_BUTTON.setSelected(true);
        }
    }//GEN-LAST:event_MALE_BUTTONActionPerformed

    private void SURNAME_TUSSENVOEGSEL_INPUTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SURNAME_TUSSENVOEGSEL_INPUTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SURNAME_TUSSENVOEGSEL_INPUTActionPerformed

    private void REGISTERActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_REGISTERActionPerformed
        try {
            setBackgroundColors(Color.WHITE);
            model.Passenger passenger = createPassenger();
            model.Address homeAddress = createHomeAddressFromForms();
            model.Address tempAddress = createTempAddressFromForms();
            int result = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to register a new passenger?", "Question",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (result == JOptionPane.YES_OPTION) {
                homeAddress.setAddressid(model.AddressDAO.create(homeAddress).get(1));
                if (!EQUALCHECKBOX.isSelected() && addressesAreEqual()) {
                    EQUALCHECKBOX.setSelected(true);
                }

                if (!EQUALCHECKBOX.isSelected()) {
                    tempAddress.setAddressid(model.AddressDAO.create(tempAddress).get(1));
                }

                passenger.setHomeaddress(homeAddress);
                if (EQUALCHECKBOX.isSelected()) {
                    passenger.setTempaddress(homeAddress);
                } else {
                    passenger.setTempaddress(tempAddress);
                }

                model.PassengerDAO.create(passenger);
                main.LuggageTrackerTool2.getInstance().getMainMenu().getPassengerTab().refresh();
                main.LuggageTrackerTool2.getInstance().getMainMenu().getjTabbedPane().setSelectedIndex(0);

                clearFields();

                
               
            }

        } catch (SQLException ex) {
            System.err.println("Failed to create passenger.");
            System.err.println("Message: " + ex.getMessage());
            ex.printStackTrace();
        } catch (CustomException cEx) {
            JOptionPane.showMessageDialog(null, cEx.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            if (cEx.getComponent() != null) {
                cEx.getComponent().setBackground(Color.RED);
            }
        }

    }//GEN-LAST:event_REGISTERActionPerformed

    private void FEMALE_BUTTONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FEMALE_BUTTONActionPerformed
        if (MALE_BUTTON.isSelected()) {
            MALE_BUTTON.setSelected(false);
        } else {
            MALE_BUTTON.setSelected(true);
        }
    }//GEN-LAST:event_FEMALE_BUTTONActionPerformed

    private void EQUALCHECKBOXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EQUALCHECKBOXActionPerformed
        if (EQUALCHECKBOX.isSelected()) {
            TEMP_COUNTRY_INPUT.setSelectedIndex(HOME_COUNTRY_INPUT.getSelectedIndex());
            TEMP_CITY_INPUT.setText(HOME_CITY_INPUT.getText());
            TEMP_STREET_INPUT.setText(HOME_STREET_INPUT.getText());
            TEMP_POSTAL_CODE_INPUT.setText(HOME_POSTAL_CODE_INPUT.getText());
        }
    }//GEN-LAST:event_EQUALCHECKBOXActionPerformed

    private void HOME_COUNTRY_INPUTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HOME_COUNTRY_INPUTActionPerformed
        if (EQUALCHECKBOX.isSelected()) {
            TEMP_COUNTRY_INPUT.setSelectedIndex(HOME_COUNTRY_INPUT.getSelectedIndex());
        }
    }//GEN-LAST:event_HOME_COUNTRY_INPUTActionPerformed

    private void MONTHOFBIRTHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MONTHOFBIRTHActionPerformed
        int daySelectedIndex = this.DAYOFBIRTH.getSelectedIndex();

        if (this.MONTHOFBIRTH.getSelectedIndex() == 0) {
            this.DAYOFBIRTH.removeAllItems();
            this.DAYOFBIRTH.addItem("DD");
            for (int i = 1; i <= 31; i++) {
                this.DAYOFBIRTH.addItem(i);
            }

        } else {
            int maximum = 0;
            int month = Integer.parseInt(this.MONTHOFBIRTH.getSelectedItem().toString());
            if (this.YEAROFBIRTH.getSelectedIndex() != 0 && month == 2 && Integer.parseInt(this.YEAROFBIRTH.getSelectedItem().toString()) % 4 == 0) {
                maximum = 29;
            } else if (month == 2) //february
            {
                maximum = 28;
            } else if ((month < 8 && month % 2 == 0) || (month > 7 && month % 2 == 1)) {
                maximum = 30;
            } else if ((month > 7 && month % 2 == 0) || (month < 8 && month % 2 == 1)) {
                maximum = 31;
            }

            this.DAYOFBIRTH.removeAllItems();
            this.DAYOFBIRTH.addItem("DD");
            for (int i = 1; i <= maximum; i++) {
                this.DAYOFBIRTH.addItem(i);
            }
        }

        if (this.DAYOFBIRTH.getItemCount() - 1 >= daySelectedIndex) {
            this.DAYOFBIRTH.setSelectedIndex(daySelectedIndex);
        }
    }//GEN-LAST:event_MONTHOFBIRTHActionPerformed

    private void YEAROFBIRTHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_YEAROFBIRTHActionPerformed
        int daySelectedIndex = this.DAYOFBIRTH.getSelectedIndex();

        if (this.YEAROFBIRTH.getSelectedIndex() > 0) {
            int year = Integer.parseInt(this.YEAROFBIRTH.getSelectedItem().toString());
            int month = -1;
            if (this.MONTHOFBIRTH.getSelectedIndex() > 0) {
                month = Integer.parseInt(this.MONTHOFBIRTH.getSelectedItem().toString());
                if (month == 2 && year % 4 == 0) {
                    this.DAYOFBIRTH.removeAllItems();
                    this.DAYOFBIRTH.addItem("DD");
                    for (int i = 1; i <= 29; i++) {
                        this.DAYOFBIRTH.addItem(i);
                    }
                } else if (month == 2 && year % 4 != 0 && this.DAYOFBIRTH.getItemCount() == 30) {
                    this.DAYOFBIRTH.removeAllItems();
                    this.DAYOFBIRTH.addItem("DD");
                    for (int i = 1; i <= 28; i++) {
                        this.DAYOFBIRTH.addItem(i);
                    }
                }
            }
        }

        if (this.DAYOFBIRTH.getItemCount() - 1 >= daySelectedIndex) {
            this.DAYOFBIRTH.setSelectedIndex(daySelectedIndex);
        }
    }//GEN-LAST:event_YEAROFBIRTHActionPerformed
    // This is the print button.
    private void PRINTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PRINTActionPerformed
        
         int result = JOptionPane.showConfirmDialog(null,
                "Are you sure you want to print the information?", "Question",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
         if (result == JOptionPane.YES_OPTION){
        
        model.Passenger selectedPassenger = main.LuggageTrackerTool2.getInstance().getSelectedPassenger();
        model.Luggage selectedLuggage = main.LuggageTrackerTool2.getInstance().getSelectedLuggage();
        
        PDFGenerator pdf = new PDFGenerator();
        pdf.generate(selectedPassenger, selectedLuggage);
        pdf.save("Informatie.pdf");
         }
        
        
        
        
    }//GEN-LAST:event_PRINTActionPerformed

    private void TEMP_COUNTRY_INPUTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TEMP_COUNTRY_INPUTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TEMP_COUNTRY_INPUTActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BASIC_INFORMATION_TITLE;
    private javax.swing.JLabel DATE_OF_BIRTH;
    private javax.swing.JComboBox DAYOFBIRTH;
    private javax.swing.JCheckBox EQUALCHECKBOX;
    private javax.swing.JRadioButton FEMALE_BUTTON;
    private javax.swing.JLabel GENDER;
    private javax.swing.JLabel HOME_ADDRESS_TITLE;
    private javax.swing.JLabel HOME_CITY;
    private javax.swing.JTextField HOME_CITY_INPUT;
    private javax.swing.JLabel HOME_COUNTRY;
    private javax.swing.JComboBox HOME_COUNTRY_INPUT;
    private javax.swing.JLabel HOME_PHONE_NUMBER;
    private javax.swing.JTextField HOME_PHONE_NUMBER_INPUT;
    private javax.swing.JLabel HOME_POSTAL_CODE;
    private javax.swing.JTextField HOME_POSTAL_CODE_INPUT;
    private javax.swing.JLabel HOME_STREET;
    private javax.swing.JTextField HOME_STREET_INPUT;
    private javax.swing.JRadioButton MALE_BUTTON;
    private javax.swing.JLabel MOBILE_PHONE_NUMBER;
    private javax.swing.JTextField MOBILE_PHONE_NUMBER_INPUT;
    private javax.swing.JComboBox MONTHOFBIRTH;
    private javax.swing.JLabel NAME;
    private javax.swing.JTextField NAME_INPUT;
    private javax.swing.JCheckBox PAIR_PASSENGER_LUGGAGE;
    private javax.swing.JButton PRINT;
    private javax.swing.JButton REGISTER;
    private javax.swing.JLabel SURNAME;
    private javax.swing.JTextField SURNAME_INPUT;
    private javax.swing.JTextField SURNAME_TUSSENVOEGSEL_INPUT;
    private javax.swing.JLabel TEMPORARY_ADDRESS_TITLE;
    private javax.swing.JLabel TEMP_CITY;
    private javax.swing.JTextField TEMP_CITY_INPUT;
    private javax.swing.JLabel TEMP_COUNTRY;
    private javax.swing.JComboBox TEMP_COUNTRY_INPUT;
    private javax.swing.JLabel TEMP_POSTAL_CODE;
    private javax.swing.JTextField TEMP_POSTAL_CODE_INPUT;
    private javax.swing.JLabel TEMP_STREET;
    private javax.swing.JTextField TEMP_STREET_INPUT;
    private javax.swing.JComboBox YEAROFBIRTH;
    private javax.swing.JToggleButton jToggleButton1;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
