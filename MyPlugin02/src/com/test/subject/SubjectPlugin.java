package com.test.subject;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.core.runtime.IPluginDescriptor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The main plugin class to be used in the desktop.
 */
public class SubjectPlugin extends AbstractUIPlugin {
    final public static String PLUGIN_ID = "com.test.subject";

    public SubjectPlugin(IPluginDescriptor descriptor) {
        super(descriptor);
    }

    //The shared instance.
    private static SubjectPlugin plugin;

    //Resource bundle.
    private ResourceBundle resourceBundle;

    /**
     * The constructor.
     */
    public SubjectPlugin() {
        super();
        plugin = this;
        try {
            resourceBundle = ResourceBundle.getBundle("com.test.subject.SubjectPluginResources");
        } catch (MissingResourceException x) {
            resourceBundle = null;
        }
    }

    /**
     * ×¢²áÍ¼Ïó
     */
    protected void initializeImageRegistry(ImageRegistry reg) {
        super.initializeImageRegistry(reg);
        reg.put(IConstants.IMG_FOLDER, imageDescriptorFromPlugin(PLUGIN_ID, "icons/folder.gif"));
        reg.put(IConstants.IMG_FILE, imageDescriptorFromPlugin(PLUGIN_ID, "icons/file.gif"));
    }

    public static Image getImage(String symbolicName) {
        return getDefault().getImageRegistry().get(symbolicName);
    }

    public static ImageDescriptor getImageDescriptor(String symbolicName) {
        return getDefault().getImageRegistry().getDescriptor(symbolicName);
    }

    /**
     * This method is called upon plug-in activation
     */
    public void start(BundleContext context) throws Exception {
        super.start(context);
    }

    /**
     * This method is called when the plug-in is stopped
     */
    public void stop(BundleContext context) throws Exception {
        super.stop(context);
    }

    /**
     * Returns the shared instance.
     */
    public static SubjectPlugin getDefault() {
        return plugin;
    }

    /**
     * Returns the string from the plugin's resource bundle,
     * or 'key' if not found.
     */
    public static String getResourceString(String key) {
        ResourceBundle bundle = SubjectPlugin.getDefault().getResourceBundle();
        try {
            return (bundle != null) ? bundle.getString(key) : key;
        } catch (MissingResourceException e) {
            return key;
        }
    }

    /**
     * Returns the plugin's resource bundle,
     */
    public ResourceBundle getResourceBundle() {
        return resourceBundle;
    }
}