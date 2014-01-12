/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import main.CustomException;
import model.Address;
import model.AddressDAO;
import model.Luggage;
import model.LuggageDAO;
import model.Passenger;
import model.PassengerDAO;

/**
 *
 * @author Tomas Slaman
 */
public class EditPanel extends javax.swing.JPanel {

    private List<Luggage> connectedLuggages;
    private JFrame frame;
    private Passenger p = null;
    private boolean addressesWereEqual = false;

    /**
     * Creates new form EditPanel
     *
     * @param holdingFrame
     */
    public EditPanel(JFrame holdingFrame) {
        initComponents();
        this.GENDERGROUP.add(MALE_BUTTON);
        this.GENDERGROUP.add(FEMALE_BUTTON);
        this.frame = holdingFrame;
        this.connectedLuggages = new ArrayList<Luggage>();
        setDocumentListeners();

    }

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

    public void fillPassengerInformation(Passenger passenger) {
        p = passenger;
        Address ha = passenger.getHomeaddress();
        Address ta = passenger.getTempaddress();
        this.NAME_INPUT.setText(passenger.getName());
        this.SURNAME_TUSSENVOEGSEL_INPUT.setText(passenger.getInsertion());
        this.SURNAME_INPUT.setText(passenger.getSurname());
        this.DATE_OF_BIRTH_INPUT.setText(passenger.getDob().toString());
        if (passenger.getGender().equals("m")) {
            this.MALE_BUTTON.setSelected(true);
        } else {
            this.FEMALE_BUTTON.setSelected(true);
        }
        this.HOME_PHONE_NUMBER_INPUT.setText(passenger.getHomephone());
        this.MOBILE_PHONE_NUMBER_INPUT.setText(passenger.getMobphone());

        this.HOME_COUNTRY_INPUT.setSelectedItem(ha.getCountry());
        this.HOME_CITY_INPUT.setText(ha.getCity());
        this.HOME_STREET_INPUT.setText(ha.getStreetname());
        this.HOME_POSTAL_CODE_INPUT.setText(ha.getZipcode());

        this.TEMP_COUNTRY_INPUT.setSelectedItem(ta.getCountry());
        this.TEMP_CITY_INPUT.setText(ta.getCity());
        this.TEMP_STREET_INPUT.setText(ta.getStreetname());
        this.TEMP_POSTAL_CODE_INPUT.setText(ta.getZipcode());

        if (addressesAreEqual()) {
            this.EQUALCHECKBOX.setSelected(true);
            this.setAddressesWereEqual(true);
        } else {
            this.EQUALCHECKBOX.setSelected(false);
            this.setAddressesWereEqual(false);
        }
    }

    public void fillLuggageInformation(Luggage luggage) {
        this.connectedLuggages.add(luggage);
        this.LUGGAGE_LABEL_TEXTBOX.setText(luggage.getLuggageLabel());
        this.CONNECTED_LUGGAGES_CBOX.addItem(luggage.getLuggageid());
        this.DESCRIPTION_INPUT1.setText(luggage.getDescription());
        this.STORAGE_LOCATION_INPUT.setSelectedItem(luggage.getStoragelocation());
        this.ANDERS_INPUT.setText(luggage.getDifferentLocation());
        this.STATUS_COMBOBOX.setSelectedItem(luggage.getLuggagestatus());
        this.setStorageLocationEditability();
    }

    public void populateComboBox(List<Luggage> conLuggages) {
        this.connectedLuggages = conLuggages;
        conLuggages = null;
        for (Luggage l : this.connectedLuggages) {
            this.CONNECTED_LUGGAGES_CBOX.addItem(l.getLuggageid());
        }

        this.CONNECTED_LUGGAGES_CBOX.setSelectedIndex(0);
        setLuggageEditability(true);
    }

    public void setLuggageEditability(Boolean bool) {
        Color c = bool ? Color.WHITE : Color.LIGHT_GRAY;
        this.LUGGAGE_LABEL_TEXTBOX.setEnabled(bool);
        this.CONNECTED_LUGGAGES_CBOX.setEnabled(bool);
        this.DESCRIPTION_INPUT1.setEnabled(bool);
        setStorageLocationEditability();
        this.STATUS_COMBOBOX.setEnabled(bool);
        this.STORAGE_LOCATION_INPUT.setEnabled(bool);
        this.DESCRIPTION_INPUT1.setBackground(c);
        if (bool) {
            //this.PASSENGER_NOT_LINKED.setText("");
        } else {
            //this.PASSENGER_NOT_LINKED.setText("No luggage connected to this passenger.");
        }
    }

