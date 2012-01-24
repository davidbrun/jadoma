package fr.uha.ensisa.jadoma.util;

import java.io.File;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 * This class provides constants to easily locate resources
 */
public class ResourcesUtil
{
    /**
     * The path of the resources folder
     */
    public static final String RESOURCES_PATH;
    /**
     * The icon of the application
     */
//    public static final Image APPLICATION_IMAGE;
    /**
     * The image of the button to start a download
     */
    public static final ImageIcon START_BUTTON_IMAGE_ICON;
    /**
     * The image of the button to cancel a download
     */
    public static final ImageIcon STOP_BUTTON_IMAGE_ICON;
    /**
     * The image of the button to suspend a download
     */
    public static final ImageIcon PAUSE_BUTTON_IMAGE_ICON;
    /**
     * The image of the application used in the about box
     */
//    public static final ImageIcon ABOUT_BOX_IMAGE_ICON;
    /**
     * The file where is located the Marker Felt font delivered with the application
     */
//    public static final URL MARKER_FELT_FONT;
    
    static
    {
        RESOURCES_PATH = new File(fr.uha.ensisa.jadoma.util.ResourcesUtil.class.getResource("/").getPath()).getParent() + "/resources";
        
//        Toolkit kit = Toolkit.getDefaultToolkit();
        // Locate the resources
//        URL urlApplicationImage = null;
        URL urlStartButtonImage = null;
        URL urlStopButtonImage = null;
        URL urlPauseButtonImage = null;
//        URL urlAboutBoxImage = null;
//        URL urlMarkerFeltFont = null;
        
        try
        {
//            urlApplicationImage = new URL("file:" + RESOURCES_PATH + "/images/");
            urlStartButtonImage = new URL("file:" + RESOURCES_PATH + "/images/play_24x24.png");
            urlStopButtonImage = new URL("file:" + RESOURCES_PATH + "/images/cancel_24x24.png");
            urlPauseButtonImage = new URL("file:" + RESOURCES_PATH + "/images/pause_24x24.png");
//            urlAboutBoxImage = new URL("file:" + RESOURCES_PATH + "/images/");
//            urlMarkerFeltFont = new URL("file:" + RESOURCES_PATH + "/fonts/MarkerFelt.pfb");
        }
        catch (Exception e)
        { }
        finally
        {
//            APPLICATION_IMAGE = kit.createImage(urlApplicationImage);
        	START_BUTTON_IMAGE_ICON = new ImageIcon(urlStartButtonImage);
        	STOP_BUTTON_IMAGE_ICON = new ImageIcon(urlStopButtonImage);
        	PAUSE_BUTTON_IMAGE_ICON = new ImageIcon(urlPauseButtonImage);
//            ABOUT_BOX_IMAGE_ICON = new ImageIcon(urlAboutBoxImage);
//            MARKER_FELT_FONT = urlMarkerFeltFont;
        }
    }
}