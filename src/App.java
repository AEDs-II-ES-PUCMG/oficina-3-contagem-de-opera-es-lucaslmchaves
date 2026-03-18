import java.util.Random;

/** * MIT License
 *
 * Copyright(c) 2024-255 João Caram <caram@pucminas.br>
 * Eveline Alonso Veloso
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

public class App {
    static final int[] tamanhosTesteGrande =  { 31_250_000, 62_500_000, 125_000_000, 250_000_000, 500_000_000 };
    static final int[] tamanhosTesteMedio =   {     12_500,     25_000,      50_000,     100_000,     200_000 };
    static final int[] tamanhosTestePequeno = {          3,          6,          12,          24,          48 };
    static Random aleatorio = new Random(42);
    static long operacoes;
    static double nanoToMilli = 1.0/1_000_000;

    /**
     * Código de teste 1. Este método percorre os elementos do vetor localizados em índices pares 
     * e calcula a soma dos restos da divisão desses elementos por 2.
     * Complexidade: O(N) - Linear.
     * @param vetor Vetor com dados para teste.
     * @return Uma resposta que significa a soma dos restos da divisão por 2 dos elementos em posições pares.
     */
    static int codigo1(int[] vetor) {
        int resposta = 0;
        for (int i = 0; i < vetor.length; i += 2) {
            operacoes++;
            resposta += vetor[i]%2;
        }
        return resposta;
    }

    /**
     * Código de teste 2. Este algoritmo possui um laço externo que divide o 
     * tamanho (k) pela metade a cada iteração, e um laço interno que executa 'k' vezes.
     * O número de operações forma uma Progressão Geométrica (N + N/2 + N/4 + ... + 1),
     * A complexidade final se mantém linear.
     * Complexidade: O(N) - Linear.
     * @param vetor Vetor com dados para teste.
     * @return Uma contagem total das iterações executadas.
     */
    static int codigo2(int[] vetor) {
        int contador = 0;
        for (int k = (vetor.length - 1); k > 0; k /= 2) {
            for (int i = 0; i <= k; i++) {
                operacoes++;
                contador++;
            }
        }
        return contador;
    }

    /**
     * Código de teste 3. Este método implementa o algoritmo de ordenação Selection Sort (Ordenação por Seleção).
     * Ele possui dois laços aninhados que percorrem o vetor para encontrar e reposicionar o menor elemento.
     * Complexidade: O(N²) - Quadrática.
     * @param vetor Vetor com dados para teste.
     */
    static void codigo3(int[] vetor) {
        for (int i = 0; i < vetor.length - 1; i++) {
            int menor = i;
            for (int j = i + 1; j < vetor.length; j++) {
                operacoes++;
                if (vetor[j] < vetor[menor])
                    menor = j;
            }
            int temp = vetor[i];
            vetor[i] = vetor[menor];
            vetor[menor] = temp;
        }
    }

    /**
     * Código de teste 4 (recursivo). Este método implementa o cálculo do n-ésimo termo da
     * sequência de Fibonacci de forma recursiva tradicional e não otimizada.
     * Complexidade: O(2^N) - Exponencial.
     * @param n Ponto inicial do algoritmo (termo de Fibonacci desejado).
     * @return Um inteiro que significa o valor do termo calculado.
     */
    static int codigo4(int n) {
        operacoes++;
        if (n <= 2)
            return 1;
        else
            return codigo4(n - 1) + codigo4(n - 2);
    }

    /**
     * Gerador de vetores aleatórios de tamanho pré-definido. 
     * @param tamanho Tamanho do vetor a ser criado.
     * @return Vetor com dados aleatórios, com valores entre 1 e (tamanho/2), desordenado.
     */
    static int[] gerarVetor(int tamanho){
        int[] vetor = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = aleatorio.nextInt(1, tamanho/2);
        }
        return vetor;
    }

public static void main(String[] args) {
        
        System.out.println("--- Algoritmo 1 (Teste Grande) - O(N) Linear ---");
        System.out.printf("%15s | %15s | %15s%n", "TAMANHO (N)", "OPERACOES", "TEMPO");
        System.out.println("-----------------------------------------------------");
        for (int tamanho : tamanhosTesteGrande) {
            int[] vetor = gerarVetor(tamanho);
            operacoes = 0;
            long inicio = System.nanoTime();
            codigo1(vetor);
            long fim = System.nanoTime();
            System.out.printf("%,15d | %,15d | %,12.2f ms%n", tamanho, operacoes, (fim - inicio) * nanoToMilli);
        }

        System.out.println("\n--- Algoritmo 2 (Teste Grande) - O(N) Linear ---");
        System.out.printf("%15s | %15s | %15s%n", "TAMANHO (N)", "OPERACOES", "TEMPO");
        System.out.println("-----------------------------------------------------");
        for (int tamanho : tamanhosTesteGrande) {
            int[] vetor = gerarVetor(tamanho);
            operacoes = 0;
            long inicio = System.nanoTime();
            codigo2(vetor);
            long fim = System.nanoTime();
            System.out.printf("%,15d | %,15d | %,12.2f ms%n", tamanho, operacoes, (fim - inicio) * nanoToMilli);
        }

        System.out.println("\n--- Algoritmo 3 (Teste Medio) - O(N^2) Quadratico ---");
        System.out.printf("%15s | %15s | %15s%n", "TAMANHO (N)", "OPERACOES", "TEMPO");
        System.out.println("-----------------------------------------------------");
        for (int tamanho : tamanhosTesteMedio) {
            int[] vetor = gerarVetor(tamanho);
            operacoes = 0;
            long inicio = System.nanoTime();
            codigo3(vetor);
            long fim = System.nanoTime();
            System.out.printf("%,15d | %,15d | %,12.2f ms%n", tamanho, operacoes, (fim - inicio) * nanoToMilli);
        }

        System.out.println("\n--- Algoritmo 4 (Teste Pequeno) - O(2^N) Exponencial ---");
        System.out.printf("%15s | %15s | %15s%n", "VALOR DE (N)", "OPERACOES", "TEMPO");
        System.out.println("-----------------------------------------------------");
        for (int n : tamanhosTestePequeno) {
            operacoes = 0;
            long inicio = System.nanoTime();
            codigo4(n);
            long fim = System.nanoTime();
            System.out.printf("%,15d | %,15d | %,12.2f ms%n", n, operacoes, (fim - inicio) * nanoToMilli);
        }
    }
}