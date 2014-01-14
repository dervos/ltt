/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;


import model.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;


public class PDFGenerator {
    
    PDDocument document;
    PDPageContentStream contentStream;
    

    public PDFGenerator() {
        try {
            // Create a document and add a page to it
            this.document = new PDDocument();
            PDPage page = new PDPage();
            this.document.addPage(page);

            // Start a new content stream which will "hold" the to be created content
            this.contentStream = new PDPageContentStream(document, page);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void generate(Passenger passenger, Luggage luggage){
         
         DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
         String basicInformationOutput[] = {"Basic information", "Name: ", "Surname: ", "Date of birth: ", "Gender: ", "Home phone number: ", "Mobile phone number:", "Home address", "Country: ", "City: ", "Street: ", "Postal code: ", "Temporary address", "Country: ", "City: ", "Street: ", "Postal code: ", "Luggage", "Label Id: " ,"Label number: " ,"Description: ", "Storage location: ", "Status: "};
         String gegevens = null;
         try {
            int x = 75;
            int y = 725;
         
            
            // standard information output in pdf
            for (int i = 0; i < basicInformationOutput.length; i++) {
                this.contentStream.beginText();
                this.contentStream.moveTextPositionByAmount(x, y);
                this.contentStream.setFont(PDType1Font.HELVETICA, 12);
             
                //headers
                if (i == 0) {
                    this.contentStream.setFont(PDType1Font.HELVETICA_BOLD, 16);
                  
                }
                if (i == 7) {
                    this.contentStream.setFont(PDType1Font.HELVETICA_BOLD, 16);
                  
                   
                }
                if (i == 12) {
                    this.contentStream.setFont(PDType1Font.HELVETICA_BOLD, 16);
                    
                    
                }
                if (i == 17) {
                    this.contentStream.setFont(PDType1Font.HELVETICA_BOLD, 16);
                   
                }
                if (i == 23) {
                    this.contentStream.setFont(PDType1Font.HELVETICA_BOLD, 16);
                     
            }else {
                    this.contentStream.drawString(basicInformationOutput[i]);  
                    
                    //basic information output
                    y -= 25;
                    switch (i){
                        case 0: gegevens = "";
                            break;
                        case 1: gegevens = passenger.getName();
                            break;
                        case 2: gegevens = passenger.getSurname();
                            break;
                        case 3: gegevens = df.format(passenger.getDob());
                            break;
                        case 4: gegevens = passenger.getGender();
                            break;
                        case 5: gegevens = passenger.getHomephone();
                            break;
                        case 6: gegevens = passenger.getMobphone();
                            break;
                        case 7: gegevens = "";
                            break;
                        case 8: gegevens = passenger.getHomeaddress().getCountry();
                            break;
                        case 9: gegevens = passenger.getHomeaddress().getCity();
                            break;
                        case 10: gegevens = passenger.getHomeaddress().getStreetname();
                            break;
                        case 11: gegevens = passenger.getHomeaddress().getZipcode();
                            break;
                        case 12: gegevens = "";
                            break;
                        case 13: gegevens = passenger.getTempaddress().getCountry();
                            break;
                        case 14: gegevens = passenger.getTempaddress().getCity();
                            break;
                        case 15: gegevens = passenger.getTempaddress().getStreetname();
                            break;
                        case 16: gegevens = passenger.getTempaddress().getZipcode();
                            break;
                        case 17: gegevens = "";
                            break;
                        case 18: gegevens = luggage.getLuggageid().toString();
                            break;
                        case 19: gegevens = luggage.getLuggageLabel();
                            break;
                        case 20: gegevens = luggage.getDescription();
                            break;        
                        case 21: gegevens = luggage.getStoragelocation();
                                break;
                        case 22: gegevens = luggage.getLuggagestatus().name();
                            break;
                        default:
                            break;
                            
                      
                    }
                    this.contentStream.drawString(gegevens); 
                }
                          
                this.contentStream.endText();
                this.contentStream.close();
               
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
     
    public void save(String filename) {
        try {
            
            this.contentStream.close();

            // Save the results and ensure that the document is properly closed:
            this.document.save(filename);
            this.document.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
        public static int stringWidth(String s, PDFont font, double fontSize) {
        try {
            return (int) (font.getStringWidth(s) * fontSize / 1000) + 1;
        } catch (IOException ex) {
            // ignore, but return 0
            return 0;
        }
    }
}
    

