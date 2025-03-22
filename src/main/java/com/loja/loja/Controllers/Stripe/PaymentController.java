package com.loja.loja.Controllers.Stripe;

import com.loja.loja.Model.Cart.CartItemDTO;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PaymentController {

    private static final String STRIPE_SECRET_KEY = "sk_test_51R2F8p01Fjd6YAYzLVEZAN1eLVoY74Ow6xtlSp0pJGLDRdX9z1mDONhp3dKwyK5IMOQWMR9L9XpKbNovyZzG6o0F00GW80ABMR";

    @PostMapping("/payment")
    public ResponseEntity<?> createPaymentIntent(@RequestBody PaymentRequest paymentRequest) {
        Stripe.apiKey = STRIPE_SECRET_KEY;

        List<CartItemDTO> cartItems = paymentRequest.getCartItems();
        String paymentMethodId = paymentRequest.getPaymentMethodId();

        // Calcular o valor total do carrinho
// Calcular o valor total do carrinho em centavos
Long totalAmount = cartItems.stream()
    .mapToLong(item -> (long) (item.getProductPrice() * item.getQuantity() * 100)) // Multiplica por 100
    .sum();

        // Criar o PaymentIntent no Stripe
        try {
            PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
            .setAmount(totalAmount) // Valor em centavos
            .setCurrency("brl") // Moeda (Real Brasileiro)
            .setPaymentMethod(paymentMethodId) // Método de pagamento
            .setConfirm(true) // Confirma o pagamento imediatamente
            .setDescription("Pagamento para os itens do carrinho")
            .setAutomaticPaymentMethods(
                PaymentIntentCreateParams.AutomaticPaymentMethods.builder()
                    .setEnabled(true) // Habilita métodos de pagamento automáticos
                    .setAllowRedirects(PaymentIntentCreateParams.AutomaticPaymentMethods.AllowRedirects.NEVER) // Bloqueia redirecionamentos
                    .build()
            )
            .build();

            PaymentIntent paymentIntent = PaymentIntent.create(params);

            // Retornar o clientSecret para o frontend
            return ResponseEntity.ok().body(new PaymentResponse(paymentIntent.getClientSecret()));
        } catch (StripeException e) {
            return ResponseEntity.internalServerError().body("Erro ao processar o pagamento: " + e.getMessage());
        }
    }
}