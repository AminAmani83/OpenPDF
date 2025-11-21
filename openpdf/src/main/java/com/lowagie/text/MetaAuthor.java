package com.lowagie.text;

import com.lowagie.text.pdf.PdfDocument;

public class MetaAuthor extends Meta {

    public MetaAuthor(String content) {
        super(Element.AUTHOR, content);
    }

    @Override
    public boolean add(PdfDocument pdfDocument) throws DocumentException {
        pdfDocument.getInfo().addAuthor(getContent());
        return true;
    }
}