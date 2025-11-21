package com.lowagie.text;

import com.lowagie.text.pdf.PdfDocument;

public class MetaCreationDate extends Meta {

    public MetaCreationDate(String content) {
        super(Element.CREATIONDATE, content);
    }

    @Override
    public boolean add(PdfDocument pdfDocument) throws DocumentException {
        pdfDocument.getInfo().addCreationDate(); // you can not set the creation date, only reset it
        return true;
    }
}

