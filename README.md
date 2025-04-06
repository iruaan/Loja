# 🛍️ Loja Online com Spring Boot

Este é um projeto de e-commerce focado no Backend, mas possui alguns elementos basicos de frontend desenvolvido com **Spring Boot 3.4.3**, utilizando **Thymeleaf**, **Spring Security**, **JPA**, **MySQL** e integração com **Stripe** para pagamentos. O sistema permite aos usuários navegar pelos produtos, adicioná-los ao carrinho e realizar o checkout.

## ⚙️ Tecnologias Utilizadas

- **Java 23**
- **Spring Boot**
  - Spring Web
  - Spring Data JPA
  - Spring Security
  - Spring Validation
  - Spring Mail
- **Thymeleaf**
- **Stripe (pagamentos)**
- **MySQL**
- **Lombok**
- **DevTools**

## 📁 Estrutura de Diretórios

```
com.loja.loja
├── Controllers
│   ├── PageController.java
│   └── CartController.java
├── Model
│   ├── Products
│   ├── Cart
│   └── User
├── Repository
│   ├── ProductRepository
│   └── UserRepository.java
```

## 📦 Dependências (pom.xml)

Inclui dependências para:

- Spring Boot Starters (Web, Security, Data JPA, Validation, Thymeleaf)
- MySQL Driver
- Stripe SDK
- Lombok

## 🧪 Como Rodar Localmente

1. **Clone o repositório:**

```bash
git clone https://github.com/seu-usuario/usecs.git
cd usecs
```

2. **Configure o banco MySQL:**

Crie um banco com o nome `usecs` e atualize `application.properties` com seu usuário e senha do MySQL:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/usecs
spring.datasource.username=root
spring.datasource.password=1234
```

3. **Atualize as configurações do Stripe:**

```properties
stripe.public-key=pk_test_xxx
stripe.secret-key=sk_test_xxx
```

4. **Execute a aplicação:**

```bash
./mvnw spring-boot:run
```

5. **Acesse no navegador:**

```
http://localhost:8080/home //Pagina inicial
http://localhost:8080/product // Cadastro de Produtos utilizando URL de imagens em sites de armazenamento como Imgur.com
http://localhost:8080/cart // Carrinho de produtos

```

## 🛒 Funcionalidades

- Cadastro de usuário e login com Spring Security
- Listagem de produtos com opções de cor e tamanho
- Carrinho de compras
- Checkout com seleção de produtos
- Integração com Stripe para pagamentos
- Painel de administração de produtos (em desenvolvimento)



Desenvolvido por **Ruan Antonio dos Santos **  
[LinkedIn](https://www.linkedin.com/in/ruan-santoss/)