    /*public void setLuggageColors(Color c) {
     this.CONNECTED_LUGGAGES_CBOX.setBackground(c);
     this.DESCRIPTION_INPUT1.setBackground(c);
     this.STATUS_COMBOBOX.setBackground(c);
     }*/
    public void setPassengerEditability(Boolean bool) {
        //Color c = bool ? Color.WHITE : Color.LIGHT_GRAY;
        this.NAME_INPUT.setEnabled(bool);
        this.SURNAME_INPUT.setEnabled(bool);
        this.SURNAME_TUSSENVOEGSEL_INPUT.setEnabled(bool);
        this.DATE_OF_BIRTH_INPUT.setEnabled(bool);
        this.MALE_BUTTON.setEnabled(bool);
        this.FEMALE_BUTTON.setEnabled(bool);
        this.HOME_PHONE_NUMBER_INPUT.setEnabled(bool);
        this.MOBILE_PHONE_NUMBER_INPUT.setEnabled(bool);

        this.HOME_COUNTRY_INPUT.setEnabled(bool);
        this.HOME_CITY_INPUT.setEnabled(bool);
        this.HOME_STREET_INPUT.setEnabled(bool);
        this.HOME_POSTAL_CODE_INPUT.setEnabled(bool);

        this.TEMP_COUNTRY_INPUT.setEnabled(bool);
        this.TEMP_CITY_INPUT.setEnabled(bool);
        this.TEMP_STREET_INPUT.setEnabled(bool);
        this.TEMP_POSTAL_CODE_INPUT.setEnabled(bool);
        if (bool) {
            this.LUGGAGE_NOT_LINKED_LABEL.setText("");
        } else {
            this.LUGGAGE_NOT_LINKED_LABEL.setText("No passenger linked to this luggage.");
        }
        //setPassengerColors(c);

    }

    public void setPassengerColors(Color c) {
        this.NAME_INPUT.setBackground(c);
        this.SURNAME_INPUT.setBackground(c);
        this.DATE_OF_BIRTH_INPUT.setBackground(c);
        this.MALE_BUTTON.setBackground(c);
        this.FEMALE_BUTTON.setBackground(c);
        this.HOME_PHONE_NUMBER_INPUT.setBackground(c);
        this.MOBILE_PHONE_NUMBER_INPUT.setBackground(c);

        this.HOME_CITY_INPUT.setBackground(c);
        this.HOME_STREET_INPUT.setBackground(c);
        this.HOME_POSTAL_CODE_INPUT.setBackground(c);

        if (!EQUALCHECKBOX.isSelected()) {
            TEMP_CITY_INPUT.setBackground(c);
            TEMP_STREET_INPUT.setBackground(c);
            TEMP_POSTAL_CODE_INPUT.setBackground(c);
        }
    }

    public void setStorageLocationEditability() {
        if (STORAGE_LOCATION_INPUT.getSelectedItem().toString().equals("Other")) {
            ANDERS_INPUT.setEnabled(true);
            ANDERS_INPUT.setBackground(Color.WHITE);
        } else if (ANDERS_INPUT.isEditable()) {
            ANDERS_INPUT.setEnabled(false);
            ANDERS_INPUT.setBackground(Color.lightGray);
        }
    }

