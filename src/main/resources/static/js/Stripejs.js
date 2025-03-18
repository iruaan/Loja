const stripe = Stripe('pk_test_51XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX'); // Use sua chave pública
const elements = stripe.elements();
const cardElement = elements.create('card');
cardElement.mount('#card-element');

const form = document.getElementById('payment-form');
const errorMessage = document.getElementById('error-message');

form.addEventListener('submit', async (event) => {
    event.preventDefault();

    // Cria o PaymentIntent no backend
    const response = await fetch('/create-payment-intent', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            amount: 1000, // Valor em centavos (ex: R$ 10,00 = 1000)
            currency: 'brl', // Moeda (ex: "brl" para Real Brasileiro)
        }),
    });

    const { clientSecret } = await response.json();

    // Confirma o pagamento com o Stripe
    const { error, paymentIntent } = await stripe.confirmCardPayment(clientSecret, {
        payment_method: {
            card: cardElement,
        },
    });

    if (error) {
        errorMessage.textContent = error.message;
    } else {
        alert('Pagamento realizado com sucesso!');
        // Redirecione o usuário ou atualize a interface
    }
});