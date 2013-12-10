/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.Component;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JOptionPane;

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
    }

    class CustomException extends Exception {

        private Component cmp = null;

        public CustomException(String exMessage) {
            super(exMessage);
        }

        public CustomException(String exMessage, Component comp) {
            super(exMessage);
            this.cmp = comp;
        }

        public Component getComponent() {
            return this.cmp;
        }
    }

    public model.Passenger createPassenger() throws CustomException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        String name = NAME_INPUT.getText();
        String insertion = SURNAME_TUSSENVOEGSEL_INPUT.getText();
        String surname = SURNAME_INPUT.getText();
        java.util.Date dob;
        String strDob = DATE_OF_BIRTH_INPUT.getText();
        String gender = MALE_BUTTON.isSelected() ? "m" : "f";
        String homePhone = HOME_PHONE_NUMBER_INPUT.getText();
        String mobPhone = MOBILE_PHONE_NUMBER_INPUT.getText();

        String[] dateContent = strDob.split("-");

        if (name.length() > 20) {
            throw new CustomException("First name length is too long, 20 characters maximum. You've got: " + name.length(), NAME_INPUT);
        } else if (name.length() == 0) {
            throw new CustomException("First name has to be filled in.", NAME_INPUT);
        }

        if (insertion.length() > 8) {
            throw new CustomException("Insertion length is too long, 8 characters maximum. You've got: " + insertion.length(), SURNAME_TUSSENVOEGSEL_INPUT);
        }

        if (surname.length() > 35) {
            throw new CustomException("Surname length is too long, 35 characters maximum. You've got: " + surname.length(), SURNAME_INPUT);
        } else if (surname.length() == 0) {
            throw new CustomException("Surname has to be filled in.", SURNAME_INPUT);
        }

        if (!strDob.contains("-") || dateContent.length != 3) {
            throw new CustomException("Date of birth is not in the correct format, please use: yyyy-mm-dd as format.", DATE_OF_BIRTH_INPUT);
        }

        for (int i = 0; i < dateContent.length; i++) {
            int result = tryParseInt(dateContent[i]);
            if (result == -1) {
                switch (i) {
                    case 0:
                        if ((result < 1850 || result >= (Calendar.getInstance().get(Calendar.YEAR)) + 1)) {
                            throw new CustomException("Date of Birth year is incorrect.", DATE_OF_BIRTH_INPUT);
                        }
                        break;
                    case 1:
                        if (result < 1 || result > 12) {
                            throw new CustomException("Date of birth month is incorrect.", DATE_OF_BIRTH_INPUT);
                        }
                        break;
                    case 2:
                        if (result < 1 || result > 31) {
                            throw new CustomException("Date of birth day is incorrect.", DATE_OF_BIRTH_INPUT);
                        }
                        break;
                }
            }
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

        try {
            dob = df.parse(DATE_OF_BIRTH_INPUT.getText());
        } catch (ParseException e) {
            throw new CustomException("Date of Birth format is incorrect", DATE_OF_BIRTH_INPUT);
        }

        model.Passenger tempPassenger = new model.Passenger();

        tempPassenger.setName(name);
        tempPassenger.setInsertion(insertion);
        tempPassenger.setSurname(surname);
        try {
            tempPassenger.setDob(dob);
        } catch (ParseException e) {
            throw new CustomException("Date of Birth format is incorrect", DATE_OF_BIRTH_INPUT);
        }
        tempPassenger.setGender(gender);
        tempPassenger.setHomephone(homePhone);
        tempPassenger.setMobphone(mobPhone);

        //clearFields();
        return tempPassenger;
    }

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

    public void clearFields() {
        SURNAME_INPUT.setText("");
        SURNAME_TUSSENVOEGSEL_INPUT.setText("");
        NAME_INPUT.setText("");
        //DATE_OF_BIRTH_INPUT.setText("####-##-##");
        DATE_OF_BIRTH_INPUT.setValue(null);
        main.LuggageTrackerTool2.getInstance().createJFTFMask(getDateOfBirthControl(), "yyyy-MM-dd", "####-##-##");
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

    public void setBackgroundColors(Color c) {
        SURNAME_INPUT.setBackground(c);
        SURNAME_TUSSENVOEGSEL_INPUT.setBackground(c);
        NAME_INPUT.setBackground(c);
        DATE_OF_BIRTH_INPUT.setBackground(c);
        HOME_PHONE_NUMBER_INPUT.setBackground(c);
        MOBILE_PHONE_NUMBER_INPUT.setBackground(c);

        HOME_CITY_INPUT.setBackground(c);
        HOME_STREET_INPUT.setBackground(c);
        HOME_POSTAL_CODE_INPUT.setBackground(c);

        TEMP_CITY_INPUT.setBackground(c);
        TEMP_STREET_INPUT.setBackground(c);
        TEMP_POSTAL_CODE_INPUT.setBackground(c);
    }

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
     *
     * @return Can be accessed from LTT2 class
     * .sideBar.getPassengerControl().getDayOfBirthControl();
     */
    public javax.swing.JFormattedTextField getDateOfBirthControl() {
        return this.DATE_OF_BIRTH_INPUT;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToggleButton1 = new javax.swing.JToggleButton();
        BASIC_INFORMATION_TITLE = new javax.swing.JLabel();
        NAME = new javax.swing.JLabel();
        NAME_INPUT = new javax.swing.JTextField();
        SURNAME = new javax.swing.JLabel();
        SURNAME_TUSSENVOEGSEL_INPUT = new javax.swing.JTextField();
        SURNAME_INPUT = new javax.swing.JTextField();
        DATE_OF_BIRTH = new javax.swing.JLabel();
        DD_MM_YYYY = new javax.swing.JLabel();
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
        ADDITIONAL_OPTIONS_TITLE = new javax.swing.JLabel();
        PRINT_ON_REGISTER = new javax.swing.JCheckBox();
        PAIR_PASSENGER_LUGGAGE = new javax.swing.JCheckBox();
        PRINT = new javax.swing.JButton();
        REGISTER = new javax.swing.JButton();
        DATE_OF_BIRTH_INPUT = new javax.swing.JFormattedTextField();

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

        DD_MM_YYYY.setFont(new java.awt.Font("Tahoma", 2, 13)); // NOI18N
        DD_MM_YYYY.setText("(YYYY-MM-DD)");

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

        HOME_COUNTRY_INPUT.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Please select your country ..", "The Netherlands", "Germany", "Belgium" }));

        HOME_CITY.setText("City");

        HOME_STREET.setText("Street");

        HOME_POSTAL_CODE.setText("Postal Code");

        TEMPORARY_ADDRESS_TITLE.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        TEMPORARY_ADDRESS_TITLE.setText("Temporary Address");

        TEMP_COUNTRY.setText("Country");

        TEMP_COUNTRY_INPUT.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Please select your country ..", "The Netherlands", "Germany", "Belgium" }));

        TEMP_CITY.setText("City");

        TEMP_STREET.setText("Street");

        TEMP_POSTAL_CODE.setText("Postal Code");

        ADDITIONAL_OPTIONS_TITLE.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        ADDITIONAL_OPTIONS_TITLE.setText("Additional Options");

        PRINT_ON_REGISTER.setSelected(true);
        PRINT_ON_REGISTER.setText("Print on register");

        PAIR_PASSENGER_LUGGAGE.setSelected(true);
        PAIR_PASSENGER_LUGGAGE.setText("Pair passenger with luggage");

        PRINT.setText("Print");

        REGISTER.setText("Submit");
        REGISTER.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                REGISTERActionPerformed(evt);
            }
        });

        DATE_OF_BIRTH_INPUT.setColumns(3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
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
                                            .addComponent(NAME)
                                            .addComponent(SURNAME)
                                            .addComponent(DATE_OF_BIRTH)
                                            .addComponent(GENDER)
                                            .addComponent(TEMP_CITY)
                                            .addComponent(TEMP_COUNTRY)
                                            .addComponent(TEMP_STREET)
                                            .addComponent(TEMP_POSTAL_CODE)
                                            .addComponent(HOME_PHONE_NUMBER)
                                            .addComponent(MOBILE_PHONE_NUMBER)
                                            .addComponent(PRINT_ON_REGISTER)
                                            .addComponent(PRINT)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(HOME_CITY)
                                            .addComponent(HOME_COUNTRY)
                                            .addComponent(HOME_STREET)
                                            .addComponent(HOME_POSTAL_CODE))))
                                .addGap(43, 43, 43)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PAIR_PASSENGER_LUGGAGE)
                            .addComponent(REGISTER)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(MALE_BUTTON)
                                .addGap(18, 18, 18)
                                .addComponent(FEMALE_BUTTON))))
                    .addComponent(ADDITIONAL_OPTIONS_TITLE)
                    .addComponent(BASIC_INFORMATION_TITLE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(HOME_ADDRESS_TITLE)
                        .addGap(94, 94, 94)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(DATE_OF_BIRTH_INPUT, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(DD_MM_YYYY, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(NAME_INPUT, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(SURNAME_TUSSENVOEGSEL_INPUT, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(SURNAME_INPUT, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(TEMP_POSTAL_CODE_INPUT, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TEMP_STREET_INPUT, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TEMP_CITY_INPUT, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(HOME_POSTAL_CODE_INPUT, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(HOME_STREET_INPUT, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(HOME_CITY_INPUT, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(HOME_COUNTRY_INPUT, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(HOME_PHONE_NUMBER_INPUT, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(MOBILE_PHONE_NUMBER_INPUT, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(189, 189, 189)
                        .addComponent(TEMP_COUNTRY_INPUT, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SURNAME)
                        .addGap(4, 4, 4))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(SURNAME_TUSSENVOEGSEL_INPUT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(SURNAME_INPUT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DATE_OF_BIRTH)
                    .addComponent(DD_MM_YYYY)
                    .addComponent(DATE_OF_BIRTH_INPUT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addComponent(TEMPORARY_ADDRESS_TITLE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ADDITIONAL_OPTIONS_TITLE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PRINT_ON_REGISTER)
                    .addComponent(PAIR_PASSENGER_LUGGAGE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PRINT)
                    .addComponent(REGISTER))
                .addContainerGap())
        );
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
            homeAddress.setAddressid(1);
            tempAddress.setAddressid(2);
            passenger.setHomeaddress(homeAddress);
            passenger.setTempaddress(tempAddress);
            model.AddressDAO.create(homeAddress);
            model.AddressDAO.create(tempAddress);

            model.PassengerDAO.create(passenger);
            main.LuggageTrackerTool2.getInstance().getMainMenu().getPassengerTab().refresh();
            main.LuggageTrackerTool2.getInstance().getMainMenu().getjTabbedPane().setSelectedIndex(0);
            clearFields();

        } catch (SQLException ex) {
            System.err.println("Failed to create passenger.");
            System.err.println("Message: " + ex.getMessage());
            ex.printStackTrace();
        } catch (CustomException cEx) {
            JOptionPane.showMessageDialog(main.LuggageTrackerTool2.getInstance().getMainMenu(), cEx.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ADDITIONAL_OPTIONS_TITLE;
    private javax.swing.JLabel BASIC_INFORMATION_TITLE;
    private javax.swing.JLabel DATE_OF_BIRTH;
    private javax.swing.JFormattedTextField DATE_OF_BIRTH_INPUT;
    private javax.swing.JLabel DD_MM_YYYY;
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
    private javax.swing.JLabel NAME;
    private javax.swing.JTextField NAME_INPUT;
    private javax.swing.JCheckBox PAIR_PASSENGER_LUGGAGE;
    private javax.swing.JButton PRINT;
    private javax.swing.JCheckBox PRINT_ON_REGISTER;
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
    private javax.swing.JToggleButton jToggleButton1;
    // End of variables declaration//GEN-END:variables
}