    public model.Passenger createPassenger(int id) throws CustomException {
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
                            throw new CustomException("Date of birth year is incorrect.", DATE_OF_BIRTH_INPUT);
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
        tempPassenger.setDob(dob);
        tempPassenger.setGender(gender);
        tempPassenger.setHomephone(homePhone);
        tempPassenger.setMobphone(mobPhone);
        tempPassenger.setHomeaddress(p.getHomeaddress());
        tempPassenger.setTempaddress(p.getTempaddress());
        tempPassenger.setHomeaddressid(p.getHomeaddressid());
        tempPassenger.setTempaddressid(p.getTempaddressid());
        tempPassenger.setPassengerid(id);

        return tempPassenger;
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

    public void updateSelectedLuggage() throws CustomException {
        Luggage selectedLuggage = this.connectedLuggages.get(this.CONNECTED_LUGGAGES_CBOX.getSelectedIndex());

        String label = this.LUGGAGE_LABEL_TEXTBOX.getText();
        String description = this.DESCRIPTION_INPUT1.getText();
        String storage = this.STORAGE_LOCATION_INPUT.getSelectedItem().toString();
        String different = this.ANDERS_INPUT.getText();
        String status = this.STATUS_COMBOBOX.getSelectedItem().toString();

        if (label.length() > 50) {
            throw new CustomException("Luggage Label length is too long, 50 characters maximum. You've got: " + label.length(), this.LUGGAGE_LABEL_TEXTBOX);
        }
        if (label.equals("")) {
            label = null;
        }
        if (description.length() == 0) {
            throw new CustomException("A luggage description is required!", this.DESCRIPTION_INPUT1);
        }
        if (description.length() > 200) {
            throw new CustomException("Description is too long, 200 characters maximum. You've got: " + description.length(), this.DESCRIPTION_INPUT1);
        }
        if (different.length() > 200) {
            throw new CustomException("Other field has too much characters, 200 maximum. You've got: " + different.length(), this.ANDERS_INPUT);
        }
        if (!storage.equals("Other")) {
            different = null;
        }
        if (storage.length() > 45) {
            throw new CustomException("Storage length is too long, 45 characters maximum. You've got: " + storage.length(), this.STORAGE_LOCATION_INPUT);
        }
        if (status.length() > 20) {
            throw new CustomException("Status length is too long, 20 characters maximum. You've got: " + status.length(), this.STATUS_COMBOBOX);
        }

        selectedLuggage.setLuggageLabel(label);
        selectedLuggage.setDescription(description);
        selectedLuggage.setStoragelocation(storage);
        selectedLuggage.setDifferentLocation(different);
        selectedLuggage.setLuggagestatus(status);
    }

    public model.Address createHomeAddressFromForms(int addressID) throws CustomException {
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
        address.setAddressid(addressID);
        address.setCity(city);
        address.setCountry(country);
        address.setStreetname(streetName);
        address.setZipcode(zipCode);
        return address;
    }

    public model.Address createTempAddressFromForms(int addressID) throws CustomException {
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
        address.setAddressid(addressID);
        address.setCity(city);
        address.setCountry(country);
        address.setStreetname(streetName);
        address.setZipcode(zipCode);
        return address;
    }

    public boolean addressesAreEqual() {
        return ((this.HOME_COUNTRY_INPUT.getSelectedIndex() == this.TEMP_COUNTRY_INPUT.getSelectedIndex())
                && (this.HOME_CITY_INPUT.getText().equals(this.TEMP_CITY_INPUT.getText()))
                && (this.HOME_POSTAL_CODE_INPUT.getText().equals(this.TEMP_POSTAL_CODE_INPUT.getText()))
                && (this.HOME_STREET_INPUT.getText().equals(this.TEMP_STREET_INPUT.getText())));
    }

    public void setAddressesWereEqual(boolean bool) {
        this.addressesWereEqual = bool;
    }

    public boolean getAddressesWereEqual() {
        return this.addressesWereEqual;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        GENDERGROUP = new javax.swing.ButtonGroup();
        NAME_INPUT = new javax.swing.JTextField();
        SURNAME_TUSSENVOEGSEL_INPUT = new javax.swing.JTextField();
        SURNAME_INPUT = new javax.swing.JTextField();
        DATE_OF_BIRTH_INPUT = new javax.swing.JFormattedTextField();
        DD_MM_YYYY = new javax.swing.JLabel();
        FEMALE_BUTTON = new javax.swing.JRadioButton();
        MALE_BUTTON = new javax.swing.JRadioButton();
        HOME_PHONE_NUMBER_INPUT = new javax.swing.JTextField();
        MOBILE_PHONE_NUMBER_INPUT = new javax.swing.JTextField();
        HOME_COUNTRY_INPUT = new javax.swing.JComboBox();
        HOME_CITY_INPUT = new javax.swing.JTextField();
        HOME_STREET_INPUT = new javax.swing.JTextField();
        HOME_POSTAL_CODE_INPUT = new javax.swing.JTextField();
        TEMP_COUNTRY_INPUT = new javax.swing.JComboBox();
        TEMP_CITY_INPUT = new javax.swing.JTextField();
        TEMP_STREET_INPUT = new javax.swing.JTextField();
        TEMP_POSTAL_CODE_INPUT = new javax.swing.JTextField();
        TEMP_POSTAL_CODE = new javax.swing.JLabel();
        TEMP_STREET = new javax.swing.JLabel();
        TEMP_CITY = new javax.swing.JLabel();
        TEMP_COUNTRY = new javax.swing.JLabel();
        TEMPORARY_ADDRESS_TITLE = new javax.swing.JLabel();
        HOME_POSTAL_CODE = new javax.swing.JLabel();
        HOME_STREET = new javax.swing.JLabel();
        HOME_CITY = new javax.swing.JLabel();
        HOME_COUNTRY = new javax.swing.JLabel();
        HOME_ADDRESS_TITLE = new javax.swing.JLabel();
        MOBILE_PHONE_NUMBER = new javax.swing.JLabel();
        HOME_PHONE_NUMBER = new javax.swing.JLabel();
        GENDER = new javax.swing.JLabel();
        DATE_OF_BIRTH = new javax.swing.JLabel();
        SURNAME = new javax.swing.JLabel();
        NAME = new javax.swing.JLabel();
        BASIC_INFORMATION_TITLE = new javax.swing.JLabel();
        CONNECTED_LUGGAGES_CBOX = new javax.swing.JComboBox();
        DESCRIPTION = new javax.swing.JLabel();
        DESCRIPTION_INPUT_FRAME = new javax.swing.JScrollPane();
        DESCRIPTION_INPUT1 = new javax.swing.JTextArea();
        STORAGE_LOCATION_INPUT = new javax.swing.JComboBox();
        ANDERS_INPUT_FRAME = new javax.swing.JScrollPane();
        ANDERS_INPUT = new javax.swing.JTextArea();
        STORAGE_LOCATION = new javax.swing.JLabel();
        ANDERS = new javax.swing.JLabel();
        LUGGAGE_INFORMATION_LABEL = new javax.swing.JLabel();
        CONNECTED_LUGGAGES_LABEL = new javax.swing.JLabel();
        LUGGAGE_NOT_LINKED_LABEL = new javax.swing.JLabel();
        LUGGAGE_NOT_LINKED_COPY = new javax.swing.JLabel();
        SAVE_BUTTON = new javax.swing.JButton();
        CANCEL_BUTTON = new javax.swing.JButton();
        STATUS_LABEL = new javax.swing.JLabel();
        STATUS_COMBOBOX = new javax.swing.JComboBox();
        LUGGAGE_LABEL_TEXTBOX = new javax.swing.JTextField();
        LUGGAGE_LABEL = new javax.swing.JLabel();
        PASSENGER_NOT_LINKED = new javax.swing.JLabel();
        EQUALCHECKBOX = new javax.swing.JCheckBox();

        setName(""); // NOI18N

        DATE_OF_BIRTH_INPUT.setColumns(3);

        DD_MM_YYYY.setFont(new java.awt.Font("Tahoma", 2, 13)); // NOI18N
        DD_MM_YYYY.setText("(YYYY-MM-DD)");

        FEMALE_BUTTON.setText("Female");

        MALE_BUTTON.setSelected(true);
        MALE_BUTTON.setText("Male");

        HOME_COUNTRY_INPUT.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Please select your country ..", "The Netherlands", "Germany", "Belgium" }));
        HOME_COUNTRY_INPUT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HOME_COUNTRY_INPUTActionPerformed(evt);
            }
        });

        TEMP_COUNTRY_INPUT.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Please select your country ..", "The Netherlands", "Germany", "Belgium" }));

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, EQUALCHECKBOX, org.jdesktop.beansbinding.ELProperty.create("${!selected}"), TEMP_COUNTRY_INPUT, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, EQUALCHECKBOX, org.jdesktop.beansbinding.ELProperty.create("${!selected}"), TEMP_CITY_INPUT, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, EQUALCHECKBOX, org.jdesktop.beansbinding.ELProperty.create("${!selected}"), TEMP_STREET_INPUT, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, EQUALCHECKBOX, org.jdesktop.beansbinding.ELProperty.create("${!selected}"), TEMP_POSTAL_CODE_INPUT, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        TEMP_POSTAL_CODE.setText("Postal Code");

        TEMP_STREET.setText("Street");

        TEMP_CITY.setText("City");

        TEMP_COUNTRY.setText("Country");

        TEMPORARY_ADDRESS_TITLE.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        TEMPORARY_ADDRESS_TITLE.setText("Temporary Address");

        HOME_POSTAL_CODE.setText("Postal Code");

        HOME_STREET.setText("Street");

        HOME_CITY.setText("City");

        HOME_COUNTRY.setText("Country");

        HOME_ADDRESS_TITLE.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        HOME_ADDRESS_TITLE.setText("Home Address");

        MOBILE_PHONE_NUMBER.setText("Mobile Phone Number");
        MOBILE_PHONE_NUMBER.setAlignmentY(0.0F);

        HOME_PHONE_NUMBER.setText("Home Phone Number");

        GENDER.setText("Gender");

        DATE_OF_BIRTH.setText("Date of Birth");

        SURNAME.setText("Surname");

        NAME.setText("Name");

        BASIC_INFORMATION_TITLE.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        BASIC_INFORMATION_TITLE.setText("Basic Information");

        CONNECTED_LUGGAGES_CBOX.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CONNECTED_LUGGAGES_CBOXMouseClicked(evt);
            }
        });
        CONNECTED_LUGGAGES_CBOX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CONNECTED_LUGGAGES_CBOXActionPerformed(evt);
            }
        });

        DESCRIPTION.setText("Description");

        DESCRIPTION_INPUT1.setColumns(20);
        DESCRIPTION_INPUT1.setRows(5);
        DESCRIPTION_INPUT_FRAME.setViewportView(DESCRIPTION_INPUT1);

        STORAGE_LOCATION_INPUT.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Storage A", "Storage B", "Storage C", "Other" }));
        STORAGE_LOCATION_INPUT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                STORAGE_LOCATION_INPUTActionPerformed(evt);
            }
        });

        ANDERS_INPUT.setColumns(20);
        ANDERS_INPUT.setRows(5);
        ANDERS_INPUT.setText("Vul hier zo specifiek mogelijk de\ngegevens in van de opslagplaats.");
        ANDERS_INPUT_FRAME.setViewportView(ANDERS_INPUT);

        STORAGE_LOCATION.setText("Storage Location");

        ANDERS.setFont(new java.awt.Font("Tahoma", 2, 13)); // NOI18N
        ANDERS.setText("Other");

        LUGGAGE_INFORMATION_LABEL.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        LUGGAGE_INFORMATION_LABEL.setText("Luggage Information");

        CONNECTED_LUGGAGES_LABEL.setText("Connected Luggage");

        LUGGAGE_NOT_LINKED_LABEL.setForeground(java.awt.Color.red);
        LUGGAGE_NOT_LINKED_LABEL.setToolTipText("");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, LUGGAGE_NOT_LINKED_LABEL, org.jdesktop.beansbinding.ELProperty.create("${foreground}"), LUGGAGE_NOT_LINKED_COPY, org.jdesktop.beansbinding.BeanProperty.create("foreground"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, LUGGAGE_NOT_LINKED_LABEL, org.jdesktop.beansbinding.ELProperty.create("${text}"), LUGGAGE_NOT_LINKED_COPY, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        SAVE_BUTTON.setText("Save");
        SAVE_BUTTON.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SAVE_BUTTONActionPerformed(evt);
            }
        });

        CANCEL_BUTTON.setText("Cancel");
        CANCEL_BUTTON.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CANCEL_BUTTONActionPerformed(evt);
            }
        });

        STATUS_LABEL.setText("Status");

        STATUS_COMBOBOX.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Missing", "Found", "Returned", "Destroyed" }));

        LUGGAGE_LABEL.setText("Luggage Label");

        PASSENGER_NOT_LINKED.setForeground(java.awt.Color.red);
        PASSENGER_NOT_LINKED.setToolTipText("");

        EQUALCHECKBOX.setSelected(true);
        EQUALCHECKBOX.setText("Same as Home Address");
        EQUALCHECKBOX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EQUALCHECKBOXActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(BASIC_INFORMATION_TITLE)
                                .addGap(18, 18, 18)
                                .addComponent(LUGGAGE_NOT_LINKED_LABEL))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(SURNAME)
                                            .addComponent(DATE_OF_BIRTH)
                                            .addComponent(GENDER)
                                            .addComponent(NAME)))
                                    .addComponent(MOBILE_PHONE_NUMBER)
                                    .addComponent(HOME_PHONE_NUMBER)
                                    .addComponent(HOME_ADDRESS_TITLE))
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addComponent(MALE_BUTTON)
                                            .addGap(18, 18, 18)
                                            .addComponent(FEMALE_BUTTON)
                                            .addGap(75, 75, 75))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(DATE_OF_BIRTH_INPUT, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                    .addGap(103, 103, 103)
                                                    .addComponent(DD_MM_YYYY, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE))
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                    .addComponent(SURNAME_TUSSENVOEGSEL_INPUT, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(SURNAME_INPUT))
                                                .addComponent(NAME_INPUT))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(MOBILE_PHONE_NUMBER_INPUT, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(HOME_PHONE_NUMBER_INPUT, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(10, 10, 10)
                                                .addComponent(LUGGAGE_NOT_LINKED_COPY))))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(HOME_CITY)
                                            .addComponent(HOME_COUNTRY)
                                            .addComponent(HOME_STREET)
                                            .addComponent(HOME_POSTAL_CODE))
                                        .addGap(56, 56, 56)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(HOME_CITY_INPUT, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(HOME_STREET_INPUT, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(HOME_POSTAL_CODE_INPUT, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(HOME_COUNTRY_INPUT, javax.swing.GroupLayout.Alignment.TRAILING, 0, 200, Short.MAX_VALUE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(TEMP_CITY)
                                            .addComponent(TEMP_COUNTRY)
                                            .addComponent(TEMP_STREET)
                                            .addComponent(TEMP_POSTAL_CODE))
                                        .addGap(56, 56, 56)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(TEMP_COUNTRY_INPUT, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(TEMP_CITY_INPUT, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(TEMP_STREET_INPUT, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(TEMP_POSTAL_CODE_INPUT, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(EQUALCHECKBOX, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(LUGGAGE_INFORMATION_LABEL)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(PASSENGER_NOT_LINKED)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(CANCEL_BUTTON)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(SAVE_BUTTON, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(25, 25, 25)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(LUGGAGE_LABEL)
                                                    .addComponent(DESCRIPTION)
                                                    .addComponent(STORAGE_LOCATION)
                                                    .addComponent(ANDERS, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(STATUS_LABEL))
                                                .addGap(33, 33, 33))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(CONNECTED_LUGGAGES_LABEL)
                                                .addGap(18, 18, 18)))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(LUGGAGE_LABEL_TEXTBOX, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(CONNECTED_LUGGAGES_CBOX, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(DESCRIPTION_INPUT_FRAME, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(STORAGE_LOCATION_INPUT, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(ANDERS_INPUT_FRAME, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(STATUS_COMBOBOX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(TEMPORARY_ADDRESS_TITLE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BASIC_INFORMATION_TITLE)
                            .addComponent(LUGGAGE_NOT_LINKED_LABEL))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(NAME_INPUT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(NAME)
                            .addComponent(CONNECTED_LUGGAGES_LABEL)
                            .addComponent(CONNECTED_LUGGAGES_CBOX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(SURNAME_TUSSENVOEGSEL_INPUT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SURNAME_INPUT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SURNAME)
                            .addComponent(LUGGAGE_LABEL)
                            .addComponent(LUGGAGE_LABEL_TEXTBOX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(LUGGAGE_INFORMATION_LABEL)
                        .addComponent(PASSENGER_NOT_LINKED)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(DESCRIPTION_INPUT_FRAME, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(STORAGE_LOCATION_INPUT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(STORAGE_LOCATION))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(ANDERS_INPUT_FRAME, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(STATUS_COMBOBOX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(STATUS_LABEL)))
                            .addComponent(ANDERS))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(DATE_OF_BIRTH_INPUT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(DD_MM_YYYY)
                            .addComponent(DATE_OF_BIRTH)
                            .addComponent(DESCRIPTION))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(MALE_BUTTON)
                            .addComponent(FEMALE_BUTTON)
                            .addComponent(GENDER))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(HOME_PHONE_NUMBER_INPUT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(HOME_PHONE_NUMBER))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(MOBILE_PHONE_NUMBER_INPUT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(MOBILE_PHONE_NUMBER))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(HOME_ADDRESS_TITLE)
                            .addComponent(LUGGAGE_NOT_LINKED_COPY))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(HOME_COUNTRY_INPUT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(HOME_COUNTRY))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(HOME_CITY_INPUT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(HOME_CITY))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(HOME_STREET_INPUT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(HOME_STREET))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(HOME_POSTAL_CODE_INPUT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(HOME_POSTAL_CODE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TEMPORARY_ADDRESS_TITLE)
                            .addComponent(EQUALCHECKBOX))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TEMP_COUNTRY_INPUT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TEMP_COUNTRY))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TEMP_CITY_INPUT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TEMP_CITY))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TEMP_STREET_INPUT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TEMP_STREET))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TEMP_POSTAL_CODE_INPUT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TEMP_POSTAL_CODE)
                            .addComponent(SAVE_BUTTON)
                            .addComponent(CANCEL_BUTTON))
                        .addGap(25, 25, 25))))
        );

        bindingGroup.bind();
    }// </editor-fold>//GEN-END:initComponents

    private void STORAGE_LOCATION_INPUTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_STORAGE_LOCATION_INPUTActionPerformed
        setStorageLocationEditability();
    }//GEN-LAST:event_STORAGE_LOCATION_INPUTActionPerformed

    private void CONNECTED_LUGGAGES_CBOXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CONNECTED_LUGGAGES_CBOXActionPerformed
        Luggage selectedLuggage = this.connectedLuggages.get(this.CONNECTED_LUGGAGES_CBOX.getSelectedIndex());

        this.LUGGAGE_LABEL_TEXTBOX.setText(selectedLuggage.getLuggageLabel());
        this.DESCRIPTION_INPUT1.setText(selectedLuggage.getDescription());
        this.STORAGE_LOCATION_INPUT.setSelectedItem(selectedLuggage.getStoragelocation());
        this.ANDERS_INPUT.setText(selectedLuggage.getDifferentLocation());
        this.STATUS_COMBOBOX.setSelectedItem(selectedLuggage.getLuggagestatus());

    }//GEN-LAST:event_CONNECTED_LUGGAGES_CBOXActionPerformed

    private void CANCEL_BUTTONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CANCEL_BUTTONActionPerformed
        this.frame.dispose();
    }//GEN-LAST:event_CANCEL_BUTTONActionPerformed

    private void SAVE_BUTTONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SAVE_BUTTONActionPerformed
        boolean delete = false;
        int tempaddr = 0;
        int result = JOptionPane.showConfirmDialog(null,
                "Are you sure you want to save these changes?", "Question",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (result == JOptionPane.YES_OPTION) {
            try {
                if (this.p != null) {
                    p = createPassenger(p.getPassengerid());
                    Address homeAddress = createHomeAddressFromForms(p.getHomeaddressid());
                    Address tempAddress = createTempAddressFromForms(p.getTempaddressid());

                    p.setHomeaddress(homeAddress);
                    p.setTempaddress(tempAddress);
                    p.setHomeaddressid(homeAddress.getAddressid());
                    p.setTempaddressid(tempAddress.getAddressid());
                }
                if (connectedLuggages != null && connectedLuggages.size() > 0) {
                    updateSelectedLuggage();
                }

                if (this.p != null) {
                    AddressDAO.update(p.getHomeaddress());
                    
                    if (this.addressesWereEqual && !this.EQUALCHECKBOX.isSelected())
                    {
                        if (addressesAreEqual())
                        {
                            p.setTempaddressid(p.getHomeaddress().getAddressid());
                            p.setTempaddress(p.getHomeaddress());
                            //No need to update the address again as its the same ID as home ID
                        }
                        else
                            p.setTempaddressid(AddressDAO.create(p.getTempaddress()).get(1));
                    }
                    else if (!this.addressesWereEqual && !this.EQUALCHECKBOX.isSelected())
                    {
                        if (addressesAreEqual())
                        {
                            p.setTempaddressid(p.getHomeaddress().getAddressid());
                            p.setTempaddress(p.getHomeaddress());
                        }
                        else
                            AddressDAO.update(p.getTempaddress());
                    }
                    else if (!this.addressesWereEqual && this.EQUALCHECKBOX.isSelected())
                    {
                        tempaddr = p.getTempaddressid();
                        p.setTempaddressid(p.getHomeaddress().getAddressid());
                        p.setTempaddress(p.getHomeaddress());
                        delete = true;
                    }
                    //else if (this.addressesWereEqual && this.EQUALCHECKBOX.isSelected())
                        // Do nothing, temp already is == to home, no changes to temp needed.                    
                    PassengerDAO.update(p);
                    if (delete)
                        AddressDAO.delete(tempaddr);
                }
                if (connectedLuggages != null && connectedLuggages.size() > 0) {
                    for (Luggage l : connectedLuggages) {
                        LuggageDAO.update(l);
                    }
                }
                main.LuggageTrackerTool2.getInstance().getMainMenu().getInformationPanel().clearLuggageLabels();
                main.LuggageTrackerTool2.getInstance().getMainMenu().getInformationPanel().clearPassengerLabels();
                main.LuggageTrackerTool2.getInstance().setSelectedLuggage(null);
                main.LuggageTrackerTool2.getInstance().setSelectedPassenger(null);
                main.LuggageTrackerTool2.getInstance().getMainMenu().getLuggageTab().refresh();
                main.LuggageTrackerTool2.getInstance().getMainMenu().getPassengerTab().refresh();
                this.frame.dispose();

            } catch (CustomException cEx) {
                JOptionPane.showMessageDialog(main.LuggageTrackerTool2.getInstance().getMainMenu(), cEx.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
                if (cEx.getComponent() != null) {
                    cEx.getComponent().setBackground(Color.RED);
                }
            } catch (SQLException e) {
                System.err.println("Couldn't save edits.");
                System.err.println(e.getMessage());
            }
        } else {
            this.setPassengerColors(Color.WHITE);
        }
    }//GEN-LAST:event_SAVE_BUTTONActionPerformed

    private void CONNECTED_LUGGAGES_CBOXMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CONNECTED_LUGGAGES_CBOXMouseClicked
        try {
            updateSelectedLuggage();
        } catch (CustomException eCx) {
            JOptionPane.showMessageDialog(main.LuggageTrackerTool2.getInstance().getMainMenu(), eCx.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            if (eCx.getComponent() != null) {
                eCx.getComponent().setBackground(Color.RED);
            }
        }
    }//GEN-LAST:event_CONNECTED_LUGGAGES_CBOXMouseClicked

    private void HOME_COUNTRY_INPUTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HOME_COUNTRY_INPUTActionPerformed
        if (this.EQUALCHECKBOX.isSelected()) {
            this.TEMP_COUNTRY_INPUT.setSelectedIndex(this.HOME_COUNTRY_INPUT.getSelectedIndex());
        }
    }//GEN-LAST:event_HOME_COUNTRY_INPUTActionPerformed

    private void EQUALCHECKBOXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EQUALCHECKBOXActionPerformed
        if (EQUALCHECKBOX.isSelected()) {
            TEMP_COUNTRY_INPUT.setSelectedIndex(HOME_COUNTRY_INPUT.getSelectedIndex());
            TEMP_CITY_INPUT.setText(HOME_CITY_INPUT.getText());
            TEMP_STREET_INPUT.setText(HOME_STREET_INPUT.getText());
            TEMP_POSTAL_CODE_INPUT.setText(HOME_POSTAL_CODE_INPUT.getText());
        }
    }//GEN-LAST:event_EQUALCHECKBOXActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ANDERS;
    private javax.swing.JTextArea ANDERS_INPUT;
    private javax.swing.JScrollPane ANDERS_INPUT_FRAME;
    private javax.swing.JLabel BASIC_INFORMATION_TITLE;
    private javax.swing.JButton CANCEL_BUTTON;
    private javax.swing.JComboBox CONNECTED_LUGGAGES_CBOX;
    private javax.swing.JLabel CONNECTED_LUGGAGES_LABEL;
    private javax.swing.JLabel DATE_OF_BIRTH;
    private javax.swing.JFormattedTextField DATE_OF_BIRTH_INPUT;
    private javax.swing.JLabel DD_MM_YYYY;
    private javax.swing.JLabel DESCRIPTION;
    private javax.swing.JTextArea DESCRIPTION_INPUT1;
    private javax.swing.JScrollPane DESCRIPTION_INPUT_FRAME;
    private javax.swing.JCheckBox EQUALCHECKBOX;
    private javax.swing.JRadioButton FEMALE_BUTTON;
    private javax.swing.JLabel GENDER;
    private javax.swing.ButtonGroup GENDERGROUP;
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
    private javax.swing.JLabel LUGGAGE_INFORMATION_LABEL;
    private javax.swing.JLabel LUGGAGE_LABEL;
    private javax.swing.JTextField LUGGAGE_LABEL_TEXTBOX;
    private javax.swing.JLabel LUGGAGE_NOT_LINKED_COPY;
    private javax.swing.JLabel LUGGAGE_NOT_LINKED_LABEL;
    private javax.swing.JRadioButton MALE_BUTTON;
    private javax.swing.JLabel MOBILE_PHONE_NUMBER;
    private javax.swing.JTextField MOBILE_PHONE_NUMBER_INPUT;
    private javax.swing.JLabel NAME;
    private javax.swing.JTextField NAME_INPUT;
    private javax.swing.JLabel PASSENGER_NOT_LINKED;
    private javax.swing.JButton SAVE_BUTTON;
    private javax.swing.JComboBox STATUS_COMBOBOX;
    private javax.swing.JLabel STATUS_LABEL;
    private javax.swing.JLabel STORAGE_LOCATION;
    private javax.swing.JComboBox STORAGE_LOCATION_INPUT;
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
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
