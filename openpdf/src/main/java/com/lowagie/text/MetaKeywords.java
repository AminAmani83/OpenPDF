package com.lowagie.text;

import com.lowagie.text.pdf.PdfDocument;

public class MetaKeywords extends Meta {

    public MetaKeywords(String content) {
        super(Element.KEYWORDS, content);
    }

    @Override
    public boolean add(PdfDocument pdfDocument) throws DocumentException {
        pdfDocument.getInfo().addKeywords(getContent());
        return true;
    }
}


