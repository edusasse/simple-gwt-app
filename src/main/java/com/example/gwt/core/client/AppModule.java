package com.example.gwt.core.client;

import com.google.gwt.core.client.EntryPoint;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.rpc.DMI;
import com.smartgwt.client.rpc.RPCResponse;
import com.smartgwt.client.types.Autofit;
import com.smartgwt.client.types.ExportDisplay;
import com.smartgwt.client.types.ExportFormat;
import com.smartgwt.client.util.EnumUtil;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

import java.util.LinkedHashMap;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class AppModule implements EntryPoint {

	/**
     * This is the entry point method.
     */
	public void onModuleLoad() {

		DataSource worldDS = DataSource.get("world");

		final ListGrid countryList = new ListGrid();
		countryList.setWidth(500);
		countryList.setAlternateRecordStyles(true);
		countryList.setDataSource(worldDS);
		countryList.setAutoFetchData(true);

		ListGridField countryName = new ListGridField("countryName", "Country");
		ListGridField capital = new ListGridField("capital", "Capital");
		ListGridField continent = new ListGridField("continent", "Continent");

		countryList.setFields(countryName, capital, continent);
		countryList.setAutoFitData(Autofit.VERTICAL);
		countryList.setShowFilterEditor(true);
		countryList.setAutoFitMaxRecords(10);

		final DynamicForm exportForm = new DynamicForm();
		exportForm.setWidth(300);

		SelectItem exportTypeItem = new SelectItem("exportType", "Export Type");
		exportTypeItem.setWidth("*");
		exportTypeItem.setDefaultToFirstOption(true);

		LinkedHashMap<String, String> valueMap = new LinkedHashMap<>();
		valueMap.put("csv", "CSV (Excel)");
		valueMap.put("xml", "XML");
		valueMap.put("json", "JSON");
		valueMap.put("xls", "XLS (Excel97)");
		valueMap.put("ooxml", "XLSX (Excel2007/OOXML)");

		exportTypeItem.setValueMap(valueMap);

		exportForm.setItems(exportTypeItem);

		IButton exportButton = new IButton("Export");
		exportButton.addClickHandler(new ClickHandler() {
			public void onClick(com.smartgwt.client.widgets.events.ClickEvent event) {
				String exportAs = (String) exportForm.getField("exportType").getValue();

				DSRequest dsRequestProperties = new DSRequest();
				dsRequestProperties.setExportAs(EnumUtil.getEnum(ExportFormat.values(), exportAs));
				dsRequestProperties.setExportDisplay(ExportDisplay.DOWNLOAD);

				countryList.exportClientData(dsRequestProperties);
			}
		});

		HLayout formLayout = new HLayout(15);
		formLayout.addMember(exportForm);
		formLayout.addMember(exportButton);

		VLayout layout = new VLayout();
		layout.addMember(formLayout);
		layout.addMember(countryList);
		layout.draw();
	}

}