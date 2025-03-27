# Gerenciamento de Estoque de Carros

Este programa em Java foi desenvolvido para gerenciar o estoque de carros, permitindo registrar novos carros, registrar novas marcas e fazer consultas. O sistema utiliza o PostgreSQL como banco de dados para armazenar as informações.

## Funcionalidades

- **Registrar Carros**: Permite o registro de novos carros no estoque.
- **Registrar Marcas**: Permite registrar novas marcas de carros.
- **Consulta de Carros**: Permite consultar os carros registrados.
- **Relação entre as Tabelas**: Ao registrar um carro, apenas as marcas já registradas estarão disponíveis para seleção.

## Tecnologias

- **Java**: Linguagem de programação utilizada.
- **PostgreSQL**: Banco de dados utilizado para armazenar informações sobre carros e marcas.

## Estrutura do Banco de Dados

O banco de dados possui duas tabelas principais:

1. **Marcas**: Contém as informações das marcas de carros.
2. **Carros**: Contém as informações dos carros, com uma chave estrangeira que se relaciona com a tabela `Marcas`.
