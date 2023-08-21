// Clase Reglas
public class Reglas {

    public static boolean[][] aplicarReglas(boolean[][] actual) {
        boolean[][] generacion = new boolean[actual.length][actual[0].length];

        for (int i = 0; i < actual.length; i++) {
            for (int j = 0; j < actual[i].length; j++) {
                int vecinos = contarVecinos(actual, i, j);

                if (actual[i][j]) {
                    // Regla de subpoblación
                    if (vecinos < 2) {
                        generacion[i][j] = false;
                    }
                    // Regla de sobrepoblación
                    else if (vecinos > 3) {
                        generacion[i][j] = false;
                    }
                    // La célula sobrevive
                    else {
                        generacion[i][j] = true;
                    }
                } else {
                    // Regla de reproducción
                    if (vecinos == 3) {
                        generacion[i][j] = true;
                    }
                }
            }
        }
        return generacion;
    }

    private static int contarVecinos(boolean[][] actual, int i, int j) {
        int vecinos = 0;
        for (int x = i - 1; x <= i + 1; x++) {
            for (int y = j - 1; y <= j + 1; y++) {
                if (x >= 0 && x < actual.length && y >= 0 && y < actual[x].length) {
                    if (!(x == i && y == j)) {
                        if (actual[x][y]) {
                            vecinos++;
                        }
                    }
                }
            }
        }
        return vecinos;
    }
}
