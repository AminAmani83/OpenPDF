package com.lowagie.text;

public class TestRunner {
    public static void main(String[] args) {
        try {
            System.out.println("Starting tests...");

            System.out.println("\n1. Running FooterTableTest...");
            FooterTableTest tableTest = new FooterTableTest();
            tableTest.imageLeftAlignmentPositionTest();
            System.out.println("   ✓ Generated: footer-table-test.pdf");

            System.out.println("\n2. Running FooterImageSceneTest...");
            FooterImageSceneTest sceneTest = new FooterImageSceneTest();
            sceneTest.centerUnderlyingPositionSceneTest();
            System.out.println("   ✓ Generated: testCenterPosition.pdf");

            System.out.println("\n✓ All tests completed successfully!");

        } catch (Exception e) {
            System.err.println("✗ Test failed with error:");
            e.printStackTrace();
            System.exit(1);
        }
    }
}

