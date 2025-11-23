#!/bin/bash
set -e
cd /Users/josh/IdeaProjects/OpenPDF2/openpdf

LOGFILE="../test-run.log"
echo "Test run started at $(date)" > "$LOGFILE"

echo "Compiling tests..."
mvn test-compile >> "$LOGFILE" 2>&1
echo "✓ Compilation complete" | tee -a "$LOGFILE"

echo ""
echo "Running FooterTableTest..."
java -cp "target/test-classes:target/classes:$(mvn dependency:build-classpath -DincludeScope=test -q -Dmdep.outputFile=/dev/stdout 2>/dev/null)" com.lowagie.text.FooterTableTest >> "$LOGFILE" 2>&1 && echo "✓ FooterTableTest completed" | tee -a "$LOGFILE" || echo "✗ FooterTableTest failed" | tee -a "$LOGFILE"

echo ""
echo "Running FooterImageSceneTest..."
java -cp "target/test-classes:target/classes:$(mvn dependency:build-classpath -DincludeScope=test -q -Dmdep.outputFile=/dev/stdout 2>/dev/null)" com.lowagie.text.FooterImageSceneTest >> "$LOGFILE" 2>&1 && echo "✓ FooterImageSceneTest completed" | tee -a "$LOGFILE" || echo "✗ FooterImageSceneTest failed" | tee -a "$LOGFILE"

echo ""
echo "Generated PDF files:"
find . -maxdepth 2 -name "*.pdf" -type f -mmin -5 -exec ls -lh {} \; 2>/dev/null | tee -a "$LOGFILE"

echo ""
echo "Done! Check $LOGFILE for details"
cat "$LOGFILE"

