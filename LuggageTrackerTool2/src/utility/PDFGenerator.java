/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;


import model.*;

import java.io.IOException;
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
    
    public void generate(Passenger passenger, Address HomeAddress, Address TempAddress){
         String basicInformationOutput[] = {"Basic information", "Name: ", "Surname: ", "Date of birth: ", "Gender: ", "Home phone number: ", "Mobile phone number:", "Home address", "Country: ", "City: ", "Street: ", "Postal code: ", "Temporary address", "Country: ", "City: ", "Street: ", "Postal code: ", "Luggage", "Description: ", "Storage location: ", "Otherwise: "};
         String gegevens = null;
         try {
            int x = 75;
            int y = 725;
         
            
            // standard information output in pdf
            for (int i = 0; i < basicInformationOutput.length; i++) {
                this.contentStream.beginText();
            this.contentStream.moveTextPositionByAmount(x, y);
                
                //headers
                if (i == 0) {
                    this.contentStream.setFont(PDType1Font.HELVETICA_BOLD, 16);
                    this.contentStream.drawString(basicInformationOutput[i]);
                }
                if (i == 7) {
                    this.contentStream.setFont(PDType1Font.HELVETICA_BOLD, 16);
                    this.contentStream.drawString(basicInformationOutput[i]);
                   
                }
                if (i == 12) {
                    this.contentStream.setFont(PDType1Font.HELVETICA_BOLD, 16);
                    this.contentStream.drawString(basicInformationOutput[i]);
                    
                }
                if (i == 17) {
                    this.contentStream.setFont(PDType1Font.HELVETICA_BOLD, 16);
                    this.contentStream.drawString(basicInformationOutput[i]);
                    //basic information output
                } else {

                    this.contentStream.setFont(PDType1Font.HELVETICA, 12);
                     
                    
                    
                    y -= 25;
                    switch (i){
                        case 1: gegevens = passenger.getName();
                            break;
                        case 2: gegevens = passenger.getSurname();
                            break;
                       
                    }
                    this.contentStream.drawString(basicInformationOutput[i] + gegevens); 
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
    

