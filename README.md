# TrabalhoOOP

Projeto acadêmico em Java com foco em orientação a objetos, executado em console, inspirado no universo da Família Addams.

O sistema simula uma batalha `single player` em que o usuário escolhe um personagem da família, enfrenta um adversário sorteado pelo sistema e interage com ataques, defesa, habilidade exclusiva, mascote e intervenções aleatórias da mansão mal-assombrada.

## Objetivo do trabalho

O projeto foi desenvolvido para demonstrar, de forma prática, os principais conceitos cobrados na disciplina:

- classes e atributos;
- objetos e encapsulamento;
- uso de métodos públicos para comportamento;
- interação entre objetos;
- organização de responsabilidades;
- uso de `enum`;
- convenções de nomenclatura da linguagem Java;
- execução em console com fluxo completo de jogo.

## Tema

Todo o sistema foi ambientado no universo da Família Addams.  
Os personagens, classes narrativas, mascotes e eventos da casa foram pensados para manter a estética sombria, excêntrica e bem-humorada da mansão.

## Funcionalidades principais

- escolha do personagem pelo jogador;
- sorteio automático de um inimigo diferente;
- vida inicial de `1000` para os dois lados;
- atributos completos para cada personagem:
  nome, vida, força, defesa, mana, nível, classe narrativa, roupa/armadura, habilidade exclusiva e mascote;
- menu de ações por rodada;
- ataque comum;
- defesa com bônus temporário;
- habilidade especial com custo de mana;
- ajuda do mascote em modo ofensivo ou defensivo;
- resumos de rodada no console;
- intervenções aleatórias da mansão mal-assombrada;
- teste do sistema sem bibliotecas externas.

## Regras da batalha

- o jogo é `single player`;
- o inimigo é controlado automaticamente pelo sistema;
- personagens podem atacar, defender, usar habilidade especial, pedir ajuda ao mascote e exibir status;
- a habilidade especial consome mana;
- o mascote pode atacar, fortalecer a defesa, recuperar vida ou recuperar mana;
- a vida nunca fica abaixo de zero;
- a mana nunca fica negativa;
- personagens derrotados não podem agir;
- a batalha termina quando um dos lados chega a `0` de vida.

## Intervenções da mansão

A mansão pode interferir aleatoriamente ao fim das rodadas, reforçando o clima de casa mal-assombrada.

Eventos implementados:

- `Armadura Despencando`
- `Sussurros da Mansão`
- `Brisa Gelada`
- `Porta Batendo`
- `Retratos Vigiando`
- `Velas Tremulando`

Esses eventos podem, por exemplo:

- causar dano leve;
- reduzir mana;
- remover defesa temporária;
- conceder bônus de defesa;
- reduzir energia do mascote;
- restaurar um pouco de mana.

## Estrutura do projeto

Arquivos principais em `src/main/java/br/com/unipar/atividade`:

- `Main.java`
  Responsável por iniciar o jogo, apresentar o tema e conduzir a escolha do personagem.
- `Batalha.java`
  Controla o fluxo completo da luta por rodadas.
- `Personagem.java`
  Representa o personagem jogável com atributos privados e métodos de batalha.
- `Mascote.java`
  Representa o mascote associado ao personagem.
- `GeradorPersonagem.java`
  Cria o jogador e sorteia o inimigo.
- `TesteSistema.java`
  Executa testes simples no console, sem JUnit.

Enums:

- `ClassePersonagem.java`
- `TipoMascote.java`
- `AcaoBatalha.java`
- `ModoAjudaMascote.java`
- `EventoMansao.java`

Classes auxiliares de resultado:

- `ResultadoDano.java`
- `ResultadoHabilidade.java`
- `ResultadoMascote.java`
- `ResultadoIntervencaoMansao.java`

Catálogo de personagens:

- `PersonagemBaseAddams.java`

Lógica da mansão:

- `IntervencaoMansao.java`

## Personagens disponíveis

O projeto inclui personagens temáticos do universo Addams:

