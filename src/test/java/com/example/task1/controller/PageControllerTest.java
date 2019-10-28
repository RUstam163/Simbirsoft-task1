package com.example.task1.controller;

import com.example.task1.service.BookService;
import com.example.task1.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.matchers.JUnitMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PageControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Mock
    private BookService bookService;

    @Mock
    private UserService userService;

    @Mock
    private MessageSource messageSource;

    @InjectMocks
    PageController pageController = new PageController(bookService, userService, messageSource);

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .alwaysDo(MockMvcResultHandlers.print())
                .build();
//        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("viewer", "password", Stream.of("ROLE_ADMIN", "ROLE_MODERATOR", "ROLE_USER")
//                .map(SimpleGrantedAuthority::new).collect(Collectors.toList())));
    }

    @Test
    @WithMockUser(username = "sam")
    public void indexTestUserLessThan18YearsOld() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(content()
                        .string(containsString("Незнайка на Луне")));
    }

    @Test(expected = AssertionError.class)
    @WithMockUser(username = "ss")
    public void indexTestUserOver18YearsOld() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(content()
                        .string(containsString("Незнайка на Луне")));
    }
}