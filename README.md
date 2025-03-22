# Spring Discovery Client App 🚀

Este é um exemplo de aplicação **Spring Boot** que utiliza o **Discovery Client** com **Eureka** para registro e descoberta de serviços, mais a configuração de Load Balancer. Essa abordagem permite que microserviços se comuniquem entre si sem a necessidade de configurações manuais de endereços de IP ou URLs.

E sobre o DiscoveryClient e o Load Balancer?
Quando você usa o DiscoveryClient, você não tem o balanceamento de carga automático, e é necessário adicionar explicitamente a configuração de um Load Balancer, como o Ribbon ou outro client-side load balancer.

Isso porque o DiscoveryClient apenas fornece uma lista das instâncias disponíveis de um serviço, mas não tem o mecanismo de balanceamento de carga automático como o Feign.

Para adicionar o load balancing ao código com o DiscoveryClient, é necessário configurar o RestTemplate para ser balanceado. Isso pode ser feito utilizando a anotação @LoadBalanced no RestTemplate.

## Como tudo ocorre?

No caso, ao fazer a chamada restTemplate.getForObject("http://servicea/helloWorld", String.class);, o RestTemplate vai usar o Eureka para encontrar todas as instâncias do serviço servicea e, com o balanceamento de carga, escolher uma instância para enviar a requisição. O Spring, por padrão, usa uma estratégia de Round Robin para escolher qual instância será chamada (ou seja, ele vai balancear as requisições entre as instâncias de forma sequencial).

Se houver várias instâncias do servicea rodando, o Spring vai garantir que a carga seja distribuída entre essas instâncias, ajudando a evitar sobrecarga em uma única instância. Então sim, o balanceamento de carga será feito entre as instâncias do servicea, mas ele não escolhe a "menos solicitada". Ele apenas distribui as requisições de forma balanceada entre as instâncias registradas.

## Tecnologias Utilizadas 🛠️

- **Spring Boot** - Framework para desenvolvimento de aplicativos Java.
- **Spring Cloud** - Conjunto de ferramentas para construir sistemas baseados em microserviços.
- **Spring Cloud Netflix Eureka** - Para registro e descoberta de serviços.
- **Spring Web** - Para criar endpoints REST.
- **Maven** - Para gerenciamento de dependências e construção do projeto.

## Funcionalidades ⚙️

- Registro da aplicação como um cliente de serviço no Eureka.
- Descoberta de outros serviços registrados via Eureka.
- Comunicação entre microserviços utilizando o **Discovery Client**.

## Pré-requisitos 🚨

Antes de rodar a aplicação, você precisa de:

- **Java 17** ou superior.
- **Maven** para gerenciamento de dependências.
- **Spring Cloud Eureka Server** em execução (caso você queira testar localmente).
- IDE de sua escolha (recomendamos VSCode, IntelliJ IDEA ou Eclipse).

## Como Rodar o Projeto 🚀

### 1. Clone o repositório

```bash
git clone https://github.com/alfecjo/discoveryclient.git
cd discoveryclient
```

## Uso
- curl http://localhost:8081/helloWorld
- curl http://host.docker.internal:8081/helloWorld
- curl http://localhost:8082/helloEureka
- curl http://host.docker.internal:8082/helloEureka
- curl http://localhost:8081/actuator/info
- curl http://host.docker.internal:8081/actuator/info
- curl http://localhost:8082/actuator/info
- curl http://host.docker.internal:8082/actuator/info

## Observação Importante

No Kubernetes, o nome servicea já é resolvido automaticamente para o IP interno do serviço Kubernetes servicea.

Você não precisa mais do DiscoveryClient e pode remover o Eureka.

## 📌 O que acontece?

O Kubernetes cria um DNS interno http://servicea:8080 que pode ser chamado diretamente do serviceb.

As requisições serão automaticamente distribuídas entre as réplicas de servicea.

# 🔥 Resumo Final

## Comparação: Eureka vs Kubernetes

| Configuração            | Com Eureka                          | Com Kubernetes                         |
|-------------------------|-----------------------------------|---------------------------------------|
| **Descoberta de Serviços** | Feita pelo Eureka                 | Feita pelo Kubernetes                 |
| **Chamadas entre serviços** | `http://servicea/helloWorld` via Eureka | `http://servicea:8080/helloWorld` via Kubernetes Service |
| **Balanceamento de Carga** | Spring Cloud LoadBalancer        | Kubernetes Service distribui tráfego  |
| **Escalabilidade**      | Feita manualmente via Eureka      | Kubernetes gerencia automaticamente  |

✅ **Seu código já está quase 100% pronto para Kubernetes!**  
Só precisa remover o Eureka, se quiser, e garantir que o **Service** do Kubernetes seja corretamente configurado.  🚀🔥