/**
 * 
 */
package ca.footeware.converter.spi;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

/**
 * @author Footeware.ca
 *
 */
public abstract class ConverterPanel {

	private Map<String, Image> images = new HashMap<>();

	/**
	 * Get the label identifying the tabPanel.
	 * 
	 * @return {@link String}
	 */
	public abstract String getLabel();

	/**
	 * Get the icon image for this tab's composite.
	 * 
	 * @return {@link Image}
	 */
	public abstract Image getImage();

	/**
	 * Get the panel laying out the controls for the tab.
	 * 
	 * @return parent {@link Composite}
	 */
	public abstract Composite getPanel(Composite parent);

	/**
	 * Get the image at provided path. Store in a list so that it can be disposed of
	 * properly.
	 * 
	 * @param path {@link String}
	 * @return {@link Image}
	 */
	public Image getImage(String path) {
		Image image = images.get(path);
		if (image == null) {
			image = new Image(Display.getDefault(), this.getClass().getResourceAsStream(path));
			images.put(path, image);
		}
		return image;
	}

	public void dispose() {
		for (String path : images.keySet()) {
			Image image = images.get(path);
			if (image != null && !image.isDisposed()) {
				image.dispose();
			}
		}
	};
}
