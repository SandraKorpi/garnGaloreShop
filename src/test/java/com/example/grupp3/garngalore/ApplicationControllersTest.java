package com.example.grupp3.garngalore;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

import com.example.grupp3.garngalore.Controllers.CartController;
import com.example.grupp3.garngalore.Controllers.OrderController;
import com.example.grupp3.garngalore.Controllers.ProductController;
import com.example.grupp3.garngalore.Models.Cart;
import com.example.grupp3.garngalore.Models.Order;
import com.example.grupp3.garngalore.Models.Product;
import com.example.grupp3.garngalore.Services.CartService;
import com.example.grupp3.garngalore.Services.OrderService;
import com.example.grupp3.garngalore.Services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;


@WebMvcTest(CartController.class)
public class ApplicationControllersTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CartService cartService;

	@MockBean
	private OrderService orderService;

	@MockBean
	private ProductService productService;

	private MockHttpSession session;

	@BeforeEach
	public void setUp() {
		session = new MockHttpSession();
	}

	@Test
	public void testShowCartPage_CartExists() throws Exception {
		Cart cart = new Cart();
		session.setAttribute("cart", cart);

		mockMvc.perform(get("/cart").session(session))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("cart"))
				.andExpect(view().name("Cart"));
	}

	@Test
	public void testStartCartSession_CartAlreadyExists() throws Exception {
		Cart cart = new Cart();
		session.setAttribute("cart", cart);

		mockMvc.perform(post("/startCartSession").session(session))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.message").value("Cart session started successfully"));
	}

	@Test
	public void testStartCartSession_NoCart() throws Exception {
		mockMvc.perform(post("/startCartSession").session(session))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.message").value("Cart session started successfully"));
	}

	@Test
	public void testGetCartItemCount() throws Exception {
		Cart cart = new Cart();
		cart.setNumberOfProducts(3);
		session.setAttribute("cart", cart);

		mockMvc.perform(get("/getCartItemCount").session(session))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.cartItemCount").value(3));
	}

	@Test
	public void testClearCart_CartExists() throws Exception {
		Cart cart = new Cart();
		session.setAttribute("cart", cart);

		mockMvc.perform(post("/cart/clearCart").session(session))
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/cart"));
	}

	@Test
	public void testClearCart_NoCart() throws Exception {
		mockMvc.perform(post("/cart/clearCart").session(session))
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/cart"));
	}

	@Test
	public void testShowPaymentPage_CartExists() throws Exception {
		Cart cart = new Cart();
		session.setAttribute("cart", cart);

		mockMvc.perform(get("/paymentPage").session(session))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("cart"))
				.andExpect(view().name("PaymentPage"));
	}

	@Test
	public void testShowPaymentPage_NoCart() throws Exception {
		mockMvc.perform(get("/paymentPage").session(session))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("cart"))
				.andExpect(view().name("PaymentPage"));
	}

	@Test
	public void testPlaceOrder_NoCart() throws Exception {
		mockMvc.perform(post("/placeOrder").param("paymentMethod", "Credit Card").session(session))
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/cart"));
	}
}


