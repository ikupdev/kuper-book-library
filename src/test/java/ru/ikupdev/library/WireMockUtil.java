package ru.ikupdev.library;

import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

/**
 * @author Ilya V. Kupriyanov
 * @version 10.02.2022
 */
public class WireMockUtil {
    private WireMockUtil() {
    }

    private static void makePostStubs(String address, Map<String, String> varsMap) {
        for (Map.Entry<String, String> entry : varsMap.entrySet()) {
            AbstractIntegrationTest.wireMock.stubFor(post(urlEqualTo(address))
                    .withRequestBody(containing(entry.getKey()))
                    .willReturn(aResponse()
                            .withHeader("Content-Type", "application/json")
                            .withBodyFile(entry.getValue())));
        }
    }

    private static void makeGetStubs(String address, Map<String, String> varsMap) {
        for (Map.Entry<String, String> entry : varsMap.entrySet()) {
            AbstractIntegrationTest.wireMock.stubFor(get(urlEqualTo(address + entry.getKey()))
                    .willReturn(aResponse()
                            .withHeader("Content-Type", "application/json")
                            .withBodyFile(entry.getValue())));
        }
    }

    private static void makePatchStubs(String address, Map<String, String> varsMap) {
        for (Map.Entry<String, String> entry : varsMap.entrySet()) {
            AbstractIntegrationTest.wireMock.stubFor(patch(urlEqualTo(address + entry.getKey()))
                    .willReturn(aResponse()
                            .withHeader("Content-Type", "application/json")
                            .withBodyFile(entry.getValue())));
        }
    }

    public static void stubs() {
            AbstractIntegrationTest.wireMock.stubFor(
                    get(urlEqualTo("/volumes?q=inauthor%3ARowling&key=AIzaSyDIo47O3Sy9s4zOfyXvL_zSRxpMf2XeD3s"))
                    .willReturn(aResponse()
                            .withHeader("Content-Type", "application/json")
                            .withBodyFile("gbook_volumes_by_inauthor_rowling.json")));

            AbstractIntegrationTest.wireMock.stubFor(
                    get(urlEqualTo("/volumes/DDDDDDDDDDDD"))
                    .willReturn(aResponse()
                            .withHeader("Content-Type", "application/json")
                            .withBodyFile("new_book_by_volume_id.json")));
    }

}
