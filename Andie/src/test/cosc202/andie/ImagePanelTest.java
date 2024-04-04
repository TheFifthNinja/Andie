package test.cosc202.andie;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import cosc202.andie.ImagePanel;

public class ImagePanelTest {
    @Test
    void initialDummyTest(){}

    @Test
    void getZoomInitalValue()
    {
        ImagePanel testPanel = new ImagePanel();
        Assertions.assertEquals(100.0, testPanel.getZoom());
    }

    @Test
    void getZoomAftersetZoom()
    {
        ImagePanel testPanel = new ImagePanel();
        testPanel.setZoom(0.0);
        Assertions.assertFalse(testPanel.getZoom()== 100.0);
        Assertions.assertTrue(testPanel.getZoom() >= 50.0);
        testPanel.setZoom(1000.0);
        Assertions.assertFalse(testPanel.getZoom()== 100.0);
        Assertions.assertTrue(testPanel.getZoom() <= 200.0);
    }

}