- Gomez Addams
- Morticia Addams
- Wandinha Addams
- Feioso Addams
- Tio Chico
- Tropeço
- Mãozinha

Cada personagem possui:

- classe narrativa própria;
- conjunto de atributos balanceado;
- habilidade exclusiva;
- mascote compatível com sua classe.

## Conceitos de orientação a objetos aplicados

### Encapsulamento

Os atributos principais dos objetos foram declarados como `private`, com acesso controlado por getters e métodos de comportamento.

### Associação entre objetos

Cada `Personagem` possui um `Mascote`, e ambos interagem durante a batalha.

### Separação de responsabilidades

O projeto foi dividido em classes pequenas e com funções específicas:

- criação de personagens;
- fluxo da batalha;
- cálculo de dano;
- comportamento do mascote;
- eventos aleatórios da mansão;
- testes.

### Uso de enum

Os `enum` foram usados para representar:

- tipos narrativos de personagem;
- tipos de mascote;
- ações de batalha;
- modos de ajuda do mascote;
- eventos sobrenaturais da mansão.

## Execução do projeto

### Requisitos

- Java JDK instalado;
- Windows com `cmd` ou terminal do VS Code;
- PowerShell ou Prompt de Comando para executar os arquivos `.bat`.

### Execução com UTF-8

Para evitar problemas com acentuação no terminal, o repositório já possui scripts de execução preparados para `UTF-8`.

### Rodar o jogo

Use:

```bat
executar-jogo.bat
```

Esse arquivo:

- troca o terminal para UTF-8;
- compila todos os arquivos `.java`;
- executa a classe principal do jogo.

### Rodar os testes

Use:

```bat
executar-testes.bat
```

Esse arquivo:

- compila o projeto;
- executa a classe `TesteSistema`.

### Executar pelo VS Code

O projeto também possui tarefas configuradas em `.vscode/tasks.json`:

- `Executar jogo (UTF-8)`
- `Executar testes (UTF-8)`

## Compilação manual

Se preferir compilar manualmente:

```bat
javac -encoding UTF-8 -d bin src\main\java\br\com\unipar\atividade\*.java
java -Dfile.encoding=UTF-8 -cp bin br.com.unipar.atividade.Main
```

Observação:
como o projeto possui vários arquivos, os scripts `.bat` são a forma mais prática e confiável de execução no Windows.

## Testes implementados

A classe `TesteSistema` valida comportamentos essenciais do projeto:

- ataque reduz vida;
- defesa reduz dano;
- habilidade especial consome mana;
- habilidade especial retorna sucesso;
- vida não fica negativa;
- personagem morre corretamente;
- mascote participa da batalha;
- mascote ofensivo registra dano;
- intervenção da mansão aplica efeito;
- retratos da mansão reduzem energia do mascote;
- inimigo sorteado é diferente do jogador.

Resultado esperado da execução atual:

```text
Aprovados: 11
Reprovados: 0
```

## Fluxo do jogo

1. O sistema apresenta o universo do jogo.
2. O jogador escolhe um personagem da Família Addams.
3. O sistema sorteia um adversário diferente.
4. A batalha começa com os dois personagens em estado inicial.
5. A cada rodada, o jogador escolhe uma ação.
6. O inimigo responde automaticamente.
7. A mansão pode interferir com um evento aleatório.
8. O console mostra os resultados da rodada.
9. O combate termina quando um dos lados perde toda a vida.

## Destaques da implementação

- código comentado e organizado;
- nomes em português;
- estrutura acadêmica e didática;
- sem dependências externas;
- compatível com console;
- foco em clareza e demonstração dos conceitos de POO;
- métodos quebrados em partes menores para manter a legibilidade.

## Observações finais

Este projeto foi pensado para ser ao mesmo tempo:

- funcional como jogo em console;
- coerente com o tema da Família Addams;
- adequado para avaliação acadêmica em orientação a objetos.

Além da batalha entre personagens, o projeto destaca a interação entre objetos, o uso de `enum`, o encapsulamento e a ambientação da mansão como parte ativa do sistema.
