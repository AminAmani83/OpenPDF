package com.lowagie.text;

import com.lowagie.text.pdf.PdfDocument;

/**
 * Interface for a non-text element.
 */
public interface SpecialElement {
    void flushSpecial(PdfDocument pdfDocument);
}
