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
    
    public static final ImageIcon TOOLBAR_START_BUTTON_IMAGE_ICON;
    public static final ImageIcon TOOLBAR_PAUSE_BUTTON_IMAGE_ICON;
    public static final ImageIcon TOOLBAR_CANCEL_BUTTON_IMAGE_ICON;
    public static final ImageIcon TOOLBAR_ADD_BUTTON_IMAGE_ICON;
    public static final ImageIcon TOOLBAR_ADD_LIST_BUTTON_IMAGE_ICON;
    public static final ImageIcon TOOLBAR_START_ALL_BUTTON_IMAGE_ICON;
    public static final ImageIcon TOOLBAR_PAUSE_ALL_BUTTON_IMAGE_ICON;
    public static final ImageIcon TOOLBAR_CANCEL_ALL_BUTTON_IMAGE_ICON;
    public static final ImageIcon TOOLBAR_PREFERENCES_BUTTON_IMAGE_ICON;
    public static final ImageIcon TOOLBAR_SCHEDULER_BUTTON_IMAGE_ICON;
    
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
        
        URL urlAddDownloadButtonImage = null;
        URL urlStartDownloadButtonImage = null;
        URL urlPauseDownloadButtonImage = null;
        URL urlCancelDownloadButtonImage = null;
        URL urlAddDownloadsButtonImage = null;
        URL urlStartDownloadsButtonImage = null;
        URL urlPauseDownloadsButtonImage = null;
        URL urlCancelDownloadsButtonImage = null;
        URL urlPreferencesButtonImage = null;
        URL urlSchedulerButtonImage = null;
        
//        URL urlAboutBoxImage = null;
//        URL urlMarkerFeltFont = null;
        
        try
        {
//            urlApplicationImage = new URL("file:" + RESOURCES_PATH + "/images/");
            urlStartButtonImage = new URL("file:" + RESOURCES_PATH + "/images/play_24x24.png");
            urlStopButtonImage = new URL("file:" + RESOURCES_PATH + "/images/cancel_24x24.png");
            urlPauseButtonImage = new URL("file:" + RESOURCES_PATH + "/images/pause_24x24.png");
            urlAddDownloadButtonImage = new URL("file:" + RESOURCES_PATH + "/images/pause_24x24.png");;
            urlStartDownloadButtonImage = new URL("file:" + RESOURCES_PATH + "/images/pause_24x24.png");;
            urlPauseDownloadButtonImage = new URL("file:" + RESOURCES_PATH + "/images/pause_24x24.png");;
            urlCancelDownloadButtonImage = new URL("file:" + RESOURCES_PATH + "/images/pause_24x24.png");;
            urlAddDownloadsButtonImage = new URL("file:" + RESOURCES_PATH + "/images/pause_24x24.png");;
            urlStartDownloadsButtonImage = new URL("file:" + RESOURCES_PATH + "/images/pause_24x24.png");;
            urlPauseDownloadsButtonImage = new URL("file:" + RESOURCES_PATH + "/images/pause_24x24.png");;
            urlCancelDownloadsButtonImage = new URL("file:" + RESOURCES_PATH + "/images/pause_24x24.png");;
            urlPreferencesButtonImage = new URL("file:" + RESOURCES_PATH + "/images/pause_24x24.png");;
            urlSchedulerButtonImage = new URL("file:" + RESOURCES_PATH + "/images/pause_24x24.png");;
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
        	TOOLBAR_START_BUTTON_IMAGE_ICON = new ImageIcon(urlStartDownloadButtonImage);
            TOOLBAR_PAUSE_BUTTON_IMAGE_ICON = new ImageIcon(urlPauseDownloadButtonImage);
            TOOLBAR_CANCEL_BUTTON_IMAGE_ICON = new ImageIcon(urlCancelDownloadButtonImage);
            TOOLBAR_ADD_BUTTON_IMAGE_ICON = new ImageIcon(urlAddDownloadButtonImage);
            TOOLBAR_ADD_LIST_BUTTON_IMAGE_ICON = new ImageIcon(urlAddDownloadsButtonImage);
            TOOLBAR_START_ALL_BUTTON_IMAGE_ICON = new ImageIcon(urlStartDownloadsButtonImage);
            TOOLBAR_PAUSE_ALL_BUTTON_IMAGE_ICON = new ImageIcon(urlPauseDownloadsButtonImage);
            TOOLBAR_CANCEL_ALL_BUTTON_IMAGE_ICON = new ImageIcon(urlCancelDownloadsButtonImage);
            TOOLBAR_PREFERENCES_BUTTON_IMAGE_ICON = new ImageIcon(urlPreferencesButtonImage);
            TOOLBAR_SCHEDULER_BUTTON_IMAGE_ICON = new ImageIcon(urlSchedulerButtonImage);
//            ABOUT_BOX_IMAGE_ICON = new ImageIcon(urlAboutBoxImage);
//            MARKER_FELT_FONT = urlMarkerFeltFont;
        }
    }
}