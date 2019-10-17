package com.example.gwt.core.server;

import com.isomorphic.datasource.BasicDataSource;
import com.isomorphic.datasource.DSRequest;
import com.isomorphic.datasource.DSResponse;
import com.example.gwt.core.shared.Country;

import java.util.Arrays;
import java.util.List;

public class WorldDataSource extends BasicDataSource {

    @Override
    public DSResponse executeFetch(DSRequest req) throws Exception {
        DSResponse response = new DSResponse();

        Country brazil = new Country(1, "Brasil", "Brasilia", "South America");
        Country germany = new Country(2,"Germany", "Berlin", "Europe");

        List<Country> countries = Arrays.asList(brazil, germany);

        return response.setData(countries).setSuccess();
    }
}
