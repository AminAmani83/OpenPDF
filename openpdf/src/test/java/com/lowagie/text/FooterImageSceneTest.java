package com.lowagie.text;

import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.lowagie.text.pdf.PdfWriter;

public class FooterImageSceneTest {
    @Test
    public void centerUnderlyingPositionSceneTest() throws IOException {
        Document document = new Document(PageSize.A4);
        Image jpg = Image.getInstance("src/test/resources/GitHub-Mark-32px.png");
        jpg.setAlignment(Image.UNDERLYING);

        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("testCenterPosition.pdf"));

        Paragraph footerParagraph = new Paragraph();
        String test = "This is a test for team smellbusters.";
        footerParagraph.add(jpg);
        HeaderFooter footer = new HeaderFooter(footerParagraph, true);
        footer.setAlignment(Element.ALIGN_CENTER);
        document.setFooter(footer);

        document.open();
        document.add(new Paragraph(test));
        document.close();
    }

    public static void main(String[] args) throws IOException {
        FooterImageSceneTest test = new FooterImageSceneTest();
        test.centerUnderlyingPositionSceneTest();
        System.out.println("PDF generated: testCenterPosition.pdf");
    }
}

