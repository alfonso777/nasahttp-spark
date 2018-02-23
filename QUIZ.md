# Spark Quiz
### 1. Qual o objetivo do comando cache em Spark?
O seu objetivo é acelerar a execução fazendo com que o mesmo RDD seja usado múltiplas vezes de modo a não ter que avaliá-lo toda vez que é invocado.

### 2. O mesmo código implementado em Spark é normalmente mais rápido que a implementação equivalente em MapReduce. Por quê?
Isso acontece porque Spark realiza o processamento principalmente na memoria RAM e minimiza as operações I/O em HD. O motor de processamento do Spark usa topologias (DAG) do fluxo de tarefas/transformações a serem realizadas e só são executadas de fato quando uma ação (_reduce_) acontece. Usa as RDDs para realizar pipelines e permite a execução em paralelo.

### 3. Qual é a função do SparkContext?
É o ponto de entrada (conexão) para interagir com o ambiente de execução (máquina local, clusters, rede, etc), Configura e levanta serviços, permite criar e compartilhar variáveis na rede. Podemos rodar jobs, criar RDDs, etc.

### 4. Explique com suas palavras o que é Resilient Distributed Datasets(RDD).
É uma coleção de objetos que podem ser particionados e distribuídos sobre os clusters, permitindo desse modo o processamento em paralelo. Estas estruturas proveem de tolerância à falhas e devido ao mapa de transformações permite recuperação automática e evita replicações não necessárias.

### 5. GroupByKey é menos eficiente que reduceByKey em grandes dataset. Por quê?

GroupByKey realiza antes o shuffle sobre todas as partições e clusters anulando o poder da paralelização. Assim, quanto mais dados mais será a distribuição e por isso mais movimntações de dados a serem feitas.

### 6. Explique o que o código Scala abaixo faz.
```
val textFile = sc.textFile("hdfs://...")
val counts = textFile.flatMap(line => line.split(" "))
                .map(word => (word, 1)) 
                .reduceByKey(_ + _)
counts.saveAsTextFile("hdfs://...")
```
- Cria um RDD textFile a partir dos dados carregados em um arquivo armazenado em HDFS.
- Faz algumas transformações para ter palavras com contagem inicial de 1.
- Realiza-se o cálculo de frequencias de cada palavra.
- Por fim, o resultado é salvo e armazenado em HDFS.

