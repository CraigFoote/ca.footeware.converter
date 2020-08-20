/**
 * 
 */
package ca.footeware.converter;

import java.util.ServiceLoader;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

import ca.footeware.converter.spi.ConverterPanel;

/**
 * @author Footeware.ca
 *
 */
public class Converter {

	private static Image icon;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("Footeware Converter");
		shell.setImage(getImage("/icons8-foot-100.png"));
		shell.addDisposeListener(new DisposeListener() {
			@Override
			public void widgetDisposed(DisposeEvent e) {
				if (icon != null && !icon.isDisposed()) {
					icon.dispose();
				}
			}
		});

		TabFolder tabFolder = new TabFolder(shell, SWT.TOP);

		ServiceLoader<ConverterPanel> converterPanelLoader = ServiceLoader.load(ConverterPanel.class);
		for (ConverterPanel converterPanel : converterPanelLoader) {
			TabItem tabItem = new TabItem(tabFolder, SWT.NONE);
			tabItem.setText(converterPanel.getLabel());
			tabItem.setImage(converterPanel.getImage());
			tabItem.setControl(converterPanel.getPanel(tabFolder));
		}

		tabFolder.pack();
		shell.pack();

		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

	/**
	 * Get the application icon image. Load once into an instance variable so that
	 * it can be disposed of properly.
	 * 
	 * @return {@link Image}
	 */
	private static Image getImage(String path) {
		if (icon == null) {
			icon = new Image(Display.getDefault(), Converter.class.getResourceAsStream(path));
		}
		return icon;
	}

}
