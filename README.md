# TrabalhoOOP

Projeto acadêmico em Java com foco em orientação a objetos, executado em console e ambientado no universo da Família Addams.

O sistema simula uma batalha `single player` em que o jogador escolhe um personagem da família, enfrenta um adversário sorteado pelo sistema e utiliza ataques, defesa, habilidade exclusiva, mascote e intervenções sobrenaturais da mansão mal-assombrada.

## Objetivo do trabalho

O projeto foi desenvolvido para demonstrar, de forma prática e didática:

- classes e atributos;
- objetos e encapsulamento;
- uso de métodos públicos de comportamento;
- interação entre objetos;
- organização por responsabilidades;
- uso correto de `enum`;
- convenções de nomenclatura da linguagem Java;
- execução em console com fluxo completo de jogo.

## Tema

Todo o sistema foi construído com base no universo da Família Addams.  
Os personagens, mascotes, habilidades e eventos da casa foram pensados para manter a estética sombria, excêntrica e misteriosa da mansão.

## Funcionalidades implementadas

- escolha do personagem pelo usuário;
- sorteio automático de um inimigo diferente;
- vida inicial de `1000` para os dois lados;
- atributos completos para cada personagem:
  nome, vida, força, defesa, mana, nível, classe narrativa, roupa/armadura, habilidade exclusiva e mascote;
- batalha por rodadas no console;
- ataque básico;
- defesa com bônus temporário;
- habilidade especial com custo de mana;
- ataque conjunto entre personagem e mascote;
- uso exclusivo do mascote em modo ofensivo ou defensivo;
- intervenções aleatórias da mansão ao final das rodadas;
- tabela ASCII de acompanhamento ao final de cada rodada;
- opção de iniciar uma nova luta ao final da partida;
- testes sem dependências externas.

## Regras da batalha

- o jogo é `single player`;
- o inimigo é controlado automaticamente pelo sistema;
- personagens podem atacar, defender, usar habilidade especial, fazer ataque conjunto e usar somente o mascote;
- o mascote pode agir de forma ofensiva ou defensiva;
- a habilidade especial consome mana;
- a vida nunca fica abaixo de zero;
- a mana nunca fica negativa;
- personagens derrotados não podem agir;
- a luta termina imediatamente quando um dos lados chega a `0` de vida.

## Ações disponíveis no menu

Durante cada rodada, o jogador pode escolher:

- `1 - Atacar`
- `2 - Defender`
- `3 - Usar habilidade especial`
- `4 - Ataque conjunto com mascote`
- `5 - Usar somente o mascote`
- `6 - Exibir status`

Ao escolher `Usar somente o mascote`, é aberto um submenu:

- `1 - Ajuda ofensiva`
- `2 - Ajuda defensiva`

## Mascotes

Cada personagem possui um mascote compatível com sua classe narrativa.

O mascote pode:

- atacar sozinho;
- participar de um ataque conjunto com o personagem;
- conceder defesa temporária;
- recuperar vida;
- recuperar mana.

## Intervenções da mansão

A mansão mal-assombrada pode interferir aleatoriamente ao fim das rodadas.

Eventos implementados:

- `Armadura Despencando`
- `Sussurros da Mansão`
- `Brisa Gelada`
- `Porta Batendo`
- `Retratos Vigiando`
- `Velas Tremulando`

Esses eventos podem:

- causar dano leve;
- reduzir mana;
- remover defesa temporária;
- conceder bônus de defesa;
- reduzir energia do mascote;
- restaurar mana.

## Tabela de rodada

Ao final de cada rodada, o jogo exibe uma tabela ASCII com o estado dos dois lados da batalha.

Colunas exibidas:

- `Vida`
  valor atual de vida do personagem;
- `Mana`
  valor atual de mana do personagem;
- `Energia Masc.`
  energia atual do mascote;
- `Rodadas`
  quantidade de rodadas em que o personagem participou;
- `Maior Dano Pers.`
  maior dano final causado pelo personagem em uma única ação ofensiva;
- `Maior Bloq. Pers.`
  maior bloqueio registrado pelo personagem ao reduzir um golpe recebido;
- `Maior Dano Masc.`
  maior dano final causado pelo mascote em uma única ação ofensiva;
- `Maior Suporte Masc.`
  maior impacto defensivo ou de recuperação causado pelo mascote em uma única ação de suporte.

A coluna `Maior Suporte Masc.` considera:

- defesa concedida pelo mascote;
- vida recuperada;
- mana recuperada.

O maior valor entre esses efeitos é o que aparece na tabela.

## Estrutura do projeto

Arquivos principais em `src/main/java/br/com/unipar/atividade`:

