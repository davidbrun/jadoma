package fr.uha.ensisa.jadoma.util;

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
    public static final ImageIcon ABOUT_BOX_IMAGE_ICON;
    
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
        RESOURCES_PATH = "resources";
        
//		Toolkit kit = Toolkit.getDefaultToolkit();
		// Load the resources
//		APPLICATION_IMAGE = kit.createImage(RESOURCES_PATH + "/images/");
		START_BUTTON_IMAGE_ICON = new ImageIcon(RESOURCES_PATH + "/images/play_24x24.png");
		STOP_BUTTON_IMAGE_ICON = new ImageIcon(RESOURCES_PATH + "/images/cancel_24x24.png");
		PAUSE_BUTTON_IMAGE_ICON = new ImageIcon(RESOURCES_PATH + "/images/pause_24x24.png");
		TOOLBAR_START_BUTTON_IMAGE_ICON = new ImageIcon(RESOURCES_PATH + "/images/play_32x32.png");
		TOOLBAR_PAUSE_BUTTON_IMAGE_ICON = new ImageIcon(RESOURCES_PATH + "/images/stop_32x32.png");
		TOOLBAR_CANCEL_BUTTON_IMAGE_ICON = new ImageIcon(RESOURCES_PATH + "/images/cancel_32x32.png");
		TOOLBAR_ADD_BUTTON_IMAGE_ICON = new ImageIcon(RESOURCES_PATH + "/images/add_32x32.png");
		TOOLBAR_ADD_LIST_BUTTON_IMAGE_ICON = new ImageIcon(RESOURCES_PATH + "/images/add-all_32x32.png");
		TOOLBAR_START_ALL_BUTTON_IMAGE_ICON = new ImageIcon(RESOURCES_PATH + "/images/play-all_32x32.png");
		TOOLBAR_PAUSE_ALL_BUTTON_IMAGE_ICON = new ImageIcon(RESOURCES_PATH + "/images/pause-all_32x32.png");
		TOOLBAR_CANCEL_ALL_BUTTON_IMAGE_ICON = new ImageIcon(RESOURCES_PATH + "/images/cancel-all_32x32.png");
		TOOLBAR_PREFERENCES_BUTTON_IMAGE_ICON = new ImageIcon(RESOURCES_PATH + "/images/settings_32x32.png");
		TOOLBAR_SCHEDULER_BUTTON_IMAGE_ICON = new ImageIcon(RESOURCES_PATH + "/images/scheduler_32x32.png");
		ABOUT_BOX_IMAGE_ICON = new ImageIcon(RESOURCES_PATH + "/images/help_32x32.png");
//		MARKER_FELT_FONT = RESOURCES_PATH + "/fonts/MarkerFelt.pfb";
    }
}