## Análise e processamento de um log de requisições da NASA

### Overview
O objetivo é processar alguns logs de requisições da _NASA Kennedy Space Center WWW Server_ e obter as respostas para as seguintes questões:
1. Número de hosts únicos.
2. O total de erros 404.
3. Os 5 URLs que mais causaram erro 404.
4. Quantidade de erros 404 por dia.
5. O total de bytes retornados.

A fonte oficial do dateset está em: http://ita.ee.lbl.gov/html/contrib/NASA-HTTP.html

### Solução
A solução foi implementada usando Scala e API do Spark (versão 2.2.0) nessa linguagem.

SBT foi usado como ferramenta de criação. O projeto pode ser fácilmente importado no IDE Intellij.

#### Como executar
* Em um ambiente Linux ou compatível (docker, vagrant, vm, etc), deve ter instalado Apache Spark e ter adicionado o $SPARK_HOME/bin no $PATH.

* Para gerar o pacote usar:
 ```sbt package```
Isso que gerará o pacote na pasta _target_.
* Rodar o script para submeter o job:
 ```sh submit.sh inputhPath  outputPath```
 Onde _inputhPath_ é a rota da pasta onde estão os datasets e _outputPath_  é a rota da pasta onde serão armazenados os resultados do processamento.

* As respostas das questões 1,2 e 5 serão armazenadas na pasta `outputPath/question125` e as repostas restantes serão armazenadas nas pastas `outputPath/question3` e `outputPath/question4`.

### Trabalhos futuros
* Nesta versão não usou-se dataframes. Em um futuro adcionarei a versão com dataframes.
