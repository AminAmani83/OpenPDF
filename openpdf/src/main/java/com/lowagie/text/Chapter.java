/*
 * $Id: Chapter.java 3373 2008-05-12 16:21:24Z xlv $
 *
 * Copyright 1999, 2000, 2001, 2002 by Bruno Lowagie.
 *
 * The contents of this file are subject to the Mozilla Public License Version 1.1
 * (the "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the License.
 *
 * The Original Code is 'iText, a free JAVA-PDF library'.
 *
 * The Initial Developer of the Original Code is Bruno Lowagie. Portions created by
 * the Initial Developer are Copyright (C) 1999, 2000, 2001, 2002 by Bruno Lowagie.
 * All Rights Reserved.
 * Co-Developer of the code is Paulo Soares. Portions created by the Co-Developer
 * are Copyright (C) 2000, 2001, 2002 by Paulo Soares. All Rights Reserved.
 *
 * Contributor(s): all the names of the contributors are added in the source code
 * where applicable.
 *
 * Alternatively, the contents of this file may be used under the terms of the
 * LGPL license (the "GNU LIBRARY GENERAL PUBLIC LICENSE"), in which case the
 * provisions of LGPL are applicable instead of those above.  If you wish to
 * allow use of your version of this file only under the terms of the LGPL
 * License and not to allow others to use your version of this file under
 * the MPL, indicate your decision by deleting the provisions above and
 * replace them with the notice and other provisions required by the LGPL.
 * If you do not delete the provisions above, a recipient may use your version
 * of this file under either the MPL or the GNU LIBRARY GENERAL PUBLIC LICENSE.
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the MPL as stated above or under the terms of the GNU
 * Library General Public License as published by the Free Software Foundation;
 * either version 2 of the License, or any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Library general Public License for more
 * details.
 *
 * If you didn't download this code from the following link, you should check if
 * you aren't using an obsolete version:
 * https://github.com/LibrePDF/OpenPDF
 *
 */

package com.lowagie.text;

import com.lowagie.text.pdf.PdfDestination;
import com.lowagie.text.pdf.PdfDocument;
import com.lowagie.text.pdf.PdfOutline;
import com.lowagie.text.pdf.PdfPageEvent;

import java.util.ArrayList;

/**
 * A <CODE>Chapter</CODE> is a special <CODE>Section</CODE>.
 * <P>
 * A chapter number has to be created using a <CODE>Paragraph</CODE> as title
 * and an <CODE>int</CODE> as chapter number. The chapter number is shown be
 * default. If you don't want to see the chapter number, you have to set the
 * numberdepth to <VAR>0</VAR>.
 * <P>
 * Example:
 * <BLOCKQUOTE><PRE>
 * Paragraph title2 = new Paragraph("This is Chapter 2", FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLDITALIC, new Color(0, 0, 255)));
 * <STRONG>Chapter chapter2 = new Chapter(title2, 2);</STRONG>
 * <STRONG>chapter2.setNumberDepth(0);</STRONG>
 * Paragraph someText = new Paragraph("This is some text");
 * <STRONG>chapter2.add(someText);</STRONG>
 * Paragraph title21 = new Paragraph("This is Section 1 in Chapter 2", FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD, new Color(255, 0, 0)));
 * Section section1 = <STRONG>chapter2.addSection(title21);</STRONG>
 * Paragraph someSectionText = new Paragraph("This is some silly paragraph in a chapter and/or section. It contains some text to test the functionality of Chapters and Section.");
 * section1.add(someSectionText);
 * </PRE></BLOCKQUOTE>
 */

public class Chapter extends Section {
    
    // constant
    private static final long serialVersionUID = 1791000695779357361L;
    
    /**
     * Constructs a new <CODE>Chapter</CODE>.
     * @param    number        the Chapter number
     */
    public Chapter(int number) {
        super(null, 1);
        numbers = new ArrayList<>();
        numbers.add(number);
        triggerNewPage = true;
    }
    
    /**
     * Constructs a new <CODE>Chapter</CODE>.
     *
     * @param    title        the Chapter title (as a <CODE>Paragraph</CODE>)
     * @param    number        the Chapter number
     */
    
    public Chapter(Paragraph title, int number) {
        super(title, 1);
        numbers = new ArrayList<>();
        numbers.add(number);
        triggerNewPage = true;
    }
    
    /**
     * Constructs a new <CODE>Chapter</CODE>.
     *
     * @param    title        the Chapter title (as a <CODE>String</CODE>)
     * @param    number        the Chapter number
     */
    public Chapter(String title, int number) {
        this(new Paragraph(title), number);
    }
    
    // implementation of the Element-methods
    
    /**
     * Gets the type of the text element.
     *
     * @return    a type
     */
    public int type() {
        return Element.CHAPTER;
    }

    /**
     * @see com.lowagie.text.Element#isNestable()
     * @since    iText 2.0.8
     */
    public boolean isNestable() {
        return false;
    }

    @Override
    public boolean add(PdfDocument pdfDocument) throws DocumentException {
        PdfPageEvent pageEvent = pdfDocument.getWriter().getPageEvent();

        boolean hasTitle = isNotAddedYet()
                && getTitle() != null;

        // if the section is a chapter, we begin a new page
        if (isTriggerNewPage()) {
            newPage();
        }

        if (hasTitle) {
            float fith = pdfDocument.indentTop() - pdfDocument.getCurrentHeight();
            int rotation = pdfDocument.pageSize.getRotation();
            if (rotation == 90 || rotation == 180)
                fith = pdfDocument.pageSize.getHeight() - fith;
            PdfDestination destination = new PdfDestination(PdfDestination.FITH, fith);
            while (pdfDocument.getCurrentOutline().level() >= getDepth()) {
                pdfDocument.setCurrentOutline(pdfDocument.getCurrentOutline().parent());
            }
            PdfOutline outline = new PdfOutline(pdfDocument.getCurrentOutline(), destination, getBookmarkTitle(), isBookmarkOpen());
            pdfDocument.setCurrentOutline(outline);
        }

        // some values are set
        pdfDocument.carriageReturn();
        pdfDocument.getIndentation().sectionIndentLeft += getIndentationLeft();
        pdfDocument.getIndentation().sectionIndentRight += getIndentationRight();

        if (isNotAddedYet() && pageEvent != null)
            pageEvent.onChapter(pdfDocument.getWriter(), pdfDocument, pdfDocument.indentTop() - pdfDocument.getCurrentHeight(), getTitle());

        // the title of the section (if any has to be printed)
        if (hasTitle) {
            pdfDocument.setSectionTitle(true);
            add(getTitle());
            pdfDocument.setSectionTitle(false);
        }
        pdfDocument.getIndentation().sectionIndentLeft += getIndentation();
        // we process the section
        process(pdfDocument);
        pdfDocument.flushLines();
        // some parameters are set back to normal again
        pdfDocument.getIndentation().sectionIndentLeft -= (getIndentationLeft() + getIndentation());
        pdfDocument.getIndentation().sectionIndentRight -= getIndentationRight();

        if (isComplete() && pageEvent != null)
            pageEvent.onChapterEnd(pdfDocument.getWriter(), pdfDocument, pdfDocument.indentTop() - pdfDocument.getCurrentHeight());

        return true;
    }
}