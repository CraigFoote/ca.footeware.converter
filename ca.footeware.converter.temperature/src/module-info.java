module ca.footeware.converter.temperature {
	requires transitive org.eclipse.swt.gtk.linux.x86_64;
	requires ca.footeware.converter;
	exports ca.footeware.converter.temperature;
	provides ca.footeware.converter.spi.ConverterPanel with ca.footeware.converter.temperature.TemperaturePanel;
}