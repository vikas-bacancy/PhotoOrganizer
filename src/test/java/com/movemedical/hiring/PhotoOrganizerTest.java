package com.movemedical.hiring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PhotoOrganizerTest {
    @Test
    void testOrganizeMyPhotos() {
        String testInput =
                "DSC012333.jpg, Madrid, 2016-10-01 13:02:34\n" +
                        "DSC044322.jpg, Milan, 2015-03-05 10:11:22\n" +
                        "DSC130033.raw, Rio, 2018-06-02 17:01:30\n" +
                        "DSC044322.jpeg, Milan, 2015-03-04 14:55:01\n" +
                        "DSC130033.jpg, Rio, 2018-06-02 17:05:10\n" +
                        "DSC012335.jpg, Milan, 2015-03-05 10:11:24";

        String expectedOutput =
                "Madrid1.jpg\n" +
                        "Milan2.jpg\n" +
                        "Rio1.raw\n" +
                        "Milan1.jpeg\n" +
                        "Rio2.jpg\n" +
                        "Milan3.jpg";

        String actualOutput = new PhotoOrganizer().organizeMyPhotos(testInput);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }
}
