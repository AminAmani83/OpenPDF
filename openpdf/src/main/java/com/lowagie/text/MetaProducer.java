package com.lowagie.text;

import com.lowagie.text.pdf.PdfDocument;

public class MetaProducer extends Meta {

    public MetaProducer(String content) {
        super(Element.PRODUCER, content);
    }

    @Override
    public boolean add(PdfDocument pdfDocument) throws DocumentException {
        pdfDocument.getInfo().addProducer(getContent());
        return true;
    }
}
