package com.ccsw.tutorial.loan;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;

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

import com.ccsw.tutorial.author.model.AuthorDto;
import com.ccsw.tutorial.category.model.CategoryDto;
import com.ccsw.tutorial.customer.model.CustomerDto;
import com.ccsw.tutorial.game.model.GameDto;
import com.ccsw.tutorial.loan.model.LoanDto;
import com.ccsw.tutorial.loan.model.LoanSearchDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class LoanIT {

    public static final String LOCALHOST = "http://localhost:";
    public static final String SERVICE_PATH = "/loan/";

    public static final Long DELETE_LOAN_ID = 6L;
    public static final Long MODIFY_LOAN_ID = 3L;

    private static final int TOTAL_LOANS = 6;
    private static final int PAGE_SIZE = 5;

    private static final Long EXISTS_CUSTOMER = 1L;
    private static final Long NOT_EXISTS_CUSTOMER = 0L;

    private static final Long EXISTS_GAME = 1L;
    private static final Long NOT_EXISTS_GAME = 0L;

    private static final String EXISTS_DATE = "2016-01-12";
    private static final String NOT_EXISTS_DATE = "9999-12-01";

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    ParameterizedTypeReference<Page<LoanDto>> responseTypePage = new ParameterizedTypeReference<Page<LoanDto>>() {
    };

    @Test
    public void findExistsTitleShouldReturnLoans() {

        int LOANS_WITH_FILTER = 2;

        LoanSearchDto searchDto = new LoanSearchDto();
        searchDto.setPageable(PageRequest.of(0, PAGE_SIZE));

        ResponseEntity<Page<LoanDto>> response = restTemplate.exchange(
                LOCALHOST + port + SERVICE_PATH + "?gameId=" + EXISTS_GAME, HttpMethod.POST,
                new HttpEntity<>(searchDto), responseTypePage);

        assertNotNull(response);
        assertEquals(LOANS_WITH_FILTER, response.getBody().getTotalElements());
        assertEquals(2, response.getBody().getContent().size());
    }

    @Test
    public void findExistsCustomerShouldReturnLoans() {

        int LOANS_WITH_FILTER = 3;

        LoanSearchDto searchDto = new LoanSearchDto();
        searchDto.setPageable(PageRequest.of(0, PAGE_SIZE));

        ResponseEntity<Page<LoanDto>> response = restTemplate.exchange(
                LOCALHOST + port + SERVICE_PATH + "?customerId=" + EXISTS_CUSTOMER, HttpMethod.POST,
                new HttpEntity<>(searchDto), responseTypePage);

        assertNotNull(response);
        assertEquals(LOANS_WITH_FILTER, response.getBody().getTotalElements());
        assertEquals(3, response.getBody().getContent().size());

    }

    @Test
    public void findExistDateShouldReturnLoans() {

        int LOANS_WITH_FILTER = 2;

        LoanSearchDto searchDto = new LoanSearchDto();
        searchDto.setPageable(PageRequest.of(0, PAGE_SIZE));

        ResponseEntity<Page<LoanDto>> response = restTemplate.exchange(
                LOCALHOST + port + SERVICE_PATH + "?searchDate=" + EXISTS_DATE, HttpMethod.POST,
                new HttpEntity<>(searchDto), responseTypePage);

        assertNotNull(response);
        assertEquals(LOANS_WITH_FILTER, response.getBody().getTotalElements());
        assertEquals(2, response.getBody().getContent().size());
    }

    @Test
    public void findExistDateAndCustomerAndGameShouldReturnLoans() {

        int LOANS_WITH_FILTER = 1;

        LoanSearchDto searchDto = new LoanSearchDto();
        searchDto.setPageable(PageRequest.of(0, PAGE_SIZE));

        ResponseEntity<Page<LoanDto>> response = restTemplate.exchange(
                LOCALHOST + port + SERVICE_PATH + "?searchDate=" + EXISTS_DATE + "&gameId=" + EXISTS_GAME
                        + "&customerId=" + EXISTS_CUSTOMER,
                HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);

        assertNotNull(response);
        assertEquals(LOANS_WITH_FILTER, response.getBody().getTotalElements());
        assertEquals(1, response.getBody().getContent().size());

    }

    @Test
    public void findNotExistDateOrCustomerOrGameShouldReturnEmpty() {

        int LOANS_WITH_FILTER = 0;

        LoanSearchDto searchDto = new LoanSearchDto();
        searchDto.setPageable(PageRequest.of(0, PAGE_SIZE));

        ResponseEntity<Page<LoanDto>> response = restTemplate.exchange(
                LOCALHOST + port + SERVICE_PATH + "?searchDate=" + EXISTS_DATE + "&gameId=" + EXISTS_GAME
                        + "&customerId=" + NOT_EXISTS_CUSTOMER,
                HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);

        assertNotNull(response);
        assertEquals(LOANS_WITH_FILTER, response.getBody().getTotalElements());
        assertEquals(0, response.getBody().getContent().size());

        response = restTemplate.exchange(
                LOCALHOST + port + SERVICE_PATH + "?searchDate=" + EXISTS_DATE + "&gameId=" + NOT_EXISTS_GAME
                        + "&customerId=" + EXISTS_CUSTOMER,
                HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);

        assertNotNull(response);
        assertEquals(LOANS_WITH_FILTER, response.getBody().getTotalElements());
        assertEquals(0, response.getBody().getContent().size());

        response = restTemplate.exchange(
                LOCALHOST + port + SERVICE_PATH + "?searchDate=" + NOT_EXISTS_DATE + "&gameId=" + EXISTS_GAME
                        + "&customerId=" + EXISTS_CUSTOMER,
                HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);

        assertNotNull(response);
        assertEquals(LOANS_WITH_FILTER, response.getBody().getTotalElements());
        assertEquals(0, response.getBody().getContent().size());

    }

    @Test
    public void saveWithoutIdShouldCreateNewLoan() {

        LoanSearchDto searchDto = new LoanSearchDto();
        searchDto.setPageable(PageRequest.of(0, PAGE_SIZE));

        LoanDto dto = new LoanDto();

        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(1L);
        customerDto.setName("NuevoCliente");

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(1L);

        AuthorDto authorDto = new AuthorDto();
        authorDto.setId(1L);

        GameDto gameDto = new GameDto();

        gameDto.setId(1L);
        gameDto.setTitle("NuevoJuego");
        gameDto.setAge("18");
        gameDto.setAuthor(authorDto);
        gameDto.setCategory(categoryDto);

        dto.setCustomer(customerDto);
        dto.setGame(gameDto);
        dto.setLoan_date(Date.valueOf("2024-12-01"));
        dto.setEnd_date(Date.valueOf("2024-12-13"));

        ResponseEntity<Page<LoanDto>> response = restTemplate
                .exchange(
                        LOCALHOST + port + SERVICE_PATH + "?searchDate=" + EXISTS_DATE + "&gameId=" + 1L
                                + "&customerId=" + NOT_EXISTS_CUSTOMER,
                        HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);

        assertNotNull(response);
        assertEquals(0, response.getBody().getContent().size());

        restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT, new HttpEntity<>(dto), Void.class);

        response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "?searchDate=2024-12-01", HttpMethod.POST,
                new HttpEntity<>(searchDto), responseTypePage);

        assertNotNull(response);
        assertEquals(1, response.getBody().getContent().size());

    }

    @Test
    public void saveWithBorrowedGameShouldReturnError() {

        LoanSearchDto searchDto = new LoanSearchDto();
        searchDto.setPageable(PageRequest.of(0, PAGE_SIZE));

        LoanDto dto = new LoanDto();

        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(1L);
        customerDto.setName("NuevoCliente");

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(1L);

        AuthorDto authorDto = new AuthorDto();
        authorDto.setId(1L);

        GameDto gameDto = new GameDto();

        gameDto.setId(1L);
        gameDto.setTitle("NuevoJuego");
        gameDto.setAge("18");
        gameDto.setAuthor(authorDto);
        gameDto.setCategory(categoryDto);

        dto.setCustomer(customerDto);
        dto.setGame(gameDto);
        dto.setLoan_date(Date.valueOf("2016-01-02"));
        dto.setEnd_date(Date.valueOf("2016-01-17"));

        ResponseEntity<Page<LoanDto>> response = restTemplate
                .exchange(
                        LOCALHOST + port + SERVICE_PATH + "?searchDate=" + EXISTS_DATE + "&gameId=" + 1L
                                + "&customerId=" + NOT_EXISTS_CUSTOMER,
                        HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);

        assertNotNull(response);
        assertEquals(0, response.getBody().getContent().size());

        restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT, new HttpEntity<>(dto), Void.class);

        response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "?searchDate=2016-01-14", HttpMethod.POST,
                new HttpEntity<>(searchDto), responseTypePage);

        assertNotNull(response);
        assertEquals(2, response.getBody().getContent().size());
    }

    @Test
    public void findSecondPageWithFiveSizeShouldReturnLastResult() {

        int elementsCount = TOTAL_LOANS - PAGE_SIZE;

        LoanSearchDto searchDto = new LoanSearchDto();
        searchDto.setPageable(PageRequest.of(1, PAGE_SIZE));

        ResponseEntity<Page<LoanDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.POST,
                new HttpEntity<>(searchDto), responseTypePage);

        assertNotNull(response);
        assertEquals(TOTAL_LOANS, response.getBody().getTotalElements());
        assertEquals(elementsCount, response.getBody().getContent().size());
    }

}