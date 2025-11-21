package com.lowagie.text;

import com.lowagie.text.pdf.PdfDocument;

public class MetaTitle extends Meta {

    public MetaTitle(String content) {
        super(Element.TITLE, content);
    }

    @Override
    public boolean add(PdfDocument pdfDocument) throws DocumentException {
        pdfDocument.getInfo().addTitle(getContent());
        return true;
    }
}

