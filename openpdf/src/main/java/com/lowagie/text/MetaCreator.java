package com.lowagie.text;

import com.lowagie.text.pdf.PdfDocument;

public class MetaCreator extends Meta {

    public MetaCreator(String content) {
        super(Element.CREATOR, content);
    }

    @Override
    public boolean add(PdfDocument pdfDocument) throws DocumentException {
        pdfDocument.getInfo().addCreator(getContent());
        return true;
    }
}
