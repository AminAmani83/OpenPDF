package com.lowagie.text;

import com.lowagie.text.pdf.PdfDocument;

public class MetaSubject extends Meta {

    public MetaSubject(String content) {
        super(Element.SUBJECT, content);
    }

    @Override
    public boolean add(PdfDocument pdfDocument) throws DocumentException {
        pdfDocument.getInfo().addSubject(getContent());
        return true;
    }
}
