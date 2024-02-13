package com.github.scgwiremock.mock;

import com.github.tomakehurst.wiremock.extension.MappingsLoaderExtension;
import com.github.tomakehurst.wiremock.stubbing.StubMappings;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class WireMockMappingsLoaderExtension implements MappingsLoaderExtension {
    @Override
    public String getName() {
        return "scg-mappings-loader"; // Return the name of extension
    }

    @Override
    public void loadMappingsInto(StubMappings stubMappings) {
        // implementation to load the mappings
        // mappings can be loaded from any source like git repo, database, file storage, stc


    }
}
