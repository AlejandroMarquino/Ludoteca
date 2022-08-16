package com.ccsw.tutorial.leasing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import com.ccsw.tutorial.leasing.model.LeasingDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class LeasingIT {

    public static final String LOCALHOST = "http://localhost:";
    public static final String SERVICE_PATH = "/leasing/";

    private static final String EXISTS_DATE = "2016-01-12";
    private static final String NOT_EXISTS_DATE = "9999-12-01";
    public static final Long DELETE_LEASING_ID = 6L;
    public static final Long MODIFY_LEASING_ID = 3L;
    private static final int PAGE_SIZE = 5;

    private static final Long EXISTS_CUSTOMER = 1L;
    private static final Long NOT_EXISTS_CUSTOMER = 0L;
    private static final Long EXISTS_GAME = 1L;
    private static final Long NOT_EXISTS_GAME = 0L;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    ParameterizedTypeReference<Page<LeasingDto>> responseTypePage = new ParameterizedTypeReference<Page<LeasingDto>>() {
    };

    // No devuelve resultados si se realiza una busqueda con datos que no existen.

    @Test
    public void DataDontExistCustomerGameDate() {

        int LEASINGS_WITH_FILTER = 0;

        LeasingDto searchDto = new LeasingDto();
        searchDto.setPageable(PageRequest.of(0, PAGE_SIZE));

        ResponseEntity<Page<LeasingDto>> response = restTemplate.exchange(
                LOCALHOST + port + SERVICE_PATH + "?searchDate=" + EXISTS_DATE + "&gameId=" + EXISTS_GAME
                        + "&customerId=" + NOT_EXISTS_CUSTOMER,
                HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);

        assertNotNull(response);
        assertEquals(LEASINGS_WITH_FILTER, response.getBody().getTotalElements());
        assertEquals(0, response.getBody().getContent().size());

        response = restTemplate.exchange(
                LOCALHOST + port + SERVICE_PATH + "?searchDate=" + EXISTS_DATE + "&gameId=" + NOT_EXISTS_GAME
                        + "&customerId=" + EXISTS_CUSTOMER,
                HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);

        assertNotNull(response);
        assertEquals(LEASINGS_WITH_FILTER, response.getBody().getTotalElements());
        assertEquals(0, response.getBody().getContent().size());

        response = restTemplate.exchange(
                LOCALHOST + port + SERVICE_PATH + "?searchDate=" + NOT_EXISTS_DATE + "&gameId=" + EXISTS_GAME
                        + "&customerId=" + EXISTS_CUSTOMER,
                HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);

        assertNotNull(response);
        assertEquals(LEASINGS_WITH_FILTER, response.getBody().getTotalElements());
        assertEquals(0, response.getBody().getContent().size());
    }
}
