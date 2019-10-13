package com.duriancodes.ratingcontrolservice.automation.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;

@SpringBootTest()
@RunWith(SpringRunner.class)
@ActiveProfiles("local")
public class RatingControlServiceConfigTest {

    public static final String RATING_CONTROL_SERVICE_URL = "https://localhost:8080/rating-control/rcl/book/v1/read/eligibility/";

    @Autowired
    private RatingControlServiceConfig ratingControlServiceConfig;

    @Test
    public void loadContent() {

    }

    @Test
    public void shouldLoadRateControlServiceEndPoint() {
        assertThat("Rating Service Endpoint value is null", ratingControlServiceConfig.getRatingServiceEndPoint(), not(isEmptyOrNullString()));
        assertThat("Rating Service Endpoint value missmatch", ratingControlServiceConfig.getRatingServiceEndPoint(), is(RATING_CONTROL_SERVICE_URL));
    }
}
