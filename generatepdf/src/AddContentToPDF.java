import java.io.*;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.*;

public class AddContentToPDF {

	public static void main(String[] args) {

        /* example inspired from "iText in action" (2006), chapter 2 */

        PdfReader reader = null;
		try {
			reader = new PdfReader("/Users/veronica/Documents/test.pdf");
		} catch (IOException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
		} // input PDF
        PdfStamper stamper = null;
		try {
			stamper = new PdfStamper(reader,
			  new FileOutputStream("/Users/veronica/Documents/test_modified.pdf"));
		} catch (FileNotFoundException e) {
			System.out.println("Line 25");
			System.exit(0);
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // output PDF
        BaseFont bf = null;
		try {
			bf = BaseFont.createFont(
			        BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			System.out.println("Line 38");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // set font

        //loop on pages (1-based)
        for (int i=1; i<=reader.getNumberOfPages(); i++){

            // get object for writing over the existing content;
            // you can also use getUnderContent for writing in the bottom layer
            PdfContentByte over = stamper.getOverContent(i);

            // write text
            over.beginText();
            over.setFontAndSize(bf, 10);    // set font and size
            over.setTextMatrix(100, 100);   // set x,y position (0,0 is at the bottom left)
            over.showText("I can write at page " + i);  // set text
            over.endText();

            // draw a red circle
            over.setRGBColorStroke(0xFF, 0x00, 0x00);
            over.setLineWidth(5f);
            over.ellipse(250, 450, 350, 550);
            over.stroke();
        }

        try {
			stamper.close();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
}