- `Main.java`
  inicia o sistema, controla a escolha do personagem e pergunta se o usuário deseja uma nova luta;
- `Batalha.java`
  controla o fluxo completo da luta por rodadas;
- `Personagem.java`
  representa o personagem com atributos privados, comportamento de combate e estatísticas acumuladas;
- `Mascote.java`
  representa o mascote associado ao personagem;
- `GeradorPersonagem.java`
  cria o jogador e sorteia o inimigo;
- `IntervencaoMansao.java`
  aplica os eventos sobrenaturais da mansão;
- `TesteSistema.java`
  executa os testes simples no console.

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
- `ResultadoAtaqueConjunto.java`
- `ResultadoIntervencaoMansao.java`

Catálogo fixo:

- `PersonagemBaseAddams.java`

## Personagens disponíveis

O jogo inclui:

- Gomez Addams
- Morticia Addams
- Wandinha Addams
- Feioso Addams
- Tio Chico
- Tropeço
- Mãozinha

Cada personagem possui:

- classe narrativa própria;
- atributos balanceados;
- habilidade exclusiva;
- mascote compatível com sua classe.

## Conceitos de orientação a objetos aplicados

### Encapsulamento

Os atributos principais são `private`, com acesso controlado por getters e métodos públicos.

### Associação entre objetos

Cada `Personagem` possui um `Mascote`, e ambos interagem durante a batalha.

### Separação de responsabilidades

O projeto foi dividido em classes pequenas e com funções bem definidas:

- criação dos personagens;
- fluxo da batalha;
- cálculo de dano;
- comportamento do mascote;
- eventos da mansão;
- testes.

### Uso de enum

Os `enum` representam:

- classes narrativas dos personagens;
- tipos de mascote;
- ações de batalha;
- modos de ajuda do mascote;
- eventos da mansão.

## Execução do projeto

### Requisitos

- Java JDK instalado;
- Windows com `cmd`, PowerShell ou terminal do VS Code.

### Execução com UTF-8

O repositório já possui scripts preparados para evitar problemas com acentuação.

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

O projeto também possui tarefas em `.vscode/tasks.json`:

- `Executar jogo (UTF-8)`
- `Executar testes (UTF-8)`

## Fluxo do jogo

1. O sistema apresenta o universo da batalha.
2. O jogador escolhe um personagem da Família Addams.
3. O sistema sorteia um adversário diferente.
4. A luta começa com os dois personagens em estado inicial.
5. A cada rodada, o jogador escolhe uma ação.
6. O adversário responde automaticamente.
7. A mansão pode interferir com um evento aleatório.
8. Ao fim da rodada, é exibida a tabela de acompanhamento.
9. Quando um dos lados chega a `0`, a luta termina imediatamente.
10. O sistema pergunta se o usuário deseja iniciar uma nova luta.

## Testes implementados

A classe `TesteSistema` valida:

- ataque reduz a vida do alvo;
- defesa reduz o dano recebido;
- habilidade especial consome mana;
- habilidade especial retorna sucesso;
- vida não fica abaixo de zero;
- personagem morre corretamente;
- mascote participa da batalha;
- mascote ofensivo registra dano;
- ataque conjunto registra dano do personagem;
- ataque conjunto consolida o dano total;
- tabela registra quantidade de rodadas;
- tabela registra maior bloqueio do personagem;
- tabela registra maior suporte do mascote;
- intervenção da mansão aplica efeito na rodada;
- retratos da mansão reduzem energia do mascote;
- inimigo sorteado é diferente do jogador.

Resultado esperado atual:

```text
Aprovados: 16
Reprovados: 0
```

## Destaques da implementação

- código comentado e organizado;
- nomes em português;
- estrutura acadêmica e didática;
- sem dependências externas;
- compatível com execução em console;
- foco em clareza e demonstração de POO;
- batalha com variedade de ações e eventos;
- tabela de rodada para leitura fácil no terminal.

## Publicação

O projeto está preparado para publicação no GitHub:

- `README` atualizado com a documentação completa;
- scripts `.bat` ajustados para execução em UTF-8;
- testes funcionando;
- estrutura final do projeto consolidada.

Caso o repositório remoto exija autenticação, basta usar a conta com permissão de escrita e executar:

```powershell
git push origin main
```

## Observações finais

Este projeto foi pensado para ser ao mesmo tempo:

- funcional como jogo em console;
- coerente com o universo da Família Addams;
- adequado para avaliação acadêmica em orientação a objetos.

Além da batalha entre personagens, o sistema destaca a interação entre objetos, o uso de `enum`, o encapsulamento, a participação dos mascotes e a mansão como parte ativa do jogo.
