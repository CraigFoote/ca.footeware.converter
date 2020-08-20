module ca.footeware.converter.temperature {
	requires swt;
	requires ca.footeware.converter;
	exports ca.footeware.converter.temperature;
	provides ca.footeware.converter.spi.ConverterPanel with ca.footeware.converter.temperature.TemperaturePanel;
}