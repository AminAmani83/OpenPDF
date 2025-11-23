#!/bin/bash
cd /Users/josh/IdeaProjects/OpenPDF2/openpdf

# Compile
mvn test-compile -q

# Run TestRunner
java -cp "target/test-classes:target/classes:$(mvn dependency:build-classpath -DincludeScope=test -q -Dmdep.outputFile=/dev/stdout 2>/dev/null)" com.lowagie.text.TestRunner

# List generated PDFs
echo ""
echo "PDF files in openpdf directory:"
ls -lh *.pdf 2>/dev/null || echo "No PDFs found"

